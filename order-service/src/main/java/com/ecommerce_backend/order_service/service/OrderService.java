package com.ecommerce_backend.order_service.service;

import com.ecommerce_backend.order_service.client.CartClient;
import com.ecommerce_backend.order_service.client.CatalogClient;
import com.ecommerce_backend.order_service.client.InventoryClient;
import com.ecommerce_backend.order_service.client.dto.CartResponse;
import com.ecommerce_backend.order_service.client.dto.ConsumeStockRequest;
import com.ecommerce_backend.order_service.client.dto.ProductResponse;
import com.ecommerce_backend.order_service.client.dto.StockItemRequest;
import com.ecommerce_backend.order_service.dto.request.CreateOrderRequest;
import com.ecommerce_backend.order_service.dto.response.OrderResponse;
import com.ecommerce_backend.order_service.entity.Order;
import com.ecommerce_backend.order_service.entity.OrderItem;
import com.ecommerce_backend.order_service.entity.OrderStatus;
import com.ecommerce_backend.order_service.exception.EmptyCartException;
import com.ecommerce_backend.order_service.mapper.OrderMapper;
import com.ecommerce_backend.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    private final CartClient cartClient;
    private final CatalogClient catalogClient;
    private final InventoryClient inventoryClient;

    @Transactional(readOnly = true)
    public List<OrderResponse> findMyOrders(Long userId) {
        return orderRepository.findByUserIdOrderByCreatedAtDesc(userId)
                .stream()
                .map(orderMapper::toResponse)
                .toList();
    }

    @Transactional
    public OrderResponse createOrder(
            Long userId,
            String authorization,
            CreateOrderRequest request
    ) {
        // 1. Obtener carrito persistente
        CartResponse cart = cartClient.getCart(authorization);

        if (cart.items() == null || cart.items().isEmpty()) {
            throw new EmptyCartException();
        }

        // 2. Preparar pedido
        Order order = new Order();

        order.setUserId(userId);
        order.setStatus(OrderStatus.CONFIRMED);
        order.setRecipientName(request.recipientName());
        order.setPhone(request.phone());
        order.setAddress(request.address());
        order.setDistrict(request.district());
        order.setCity(request.city());
        order.setReference(request.reference());
        order.setCreatedAt(LocalDateTime.now());
        order.setItems(new ArrayList<>());

        BigDecimal total = BigDecimal.ZERO;

        List<StockItemRequest> stockItems = new ArrayList<>();

        // 3. Construir snapshot usando Catalog
        for (var cartItem : cart.items()) {
            ProductResponse product = catalogClient.getProductById(cartItem.productId());

            BigDecimal subtotal = product.price().multiply(BigDecimal.valueOf(cartItem.quantity()));

            OrderItem orderItem = new OrderItem();

            orderItem.setOrder(order);
            orderItem.setProductId(product.id());
            orderItem.setProductName(product.name());
            orderItem.setUnitPrice(product.price());
            orderItem.setQuantity(cartItem.quantity());
            orderItem.setSubtotal(subtotal);

            order.getItems().add(orderItem);

            total = total.add(subtotal);

            stockItems.add(new StockItemRequest(cartItem.productId(), cartItem.quantity()));

        }

        order.setTotal(total);

        // 4. Inventory valida TODOS y consume stock
        inventoryClient.consumeStock(new ConsumeStockRequest(stockItems));

        // 5. Guardar pedido + items
        Order savedOrder = orderRepository.save(order);

        // 6. Vaciar carrito
        cartClient.clearCart(authorization);

        return orderMapper.toResponse(savedOrder);

    }

}

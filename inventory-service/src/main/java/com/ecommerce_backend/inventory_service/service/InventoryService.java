package com.ecommerce_backend.inventory_service.service;

import com.ecommerce_backend.inventory_service.dto.request.ConsumeStockRequest;
import com.ecommerce_backend.inventory_service.dto.request.StockItemRequest;
import com.ecommerce_backend.inventory_service.dto.response.InventoryResponse;
import com.ecommerce_backend.inventory_service.entity.Inventory;
import com.ecommerce_backend.inventory_service.exception.InsufficienStockException;
import com.ecommerce_backend.inventory_service.exception.InventoryNotFoundException;
import com.ecommerce_backend.inventory_service.mapper.InventoryMapper;
import com.ecommerce_backend.inventory_service.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final InventoryMapper inventoryMapper;

    @Transactional(readOnly = true)
    public InventoryResponse findByProductId(Long productId) {
        Inventory inventory = inventoryRepository.findByProductId(productId)
                .orElseThrow(() -> new InventoryNotFoundException(productId));

        return inventoryMapper.toResponse(inventory);
    }

    @Transactional
    public void consumeStock(ConsumeStockRequest request) {
        List<Inventory> inventories = new ArrayList<>();

        // 1. Primero validamos TODO
        for (StockItemRequest item: request.items()) {
            Inventory inventory = inventoryRepository.findByProductId(item.productId())
                    .orElseThrow(() -> new InventoryNotFoundException(item.productId()));

            if (inventory.getCurrentQuantity() < item.quantity()) {
                throw new InsufficienStockException(
                        item.productId(),
                        item.quantity(),
                        inventory.getCurrentQuantity()
                );
            }

            inventories.add(inventory);
        }

        // 2. Solo si TODO es valido, descontamos
        for (int i = 0; i < request.items().size(); i++) {
            StockItemRequest item = request.items().get(i);
            Inventory inventory = inventories.get(i);

            inventory.setCurrentQuantity(inventory.getCurrentQuantity() - item.quantity());
        }

        inventoryRepository.saveAll(inventories);
    }

}

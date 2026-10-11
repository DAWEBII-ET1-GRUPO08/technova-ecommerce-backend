package com.ecommerce_backend.order_service.controller;

import com.ecommerce_backend.order_service.dto.request.CreateOrderRequest;
import com.ecommerce_backend.order_service.dto.response.OrderResponse;
import com.ecommerce_backend.order_service.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderResponse>> findMyOrders(
            Authentication authentication
    ) {
        Long userId = (Long) authentication.getPrincipal();
        return ResponseEntity.ok(orderService.findMyOrders(userId));
    }

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(
            Authentication authentication,
            @RequestHeader("Authorization") String authorization,
            @Valid @RequestBody CreateOrderRequest request
    ) {
        Long userId = (Long) authentication.getPrincipal();

        OrderResponse response = orderService.createOrder(userId, authorization, request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

}

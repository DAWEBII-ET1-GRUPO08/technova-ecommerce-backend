package com.ecommerce_backend.inventory_service.controller;

import com.ecommerce_backend.inventory_service.dto.request.ConsumeStockRequest;
import com.ecommerce_backend.inventory_service.dto.response.InventoryResponse;
import com.ecommerce_backend.inventory_service.dto.response.ProductResponse;
import com.ecommerce_backend.inventory_service.service.InventoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("/products/{productId}")
    public ResponseEntity<InventoryResponse> findByProductId(@PathVariable Long productId) {
        return ResponseEntity.ok(inventoryService.findByProductId(productId));
    }

    @PostMapping("/consume")
    public ResponseEntity<Void> consumeStock(
            @Valid @RequestBody ConsumeStockRequest request
    ) {
        inventoryService.consumeStock(request);
        return ResponseEntity.noContent().build();
    }

}

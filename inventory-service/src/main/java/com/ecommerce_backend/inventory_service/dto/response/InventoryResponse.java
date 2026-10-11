package com.ecommerce_backend.inventory_service.dto.response;

public record InventoryResponse(
        Long id,
        Long productId,
        Integer currentQuantity
) {
}

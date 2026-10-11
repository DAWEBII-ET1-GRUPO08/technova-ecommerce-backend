package com.ecommerce_backend.inventory_service.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record StockItemRequest(
        @NotNull
        Long productId,

        @NotNull
        @Positive
        Integer quantity
) {
}

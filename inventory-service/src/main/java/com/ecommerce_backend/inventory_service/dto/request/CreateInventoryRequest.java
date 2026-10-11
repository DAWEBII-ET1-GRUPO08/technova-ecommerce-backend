package com.ecommerce_backend.inventory_service.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record CreateInventoryRequest(
        @NotNull
        Long productId,

        @NotNull
        @PositiveOrZero
        Integer initialQuantity
) {
}

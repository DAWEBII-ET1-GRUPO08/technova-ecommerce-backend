package com.ecommerce_backend.inventory_service.dto.request;

import com.ecommerce_backend.inventory_service.entity.MovementType;
import jakarta.validation.constraints.NotNull;

public record MovementRequest(
        @NotNull
        MovementType type,

        @NotNull
        Integer quantity,

        String referenceId
) {
}

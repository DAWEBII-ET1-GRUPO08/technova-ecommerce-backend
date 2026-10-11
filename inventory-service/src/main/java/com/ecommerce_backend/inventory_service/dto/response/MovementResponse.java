package com.ecommerce_backend.inventory_service.dto.response;

import com.ecommerce_backend.inventory_service.entity.MovementType;

import java.time.LocalDateTime;

public record MovementResponse(
        Long id,
        Long inventoryId,
        MovementType type,
        Integer quantity,
        LocalDateTime createdAt,
        String referenceId
) {
}

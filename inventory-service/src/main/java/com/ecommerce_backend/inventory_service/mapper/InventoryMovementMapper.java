package com.ecommerce_backend.inventory_service.mapper;

import com.ecommerce_backend.inventory_service.dto.response.MovementResponse;
import com.ecommerce_backend.inventory_service.entity.InventoryMovement;
import org.springframework.stereotype.Component;

@Component
public class InventoryMovementMapper {

    public MovementResponse toResponse(InventoryMovement movement) {
        return new MovementResponse(
                movement.getId(),
                movement.getInventoryId(),
                movement.getType(),
                movement.getQuantity(),
                movement.getCreatedAt(),
                movement.getReferenceId()
        );
    }

}

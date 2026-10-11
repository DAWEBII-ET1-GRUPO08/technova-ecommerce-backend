package com.ecommerce_backend.inventory_service.mapper;

import com.ecommerce_backend.inventory_service.dto.response.InventoryResponse;
import com.ecommerce_backend.inventory_service.entity.Inventory;
import org.springframework.stereotype.Component;

@Component
public class InventoryMapper {

    public InventoryResponse toResponse(Inventory inventory) {
        return new InventoryResponse(
                inventory.getId(),
                inventory.getProductId(),
                inventory.getCurrentQuantity()
        );
    }

}

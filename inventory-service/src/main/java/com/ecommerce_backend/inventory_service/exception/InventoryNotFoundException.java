package com.ecommerce_backend.inventory_service.exception;

public class InventoryNotFoundException extends RuntimeException {
    public InventoryNotFoundException(Long productId) {
        super("Inventario para producto con Id " + productId + " no encontrado");
    }
}

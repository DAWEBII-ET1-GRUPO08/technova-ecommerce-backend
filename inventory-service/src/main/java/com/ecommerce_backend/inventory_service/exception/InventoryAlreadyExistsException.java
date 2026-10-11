package com.ecommerce_backend.inventory_service.exception;

public class InventoryAlreadyExistsException extends RuntimeException {
    public InventoryAlreadyExistsException(Long productId) {
        super("Ya existe inventario para el producto con ID " + productId);
    }
}

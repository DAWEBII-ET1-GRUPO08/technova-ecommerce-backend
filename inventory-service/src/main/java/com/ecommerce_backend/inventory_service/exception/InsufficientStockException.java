package com.ecommerce_backend.inventory_service.exception;

public class InsufficientStockException extends RuntimeException {
    public InsufficientStockException(Long productId) {
        super("Stock insuficiente para el producto con ID " + productId);
    }
}

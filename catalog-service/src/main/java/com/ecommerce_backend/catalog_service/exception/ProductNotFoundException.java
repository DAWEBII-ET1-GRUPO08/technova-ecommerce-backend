package com.ecommerce_backend.catalog_service.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(Long id) {
        super("Producto con ID: " + id + "no encontrado");
    }
}

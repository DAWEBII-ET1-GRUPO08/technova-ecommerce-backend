package com.ecommerce_backend.catalog_service.exception;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(Long id) {
        super("Categoria con ID: " + id + " no encontrado");
    }
}

package com.ecommerce_backend.inventory_service.exception;

public class InsufficienStockException extends RuntimeException {
    public InsufficienStockException(
            Long productId,
            Integer requested,
            Integer available
    ) {
        super(
                "Stock insuficiente para el producto " + productId +
                ". Solicitado: " + requested +
                ", disponible: " + available
        );
    }
}

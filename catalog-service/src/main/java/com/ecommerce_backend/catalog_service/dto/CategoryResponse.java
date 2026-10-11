package com.ecommerce_backend.catalog_service.dto;

public record CategoryResponse(
        Long id,
        String name,
        String description
) {
}

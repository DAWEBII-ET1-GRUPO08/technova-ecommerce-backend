package com.ecommerce_backend.catalog_service.dto;

import java.math.BigDecimal;

public record ProductResponse(
        Long id,
        String name,
        String description,
        BigDecimal price,
        String imageUrl,
        Long categoryId,
        String categoryName
) {
}

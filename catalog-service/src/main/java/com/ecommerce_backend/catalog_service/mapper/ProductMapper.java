package com.ecommerce_backend.catalog_service.mapper;

import com.ecommerce_backend.catalog_service.dto.ProductResponse;
import com.ecommerce_backend.catalog_service.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductResponse toResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getImageUrl(),
                product.getCategory().getId(),
                product.getCategory().getName()
        );
    }

}

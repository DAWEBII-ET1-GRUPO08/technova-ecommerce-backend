package com.ecommerce_backend.catalog_service.mapper;

import com.ecommerce_backend.catalog_service.dto.CategoryResponse;
import com.ecommerce_backend.catalog_service.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public CategoryResponse toResponse(Category category) {
        return new CategoryResponse(
                category.getId(),
                category.getName(),
                category.getDescription()
        );
    }

}

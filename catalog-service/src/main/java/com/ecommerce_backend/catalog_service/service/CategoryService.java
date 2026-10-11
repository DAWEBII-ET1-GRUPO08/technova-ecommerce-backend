package com.ecommerce_backend.catalog_service.service;

import com.ecommerce_backend.catalog_service.dto.CategoryResponse;
import com.ecommerce_backend.catalog_service.exception.CategoryNotFoundException;
import com.ecommerce_backend.catalog_service.mapper.CategoryMapper;
import com.ecommerce_backend.catalog_service.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public List<CategoryResponse> findAll() {
        return categoryRepository.findByActiveTrue()
                .stream()
                .map(categoryMapper::toResponse)
                .toList();
    }

    public void validateExists(Long id) {
        boolean exists = categoryRepository.existsById(id);

        if (!exists) throw new CategoryNotFoundException(id);
    }

}

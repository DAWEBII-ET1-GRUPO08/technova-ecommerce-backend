package com.ecommerce_backend.catalog_service.service;

import com.ecommerce_backend.catalog_service.dto.ProductResponse;
import com.ecommerce_backend.catalog_service.entity.Product;
import com.ecommerce_backend.catalog_service.exception.ProductNotFoundException;
import com.ecommerce_backend.catalog_service.mapper.ProductMapper;
import com.ecommerce_backend.catalog_service.repository.ProductRepository;
import com.ecommerce_backend.catalog_service.specifications.ProductSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public Page<ProductResponse> find(
            Pageable pageable, String search, Long categoryId,
            BigDecimal minPrice, BigDecimal maxPrice) {

        Specification<Product> filters = Specification.unrestricted();
        filters = filters.and(ProductSpecification.containsText(search));
        filters = filters.and(ProductSpecification.hasCategory(categoryId));
        filters = filters.and(ProductSpecification.priceGreaterThanOrEqual(minPrice));
        filters = filters.and(ProductSpecification.priceLessThanOrEqual(maxPrice));
        filters = filters.and(ProductSpecification.isActive());

        Page<Product> products = productRepository.findAll(filters, pageable);

        return products.map(productMapper::toResponse);
    }

    public ProductResponse findById(Long id) {
        Product product = productRepository.findById(id)
                .filter(Product::getActive)
                .orElseThrow(() -> new ProductNotFoundException(id));

        return productMapper.toResponse(product);
    }

}

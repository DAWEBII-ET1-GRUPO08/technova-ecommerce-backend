package com.ecommerce_backend.catalog_service.specifications;

import com.ecommerce_backend.catalog_service.entity.Product;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class ProductSpecification {

    public static Specification<Product> isActive() {
        return (root, query, cb) ->
                cb.isTrue(root.get("active"));
    }

    public static Specification<Product> containsText(String search) {
        return (root, query, cb) -> {
            if (search == null || search.isBlank()) {
                return cb.conjunction();
            }

            String value = "%" + search.toLowerCase() + "%";

            return cb.or(
                    cb.like(cb.lower(root.get("name")), value),
                    cb.like(cb.lower(root.get("description")), value)
            );
        };
    }

    public static Specification<Product> hasCategory(Long categoryId) {
        return (root, query, cb) ->
                categoryId == null
                        ? cb.conjunction()
                        : cb.equal(root.get("category").get("id"), categoryId);
    }

    public static Specification<Product> priceGreaterThanOrEqual(BigDecimal minPrice) {
        return (root, query, cb) ->
                minPrice == null
                        ? cb.conjunction()
                        : cb.greaterThanOrEqualTo(root.get("price"), minPrice);
    }

    public static Specification<Product> priceLessThanOrEqual(BigDecimal maxPrice) {
        return (root, query, cb) ->
                maxPrice == null
                        ? cb.conjunction()
                        : cb.lessThanOrEqualTo(root.get("price"), maxPrice);
    }

}

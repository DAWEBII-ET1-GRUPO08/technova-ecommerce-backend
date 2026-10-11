package com.ecommerce_backend.catalog_service.repository;

import com.ecommerce_backend.catalog_service.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findByActiveTrue();

}

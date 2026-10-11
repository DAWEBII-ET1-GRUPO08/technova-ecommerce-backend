package com.ecommerce_backend.inventory_service.repository;

import com.ecommerce_backend.inventory_service.entity.InventoryMovement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryMovementRepository extends JpaRepository<InventoryMovement, Long> {

    List<InventoryMovement> findByInventoryIdOrderByCreatedAtDesc(Long inventoryId);

}

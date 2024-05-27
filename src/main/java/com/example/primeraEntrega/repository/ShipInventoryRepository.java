package com.example.primeraEntrega.repository;

import com.example.primeraEntrega.model.InventarioNave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipInventoryRepository extends JpaRepository<InventarioNave, Long> {
}

package com.example.primeraEntrega.repository;

import com.example.primeraEntrega.model.StockPlaneta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlanetStockRepository extends JpaRepository<StockPlaneta, Long> {
    @Query("SELECT sp FROM StockPlaneta sp WHERE sp.planeta.id = :planetId AND sp.producto.id = :productId")
    Optional<StockPlaneta> findByPlanetIdAndProductId(@Param("planetId") Long planetId, @Param("productId") Long productId);
}

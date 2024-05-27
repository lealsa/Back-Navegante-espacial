package com.example.primeraEntrega.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.primeraEntrega.model.StockPlaneta;

public interface InventarioPlanetaRepository extends JpaRepository<StockPlaneta, Long> {

    @Query("SELECT Producto FROM InventarioPlaneta as ip JOIN Planeta as p WHERE (ip.id = p.id) and p.id = :id")
    List<StockPlaneta> buscarProductos(@Param("id") Long id);

    @Query("SELECT ip FROM InventarioPlaneta ip WHERE ip.planeta.id = :id")
    List<StockPlaneta> buscarProductosPorPlaneta(@Param("id") Long id);

}

package com.example.primeraEntrega.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.primeraEntrega.model.InventarioNave;

public interface InventarioNaveRepository extends JpaRepository<InventarioNave, Long> {


    @Query("SELECT inv FROM InventarioNave inv WHERE inv.nave.nombre = :nombre")
    List<InventarioNave> buscarProductosPorNombreNave(@Param("nombre") String nombreNave);


}

package com.example.primeraEntrega.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.primeraEntrega.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

}

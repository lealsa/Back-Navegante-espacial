package com.example.primeraEntrega.repository;

import com.example.primeraEntrega.model.Nave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipRepository extends JpaRepository<Nave, Long> {
}

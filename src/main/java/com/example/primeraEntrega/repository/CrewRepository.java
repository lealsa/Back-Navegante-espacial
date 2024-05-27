package com.example.primeraEntrega.repository;

import com.example.primeraEntrega.model.Tripulacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrewRepository extends JpaRepository<Tripulacion, Long> {
}

package com.example.primeraEntrega.repository;

import com.example.primeraEntrega.model.Planeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanetRepository extends JpaRepository<Planeta, Long> {
}

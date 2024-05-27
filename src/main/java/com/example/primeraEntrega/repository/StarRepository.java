package com.example.primeraEntrega.repository;

import com.example.primeraEntrega.model.Estrella;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StarRepository extends JpaRepository<Estrella, Long> {
}

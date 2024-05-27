package com.example.primeraEntrega.repository;

import com.example.primeraEntrega.model.AgujeroDeGusano;
import com.example.primeraEntrega.model.Estrella;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WormholeRepository extends JpaRepository<AgujeroDeGusano, Long> {
    @Query("SELECT w.estrellaFin FROM AgujeroDeGusano w WHERE w.estrellaInicio = :estrella AND w.distancia <= :maxDistance ORDER BY w.distancia ASC")
    List<Estrella> findNearbyStars(Estrella estrella, double maxDistance);
}

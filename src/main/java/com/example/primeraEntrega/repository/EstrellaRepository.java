package com.example.primeraEntrega.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.primeraEntrega.model.Estrella;
import com.example.primeraEntrega.model.Planeta;

@Repository
public interface EstrellaRepository extends JpaRepository<Estrella, Long> {

    @Query("SELECT p FROM Estrella e JOIN e.planetas p WHERE e.id = :id")
    List<Planeta> findPlanetasinEstrellas(@Param("id") Long id);

    @Query(value = "SELECT *, SQRT(POWER(coordX - :x, 2) + POWER(coordY - :y, 2) + POWER(coordZ - :z, 2)) AS distancia FROM estrella ORDER BY distancia LIMIT 10", nativeQuery = true)
    List<Estrella> findNearestStars(@Param("x") double x, @Param("y") double y, @Param("z") double z
    /* Pageable pageable */);

}

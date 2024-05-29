package com.example.primeraEntrega.repository;

import com.example.primeraEntrega.model.Game;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    @Query("SELECT g FROM Game g JOIN FETCH g.nave n WHERE n.tripulacionId IS NOT NULL")
    List<Game> findAllGamesWithNaveAndTripulacion();
}

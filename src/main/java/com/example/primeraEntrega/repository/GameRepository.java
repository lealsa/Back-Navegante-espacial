package com.example.primeraEntrega.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.primeraEntrega.model.Game;

public interface GameRepository extends JpaRepository<Game, Long> {

    @Query(value = "SELECT tiempo FROM partida WHERE id = :id", nativeQuery = true)
    public Double findGameTime(@Param("id") Long id);

    @Query(value = "SELECT puntaje FROM partida WHERE id = :id", nativeQuery = true)
    public Double findGameScore(@Param("id") Long id);

}

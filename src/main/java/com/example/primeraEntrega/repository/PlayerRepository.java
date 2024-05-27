package com.example.primeraEntrega.repository;

import com.example.primeraEntrega.model.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Jugador, Long> {
    Optional<Jugador> findByUsuarioAndContrasena(String usuario, String contrasena);
}

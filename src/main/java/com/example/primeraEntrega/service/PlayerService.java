package com.example.primeraEntrega.service;

import com.example.primeraEntrega.model.Jugador;
import com.example.primeraEntrega.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    public List<Jugador> findAllPlayers() {
        return playerRepository.findAll();
    }

    public Jugador findPlayerById(Long id) {
        return playerRepository.findById(id).orElseThrow(() -> new RuntimeException("Player not found"));
    }

    public Jugador savePlayer(Jugador jugador) {
        return playerRepository.save(jugador);
    }

    public void deletePlayer(Long id) {
        playerRepository.deleteById(id);
    }
    public Optional<Jugador> findPlayerByCredentials(String usuario, String contrasena) {
        return playerRepository.findByUsuarioAndContrasena(usuario, contrasena);
    }
}

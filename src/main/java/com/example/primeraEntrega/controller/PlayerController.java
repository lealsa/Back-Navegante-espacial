package com.example.primeraEntrega.controller;

import com.example.primeraEntrega.model.Jugador;
import com.example.primeraEntrega.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/players")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @GetMapping
    public List<Jugador> getAllPlayers() {
        return playerService.findAllPlayers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Jugador> getPlayerById(@PathVariable Long id) {
        Jugador jugador = playerService.findPlayerById(id);
        return ResponseEntity.ok(jugador);
    }

    @PostMapping
    public Jugador createPlayer(@RequestBody Jugador jugador) {
        return playerService.savePlayer(jugador);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Jugador> updatePlayer(@PathVariable Long id, @RequestBody Jugador playerDetails) {
        Jugador jugador = playerService.findPlayerById(id);
        jugador.setUsuario(playerDetails.getUsuario());
        jugador.setContrasena(playerDetails.getContrasena());
        final Jugador updatedPlayer = playerService.savePlayer(jugador);
        return ResponseEntity.ok(updatedPlayer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlayer(@PathVariable Long id) {
        playerService.deletePlayer(id);
        return ResponseEntity.ok().build();
    }
}

package com.example.primeraEntrega.controller;

import com.example.primeraEntrega.model.Jugador;
import com.example.primeraEntrega.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private PlayerService playerService;

    @PostMapping("/register")
    public ResponseEntity<Jugador> registerPlayer(@RequestBody Jugador jugador) {
        return ResponseEntity.ok(playerService.savePlayer(jugador));
    }

    @PostMapping("/login")
    public ResponseEntity<Jugador> loginPlayer(@RequestBody Jugador loginDetails) {
        return playerService.findPlayerByCredentials(loginDetails.getUsuario(), loginDetails.getContrasena())
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }
}

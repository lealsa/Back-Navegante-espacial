package com.example.primeraEntrega.controller;

import com.example.primeraEntrega.model.Game;
import com.example.primeraEntrega.model.Tripulacion;
import com.example.primeraEntrega.service.CrewService;
import com.example.primeraEntrega.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/games")
public class GameController {

    @Autowired
    private GameService gameService;

    @Autowired
    private CrewService tripulacionService;

    @GetMapping
    public List<Game> getAllGames() {
        return gameService.findAllGames();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Game> getGameById(@PathVariable Long id) {
        Game game = gameService.findGameById(id);
        return ResponseEntity.ok(game);
    }

    @PostMapping
    public Game createGame(@RequestBody Game game) {
        return gameService.saveGame(game);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Game> updateGame(@PathVariable Long id, @RequestBody Game gameDetails) {
        Game game = gameService.findGameById(id);
        game.setTime(gameDetails.getTime());
        game.setScore(gameDetails.getScore());
        game.setTimeMax(gameDetails.getTimeMax());
        game.setCuota(gameDetails.getCuota());
        game.setNave(gameDetails.getNave());
        final Game updatedGame = gameService.saveGame(game);
        return ResponseEntity.ok(updatedGame);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable Long id) {
        gameService.deleteGame(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/player/{playerId}")
    public ResponseEntity<Game> getGameByPlayerId(@PathVariable Long playerId) {
        List<Game> allGames = gameService.findAllGames();
        for (Game game : allGames) {
            Long tripulacionId = game.getTripulacionId();
            if (tripulacionId != null) {
                System.out.println(tripulacionId);
                Tripulacion tripulacion = tripulacionService.findTripulacionById(tripulacionId);
                System.out.println(tripulacion.getJugadorIds());
                if (tripulacion != null && tripulacion.getJugadorIds().contains(playerId)) {
                    return ResponseEntity.ok(game);
                }
            }
        }
        return ResponseEntity.notFound().build();
    }
}


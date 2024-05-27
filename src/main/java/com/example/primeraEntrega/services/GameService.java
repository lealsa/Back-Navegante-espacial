package com.example.primeraEntrega.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.primeraEntrega.model.Game;
import com.example.primeraEntrega.repository.GameRepository;

import io.micrometer.common.lang.NonNull;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    public void updateGameTime(Double time, Game game) {
        game.setTime(time);
        gameRepository.save(game);
    }

    public boolean calculateGameTime(Double time, Double maxTime) {
        if (time > maxTime) {
            return true;
        }
        return false;
    }

    public void updateScore(Double score, Game game) {
        game.setScore(score);
        gameRepository.save(game);
    }

    public Game buscar(@NonNull Long id) {
        return gameRepository.findById(id).orElseThrow();
    }

    public void saveGame(Game game) {
        gameRepository.save(game);
    }

    public Double getGameTime(Long id) {
        return gameRepository.findGameTime(id);
    }

    public Double getGameScore(Long id) {
        return gameRepository.findGameScore(id);
    }
}

package com.example.primeraEntrega.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.example.primeraEntrega.model.Game;

@DataJpaTest
public class GameRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private GameRepository gameRepository;

    @BeforeEach
    void setUp() {
        // Preparing a game instance to use in tests
        Game game = new Game();
        game.setId(1L);
        game.settime(150.0);
        game.setPuntaje(1000.0);
        entityManager.persist(game);
        entityManager.flush();
    }

    @Test
    void testFindGameTime() {
        Double expectedTime = 150.0;
        Double actualTime = gameRepository.findGameTime(1L);
        assertEquals(expectedTime, actualTime, "The game time should match the expected time.");
    }

    @Test
    void testFindGameScore() {
        Double expectedScore = 1000.0;
        Double actualScore = gameRepository.findGameScore(1L);
        assertEquals(expectedScore, actualScore, "The game score should match the expected score.");
    }
}

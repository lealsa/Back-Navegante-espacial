package com.example.primeraEntrega.services;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.primeraEntrega.model.Game;
import com.example.primeraEntrega.repository.GameRepository;

@SpringBootTest
public class GameServiceTests {

    @Mock
    private GameRepository gameRepository;

    @InjectMocks
    private GameService gameService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testUpdateGameTime() {
        Game game = new Game();
        gameService.updateGameTime(120.0, game);
        verify(gameRepository).save(game);
        assertEquals(120.0, game.gettime());
    }

    @Test
    public void testCalculateGameTime() {
        assertTrue(gameService.calculateGameTime(10.0, 5.0));
        assertFalse(gameService.calculateGameTime(4.0, 5.0));
    }

    @Test
    public void testUpdateScore() {
        Game game = new Game();
        gameService.updateScore(500.0, game);
        verify(gameRepository).save(game);
        assertEquals(500.0, game.getscore());
    }

    @Test
    public void testBuscar() {
        Long id = 1L;
        Game game = new Game();
        game.setId(id);
        when(gameRepository.findById(id)).thenReturn(java.util.Optional.of(game));
        Game found = gameService.buscar(id);
        assertNotNull(found);
        assertEquals(id, found.getId());
    }

    @Test
    public void testSaveGame() {
        Game game = new Game();
        gameService.saveGame(game);
        verify(gameRepository).save(game);
    }

    @Test
    public void testGetGameTime() {
        Long id = 1L;
        Double expectedTime = 150.0;
        when(gameRepository.findGameTime(id)).thenReturn(expectedTime);
        Double actualTime = gameService.getGameTime(id);
        assertEquals(expectedTime, actualTime);
    }

    @Test
    public void testGetGameScore() {
        Long id = 1L;
        Double expectedScore = 1000.0;
        when(gameRepository.findGameScore(id)).thenReturn(expectedScore);
        Double actualScore = gameService.getGameScore(id);
        assertEquals(expectedScore, actualScore);
    }
}

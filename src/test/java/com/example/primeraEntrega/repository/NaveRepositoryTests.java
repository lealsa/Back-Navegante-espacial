package com.example.primeraEntrega.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.example.primeraEntrega.model.Jugador;
import com.example.primeraEntrega.model.Nave;

@DataJpaTest
public class NaveRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private NaveRepository naveRepository;

    @BeforeEach
    void setUp() {
        // Preparando datos de prueba
        Nave nave = new Nave();
        nave.setNombre("NaveExplorer");
        
        Jugador jugador1 = new Jugador();
        jugador1.setUsuario("John");
        jugador1.setNave(nave);
        
        Jugador jugador2 = new Jugador();
        jugador2.setUsuario("Jane");
        jugador2.setNave(nave);
        
        nave.setJugadores(Arrays.asList(jugador1, jugador2));

        entityManager.persist(nave);
        entityManager.persist(jugador1);
        entityManager.persist(jugador2);
        entityManager.flush();
    }

    @Test
    void testFindEquipo() {
        List<Jugador> jugadores = naveRepository.findEquipo();
        assertNotNull(jugadores);
        assertFalse(jugadores.isEmpty());
        assertEquals(2, jugadores.size(), "Debería haber dos jugadores en la tripulación de la nave");
        assertTrue(jugadores.stream().anyMatch(j -> j.getUsuario().equals("John")));
        assertTrue(jugadores.stream().anyMatch(j -> j.getUsuario().equals("Jane")));
    }
}

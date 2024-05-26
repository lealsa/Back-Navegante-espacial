package com.example.primeraEntrega.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.example.primeraEntrega.model.Estrella;
import com.example.primeraEntrega.model.Planeta;

@DataJpaTest
public class EstrellaRepositoryTests {

    @Mock
    private TestEntityManager entityManager;

    @InjectMocks
    private EstrellaRepository estrellaRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindPlanetasInEstrellas() {
        Estrella estrella = new Estrella();
        estrella.setId(1L);
        Planeta planeta1 = new Planeta();
        Planeta planeta2 = new Planeta();
        estrella.setPlanetas(Arrays.asList(planeta1, planeta2));

        entityManager.persist(estrella);
        entityManager.persist(planeta1);
        entityManager.persist(planeta2);
        entityManager.flush();

        List<Planeta> result = estrellaRepository.findPlanetasinEstrellas(estrella.getId());
        assertEquals(2, result.size());
    }

    @Test
    void testFindNearestStars() {
        Estrella estrella1 = new Estrella();
        estrella1.setCoordenadaX(1.0);
        estrella1.setCoordenadaY(1.0);
        estrella1.setCoordenadaZ(1.0);

        Estrella estrella2 = new Estrella();
        estrella2.setCoordenadaX(2.0);
        estrella2.setCoordenadaY(2.0);
        estrella2.setCoordenadaZ(2.0);

        entityManager.persist(estrella1);
        entityManager.persist(estrella2);
        entityManager.flush();

        List<Estrella> result = estrellaRepository.findNearestStars(0.0, 0.0, 0.0);
        assertEquals(2, result.size());
    }
}

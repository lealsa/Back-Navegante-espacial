package com.example.primeraEntrega.services;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.primeraEntrega.model.Estrella;
import com.example.primeraEntrega.model.Planeta;
import com.example.primeraEntrega.repository.EstrellaRepository;

@SpringBootTest
public class EstrellaServiceTests {

    @Mock
    private EstrellaRepository estrellaRepository;

    @InjectMocks
    private EstrellaService estrellaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testListarEstrellas() {
        Estrella estrella1 = new Estrella(); // Configura propiedades si es necesario
        Estrella estrella2 = new Estrella();
        when(estrellaRepository.findAll()).thenReturn(Arrays.asList(estrella1, estrella2));

        List<Estrella> estrellas = estrellaService.listarEstrellas();

        assertNotNull(estrellas);
        assertEquals(2, estrellas.size());
        verify(estrellaRepository).findAll();
    }

    @Test
    public void testListarEstrellasCercanas() {
        Estrella estrella1 = new Estrella();
        when(estrellaRepository.findNearestStars(1.0, 1.0, 1.0)).thenReturn(Arrays.asList(estrella1));

        List<Estrella> estrellas = estrellaService.listarEstrellasCercanas(1.0, 1.0, 1.0);

        assertNotNull(estrellas);
        assertFalse(estrellas.isEmpty());
        assertEquals(1, estrellas.size());
        verify(estrellaRepository).findNearestStars(1.0, 1.0, 1.0);
    }

    @Test
    public void testBuscarEstrellaExistente() {
        Estrella estrella = new Estrella();
        estrella.setId(1L);
        when(estrellaRepository.findById(1L)).thenReturn(Optional.of(estrella));

        Estrella encontrada = estrellaService.buscar(1L);

        assertNotNull(encontrada);
        assertEquals(1L, encontrada.getId());
    }

    @Test
    public void testGuardarEstrella() {
        Estrella estrella = new Estrella();
        estrellaService.guardarEstrella(estrella);
        verify(estrellaRepository).save(estrella);
    }

    @Test
    public void testEliminarEstrella() {
        estrellaService.eliminarEstrella(1L);
        verify(estrellaRepository).deleteById(1L);
    }

    @Test
    public void testListarPlanetasPorEstrella() {
        Planeta planeta1 = new Planeta();
        when(estrellaRepository.findPlanetasinEstrellas(1L)).thenReturn(Arrays.asList(planeta1));

        List<Planeta> planetas = estrellaService.listarPlanetasPorEstrellas(1L);

        assertNotNull(planetas);
        assertFalse(planetas.isEmpty());
        assertEquals(1, planetas.size());
        verify(estrellaRepository).findPlanetasinEstrellas(1L);
    }
}

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

import com.example.primeraEntrega.model.InventarioPlaneta;
import com.example.primeraEntrega.model.Planeta;
import com.example.primeraEntrega.repository.PlanetaRepository;

@SpringBootTest
public class PlanetaServiceTests {

    @Mock
    private PlanetaRepository planetaRepository;

    @InjectMocks
    private PlanetaService planetaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testListarPlanetas() {
        Planeta planeta1 = new Planeta();
        Planeta planeta2 = new Planeta();
        when(planetaRepository.findAll()).thenReturn(Arrays.asList(planeta1, planeta2));

        List<Planeta> planetas = planetaService.listarPlanetas();

        assertNotNull(planetas);
        assertEquals(2, planetas.size());
        verify(planetaRepository).findAll();
    }

    @Test
    public void testBuscarPlaneta() {
        Long id = 1L;
        Planeta planeta = new Planeta();
        planeta.setId(id);
        when(planetaRepository.findById(id)).thenReturn(Optional.of(planeta));

        Planeta found = planetaService.buscarPlaneta(id);

        assertNotNull(found);
        assertEquals(id, found.getId());
    }

    @Test
    public void testBuscarPlanetaOptional() {
        Long id = 2L;
        Planeta planeta = new Planeta();
        planeta.setId(id);
        when(planetaRepository.findById(id)).thenReturn(Optional.of(planeta));

        Optional<Planeta> result = planetaService.buscarPlanetaOptional(id);

        assertTrue(result.isPresent());
        assertEquals(id, result.get().getId());
    }

    @Test
    public void testGuardarPlaneta() {
        Planeta planeta = new Planeta();
        planetaService.guardarPlaneta(planeta);
        verify(planetaRepository).save(planeta);
    }

    @Test
    public void testActualizarPlaneta() {
        Planeta planeta = new Planeta();
        planeta.setId(3L);
        planeta.setNombre("Nuevo Nombre");
        when(planetaRepository.findById(planeta.getId())).thenReturn(Optional.of(new Planeta()));

        planetaService.actualizarPlaneta(planeta);

        verify(planetaRepository).save(planeta);
        assertEquals("Nuevo Nombre", planeta.getNombre());
    }

    @Test
    public void testEliminarPlaneta() {
        Long id = 4L;
        planetaService.eliminarPlaneta(id);
        verify(planetaRepository).deleteById(id);
    }

    @Test
    public void testCrearInventario() {
        Planeta planeta = new Planeta();
        InventarioPlaneta iplaneta = new InventarioPlaneta();
        planetaService.crearInventario(iplaneta, planeta);

        assertTrue(planeta.getInventario().contains(iplaneta));
        verify(planetaRepository).save(planeta);
    }
}

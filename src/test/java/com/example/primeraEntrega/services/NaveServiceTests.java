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

import com.example.primeraEntrega.model.InventarioNave;
import com.example.primeraEntrega.model.Jugador;
import com.example.primeraEntrega.model.Nave;
import com.example.primeraEntrega.repository.NaveRepository;

@SpringBootTest
public class NaveServiceTests {

    @Mock
    private NaveRepository naveRepository;

    @InjectMocks
    private NaveService naveService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testListarNaves() {
        Nave nave1 = new Nave();
        Nave nave2 = new Nave();
        when(naveRepository.findAll()).thenReturn(Arrays.asList(nave1, nave2));

        List<Nave> naves = naveService.listarNaves();

        assertNotNull(naves);
        assertEquals(2, naves.size());
        verify(naveRepository).findAll();
    }

    @Test
    public void testBuscarNave() {
        String nombre = "Nave001";
        Nave nave = new Nave();
        nave.setNombre(nombre);
        when(naveRepository.findById(nombre)).thenReturn(Optional.of(nave));

        Nave found = naveService.buscarNave(nombre);

        assertNotNull(found);
        assertEquals(nombre, found.getNombre());
    }

    @Test
    public void testBuscarNaveOptional() {
        String nombre = "Nave002";
        Nave nave = new Nave();
        nave.setNombre(nombre);
        when(naveRepository.findById(nombre)).thenReturn(Optional.of(nave));

        Optional<Nave> result = naveService.buscarNaveOptional(nombre);

        assertTrue(result.isPresent());
        assertEquals(nombre, result.get().getNombre());
    }

    @Test
    public void testGuardarNave() {
        Nave nave = new Nave();
        naveService.guardarNave(nave);
        verify(naveRepository).save(nave);
    }

    @Test
    public void testActualizarNave() {
        Nave nave = new Nave();
        nave.setNombre("Nave003");
        nave.setDinero(1000.0);
        when(naveRepository.findById("Nave003")).thenReturn(Optional.of(new Nave()));

        naveService.actualizarNave(nave);

        verify(naveRepository).save(nave);
        assertEquals(1000.0, nave.getDinero());
    }

    @Test
    public void testEliminarNave() {
        String nombre = "Nave004";
        naveService.eliminarNave(nombre);
        verify(naveRepository).deleteById(nombre);
    }

    @Test
    public void testListarEquipo() {
        Jugador jugador1 = new Jugador();
        Jugador jugador2 = new Jugador();
        when(naveRepository.findEquipo()).thenReturn(Arrays.asList(jugador1, jugador2));

        List<Jugador> equipo = naveService.listarEquipo();

        assertNotNull(equipo);
        assertEquals(2, equipo.size());
    }

    @Test
    public void testActualizarCoordenadasNave() {
        Nave nave = new Nave();
        naveService.actualizarCoordenadasNave(1.0, 2.0, 3.0, nave);

        verify(naveRepository).save(nave);
        assertEquals(1.0, nave.getCoordenadaX());
        assertEquals(2.0, nave.getCoordenadaY());
        assertEquals(3.0, nave.getCoordenadaZ());
    }

    @Test
    public void testCrearInventario() {
        Nave nave = new Nave();
        InventarioNave inave = new InventarioNave();
        naveService.crearInventario(inave, nave);

        assertTrue(nave.getInventario().contains(inave));
        verify(naveRepository).save(nave);
    }
}

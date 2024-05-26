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

import com.example.primeraEntrega.model.Jugador;
import com.example.primeraEntrega.repository.JugadorRepository;

@SpringBootTest
public class JugadorServiceTests {

    @Mock
    private JugadorRepository jugadorRepository;

    @InjectMocks
    private JugadorService jugadorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testListarJugadores() {
        Jugador jugador1 = new Jugador();
        Jugador jugador2 = new Jugador();
        when(jugadorRepository.findAll()).thenReturn(Arrays.asList(jugador1, jugador2));

        List<Jugador> jugadores = jugadorService.listarJugadores();

        assertNotNull(jugadores);
        assertEquals(2, jugadores.size());
        verify(jugadorRepository).findAll();
    }

    @Test
    public void testBuscarJugador() {
        Long id = 1L;
        Jugador jugador = new Jugador();
        jugador.setId(id);
        when(jugadorRepository.findById(id)).thenReturn(Optional.of(jugador));

        Jugador found = jugadorService.buscarJugador(id);

        assertNotNull(found);
        assertEquals(id, found.getId());
    }

    @Test
    public void testActualizarJugador() {
        Jugador jugador = new Jugador();
        jugador.setId(1L);
        jugador.setUsuario("usuarioActualizado");
        jugador.setContrasena("contrasenaActualizada");
        jugador.setRol("rolActualizado");

        when(jugadorRepository.findById(1L)).thenReturn(Optional.of(new Jugador()));

        jugadorService.actualizarJuagdor(jugador);

        verify(jugadorRepository).save(jugador);
        assertEquals("usuarioActualizado", jugador.getUsuario());
        assertEquals("contrasenaActualizada", jugador.getContrasena());
        assertEquals("rolActualizado", jugador.getRol());
    }

    @Test
    public void testGuardarJugador() {
        Jugador jugador = new Jugador();
        jugadorService.guardarJugador(jugador);
        verify(jugadorRepository).save(jugador);
    }

    @Test
    public void testEliminarJugador() {
        Long id = 1L;
        jugadorService.eliminarJugador(id);
        verify(jugadorRepository).deleteById(id);
    }
}

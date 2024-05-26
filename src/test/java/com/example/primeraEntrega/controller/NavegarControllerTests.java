package com.example.primeraEntrega.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.primeraEntrega.model.Estrella;
import com.example.primeraEntrega.model.Nave;
import com.example.primeraEntrega.services.EstrellaService;
import com.example.primeraEntrega.services.NaveService;
import com.example.primeraEntrega.services.GameService;

@WebMvcTest(NavegarController.class)
public class NavegarControllerTests {

    private MockMvc mockMvc;

    @MockBean
    private EstrellaService estrellaService;

    @MockBean
    private NaveService naveService;

    @MockBean
    private GameService gameService;

    @InjectMocks
    private NavegarController navegarController;

    @BeforeEach
    void setUp() {
        mockMvc = standaloneSetup(navegarController).build();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testListarEstrellas() throws Exception {
        Estrella estrella1 = new Estrella();
        estrella1.setId(1L);
        Estrella estrella2 = new Estrella();
        estrella2.setId(2L);
        
        when(naveService.buscarNaveOptional("nave1")).thenReturn(Optional.of(new Nave()));
        when(estrellaService.listarEstrellasCercanas(anyDouble(), anyDouble(), anyDouble())).thenReturn(Arrays.asList(estrella1, estrella2));

        mockMvc.perform(get("/api/navegar/list"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].id").value("1"))
            .andExpect(jsonPath("$[1].id").value("2"));
    }

    @Test
    public void testCambiarCoordenadasNave() throws Exception {
        Long estrellaId = 1L;
        Estrella estrella = new Estrella();
        estrella.setCoordenadaX(1.0);
        estrella.setCoordenadaY(2.0);
        estrella.setCoordenadaZ(3.0);
        Nave nave = new Nave();
        nave.setNombre("nave1");

        when(estrellaService.buscar(estrellaId)).thenReturn(estrella);
        when(naveService.buscarNave("nave1")).thenReturn(nave);

        mockMvc.perform(post("/api/navegar/desplazar-nave/{estrellaId}", estrellaId))
            .andExpect(status().isOk());

        verify(naveService).actualizarCoordenadasNave(1.0, 2.0, 3.0, nave);
    }
}

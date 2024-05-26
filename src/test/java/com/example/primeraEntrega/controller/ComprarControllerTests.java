package com.example.primeraEntrega.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import com.example.primeraEntrega.dto.InformacionCompraProductoDTO;
import com.example.primeraEntrega.services.InventarioPlanetaService;
import com.example.primeraEntrega.services.NaveService;
import com.example.primeraEntrega.services.GameService;

@WebMvcTest(ComprarController.class)
public class ComprarControllerTests {

    private MockMvc mockMvc;

    @MockBean
    private InventarioPlanetaService inventarioPlanetaService;

    @MockBean
    private GameService gameService;

    @MockBean
    private NaveService naveService;

    @InjectMocks
    private ComprarController comprarController;

    @BeforeEach
    void setUp() {
        mockMvc = standaloneSetup(comprarController).build();
    }

    @Test
    public void testListarProductos() throws Exception {
        Long planetaId = 1L;
        InformacionCompraProductoDTO dto = new InformacionCompraProductoDTO(1L, "Producto1", 10.0, 100.0, 10.0);
        when(inventarioPlanetaService.listarInformacionCompraProducto(planetaId)).thenReturn(Arrays.asList(dto));

        mockMvc.perform(get("/api/comprar/list/{id}", planetaId))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].idInventario").value(dto.getIdInventario()));
    }


    @Test
    public void testActualizarPuntaje() throws Exception {
        Long juegoId = 1L;
        mockMvc.perform(post("/api/comprar/actualizar-puntaje/{id}", juegoId))
            .andExpect(status().isOk());

        verify(gameService).updateScore(anyDouble(), any());
    }

    @Test
    public void testObtenerPuntaje() throws Exception {
        when(gameService.getGameScore(anyLong())).thenReturn(100.0);

        mockMvc.perform(get("/api/comprar/obtener-puntaje"))
            .andExpect(status().isOk())
            .andExpect(content().string("100.0"));
    }
}


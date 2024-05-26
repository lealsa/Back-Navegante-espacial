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

import com.example.primeraEntrega.dto.InformacionCompraProductoDTO;
import com.example.primeraEntrega.model.InventarioPlaneta;
import com.example.primeraEntrega.model.Producto;
import com.example.primeraEntrega.repository.InventarioPlanetaRepository;

@SpringBootTest
public class InventarioPlanetaServiceTests {

    @Mock
    private InventarioPlanetaRepository inventarioPlanetaRepository;

    @InjectMocks
    private InventarioPlanetaService inventarioPlanetaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testListarInventarioPlaneta() {
        InventarioPlaneta inventario1 = new InventarioPlaneta();
        InventarioPlaneta inventario2 = new InventarioPlaneta();
        when(inventarioPlanetaRepository.findAll()).thenReturn(Arrays.asList(inventario1, inventario2));

        List<InventarioPlaneta> inventario = inventarioPlanetaService.listarInventarioPlaneta();

        assertNotNull(inventario);
        assertEquals(2, inventario.size());
        verify(inventarioPlanetaRepository).findAll();
    }

    @Test
    public void testBuscarInventario() {
        Long id = 1L;
        InventarioPlaneta inventario = new InventarioPlaneta();
        inventario.setId(id);
        when(inventarioPlanetaRepository.findById(id)).thenReturn(Optional.of(inventario));

        InventarioPlaneta found = inventarioPlanetaService.buscarInventario(id);

        assertNotNull(found);
        assertEquals(id, found.getId());
    }

    @Test
    public void testActualizarInventario() {
        InventarioPlaneta inventario = new InventarioPlaneta();
        inventario.setId(1L);
        inventario.setCantidad(10.0);
        inventario.setfOfertaDemanda(100.0);

        when(inventarioPlanetaRepository.findById(1L)).thenReturn(Optional.of(inventario));

        inventarioPlanetaService.actualizarInventario(inventario);

        verify(inventarioPlanetaRepository).save(inventario);
        assertEquals(10.0, inventario.getCantidad());
        assertEquals(100.0, inventario.getfOfertaDemanda());
    }

    @Test
    public void testCambiarCantidadInventario() {
        InventarioPlaneta inventario = new InventarioPlaneta();
        inventario.setId(1L);
        inventario.setCantidad(50.0);

        inventarioPlanetaService.cambiarCantidadInventario(100.0, inventario);

        verify(inventarioPlanetaRepository).save(inventario);
        assertEquals(100.0, inventario.getCantidad());
    }

    @Test
    public void testGuardarInventario() {
        InventarioPlaneta inventario = new InventarioPlaneta();
        inventarioPlanetaService.guardarInventario(inventario);
        verify(inventarioPlanetaRepository).save(inventario);
    }

    @Test
    public void testEliminarInventario() {
        inventarioPlanetaService.eliminarInventario(1L);
        verify(inventarioPlanetaRepository).deleteById(1L);
    }

    @Test
    public void testListarInformacionCompraProducto() {
        Long id = 1L;
        InventarioPlaneta ip = new InventarioPlaneta();
        Producto prod = new Producto();
        prod.setTipo("tipo");
        prod.setPrecio(200.0);

        ip.setProducto(prod);
        ip.setCantidad(5.0);
        ip.setfOfertaDemanda(500.0);
        ip.setId(id);

        when(inventarioPlanetaRepository.buscarProductosPorPlaneta(id)).thenReturn(Arrays.asList(ip));

        List<InformacionCompraProductoDTO> result = inventarioPlanetaService.listarInformacionCompraProducto(id);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        InformacionCompraProductoDTO dto = result.get(0);
        assertEquals("tipo", dto.getNombreProducto());
        assertEquals(5.0, dto.getCantidad());
        assertEquals(500.0 / (1 + 5.0), dto.getPrecio());
    }
}

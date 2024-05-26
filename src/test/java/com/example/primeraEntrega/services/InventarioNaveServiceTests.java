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

import com.example.primeraEntrega.dto.InformacionVentaProductoDTO;
import com.example.primeraEntrega.model.InventarioNave;
import com.example.primeraEntrega.model.Producto;
import com.example.primeraEntrega.repository.InventarioNaveRepository;

@SpringBootTest
public class InventarioNaveServiceTests {

    @Mock
    private InventarioNaveRepository inventarioNaveRepository;

    @InjectMocks
    private InventarioNaveService inventarioNaveService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testListarInventarioNave() {
        InventarioNave inventario1 = new InventarioNave();
        InventarioNave inventario2 = new InventarioNave();
        when(inventarioNaveRepository.findAll()).thenReturn(Arrays.asList(inventario1, inventario2));

        List<InventarioNave> inventario = inventarioNaveService.listarInventarioNave();

        assertNotNull(inventario);
        assertEquals(2, inventario.size());
        verify(inventarioNaveRepository).findAll();
    }

    @Test
    public void testBuscarInventario() {
        Long id = 1L;
        InventarioNave inventario = new InventarioNave();
        inventario.setId(id);
        when(inventarioNaveRepository.findById(id)).thenReturn(Optional.of(inventario));

        InventarioNave found = inventarioNaveService.buscarInventario(id);

        assertNotNull(found);
        assertEquals(id, found.getId());
    }

    @Test
    public void testActualizarInventario() {
        InventarioNave inventario = new InventarioNave();
        inventario.setId(1L);
        inventario.setCantidad(10.0);

        when(inventarioNaveRepository.findById(1L)).thenReturn(Optional.of(inventario));

        inventarioNaveService.actualizarInventario(inventario);

        verify(inventarioNaveRepository).save(inventario);
        assertEquals(10, inventario.getCantidad());
    }

    @Test
    public void testGuardarInventario() {
        InventarioNave inventario = new InventarioNave();
        inventarioNaveService.guardarInventario(inventario);
        verify(inventarioNaveRepository).save(inventario);
    }

    @Test
    public void testEliminarInventario() {
        inventarioNaveService.eliminarInventario(1L);
        verify(inventarioNaveRepository).deleteById(1L);
    }

    @Test
    public void testListarInformacionVentaProducto() {
        String nombreNave = "nave1";
        InventarioNave inv = new InventarioNave();
        Producto prod = new Producto();
        prod.setTipo("tipo");
        prod.setPrecio(100.0);
        prod.setVolumen(5.0);

        inv.setProducto(prod);
        inv.setCantidad(10.0);
        inv.setfOfertaDemanda(100.0);
        inv.setId(1L);

        when(inventarioNaveRepository.buscarProductosPorNombreNave(nombreNave)).thenReturn(Arrays.asList(inv));

        List<InformacionVentaProductoDTO> result = inventarioNaveService.listarInformacionVentaProducto(nombreNave);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals("tipo", result.get(0).getNombreProducto());
        assertEquals(10, result.get(0).getCantidad());
    }

    @Test
    public void testCalcularVolumenTotal() {
        InventarioNave inv = new InventarioNave();
        Producto prod = new Producto();
        prod.setVolumen(5.0);
        inv.setProducto(prod);

        when(inventarioNaveRepository.buscarProductosPorNombreNave("nave0")).thenReturn(Arrays.asList(inv, inv));

        Double vol = inventarioNaveService.calcularVolumenTotal(Arrays.asList(inv, inv));

        assertEquals(10.0, vol, 0.01);
    }
}

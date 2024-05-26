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

import com.example.primeraEntrega.model.Producto;
import com.example.primeraEntrega.repository.ProductoRepository;

@SpringBootTest
public class ProductoServiceTests {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoService productoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testListarProductos() {
        Producto producto1 = new Producto();
        Producto producto2 = new Producto();
        when(productoRepository.findAll()).thenReturn(Arrays.asList(producto1, producto2));

        List<Producto> productos = productoService.listarProductos();

        assertNotNull(productos);
        assertEquals(2, productos.size());
        verify(productoRepository).findAll();
    }

    @Test
    public void testBuscarProducto() {
        Long id = 1L;
        Producto producto = new Producto();
        producto.setId(id);
        when(productoRepository.findById(id)).thenReturn(Optional.of(producto));

        Producto found = productoService.buscarProducto(id);

        assertNotNull(found);
        assertEquals(id, found.getId());
    }

    @Test
    public void testBuscarProductoOptional() {
        Long id = 2L;
        Producto producto = new Producto();
        producto.setId(id);
        when(productoRepository.findById(id)).thenReturn(Optional.of(producto));

        Optional<Producto> result = productoService.buscarProductoOptional(id);

        assertTrue(result.isPresent());
        assertEquals(id, result.get().getId());
    }

    @Test
    public void testGuardarProducto() {
        Producto producto = new Producto();
        productoService.guardarProducto(producto);
        verify(productoRepository).save(producto);
    }

    @Test
    public void testEliminarProducto() {
        Long id = 3L;
        productoService.eliminarProducto(id);
        verify(productoRepository).deleteById(id);
    }
}

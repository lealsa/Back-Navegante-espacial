package com.example.primeraEntrega.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.example.primeraEntrega.model.InventarioPlaneta;
import com.example.primeraEntrega.model.Planeta;

@DataJpaTest
public class InventarioPlanetaRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private InventarioPlanetaRepository inventarioPlanetaRepository;

    @BeforeEach
    void setUp() {
        // Preparando datos de prueba
        Planeta planeta = new Planeta();
        planeta.setId(1L);
        entityManager.persist(planeta);

        InventarioPlaneta inventario1 = new InventarioPlaneta();
        inventario1.setPlaneta(planeta);
        InventarioPlaneta inventario2 = new InventarioPlaneta();
        inventario2.setPlaneta(planeta);

        entityManager.persist(inventario1);
        entityManager.persist(inventario2);
        entityManager.flush();
    }

    @Test
    void testBuscarProductos() {
        Long planetaId = 1L;
        List<InventarioPlaneta> productos = inventarioPlanetaRepository.buscarProductos(planetaId);
        assertNotNull(productos);
        assertFalse(productos.isEmpty());
        assertEquals(2, productos.size(), "Debería haber dos inventarios para el planeta");
    }

    @Test
    void testBuscarProductosPorPlaneta() {
        Long planetaId = 1L;
        List<InventarioPlaneta> productos = inventarioPlanetaRepository.buscarProductosPorPlaneta(planetaId);
        assertNotNull(productos);
        assertFalse(productos.isEmpty());
        assertEquals(2, productos.size(), "Debería haber dos inventarios para el planeta");
    }
}

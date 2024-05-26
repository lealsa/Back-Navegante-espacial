package com.example.primeraEntrega.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.example.primeraEntrega.model.InventarioNave;
import com.example.primeraEntrega.model.Nave;

@DataJpaTest
public class InventarioNaveRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private InventarioNaveRepository inventarioNaveRepository;

    @BeforeEach
    void setUp() {
        // Preparando datos de prueba
        Nave nave = new Nave();
        nave.setNombre("NaveExplorer");
        entityManager.persist(nave);

        InventarioNave inv1 = new InventarioNave();
        inv1.setNave(nave);
        InventarioNave inv2 = new InventarioNave();
        inv2.setNave(nave);

        entityManager.persist(inv1);
        entityManager.persist(inv2);
        entityManager.flush();
    }

    @Test
    void testBuscarProductosPorNombreNave() {
        List<InventarioNave> inventario = inventarioNaveRepository.buscarProductosPorNombreNave("NaveExplorer");
        assertNotNull(inventario);
        assertFalse(inventario.isEmpty());
        assertEquals(2, inventario.size());
    }
}

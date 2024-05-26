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

import com.example.primeraEntrega.model.TipoNave;
import com.example.primeraEntrega.repository.TipoNaveRepository;

@SpringBootTest
public class ServicioTipoNaveTests {

    @Mock
    private TipoNaveRepository tipoNaveRepository;

    @InjectMocks
    private ServicioTipoNave servicioTipoNave;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testListarTipoNaves() {
        TipoNave tipoNave1 = new TipoNave();
        TipoNave tipoNave2 = new TipoNave();
        when(tipoNaveRepository.findAll()).thenReturn(Arrays.asList(tipoNave1, tipoNave2));

        List<TipoNave> tipoNaves = servicioTipoNave.listarTipoNaves();

        assertNotNull(tipoNaves);
        assertEquals(2, tipoNaves.size());
        verify(tipoNaveRepository).findAll();
    }

    @Test
    public void testBuscarTipoNave() {
        Long id = 1L;
        TipoNave tipoNave = new TipoNave();
        tipoNave.setId(id);
        when(tipoNaveRepository.findById(id)).thenReturn(Optional.of(tipoNave));

        TipoNave found = servicioTipoNave.buscar(id);

        assertNotNull(found);
        assertEquals(id, found.getId());
    }

    @Test
    public void testBuscarTipoNaveOptional() {
        Long id = 2L;
        TipoNave tipoNave = new TipoNave();
        tipoNave.setId(id);
        when(tipoNaveRepository.findById(id)).thenReturn(Optional.of(tipoNave));

        Optional<TipoNave> result = servicioTipoNave.buscarTipoNaveOptional(id);

        assertTrue(result.isPresent());
        assertEquals(id, result.get().getId());
    }

    @Test
    public void testGuardarTipoNave() {
        TipoNave tipoNave = new TipoNave();
        servicioTipoNave.guardarTipoNave(tipoNave);
        verify(tipoNaveRepository).save(tipoNave);
    }

    @Test
    public void testEliminarTipoNave() {
        Long id = 3L;
        servicioTipoNave.eliminarTipoNave(id);
        verify(tipoNaveRepository).deleteById(id);
    }
}

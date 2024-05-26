package com.example.primeraEntrega.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.primeraEntrega.model.TipoNave;
import com.example.primeraEntrega.repository.TipoNaveRepository;

import io.micrometer.common.lang.NonNull;

@Service
public class ServicioTipoNave {
    @Autowired
    private TipoNaveRepository tipoNaveRepositorio;

    public List<TipoNave> listarTipoNaves() {
        return tipoNaveRepositorio.findAll();
    }

    public TipoNave buscar(@NonNull Long id) {
        return tipoNaveRepositorio.findById(id).orElseThrow();
    }

    public Optional<TipoNave> buscarTipoNaveOptional(Long id) {
        return tipoNaveRepositorio.findById(id);
    }

    public void guardarTipoNave(TipoNave tipoNavecita) {
        tipoNaveRepositorio.save(tipoNavecita);
    }

    public void eliminarTipoNave(Long id) {
        tipoNaveRepositorio.deleteById(id);
    }

}

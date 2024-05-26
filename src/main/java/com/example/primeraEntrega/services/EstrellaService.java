package com.example.primeraEntrega.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.primeraEntrega.repository.EstrellaRepository;
import io.micrometer.common.lang.NonNull;
import com.example.primeraEntrega.model.Estrella;
import com.example.primeraEntrega.model.Planeta;

@Service
public class EstrellaService {
    @Autowired
    private EstrellaRepository estrellaRepositorio;

    public List<Estrella> listarEstrellas() {
        return estrellaRepositorio.findAll();
    }

    public List<Estrella> listarEstrellasCercanas(Double x, Double y, Double z) {
        return estrellaRepositorio.findNearestStars(x, y, z);
    }

    public Estrella buscar(@NonNull Long id) {
        return estrellaRepositorio.findById(id).orElseThrow();
    }
    public void guardarEstrella(Estrella estrellita) {
        estrellaRepositorio.save(estrellita);
    }

    public void eliminarEstrella(Long id) {
        estrellaRepositorio.deleteById(id);
    }

    public List<Planeta> listarPlanetasPorEstrellas(Long id) {
        return estrellaRepositorio.findPlanetasinEstrellas(id);
    }

}
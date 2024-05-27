package com.example.primeraEntrega.service;

import com.example.primeraEntrega.model.Planeta;
import com.example.primeraEntrega.repository.PlanetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanetService {

    @Autowired
    private PlanetRepository planetRepository;

    public List<Planeta> findAllPlanets() {
        return planetRepository.findAll();
    }

    public Planeta findPlanetById(Long id) {
        return planetRepository.findById(id).orElseThrow(() -> new RuntimeException("Planet not found"));
    }

    public Planeta savePlanet(Planeta planeta) {
        return planetRepository.save(planeta);
    }

    public void deletePlanet(Long id) {
        planetRepository.deleteById(id);
    }
}

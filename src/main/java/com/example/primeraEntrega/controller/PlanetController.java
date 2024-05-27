package com.example.primeraEntrega.controller;

import com.example.primeraEntrega.model.Planeta;
import com.example.primeraEntrega.service.PlanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/planets")
public class PlanetController {

    @Autowired
    private PlanetService planetService;

    @GetMapping
    public List<Planeta> getAllPlanets() {
        return planetService.findAllPlanets();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Planeta> getPlanetById(@PathVariable Long id) {
        Planeta planeta = planetService.findPlanetById(id);
        return ResponseEntity.ok(planeta);
    }

    @PostMapping
    public Planeta createPlanet(@RequestBody Planeta planeta) {
        return planetService.savePlanet(planeta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Planeta> updatePlanet(@PathVariable Long id, @RequestBody Planeta planetDetails) {
        Planeta planeta = planetService.findPlanetById(id);
        planeta.setNombre(planetDetails.getNombre());
        planeta.setImagen(planetDetails.getImagen());
        planeta.setEstrella(planetDetails.getEstrella());
        final Planeta updatedPlanet = planetService.savePlanet(planeta);
        return ResponseEntity.ok(updatedPlanet);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlanet(@PathVariable Long id) {
        planetService.deletePlanet(id);
        return ResponseEntity.ok().build();
    }
}

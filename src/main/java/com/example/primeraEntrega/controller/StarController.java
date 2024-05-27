package com.example.primeraEntrega.controller;

import com.example.primeraEntrega.model.Estrella;
import com.example.primeraEntrega.service.StarService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stars")
public class StarController {

    @Autowired
    private StarService starService;

    @GetMapping
    public List<Estrella> getAllStars() {
        return starService.findAllStars();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estrella> getStarById(@PathVariable Long id) {
        Estrella estrella = starService.findStarById(id);
        return ResponseEntity.ok(estrella);
    }

    @PostMapping
    public Estrella createStar(@RequestBody Estrella estrella) {
        return starService.saveStar(estrella);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estrella> updateStar(@PathVariable Long id, @RequestBody Estrella estrellaDetails) {
        Estrella estrella = starService.findStarById(id);
        estrella.setNombreEstrella(estrellaDetails.getNombreEstrella());
        estrella.setCoordenadaX(estrellaDetails.getCoordenadaX());
        estrella.setCoordenadaY(estrellaDetails.getCoordenadaY());
        estrella.setCoordenadaZ(estrellaDetails.getCoordenadaZ());
        estrella.setImagen(estrellaDetails.getImagen());
        final Estrella updatedEstrella = starService.saveStar(estrella);
        return ResponseEntity.ok(updatedEstrella);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStar(@PathVariable Long id) {
        starService.deleteStar(id);
        return ResponseEntity.ok().build();
    }
}

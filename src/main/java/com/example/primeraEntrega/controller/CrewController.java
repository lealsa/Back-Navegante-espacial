package com.example.primeraEntrega.controller;

import com.example.primeraEntrega.DTO.JoinCrewRequest;
import com.example.primeraEntrega.model.Tripulacion;
import com.example.primeraEntrega.service.CrewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/crews")
public class CrewController {

    @Autowired
    private CrewService crewService;

    @GetMapping
    public List<Tripulacion> getAllCrews() {
        return crewService.findAllCrews();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tripulacion> getCrewById(@PathVariable Long id) {
        Tripulacion tripulacion = crewService.findCrewById(id);
        if (tripulacion != null) {
            return ResponseEntity.ok(tripulacion);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Tripulacion> createCrew(@RequestBody Tripulacion tripulacion) {
        return ResponseEntity.ok(crewService.saveCrew(tripulacion));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCrew(@PathVariable Long id, @RequestBody Tripulacion crewDetails) {
        Tripulacion tripulacion = crewService.findCrewById(id);
        if (tripulacion != null) {
            tripulacion.setJugadores(crewDetails.getJugadores());  // Assuming you handle the entire set of players
            tripulacion.setNave(crewDetails.getNave());
            tripulacion.setRol(crewDetails.getRol());
            return ResponseEntity.ok(crewService.saveCrew(tripulacion));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCrew(@PathVariable Long id) {
        crewService.deleteCrew(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/join")
    public ResponseEntity<String> joinCrew(@RequestBody JoinCrewRequest joinRequest) {
        boolean success = crewService.joinCrew(joinRequest.getCrewId(), joinRequest.getPlayerId());
        if (success) {
            return ResponseEntity.ok("Joined crew successfully");
        } else {
            return ResponseEntity.badRequest().body("Failed to join crew");
        }
    }
}

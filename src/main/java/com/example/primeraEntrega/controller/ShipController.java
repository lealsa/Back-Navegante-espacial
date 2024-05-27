package com.example.primeraEntrega.controller;

import com.example.primeraEntrega.model.Nave;
import com.example.primeraEntrega.service.ShipService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ships")
public class ShipController {

    @Autowired
    private ShipService shipService;

    @GetMapping
    public List<Nave> getAllShips() {
        return shipService.findAllShips();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Nave> getShipById(@PathVariable Long id) {
        Nave nave = shipService.findShipById(id);
        return ResponseEntity.ok(nave);
    }

    @PostMapping
    public Nave createShip(@RequestBody Nave nave) {
        return shipService.saveShip(nave);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Nave> updateShip(@PathVariable Long id, @RequestBody Nave naveDetails) {
        Nave nave = shipService.findShipById(id);
        nave.setNombre(naveDetails.getNombre());
        nave.setFoto(naveDetails.getFoto());
        nave.setCapacidadMax(naveDetails.getCapacidadMax());
        nave.setVelocidad(naveDetails.getVelocidad());
        final Nave updatedShip = shipService.saveShip(nave);
        return ResponseEntity.ok(updatedShip);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShip(@PathVariable Long id) {
        shipService.deleteShip(id);
        return ResponseEntity.ok().build();
    }
}

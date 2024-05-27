package com.example.primeraEntrega.controller;

import com.example.primeraEntrega.model.AgujeroDeGusano;
import com.example.primeraEntrega.service.WormholeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wormholes")
public class WormholeController {

    @Autowired
    private WormholeService wormholeService;

    @GetMapping
    public List<AgujeroDeGusano> getAllWormholes() {
        return wormholeService.findAllWormholes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgujeroDeGusano> getWormholeById(@PathVariable Long id) {
        AgujeroDeGusano agujeroDeGusano = wormholeService.findWormholeById(id);
        return ResponseEntity.ok(agujeroDeGusano);
    }

    @PostMapping
    public AgujeroDeGusano createWormhole(@RequestBody AgujeroDeGusano agujeroDeGusano) {
        return wormholeService.saveWormhole(agujeroDeGusano);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AgujeroDeGusano> updateWormhole(@PathVariable Long id, @RequestBody AgujeroDeGusano wormholeDetails) {
        AgujeroDeGusano agujeroDeGusano = wormholeService.findWormholeById(id);
        agujeroDeGusano.setDistancia(wormholeDetails.getDistancia());
        agujeroDeGusano.setEstrellaInicio(wormholeDetails.getEstrellaInicio());
        agujeroDeGusano.setEstrellaFin(wormholeDetails.getEstrellaFin());
        final AgujeroDeGusano updatedWormhole = wormholeService.saveWormhole(agujeroDeGusano);
        return ResponseEntity.ok(updatedWormhole);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWormhole(@PathVariable Long id) {
        wormholeService.deleteWormhole(id);
        return ResponseEntity.ok().build();
    }
}

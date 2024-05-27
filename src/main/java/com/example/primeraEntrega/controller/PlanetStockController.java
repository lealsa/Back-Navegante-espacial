package com.example.primeraEntrega.controller;

import com.example.primeraEntrega.model.StockPlaneta;
import com.example.primeraEntrega.service.PlanetStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/planet-stocks")
public class PlanetStockController {

    @Autowired
    private PlanetStockService planetStockService;

    @GetMapping
    public List<StockPlaneta> getAllPlanetStocks() {
        return planetStockService.findAllPlanetStocks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StockPlaneta> getPlanetStockById(@PathVariable Long id) {
        StockPlaneta stockPlaneta = planetStockService.findPlanetStockById(id);
        return ResponseEntity.ok(stockPlaneta);
    }

    @PostMapping
    public StockPlaneta createPlanetStock(@RequestBody StockPlaneta stockPlaneta) {
        return planetStockService.savePlanetStock(stockPlaneta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StockPlaneta> updatePlanetStock(@PathVariable Long id, @RequestBody StockPlaneta stockDetails) {
        StockPlaneta stockPlaneta = planetStockService.findPlanetStockById(id);
        stockPlaneta.setPlaneta(stockDetails.getPlaneta());
        stockPlaneta.setProducto(stockDetails.getProducto());
        stockPlaneta.setStock(stockDetails.getStock());
        stockPlaneta.setFactorDemanda(stockDetails.getFactorDemanda());
        stockPlaneta.setFactorOferta(stockDetails.getFactorOferta());
        final StockPlaneta updatedStock = planetStockService.savePlanetStock(stockPlaneta);
        return ResponseEntity.ok(updatedStock);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlanetStock(@PathVariable Long id) {
        planetStockService.deletePlanetStock(id);
        return ResponseEntity.ok().build();
    }
}

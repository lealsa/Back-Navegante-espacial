package com.example.primeraEntrega.service;

import com.example.primeraEntrega.model.StockPlaneta;
import com.example.primeraEntrega.repository.PlanetStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanetStockService {

    @Autowired
    private PlanetStockRepository planetStockRepository;

    public List<StockPlaneta> findAllPlanetStocks() {
        return planetStockRepository.findAll();
    }

    public StockPlaneta findPlanetStockById(Long id) {
        return planetStockRepository.findById(id).orElseThrow(() -> new RuntimeException("Planet stock not found"));
    }

    public StockPlaneta savePlanetStock(StockPlaneta stockPlaneta) {
        return planetStockRepository.save(stockPlaneta);
    }

    public void deletePlanetStock(Long id) {
        planetStockRepository.deleteById(id);
    }
}

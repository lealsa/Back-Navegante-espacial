package com.example.primeraEntrega.controller;

import com.example.primeraEntrega.DTO.TradeRequest;
import com.example.primeraEntrega.model.Producto;
import com.example.primeraEntrega.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trade")
public class TradeController {

    @Autowired
    private TradeService tradeService;

    @GetMapping("/products/{planetId}")
    public ResponseEntity<List<Producto>> listProducts(@PathVariable Long planetId) {
        return ResponseEntity.ok(tradeService.listProductsOnPlanet(planetId));
    }

    @PostMapping("/buy")
    public ResponseEntity<String> buyProduct(@RequestBody TradeRequest tradeRequest) {
        boolean success = tradeService.buyProduct(tradeRequest.getProductId(), tradeRequest.getPlanetId(), tradeRequest.getQuantity(), tradeRequest.getGameId());
        if (success) {
            return ResponseEntity.ok("Purchase successful");
        } else {
            return ResponseEntity.badRequest().body("Purchase failed");
        }
    }

    @PostMapping("/sell")
    public ResponseEntity<String> sellProduct(@RequestBody TradeRequest tradeRequest) {
        boolean success = tradeService.sellProduct(tradeRequest.getProductId(), tradeRequest.getPlanetId(), tradeRequest.getQuantity(), tradeRequest.getGameId());
        if (success) {
            return ResponseEntity.ok("Sale successful");
        } else {
            return ResponseEntity.badRequest().body("Sale failed");
        }
    }
}

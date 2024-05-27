package com.example.primeraEntrega.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.primeraEntrega.model.Game;
import com.example.primeraEntrega.model.Producto;
import com.example.primeraEntrega.model.StockPlaneta;
import com.example.primeraEntrega.repository.GameRepository;
import com.example.primeraEntrega.repository.PlanetStockRepository;
import com.example.primeraEntrega.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TradeService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private PlanetStockRepository planetStockRepository;
    @Autowired
    private GameRepository gameRepository;

    // Método para listar productos disponibles en un planeta
    public List<Producto> listProductsOnPlanet(Long planetId) {
        return productRepository.findByPlanetId(planetId);
    }

    @Transactional
    public boolean buyProduct(Long productId, Long planetId, Integer quantity, Long gameId) {
        Optional<Game> gameOpt = gameRepository.findById(gameId);
        Optional<StockPlaneta> stockOpt = planetStockRepository.findByPlanetIdAndProductId(planetId, productId);
        if (gameOpt.isPresent() && stockOpt.isPresent()) {
            StockPlaneta stock = stockOpt.get();
            Game game = gameOpt.get();
            double totalPrice = calculatePurchasePrice(stock.getFactorOferta(), stock.getStock(), quantity);
            if (game.getCreditos() >= totalPrice) {
                game.setCreditos(game.getCreditos() - totalPrice);
                stock.setStock(stock.getStock() - quantity);
                planetStockRepository.save(stock);
                gameRepository.save(game);
                return true;
            }
        }
        return false;
    }

    @Transactional
    public boolean sellProduct(Long productId, Long planetId, Integer quantity, Long gameId) {
        Optional<Game> gameOpt = gameRepository.findById(gameId);
        Optional<StockPlaneta> stockOpt = planetStockRepository.findByPlanetIdAndProductId(planetId, productId);
        if (gameOpt.isPresent() && stockOpt.isPresent()) {
            StockPlaneta stock = stockOpt.get();
            Game game = gameOpt.get();
            double totalPrice = calculateSalePrice(stock.getFactorDemanda(), stock.getStock(), quantity);
            game.setCreditos(game.getCreditos() + totalPrice);
            stock.setStock(stock.getStock() + quantity);
            planetStockRepository.save(stock);
            gameRepository.save(game);
            return true;
        }
        return false;
    }

    private double calculatePurchasePrice(double factorOferta, int stock, int quantity) {
        return factorOferta / (1 + stock) * quantity;
    }

    private double calculateSalePrice(double factorDemanda, int stock, int quantity) {
        return factorDemanda / (1 + stock) * quantity;
    }
}

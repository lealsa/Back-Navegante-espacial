package com.example.primeraEntrega.service;

import com.example.primeraEntrega.model.Estrella;
import com.example.primeraEntrega.model.Nave;
import com.example.primeraEntrega.model.Planeta;
import com.example.primeraEntrega.repository.StarRepository;
import com.example.primeraEntrega.repository.ShipRepository;
import com.example.primeraEntrega.repository.WormholeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NavigationService {
    @Autowired
    private StarRepository starRepository;
    @Autowired
    private ShipRepository shipRepository;
    @Autowired
    private WormholeRepository wormholeRepository;

    // Método para listar estrellas cercanas
    public List<Estrella> listNearbyStars(Long starId, double maxDistance) {
        Estrella currentStar = starRepository.findById(starId).orElse(null);
        if (currentStar != null) {
            return wormholeRepository.findNearbyStars(currentStar, maxDistance);
        }
        return List.of(); // Devuelve una lista vacía si no se encuentra la estrella o no hay estrellas cercanas
    }

    // Método para listar planetas en una estrella
    public List<Planeta> listPlanetsInStar(Long starId) {
        return starRepository.findById(starId)
            .map(Estrella::getPlanetas)
            .orElse(List.of()); // Devuelve una lista vacía si no se encuentra la estrella
    }

    // Método para viajar a otra estrella
    public boolean travel(Long starId, Long shipId) {
        Nave ship = shipRepository.findById(shipId).orElse(null);
        if (ship != null && starRepository.existsById(starId)) {
            ship.setCurrentStar(starRepository.findById(starId).get());
            shipRepository.save(ship);
            return true;
        }
        return false;
    }
}

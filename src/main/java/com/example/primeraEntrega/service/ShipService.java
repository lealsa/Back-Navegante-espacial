package com.example.primeraEntrega.service;

import com.example.primeraEntrega.model.Nave;
import com.example.primeraEntrega.repository.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShipService {

    @Autowired
    private ShipRepository shipRepository;

    public List<Nave> findAllShips() {
        return shipRepository.findAll();
    }

    public Nave findShipById(Long id) {
        return shipRepository.findById(id).orElseThrow(() -> new RuntimeException("Ship not found"));
    }

    public Nave saveShip(Nave nave) {
        return shipRepository.save(nave);
    }

    public void deleteShip(Long id) {
        shipRepository.deleteById(id);
    }
}

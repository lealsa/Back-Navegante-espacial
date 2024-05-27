package com.example.primeraEntrega.service;

import com.example.primeraEntrega.model.InventarioNave;
import com.example.primeraEntrega.repository.ShipInventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShipInventoryService {

    @Autowired
    private ShipInventoryRepository shipInventoryRepository;

    public List<InventarioNave> findAllShipInventories() {
        return shipInventoryRepository.findAll();
    }

    public InventarioNave findShipInventoryById(Long id) {
        return shipInventoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Ship inventory not found"));
    }

    public InventarioNave saveShipInventory(InventarioNave inventarioNave) {
        return shipInventoryRepository.save(inventarioNave);
    }

    public void deleteShipInventory(Long id) {
        shipInventoryRepository.deleteById(id);
    }
}

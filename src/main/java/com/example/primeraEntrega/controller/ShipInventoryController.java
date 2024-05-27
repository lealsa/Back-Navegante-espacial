package com.example.primeraEntrega.controller;

import com.example.primeraEntrega.model.InventarioNave;
import com.example.primeraEntrega.service.ShipInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ship-inventories")
public class ShipInventoryController {

    @Autowired
    private ShipInventoryService shipInventoryService;

    @GetMapping
    public List<InventarioNave> getAllShipInventories() {
        return shipInventoryService.findAllShipInventories();
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventarioNave> getShipInventoryById(@PathVariable Long id) {
        InventarioNave inventarioNave = shipInventoryService.findShipInventoryById(id);
        return ResponseEntity.ok(inventarioNave);
    }

    @PostMapping
    public InventarioNave createShipInventory(@RequestBody InventarioNave inventarioNave) {
        return shipInventoryService.saveShipInventory(inventarioNave);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventarioNave> updateShipInventory(@PathVariable Long id, @RequestBody InventarioNave inventoryDetails) {
        InventarioNave inventarioNave = shipInventoryService.findShipInventoryById(id);
        inventarioNave.setNave(inventoryDetails.getNave());
        inventarioNave.setProducto(inventoryDetails.getProducto());
        inventarioNave.setStock(inventoryDetails.getStock());
        final InventarioNave updatedInventory = shipInventoryService.saveShipInventory(inventarioNave);
        return ResponseEntity.ok(updatedInventory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShipInventory(@PathVariable Long id) {
        shipInventoryService.deleteShipInventory(id);
        return ResponseEntity.ok().build();
    }
}

package com.example.primeraEntrega.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.primeraEntrega.dto.InformacionCompraProductoDTO;
import com.example.primeraEntrega.model.InventarioNave;
import com.example.primeraEntrega.services.*;

import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/api/comprar")
public class ComprarController {

    @Autowired
    private InventarioPlanetaService inventarioPlanetaService;

    @Autowired
    private GameService gameService;

    @Autowired
    private NaveService naveService;

    private InventarioNave inventarioNave;

    @GetMapping("/list/{id}")
    public List<InformacionCompraProductoDTO> listarProductos(@PathVariable Long id) {
        return inventarioPlanetaService.listarInformacionCompraProducto(id);
    }

    @PostMapping("/realizar-compra/{id}")
    public void comprar(@PathVariable Long id) {
        inventarioNave = new InventarioNave(inventarioPlanetaService.buscarInventario(id).getCantidad(),
                inventarioPlanetaService.buscarInventario(id).getfOfertaDemanda());
        inventarioNave.setProducto(inventarioPlanetaService.buscarInventario(id).getProducto());
        inventarioNave.setNave(naveService.buscarNave("nave1"));
        naveService.crearInventario(inventarioNave, naveService.buscarNave("nave1"));
        inventarioPlanetaService.cambiarCantidadInventario(
                inventarioPlanetaService.buscarInventario(id).getCantidad() - 1,
                inventarioPlanetaService.buscarInventario(id));
    }

    @PostMapping("/actualizar-puntaje/{id}")
    public void updateScore(@PathVariable Long id) {
        Double puntaje = gameService.buscar((long) 1).getscore()
                - inventarioPlanetaService.buscarInventario(id).getProducto().getPrecio();
        gameService.updateScore(puntaje, gameService.buscar((long) 1));
    }

    @GetMapping("/obtener-puntaje")
    public Double getScore() {
        return gameService.getGameScore((long) 1);
    }

}

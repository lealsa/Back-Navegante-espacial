package com.example.primeraEntrega.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.primeraEntrega.dto.InformacionCompraProductoDTO;
import com.example.primeraEntrega.model.InventarioPlaneta;
import com.example.primeraEntrega.repository.InventarioPlanetaRepository;

import io.micrometer.common.lang.NonNull;

@Service
public class InventarioPlanetaService {

    @Autowired
    private InventarioPlanetaRepository inventarioPlanetaRepositorio;

    public List<InventarioPlaneta> listarInventarioPlaneta() {
        return inventarioPlanetaRepositorio.findAll();
    }

    public InventarioPlaneta buscarInventario(@NonNull Long id) {
        return inventarioPlanetaRepositorio.findById(id).orElseThrow();
    }

    public void actualizarInventario(InventarioPlaneta inventario) {
        InventarioPlaneta ip = inventarioPlanetaRepositorio.findById(inventario.getId()).orElseThrow();
        ip.setCantidad(inventario.getCantidad());
        ip.setfOfertaDemanda(inventario.getfOfertaDemanda());
        inventarioPlanetaRepositorio.save(ip);
    }

    public void cambiarCantidadInventario(Double cantidad, InventarioPlaneta ip) {
        ip.setCantidad(cantidad);
        inventarioPlanetaRepositorio.save(ip);
    }

    public void guardarInventario(InventarioPlaneta inventario) {
        inventarioPlanetaRepositorio.save(inventario);
    }

    public void eliminarInventario(Long id) {
        inventarioPlanetaRepositorio.deleteById(id);
    }

    public List<InformacionCompraProductoDTO> listarInformacionCompraProducto(Long id) {
        List<InformacionCompraProductoDTO> listaProductosDTO = new ArrayList<>();

        List<InventarioPlaneta> list = inventarioPlanetaRepositorio.buscarProductosPorPlaneta(id);
        for (InventarioPlaneta i : list) {

            Double precio = i.getfOfertaDemanda() / (1 + i.getCantidad());
            i.getProducto().setPrecio(precio);
            inventarioPlanetaRepositorio.save(i);
            InformacionCompraProductoDTO compra = new InformacionCompraProductoDTO(i.getId(), i.getProducto().getTipo(),
                    i.getCantidad(), i.getfOfertaDemanda(), precio);

            listaProductosDTO.add(compra);
        }
        return listaProductosDTO;

    }
}

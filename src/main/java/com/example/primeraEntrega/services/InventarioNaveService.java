package com.example.primeraEntrega.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.primeraEntrega.dto.InformacionVentaProductoDTO;
import com.example.primeraEntrega.model.InventarioNave;
import com.example.primeraEntrega.repository.InventarioNaveRepository;
import io.micrometer.common.lang.NonNull;

@Service
public class InventarioNaveService {

    @Autowired
    private InventarioNaveRepository inventarioNaveRepositorio;

    public List<InventarioNave> listarInventarioNave() {
        return inventarioNaveRepositorio.findAll();
    }

    public InventarioNave buscarInventario(@NonNull Long id) {
        return inventarioNaveRepositorio.findById(id).orElseThrow();
    }

    public void actualizarInventario(InventarioNave inventario) {
        InventarioNave in = inventarioNaveRepositorio.findById(inventario.getId()).orElseThrow();
        in.setCantidad(inventario.getCantidad());
        inventarioNaveRepositorio.save(in);
    }

    public void guardarInventario(InventarioNave inventario) {
        inventarioNaveRepositorio.save(inventario);
    }

    public void eliminarInventario(Long id) {
        inventarioNaveRepositorio.deleteById(id);
    }
    public List<InformacionVentaProductoDTO> listarInformacionVentaProducto(String nombre) {
        List<InformacionVentaProductoDTO> listaProductosDTO = new ArrayList<>();

        List<InventarioNave> list = inventarioNaveRepositorio.buscarProductosPorNombreNave(nombre);

        for (InventarioNave i : list) {

            Double precio = i.getfOfertaDemanda() / (1 + i.getCantidad());
            i.getProducto().setPrecio(precio);

            InformacionVentaProductoDTO venta = new InformacionVentaProductoDTO(i.getProducto().getTipo(),
                    i.getCantidad(), i.getfOfertaDemanda(), precio, i.getId());
            listaProductosDTO.add(venta);
        }
        return listaProductosDTO;

    }

    public Double calcularVolumenTotal(List<InventarioNave> list) {
        Double vol = 0.0;
        List<InventarioNave> productos = inventarioNaveRepositorio.buscarProductosPorNombreNave("nave0");

        for (InventarioNave p : productos) {
            vol += p.getProducto().getVolumen();
        }

        return vol;
    }
}

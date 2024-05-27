package com.example.primeraEntrega.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "volumen", nullable = false)
    private Double volumen;

    @Column(name = "imagen", nullable = false)
    private String imagen;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StockPlaneta> stockPlanetas = new ArrayList<>();

    // Constructores, Getters y Setters

    public Producto(Double volumen, String imagen, String nombre) {
        this.volumen = volumen;
        this.imagen = imagen;
        this.nombre = nombre;
    }

    public Producto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getVolumen() {
        return volumen;
    }

    public void setVolumen(Double volumen) {
        this.volumen = volumen;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<StockPlaneta> getStockPlanetas() {
        return stockPlanetas;
    }

    public void setStockPlanetas(List<StockPlaneta> stockPlanetas) {
        this.stockPlanetas = stockPlanetas;
    }
}

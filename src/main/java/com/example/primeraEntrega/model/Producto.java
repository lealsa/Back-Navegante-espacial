package com.example.primeraEntrega.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "volumen", nullable = false)
    private Double volumen;

    @Column(name = "precio", nullable = false)
    private Double precio;

    @Column(name = "tipo", nullable = false)
    private String tipo;

    @OneToMany(mappedBy = "producto")
    private List<InventarioNave> naves = new ArrayList<>();

    @OneToMany(mappedBy = "producto")
    private List<InventarioPlaneta> planeta = new ArrayList<>();

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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public List<InventarioNave> getNaves() {
        return naves;
    }

    public void setNaves(List<InventarioNave> naves) {
        this.naves = naves;
    }

    public List<InventarioPlaneta> getPlaneta() {
        return planeta;
    }

    public void setPlaneta(List<InventarioPlaneta> planeta) {
        this.planeta = planeta;
    }

    public Producto(Double volumen, String tipo) {
        this.volumen = volumen;
        this.tipo = tipo;
        this.precio = 0.0;
    }

    public Producto() {

    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

}

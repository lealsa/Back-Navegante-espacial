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

    @Column(name = "imagen", nullable = false)
    private String imagen;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @OneToMany(mappedBy = "producto")
    private List<InventarioNave> naves = new ArrayList<>();

    @OneToMany(mappedBy = "producto")
    private List<StockPlaneta> planeta = new ArrayList<>();

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

    public void setNombre(String imagen) {
        this.nombre = imagen;
    }
    public List<InventarioNave> getNaves() {
        return naves;
    }

    public void setNaves(List<InventarioNave> naves) {
        this.naves = naves;
    }

    public List<StockPlaneta> getPlaneta() {
        return planeta;
    }

    public void setPlaneta(List<StockPlaneta> planeta) {
        this.planeta = planeta;
    }

    public Producto(Double volumen, String imagen, String nombre) {
        this.volumen = volumen;
        this.imagen = imagen;
        this.nombre = nombre;
    }

    public Producto() {

    }


}

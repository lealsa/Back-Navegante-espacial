package com.example.primeraEntrega.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "nave")
public class Nave {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String foto;

    @Column(nullable = false)
    private String nombre;

    @Column(name = "capacidadMax", nullable = false)
    private Double capacidadMax;

    @Column(name = "velocidad", nullable = false)
    private Double velocidad;

    @OneToMany(mappedBy = "nave")
    private List<Tripulacion> tripulacion = new ArrayList<>();

    @OneToMany(mappedBy = "nave")
    private List<InventarioNave> inventario = new ArrayList<>();

    // Constructor completo
    public Nave(String foto, String nombre, Double capacidadMax, Double velocidad) {
        this.foto = foto;
        this.nombre = nombre;
        this.capacidadMax = capacidadMax;
        this.velocidad = velocidad;
        this.tripulacion = new ArrayList<>();
        this.inventario = new ArrayList<>();
    }

    // Constructor vacío
    public Nave() {
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getCapacidadMax() {
        return capacidadMax;
    }

    public void setCapacidadMax(Double capacidadMax) {
        this.capacidadMax = capacidadMax;
    }

    public Double getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(Double velocidad) {
        this.velocidad = velocidad;
    }

    public List<Tripulacion> getTripulacion() {
        return tripulacion;
    }

    public void setTripulacion(List<Tripulacion> tripulacion) {
        this.tripulacion = tripulacion;
    }

    public List<InventarioNave> getInventario() {
        return inventario;
    }

    public void setInventario(List<InventarioNave> inventario) {
        this.inventario = inventario;
    }

    public void addTripulacion(Tripulacion t) {
        this.tripulacion.add(t);
    }

    public void addInventario(InventarioNave iNave) {
        this.inventario.add(iNave);
    }
}

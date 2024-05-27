package com.example.primeraEntrega.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
public class Planeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "imagen", nullable = false)
    private String imagen;

    @OneToMany(mappedBy = "planeta")
    @JsonIgnore
    private List<Nave> naves = new ArrayList<>();

    @OneToMany(mappedBy = "planeta")
    @JsonIgnore
    private List<StockPlaneta> inventario = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "estrella_id")
    @JsonIgnore
    private Estrella estrella;

    // Constructor completo
    public Planeta(String nombre, String imagen, Estrella estrella) {
        this.nombre = nombre;
        this.imagen = imagen;
        this.estrella = estrella;
        this.naves = new ArrayList<>();
        this.inventario = new ArrayList<>();
    }

    // Constructor vacío
    public Planeta() {}

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public List<Nave> getNaves() {
        return naves;
    }

    public void setNaves(List<Nave> naves) {
        this.naves = naves;
    }

    public List<StockPlaneta> getInventario() {
        return inventario;
    }

    public void setInventario(List<StockPlaneta> inventario) {
        this.inventario = inventario;
    }

    public Estrella getEstrella() {
        return estrella;
    }

    public void setEstrella(Estrella estrella) {
        this.estrella = estrella;
    }

    public void addInventario(StockPlaneta iPlaneta) {
        this.inventario.add(iPlaneta);
    }
}

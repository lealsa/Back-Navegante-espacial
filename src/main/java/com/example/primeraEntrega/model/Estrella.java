package com.example.primeraEntrega.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Estrella {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombreEstrella", nullable = false)
    private String nombreEstrella;

    @Column(name = "coordX", nullable = false)
    private Double coordX;

    @Column(name = "coordY", nullable = false)
    private Double coordY;

    @Column(name = "coordZ", nullable = false)
    private Double coordZ;

    @Column(name = "imagen", nullable = false)
    private String imagen;

    @OneToMany(mappedBy = "estrella")
    @JsonIgnore
    private List<Planeta> planetas = new ArrayList<>();

    @OneToMany(mappedBy = "estrellaInicio")
    
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    private List<AgujeroDeGusano> agujerosDeGusano = new ArrayList<>();
    
    public Estrella(String nombreEstrella, Double coordX, Double coordY, Double coordZ, String imagen) {
        this.nombreEstrella = nombreEstrella;
        this.coordX = coordX;
        this.coordY = coordY;
        this.coordZ = coordZ;
        this.imagen = imagen;
    }

    public String getNombreEstrella() {
        return this.nombreEstrella;
    }

    public void setNombreEstrella(String n) {
        this.nombreEstrella = n;
    }

    public String getImagen() {
        return this.imagen;
    }

    public void setImagen(String n) {
        this.imagen = n;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getCoordenadaX() {
        return coordX;
    }

    public void setCoordenadaX(Double coordenadaX) {
        this.coordX = coordenadaX;
    }

    public Double getCoordenadaZ() {
        return coordZ;
    }

    public void setCoordenadaZ(Double coordenadaZ) {
        this.coordZ = coordenadaZ;
    }

    public Double getCoordenadaY() {
        return coordY;
    }

    public void setCoordenadaY(Double coordenadaY) {
        this.coordY = coordenadaY;
    }

    public List<Planeta> getPlanetas() {
        return planetas;
    }

    public void setPlanetas(List<Planeta> planetas) {
        this.planetas = planetas;
    }

    public void addPlaneta(Planeta p) {
        this.planetas.add(p);
    }

    public void addTunel(AgujeroDeGusano t) {
        this.agujerosDeGusano.add(t);
    }

    public List<AgujeroDeGusano> getTuneles() {
        return agujerosDeGusano;
    }

    public void setTuneles(List<AgujeroDeGusano> t) {
        this.agujerosDeGusano = t;
    }

    public Estrella() {

    }
}

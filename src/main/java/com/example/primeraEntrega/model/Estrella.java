package com.example.primeraEntrega.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

    @Column(name = "coordX", nullable = false)
    private Double coordX;

    @Column(name = "coordY", nullable = false)
    private Double coordY;

    @Column(name = "coordZ", nullable = false)
    private Double coordZ;

    @OneToMany(mappedBy = "estrella")
    @JsonIgnore
    private List<Planeta> planetas = new ArrayList<>();

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

    public Estrella(Double coordenadaX, Double coordenadaY, Double coordenadaZ) {
        this.coordX = coordenadaX;
        this.coordY = coordenadaY;
        this.coordZ = coordenadaZ;
    }

    public Estrella() {

    }
}

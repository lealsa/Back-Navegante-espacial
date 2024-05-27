package com.example.primeraEntrega.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class AgujeroDeGusano {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "distancia", nullable = false)
    private Double distancia;

    @ManyToOne
    @JoinColumn(name = "estrellaInicioId", nullable = false)
    private Estrella estrellaInicio;

    @ManyToOne
    @JoinColumn(name = "estrellaFinId", nullable = false)
    private Estrella estrellaFin;

    public AgujeroDeGusano() {
    }

    public AgujeroDeGusano(Double distancia, Estrella estrellaInicio, Estrella estrellaFin) {
        this.distancia = distancia;
        this.estrellaInicio = estrellaInicio;
        this.estrellaFin = estrellaFin;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public Double getDistancia() {
        return distancia;
    }

    public Estrella getEstrellaInicio() {
        return estrellaInicio;
    }

    public Estrella getEstrellaFin() {
        return estrellaFin;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setDistancia(Double distancia) {
        this.distancia = distancia;
    }

    public void setEstrellaInicio(Estrella estrellaInicio) {
        this.estrellaInicio = estrellaInicio;
    }

    public void setEstrellaFin(Estrella estrellaFin) {
        this.estrellaFin = estrellaFin;
    }

    @Override
    public String toString() {
        return "AgujeroDeGusano{" +
                "id=" + id +
                ", distancia=" + distancia +
                ", estrellaInicio=" + estrellaInicio.getNombreEstrella() +
                ", estrellaFin=" + estrellaFin.getNombreEstrella() +
                '}';
    }
}

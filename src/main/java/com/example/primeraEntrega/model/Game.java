package com.example.primeraEntrega.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "time", nullable = false)
    private Double time;

    @Column(name = "score", nullable = false)
    private Double score;

    @Column(name = "timeMax", nullable = false)
    private Double timeMax;

    @Column(name = "cuota", nullable = false)
    private Double cuota;

    @Column(name = "creditos", nullable = false)
    private Double creditos;

    @OneToOne
    @JsonIgnore
    private Nave nave;

    @Column(name = "tripulacion_id", nullable = false)
    private Long tripulacionId;

    // Constructor completo
    public Game(Nave nave, Long tripulacionId) {
        this.time = 0D;
        this.score = 0D;
        this.timeMax = 3D;
        this.cuota = 300D;
        this.creditos = 0D;
        this.nave = nave;
        this.tripulacionId = tripulacionId;
    }

    // Constructor vacío
    public Game() {
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getCreditos() {
        return creditos;
    }

    public void setCreditos(Double creditos) {
        this.creditos = creditos;
    }

    public Double getTime() {
        return time;
    }

    public void setTime(Double time) {
        this.time = time;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Double getTimeMax() {
        return timeMax;
    }

    public void setTimeMax(Double timeMax) {
        this.timeMax = timeMax;
    }

    public Double getCuota() {
        return cuota;
    }

    public void setCuota(Double cuota) {
        this.cuota = cuota;
    }

    public Nave getNave() {
        return nave;
    }

    public void setNave(Nave nave) {
        this.nave = nave;
    }

    public Long getTripulacionId() {
        return tripulacionId;
    }

    public void setTripulacionId(Long tripulacionId) {
        this.tripulacionId = tripulacionId;
    }
}

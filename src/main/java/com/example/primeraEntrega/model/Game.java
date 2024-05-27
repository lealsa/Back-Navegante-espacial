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

    // Constructor completo
    public Game(Double time, Double score, Double timeMax, Double cuota, Nave nave) {
        this.time = time;
        this.score = score;
        this.timeMax = timeMax;
        this.cuota = cuota;
        this.nave = nave;
    }

    // Constructor vacío
    public Game() {
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Double getCreditos() {
        return creditos;
    }

    public void setCreditos(Double id) {
        this.creditos= id;
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
}

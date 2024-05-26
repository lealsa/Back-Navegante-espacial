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

    @OneToOne
    @JsonIgnore
    private Nave nave;

    public Game(Double time, Double score, Double timeMax) {
        this.time = time;
        this.score = score;
        this.timeMax = timeMax;
    }

    public Game() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double gettime() {
        return time;
    }

    public void settime(Double time) {
        this.time = time;
    }

    public Double getscore() {
        return score;
    }

    public void setscore(Double score) {
        this.score = score;
    }

    public Double gettimeMax() {
        return timeMax;
    }

    public void settimeMax(Double timeMax) {
        this.timeMax = timeMax;
    }

    public Nave getNave() {
        return nave;
    }

    public void setNave(Nave nave) {
        this.nave = nave;
    }

    public void setPuntaje(double d) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setPuntaje'");
    }

}

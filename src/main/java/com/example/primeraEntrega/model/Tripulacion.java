package com.example.primeraEntrega.model;

import jakarta.persistence.*;

@Entity
public class Tripulacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "jugador_id", nullable = false)
    private Jugador jugador;

    @ManyToOne
    @JoinColumn(name = "nave_id", nullable = false)
    private Nave nave;

    @Column(nullable = false)
    private String rol;

    // Constructor completo
    public Tripulacion(Jugador jugador, Nave nave, String rol) {
        this.jugador = jugador;
        this.nave = nave;
        this.rol = rol;
    }

    // Constructor vacío
    public Tripulacion() {
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public Nave getNave() {
        return nave;
    }

    public void setNave(Nave nave) {
        this.nave = nave;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}

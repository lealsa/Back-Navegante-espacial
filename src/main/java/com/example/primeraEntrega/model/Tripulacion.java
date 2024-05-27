package com.example.primeraEntrega.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Tripulacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable(
        name = "tripulacion_jugador",
        joinColumns = @JoinColumn(name = "tripulacion_id"),
        inverseJoinColumns = @JoinColumn(name = "jugador_id")
    )
    private Set<Jugador> jugadores = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "nave_id", nullable = false)
    private Nave nave;

    @Column(nullable = false)
    private String rol;

    // Constructor completo
    public Tripulacion(Nave nave, String rol) {
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

    public Set<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(Set<Jugador> jugadores) {
        this.jugadores = jugadores;
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

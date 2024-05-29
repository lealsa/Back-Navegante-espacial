package com.example.primeraEntrega.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Tripulacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "tripulacion_jugador", joinColumns = @JoinColumn(name = "tripulacion_id"))
    @Column(name = "jugador_id")
    private Set<Long> jugadorIds = new HashSet<>();


    @ManyToOne
    @JoinColumn(name = "nave_id", nullable = false)
    private Nave nave;

    // Constructor con nave
    public Tripulacion(Nave nave) {
        this.nave = nave;
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

    public Set<Long> getJugadorIds() {
        return jugadorIds;
    }

    public void setJugadorIds(Set<Long> jugadorIds) {
        this.jugadorIds = jugadorIds;
    }

    public Nave getNave() {
        return nave;
    }

    public void setNave(Nave nave) {
        this.nave = nave;
    }
}

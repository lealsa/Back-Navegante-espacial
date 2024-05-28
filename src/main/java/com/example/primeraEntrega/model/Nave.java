package com.example.primeraEntrega.model;

import jakarta.persistence.*;

@Entity
@Table(name = "nave")
public class Nave {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String foto;

    @Column(nullable = false)
    private String nombre;

    @Column(name = "capacidadMax", nullable = false)
    private Double capacidadMax;

    @Column(name = "velocidad", nullable = false)
    private Double velocidad;

    // Almacena solo el ID de la tripulación asociada a esta nave
    @Column(name = "tripulacion_id")
    private Long tripulacionId;

    @ManyToOne
    @JoinColumn(name = "current_star_id")
    private Estrella currentStar;

    @ManyToOne
    @JoinColumn(name = "planeta_id")
    private Planeta planeta;

    // Constructor completo
    public Nave(String foto, String nombre, Double capacidadMax, Double velocidad, Estrella currentStar, Planeta planeta) {
        this.foto = foto;
        this.nombre = nombre;
        this.capacidadMax = capacidadMax;
        this.velocidad = velocidad;
        this.currentStar = currentStar;
        this.planeta = planeta;
    }

    // Constructor vacío
    public Nave() {}

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getCapacidadMax() {
        return capacidadMax;
    }

    public void setCapacidadMax(Double capacidadMax) {
        this.capacidadMax = capacidadMax;
    }

    public Double getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(Double velocidad) {
        this.velocidad = velocidad;
    }

    public Long getTripulacionId() {
        return tripulacionId;
    }

    public void setTripulacionId(Long tripulacionId) {
        this.tripulacionId = tripulacionId;
    }

    public Estrella getCurrentStar() {
        return currentStar;
    }

    public void setCurrentStar(Estrella currentStar) {
        this.currentStar = currentStar;
    }

    public Planeta getPlaneta() {
        return planeta;
    }

    public void setPlaneta(Planeta planeta) {
        this.planeta = planeta;
    }
}

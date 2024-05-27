package com.example.primeraEntrega.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Jugador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "usuario", nullable = false)
    private String usuario;

    @Column(name = "contrasena", nullable = false)
    private String contrasena;

    @ManyToMany(mappedBy = "jugadores")
    private Set<Tripulacion> tripulaciones = new HashSet<>();

    @Column(name = "rol", nullable = false)
    private String rol;  // Nuevo atributo para el rol

    public Jugador(String usuario, String contrasena, String rol) {
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.rol = rol;  // Inicializa el rol
    }

    // Getters y Setters para 'rol'...
    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Jugador(String usuario, String contrasena) {
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    public Jugador() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Tripulacion> getTripulaciones() {
        return tripulaciones;
    }

    public void setTripulaciones(Set<Tripulacion> tripulaciones) {
        this.tripulaciones = tripulaciones;
    }
}

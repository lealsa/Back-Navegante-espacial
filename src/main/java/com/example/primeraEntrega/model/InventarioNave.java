package com.example.primeraEntrega.model;

import jakarta.persistence.*;

@Entity
public class InventarioNave {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "nave_id", nullable = false)
    private Nave nave;

    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    @Column(nullable = false)
    private Integer stock;

    // Constructor completo
    public InventarioNave(Nave nave, Producto producto, Integer stock) {
        this.nave = nave;
        this.producto = producto;
        this.stock = stock;
    }

    // Constructor vacío
    public InventarioNave() {
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Nave getNave() {
        return nave;
    }

    public void setNave(Nave nave) {
        this.nave = nave;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}

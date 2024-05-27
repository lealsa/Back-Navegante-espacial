package com.example.primeraEntrega.model;

import jakarta.persistence.*;

@Entity
@Table(name = "stock_planeta")
public class StockPlaneta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "planeta_id", nullable = false)
    private Planeta planeta;

    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    @Column(nullable = false)
    private Integer stock;

    @Column(name = "factor_demanda", nullable = false)
    private Double factorDemanda;

    @Column(name = "factor_oferta", nullable = false)
    private Double factorOferta;

    // Constructor completo
    public StockPlaneta(Planeta planeta, Producto producto, Integer stock, Double factorDemanda, Double factorOferta) {
        this.planeta = planeta;
        this.producto = producto;
        this.stock = stock;
        this.factorDemanda = factorDemanda;
        this.factorOferta = factorOferta;
    }

    // Constructor vacío
    public StockPlaneta() {
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Planeta getPlaneta() {
        return planeta;
    }

    public void setPlaneta(Planeta planeta) {
        this.planeta = planeta;
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

    public Double getFactorDemanda() {
        return factorDemanda;
    }

    public void setFactorDemanda(Double factorDemanda) {
        this.factorDemanda = factorDemanda;
    }

    public Double getFactorOferta() {
        return factorOferta;
    }

    public void setFactorOferta(Double factorOferta) {
        this.factorOferta = factorOferta;
    }
}

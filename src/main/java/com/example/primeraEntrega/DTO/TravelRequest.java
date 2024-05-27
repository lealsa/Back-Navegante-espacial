package com.example.primeraEntrega.DTO;




public class TravelRequest {
    private Long starId; // ID of the destination star
    private Long shipId; // ID of the ship traveling

    // Getters and Setters
    public Long getStarId() { return starId; }
    public void setStarId(Long starId) { this.starId = starId; }
    public Long getShipId() { return shipId; }
    public void setShipId(Long shipId) { this.shipId = shipId; }
}

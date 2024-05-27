package com.example.primeraEntrega.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.primeraEntrega.DTO.TravelRequest;
import com.example.primeraEntrega.model.Estrella;
import com.example.primeraEntrega.model.Planeta;
import com.example.primeraEntrega.service.NavigationService;

import java.util.List;

@RestController
@RequestMapping("/api/navigation")
public class NavigationController {

    @Autowired
    private NavigationService navigationService;

    @GetMapping("/nearby-stars/{starId}")
    public ResponseEntity<List<Estrella>> listNearbyStars(@PathVariable Long starId) {
        double maxDistance = 10.0; // Define how 'nearby' is calculated, e.g., 10 light-years
        return ResponseEntity.ok(navigationService.listNearbyStars(starId, maxDistance));
    }

    @GetMapping("/planets/{starId}")
    public ResponseEntity<List<Planeta>> listPlanets(@PathVariable Long starId) {
        return ResponseEntity.ok(navigationService.listPlanetsInStar(starId));
    }

    @PostMapping("/travel")
    public ResponseEntity<String> travel(@RequestBody TravelRequest travelRequest) {
        boolean success = navigationService.travel(travelRequest.getStarId(), travelRequest.getShipId());
        if (success) {
            return ResponseEntity.ok("Travel successful");
        } else {
            return ResponseEntity.badRequest().body("Travel failed");
        }
    }
}

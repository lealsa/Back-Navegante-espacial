package com.example.primeraEntrega.service;

import com.example.primeraEntrega.model.Jugador;
import com.example.primeraEntrega.model.Tripulacion;
import com.example.primeraEntrega.repository.CrewRepository;
import com.example.primeraEntrega.repository.PlayerRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrewService {

    
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private CrewRepository crewRepository;

    public CrewService(CrewRepository crewRepository) {
        this.crewRepository = crewRepository;
    }

    @Transactional(readOnly = true)
    public Tripulacion findTripulacionById(Long id) {
        Optional<Tripulacion> tripulacion = crewRepository.findById(id);
        tripulacion.ifPresent(t -> t.getJugadorIds().size()); // Initialize the collection
        return tripulacion.orElse(null);
    }
    public List<Tripulacion> findAllCrews() {
        return crewRepository.findAll();
    }

    public Tripulacion findCrewById(Long id) {
        return crewRepository.findById(id).orElseThrow(() -> new RuntimeException("Crew not found"));
    }

    public Tripulacion saveCrew(Tripulacion tripulacion) {
        return crewRepository.save(tripulacion);
    }

    public void deleteCrew(Long id) {
        crewRepository.deleteById(id);
    }

    public boolean joinCrew(Long crewId, Long playerId) {
        Tripulacion crew = crewRepository.findById(crewId).orElse(null);
        Jugador player = playerRepository.findById(playerId).orElse(null);
        if (crew != null && player != null && !crew.getJugadorIds().contains(player.getId())) {
            crew.getJugadorIds().add(player.getId());
            crewRepository.save(crew);
            return true;
        }
        return false;
    }
}

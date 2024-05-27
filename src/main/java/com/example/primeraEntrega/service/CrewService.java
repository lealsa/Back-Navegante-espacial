package com.example.primeraEntrega.service;

import com.example.primeraEntrega.model.Jugador;
import com.example.primeraEntrega.model.Tripulacion;
import com.example.primeraEntrega.repository.CrewRepository;
import com.example.primeraEntrega.repository.PlayerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrewService {

    @Autowired
    private CrewRepository crewRepository;
    @Autowired
    private PlayerRepository playerRepository;

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
        if (crew != null && player != null && !crew.getJugadores().contains(player)) {
            crew.getJugadores().add(player);
            crewRepository.save(crew);
            return true;
        }
        return false;
    }
}

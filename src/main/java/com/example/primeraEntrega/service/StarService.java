package com.example.primeraEntrega.service;

import com.example.primeraEntrega.model.Estrella;
import com.example.primeraEntrega.repository.StarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StarService {

    @Autowired
    private StarRepository starRepository;

    public List<Estrella> findAllStars() {
        return starRepository.findAll();
    }

    public Estrella findStarById(Long id) {
        return starRepository.findById(id).orElseThrow(() -> new RuntimeException("Star not found"));
    }

    public Estrella saveStar(Estrella estrella) {
        return starRepository.save(estrella);
    }

    public void deleteStar(Long id) {
        starRepository.deleteById(id);
    }
}

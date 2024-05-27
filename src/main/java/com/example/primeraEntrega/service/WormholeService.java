package com.example.primeraEntrega.service;

import com.example.primeraEntrega.model.AgujeroDeGusano;
import com.example.primeraEntrega.repository.WormholeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WormholeService {

    @Autowired
    private WormholeRepository wormholeRepository;

    public List<AgujeroDeGusano> findAllWormholes() {
        return wormholeRepository.findAll();
    }

    public AgujeroDeGusano findWormholeById(Long id) {
        return wormholeRepository.findById(id).orElseThrow(() -> new RuntimeException("Wormhole not found"));
    }

    public AgujeroDeGusano saveWormhole(AgujeroDeGusano agujeroDeGusano) {
        return wormholeRepository.save(agujeroDeGusano);
    }

    public void deleteWormhole(Long id) {
        wormholeRepository.deleteById(id);
    }
}

package com.example.primeraEntrega.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.primeraEntrega.model.Estrella;
import com.example.primeraEntrega.model.Nave;
import com.example.primeraEntrega.services.EstrellaService;
import com.example.primeraEntrega.services.NaveService;
import com.example.primeraEntrega.services.GameService;

@RestController
@RequestMapping("/api/navegar")
public class NavegarController {

    Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private EstrellaService estrellaService;

    @Autowired
    private NaveService naveService;

    @Autowired
    private GameService gameService;

    @RequestMapping("/list")
    public List<Estrella> listarEstrellas() {
        Double x = naveService.buscarNaveOptional("nave1").get().getCoordenadaX();
        Double y = naveService.buscarNaveOptional("nave1").get().getCoordenadaY();
        Double z = naveService.buscarNaveOptional("nave1").get().getCoordenadaZ();
        return estrellaService.listarEstrellasCercanas(x, y, z);
    }

    @PostMapping("/desplazar-nave/{estrellaId}")
    public void cambiarCoordenadasNave(@PathVariable Long estrellaId) {
        Estrella estrella = estrellaService.buscar(estrellaId);
        Nave nave = naveService.buscarNave("nave1");
        naveService.actualizarCoordenadasNave(estrella.getCoordenadaX(), estrella.getCoordenadaY(),
                estrella.getCoordenadaZ(), nave);

    }

}

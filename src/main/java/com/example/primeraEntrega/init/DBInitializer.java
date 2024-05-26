package com.example.primeraEntrega.init;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.primeraEntrega.model.Estrella;
import com.example.primeraEntrega.model.InventarioNave;
import com.example.primeraEntrega.model.InventarioPlaneta;
import com.example.primeraEntrega.model.Jugador;
import com.example.primeraEntrega.model.Nave;
import com.example.primeraEntrega.model.Game;
import com.example.primeraEntrega.model.Planeta;
import com.example.primeraEntrega.model.Producto;
import com.example.primeraEntrega.model.TipoNave;
import com.example.primeraEntrega.repository.EstrellaRepository;
import com.example.primeraEntrega.repository.InventarioNaveRepository;
import com.example.primeraEntrega.repository.InventarioPlanetaRepository;
import com.example.primeraEntrega.repository.JugadorRepository;
import com.example.primeraEntrega.repository.NaveRepository;
import com.example.primeraEntrega.repository.GameRepository;
import com.example.primeraEntrega.repository.PlanetaRepository;
import com.example.primeraEntrega.repository.ProductoRepository;
import com.example.primeraEntrega.repository.TipoNaveRepository;

@Component
public class DBInitializer implements CommandLineRunner {

        @Autowired
        private NaveRepository naveRepository;
        @Autowired
        private EstrellaRepository estrellaRepository;
        @Autowired
        private JugadorRepository jugadorRepository;
        @Autowired
        private PlanetaRepository planetaRepository;
        @Autowired
        private ProductoRepository productoRepository;
        @Autowired
        private TipoNaveRepository tipoNaveRepository;
        @Autowired
        private InventarioNaveRepository inventarioNaveRepository;
        @Autowired
        private InventarioPlanetaRepository inventarioPlanetaRepository;
        @Autowired
        private GameRepository partidaRepository;

        @Override
        public void run(String... args) throws Exception {

                Logger loggy = LoggerFactory.getLogger(getClass());

                List<Producto> productos = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                        Producto producto = new Producto((i * 3.6), "nombre" + (i + 10));
                        productos.add(producto);

                }
                productoRepository.saveAll(productos);

                List<TipoNave> tipoNaves = new ArrayList<>();
                Random random = new Random();
                for (int i = 0; i < 5; i++) {
                        TipoNave tipoNave = new TipoNave("naveT" + i, random.nextDouble() * 2.5,
                                        random.nextDouble() * 3.6);
                        tipoNaves.add(tipoNave);
                }

                tipoNaveRepository.saveAll(tipoNaves);

                List<Nave> naves = new ArrayList<>();
                int min = 1000000;
                for (int i = 0; i < 5; i++) {
                        Nave nave = new Nave(
                                        random.nextDouble() + min,
                                        random.nextDouble() * (90 - 10) + 10,
                                        random.nextDouble() * (90 - 10) + 10,
                                        random.nextDouble() * (90 - 10) + 10,
                                        "nave" + i,
                                        random.nextDouble() * 200 + 15, random.nextDouble() * 500.5);
                        nave.setTipo(tipoNaves.get(i));
                        naves.add(nave);

                }
                naveRepository.saveAll(naves);

                for (Nave nave : naves) {
                        for (int k = 0; k < productos.size(); k++) {
                                InventarioNave inventarioNave = new InventarioNave();
                                inventarioNave.setNave(nave);
                                inventarioNave.setCantidad(20.2 + k * 2);
                                inventarioNave.setfOfertaDemanda(random.nextDouble() * 1000000);
                                inventarioNave.setProducto(productos.get(k));
                                inventarioNaveRepository.save(inventarioNave);
                        }
                }

                List<Jugador> jugadores = new ArrayList<>();
                for (int i = 0; i < 25; i++) {
                        Jugador jugador = new Jugador("rol" + i, "jugador" + i, "hola" + i);
                        jugadores.add(jugador);
                }

                List<List<Jugador>> equipos = new ArrayList<>();
                for (int i = 0; i < 5; i++) {
                        List<Jugador> equipo = new ArrayList<>();
                        for (int j = 0; j < 5; j++) {
                                equipo.add(jugadores.get(i * 5 + j));
                        }
                        equipos.add(equipo);
                }

                for (int i = 0; i < 5; i++) {
                        List<Jugador> equipo = equipos.get(i);
                        Nave nave = naves.get(i);
                        for (Jugador jugador : equipo) {
                                jugador.setNave(nave);
                                nave.addJugador(jugador);
                        }
                }

                jugadorRepository.saveAll(jugadores);
                Game game = new Game(0.0, naves.get(0).getDinero(), 1.0);
                partidaRepository.save(game);

                int cont2 = 0;
                for (int i = 0; i < 100; i++) {
                        Estrella estrella = new Estrella(
                                        random.nextDouble() * (90 - 10) + 10,
                                        random.nextDouble() * (90 - 10) + 10,
                                        random.nextDouble() * (90 - 10) + 10);

                        estrellaRepository.save(estrella);
                        if (random.nextDouble() < 1) {
                                int numPlanets = random.nextInt(3) + 1;
                                loggy.info("numPlanetas" + numPlanets);
                                for (int j = 0; j < numPlanets; j++) {
                                        Planeta planeta = new Planeta("Planeta_" + i + "_" + j);
                                        planeta.setEstrella(estrella);
                                        estrella.addPlaneta(planeta);
                                        planetaRepository.save(planeta);

                                        for (int k = 0; k < 3; k++) {
                                                InventarioPlaneta inventarioPlaneta = new InventarioPlaneta();
                                                inventarioPlaneta.setPlaneta(planeta);
                                                inventarioPlaneta.setCantidad(20.2 + k * 2);

                                                Double randomNumber = random.nextDouble(1000001);
                                                inventarioPlaneta.setfOfertaDemanda(randomNumber);

                                                if (cont2 == 10) {
                                                        cont2 = 0;
                                                }
                                                if (cont2 < 10) {
                                                        inventarioPlaneta.setProducto(productos.get(cont2));
                                                        cont2++;
                                                }
                                                inventarioPlanetaRepository.save(inventarioPlaneta);
                                        }

                                }

                        }
                }

        }
}

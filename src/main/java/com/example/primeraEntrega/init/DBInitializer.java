package com.example.primeraEntrega.init;

import java.util.*;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.example.primeraEntrega.model.*;
import com.example.primeraEntrega.repository.*;

@Component
public class DBInitializer implements CommandLineRunner {

    @Autowired private StarRepository estrellaRepository;
    @Autowired private PlanetRepository planetaRepository;
    @Autowired private ProductRepository productoRepository;
    @Autowired private PlayerRepository jugadorRepository;
    @Autowired private ShipRepository naveRepository;
    @Autowired private CrewRepository tripulacionRepository;
    @Autowired private GameRepository gameRepository;
    @Autowired private WormholeRepository agujeroDeGusanoRepository;

    private void createWormholes(List<Estrella> estrellas) {
        int wormholeCount = 5000; // Define cuántos agujeros de gusano quieres crear
        List<AgujeroDeGusano> wormholes = new ArrayList<>();
        for (int i = 0; i < wormholeCount; i++) {
            Estrella start = estrellas.get(random.nextInt(estrellas.size()));
            Estrella end = estrellas.get(random.nextInt(estrellas.size()));
            while (start.getId().equals(end.getId())) { // Asegúrate de que no sea la misma estrella
                end = estrellas.get(random.nextInt(estrellas.size()));
            }
            double distancia = random.nextDouble() * 10; // Genera una distancia aleatoria para el agujero de gusano
            AgujeroDeGusano wormhole = new AgujeroDeGusano(distancia, start, end);
            wormholes.add(wormhole);
        }
        agujeroDeGusanoRepository.saveAll(wormholes);
    }
    private Random random = new Random();

    // Arrays de imágenes
    private static final String[] PRODUCT_IMAGES = {
        "https://www.svgrepo.com/show/303723/crystal-shard.svg",
        "https://www.svgrepo.com/show/303725/item-bag.svg",
        "https://www.svgrepo.com/show/303724/food.svg",
        "https://www.svgrepo.com/show/303719/dragon-egg.svg",
        "https://www.svgrepo.com/show/303718/armor.svg",
        "https://www.svgrepo.com/show/303737/spell-book.svg",
        "https://www.svgrepo.com/show/303727/helmet.svg",
        "https://www.svgrepo.com/show/303739/sword.svg",
        "https://www.svgrepo.com/show/303742/destructive-magic.svg"
    };

    private static final String[] STAR_IMAGES = {
        "https://www.svgrepo.com/show/528679/star-rings.svg",
        "https://www.svgrepo.com/show/528675/star-ring.svg",
        "https://www.svgrepo.com/show/528674/star-rainbow.svg",
        "https://www.svgrepo.com/show/528673/star-fall-2.svg",
        "https://www.svgrepo.com/show/528672/star-fall-minimalistic-2.svg",
        "https://www.svgrepo.com/show/528671/star-fall.svg",
        "https://www.svgrepo.com/show/528670/star-fall-minimalistic.svg",
        "https://www.svgrepo.com/show/528669/star-circle.svg",
        "https://www.svgrepo.com/show/528668/star-angle.svg",
        "https://www.svgrepo.com/show/527912/star-shine.svg"
    };

    private static final String[] SHIP_IMAGES = {
        "https://opengameart.org/sites/default/files/faction1-preview.png",
        "https://opengameart.org/sites/default/files/faction4-preview.png",
        "https://opengameart.org/sites/default/files/preview_165.png",
        "https://opengameart.org/sites/default/files/preview_161.png",
        "https://opengameart.org/sites/default/files/faction4-station-preview.png",
        "https://opengameart.org/sites/default/files/faction2-preview.png",
        "https://opengameart.org/sites/default/files/faction5-preview.png",
        "https://opengameart.org/sites/default/files/faction7-preview.png"
    };

    private static final Logger logger = LoggerFactory.getLogger(DBInitializer.class);

    @Override
    public void run(String... args) throws Exception {
        logger.info("Creando estrellas...");
        List<Estrella> estrellas = createStars();
        logger.info("Estrellas creadas: {}", estrellas.size());

        logger.info("Creando productos...");
        createProducts();
        
        logger.info("Creando jugadores y tripulaciones...");
        createPlayersAndCrews(estrellas);

        logger.info("Creando juegos...");
        createGames();

        logger.info("Creando agujeros de gusano...");
        createWormholes(estrellas);
    }
private List<Estrella> createStars() {
        List<Estrella> estrellas = new ArrayList<>();
        List<Planeta> todosLosPlanetas = new ArrayList<>();
        
        // Primero crea todas las estrellas sin planetas
        for (int i = 0; i < 40000; i++) {
            String nombreEstrella = "Estrella_" + i;
            double coordX = random.nextDouble() * 300;
            double coordY = random.nextDouble() * 300;
            double coordZ = random.nextDouble() * 300;
            String imagen = STAR_IMAGES[random.nextInt(STAR_IMAGES.length)];
            Estrella estrella = new Estrella(nombreEstrella, coordX, coordY, coordZ, imagen);
            estrellas.add(estrella);
        }
        // Guardar todas las estrellas antes de crear planetas
        estrellaRepository.saveAll(estrellas);
    
        // Ahora crea los planetas asignándolos a las estrellas ya guardadas
        for (Estrella estrella : estrellas) {
            if (random.nextDouble() < 0.01) { // 1% de las estrellas tendrán planetas
                int numPlanetas = random.nextInt(3) + 1; // Entre 1 y 3 planetas por estrella
                for (int j = 0; j < numPlanetas; j++) {
                    String nombrePlaneta = "Planeta_" + estrella.getNombreEstrella() + "_" + j;
                    String imagenPlaneta = "imagen_planeta_" + estrella.getNombreEstrella() + "_" + j + ".png";
                    Planeta planeta = new Planeta(nombrePlaneta, imagenPlaneta, estrella);
                    todosLosPlanetas.add(planeta);
                }
            }
        }
        // Guardar todos los planetas al final
        planetaRepository.saveAll(todosLosPlanetas);
        System.out.println("Planetas creados: " + todosLosPlanetas.size());
        System.out.println("Estrellas después de guardar: " + estrellaRepository.count());
        System.out.println("Planetas después de guardar: " + planetaRepository.count());

        return estrellas;
    }
    
    
    private void createPlayersAndCrews(List<Estrella> estrellas) {
        List<Jugador> jugadores = new ArrayList<>();
        List<Nave> naves = new ArrayList<>();
        List<Tripulacion> tripulaciones = new ArrayList<>();
        String[] roles = {"Capitán", "Piloto", "Comerciante"};
        List<Estrella> estrellasConPlanetas = estrellas.stream()
        .filter(e -> !(e.getPlanetas().isEmpty()))
        .collect(Collectors.toList());

if (estrellasConPlanetas.isEmpty()) {
System.out.println("No hay estrellas con planetas. No se pueden crear jugadores ni tripulaciones.");
return;  // Salir si no hay estrellas con planetas
}

        for (int i = 0; i < 100; i++) {
            String usuario = "Jugador_" + i;
            String contrasena = "Password_" + i;
            String rol = roles[i % 3];
            Jugador jugador = new Jugador(usuario, contrasena, rol);
            jugadores.add(jugador);
    
            if (i % 10 == 0) {  // Cada 10 jugadores, una nueva tripulación y nave
                String nombreNave = "Nave_Awesome_" + (i / 10);
                String fotoNave = SHIP_IMAGES[(i / 10) % SHIP_IMAGES.length];
                double capacidadMax = 1000 + random.nextDouble() * 500;
                double velocidad = 10000 + random.nextDouble() * 5000;
    
                // Asignar estrella y planeta al azar
                Estrella estrella = estrellasConPlanetas.get(random.nextInt(estrellasConPlanetas.size()));
                Planeta planeta = estrella.getPlanetas().get(random.nextInt(estrella.getPlanetas().size()));
    
                Nave nave = new Nave(fotoNave, nombreNave, capacidadMax, velocidad, estrella, planeta);
                naves.add(nave);
    
                Tripulacion tripulacion = new Tripulacion(nave);
                tripulaciones.add(tripulacion);
            }
            tripulaciones.get(i / 10).getJugadorIds().add(jugador.getId());
        }
    
        jugadorRepository.saveAll(jugadores);
        naveRepository.saveAll(naves); // Asegurarse que los planetas ya están guardados antes de guardar naves
        tripulacionRepository.saveAll(tripulaciones);
    }
    private void createProducts() {
        List<Producto> productos = new ArrayList<>();
        for (int i = 0; i < 500; i++) {
            double volumen = 0.1 + (0.9 * random.nextDouble());
            String imagen = PRODUCT_IMAGES[i % PRODUCT_IMAGES.length];
            String nombre = "Producto_" + i;
            Producto producto = new Producto(volumen, imagen, nombre);
            productos.add(producto);
        }
        productoRepository.saveAll(productos);
    }
    
    private void createGames() {
        List<Game> games = new ArrayList<>();
        for (Tripulacion tripulacion : tripulacionRepository.findAll()) {
            Game game = new Game(tripulacion.getNave());
            games.add(game);
        }
        gameRepository.saveAll(games);
    }
}

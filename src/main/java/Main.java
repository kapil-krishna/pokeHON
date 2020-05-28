import controllers.PokemonController;
import models.db.PokemonDBModel;
import models.pokeAPI.PokemonResponseModel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import services.PokeAPIService;
import services.PokemonService;

import java.util.List;

@SpringBootApplication
public class Main {

    public static void main (String[] args) {
        SpringApplication.run(Main.class, args);
    }
}

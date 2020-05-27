import controllers.PokemonController;
import models.db.PokemonDBModel;
import models.pokeAPI.PokemonResponseModel;
import services.PokeAPIService;
import services.PokemonService;

import java.util.List;

public class Main {

    public static void main (String[] args) {
        PokemonService pokemonService = new PokemonService();
        PokeAPIService pokeAPIService = new PokeAPIService();
        PokemonController pokemonController = new PokemonController(pokemonService, pokeAPIService);

        pokemonController.fetchPokemonData(5);

//        PokeAPIService pokeAPIService = new PokeAPIService();
//
//        PokemonResponseModel pokemon = pokeAPIService.getPokemonFromApi(1);
//
//        System.out.println(pokemon.getName());

    }
}

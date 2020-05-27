import models.db.PokemonDBModel;
import models.pokeAPI.PokemonResponseModel;
import services.PokeAPIService;
import services.PokemonService;

import java.util.List;

public class Main {

    public static void main (String[] args) {
        PokeAPIService pokeAPIService = new PokeAPIService();
        PokemonService pokemonService = new PokemonService();

        PokemonResponseModel bulbasaur = pokeAPIService.getPokemonFromApi(1);

        pokemonService.addPokemonToDB(bulbasaur);
    }
}

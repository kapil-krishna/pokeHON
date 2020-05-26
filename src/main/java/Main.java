import models.db.PokemonDBModel;
import services.PokemonService;

import java.util.List;

public class Main {

    public static void main (String[] args) {
        PokemonService pokemonService = new PokemonService();

        PokemonDBModel bulbasaur = pokemonService.getPokemon(1);
        System.out.println(bulbasaur.getImageURL());
    }
}

package models.view;

import models.db.PokemonDBModel;

public class PokemonViewModel {
    private final PokemonDBModel pokemonDBModel;

    public PokemonViewModel(PokemonDBModel pokemonDBModel) {
        this.pokemonDBModel = pokemonDBModel;
    }

    public int getId() {
        return pokemonDBModel.getId();
    }
}

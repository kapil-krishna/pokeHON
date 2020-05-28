package com.pokehon.models.view;

import com.pokehon.models.db.PokemonDBModel;

import java.util.List;
import java.util.stream.Collectors;

public class AllPokemonViewModel {
    private final List<PokemonViewModel> pokemon;

    public AllPokemonViewModel(List<PokemonDBModel> pokemonDBModel) {
        this.pokemon = pokemonDBModel.stream()
                .map(PokemonViewModel::new)
                .collect(Collectors.toList());
    }

    public List<PokemonViewModel> getPokemon() {
        return pokemon;
    }
}

package com.pokehon.controllers;

import com.pokehon.models.db.PokemonDBModel;
import com.pokehon.services.PokeAPIService;
import com.pokehon.services.PokemonService;
import com.pokehon.models.pokeAPI.PokemonResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/pokemon")
public class PokemonController {

    private PokemonService pokemonService;
    private PokeAPIService pokeAPIService;

    @Autowired
    public PokemonController(PokemonService pokemonService, PokeAPIService pokeAPIService) {
        this.pokemonService = pokemonService;
        this.pokeAPIService = pokeAPIService;
    }

    @RequestMapping(value = "fetch-pokemon-data")
    public void fetchPokemonData(int limit) throws IOException {
        System.out.println("-----\n" +
                "START");
        int currentPokemon = pokemonService.getNumberOfPokemon();
        System.out.println("Looking for " + limit + " Pokemon from PokeAPI...");
        List<PokemonResponseModel> allPokemon = pokeAPIService.getAllPokemonFromApi(limit);
        System.out.println("Adding Pokemon to database...");
        pokemonService.addAllPokemonToDB(allPokemon);
        int newPokemon = pokemonService.getNumberOfPokemon() - currentPokemon;

        if (newPokemon == limit) {
            System.out.println("Succesfully inserted " + newPokemon + " rows");
        } else if (newPokemon > 0) {
            System.out.println("Succesfully inserted " + newPokemon + " rows");
            System.out.println("Remaining " + (allPokemon.size() - newPokemon) + " Pokemon already exist in database");
        } else {
            System.out.println("No rows inserted; database is already up to date");
        }

        System.out.println("END\n" +
                "-----");

    }

    @RequestMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity getPokemonById(@PathVariable int id) {
        PokemonDBModel pokemon = pokemonService.getPokemon(id);
        return ResponseEntity.ok().body(pokemon);
    }

    @RequestMapping(value = "/noOfPokemon")
    @ResponseBody
    public int getNumberOfPokemonInDB() {
        return pokemonService.getNumberOfPokemon();
    }

}

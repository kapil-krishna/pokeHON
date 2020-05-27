package controllers;

import models.db.PokemonDBModel;
import models.pokeAPI.PokemonResponseModel;
import models.view.PokemonViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import services.PokeAPIService;
import services.PokemonService;

import java.util.List;

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
    public void fetchPokemonData(int limit) {
        List<PokemonResponseModel> allPokemon = pokeAPIService.getAllPokemonFromApi(limit);
        int rowsInserted = pokemonService.addAllPokemonToDB(allPokemon);
        System.out.println("Succesfully inserted rows: " + rowsInserted);
    }

}

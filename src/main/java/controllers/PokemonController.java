package controllers;

import models.db.PokemonDBModel;
import models.view.PokemonViewModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import services.PokemonService;

import java.util.List;

@Controller
@RequestMapping("/pokemon")
public class PokemonController {
    private final PokemonService pokemonService;

    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

//    @RequestMapping(value = "", method = RequestMethod.GET)
//    public List<PokemonDBModel> getAllPokemon() {
//        List<PokemonDBModel> pokemon = pokemonService.getAllPokemon();
//        PokemonViewModel pokemonViewModel = new PokemonViewModel(pokemon);
//    }

//    @RequestMapping(value = "", method = RequestMethod.GET)
//    public ModelAndView getSinglePokemon(@PathVariable("id") int id) {
//        PokemonDBModel pokemonDBModel = pokemonService.getPokemon(id);
//        PokemonViewModel pokemonViewModel = new PokemonViewModel(pokemonDBModel);
//
//        return new ModelAndView("pokemon", "model", pokemonViewModel);
//    }

}

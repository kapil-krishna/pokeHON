package services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.pokeAPI.PokemonResponseModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.jmx.support.ObjectNameManager;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import java.util.Arrays;
import java.util.List;

@Service
public class PokeAPIService {

//    public List<PokemonResponseModel> getAllPokemon(int limit) {
//
//        for (int i = 0; i < limit; i++) {
//
//        }
//    }

    public PokemonResponseModel getPokemonFromApi(int id) {
        String uri = "https://pokeapi.co/api/v2/pokemon/" + id;
        RestTemplate  restTemplate = new RestTemplate();

        return restTemplate.getForObject(uri, PokemonResponseModel.class);

    }



}

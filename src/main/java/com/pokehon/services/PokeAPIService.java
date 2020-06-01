package com.pokehon.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pokehon.CustomWebClient;
import com.pokehon.models.pokeAPI.PokemonResponseModel;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class PokeAPIService {

    public List<PokemonResponseModel> getAllPokemonFromApi(int limit) throws IOException {
        List<PokemonResponseModel> allPokemon = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();

        for (int i = 1; i < limit + 1; i++) {
            ResponseEntity response = getPokemonFromApi(i);
            if (response.getStatusCode().is2xxSuccessful()) {
                PokemonResponseModel pkmn = (PokemonResponseModel) response.getBody();
                if (pkmn.getSprites().getFront_default() != null) {
                    allPokemon.add(pkmn);
                }
            } else {
                break;
            }
        }

        System.out.println("Found " + allPokemon.size() + " Pokemon with images");
        return allPokemon;
    }

    public ResponseEntity getPokemonFromApi(int id) {
        String uri = "https://pokeapi.co/api/v2/pokemon/" + id;
        RestTemplate restTemplate = new RestTemplate();

        try {
            return restTemplate.exchange(
                    uri,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<PokemonResponseModel>(){}
            );
        } catch (HttpClientErrorException e) {
            System.out.println("Error at id = " + id + ":" + e);
        }

        return ResponseEntity.badRequest().body(Collections.singletonMap("Error", "Resource not found"));


//        WebClient webClient = CustomWebClient.createCustomWebClient("http://locahost:8080", 10000);
//
//        return webClient
//                .get()
//                .uri(uri)
//                .exchange()
//                .flatMap(clientResponse -> clientResponse.bodyToMono(PokemonResponseModel.class))
//                .block();

    }
}

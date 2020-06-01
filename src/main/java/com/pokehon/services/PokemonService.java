package com.pokehon.services;

import com.pokehon.models.db.PokemonDBModel;
import com.pokehon.models.pokeAPI.PokemonResponseModel;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PokemonService {

    Jdbi jdbi = new DatabaseService().createJdbiConnection();

    public List<PokemonDBModel> getAllPokemon() {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM pokemon")
                        .mapToBean(PokemonDBModel.class)
                        .list());
    }

    public PokemonDBModel getPokemon(int id) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM Pokemon WHERE id = :id")
                        .bind("id", id)
                        .mapToBean(PokemonDBModel.class)
                        .findOnly());
    }

    public int addAllPokemonToDB(List<PokemonResponseModel> allPokemon) {
        int rowsInserted = 0;
        for (PokemonResponseModel pokemon : allPokemon) {
            addPokemonToDB(pokemon);
            rowsInserted++;
        }
        return rowsInserted;
    }

    public void addPokemonToDB(PokemonResponseModel pokemon) {
        jdbi.withHandle(handle ->
                handle.createUpdate(
                        "SELECT setval(pg_get_serial_sequence('pokemon', 'id'), coalesce(max(id),0) + 1, false) FROM pokemon;" +
                                "INSERT INTO pokemon " +
                                "(name, \"imageURL\", height, weight, type1, type2) " +
                                "VALUES " +
                                "(:name, :imageURL, :height, :weight, :type1, :type2)" +
                                "ON CONFLICT (name)" +
                                "DO NOTHING;")
                        .bind("name", pokemon.getName())
                        .bind("imageURL", pokemon.getSprites().getFront_default())
                        .bind("height", pokemon.getHeight())
                        .bind("weight", pokemon.getWeight())
                        .bind("type1", pokemon.getTypes()[0].getType().getName())
                        .bind("type2", getSecondTypeIfAvailable(pokemon))
                        .execute()
        );
    }

    public String getSecondTypeIfAvailable(PokemonResponseModel pokemon) {
        if (pokemon.getTypes().length == 2) {
            return pokemon.getTypes()[1].getType().getName();
        } else {
            return null;
        }
    }

    public int getNumberOfPokemon() {
        return jdbi.withHandle(handle ->
                        handle.createQuery("SELECT COUNT(*) FROM pokemon")
                .mapTo(Integer.class)
                .findOnly());
    }
}

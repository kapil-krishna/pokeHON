package services;

import models.db.PokemonDBModel;
import models.pokeAPI.PokemonResponseModel;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Service;

import java.sql.Array;
import java.util.List;

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
                        "INSERT INTO pokemon " +
                                "(name, \"imageURL\", height, weight, type1, type2) " +
                                "VALUES " +
                                "(:name, :imageURL, :height, :weight, :type1, :type2)")
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
}

package services;

import models.db.PokemonDBModel;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Service;

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
}

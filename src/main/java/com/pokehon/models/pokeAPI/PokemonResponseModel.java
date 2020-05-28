package com.pokehon.models.pokeAPI;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PokemonResponseModel {

    private String name;
    private SpritesResponseModel sprites;
    private TypeResponseModel[] types;
    private int height;
    private int weight;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SpritesResponseModel getSprites() {
        return sprites;
    }

    public void setSprites(SpritesResponseModel sprites) {
        this.sprites = sprites;
    }

    public TypeResponseModel[] getTypes() {
        return types;
    }

    public void setTypes(TypeResponseModel[] types) {
        this.types = types;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}

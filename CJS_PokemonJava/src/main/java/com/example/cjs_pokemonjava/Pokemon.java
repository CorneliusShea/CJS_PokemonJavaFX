package com.example.cjs_pokemonjava;

import javafx.scene.image.Image;

public class Pokemon
{
    private int pokemonID;

    private String pokemonName;

    private int pokemonHealth;

    private int[] moves = new int[2];

    private Image pokemonImage;

    public Pokemon(int pokemonID, String pokemonName, int pokemonHealth, int move1_id, int move2_id) {
        this.pokemonID = pokemonID;
        this.pokemonName = pokemonName;
        this.pokemonHealth = pokemonHealth;

        moves[0] = move1_id;
        moves[1] = move2_id;
    }

    public int getPokemonID() {
        return pokemonID;
    }

    public String getPokemonName() {
        return pokemonName;
    }

    public int[] getMoves() {
        return moves;
    }

    public int getPokemonHealth() {
        return pokemonHealth;
    }

    public void setPokemonHealth(int pokemonHealth) {
        this.pokemonHealth = pokemonHealth;
    }
}

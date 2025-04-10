package com.example.cjs_pokemonjava;

public class Pokemon
{
    private int pokemonID;

    private String pokemonName;

    private int[] moves = new int[2];

    public Pokemon(int pokemonID, String pokemonName, int[] moves) {
        this.pokemonID = pokemonID;
        this.pokemonName = pokemonName;
        this.moves = moves;
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
}

package com.example.pokeapi;

import androidx.lifecycle.LiveData;

import java.util.List;

public interface PokemonDao {
   // @Query("select * from pokemon")
    LiveData<List<Pokemon>> getPokemon();

    //@Insert
    void addPokemon(Pokemon movie);

    //@Insert
    void addPokemons(List<Pokemon> pokemon);

    //@Delete
    //void deletePokemon(Pokemon pokemon)

    //@Query("DELETE FROM pokemon")
    //void deletePokemon();
}

//}

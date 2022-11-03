package com.example.pokeapi;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class PokemonApi {

    ArrayList<Pokemon> GetPokemon() {
        String BASE_URL = "https://pokeapi.co/api/v2/pokemon/";



        try{
            String result = HttpUtils.get(BASE_URL);

            JSONObject jsonResult = new JSONObject(result);
            JSONArray results = jsonResult.getJSONArray("results");
            ArrayList<Pokemon>pokemons=new ArrayList<>();

           for (int i=0;i<results.length();i++){
               JSONObject pokemonJson = results.getJSONObject(i);
               Pokemon pokemon=new Pokemon();
               pokemon.setName(pokemonJson.getString("name"));
               pokemon.setDetailsUrl(pokemonJson.getString("url"));

               String resultDetails = HttpUtils.get(pokemon.getDetailsUrl());
               JSONObject JSONDetails = new JSONObject(resultDetails);
               JSONObject sprites = JSONDetails.getJSONObject("sprites");
               String spriteDefault = sprites.getString("front_default");
               pokemon.setImage(spriteDefault);
               pokemon.setHeight(JSONDetails.getInt("height"));

               pokemons.add(pokemon);


           }
            return pokemons;


        }
        catch(Exception e){
            System.err.println(e.getMessage());
        }

        return null;
    }
}

package com.example.pokeapi;

import android.app.Application;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PokemonViewModel extends AndroidViewModel {
    private final Application app;
    private MutableLiveData<List<Pokemon>> pokemons;

    public PokemonViewModel(@NonNull Application application) {
        super(application);
        this.app = application;
    }

    public MutableLiveData<List<Pokemon>> getPokemons(){
        if(pokemons==null){
            pokemons=new MutableLiveData<>();
        }
        return pokemons;
    }


    public void refresh() {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(app.getApplicationContext());
        String tipo = preferences.getString("tipo", "");
        ExecutorService executor = Executors.newSingleThreadExecutor();

        executor.execute(() -> {
            PokemonApi api = new PokemonApi();
            ArrayList<Pokemon> pokemons = api.GetPokemon();
            this.pokemons.postValue(pokemons);
        });
    }
}

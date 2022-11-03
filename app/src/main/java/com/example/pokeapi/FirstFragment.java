package com.example.pokeapi;

import android.content.SharedPreferences;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.preference.PreferenceManager;

import com.example.pokeapi.databinding.FragmentFirstBinding;

import org.w3c.dom.Text;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    private MiAdapter adapter;
    ArrayList<Pokemon>Pokemons;
    ArrayList<Pokemon>items;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);

        return binding.getRoot();

    }


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view,savedInstanceState);
        items = new ArrayList<Pokemon>();
        PokemonApi api = new PokemonApi();
        adapter = new MiAdapter(

                getContext(),
                R.layout.lv_pokemon_raw,
                R.id.txtListName,
                items

        );
        binding.lvPokemons.setAdapter(adapter);

        binding.lvPokemons.setOnItemClickListener(((adapterView, view1, i, l) ->  {
            Pokemon pokemon = (Pokemon) adapterView.getItemAtPosition(i);
            Pokemon item =(Pokemon) adapterView.getItemAtPosition(i);
            Bundle datos=new Bundle();
            datos.putSerializable("item",item);

            NavHostFragment.findNavController(FirstFragment.this).navigate(R.id.action_FirstFragment_to_SecondFragment,datos);
        }));

        PokemonViewModel viewModel = new ViewModelProvider(getActivity()).get(PokemonViewModel.class);
        viewModel.refresh();
        viewModel.getPokemons().observe(getActivity(),pokemons -> {
            adapter.clear();
            adapter.addAll(pokemons);
        });
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.Refresh){

        }
        return super.onOptionsItemSelected(item);
    }

   public void refresh() {

        Toast.makeText(getContext(),"Refrescando",Toast.LENGTH_LONG).show();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
        String tipo = preferences.getString("tipo","");
        if(!tipo.equals("")){

        }
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(() -> {
            PokemonApi api = new PokemonApi();

            Pokemons=api.GetPokemon();
            handler.post(()->{
                adapter.clear();
                adapter.addAll(Pokemons);

            });
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    public void onStart() {
        super.onStart();
        refresh();
    }

}

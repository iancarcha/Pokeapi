package com.example.pokeapi;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;

import java.util.List;

public class MiAdapter extends ArrayAdapter<Pokemon> {

    public MiAdapter(@NonNull Context context, int lv_pokemon_raw, int resource, List<Pokemon> objects) {
        super(context, resource, objects);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        Pokemon pokemon = getItem(position);
        Log.e("xxx", pokemon.toString());

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.lv_pokemon_raw, parent, false);
        }


        TextView txtPokemon = convertView.findViewById(R.id.txtListName);

        ImageView ivPosterImage = convertView.findViewById(R.id.imgpoke);


        txtPokemon.setText(pokemon.getName());


        Glide.with(getContext()).load(
                pokemon.getImage()
        ).into(ivPosterImage);


        return convertView;
    }
}

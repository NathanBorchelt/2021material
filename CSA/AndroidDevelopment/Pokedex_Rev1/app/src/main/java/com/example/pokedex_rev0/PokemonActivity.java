package com.example.pokedex_rev0;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PokemonActivity extends AppCompatActivity {

    private TextView pokeNameTxt;
    private TextView pokeNumText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon);
        pokeNameTxt = findViewById(R.id.pokemonName);
        pokeNumText = findViewById(R.id.pokemonNumber);


        Intent intentInfo = getIntent();
        String pokeName = intentInfo.getStringExtra("name");
        String pokeNum = intentInfo.getStringExtra("number");
        pokeNameTxt.setText(pokeName);
        pokeNumText.setText(pokeNum);


    }
}

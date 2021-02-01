package com.example.pokedex_rev0;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PokemonActivity extends AppCompatActivity {

    private TextView pokeNameTxt;
    private TextView pokeNumText;
    private String url;
    private TextView type1TV;
    private TextView type2TV;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon);
        pokeNameTxt = findViewById(R.id.pokemonName);
        pokeNumText = findViewById(R.id.pokemonNumber);
        type1TV = findViewById(R.id.type1);
        type2TV = findViewById(R.id.type2);

        requestQueue = Volley.newRequestQueue(getApplicationContext());
        url = getIntent().getStringExtra("url");

        Log.d("PokemonActivity.onCreat", url);

        pokeNameTxt.setText(getIntent().getStringExtra("name"));
        //pokeNumText.setText(pokeNum);
        load();

    }

    public void load(){
        type2TV.setText("");
        type1TV.setText("");
        pokeNameTxt.setText("");
        pokeNumText.setText("");

        JsonObjectRequest request = new JsonObjectRequest(0, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("PokeActivityRequest", "began request here");
                try {
                    JSONArray typeEntries = response.getJSONArray("types");
                    for (byte i = 0; i < typeEntries.length(); i++) {
                        JSONObject typeEntry = typeEntries.getJSONObject(i);
                        short slot = (short) typeEntry.getInt("slot");
                        String type = typeEntry.getJSONObject("type").getString("name");
                        if (slot == 1) {
                            type1TV.setText(type);
                        } else {
                            type2TV.setText(type);
                        }
                    }
                    Log.d("typeTesting", response.getString("name"));
                    Log.d("typeTesting", response.getString("id"));
                    pokeNameTxt.setText(response.getString("name").substring(0, 1).toUpperCase() +response.getString("name").substring(1));//gets pokemon name
                    pokeNumText.setText(response.getString("id")); //gets id number
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("pokemonActivity","Pokemon list Error");
            }
        });

        requestQueue.add(request);

    }
}

package com.example.pokedex_rev0;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class PokedexAdapter extends RecyclerView.Adapter<PokedexAdapter.PokedexViewHolder> {

    public static class PokedexViewHolder extends RecyclerView.ViewHolder{

        public LinearLayout containerView;
        public TextView textView;

        public PokedexViewHolder(@NonNull View itemView) {
            super(itemView);
            containerView = itemView.findViewById(R.id.pokedex_row);
            textView = itemView.findViewById(R.id.pokedex_row_txt);

            containerView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Pokemon current = (Pokemon) containerView.getTag();
                    Intent intent = new Intent(view.getContext(),PokemonActivity.class);
                    intent.putExtra("name", current.getName());
                    intent.putExtra("number",current.getNumber());
                    view.getContext().startActivity(intent);
                }
            });
        }
    }

    private List<Pokemon> pokemon = Arrays.asList(
            new Pokemon("MissingNo.", (short) 0),
            new Pokemon("Bulbasaur", (short) 1),
            new Pokemon("Ivysaur", (short) 2),
            new Pokemon("Venusaur", (short) 3),
            new Pokemon("Charmander", (short) 4),
            new Pokemon("Charmeleon", (short) 5),
            new Pokemon("Charizard", (short) 6),
            new Pokemon("Squirtle", (short) 7),
            new Pokemon("Wartortle", (short) 8),
            new Pokemon("Blastoise", (short) 9),
            new Pokemon("Caterpie", (short) 10),
            new Pokemon("Metapod", (short) 11),
            new Pokemon("Butterfree", (short) 12),
            new Pokemon("Weedle", (short) 13),
            new Pokemon("Kakuna", (short) 14),
            new Pokemon("Beedrill", (short) 15),
            new Pokemon("Pidgey", (short) 16),
            new Pokemon("Pidgeotto", (short) 17),
            new Pokemon("Pidgeot", (short) 18),
            new Pokemon("Rattata", (short) 19),
            new Pokemon("Raticate", (short) 20),
            new Pokemon("Spearow", (short) 21),
            new Pokemon("Fearow", (short) 22),
            new Pokemon("Ekans", (short) 23),
            new Pokemon("Arbok", (short) 24),
            new Pokemon("Pikachu", (short) 25),
            new Pokemon("Raichu", (short) 26),
            new Pokemon("Sandshrew", (short) 27),
            new Pokemon("Sandslash", (short) 28),
            new Pokemon("Nidoran (female)", (short) 29),
            new Pokemon("Nidorina", (short) 30),
            new Pokemon("Nidoqueen", (short) 31),
            new Pokemon("Nidoran (male)", (short) 32),
            new Pokemon("Nidorino", (short) 33),
            new Pokemon("Nidoking", (short) 34),
            new Pokemon("Clefairy", (short) 35),
            new Pokemon("Clefable", (short) 36),
            new Pokemon("Vulpix", (short) 37),
            new Pokemon("Ninetales", (short) 38),
            new Pokemon("Jigglypuff", (short) 39),
            new Pokemon("Wigglytuff", (short) 40),
            new Pokemon("Zubat", (short) 41),
            new Pokemon("Golbat", (short) 42),
            new Pokemon("Oddish", (short) 43),
            new Pokemon("Gloom", (short) 44),
            new Pokemon("Vileplume", (short) 45),
            new Pokemon("Paras", (short) 46),
            new Pokemon("Parasect", (short) 47),
            new Pokemon("Venonat", (short) 48),
            new Pokemon("Venomoth", (short) 49),
            new Pokemon("Diglett", (short) 50),
            new Pokemon("Dugtrio", (short) 51),
            new Pokemon("Meowth", (short) 52),
            new Pokemon("Persian", (short) 53),
            new Pokemon("Psyduck", (short) 54),
            new Pokemon("Golduck", (short) 55),
            new Pokemon("Mankey", (short) 56),
            new Pokemon("Primeape", (short) 57),
            new Pokemon("Growlithe", (short) 58),
            new Pokemon("Arcanine", (short) 59),
            new Pokemon("Poliwag", (short) 60),
            new Pokemon("Poliwhirl", (short) 61),
            new Pokemon("Poliwrath", (short) 62),
            new Pokemon("Abra", (short) 63),
            new Pokemon("Kadabra", (short) 64),
            new Pokemon("Alakazam", (short) 65),
            new Pokemon("Machop", (short) 66),
            new Pokemon("Machoke", (short) 67),
            new Pokemon("Machamp", (short) 68),
            new Pokemon("Bellsprout", (short) 69),
            new Pokemon("Weepinbell", (short) 70),
            new Pokemon("Victreebel", (short) 71),
            new Pokemon("Tentacool", (short) 72),
            new Pokemon("Tentacruel", (short) 73),
            new Pokemon("Geodude", (short) 74),
            new Pokemon("Graveler", (short) 75),
            new Pokemon("Golem", (short) 76),
            new Pokemon("Ponyta", (short) 77),
            new Pokemon("Rapidash", (short) 78),
            new Pokemon("Slowpoke", (short) 79),
            new Pokemon("Slowbro", (short) 80),
            new Pokemon("Magnemite", (short) 81),
            new Pokemon("Magneton", (short) 82),
            new Pokemon("Farfetch'd", (short) 83),
            new Pokemon("Doduo", (short) 84),
            new Pokemon("Dodrio", (short) 85),
            new Pokemon("Seel", (short) 86),
            new Pokemon("Dewgong", (short) 87),
            new Pokemon("Grimer", (short) 88),
            new Pokemon("Muk", (short) 89),
            new Pokemon("Shellder", (short) 90),
            new Pokemon("Cloyster", (short) 91),
            new Pokemon("Gastly", (short) 92),
            new Pokemon("Haunter", (short) 93),
            new Pokemon("Gengar", (short) 94),
            new Pokemon("Onix", (short) 95),
            new Pokemon("Drowzee", (short) 96),
            new Pokemon("Hypno", (short) 97),
            new Pokemon("Krabby", (short) 98),
            new Pokemon("Kingler", (short) 99),
            new Pokemon("Voltorb", (short) 100),
            new Pokemon("Electrode", (short) 101),
            new Pokemon("Exeggcute", (short) 102),
            new Pokemon("Exeggutor", (short) 103),
            new Pokemon("Cubone", (short) 104),
            new Pokemon("Marowak", (short) 105),
            new Pokemon("Hitmonlee", (short) 106),
            new Pokemon("Hitmonchan", (short) 107),
            new Pokemon("Lickitung", (short) 108),
            new Pokemon("Koffing", (short) 109),
            new Pokemon("Weezing", (short) 110),
            new Pokemon("Rhyhorn", (short) 111),
            new Pokemon("Rhydon", (short) 112),
            new Pokemon("Chansey", (short) 113),
            new Pokemon("Tangela", (short) 114),
            new Pokemon("Kangaskhan", (short) 115),
            new Pokemon("Horsea", (short) 116),
            new Pokemon("Seadra", (short) 117),
            new Pokemon("Goldeen", (short) 118),
            new Pokemon("Seaking", (short) 119),
            new Pokemon("Staryu", (short) 120),
            new Pokemon("Starmie", (short) 121),
            new Pokemon("Mr. Mime", (short) 122),
            new Pokemon("Scyther", (short) 123),
            new Pokemon("Jynx", (short) 124),
            new Pokemon("Electabuzz", (short) 125),
            new Pokemon("Magmar", (short) 126),
            new Pokemon("Pinsir", (short) 127),
            new Pokemon("Tauros", (short) 128),
            new Pokemon("Magikarp", (short) 129),
            new Pokemon("Gyarados", (short) 130),
            new Pokemon("Lapras", (short) 131),
            new Pokemon("Ditto", (short) 132),
            new Pokemon("Eevee", (short) 133),
            new Pokemon("Vaporeon", (short) 134),
            new Pokemon("Jolteon", (short) 135),
            new Pokemon("Flareon", (short) 136),
            new Pokemon("Porygon", (short) 137),
            new Pokemon("Omanyte", (short) 138),
            new Pokemon("Omastar", (short) 139),
            new Pokemon("Kabuto", (short) 140),
            new Pokemon("Kabutops", (short) 141),
            new Pokemon("Aerodactyl", (short) 142),
            new Pokemon("Snorlax", (short) 143),
            new Pokemon("Articuno", (short) 144),
            new Pokemon("Zapdos", (short) 145),
            new Pokemon("Moltres", (short) 146),
            new Pokemon("Dratini", (short) 147),
            new Pokemon("Dragonair", (short) 148),
            new Pokemon("Dragonite", (short) 149),
            new Pokemon("Mewtwo", (short) 150));



    private  List<Pokemon> pokemons = new ArrayList<>();
    private RequestQueue requestQueue;

    PokedexAdapter(Context context){
        requestQueue = Volley.newRequestQueue(context);
        loadPokemon();
    }

    @NonNull
    @Override
    //create the view that we see on the screen
    public PokedexAdapter.PokedexViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pokedex_row,parent,false);
        return new PokedexViewHolder(view);
    }

    @Override
    //sets the different properties of each row, but this is what reads in a click.... keyBinding to the view holder
    public void onBindViewHolder(@NonNull PokedexAdapter.PokedexViewHolder holder, int position) {
        Pokemon current = pokemon.get(position);
        holder.textView.setText(current.getName());
        holder.containerView.setTag(current);
    }

    public void loadPokemon(){
        String url = "https://pokeapi.co/api/v2/pokemon?limit=151";

        /*
        request object(Are wo getting or setting info
            url
            listener
            method
         */

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray result = response.getJSONArray("results");
                    String name = result.getString("name");
                    pokemon.add(new Pokemon(name, result.getString("url")));
                    notifyDataSetChanged();
                }
                catch(JSONException e){
                    Log.e("pokeApi pull", "JSONError",e)
                }
            }, new Response.ErrorListener(){
                @Override
                        public void onErrorResonse(VolleyError error){

                }
            }
        });
    }

    @Override
    //tells the list how long it should be
    public int getItemCount() {
        return pokemon.size();
    }
}

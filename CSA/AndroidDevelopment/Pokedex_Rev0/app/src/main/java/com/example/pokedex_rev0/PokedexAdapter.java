package com.example.pokedex_rev0;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

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
            new Pokemon("MissingNo.", "#000"), new Pokemon("Bulbasaur", "#001"),
            new Pokemon("Ivysaur", "#002"), new Pokemon("Venusaur", "#003"),
            new Pokemon("Charmander", "#004"), new Pokemon("Charmeleon", "#005"),
            new Pokemon("Charizard", "#006"), new Pokemon("Squirtle", "#007"),
            new Pokemon("Wartortle", "#008"), new Pokemon("Blastoise", "#009"),
            new Pokemon("Caterpie", "#010"), new Pokemon("Metapod", "#011"),
            new Pokemon("Butterfree", "#012"), new Pokemon("Weedle", "#013"),
            new Pokemon("Kakuna", "#014"), new Pokemon("Beedrill", "#015"),
            new Pokemon("Pidgey", "#016"), new Pokemon("Pidgeotto", "#017"),
            new Pokemon("Pidgeot", "#018"), new Pokemon("Rattata", "#019"),
            new Pokemon("Raticate", "#020"), new Pokemon("Spearow", "#021"),
            new Pokemon("Fearow", "#022"), new Pokemon("Ekans", "#023"),
            new Pokemon("Arbok", "#024"), new Pokemon("Pikachu", "#025"),
            new Pokemon("Raichu", "#026"), new Pokemon("Sandshrew", "#027"),
            new Pokemon("Sandslash", "#028"), new Pokemon("Nidoran (female)", "#029"),
            new Pokemon("Nidorina", "#030"), new Pokemon("Nidoqueen", "#031"),
            new Pokemon("Nidoran (male)", "#032"), new Pokemon("Nidorino", "#033"),
            new Pokemon("Nidoking", "#034"), new Pokemon("Clefairy", "#035"),
            new Pokemon("Clefable", "#036"), new Pokemon("Vulpix", "#037"),
            new Pokemon("Ninetales", "#038"), new Pokemon("Jigglypuff", "#039"),
            new Pokemon("Wigglytuff", "#040"), new Pokemon("Zubat", "#041"),
            new Pokemon("Golbat", "#042"),  new Pokemon("Oddish", "#043"),
            new Pokemon("Gloom", "#044"), new Pokemon("Vileplume", "#045"),
            new Pokemon("Paras", "#046"), new Pokemon("Parasect", "#047"),
            new Pokemon("Venonat", "#048"), new Pokemon("Venomoth", "#049"),
            new Pokemon("Diglett", "#050"), new Pokemon("Dugtrio", "#051"),
            new Pokemon("Meowth", "#052"), new Pokemon("Persian", "#053"),
            new Pokemon("Psyduck", "#054"), new Pokemon("Golduck", "#055"),
            new Pokemon("Mankey", "#056"), new Pokemon("Primeape", "#057"),
            new Pokemon("Growlithe", "#058"), new Pokemon("Arcanine", "#059"),
            new Pokemon("Poliwag", "#060"), new Pokemon("Poliwhirl", "#061"),
            new Pokemon("Poliwrath", "#062"),new Pokemon("Abra", "#063"),
            new Pokemon("Kadabra", "#064"), new Pokemon("Alakazam", "#065"),
            new Pokemon("Machop", "#066"), new Pokemon("Machoke", "#067"),
            new Pokemon("Machamp", "#068"), new Pokemon("Bellsprout", "#069"),
            new Pokemon("Weepinbell", "#070"), new Pokemon("Victreebel", "#071"),
            new Pokemon("Tentacool", "#072"), new Pokemon("Tentacruel", "#073"),
            new Pokemon("Geodude", "#074"), new Pokemon("Graveler", "#075"),
            new Pokemon("Golem", "#076"), new Pokemon("Ponyta", "#077"),
            new Pokemon("Rapidash", "#078"), new Pokemon("Slowpoke", "#079"),
            new Pokemon("Slowbro", "#080"), new Pokemon("Magnemite", "#081"),
            new Pokemon("Magneton", "#082"), new Pokemon("Farfetch'd", "#083"),
            new Pokemon("Doduo", "#084"), new Pokemon("Dodrio", "#085"),
            new Pokemon("Seel", "#086"), new Pokemon("Dewgong", "#087"),
            new Pokemon("Grimer", "#088"), new Pokemon("Muk", "#089"),
            new Pokemon("Shellder", "#090"), new Pokemon("Cloyster", "#091"),
            new Pokemon("Gastly", "#092"), new Pokemon("Haunter", "#093"),
            new Pokemon("Gengar", "#094"), new Pokemon("Onix", "#095"),
            new Pokemon("Drowzee", "#096"), new Pokemon("Hypno", "#097"),
            new Pokemon("Krabby", "#098"), new Pokemon("Kingler", "#099"),
            new Pokemon("Voltorb", "#100"), new Pokemon("Electrode", "#101"),
            new Pokemon("Exeggcute", "#102"), new Pokemon("Exeggutor", "#103"),
            new Pokemon("Cubone", "#104"), new Pokemon("Marowak", "#105"),
            new Pokemon("Hitmonlee", "#106"), new Pokemon("Hitmonchan", "#107"),
            new Pokemon("Lickitung", "#108"), new Pokemon("Koffing", "#109"),
            new Pokemon("Weezing", "#110"), new Pokemon("Rhyhorn", "#111"),
            new Pokemon("Rhydon", "#112"), new Pokemon("Chansey", "#113"),
            new Pokemon("Tangela", "#114"), new Pokemon("Kangaskhan", "#115"),
            new Pokemon("Horsea", "#116"), new Pokemon("Seadra", "#117"),
            new Pokemon("Goldeen", "#118"), new Pokemon("Seaking", "#119"),
            new Pokemon("Staryu", "#120"), new Pokemon("Starmie", "#121"),
            new Pokemon("Mr. Mime", "#122"), new Pokemon("Scyther", "#123"),
            new Pokemon("Jynx", "#124"), new Pokemon("Electabuzz", "#125"),
            new Pokemon("Magmar", "#126"), new Pokemon("Pinsir", "#127"),
            new Pokemon("Tauros", "#128"), new Pokemon("Magikarp", "#129"),
            new Pokemon("Gyarados", "#130"), new Pokemon("Lapras", "#131"),
            new Pokemon("Ditto", "#132"), new Pokemon("Eevee", "#133"),
            new Pokemon("Vaporeon", "#134"), new Pokemon("Jolteon", "#135"),
            new Pokemon("Flareon", "#136"), new Pokemon("Porygon", "#137"),
            new Pokemon("Omanyte", "#138"), new Pokemon("Omastar", "#139"),
            new Pokemon("Kabuto", "#140"), new Pokemon("Kabutops", "#141"),
            new Pokemon("Aerodactyl", "#142"), new Pokemon("Snorlax", "#143"),
            new Pokemon("Articuno", "#144"), new Pokemon("Zapdos", "#145"),
            new Pokemon("Moltres", "#146"), new Pokemon("Dratini", "#147"),
            new Pokemon("Dragonair", "#148"), new Pokemon("Dragonite", "#149"),
            new Pokemon("Mewtwo", "#150"), new Pokemon("Mew", "#151"));

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

    @Override
    //tells the list how long it should be
    public int getItemCount() {
        return pokemon.size();
    }
}

package com.example.triptracker2021;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Filter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class TripListActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private RecyclerView recyclerView;
    private TripAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_list);
        setTitle("Trip List");
        recyclerView = findViewById(R.id.recycler_view);
        adapter = new TripAdapter(getApplicationContext());
        layoutManager = new LinearLayoutManager(this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        /*AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int  position = info.position;
        TripAdapter adaoter = (TripAdapter) getListAdapter();*/
        return super.onContextItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        //loads in the menu file menu_trip_list_item_context

        getMenuInflater().inflate(R.menu.menu_trip_list_item_context,menu);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        refreshThePage();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        System.out.println(LoginActivity.currentUser.getDisplayName());
        getMenuInflater().inflate(R.menu.menu_trips,menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String newText) {
        adapter.getFilter().filter((newText));
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        adapter.getFilter().filter(newText);
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_refresh:
                Log.d("TripListActivity","refresh button");
                refreshThePage();
                return true;
            case R.id.action_new:
                Log.d("TripListActivity","new button");
                startActivity(new Intent(this, TripActivity.class));
                return true;
            case R.id.action_logout:
                Log.d("TripListActivity","logout button");
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(this,LoginActivity.class));
                //TODO, stop user from hitting back button
                return true;
            case R.id.action_settings:
                Log.d("TripListActivity","settings button");
                return true;
            case R.id.action_public_trips:
                Log.d("TripListActivity","public trips button");
                return true;
            case R.id.action_my_trips:
                Log.d("TripListActivity","private trips button");
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void refreshThePage() {
        recyclerView = findViewById(R.id.recycler_view);
        adapter = new TripAdapter(getApplicationContext());
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
    }
}

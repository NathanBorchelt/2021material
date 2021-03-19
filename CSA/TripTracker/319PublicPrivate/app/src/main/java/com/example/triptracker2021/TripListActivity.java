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
    public static boolean mPublicView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_list);
        setTitle("Trip List");
        mPublicView = getIntent().getBooleanExtra(Trip.EXTRA_TRIP_PUBLIC_VIEW,true);
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
    public boolean onPrepareOptionsMenu(Menu menu) {
        //toggle between public a nd my(private) trips in the action bar
        
        if(mPublicView){
            // myTrip option is visible
            menu.findItem(R.id.action_public_trips).setVisible(false);
            menu.findItem(R.id.action_my_trips).setVisible(true);
        }
        else {
            //public trip is visible
            menu.findItem(R.id.action_public_trips).setVisible(true);
            menu.findItem(R.id.action_my_trips).setVisible(false);
        }
        return true; //always runs
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //System.out.println(LoginActivity.currentUser.getUid());
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
    protected void onResume() {
        super.onResume();
        refreshThePage();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent i;
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
                i = new Intent(getApplicationContext(), TripListActivity.class);
                i.putExtra(Trip.EXTRA_TRIP_PUBLIC_VIEW,true);
                startActivity(i);
                return true;
            case R.id.action_my_trips:
                Log.d("TripListActivity","private trips button");
                i = new Intent(getApplicationContext(), TripListActivity.class);
                i.putExtra(Trip.EXTRA_TRIP_PUBLIC_VIEW,false);
                startActivity(i);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void refreshThePage() {
        recyclerView = findViewById(R.id.recycler_view);
        adapter = new TripAdapter(getApplicationContext());
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
    }
}

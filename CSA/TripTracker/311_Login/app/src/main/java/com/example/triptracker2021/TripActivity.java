package com.example.triptracker2021;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;
import java.util.Random;

public class TripActivity extends AppCompatActivity {

    private EditText mNameField;
    private EditText mDescriptionField;
    private CheckBox mPublicCheckBox;
    private TextView mIDText;
    private DatabaseReference rootRef;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip);
        setTitle("Trip Activity");

        mNameField = (EditText) findViewById(R.id.enter_trip_name);
        mDescriptionField = (EditText) findViewById(R.id.enter_trip_description);
        mPublicCheckBox = (CheckBox) findViewById(R.id.trip_public);
        mIDText = (TextView) findViewById(R.id.testTXT);
    }

    public void updateTrip(MenuItem menuItem){
        loadingScreen(menuItem);
        String name = mNameField.getText().toString().trim();
        String desc = mDescriptionField.getText().toString().trim();
        boolean shared = mPublicCheckBox.isChecked();

        if(!name.isEmpty()) {
            final Trip trip = new Trip(String.valueOf(new Random().nextLong()), name, desc, new Date(), new Date(), shared);
            Toast.makeText(this, trip.toString(), Toast.LENGTH_SHORT).show();
            //create thread
            Thread th = new Thread(new Runnable() {
                @Override
                public void run() {
                    Log.i("UpdateTripThread)","Thread built");
                    rootRef = FirebaseDatabase.getInstance().getReference("Trip");
                    rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            Log.d("UpdateTripThread","start new data push");
                            rootRef.push().setValue(trip);


                            //getting the ID from the DB

                            rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    String key = "";
                                    for(DataSnapshot ds: snapshot.getChildren()){
                                        key = ds.getKey();
                                        Log.d("updateTripThread",key);
                                    }
                                    trip.setObjectId(key);
                                    String path = "Trip/".concat(trip.getObjectId());
                                    rootRef = FirebaseDatabase.getInstance().getReference(path);
                                    rootRef.setValue(trip);
                                    mIDText.setText(trip.getObjectId());
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {
                                    Log.d("updateTripThread", "Error updating/creating something new in the DB");
                                }
                            });
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Log.d("updateTripThread", "Error updating/creating something new in the DB");
                        }
                    });
                }
            });
            //run thread
            th.start();
            //joins back to main
            try{
                th.join();
                Log.d("UpdateTripThread","attemp to join threads");
            }
            catch(InterruptedException e) {Log.e("Update TripThread", String.valueOf(e));}
            startActivity(new Intent(this,TripListActivity.class));
        }
        else {
            Toast.makeText(this, "You need a name for the trip", Toast.LENGTH_SHORT);
            mNameField.requestFocus();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_trip_details,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.home:
                Log.d("TripActivity","home button");
                return true;
            case R.id.action_post:
                Log.d("TripActivity","post button");
                updateTrip(item);
                return true;
            case R.id.action_logout:
                Log.d("TripActivity","logout button");
                return true;
            case R.id.action_settings:
                Log.d("TripActivity","settings button");
                return true;
            case R.id.action_delete:
                Log.d("TripActivity","public trips button");
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadingScreen(MenuItem item){
        item.setActionView(new ProgressBar(getApplicationContext())); //make the icon spin
        ProgressDialog progress = new ProgressDialog(this);
        progress.setMessage("Saving to FireBase");
        progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progress.setIndeterminate(true);
        progress.show();
    }
}

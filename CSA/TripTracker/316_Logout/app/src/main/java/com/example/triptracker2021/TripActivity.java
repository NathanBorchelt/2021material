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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;
import java.util.Random;

public class TripActivity extends AppCompatActivity {

    private EditText mNameField;
    private EditText mDescriptionField;
    private CheckBox mPublicCheckBox;
    private TextView mIDText;
    private DatabaseReference rootRef;
    private Trip mTrip;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip);
        setTitle("Trip Activity");

        mNameField = (EditText) findViewById(R.id.enter_trip_name);
        mDescriptionField = (EditText) findViewById(R.id.enter_trip_description);
        mPublicCheckBox = (CheckBox) findViewById(R.id.trip_public);
        mIDText = (TextView) findViewById(R.id.testTXT);
        //mTrip will be the object that is used with the entire activity

        String tripId = (String) getIntent().getSerializableExtra(Trip.EXTRA_TRIP_ID);
        String tripName = (String) getIntent().getSerializableExtra(Trip.EXTRA_TRIP_NAME);
        String descript = (String) getIntent().getSerializableExtra(Trip.EXTRA_TRIP_DESC);
        boolean shared = getIntent().getBooleanExtra(Trip.EXTRA_TRIP_PUBLIC,false);
        mTrip = new Trip(tripId,tripName,descript,new Date(), new Date(), shared);
        mTrip.setObjectId(tripId);
        mTrip.setName(tripName);
        mTrip.setDescription(descript);
        mTrip.setShared(shared);
        mNameField.setText(tripName);
        mIDText.setText(tripId);
        mDescriptionField.setText(descript);
        mPublicCheckBox.setChecked(shared);
    }

    public void updateTrip(MenuItem menuItem){
        loadingScreen(menuItem);
        String name = mNameField.getText().toString().trim();
        String desc = mDescriptionField.getText().toString().trim();
        boolean shared = mPublicCheckBox.isChecked();
        String id = mIDText.getText().toString();

        if(!name.isEmpty()) {
           // final Trip trip = new Trip("", name, desc, new Date(), new Date(), shared);

            mTrip.setName(name);
            mTrip.setDescription(desc);
            mTrip.setShared(shared);
            mTrip.setObjectId(id);

            Toast.makeText(this,mTrip.toString(), Toast.LENGTH_SHORT).show();
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

                            boolean alreadyThere = false;
                            String key = "";
                            //is the object already in the db
                            for(DataSnapshot ds : snapshot.getChildren()){
                                key = ds.getKey();
                                //Log.d("checking ds",ds.toString());
                                Log.d("Checking Key",key);
                                if(key.equals(mTrip.getObjectId())){
                                    alreadyThere = true;
                                    //mTrip = (Trip)ds;
                                    break;
                                }
                            }

                            if(alreadyThere){
                                //Log.d("updateTripActivity",mTrip.getObjectId() + " already there");
                                rootRef = FirebaseDatabase.getInstance().getReference("Trip/"+key);
                                //Log.d("checking trip string",mTrip.toString());
                                rootRef.setValue(mTrip);


                            }else{
                                Log.d("updateThipThread","New one going up");
                                rootRef.push().setValue(mTrip);
                                rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        String key = "";
                                        for(DataSnapshot ds: snapshot.getChildren()){
                                            key = ds.getKey();
                                            Log.d("updateTripThread",key);
                                        }
                                        mTrip.setObjectId(key);
                                        String path = "Trip/".concat(mTrip.getObjectId());
                                        rootRef = FirebaseDatabase.getInstance().getReference(path);
                                        rootRef.setValue(mTrip);
                                        mIDText.setText(mTrip.getObjectId());
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {
                                        Log.d("updateTripThread", "Error updating/creating something new in the DB");
                                    }
                                });
                            }
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
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(this,LoginActivity.class));
                //TODO, stop user from hitting back button
                return true;
            case R.id.action_settings:
                Log.d("TripActivity","settings button");
                return true;
            case R.id.action_delete:
                Log.d("TripActivity","del button");
                deleteTrip(item);
                startActivity(new Intent(this, com.example.triptracker2021.TripListActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void deleteTrip(MenuItem item) {
        Log.d("TripActivityDelete","Starting to delete trip");
        rootRef = FirebaseDatabase.getInstance().getReference("Trip");
        loadingScreen(item);
        //create thread to delete form firebase
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Query q = rootRef.child(mTrip.getObjectId());
                q.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        snapshot.getRef().removeValue();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        mIDText.setText("Del Error\t"+error);
                    }
                });
            }
        });

        thread.start();
        //start thread
        try {
            thread.join();
            Log.d("TripActivityDel", "Deleted" + mTrip.getObjectId());
        }catch (Exception e){
            Toast.makeText(this,"Error Removing"+e,Toast.LENGTH_SHORT).show();
        }
        //merge thread back into our application
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

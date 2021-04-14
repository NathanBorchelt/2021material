package com.example.triptracker2021;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TripAdapter extends RecyclerView.Adapter<TripAdapter.TripViewHolder> implements Filterable {

    private List<Trip> listOfTrips = new ArrayList<>();
    private DatabaseReference rootRef;
    private boolean mPublicTripView = TripListActivity.mPublicView;

    public TripAdapter(Context context) {
        Log.d("tripAdapter","new Trip adapter created");
        loadTrips();
    }
    private void loadTrips() {
        Log.d("TripAdapter","LoadingTrips");
        rootRef = FirebaseDatabase.getInstance().getReference().child("Trip");
        rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listOfTrips.clear();
                for(DataSnapshot ds : snapshot.getChildren()) {
                    //Log.d("testinginfo",ds.toString());
                    String name = ds.child("name").getValue(String.class);
                    String desc = ds.child("description").getValue(String.class);
                    String objectId = ds.child("objectId").getValue(String.class);
                    boolean shared = ds.child("shared").getValue(Boolean.class);
                    Date startDate = ds.child("startDate").getValue(Date.class);
                    Date endDate = ds.child("startDate").getValue(Date.class);
                    String creator = ds.child("creator").getValue(String.class);

                    listOfTrips.add(new Trip(objectId,name,desc,startDate,endDate,shared,creator));
                }

                if(mPublicTripView){
                    for(short i = 0; i < listOfTrips.size(); ++i){
                        if(!listOfTrips.get(i).isShared()){
                            listOfTrips.remove(i);
                        }
                    }
                }
                else{
                    for(short i = 0; i < listOfTrips.size(); ++i){
                        if(!LoginActivity.currentUser.getUid().equals(listOfTrips.get(i).getCreator())) {
                            listOfTrips.remove(i);
                        }
                        if(listOfTrips.get(i).isShared()) {
                            listOfTrips.remove(i);
                        }

                    }
                }

                notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("TripAdapter","Error loading tips");
            }
        });
    }

    @Override
    public Filter getFilter() {
        return new TripFilter();
    }

    private class TripFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Trip> filteredTrip = new ArrayList<>();
            for(Trip trip : listOfTrips){
                if ((trip.getName().toLowerCase()).contains(constraint.toString().toLowerCase())) filteredTrip.add(trip);
            }
            if(constraint.equals("")){
                filteredTrip = listOfTrips;
            }
            FilterResults results = new FilterResults();
            results.values = filteredTrip; // you need to create this variable!
            results.count = filteredTrip.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            listOfTrips = (List<Trip>) results.values;
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public TripViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trip_row,parent,false);
        return new TripViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TripViewHolder holder, int position) {
        //sort here because onBindViewHolder will run each time that the adapter is reloaded
        ArrayListSorter.selectionSort(listOfTrips,"DSC");
        Trip current = listOfTrips.get(position);
        holder.textView.setText(current.getName());
        holder.containerView.setTag(current);

    }

    @Override
    public int getItemCount() {
        return listOfTrips.size();
    }

    public class TripViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout containerView;
        public TextView textView;
        public TripViewHolder(@NonNull final View itemView) {
            super(itemView);
            //containter view is each row we see
            containerView = itemView.findViewById(R.id.trip_row);
            //text view is the info in each row
            textView = itemView.findViewById(R.id.trip_row_txt);
            //make the rows do something  when clicked
            containerView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Trip current = (Trip) containerView.getTag();
                    if (LoginActivity.currentUser.getUid().equals(current.getCreator())) {
                        Intent intent = new Intent(view.getContext(), TripActivity.class);
                        intent.putExtra(Trip.EXTRA_TRIP_NAME, current.getName());
                        intent.putExtra(Trip.EXTRA_TRIP_ID, current.getObjectId());
                        intent.putExtra(Trip.EXTRA_TRIP_DESC, current.getDescription());
                        intent.putExtra(Trip.EXTRA_TRIP_PUBLIC, current.isShared());
                        intent.putExtra(Trip.EXTRA_TRIP_CREATOR,current.getCreator());
                        Log.d("Current User", LoginActivity.currentUser.getUid());
                        view.getContext().startActivity(intent);
                    }
                }

            });
            //loading up a menu to give del optio
            containerView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(final View view) {
                    final Trip current = (Trip) containerView.getTag();
                    PopupMenu pop = new PopupMenu(containerView.getContext(),itemView);
                    pop.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem menuItem) {
                            switch (menuItem.getItemId()){
                                case(R.id.menu_item_delete_trip):
                                    if(((Trip)containerView.getTag()).getCreator().equals(LoginActivity.currentUser.getUid())) {
                                        Log.d("popups", "delete item clicked");
                                        deleteTrip(menuItem,current);
                                        loadingScreen(view);
                                        Intent i = new Intent(view.getContext(),TripListActivity.class);
                                        view.getContext().startActivity(i);
                                        return true;
                                    }
                            }
                            return false;
                        }
                    });
                    pop.inflate(R.menu.menu_trip_list_item_context);
                    pop.show();
                    return false;
                }
            });
        }
    }

    public void loadingScreen(View view){
        //item.setActionView(new ProgressBar(getApplicationContext())); //make the icon spin
        ProgressDialog progress = new ProgressDialog(view.getContext());
        progress.setMessage("Saving to FireBase");
        progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progress.setIndeterminate(true);
        progress.show();
    }

    public void deleteTrip(MenuItem item, final Trip mTrip) {
        Log.d("TripDeletePopup","Starting to delete trip");


        rootRef = FirebaseDatabase.getInstance().getReference("Trip");
        //create thread to delete form firebase
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Query q = rootRef.child(mTrip.getObjectId());
                q.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(mTrip.getCreator().equals(LoginActivity.currentUser.getUid())) {
                            Log.d("delconfirmuser","deleting if you are creator");
                            snapshot.getRef().removeValue();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.e("dellongerror",error.toString());
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
            //Toast.makeText(this,"Error Removing"+e,Toast.LENGTH_SHORT).show();
        }
        //merge thread back into our application

    }


}

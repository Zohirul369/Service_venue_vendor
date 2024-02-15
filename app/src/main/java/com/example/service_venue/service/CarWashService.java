package com.example.service_venue.service;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import com.example.service_venue.R;
import com.example.service_venue.ViewModel;
import com.example.service_venue.adapter.CarRentAdapter;
import com.example.service_venue.service.add.AddCarRent;
import com.example.service_venue.service.add.AddCarWash;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class CarWashService extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CarRentAdapter carRentAdapter;

    SearchView searchView;

    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_wash_service);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<ViewModel> options =
                new FirebaseRecyclerOptions.Builder<ViewModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("serviceVenue").child("service").child("carWash"), ViewModel.class)
                        .build();

        carRentAdapter = new CarRentAdapter(options);
        recyclerView.setAdapter(carRentAdapter);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), AddCarWash.class));
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        carRentAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        carRentAdapter.stopListening();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.search,menu);
        MenuItem item = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                txtSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                txtSearch(query);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
    private void txtSearch(String str){
        FirebaseRecyclerOptions<ViewModel> options =
                new FirebaseRecyclerOptions.Builder<ViewModel>()
                        .setQuery(FirebaseDatabase.getInstance()
                                .getReference().child("serviceVenue").child("service").child("carWash")
                                .orderByChild("vName")
                                .startAt(str).endAt(str+"~"),ViewModel.class)
                        .build();
        carRentAdapter = new CarRentAdapter(options);
        carRentAdapter.startListening();
        recyclerView.setAdapter(carRentAdapter);
    }
}
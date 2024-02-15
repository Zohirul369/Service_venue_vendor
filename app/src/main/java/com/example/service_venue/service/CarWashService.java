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
import com.example.service_venue.adapter.CarWashAdapter;
import com.example.service_venue.service.add.AddCarRent;
import com.example.service_venue.service.add.AddCarWash;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class CarWashService extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CarWashAdapter carWashAdapter;

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

        carWashAdapter = new CarWashAdapter(options);
        recyclerView.setAdapter(carWashAdapter);
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
        carWashAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        carWashAdapter.stopListening();
    }

}
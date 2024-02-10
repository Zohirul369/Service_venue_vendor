package com.example.service_venue.service;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.service_venue.R;
import com.example.service_venue.ViewModel;
import com.example.service_venue.adapter.CarRentAdapter;
import com.example.service_venue.service.add.AddCarRent;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class CarRentService extends AppCompatActivity {

    RecyclerView recyclerView;
    CarRentAdapter carRentAdapter;
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_rent_service);


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<ViewModel> options =
                new FirebaseRecyclerOptions.Builder<ViewModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("service").child("carRent"), ViewModel.class)
                        .build();

        carRentAdapter = new CarRentAdapter(options);
        recyclerView.setAdapter(carRentAdapter);

        floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), AddCarRent.class));
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
}
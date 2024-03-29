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
import com.example.service_venue.adapter.PlumberAdapter;
import com.example.service_venue.service.add.AddCarRent;
import com.example.service_venue.service.add.AddPlumber;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class PlumbingService extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PlumberAdapter plumberAdapter;

    SearchView searchView;

    FloatingActionButton floatingActionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plumbing_service);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<ViewModel> options =
                new FirebaseRecyclerOptions.Builder<ViewModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("serviceVenue").child("service").child("plumber"), ViewModel.class)
                        .build();

        plumberAdapter = new PlumberAdapter(options);
        recyclerView.setAdapter(plumberAdapter);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), AddPlumber.class));
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        plumberAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        plumberAdapter.stopListening();
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
                                .getReference().child("serviceVenue").child("service").child("carRent")
                                .orderByChild("vName")
                                .startAt(str).endAt(str+"~"),ViewModel.class)
                        .build();
        plumberAdapter = new PlumberAdapter(options);
        plumberAdapter.startListening();
        recyclerView.setAdapter(plumberAdapter);
    }
}
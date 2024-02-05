package com.example.service_venue.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.service_venue.R;
import com.example.service_venue.ViewModel;
import com.example.service_venue.adapter.UpCommingAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;


public class UpcomingFragment extends Fragment {


    RecyclerView recyclerView;
    UpCommingAdapter upCommingAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_upcoming);


        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<ViewModel> options =
                new FirebaseRecyclerOptions.Builder<ViewModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("request"),ViewModel.class)
                        .build();

        upCommingAdapter = new upCommingAdapter(options);
        recyclerView.setAdapter(upCommingAdapter);


    }

    private void setContentView(int fragmentUpcoming) {

    }

    @Override
    public void onStart() {
        super.onStart();
        upCommingAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        upCommingAdapter.stopListening();
    }


    private void txtSearch(String str){
        FirebaseRecyclerOptions<ViewModel> options =
                new FirebaseRecyclerOptions.Builder<ViewModel>()
                        .setQuery(FirebaseDatabase.getInstance()
                                .getReference().child("request")
                                .orderByChild("cname")
                                .startAt(str).endAt(str+"~"), ViewModel.class)
                        .build();
        upCommingAdapter = new upCommingAdapter(options);
        upCommingAdapter.startListening();
        recyclerView.setAdapter(upCommingAdapter);
    }
}
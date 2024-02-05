package com.example.service_venue.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.service_venue.R;
import com.example.service_venue.ViewModel;
import com.example.service_venue.adapter.UpCommingAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class UpcomingFragment extends Fragment {

    RecyclerView recyclerView;
    UpCommingAdapter upComingAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_upcoming, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        FirebaseRecyclerOptions<ViewModel> options =
                new FirebaseRecyclerOptions.Builder<ViewModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("request"), ViewModel.class)
                        .build();

        upComingAdapter = new UpCommingAdapter(options);
        recyclerView.setAdapter(upComingAdapter);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        upComingAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        upComingAdapter.stopListening();
    }

    private void txtSearch(String str) {
        FirebaseRecyclerOptions<ViewModel> options =
                new FirebaseRecyclerOptions.Builder<ViewModel>()
                        .setQuery(FirebaseDatabase.getInstance()
                                .getReference().child("request")
                                .orderByChild("cname")
                                .startAt(str).endAt(str + "~"), ViewModel.class)
                        .build();
        upComingAdapter = new UpCommingAdapter(options);
        upComingAdapter.startListening();
        recyclerView.setAdapter(upComingAdapter);
    }
}

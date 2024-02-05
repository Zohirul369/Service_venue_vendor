package com.example.service_venue;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.service_venue.fragment.CompletedFragment;
import com.example.service_venue.fragment.UpcomingFragment;

public class RequestActivity extends AppCompatActivity {
    Button upcoming, complete;
    LinearLayout fragment_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        upcoming = findViewById(R.id.upcoming);
        complete =findViewById(R.id.completed);
        fragment_view =findViewById(R.id.fragmentView_layout);

        complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CompletedFragment completeFragment = new CompletedFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragmentView_layout,completeFragment);
                transaction.commit();
            }
        });
        upcoming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpcomingFragment upComingFragment = new UpcomingFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragmentView_layout,upComingFragment);
                transaction.commit();
            }
        });
    }
}
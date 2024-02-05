package com.example.service_venue;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.service_venue.fragment.CompletedFragment;
import com.example.service_venue.fragment.UpcomingFragment;
import com.google.android.material.tabs.TabLayout;

public class RequestActivity extends AppCompatActivity {
    Button upcoming, complete;
    FrameLayout fragment_view;
    TabLayout tab_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        upcoming = findViewById(R.id.upcoming);
        complete = findViewById(R.id.completed);
        fragment_view = (FrameLayout) findViewById(R.id.frame_layout);
        tab_layout = (TabLayout) findViewById(R.id.tab_layout);

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new UpcomingFragment())
                .addToBackStack(null)
                .commit();

        tab_layout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {


                Fragment fragment = null;
                switch (tab.getPosition()) {
                    case 0:
                        fragment = new UpcomingFragment();
                        break;
                    case 1:
                        fragment = new CompletedFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, fragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
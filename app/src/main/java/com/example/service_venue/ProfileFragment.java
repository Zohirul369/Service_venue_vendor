package com.example.service_venue;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;




public class ProfileFragment extends Fragment {

    TextView vendor_view_profile, contact_layout, privacy;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        
        privacy = view.findViewById(R.id.privacy);
        vendor_view_profile = view.findViewById(R.id.vendor_view_profile);
        contact_layout= view.findViewById(R.id.contact_layout);

        privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ProfilePrivacy.class);
                startActivity(intent);
            }
        });
        contact_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Profile_Contact.class);
                startActivity(intent);
            }
        });

        vendor_view_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), profile_view.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
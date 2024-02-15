package com.example.service_venue;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.service_venue.service.BeautyParlourService;
import com.example.service_venue.service.CarRentService;
import com.example.service_venue.service.CarWashService;
import com.example.service_venue.service.ElectricianService;
import com.example.service_venue.service.HouseCleaning;
import com.example.service_venue.service.LaundryService;
import com.example.service_venue.service.PaintingService;
import com.example.service_venue.service.PlumbingService;

import java.util.ArrayList;


public class Home_Fragment extends Fragment {




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_home, container, false);
        View view = inflater.inflate(R.layout.fragment_home, container,false);

        //slider-----------------------------------------------------

        ArrayList<SlideModel> imageList = new ArrayList<>(); // Create image list

// imageList.add(new SlideModel("String Url" or R.drawable))
// imageList.add(new SlideModel("String Url" or R.drawable, "title")) You can add title

        imageList.add(new SlideModel("https://plus.unsplash.com/premium_photo-1661884973994-d7625e52631a?q=80&w=2071&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D", ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel("https://images.unsplash.com/photo-1595814433015-e6f5ce69614e?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel("https://plus.unsplash.com/premium_photo-1661884973994-d7625e52631a?q=80&w=2071&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",ScaleTypes.CENTER_CROP));

        ImageSlider imageSlider = view.findViewById(R.id.smartSlider);
        imageSlider.setImageList(imageList);

        //-------------------------------------------------------------


        // Service section start
        ImageButton car_rental_img_btn = view.findViewById(R.id.car_rental_img_btn);
        car_rental_img_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CarRentService.class);
                startActivity(intent);
            }
        });

        ImageButton electrician_img_btn = view.findViewById(R.id.electrician_img_btn);
        electrician_img_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ElectricianService.class);
                startActivity(intent);
            }
        });

        ImageButton laundry_img_btn = view.findViewById(R.id.laundry_img_btn);
        laundry_img_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LaundryService.class);
                startActivity(intent);
            }
        });

        ImageButton beauty_parler_img_btn = view.findViewById(R.id.beauty_parler_img_btn);
        beauty_parler_img_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BeautyParlourService.class);
                startActivity(intent);
            }
        });

        ImageButton house_clean_img_btn = view.findViewById(R.id.house_clean_img_btn);
        house_clean_img_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HouseCleaning.class);
                startActivity(intent);
            }
        });

        ImageButton car_wash_img_btn = view.findViewById(R.id.car_wash_img_btn);
        car_wash_img_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CarWashService.class);
                startActivity(intent);
            }
        });

        ImageButton painting_img_btn = view.findViewById(R.id.painting_img_btn);
        painting_img_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PaintingService.class);
                startActivity(intent);
            }
        });

        ImageButton plumbing_img_btn = view.findViewById(R.id.plumbing_img_btn);
        plumbing_img_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PlumbingService.class);
                startActivity(intent);
            }
        });

        return view;

    }

}
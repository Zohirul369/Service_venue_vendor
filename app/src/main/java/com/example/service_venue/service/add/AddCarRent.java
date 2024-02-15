package com.example.service_venue.service.add;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.service_venue.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AddCarRent extends AppCompatActivity {

    TextView vName, vDistance, vRate, vImage, serviceName,vPhone;
    Button btn_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car_rent);

        vName = (EditText)findViewById(R.id.vname);
        vPhone = (EditText)findViewById(R.id.v_phone);
        vDistance = (EditText)findViewById(R.id.distance);
        vRate = (EditText)findViewById(R.id.rate);
        vImage = (EditText)findViewById(R.id.vimage);
        //serviceName.setText("Car Rent");

        btn_add = (Button)findViewById(R.id.btn_add);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                insertData();
            }
        });
    }

    private void insertData(){
        Map<String,Object> map = new HashMap<>();
        map.put("vName",vName.getText().toString());
        map.put("vPhone",vPhone.getText().toString());
        //map.put("serviceName",serviceName.getText().toString());

        map.put("vRate",vRate.getText().toString());
        map.put("vDistance",vDistance.getText().toString());
        map.put("vImage",vImage.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("serviceVenue").child("service").child("carRent").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(AddCarRent.this, "data Inserted Successfully", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddCarRent.this, "Error while Insertion ", Toast.LENGTH_SHORT).show();

                    }
                });
    }
}
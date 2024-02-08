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

    TextView vname, rating, distance, rate, vimage;
    Button btn_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car_rent);

        vname = (EditText)findViewById(R.id.vname);
        rating = (EditText)findViewById(R.id.rating);
        distance = (EditText)findViewById(R.id.distance);
        rate = (EditText)findViewById(R.id.rate);
        vimage = (EditText)findViewById(R.id.vimage);

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
        map.put("vname",vname.getText().toString());
        map.put("rating",rating.getText().toString());
        map.put("rate",rate.getText().toString());
        map.put("distance",distance.getText().toString());
        map.put("vimage",vimage.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("carRentService").push()
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
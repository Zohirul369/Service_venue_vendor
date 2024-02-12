package com.example.service_venue;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class Vendor_Signup extends AppCompatActivity {

    private EditText signup_mobileno;
    private Button vendor_signup_continueBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_signup);

        signup_mobileno = findViewById(R.id.signup_mobileno);
        vendor_signup_continueBtn = findViewById(R.id.vendor_signup_continueBtn);
        
    }
}
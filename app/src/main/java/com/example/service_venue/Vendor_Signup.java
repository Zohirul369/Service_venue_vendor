package com.example.service_venue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.hbb20.CountryCodePicker;

public class Vendor_Signup extends AppCompatActivity {

    CountryCodePicker countryCodePicker;
    EditText signup_mobileno;
    Button user_signup_send_otpBtn;
    ProgressBar progressBar;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_signup);

        countryCodePicker=findViewById(R.id.vendor_signup_countrycode);
        signup_mobileno=findViewById(R.id.vendor_signup_mobileno);
        user_signup_send_otpBtn=findViewById(R.id.vendor_signup_send_otpBtn);
        progressBar=findViewById(R.id.vendor_signup_progressbar);

        progressBar.setVisibility(View.GONE);

        countryCodePicker.registerCarrierNumberEditText(signup_mobileno);

        user_signup_send_otpBtn.setOnClickListener((v)->{
            if(!countryCodePicker.isValidFullNumber()){
                signup_mobileno.setError("Phone number not valid");
                return;
            }
            Intent intent = new Intent(Vendor_Signup.this,VendorSignupOTP.class);
            intent.putExtra("phone",countryCodePicker.getFullNumberWithPlus());
            startActivity(intent);
        });

    }
}
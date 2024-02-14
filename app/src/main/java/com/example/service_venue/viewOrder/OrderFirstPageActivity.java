package com.example.service_venue.viewOrder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.service_venue.R;

public class OrderFirstPageActivity extends AppCompatActivity {

    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_first_page);

        button = findViewById(R.id.btn_accept);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderFirstPageActivity.this, OrderSecondPageActivity.class);
                startActivity(intent);
            }
        });
    }
}
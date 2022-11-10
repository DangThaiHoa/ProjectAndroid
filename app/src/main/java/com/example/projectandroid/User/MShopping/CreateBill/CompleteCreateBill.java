package com.example.projectandroid.User.MShopping.CreateBill;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.projectandroid.R;
import com.example.projectandroid.User.DashBoard;

public class CompleteCreateBill extends AppCompatActivity {

    Button backtoDashboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_complete_create_bill);

        backtoDashboard = findViewById(R.id.back_to_Dashboard);

        backtoDashboard();
    }

    private void backtoDashboard() {

        backtoDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DashBoard.class);
                startActivity(intent);
            }
        });
    }
}
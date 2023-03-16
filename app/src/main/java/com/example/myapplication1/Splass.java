package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Splass extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splass);

        Intent iHome = new Intent(Splass.this, Login.class);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity(iHome);
            }
        },3000);
    }
}
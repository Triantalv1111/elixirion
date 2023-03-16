package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

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
    @Override
    protected void onDestroy() {
        super.onDestroy();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            mAuth.signOut();
        }
    }
    protected void onPause() {
        super.onPause();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            mAuth.signOut();
        }
    }
    @Override
    protected void onStop() {
        super.onStop();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            mAuth.signOut();
        }
    }
}
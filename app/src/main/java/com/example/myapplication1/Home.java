package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;

import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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
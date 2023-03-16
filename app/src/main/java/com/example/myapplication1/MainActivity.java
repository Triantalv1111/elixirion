package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainerView, new SingUpFragment1())
                .commit();
 }
    public void navigateToNextFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainerView, new SingUpFragment2());
        transaction.addToBackStack(null);
        transaction.commit();
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
    public void navigateToNextFragment1() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainerView, new SingUpFragment3());
        transaction.addToBackStack(null);
        transaction.commit();
    }
    public void updateUI(FirebaseUser currentUser) {
        Intent profileIntent = new Intent(getApplicationContext(), Home.class);
        profileIntent.putExtra("email", currentUser.getEmail());
        Log.v("DATA", currentUser.getUid());
        startActivity(profileIntent);
    }
}
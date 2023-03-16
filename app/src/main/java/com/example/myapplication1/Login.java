package com.example.myapplication1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText email, password;
    private Button btnLogin,btnregister;
    private TextView textRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.inputemail);
        password = findViewById(R.id.inputpass);
        btnLogin = findViewById(R.id.login_btn);
        btnregister = findViewById(R.id.signup_btn); // initialize btnregister

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        btnregister.setOnClickListener(new View.OnClickListener() { // set onClickListener for btnregister
            @Override
            public void onClick(View v) {
                register();
            }
        });

    }

    private void register() {
        startActivity(new Intent(Login.this , MainActivity.class));
    }

    private void login() {
        String user = email.getText().toString().trim();
        String pass = password.getText().toString().trim();
        if(user.isEmpty())
        {            email.setError("Email can not be empty");        }
        if(pass.isEmpty())
        {            password.setError("Password can not be empty");        }
        else
        {
            mAuth.signInWithEmailAndPassword(user,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task)
                {
                    if(task.isSuccessful())
                    {
                        Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Login.this , Home.class));
                    }
                    else
                    {
                        Toast.makeText(Login.this, "Login Failed"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}


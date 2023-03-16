package com.example.myapplication1;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;


public class SingUpFragment1 extends Fragment {

    private Button button;
    private EditText email, pass, confirmpass,phoneNo;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog pd;

    @SuppressLint("MissingInflatedId")
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sing_up1, container, false);
        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() != null) {
            ((MainActivity) getActivity()).navigateToNextFragment();
        }
        phoneNo = view.findViewById(R.id.phoneNo);
        button = view.findViewById(R.id.button3);
        email = view.findViewById(R.id.regEmail);
        pass = view.findViewById(R.id.regCountry);
        confirmpass = view.findViewById(R.id.regCity);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
        pd = new ProgressDialog(this.getContext());

        return view;
    }

    private void registerUser() {
        String email_tv = email.getText().toString().trim();
        String password = pass.getText().toString().trim();
        String confermpassword = confirmpass.getText().toString().trim();
        String phoneNo1 = phoneNo.getText().toString().trim();

        if (TextUtils.isEmpty(email_tv)) {
            Toast.makeText(this.getContext(), "Enter Email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this.getContext(), "Enter password", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(phoneNo1)) {
            Toast.makeText(this.getContext(), "Enter phone number", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(confermpassword)) {
            Toast.makeText(this.getContext(), "Enter Confirm password", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!password.equals(confermpassword)) {
            Toast.makeText(this.getContext(), "Password Not Match", Toast.LENGTH_SHORT).show();
            return;
        }

        pd.setMessage("wait");
        pd.show();
        firebaseAuth.createUserWithEmailAndPassword(email_tv, password).addOnCompleteListener(requireActivity(), new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                pd.dismiss();
                if (task.isSuccessful()) {
                    Toast.makeText(requireContext(), "Registration successful!", Toast.LENGTH_LONG).show();
                    ((MainActivity) getActivity()).navigateToNextFragment();
                    // Launch OTP activity to verify phone number
                   // Intent intent = new Intent(requireContext(), SingUpFragment3.class);
                   // intent.putExtra("phoneNo", phoneNo1);
                  //  intent.putExtra("verificationId", task.getResult().getUser().getUid());
                   // startActivity(intent);
                } else {
                    // Check if there is any exception
                    Exception exception = task.getException();
                    if (exception instanceof FirebaseAuthUserCollisionException) {
                        Toast.makeText(requireContext(), "Email already registered!", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(requireContext(), "Registration failed!", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}
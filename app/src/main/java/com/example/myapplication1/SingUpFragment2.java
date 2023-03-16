package com.example.myapplication1;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


public class SingUpFragment2 extends Fragment {

    private Button button;
    private EditText fullname,country,city,address,id,postalcode;
    Uri uri;
   // private FirebaseAuth firebaseAuth;
   // private FirebaseDatabase database;
   // private DatabaseReference mDatabase;
   // private static final String USERS = "users";
  //  private FirebaseAuth mAuth;
   // private String fullName;
  //  private String id1;
  //  private String country1;
   // private String address1;
  // private String city1;
   // private String postal;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sing_up2, container, false);



        button = view.findViewById(R.id.button3);
        fullname = view.findViewById(R.id.regFullName);
        country = view.findViewById(R.id.regCountry);
        city = view.findViewById(R.id.regCity);
        address = view.findViewById(R.id.regAddress);
        id = view.findViewById(R.id.regId);
        postalcode = view.findViewById(R.id.regPostCode);
        //database = FirebaseDatabase.getInstance();
        //mDatabase = database.getReference(USERS);
        //mAuth = FirebaseAuth.getInstance();

        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == Activity.RESULT_OK){
                    Intent data = result.getData();
                    uri = data.getData();
                }else {
                    Toast.makeText(getActivity(), "something wrong",Toast.LENGTH_LONG).show();
                }
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();

            }




        });
        return view;
    }
    private void saveData() {

        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("users");
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(false);
        AlertDialog dialog = builder.create();
        dialog.show();
        uploadData();
    }
    private void uploadData(){
        String FullName = fullname.getText().toString();
        String Country = country.getText().toString();
        String city1 = city.getText().toString();
        String id1 = id.getText().toString();
        String postal = postalcode.getText().toString();
        String address1 = address.getText().toString();
        String phoneNo1 = "";
        DataClass dataClass = new DataClass(FullName,Country,city1,id1,postal,address1,phoneNo1);

        FirebaseDatabase.getInstance().getReference("Android Tutorial").child("user").push()
                .setValue(dataClass).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(getActivity(), "saved",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getActivity(), Home.class);
                            startActivity(intent);

                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity(), "something wrong1",Toast.LENGTH_LONG).show();
                    }
                });

    }
}



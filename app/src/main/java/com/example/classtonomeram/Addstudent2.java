package com.example.classtonomeram;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.classtonomeram.databinding.ActivityAddstudent2Binding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;



public class Addstudent2 extends AppCompatActivity {
    private ActivityAddstudent2Binding binding;


    private FirebaseAuth firebaseAuth;

    private ProgressDialog progressDialog;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddstudent2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please wait");
        progressDialog.setCanceledOnTouchOutside(false);
        binding.button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validdateData();
            }
        });
    }

    private String name = "", email = "", password = "",rollno = "",department = "",phone = "";
    private ArrayList<String> selectedSubjects = new ArrayList<>();


    private void validdateData() {

        CheckBox checkBox1 = binding.checkBox1;
        CheckBox checkBox2 = binding.checkBox2;
        CheckBox checkBox3 = binding.checkBox3;



        name= binding.editText4.getText().toString().trim();
        email = binding.editText12.getText().toString().trim();
        password = binding.editText14.getText().toString().trim();
        rollno=binding.editText6.getText().toString().trim();
        department=binding.editText8.getText().toString().trim();
        phone=binding.editText10.getText().toString().trim();
        if (checkBox1.isChecked()) {
            selectedSubjects.add(checkBox1.getText().toString());
        }
        if (checkBox2.isChecked()) {
            selectedSubjects.add(checkBox2.getText().toString());
        }
        if(checkBox3.isChecked())
        {
            selectedSubjects.add(checkBox3.getText().toString());
        }

        createUserAccount();


    }

    private void createUserAccount() {
        //show progress
        progressDialog.setMessage("Creating Account..");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {

                        updateUserInfo();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(Addstudent2.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void updateUserInfo() {
        progressDialog.setMessage("Saving user info..");

        long timestamp = System.currentTimeMillis();

        String uid = firebaseAuth.getUid();

        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("studentid", uid);
        hashMap.put("name", name);
        hashMap.put("rollno",rollno);
        hashMap.put("department",department);
        hashMap.put("phone",phone);
        hashMap.put("email", email);
        hashMap.put("password",password);
        hashMap.put("profileImage",""); //add empty will add later
        hashMap.put("userType", "user"); //possible values are user and admin
        hashMap.put("timestamp", timestamp);
        hashMap.put("selectedSubjects", selectedSubjects);


        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");
        ref.child(uid)
                .setValue(hashMap)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        //data added to db
                        progressDialog.dismiss();
                        Toast.makeText(Addstudent2.this, "Account created..", Toast.LENGTH_SHORT).show();
                        //Take user to dashboard
                        startActivity(new Intent(Addstudent2.this, student_information.class));
                        finish();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        //data failed to add to db
                        progressDialog.dismiss();
                        Toast.makeText(Addstudent2.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}









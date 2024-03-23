package com.example.classtonomeram;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.ktx.Firebase;

public class Teacherregister extends AppCompatActivity {

    private EditText name;
    private EditText email;
    private EditText password;
    private EditText college;
    private EditText department;

    private EditText phone;
    private Button register2;
    private FirebaseAuth auth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacherregister);

        name = findViewById(R.id.nameEt);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        college = findViewById(R.id.College);
        department = findViewById(R.id.department);
        phone = findViewById(R.id.phone);
        register2 = findViewById(R.id.register);

        auth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Faculty");

        register2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameText = name.getText().toString().trim();
                String emailText = email.getText().toString().trim();
                String passwordText = password.getText().toString().trim();
                String collegeText = college.getText().toString().trim();
                String departmentText = department.getText().toString().trim();
                String phoneText = phone.getText().toString().trim();

                if (TextUtils.isEmpty(nameText) || TextUtils.isEmpty(emailText) || TextUtils.isEmpty(passwordText) ||
                        TextUtils.isEmpty(collegeText) || TextUtils.isEmpty(departmentText) || TextUtils.isEmpty(phoneText)) {
                    Toast.makeText(Teacherregister.this, "Please fill in all the fields", Toast.LENGTH_SHORT).show();
                } else {
                    registerTeacher(nameText, emailText, passwordText, collegeText, departmentText, phoneText);
                }
            }
        });
    }

    private void registerTeacher(String name, String email, String password, String college, String department, String phone) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(Teacherregister.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    String userId = auth.getCurrentUser().getUid();
                    DatabaseReference currentUserDb = databaseReference.child(userId);
                    currentUserDb.child("Name").setValue(name);
                    currentUserDb.child("College").setValue(college);
                    currentUserDb.child("Department").setValue(department);
                    currentUserDb.child("Phone").setValue(phone);
                    currentUserDb.child("Email").setValue(email);
                    currentUserDb.child("password").setValue(password);

                    Toast.makeText(Teacherregister.this, "Successfully Registered", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Teacherregister.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

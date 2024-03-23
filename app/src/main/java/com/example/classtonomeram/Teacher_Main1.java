package com.example.classtonomeram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.*;
import android.os.Bundle;
import android.widget.*;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class Teacher_Main1 extends AppCompatActivity {
    private static final String TAG = "Teacher_Main1";
    EditText emailId, password;
    Button btnlogin;
    TextView register;
    Button Skip;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_main1);


        emailId = findViewById(R.id.email);
        password = findViewById(R.id.password);
        btnlogin = findViewById(R.id.loginBtn);
        register = findViewById(R.id.register1);
        auth=FirebaseAuth.getInstance();


      register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Intent intent=new Intent(Teacher_Main1.this,Teacherregister.class);
                    startActivity(intent);
                    finish();
                }
        });

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailId.getText().toString();
                String password1 = password.getText().toString();
                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password1)) {
                    Toast.makeText(Teacher_Main1.this, "Please enter email and password", Toast.LENGTH_SHORT).show();
                } else {
                    login3(email, password1);
                }
            }
        });
    }
    private void login3(String email,String password)
        {
            auth.signInWithEmailAndPassword(email,password).addOnSuccessListener(Teacher_Main1.this, new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    Toast.makeText(Teacher_Main1.this, "Login succesful", Toast.LENGTH_SHORT).show();

                    String currentUserUID = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    Intent intent = new Intent(Teacher_Main1.this, TeacherMainHome.class);
                    intent.putExtra("currentUserUID", currentUserUID);
                    startActivity(intent);

                }
            });



        }


}
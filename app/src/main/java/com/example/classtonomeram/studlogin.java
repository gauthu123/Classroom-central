package com.example.classtonomeram;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
public class studlogin extends AppCompatActivity {

    private EditText emailEditText, passwordEditText;
    private Button loginButton;

    private DatabaseReference usersRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studlogin);

        // Initialize views
        emailEditText = findViewById(R.id.emtext);
        passwordEditText = findViewById(R.id.passtxt);
        loginButton = findViewById(R.id.stlogin);

        // Get Firebase database reference
        usersRef = FirebaseDatabase.getInstance().getReference("Users");

        // Set click listener for login button
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get entered email and password
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                // Perform login
                loginStudent(email, password);
            }
        });
    }

    private void loginStudent(String email, String password) {
        Query query = usersRef.orderByChild("email").equalTo(email);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                boolean loginSuccessful = false;
                String studentUid = "";

                for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                    User user = userSnapshot.getValue(User.class);
                    if (user != null && user.getPassword().equals(password)) {
                        // Login successful
                        loginSuccessful = true;
                        studentUid = user.getUid();
                        break;
                    }
                }

                if (loginSuccessful) {
                    Toast.makeText(studlogin.this, "Login successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(studlogin.this, studentmenu.class);
                    intent.putExtra("studentUid", studentUid); // Pass the student's UID through intent
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(studlogin.this, "Login failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle database error
                Toast.makeText(studlogin.this, "Database error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}



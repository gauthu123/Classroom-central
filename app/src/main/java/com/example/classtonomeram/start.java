package com.example.classtonomeram;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class start extends AppCompatActivity {

    // Splash screen duration in milliseconds
    private static final int SPLASH_SCREEN_DURATION = 2000; // 2 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start  );

        // Delayed execution to show the splash screen for a certain duration
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Start the next activity after the splash screen
                Intent intent = new Intent(start.this, front_page.class);
                startActivity(intent);
                finish(); // Optional: Call finish() to close the current activity
            }
        }, 1000);
    }
}

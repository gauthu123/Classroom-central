package com.example.classtonomeram;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class front_page extends AppCompatActivity {
Button btnlec,btnstu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front_page2);
        btnlec=findViewById(R.id.btnlec);
        btnstu=findViewById(R.id.btnstu);
btnlec.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent=new Intent(front_page.this,Teacher_Main1.class);
        startActivity(intent);
    }
});
btnstu.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent=new Intent(front_page.this, studlogin.class);
        startActivity(intent);
    }
});
    }
}
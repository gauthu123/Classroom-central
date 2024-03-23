package com.example.classtonomeram;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class studentmenu extends AppCompatActivity {
    private TextView tv;
    Button attendance;
    Button pdf;
    Button notice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentmenu);
        String studentUid = getIntent().getStringExtra("studentUid");
        tv=findViewById(R.id.textView2);
        tv.setText(studentUid);
        attendance=findViewById(R.id.button);
        pdf = findViewById(R.id.button2);
        notice = findViewById(R.id.button3);
        attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(studentmenu.this, Attendance.class);
                intent.putExtra("studentUid",studentUid);
                startActivity(intent);

            }
        });
        pdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(studentmenu.this,Viewpdf.class);
                startActivity(intent);
            }
        });
        notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(studentmenu.this, ViewNoticeActivity.class);
                startActivity(intent);

            }
        });
    }
}
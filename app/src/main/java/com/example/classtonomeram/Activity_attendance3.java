package com.example.classtonomeram;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;

import java.util.HashMap;
import java.util.Locale;


public class Activity_attendance3 extends AppCompatActivity {
    CheckBox cb1,cb2,cb4,cb3,cb5;
    CalendarView cv;
    DatePicker dp;
    Button speak;
    FirebaseDatabase database;
    TextView Edittext;
    String s;

    TextToSpeech textToSpeech;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance3);
        HashMap<String,Object>hashMap=new HashMap<>();
        cb1=findViewById(R.id.cb1);
        cb2=findViewById(R.id.cb2);
        cb3=findViewById(R.id.cb3);
        cb4=findViewById(R.id.cb4);
        Edittext=findViewById(R.id.editTextText);

        //save=findViewById(R.id.save);
        speak=findViewById(R.id.save);
        textToSpeech=new TextToSpeech(Activity_attendance3.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status==TextToSpeech.SUCCESS)
                {
                    textToSpeech.setLanguage(Locale.ENGLISH);
                }
            }
        });
        speak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text=Edittext.getText().toString();
                textToSpeech.speak(text,TextToSpeech.QUEUE_ADD,null);
            }
        });

    }

}
package com.example.classtonomeram;

import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class TeacherMainHome extends AppCompatActivity {
    private Button addStudent;
    private Button viewstudentinfo;
    private Button take_attendance;
    private Button uploadpdf;
    private Button viewpdf;
   private Button sendnoticeBtn;
   private Button viewnotice;
   private TextView textView;
    private FirebaseAuth auth;
    private FirebaseUser user;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_main_home);
        // Inside onCreate() method
        Intent intent = getIntent();
        String currentUserUID = intent.getStringExtra("currentUserUID");

        auth=FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        addStudent=findViewById(R.id.add_student);
        viewstudentinfo=findViewById(R.id.Student_information);
        take_attendance=findViewById(R.id.take_attendance);
        uploadpdf=findViewById(R.id.pdfupload);
        viewpdf=findViewById(R.id.viewpdf);
        sendnoticeBtn=findViewById(R.id.sendnoticebtn);
        viewnotice=findViewById(R.id.viewnotice);
        textView=findViewById(R.id.text1);
        Animation buttonAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        Animation butAnimation = AnimationUtils.loadAnimation(this, R.anim.button_scale);



        addStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(buttonAnimation); // Start the animation

                // Start the next intent after the animation finishes
                buttonAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {}

                    @Override
                    public void onAnimationEnd(Animation animation) {
                      //  Intent intent = new Intent(TeacherMainHome.this, Addstudent2.class);
                        //startActivity(intent);
                        // Inside onSuccess() method after signing in
                        String currentUserUID = FirebaseAuth.getInstance().getCurrentUser().getUid();
                        Intent intent = new Intent(TeacherMainHome.this, Addstudent2.class);
                        intent.putExtra("currentUserUID", currentUserUID);
                        startActivity(intent);

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {}
                });
            }
        });


        viewstudentinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(buttonAnimation); // Start the animation

                // Start the next intent after a short delay
                buttonAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {}

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        Intent intent = new Intent(TeacherMainHome.this, student_information.class);
                        startActivity(intent);
                        intent.putExtra("currentUserUID", currentUserUID);
                        startActivity(intent);

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {}
                });
            }
        });

        take_attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(TeacherMainHome.this,TeacherView.class);
                startActivity(intent);

            }
        });
        uploadpdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(TeacherMainHome.this,UploadPDF.class);
                startActivity(intent);

            }
        });
        viewpdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(TeacherMainHome.this,Viewpdf.class);
                startActivity(intent);

            }
        });
        sendnoticeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(TeacherMainHome.this, SendNoticeActivity.class);
                startActivity(intent);
            }
        });
        viewnotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(TeacherMainHome.this,ViewNoticeActivity.class);
                startActivity(intent);
            }
        });


    }
}
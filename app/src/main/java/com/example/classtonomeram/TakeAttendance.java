package com.example.classtonomeram;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.Query;


import java.util.Calendar;

public class TakeAttendance extends AppCompatActivity {
    private EditText takeAttendence;
    private Button attendenceBtn;
    private Button absentBtn;
    private TextView btnvaluedatabase;
    private FirebaseAuth firebaseAuth;
    //..........Date
    private TextView tvCounter;
    private TextView stuname;
    private TextView sturollno;
    private TextView studepartment;
    private TextView stuemail;

    private TextView mDisplayDate;




    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_attendance);
        firebaseAuth = FirebaseAuth.getInstance();

        //.. DAte
        mDisplayDate = (TextView) findViewById(R.id.tvDate);
        String name = getIntent().getStringExtra("name");
        String email = getIntent().getStringExtra("email");
        String rollno = getIntent().getStringExtra("rollno");
        String department=getIntent().getStringExtra("department");
        String phone=getIntent().getStringExtra("phone");



        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        String date = month + 1 + "-" + day + "-" + year;

        mDisplayDate.setText(date);
        stuname=findViewById(R.id.tvname);
        sturollno=findViewById(R.id.tvrollno);
        stuemail=findViewById(R.id.tvemail);
        studepartment=findViewById(R.id.tvdepartment);
        stuname.setText(name);
        sturollno.setText(rollno);
        stuemail.setText(email);
        studepartment.setText(department);
        tvCounter = findViewById(R.id.tvcounter);
        takeAttendence = findViewById(R.id.student_id);
        attendenceBtn = findViewById(R.id.presentbtn);
        absentBtn=findViewById(R.id.absentbtn);
        //String code = classintent.getStringExtra("code");
        takeAttendence.setText(rollno);


        attendenceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rollNumber = takeAttendence.getText().toString();
                DatabaseReference attendanceRef = FirebaseDatabase.getInstance().getReference("Attendance").child(date);


                Query query = attendanceRef.orderByChild("rollNumber").equalTo(rollNumber);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {

                            for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                String attendanceKey = dataSnapshot.getKey();
                                DatabaseReference attendanceDataRef = attendanceRef.child(attendanceKey);
                                attendanceDataRef.child("attendanceStatus").setValue("present");
                            }
                            Toast.makeText(TakeAttendance.this, "Attendance updated", Toast.LENGTH_SHORT).show();
                        } else {

                            String studentid = firebaseAuth.getCurrentUser().getUid();
                            AttendanceData attendanceData = new AttendanceData(studentid, rollNumber, "present", date, name, department);
                            attendanceRef.push().setValue(attendanceData);
                            Toast.makeText(TakeAttendance.this, "Attendance recorded", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        // Handle the error case
                        Toast.makeText(TakeAttendance.this, "Error retrieving attendance data", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });



        absentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rollNumber = takeAttendence.getText().toString();
                DatabaseReference attendanceRef = FirebaseDatabase.getInstance().getReference("Attendance").child(date);


                Query query = attendanceRef.orderByChild("rollNumber").equalTo(rollNumber);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {

                            for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                String attendanceKey = dataSnapshot.getKey();
                                DatabaseReference attendanceDataRef = attendanceRef.child(attendanceKey);
                                attendanceDataRef.child("attendanceStatus").setValue("absent");
                            }
                            Toast.makeText(TakeAttendance.this, "Attendance updated", Toast.LENGTH_SHORT).show();
                        } else {

                            String uid = firebaseAuth.getCurrentUser().getUid();
                            AttendanceData attendanceData = new AttendanceData(uid, rollNumber, "absent", date, name, department);
                            attendanceRef.push().setValue(attendanceData);
                            Toast.makeText(TakeAttendance.this, "Attendance recorded", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        // Handle the error case
                        Toast.makeText(TakeAttendance.this, "Error retrieving attendance data", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


        TextView tvcounter=findViewById(R.id.tvcounter);
        DatabaseReference attendanceRef = FirebaseDatabase.getInstance().getReference().child("Attendance").child(date);
        Query presentQuery = attendanceRef.orderByChild("attendanceStatus").equalTo("present");
        presentQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int presentCount = (int) snapshot.getChildrenCount();
                tvcounter.setText("Present: " + presentCount);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(TakeAttendance.this, "Error retrieving present count", Toast.LENGTH_SHORT).show();
            }
        });

    }
}


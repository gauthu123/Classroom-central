package com.example.classtonomeram;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TeacherView extends AppCompatActivity {
    private Button selectDateBtn;
    private CalendarView calendarView;
    private TextView totalStudentsTextView;
    private TextView presentStudentsTextView;
    private TextView absentStudentsTextView;
    private ListView presentStudentsListView;
    private ListView absentStudentsListView;

    private DatabaseReference attendanceRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_view);

        selectDateBtn = findViewById(R.id.selectDateBtn);
        calendarView = findViewById(R.id.calendarView);
        totalStudentsTextView = findViewById(R.id.totalStudentsTextView);
        presentStudentsTextView = findViewById(R.id.presentStudentsTextView);
        presentStudentsListView = findViewById(R.id.presentStudentsListView);

        attendanceRef = FirebaseDatabase.getInstance().getReference("Attendance");

        selectDateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                String date = month + 1 + "-" + day + "-" + year;
                Query attendanceQuery = attendanceRef.child(date);
                totalStudentsTextView.setText(date);
                attendanceQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                           int totalStudents = (int) snapshot.getChildrenCount();
                          totalStudentsTextView.setText("Total Students: " + totalStudents);
                            Query presentQuery = attendanceQuery.orderByChild("attendanceStatus").equalTo("present");
                            presentQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    List<String> presentStudentsList = new ArrayList<>();

                                    for (DataSnapshot studentSnapshot : snapshot.getChildren()) {
                                        String studentName = studentSnapshot.child("name").getValue(String.class);
                                        presentStudentsList.add(studentName);
                                    }

                                    ArrayAdapter<String> presentAdapter = new ArrayAdapter<>(TeacherView.this,
                                            android.R.layout.simple_list_item_1, presentStudentsList);
                                    presentStudentsListView.setAdapter(presentAdapter);
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {
                                    // Handle the error case
                                    Toast.makeText(TeacherView.this, "Error retrieving present students", Toast.LENGTH_SHORT).show();
                                }
                            });

                            Query absentQuery = attendanceQuery.orderByChild("attendanceStatus").equalTo("absent");
                            absentQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {
                                    // Handle the error case
                                    Toast.makeText(TeacherView.this, "Error retrieving absent students", Toast.LENGTH_SHORT).show();
                                }
                            });
                        } else {
                            // No attendance data found for the selected date
                            totalStudentsTextView.setText("Total Students: 0");
                            presentStudentsListView.setAdapter(null);
                            absentStudentsListView.setAdapter(null);
                            Toast.makeText(TeacherView.this, "No attendance data found for the selected date", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        // Handle the error case
                        Toast.makeText(TeacherView.this, "Error retrieving attendance data", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
    private String convertDate(long dateInMillis) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        return dateFormat.format(new Date(dateInMillis));
    }
}


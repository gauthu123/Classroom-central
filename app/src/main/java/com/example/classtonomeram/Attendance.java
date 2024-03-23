//
//package com.example.classtonomeram;
//
//import android.os.Bundle;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//public class Attendance extends AppCompatActivity {
//
//    private DatabaseReference attendanceRef;
//    private TextView attendanceTextView;
//    private TextView check;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_attendance);
//
//        // Get Firebase database reference
//        attendanceRef = FirebaseDatabase.getInstance().getReference("Attendance");
//
//        // Initialize views
//        attendanceTextView = findViewById(R.id.attendanceTextView);
//        check = findViewById(R.id.uide);
//
//
//        // Get the student's UID from the intent (you need to pass the UID from the previous activity)
//        String studentUid = getIntent().getStringExtra("studentUid");
//        check.setText(studentUid);
//
//        // Retrieve the student's attendance data
//        retrieveAttendanceData(studentUid);
//    }
//
//    private void retrieveAttendanceData(String studentUid) {
//        //String studentUid = getIntent().getStringExtra("studentUid");
//        attendanceRef.orderByChild("uid").equalTo(studentUid).addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                StringBuilder attendanceData = new StringBuilder();
//
//                for (DataSnapshot dateSnapshot : snapshot.getChildren())
//                    for (DataSnapshot attendanceSnapshot : dateSnapshot.getChildren()) {
//                        AttendanceRecord attendanceRecord = attendanceSnapshot.getValue(AttendanceRecord.class);
//                        if (attendanceRecord != null) {
//                            String name = attendanceRecord.getName();
//                            String date = attendanceRecord.getDate();
//                            String attendanceStatus = attendanceRecord.getAttendanceStatus();
//                            String attendanceEntry = "Name: " + name + "\nDate: " + date + " - " + attendanceStatus + "\n";
//                            attendanceData.append(attendanceEntry);
//                        }
//                    }
//
//                attendanceTextView.setText(attendanceData.toString());
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                // Handle database error
//                Toast.makeText(Attendance.this, "Database error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//
//}
package com.example.classtonomeram;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Attendance extends AppCompatActivity {

    private DatabaseReference attendanceRef;
    private TextView attendanceTextView;
    private TextView check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);
        attendanceRef = FirebaseDatabase.getInstance().getReference("Attendance");
        attendanceTextView = findViewById(R.id.attendanceTextView);
        check = findViewById(R.id.uide);
        retrieveAttendanceData();
    }

    private void retrieveAttendanceData() {
        attendanceRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                StringBuilder attendanceData = new StringBuilder();
                for (DataSnapshot dateSnapshot : snapshot.getChildren()) {
                    for (DataSnapshot attendanceSnapshot : dateSnapshot.getChildren()) {
                        String studentUid = getIntent().getStringExtra("studentUid");
                        AttendanceRecord attendanceRecord = attendanceSnapshot.getValue(AttendanceRecord.class);
                        if (attendanceRecord != null){//&& attendanceRecord.getUid().equals(studentUid)) {
                            String name = attendanceRecord.getName();
                            String date = attendanceRecord.getDate();
                            String attendanceStatus = attendanceRecord.getAttendanceStatus();
                            String attendanceEntry = "Name: " + name + "\nDate: " + date + " - " + attendanceStatus + "\n";
                            attendanceData.append(attendanceEntry);
                        }
                    }
                }

                attendanceTextView.setText(attendanceData.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Attendance.this, "Database error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

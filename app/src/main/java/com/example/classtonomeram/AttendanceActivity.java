package com.example.classtonomeram;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AttendanceActivity extends AppCompatActivity {

    private ListView studentListView;
    private Button submitBtn;
    private List<StudentInformation> studentList;
    private Map<String, Boolean> attendanceMap;
    private DatabaseReference attendanceRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attendance_layout);
        attendanceRef = FirebaseDatabase.getInstance().getReference().child("Attendance");

        studentListView = findViewById(R.id.studentListView);
        submitBtn = findViewById(R.id.submitBtn);

        studentList = new ArrayList<>();
        attendanceMap = new HashMap<>();
        studentList.add(new StudentInformation("John", "123", "Computer Science","6476743272","bjjionewf@gmail.com","gyh653d%"));
        studentList.add(new StudentInformation("Alice", "456", "Electrical Engineering","6746724234","yuwrgg@email.com","uih7646"));
        studentList.add(new StudentInformation("Bob", "789", "Mechanical Engineering","6568763234","uirhu7578","hurhgruh"));
        ArrayAdapter<StudentInformation> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, studentList);
        studentListView.setAdapter(adapter);
        studentListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        studentListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                StudentInformation student = studentList.get(position);
                boolean isChecked = studentListView.isItemChecked(position);
                attendanceMap.put(student.getRollno(), isChecked);
            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveAttendanceToDatabase();
            }
        });
    }

    private void saveAttendanceToDatabase() {
        attendanceRef.setValue(attendanceMap);

        Toast.makeText(this, "Attendance submitted!", Toast.LENGTH_SHORT).show();
    }
}

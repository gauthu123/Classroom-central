package com.example.classtonomeram;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class student_information extends AppCompatActivity {



    String uid;

    private Button show;
    private ListView list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_information);
        show=findViewById(R.id.show);
        list=findViewById(R.id.list);
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> a=new ArrayList<>();
                ArrayAdapter adapter=new ArrayAdapter<>(student_information.this,R.layout.items,a);
                list.setAdapter(adapter);
                FirebaseDatabase.getInstance().getReference().child("Users").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists())
                        {
                            a.clear();
                            for(DataSnapshot snapshot1:snapshot.getChildren())
                            {
                                StudentInformation si=snapshot1.getValue(StudentInformation.class);
                                String t=si.getName()+"\n"+si.getRollno()+"\n"+si.getEmail()+"\n"+si.getDepartment()+"\n"+si.getPhone();
                                a.add(t);
                            }
                            adapter.notifyDataSetChanged();
                        }
                        list.setOnItemClickListener(
                                new AdapterView.OnItemClickListener(){
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                        String selectedItem = (String) parent.getItemAtPosition(position);
                                        String[] data = selectedItem.split("\n"); // Split the selected item by newline character

                                        String name = data[0];
                                        String rollno = data[1];
                                        String email = data[2];
                                        String department=data[3];
                                        String phone=data[4];



                                        Intent intent = new Intent(student_information.this, TakeAttendance.class);
                                        intent.putExtra("name", name);
                                        intent.putExtra("rollno", rollno);
                                        intent.putExtra("email", email);
                                        intent.putExtra("department",department);
                                        intent.putExtra("phone",phone);
                                        startActivity(intent);
                                    }
                                }
                        );
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {


                    }
                });

            }
        });
    }
}

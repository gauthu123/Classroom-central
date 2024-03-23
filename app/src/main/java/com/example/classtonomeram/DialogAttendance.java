package com.example.classtonomeram;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class DialogAttendance extends DialogFragment {
    @Override
      public void onStart(){
        super.onStart();
        Dialog dialog=getDialog();
        if(dialog!=null)
        {
           Objects.requireNonNull(dialog.getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        }

    }
     RecyclerView recyclerView;
    Button btn_simple;
    Context context;
    ArrayList<item_attendance> attendanceArrayList=new ArrayList<>();
    ArrayList<StudentInformation> studentInformationArrayList=new ArrayList<>();
    DatabaseReference database=FirebaseDatabase.getInstance().getReference();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.form_attendance,container,false);
        recyclerView=v.findViewById(R.id.recyclerView);
        btn_simple=v.findViewById(R.id.btn_simple);

        showData();
        return v;
    }

    private void showData() {
        database.child("test").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot item:snapshot.getChildren())
                {
                    StudentInformation si=item.getValue(StudentInformation.class);
                    if(si!=null)
                    {
                        studentInformationArrayList.add(si);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}

package com.example.classtonomeram;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

//public class AdapterItemAttendance extends RecyclerView.Adapter<AdapterItemAttendance.ItemViewHolder> {

//    AppCompatActivity activity;
//    ArrayList<item_attendance> attendanceArrayList;
//    ArrayList<StudentInformation> studentInformationArrayList;
//
//    Button btn_simple;
//    DatabaseReference database= FirebaseDatabase.getInstance().getReference();
//    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd-MMMM-yyyy",new Locale("in","ID"));
//
//    public AdapterItemAttendance(AppCompatActivity activity, ArrayList<item_attendance> attendanceArrayList, ArrayList<StudentInformation> studentInformationArrayList, Button btn_simple) {
//        this.activity = activity;
//        this.attendanceArrayList = attendanceArrayList;
//        this.studentInformationArrayList = studentInformationArrayList;
//        this.btn_simple = btn_simple;
//    }
//
//    public  AdapterItemAttendance(AppCompatActivity activity, ArrayList<item_attendance> attendanceArrayList, Button btn_simple){
//        this.activity=activity;
//        this.attendanceArrayList=attendanceArrayList;
//        this.btn_simple=btn_simple;
//    }
//
//
//
//    @NonNull
//    @Override
//    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_attendance,parent,false);
//        return new ItemViewHolder(itemView);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ItemViewHolder holder,final int position) {
//
//        holder.bindView(attendanceArrayList.get(position));
//
//        holder.keterangan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked) {
//                    attendanceArrayList.set(position, new item_attendance(
//                            attendanceArrayList.get(position).getStudent_key(),
//                            attendanceArrayList.get(position).getDate(),
//                            "ya"
//                    ));
//                } else {
//                    attendanceArrayList.set(position, new item_attendance(
//                            attendanceArrayList.get(position).getStudent_key(),
//                            attendanceArrayList.get(position).getDate(),
//                            "Tidak"
//                    ));
//                }
//            }
//        });
//
//
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return attendanceArrayList.size();
//    }
//
//    public class ItemViewHolder extends RecyclerView.ViewHolder {
//        CheckBox keterangan;
//        TextView et_nama;
//        public ItemViewHolder(@NonNull View itemView)
//        {
//            super(itemView);
//            et_nama=itemView.findViewById(R.id.et_nama);
//            keterangan=itemView.findViewById(R.id.checkbox_attendance);
//        }
//        public void bindView(final item_attendance item_attendance)
//        {
//
//            keterangan.setEnabled(true);
//            if(keterangan.isChecked())
//            {
//                keterangan.setChecked(true);
//            }
//            else
//            {
//                keterangan.setChecked(false);
//            }
//
//            database.child("test").child(item_attendance.getStudent_key()).addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot snapshot) {
//                    StudentInformation si=snapshot.getValue(StudentInformation.class);
//                    if(si!=null)
//                    {
//                    //    si.setKey(snapshot.getKey());
//                        et_nama.setText("Name:"+si.getName());
//                    }
//
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError error) {
//
//                }
//            });
//            btn_simple.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    for(int i=0;i<attendanceArrayList.size();i++)
//                    {
//                        database.child("Attendance").child(attendanceArrayList.get(i).getStudent_key()+"-"+simpleDateFormat.format(attendanceArrayList.get(i).getDate()))
//                                .setValue(new item_attendance(
//                                        attendanceArrayList.get(i).getStudent_key(),
//                                        attendanceArrayList.get(i).getDate(),
//                                        attendanceArrayList.get(i).getAttendance()
//
//                                ));
//                        if(i==attendanceArrayList.size()-1)
//                        {
//                            Toast.makeText(activity,"Data Saved Succesfully",Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                }
//            });
//


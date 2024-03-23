package com.example.classtonomeram;

public class AttendanceData {
    private String uid;
    private String rollNumber;
    private String attendanceStatus;
    private String date;
    private String name;
    private String department;

    public AttendanceData() {

    }

    public AttendanceData(String uid, String rollNumber, String attendanceStatus, String date,String name,String department) {
        this.uid = uid;
        this.rollNumber = rollNumber;
        this.attendanceStatus = attendanceStatus;
        this.date = date;
        this.name=name;
        this.department=department;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getAttendanceStatus() {
        return attendanceStatus;
    }

    public void setAttendanceStatus(String attendanceStatus) {
        this.attendanceStatus = attendanceStatus;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setName(String name)
    {
        this.name=name;
    }
    public  String getName(){
        return  name;
    }

    public void setDepartment(String department)
    {
        this.department=department;
    }
    public  String getDepartment(){
        return  department;
    }
}

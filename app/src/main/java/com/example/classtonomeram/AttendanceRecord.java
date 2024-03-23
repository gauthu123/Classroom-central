package com.example.classtonomeram;
public class AttendanceRecord {
    private String date;
    private String attendanceStatus;
    private String department;
    private String name;
    private String rollNumber;
    private String uid;

    public AttendanceRecord() {

    }



    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAttendanceStatus() {
        return attendanceStatus;
    }

    public void setAttendanceStatus(String attendanceStatus) {
        this.attendanceStatus = attendanceStatus;
    }


}

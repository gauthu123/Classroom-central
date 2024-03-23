package com.example.classtonomeram;

public class item_attendance {
    private String key;
    private String student_key;
    private long date;
    private String Attendance;
    public item_attendance(){

    }

    public item_attendance(String key) {
        this.key = key;
    }

    public item_attendance(String student_key, long date, String Attendance)
    {
        this.student_key=student_key;
        this.date=date;
        this.Attendance=Attendance;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
    public String getStudent_key(){
        return student_key;
    }
    public long getDate()
    {
        return date;
    }

    public String getAttendance() {
        return Attendance;
    }
}

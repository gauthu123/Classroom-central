package com.example.classtonomeram;
public class Student {
    private String uid;
    private String name;
    private boolean present;


    public Student() {
        // Default constructor required for Firebase
    }

    public Student(String uid, String name, boolean present) {
        this.uid = uid;
        this.name = name;
        this.present = present;
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

    public boolean isPresent() {
        return present;
    }

    public void setPresent(boolean present) {
        this.present = present;
    }
}

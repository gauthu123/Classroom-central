package com.example.classtonomeram;
public class StudentInformation {
    public String name;
    public String email;
    public String rollno;
    public String department;
    public String phone;
    public String password;
    private String key;

    public StudentInformation() {

    }

    public StudentInformation(String name, String rollno, String department, String phone, String email, String password) {
        this.name = name;
        this.email = email;
        this.rollno = rollno;
        this.department = department;
        this.password = password;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getRollno() {
        return rollno;
    }

    public String getDepartment() {
        return department;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setRollno(String rollno) {
        this.rollno = rollno;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

}

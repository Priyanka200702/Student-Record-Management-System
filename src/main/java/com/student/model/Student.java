
package com.student.model;

public class Student {

    private String rollNo;
    private String name;
    private String course;
    private int year;
    private String email;
    private String phone;
    private String gender;

    public Student(String rollNo, String name, String course,
                   int year, String email, String phone, String gender) {

        this.rollNo = rollNo;
        this.name = name;
        this.course = course;
        this.year = year;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
    }

    public String getRollNo() {
        return rollNo;
    }

    public String getName() {
        return name;
    }

    public String getCourse() {
        return course;
    }

    public int getYear() {
        return year;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getGender() {
        return gender;
    }
}

package com.example.attendance.model;

public class Student {
    private int studentId;
    private String studentName;
    private String studentEmail;
    private String courseId;

    public Student(int studentId, String studentName, String studentEmail, String courseId) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentEmail = studentEmail;
        this.courseId = courseId;
    }

    public Student(String studentName, String studentEmail, String courseId) {
        this.studentName = studentName;
        this.studentEmail = studentEmail;
        this.courseId = courseId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
}

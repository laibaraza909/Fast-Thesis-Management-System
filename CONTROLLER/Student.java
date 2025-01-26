package com.example.myjfxp;


public class Student {
    private String name;
    private String rollNo;
    private String dept;
    private String supervisor;
    private char grade; // Assuming grades are A, B, C, etc.
    private String remarks;

    // Constructor
    public Student(String name, String rollNo, String dept, String supervisor, char grade, String remarks) {
        this.name = name;
        this.rollNo = rollNo;
        this.dept = dept;
        this.supervisor = supervisor;
        this.grade = grade;
        this.remarks = remarks;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String  rollNo) {
        this.rollNo = rollNo;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    public char getGrade() {
        return grade;
    }

    public void setGrade(char grade) {
        this.grade = grade;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    // toString method for easy display
    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", rollNo=" + rollNo +
                ", dept='" + dept + '\'' +
                ", supervisor='" + supervisor + '\'' +
                ", grade=" + grade +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}

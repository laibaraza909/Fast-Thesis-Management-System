package org.example.thesis_management_system.Models;

import java.sql.Date;

public class Grade {
    private int gradeId;
    private Thesis thesis; // Reference Thesis object
    private String grade;
    private Date gradeDate;

    // Constructor with Thesis object (corrected to accept Thesis object)
    public Grade(int gradeId, Thesis thesis, String grade, Date gradeDate) {
        this.gradeId = gradeId;
        this.thesis = thesis; // Assign Thesis object
        this.grade = grade;
        this.gradeDate = gradeDate;
    }

    // Getters and Setters
    public int getGradeId() {
        return gradeId;
    }

    public void setGradeId(int gradeId) {
        this.gradeId = gradeId;
    }

    public Thesis getThesis() {
        return thesis;
    }

    public void setThesis(Thesis thesis) {
        this.thesis = thesis;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Date getGradeDate() {
        return gradeDate;
    }

    public void setGradeDate(Date gradeDate) {
        this.gradeDate = gradeDate;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "gradeId=" + gradeId +
                ", thesis=" + thesis.getThesisId() +  // Access Thesis ID
                ", grade='" + grade + '\'' +
                ", gradeDate=" + gradeDate +
                '}';
    }
}

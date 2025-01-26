package org.example.thesis_management_system.Models;

public class Student extends User {

    // Additional fields specific to Student (if any)

    // Constructor
    public Student(int id, String username, String password, String userType) {
        super(id, username, password, "student");
    }

    public Student(int studentId, String name, String email, String department, String username, String password) {
        super();
    }

    public Student(int studentId, int userId, String name, String email, String department) {
        super();
    }

    public Student(int studentId, int userId, String name, String email, String department, String username) {
    }

    public Student(int studentId, String name, String email, String department, String username) {
    }

    public Student(int id, String email) {

    }

    public Student(String text, String text1, String text2, String text3, char firstChar, String text4) {
    }

    // Implementation of abstract login method
    @Override
    public void login(String username, String password) {
        if (getUsername().equals(username) && getPassword().equals(password)) {
            System.out.println("Student " + username + " successfully logged in.");
        } else {
            System.out.println("Invalid credentials for student login.");
        }
    }


    public String getEmail()
    {
        return getEmail();
    }

    public String getDepartment()
    {
        return getDepartment();
    }
}

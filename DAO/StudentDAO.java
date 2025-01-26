package org.example.thesis_management_system.DAO;

import org.example.thesis_management_system.Models.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    private final Connection connection;

    public StudentDAO(Connection connection) {
        this.connection = connection;
    }

    // Create Student
    public void createStudent(Student student) throws SQLException {
        // Validate user_id
        if (!isValidUserId(student.getId())) {
            throw new SQLException("Invalid user ID");
        }
        String sql = "INSERT INTO Student (user_id, name, email, department) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, student.getId());
            stmt.setString(2, student.getUsername());
            stmt.setString(3, student.getEmail());
            stmt.setString(4, student.getDepartment());
            stmt.executeUpdate();
        }
    }

    // Read Student by ID
    public Student getStudentById(int studentId) throws SQLException {
        String sql = """
            SELECT s.*, u.username
            FROM Student s
            JOIN Users u ON s.user_id = u.user_id
            WHERE s.student_id = ?
        """;
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, studentId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Student(
                        rs.getInt("student_id"),
                        rs.getInt("user_id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("department"),
                        rs.getString("username")
                );
            }
        }
        return null;
    }

    // Update Student
    public void updateStudent(Student student) throws SQLException {
        String sql = "UPDATE Student SET name = ?, email = ?, department = ? WHERE student_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, student.getUsername());
            stmt.setString(2, student.getEmail());
            stmt.setString(3, student.getDepartment());
            stmt.setInt(4, student.getId());
            stmt.executeUpdate();
        }
    }

    // Delete Student
    public void deleteStudent(int studentId) throws SQLException {
        if (!isValidStudentId(studentId)) {
            throw new SQLException("Invalid student ID");
        }
        String sql = "DELETE FROM Student WHERE student_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, studentId);
            stmt.executeUpdate();
        }
    }

    // Get all students with user details
    public List<Student> getAllStudentsWithUserDetails() throws SQLException {
        List<Student> students = new ArrayList<>();
        String sql = """
            SELECT s.student_id, s.name, s.email, s.department, u.username
            FROM Student s
            JOIN Users u ON s.user_id = u.user_id
        """;
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Student student = new Student(
                        rs.getInt("student_id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("department"),
                        rs.getString("username")
                );
                students.add(student);
            }
        }
        return students;
    }

    // Helper method to validate user_id
    private boolean isValidUserId(int userId) throws SQLException {
        String query = "SELECT 1 FROM Users WHERE user_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userId);
            return stmt.executeQuery().next();
        }
    }

    // Helper method to validate student_id
    private boolean isValidStudentId(int studentId) throws SQLException {
        String query = "SELECT 1 FROM Student WHERE student_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, studentId);
            return stmt.executeQuery().next();
        }
    }
}

package org.example.thesis_management_system.DAO;

import org.example.thesis_management_system.Models.Grade;
import org.example.thesis_management_system.Models.Thesis;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GradeDAO {
    private Connection connection;

    // Constructor accepting the connection
    public GradeDAO(Connection connection) {
        this.connection = connection;
    }

    // Insert a new grade into the Grades table
    public void addGrade(int thesisId, String grade, Date gradeDate) throws SQLException {
        String query = "INSERT INTO Grades (thesis_id, grade, grade_date) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, thesisId); // Insert thesisId
            statement.setString(2, grade); // Insert grade
            statement.setDate(3, gradeDate); // Insert gradeDate
            statement.executeUpdate();
        }
    }

    // Get grades with associated thesis data
    public List<Grade> getGradesWithThesis() throws SQLException {
        String query = """
            SELECT g.grade_id, g.grade, g.grade_date, g.thesis_id, t.title AS thesis_title, t.status AS thesis_status
            FROM Grades g
            JOIN Thesis t ON g.thesis_id = t.thesis_id
        """;

        List<Grade> gradeList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                // Creating Grade object with Thesis details
                int thesisId = resultSet.getInt("thesis_id");
                Thesis thesis = new Thesis(thesisId, resultSet.getString("thesis_title"), resultSet.getString("thesis_status"));

                Grade grade = new Grade(
                        resultSet.getInt("grade_id"),
                        thesis, // Pass Thesis object to Grade
                        resultSet.getString("grade"),
                        resultSet.getDate("grade_date")
                );
                gradeList.add(grade);
            }
        }
        return gradeList;
    }

    // Update a grade by grade_id
    public void updateGrade(int gradeId, String grade, Date gradeDate) throws SQLException {
        String query = "UPDATE Grades SET grade = ?, grade_date = ? WHERE grade_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, grade);
            statement.setDate(2, gradeDate);
            statement.setInt(3, gradeId);
            statement.executeUpdate();
        }
    }

    // Delete a grade by grade_id
    public void deleteGrade(int gradeId) throws SQLException {
        String query = "DELETE FROM Grades WHERE grade_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, gradeId);
            statement.executeUpdate();
        }
    }
}

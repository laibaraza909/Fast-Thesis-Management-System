package org.example.thesis_management_system.DAO;

import org.example.thesis_management_system.Models.Defense;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DefenseDAO {
    private final Connection connection;

    public DefenseDAO(Connection connection) {
        this.connection = connection;
    }

    // Add a new defense
    public void addDefense(int studentId, int panelId, Date defenseDate, String feedback, String result, int scheduledBy) throws SQLException {
        String query = "INSERT INTO Defense (student_id, panel_id, defense_date, feedback, result, scheduled_by) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, studentId);
            statement.setInt(2, panelId);
            statement.setDate(3, defenseDate);
            statement.setString(4, feedback);
            statement.setString(5, result);
            statement.setInt(6, scheduledBy);
            statement.executeUpdate();
        }
    }

    // Retrieve all defenses
    public List<Defense> getAllDefenses() throws SQLException {
        String query = "SELECT * FROM Defense";
        List<Defense> defenses = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Defense defense = new Defense(
                        resultSet.getInt("defense_id"),
                        resultSet.getInt("student_id"),
                        resultSet.getInt("panel_id"),
                        resultSet.getDate("defense_date"),
                        resultSet.getString("feedback"),
                        resultSet.getString("result"),
                        resultSet.getInt("scheduled_by")
                );
                defenses.add(defense);
            }
        }
        return defenses;
    }

    // Retrieve defense by ID
    public Defense getDefenseById(int defenseId) throws SQLException {
        String query = "SELECT * FROM Defense WHERE defense_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, defenseId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Defense(
                            resultSet.getInt("defense_id"),
                            resultSet.getInt("student_id"),
                            resultSet.getInt("panel_id"),
                            resultSet.getDate("defense_date"),
                            resultSet.getString("feedback"),
                            resultSet.getString("result"),
                            resultSet.getInt("scheduled_by")
                    );
                }
            }
        }
        return null; // Return null if no record is found
    }

    // Update feedback for a defense
    public void updateFeedback(int defenseId, String feedback) throws SQLException {
        String query = "UPDATE Defense SET feedback = ? WHERE defense_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, feedback);
            statement.setInt(2, defenseId);
            statement.executeUpdate();
        }
    }

    // Update result for a defense
    public void updateResult(int defenseId, String result) throws SQLException {
        String query = "UPDATE Defense SET result = ? WHERE defense_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, result);
            statement.setInt(2, defenseId);
            statement.executeUpdate();
        }
    }

    // Delete a defense by ID
    public void deleteDefense(int defenseId) throws SQLException {
        String query = "DELETE FROM Defense WHERE defense_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, defenseId);
            statement.executeUpdate();
        }
    }
}

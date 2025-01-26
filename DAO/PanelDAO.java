package org.example.thesis_management_system.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PanelDAO {
    private Connection connection;

    public PanelDAO(Connection connection) {
        this.connection = connection;
    }

    // Add a new panel member with validation
    public void addPanelMember(int userId, int thesisId, String role, int createdBy) throws SQLException {
        // Validate user_id and thesis_id
        if (!isValidUserId(userId) || !isValidThesisId(thesisId)) {
            throw new SQLException("Invalid user ID or thesis ID");
        }
        String query = "INSERT INTO Panel (user_id, thesis_id, role, created_by) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            statement.setInt(2, thesisId);
            statement.setString(3, role);
            statement.setInt(4, createdBy);
            statement.executeUpdate();
        }
    }

    // Retrieve all panel members for a thesis with additional details
    public List<String> getPanelMembersForThesis(int thesisId) throws SQLException {
        String query = """
            SELECT p.panel_member_id, u.username, p.role, t.title AS thesis_title
            FROM Panel p
            JOIN Users u ON p.user_id = u.user_id
            JOIN Thesis t ON p.thesis_id = t.thesis_id
            WHERE p.thesis_id = ?
        """;
        List<String> panelMembers = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, thesisId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                panelMembers.add("Panel Member ID: " + resultSet.getInt("panel_member_id") +
                        ", Name: " + resultSet.getString("username") +
                        ", Role: " + resultSet.getString("role") +
                        ", Thesis Title: " + resultSet.getString("thesis_title"));
            }
        }
        return panelMembers;
    }

    // Delete a panel member with validation
    public void deletePanelMember(int panelMemberId) throws SQLException {
        if (!isValidPanelMemberId(panelMemberId)) {
            throw new SQLException("Invalid panel member ID");
        }
        String query = "DELETE FROM Panel WHERE panel_member_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, panelMemberId);
            statement.executeUpdate();
        }
    }

    // Helper methods to validate IDs
    private boolean isValidUserId(int userId) throws SQLException {
        String query = "SELECT 1 FROM Users WHERE user_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            return statement.executeQuery().next();
        }
    }

    private boolean isValidThesisId(int thesisId) throws SQLException {
        String query = "SELECT 1 FROM Thesis WHERE thesis_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, thesisId);
            return statement.executeQuery().next();
        }
    }

    private boolean isValidPanelMemberId(int panelMemberId) throws SQLException {
        String query = "SELECT 1 FROM Panel WHERE panel_member_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, panelMemberId);
            return statement.executeQuery().next();
        }
    }

    // Add a method to fetch userId by username
    public int getUserIdByName(String username) throws SQLException {
        String query = "SELECT user_id FROM Users WHERE username = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("user_id");
            } else {
                throw new SQLException("User not found");
            }
        }
    }


    // Retrieve all potential panel members (users eligible to be panel members)
    public List<String> getAvailablePanelMembers() throws SQLException {
        String query = "SELECT username FROM Users WHERE is_panel_eligible = 1";  // Assuming 'is_panel_eligible' column indicates eligibility
        List<String> panelMembers = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                panelMembers.add(resultSet.getString("username"));
            }
        }
        return panelMembers;
    }

}

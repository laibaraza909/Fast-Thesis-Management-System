package org.example.thesis_management_system.DAO;

import org.example.thesis_management_system.Models.Admin;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDAO {
    private final Connection connection;

    public AdminDAO(Connection connection) {
        this.connection = connection;
    }

    // Create a new Admin
    public void addAdmin(String name, String email) throws SQLException {
        String query = "INSERT INTO Admin (name, email) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, name);
            statement.setString(2, email);
            statement.executeUpdate();
            System.out.println("Admin added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error adding admin", e);
        }
    }

    // Retrieve an Admin by ID
    public Admin getAdminById(int adminId) throws SQLException {
        String query = "SELECT * FROM Admin WHERE admin_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, adminId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Admin(
                            resultSet.getInt("admin_id"),
                            resultSet.getString("name"),
                            resultSet.getString("email"),
                            connection // Pass the connection to Admin if needed
                    );
                }
            }
        }
        return null; // Admin not found
    }

    // Retrieve all Admins
    public List<Admin> getAllAdmins() throws SQLException {
        String query = "SELECT * FROM Admin";
        List<Admin> admins = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Admin admin = new Admin(
                        resultSet.getInt("admin_id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        connection // Pass the connection to Admin if needed
                );
                admins.add(admin);
            }
        }
        return admins;
    }

    // Update an Admin's details
    public void updateAdmin(int adminId, String name, String email) throws SQLException {
        String query = "UPDATE Admin SET name = ?, email = ? WHERE admin_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, name);
            statement.setString(2, email);
            statement.setInt(3, adminId);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Admin updated successfully!");
            } else {
                System.out.println("No admin found with the given ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error updating admin", e);
        }
    }

    // Delete an Admin by ID
    public void deleteAdmin(int adminId) throws SQLException {
        String query = "DELETE FROM Admin WHERE admin_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, adminId);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Admin deleted successfully!");
            } else {
                System.out.println("No admin found with the given ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error deleting admin", e);
        }
    }
}

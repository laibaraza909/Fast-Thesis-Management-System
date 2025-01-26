package org.example.thesis_management_system.DAO;

import org.example.thesis_management_system.Models.Supervisor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SupervisorDAO {
    private final Connection connection;

    public SupervisorDAO(Connection connection) {
        this.connection = connection;
    }

    // Create Supervisor
    public void createSupervisor(Supervisor supervisor) {
        String sql = "INSERT INTO Supervisor (user_id, department) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, supervisor.getId());
            stmt.setString(2, supervisor.getDepartment());
            stmt.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new RuntimeException("Supervisor creation failed: user ID does not exist or violates constraints.", e);
        } catch (SQLException e) {
            throw new RuntimeException("Database error occurred while creating a supervisor.", e);
        }
    }

    // Read Supervisor by ID
    public Supervisor getSupervisorById(int supervisorId) {
        String sql = "SELECT sp.supervisor_id, sp.user_id, sp.department, u.username, u.password " +
                "FROM Supervisor sp " +
                "JOIN Users u ON sp.user_id = u.user_id " +
                "WHERE sp.supervisor_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, supervisorId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Supervisor(
                        rs.getInt("supervisor_id"),
                        rs.getInt("user_id"),
                        rs.getString("department"),
                        rs.getString("username"),
                        rs.getString("password")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database error occurred while fetching supervisor by ID.", e);
        }
        return null;
    }

    // Update Supervisor
    public void updateSupervisor(Supervisor supervisor) {
        String sql = "UPDATE Supervisor SET department = ? WHERE supervisor_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, supervisor.getDepartment());
            stmt.setInt(2, supervisor.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Database error occurred while updating supervisor.", e);
        }
    }

    // Delete Supervisor
    public void deleteSupervisor(int supervisorId) {
        String sql = "DELETE FROM Supervisor WHERE supervisor_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, supervisorId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Database error occurred while deleting supervisor.", e);
        }
    }

    // Get all supervisors with their corresponding user details using JOIN
    public List<Supervisor> getAllSupervisorsWithUserDetails() {
        List<Supervisor> supervisors = new ArrayList<>();
        String sql = "SELECT sp.supervisor_id, sp.user_id, sp.department, u.username, u.password " +
                "FROM Supervisor sp " +
                "JOIN Users u ON sp.user_id = u.user_id";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Supervisor supervisor = new Supervisor(
                        rs.getInt("supervisor_id"),
                        rs.getInt("user_id"),
                        rs.getString("department"),
                        rs.getString("username"),
                        rs.getString("password")
                );
                supervisors.add(supervisor);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database error occurred while fetching all supervisors.", e);
        }
        return supervisors;
    }

    // Get supervisors by optional filters
    public List<Supervisor> getSupervisorsByCriteria(String department, Integer supervisorId) {
        List<Supervisor> supervisors = new ArrayList<>();
        StringBuilder sql = new StringBuilder(
                "SELECT sp.supervisor_id, sp.user_id, sp.department, u.username, u.password " +
                        "FROM Supervisor sp " +
                        "JOIN Users u ON sp.user_id = u.user_id WHERE 1=1"
        );

        if (department != null) sql.append(" AND sp.department = ?");
        if (supervisorId != null) sql.append(" AND sp.supervisor_id = ?");

        try (PreparedStatement stmt = connection.prepareStatement(sql.toString())) {
            int index = 1;
            if (department != null) stmt.setString(index++, department);
            if (supervisorId != null) stmt.setInt(index, supervisorId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Supervisor supervisor = new Supervisor(
                        rs.getInt("supervisor_id"),
                        rs.getInt("user_id"),
                        rs.getString("department"),
                        rs.getString("username"),
                        rs.getString("password")
                );
                supervisors.add(supervisor);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database error occurred while fetching supervisors by criteria.", e);
        }
        return supervisors;
    }
}

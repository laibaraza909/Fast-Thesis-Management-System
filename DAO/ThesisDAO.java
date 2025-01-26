package org.example.thesis_management_system.DAO;

import org.example.thesis_management_system.Models.Thesis;
import org.example.thesis_management_system.Utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ThesisDAO {
    private final Connection connection;

    // Initialize the connection in the constructor
    public ThesisDAO() throws SQLException {
        this.connection = DatabaseConnection.getConnection(); // Correct way to initialize the connection
    }

    // Add a new thesis
    public void addThesis(int studentId, int supervisorId, String title, String status) {
        String query = "INSERT INTO Thesis (student_id, supervisor_id, title, status) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, studentId);
            statement.setInt(2, supervisorId);
            statement.setString(3, title);
            statement.setString(4, status);
            statement.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new RuntimeException("Thesis creation failed: Invalid student or supervisor ID.", e);
        } catch (SQLException e) {
            throw new RuntimeException("Database error occurred while adding a new thesis.", e);
        }
    }

    // Retrieve all theses with student and supervisor details
    public List<String> getAllThesesWithDetails() {
        String query = """
            SELECT t.thesis_id, t.title, t.status, s.username AS supervisor_name, st.username AS student_name
            FROM Thesis t
            JOIN Users s ON t.supervisor_id = s.user_id
            JOIN Users st ON t.student_id = st.user_id
        """;
        List<String> thesisDetails = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                thesisDetails.add("Thesis ID: " + resultSet.getInt("thesis_id") +
                        ", Title: " + resultSet.getString("title") +
                        ", Status: " + resultSet.getString("status") +
                        ", Supervisor: " + resultSet.getString("supervisor_name") +
                        ", Student: " + resultSet.getString("student_name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database error occurred while fetching theses with details.", e);
        }
        return thesisDetails;
    }

    // Update thesis status
    public void updateThesisStatus(int thesisId, String status) {
        String query = "UPDATE Thesis SET status = ? WHERE thesis_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, status);
            statement.setInt(2, thesisId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Database error occurred while updating thesis status.", e);
        }
    }

    // Delete a thesis
    public void deleteThesis(int thesisId) {
        String query = "DELETE FROM Thesis WHERE thesis_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, thesisId);
            statement.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new RuntimeException("Cannot delete thesis: It may have dependencies.", e);
        } catch (SQLException e) {
            throw new RuntimeException("Database error occurred while deleting a thesis.", e);
        }
    }

    // Retrieve theses by optional filters
    public List<String> getThesesByCriteria(Integer studentId, Integer supervisorId, String status) {
        StringBuilder query = new StringBuilder("""
            SELECT t.thesis_id, t.title, t.status, s.username AS supervisor_name, st.username AS student_name
            FROM Thesis t
            JOIN Users s ON t.supervisor_id = s.user_id
            JOIN Users st ON t.student_id = st.user_id
            WHERE 1 = 1
        """);

        if (studentId != null) query.append(" AND t.student_id = ?");
        if (supervisorId != null) query.append(" AND t.supervisor_id = ?");
        if (status != null) query.append(" AND t.status = ?");

        List<String> thesisDetails = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(query.toString())) {
            int index = 1;
            if (studentId != null) statement.setInt(index++, studentId);
            if (supervisorId != null) statement.setInt(index++, supervisorId);
            if (status != null) statement.setString(index, status);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    thesisDetails.add("Thesis ID: " + resultSet.getInt("thesis_id") +
                            ", Title: " + resultSet.getString("title") +
                            ", Status: " + resultSet.getString("status") +
                            ", Supervisor: " + resultSet.getString("supervisor_name") +
                            ", Student: " + resultSet.getString("student_name"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database error occurred while fetching theses by criteria.", e);
        }
        return thesisDetails;
    }

    // Retrieve a thesis by its ID
    public Thesis getThesisById(int thesisId) {
        String query = "SELECT * FROM Thesis WHERE thesis_id = ?";
        Thesis thesis = null;

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, thesisId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    // Create a Thesis object from the result set
                    int studentId = resultSet.getInt("student_id");
                    int supervisorId = resultSet.getInt("supervisor_id");
                    String title = resultSet.getString("title");
                    String status = resultSet.getString("status");

                    // Instantiate Thesis object with retrieved values
                    thesis = new Thesis(thesisId, studentId, supervisorId, title, status);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database error occurred while fetching thesis by ID.", e);
        }

        return thesis;
    }

    // Update the Thesis with revised thesis content
    public void updateRevisedThesis(int thesisId, String revisedThesis) {
        String query = "UPDATE Thesis SET revised_thesis = ? WHERE thesis_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, revisedThesis);
            statement.setInt(2, thesisId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error occurred while updating revised thesis.", e);
        }
    }

    // Save revised thesis document (Implement the actual functionality as required)
    public boolean saveRevisedThesis(int thesisId, String revisedDocument) {
        String query = "UPDATE Thesis SET revised_thesis = ? WHERE thesis_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, revisedDocument); // assuming revisedDocument is a path or document URL
            statement.setInt(2, thesisId);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0; // Returns true if the update was successful
        } catch (SQLException e) {
            throw new RuntimeException("Error occurred while saving revised thesis document.", e);
        }
    }
}

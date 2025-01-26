package org.example.thesis_management_system.DAO;



import org.example.thesis_management_system.Models.ConsentForm;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;


public class ConsentFormDAO {
    private final Connection connection;

    // Constructor to initialize the connection
    public ConsentFormDAO(Connection connection) {
        this.connection = connection;
    }


    // Create: Save a new consent form to the database
    public void saveConsentForm(ConsentForm consentForm) throws SQLException {
        String query = """
            INSERT INTO ConsentForm (student_id, proposal_id, consent_form_file, submission_date)
            VALUES (?, ?, ?, ?)
        """;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, consentForm.getStudentId());
            statement.setInt(2, consentForm.getProposalId());
            statement.setString(3, consentForm.getConsentFormFile());
            statement.setDate(4, consentForm.getSubmissionDate());
            statement.executeUpdate();
        }
    }

    // Read: Get a consent form by ID (without joining other tables)
    public ConsentForm getConsentFormById(int consentFormId) throws SQLException {
        String query = "SELECT * FROM ConsentForm WHERE consent_form_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, consentFormId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return mapToConsentForm(resultSet);  // Mapping result set to ConsentForm model
            }
        }
        return null;
    }

    // Read: Get consent form details with joins for student and proposal details
    public ConsentForm getConsentFormWithDetails(int consentFormId) throws SQLException {
        String query = """
            SELECT cf.consent_form_id, cf.student_id, cf.proposal_id, cf.consent_form_file, cf.submission_date,
                   u.user_name AS student_name, p.proposal_title
            FROM ConsentForm cf
            JOIN Users u ON cf.student_id = u.user_id
            JOIN Proposal p ON cf.proposal_id = p.proposal_id
            WHERE cf.consent_form_id = ?
        """;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, consentFormId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return mapToConsentFormWithDetails(resultSet);  // Mapping result set to ConsentForm model with details
            }
        }
        return null;
    }

    // Update: Modify an existing consent form
    public void updateConsentForm(ConsentForm consentForm) throws SQLException {
        String query = """
            UPDATE ConsentForm 
            SET student_id = ?, proposal_id = ?, consent_form_file = ?, submission_date = ?
            WHERE consent_form_id = ?
        """;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, consentForm.getStudentId());
            statement.setInt(2, consentForm.getProposalId());
            statement.setString(3, consentForm.getConsentFormFile());
            statement.setDate(4, consentForm.getSubmissionDate());
            statement.setInt(5, consentForm.getConsentFormId());
            statement.executeUpdate();
        }
    }

    // Delete: Remove a consent form from the database
    public void deleteConsentForm(int consentFormId) throws SQLException {
        String query = "DELETE FROM ConsentForm WHERE consent_form_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, consentFormId);
            statement.executeUpdate();
        }
    }

    // Helper method to map result set to ConsentForm model
    private ConsentForm mapToConsentForm(ResultSet resultSet) throws SQLException {
        return new ConsentForm(
                resultSet.getInt("consent_form_id"),
                resultSet.getInt("student_id"),
                resultSet.getInt("proposal_id"),
                resultSet.getString("consent_form_file"),
                resultSet.getDate("submission_date")
        );
    }

    // Helper method to map result set to ConsentForm model with additional student and proposal details
    private ConsentForm mapToConsentFormWithDetails(ResultSet resultSet) throws SQLException {
        return new ConsentForm(
                resultSet.getInt("consent_form_id"),
                resultSet.getInt("student_id"),
                resultSet.getInt("proposal_id"),
                resultSet.getString("consent_form_file"),
                resultSet.getDate("submission_date"),
                resultSet.getString("student_name"),
                resultSet.getString("proposal_title")
        );
    }
}


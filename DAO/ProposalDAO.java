package org.example.thesis_management_system.DAO;

import eu.hansolo.toolbox.tuples.Pair;
import org.example.thesis_management_system.Models.Proposal;
import org.example.thesis_management_system.Models.Thesis;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProposalDAO {
    private Connection connection;

    public ProposalDAO(Connection connection) {
        this.connection = connection;
    }

    // Create a new proposal
    public void addProposal(int thesisId, String consentForm, Date defenseDate, String feedback) throws SQLException {
        String query = "INSERT INTO Proposal (thesis_id, consent_form, defense_date, feedback) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, thesisId);
            statement.setString(2, consentForm);
            statement.setDate(3, defenseDate);
            statement.setString(4, feedback);
            statement.executeUpdate();
        }
    }

    // Retrieve all proposals
    public List<Proposal> getAllProposals() throws SQLException {
        String query = "SELECT * FROM Proposal";
        List<Proposal> proposals = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Proposal proposal = new Proposal(
                        resultSet.getInt("proposal_id"),
                        resultSet.getInt("thesis_id"),
                        resultSet.getString("consent_form"),
                        resultSet.getDate("defense_date"),
                        resultSet.getString("feedback")
                );
                proposals.add(proposal);
            }
        }
        return proposals;
    }

    public List<Pair<Proposal, Thesis>> getProposalsWithThesisDetails() throws SQLException {
        String query = """
        SELECT p.proposal_id, p.thesis_id, p.consent_form, p.defense_date, p.feedback,
               t.thesis_id AS thesisId, t.title AS thesis_title, t.status AS thesis_status
        FROM Proposal p
        INNER JOIN Thesis t ON p.thesis_id = t.thesis_id
    """;

        List<Pair<Proposal, Thesis>> result = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                // Create Proposal object
                Proposal proposal = new Proposal(
                        resultSet.getInt("proposal_id"),
                        resultSet.getInt("thesis_id"),
                        resultSet.getString("consent_form"),
                        resultSet.getDate("defense_date"),
                        resultSet.getString("feedback")
                );

                // Create Thesis object
                Thesis thesis = new Thesis(
                        resultSet.getInt("thesisId"),
                        resultSet.getString("thesis_title"),
                        resultSet.getString("thesis_status")
                );

                // Add to the list
                result.add(new Pair<>(proposal, thesis));
            }
        }
        return result;
    }

    // Update feedback for a proposal
    public void updateFeedback(int proposalId, String feedback) throws SQLException {
        String query = "UPDATE Proposal SET feedback = ? WHERE proposal_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, feedback);
            statement.setInt(2, proposalId);
            statement.executeUpdate();
        }
    }

    // Delete a proposal by ID
    public void deleteProposal(int proposalId) throws SQLException {
        String query = "DELETE FROM Proposal WHERE proposal_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, proposalId);
            statement.executeUpdate();
        }
    }
}

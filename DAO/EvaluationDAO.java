package org.example.thesis_management_system.DAO;

import org.example.thesis_management_system.Utils.DatabaseConnection;
import org.example.thesis_management_system.Models.Evaluation;

import java.sql.*;

public class EvaluationDAO {

    public EvaluationDAO() {
        // Constructor logic if needed
    }

    // 1. CREATE operation - Save new Evaluation
    public void saveEvaluation(Evaluation evaluation) {
        if (!isValidEvaluator(evaluation.getEvaluatorId())) {
            System.out.println("Error: Only Supervisors and Admins can evaluate.");
            return;
        }

        String query = "INSERT INTO Evaluation (thesis_id, evaluator_id, score, comments) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, evaluation.getThesisId());
            stmt.setInt(2, evaluation.getEvaluatorId());
            stmt.setInt(3, evaluation.getScore());
            stmt.setString(4, evaluation.getComments() != null ? evaluation.getComments() : "");

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Evaluation saved successfully!");
            }

        } catch (SQLException e) {
            System.out.println("Error saving evaluation: " + e.getMessage());
        }
    }

    // 2. READ operation - Retrieve Evaluation by Thesis ID (with JOIN to get evaluator details)
    public String retrieveEvaluationDetailsByThesisId(int thesisId) {
        String query = "SELECT e.evaluation_id, e.thesis_id, e.evaluator_id, e.score, e.comments, u.name, u.user_type " +
                "FROM Evaluation e " +
                "JOIN Users u ON e.evaluator_id = u.user_id " +
                "WHERE e.thesis_id = ?";

        StringBuilder evaluationDetails = new StringBuilder();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, thesisId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int evaluationId = rs.getInt("evaluation_id");
                int evaluatorId = rs.getInt("evaluator_id");
                int score = rs.getInt("score");
                String comments = rs.getString("comments");
                String evaluatorName = rs.getString("name");
                String evaluatorRole = rs.getString("user_type");

                evaluationDetails.append("Evaluation ID: ").append(evaluationId)
                        .append("\nThesis ID: ").append(thesisId)
                        .append("\nEvaluator: ").append(evaluatorName).append(" (").append(evaluatorRole).append(")")
                        .append("\nScore: ").append(score)
                        .append("\nComments: ").append(comments).append("\n\n");
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving evaluation details: " + e.getMessage());
        }

        return evaluationDetails.length() > 0 ? evaluationDetails.toString() : "No evaluations found for this thesis.";
    }

    // 3. UPDATE operation - Update Evaluation details (score and comments)
    public void updateEvaluation(Evaluation evaluation) {
        if (!isValidEvaluator(evaluation.getEvaluatorId())) {
            System.out.println("Error: Only Supervisors and Admins can update evaluations.");
            return;
        }

        String query = "UPDATE Evaluation SET score = ?, comments = ? WHERE evaluation_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, evaluation.getScore());
            stmt.setString(2, evaluation.getComments());
            stmt.setInt(3, evaluation.getEvaluationId());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Evaluation updated successfully!");
            } else {
                System.out.println("No evaluation found with the provided ID.");
            }

        } catch (SQLException e) {
            System.out.println("Error updating evaluation: " + e.getMessage());
        }
    }

    // 4. DELETE operation - Delete an evaluation by its ID
    public void deleteEvaluation(int evaluationId) {
        String query = "DELETE FROM Evaluation WHERE evaluation_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, evaluationId);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Evaluation deleted successfully!");
            } else {
                System.out.println("No evaluation found with the provided ID.");
            }

        } catch (SQLException e) {
            System.out.println("Error deleting evaluation: " + e.getMessage());
        }
    }

    // Helper method to check if the evaluator is a Supervisor
    private boolean isValidEvaluator(int evaluatorId) {
        String query = "SELECT user_type FROM Users WHERE user_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, evaluatorId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String userType = rs.getString("user_type");  // Correct column name is user_type
                return userType.equalsIgnoreCase("Supervisor"); // Only Supervisors can evaluate
            }
        } catch (SQLException e) {
            System.out.println("Error checking evaluator's role: " + e.getMessage());
        }
        return false;
    }

    // 5. Get Report Details
    public Evaluation getReportDetails(int reportId) {
        String query = "SELECT * FROM Evaluation WHERE evaluation_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, reportId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Assuming the Evaluation class has a constructor that accepts these parameters
                return new Evaluation(
                        rs.getInt("evaluation_id"),
                        rs.getInt("thesis_id"),
                        rs.getInt("evaluator_id"),
                        rs.getInt("score"),
                        rs.getString("comments")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving report details: " + e.getMessage());
        }
        return null;
    }

    // 6. Get Evaluation by Report ID
    public Evaluation getEvaluationByReportId(int reportId) {
        return getReportDetails(reportId);  // Delegate to getReportDetails for now
    }
}

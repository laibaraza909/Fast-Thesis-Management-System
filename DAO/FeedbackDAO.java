package org.example.thesis_management_system.DAO;

import org.example.thesis_management_system.Models.Evaluation;
import org.example.thesis_management_system.Models.Feedback;
import org.example.thesis_management_system.Models.Thesis;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FeedbackDAO {
    private final Connection connection;

    // Constructor to initialize connection
    public FeedbackDAO(Connection connection) {
        this.connection = connection;
    }

    // CREATE method - Add a new feedback
    public void addFeedback(Feedback feedback) throws SQLException {
        String query = "INSERT INTO Feedback (evaluation_id, evaluator_id, comments, feedback_date) " +
                "VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, feedback.getEvaluationId());
            statement.setInt(2, feedback.getEvaluatorId());
            statement.setString(3, feedback.getComments());
            statement.setDate(4, new Date(feedback.getFeedbackDate().getTime()));  // Assuming feedbackDate is of type java.util.Date

            statement.executeUpdate();
        }
    }

    // READ method - Get all feedback for a specific evaluation
    public List<Feedback> getFeedbackByEvaluation(int evaluationId) throws SQLException {
        String query = "SELECT * FROM Feedback WHERE evaluation_id = ?";
        List<Feedback> feedbackList = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, evaluationId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Feedback feedback = new Feedback(
                            resultSet.getInt("feedback_id"),
                            resultSet.getInt("evaluator_id"),
                            resultSet.getString("comments"),
                            resultSet.getDate("feedback_date")
                    );
                    feedbackList.add(feedback);
                }
            }
        }
        return feedbackList;
    }

    // READ method - Get all feedback for a specific evaluator
    public List<Feedback> getFeedbackByEvaluator(int evaluatorId) throws SQLException {
        String query = "SELECT f.feedback_id, f.comments, f.feedback_date, e.evaluation_id " +
                "FROM Feedback f " +
                "JOIN Evaluation e ON f.evaluation_id = e.evaluation_id " +  // Join between Feedback and Evaluation
                "WHERE e.evaluator_id = ?";
        List<Feedback> feedbackList = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, evaluatorId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Feedback feedback = new Feedback(
                            resultSet.getInt("feedback_id"),
                            evaluatorId,  // The evaluator ID would be passed or inferred
                            resultSet.getString("comments"),
                            resultSet.getDate("feedback_date")
                    );
                    feedbackList.add(feedback);
                }
            }
        }
        return feedbackList;
    }

    // READ method - Get all feedback with evaluator information
    public List<Feedback> getFeedbackWithEvaluatorInfo(int evaluationId) throws SQLException {
        String query = "SELECT f.feedback_id, f.comments, f.feedback_date, u.username " +
                "FROM Feedback f " +
                "JOIN Evaluation e ON f.evaluation_id = e.evaluation_id " +
                "JOIN Users u ON e.evaluator_id = u.user_id " +  // Join with Users to get evaluator info
                "WHERE f.evaluation_id = ?";
        List<Feedback> feedbackList = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, evaluationId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Feedback feedback = new Feedback(
                            resultSet.getInt("feedback_id"),
                            resultSet.getInt("evaluation_id"),
                            resultSet.getString("comments"),
                            resultSet.getDate("feedback_date")
                    );
                    String evaluatorName = resultSet.getString("username");
                    // Optionally, set the evaluator's name in the feedback object if needed
                    // feedback.setEvaluatorName(evaluatorName);
                    feedbackList.add(feedback);
                }
            }
        }
        return feedbackList;
    }

    // UPDATE method - Update an existing feedback
    public void updateFeedback(Feedback feedback) throws SQLException {
        String query = "UPDATE Feedback SET comments = ?, feedback_date = ? WHERE feedback_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, feedback.getComments());
            statement.setDate(2, new Date(feedback.getFeedbackDate().getTime()));  // Assuming feedbackDate is of type java.util.Date
            statement.setInt(3, feedback.getFeedbackId());

            statement.executeUpdate();
        }
    }

    // DELETE method - Delete feedback by feedback ID
    public void deleteFeedback(int feedbackId) throws SQLException {
        String query = "DELETE FROM Feedback WHERE feedback_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, feedbackId);
            statement.executeUpdate();
        }
    }

    // In FeedbackDAO
    public List<Feedback> getFeedbackByProposalId(int proposalId) throws SQLException {
        String query = "SELECT * FROM Feedback WHERE proposal_id = ?";
        List<Feedback> feedbackList = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, proposalId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Feedback feedback = new Feedback(
                            resultSet.getInt("feedback_id"),
                            resultSet.getInt("evaluator_id"),
                            resultSet.getString("comments"),
                            resultSet.getDate("feedback_date")
                    );
                    feedbackList.add(feedback);
                }
            }
        }
        return feedbackList;
    }


    // Method to save feedback to the database
    public void saveFeedback(Feedback feedback) throws SQLException {
        String sql = "INSERT INTO Feedback (evaluation_id, evaluator_id, comments, feedback_date) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, feedback.getEvaluationId());
            statement.setInt(2, feedback.getEvaluatorId());
            statement.setString(3, feedback.getComments());
            statement.setDate(4, feedback.getFeedbackDate());

            // Execute the insert operation
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Feedback saved successfully.");
            }
        } catch (SQLException e) {
            throw new SQLException("Error saving feedback to the database.", e);
        }
    }

    // Fetch all midterm feedbacks from the database
    public List<Feedback> getMidtermFeedbacks() throws SQLException {
        List<Feedback> feedbacks = new ArrayList<>();
        String query = "SELECT * FROM feedback WHERE feedbackType = 'midterm'"; // Example query for midterm feedback
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Feedback feedback = new Feedback(rs.getInt("feedbackId"), rs.getString("comments"), rs.getInt("score"));
                feedbacks.add(feedback);
            }
        }
        return feedbacks;
    }

    // Save midterm feedback to the database
    public void saveMidtermFeedback(Feedback feedback) throws SQLException {
        String query = "INSERT INTO feedback (comments, score, feedbackType) VALUES (?, ?, 'midterm')";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, feedback.getComments());
            stmt.setInt(2, feedback.getScore());
            stmt.executeUpdate();
        }
    }


    // Method to get feedback by its ID
    public Feedback getFeedbackById(int feedbackId) throws SQLException {
        String sql = "SELECT * FROM feedback WHERE feedback_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, feedbackId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // Assuming you have a constructor or setters in Feedback class to handle the retrieved data
                int evaluatorId = resultSet.getInt("evaluator_id");
                String comments = resultSet.getString("comments");
                Date feedbackDate = resultSet.getDate("feedback_date");

                // Assuming you have an Evaluation object that can be populated from the DB (you might need to fetch the thesis and evaluation data)
                Evaluation evaluation = getEvaluationById(resultSet.getInt("evaluation_id"));

                return new Feedback(feedbackId, evaluation, evaluatorId, comments, feedbackDate); // Assuming Feedback constructor handles all parameters
            } else {
                return null;  // Return null if no feedback is found with the provided ID
            }
        }
    }

    // Helper method to get Evaluation object by ID (adjust based on your actual schema)
    private Evaluation getEvaluationById(int evaluationId) throws SQLException {
        // Implement logic to retrieve an Evaluation object using evaluationId, for example:
        String sql = "SELECT * FROM evaluation WHERE evaluation_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, evaluationId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Thesis thesis = new Thesis(resultSet.getInt("thesis_id"), "Thesis Title", "Status"); // You can adjust this based on your schema
                return new Evaluation(thesis, evaluationId, resultSet.getInt("evaluator_id"), resultSet.getInt("score"), resultSet.getString("comments"));
            } else {
                return null;
            }
        }
    }
}



































package org.example.thesis_management_system.BuisnessLogic;

import org.example.thesis_management_system.DAO.EvaluationDAO;
import org.example.thesis_management_system.DAO.ThesisDAO;
import org.example.thesis_management_system.DAO.UserDAO;
import org.example.thesis_management_system.Models.Evaluation;
import org.example.thesis_management_system.Models.Thesis;
import org.example.thesis_management_system.Models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ScheduleMidtermEvaluation {

    private final Connection connection;
    private final UserDAO userDAO;
    private final EvaluationDAO evaluationDAO;
    private final ThesisDAO thesisDAO;

    public ScheduleMidtermEvaluation(Connection connection) throws SQLException {
        this.connection = connection;
        this.userDAO = new UserDAO(connection);
        this.evaluationDAO = new EvaluationDAO();
        this.thesisDAO = new ThesisDAO();
    }

    // Method to schedule the midterm evaluation
    public boolean scheduleMidtermEvaluation(int thesisId, int evaluatorId, String dateTime) throws SQLException {
        // Retrieve thesis and evaluator details
        Thesis thesis = thesisDAO.getThesisById(thesisId);
        User evaluator = userDAO.getEvaluatorById(evaluatorId);

        if (thesis != null && evaluator != null) {
            // Create a new Evaluation object for midterm
            Evaluation evaluation = new Evaluation(thesis, evaluatorId, 0, "Midterm Evaluation scheduled");
            evaluationDAO.saveEvaluation(evaluation);  // Save the evaluation

            // Add the scheduled date and time to the evaluation
            updateSchedule(evaluation.getEvaluationId(), dateTime);

            // Successfully scheduled
            return true;
        }
        // Thesis or evaluator not found
        return false;
    }

    // Method to retrieve available dates for scheduling midterm evaluation
    public List<String> retrieveAvailableDates() throws SQLException {
        // This would query the database or system for available slots
        String query = "SELECT available_date FROM evaluation_slots WHERE is_available = 1";
        List<String> availableDates = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                availableDates.add(resultSet.getString("available_date"));
            }
        }
        return availableDates;
    }

    // Method for the supervisor to select a date and time for the evaluation
    public void selectDateTime(int evaluationId, String dateTime) throws SQLException {
        // Check if the selected date/time is available
        if (isDateTimeAvailable(dateTime)) {
            // Update the schedule with the selected date/time
            updateSchedule(evaluationId, dateTime);
            System.out.println("Date and time updated successfully.");
        } else {
            System.out.println("The selected date/time is not available.");
        }
    }

    // Method to check if the selected date and time is available
    private boolean isDateTimeAvailable(String dateTime) throws SQLException {
        String query = "SELECT is_available FROM evaluation_slots WHERE available_date = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, dateTime);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getBoolean("is_available");
                }
            }
        }
        return false;
    }

    // Method to update the schedule for the midterm evaluation
    public void updateSchedule(int evaluationId, String dateTime) throws SQLException {
        String query = "UPDATE evaluations SET evaluation_date = ? WHERE evaluation_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, dateTime);
            statement.setInt(2, evaluationId);
            statement.executeUpdate();
        }
    }
}

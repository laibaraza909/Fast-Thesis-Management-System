package org.example.thesis_management_system.Models;

import org.example.thesis_management_system.DAO.UserDAO;
import org.example.thesis_management_system.Utils.DatabaseConnection;

import java.sql.*;

import java.sql.*;

public class Evaluation {
    private Thesis thesis;
    private int evaluationId;
    private int evaluatorId;
    private int score;
    private String comments;

    // Constructor that takes a Thesis object
    public Evaluation(Thesis thesis, int evaluationId, int evaluatorId, int score, String comments) {
        this.thesis = thesis; // Set the thesis reference
        this.evaluationId = evaluationId;
        this.evaluatorId = evaluatorId;
        this.score = score;
        this.comments = comments;
    }

    // Constructor with 4 parameters (new constructor)
    public Evaluation(Thesis thesis, int evaluatorId, int score, String comments) {
        this.thesis = thesis;
        this.evaluationId = 0;  // Default value for auto-generated ID
        this.evaluatorId = evaluatorId;
        this.score = score;
        this.comments = comments;
    }

    // Constructor that takes evaluationId, thesis, evaluator, score, and comments
    public Evaluation(int evaluationId, Thesis thesis, User evaluator, int score, String comments) {
        this.evaluationId = evaluationId;
        this.thesis = thesis;
        this.evaluatorId = evaluator.getId();  // Assuming User has a getUserId() method
        this.score = score;
        this.comments = comments;
    }

    // Default constructor
    public Evaluation() {}

    // Constructor that takes evaluationId, thesisId, evaluatorId, score, and comments
    public Evaluation(int evaluationId, int thesisId, int evaluatorId, int score, String comments) {
        this.evaluationId = evaluationId;
        this.thesis = new Thesis(); // Assuming Thesis class has a default constructor
        this.thesis.setThesisId(thesisId);  // Set the Thesis ID
        this.evaluatorId = evaluatorId;
        this.score = score;
        this.comments = comments;
    }



    // Getters and Setters
    public Thesis getThesis() {
        return thesis;
    }

    public void setThesis(Thesis thesis) {
        this.thesis = thesis;
    }

    public int getEvaluationId() {
        return evaluationId;
    }

    // Method to get Proposal ID via Thesis
    public int getProposalId() {
        if (this.thesis != null) {
            return this.thesis.getProposalId();  // Get Proposal ID from Thesis
        }
        return -1;  // Or handle null case as needed
    }

    public void setEvaluationId(int evaluationId) {
        this.evaluationId = evaluationId;
    }

    public int getEvaluatorId() {
        return evaluatorId;
    }

    public void setEvaluatorId(int evaluatorId) {
        this.evaluatorId = evaluatorId;
    }

    public int getThesisId() {
        return thesis.getThesisId(); // Assuming thesis is a Thesis object with a method getThesisId()
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    // Correctly defined getEvaluator method
    public User getEvaluator() throws SQLException {
        UserDAO userDAO = new UserDAO(DatabaseConnection.getConnection()); // Assuming you have a DatabaseConnection class to manage DB connections
        try {
            return userDAO.getEvaluatorById(this.evaluatorId); // Fetch evaluator by evaluatorId
        } catch (SQLException e) {
            e.printStackTrace(); // Handle error if necessary
            return null;
        }
    }

    // Validate the Evaluation (e.g., ensuring score is within valid range)
    public boolean isValid() {
        // Example validation, could be customized
        return this.score >= 0 && this.score <= 100;  // Ensuring score is between 0 and 100
    }


}


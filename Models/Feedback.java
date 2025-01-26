package org.example.thesis_management_system.Models;

import java.sql.Date;  // For SQL Date type

public class Feedback {
    private int feedbackId;
    private Evaluation evaluation;  // Reference to Evaluation object
    private int evaluatorId;
    private String comments;
    private Date feedbackDate;

    // Constructor to initialize the Feedback object
    public Feedback(int feedbackId, Evaluation evaluation, int evaluatorId, String comments, Date feedbackDate) {
        this.feedbackId = feedbackId;
        this.evaluation = evaluation;  // Set Evaluation object
        this.evaluatorId = evaluatorId;
        this.comments = comments;
        this.feedbackDate = feedbackDate;
    }

    // Constructor with a feedbackId, comments, and score (optional)
    public Feedback(int feedbackId, String comments, int score) {
        this.feedbackId = feedbackId;
        this.comments = comments;
        // Here, you might need to set a default score or handle it in some other way
    }

    public Feedback(int feedbackId, int evaluatorId, String comments, Date feedbackDate) {
    }

    // Getters and Setters
    public int getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
    }

    public Evaluation getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Evaluation evaluation) {
        this.evaluation = evaluation;
    }

    public int getEvaluatorId() {
        return evaluatorId;
    }

    public void setEvaluatorId(int evaluatorId) {
        this.evaluatorId = evaluatorId;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Date getFeedbackDate() {
        return feedbackDate;
    }

    public void setFeedbackDate(Date feedbackDate) {
        this.feedbackDate = feedbackDate;
    }

    // Method to get the Evaluation ID from the Evaluation object
    public int getEvaluationId() {
        return evaluation.getEvaluationId();  // Assuming the Evaluation class has this method
    }

    // Method to get the score from the Evaluation object
    public int getScore() {
        return evaluation.getScore();  // Assuming the Evaluation class has this method
    }

    public boolean isMidtermFeedback() {
        return false;
    }
}

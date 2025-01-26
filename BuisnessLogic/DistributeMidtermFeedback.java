package org.example.thesis_management_system.BuisnessLogic;

import org.example.thesis_management_system.DAO.FeedbackDAO;
import org.example.thesis_management_system.Models.Feedback;

import java.sql.SQLException;
import java.util.List;

public class DistributeMidtermFeedback {

    private FeedbackDAO feedbackDAO;  // Assuming you have a DAO class for feedback

    public DistributeMidtermFeedback() {
    }

    // Method to distribute midterm feedback
    public void distributeMidtermFeedback() {
        try {
            // Fetch all feedbacks that are related to midterm evaluation (you may filter by date or other attributes)
            List<Feedback> midtermFeedbacks = feedbackDAO.getMidtermFeedbacks(); // Assuming a method to filter midterm feedback
            for (Feedback feedback : midtermFeedbacks) {
                // Logic to distribute midterm feedback (e.g., print to console or save to a file)
                System.out.println("Distributing midterm feedback for Thesis ID: " + feedback.getEvaluation().getThesis().getThesisId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error distributing midterm feedback: " + e.getMessage());
        }
    }

    // Method to retrieve specific midterm feedback details
    public Feedback retrieveMidtermFeedbackDetails(int feedbackId) {
        try {
            // Retrieve specific midterm feedback from the database
            Feedback feedback = feedbackDAO.getFeedbackById(feedbackId);
            // Additional check to ensure it's midterm feedback (based on some criteria like timestamp)
            if (feedback != null && feedback.isMidtermFeedback()) {
                System.out.println("Retrieved midterm feedback for Thesis ID: " + feedback.getEvaluation().getThesis().getThesisId());
                return feedback;
            } else {
                System.err.println("Feedback is not midterm feedback.");
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error retrieving midterm feedback details: " + e.getMessage());
            return null;
        }
    }





    // Method to submit midterm feedback to the system
    public void submitMidtermFeedback(Feedback feedback) {
        try {
            // Save the midterm feedback to the system using the FeedbackDAO
            feedbackDAO.saveMidtermFeedback(feedback); // Assuming a method to handle midterm feedback saving
            System.out.println("Submitted midterm feedback for Thesis ID: " + feedback.getEvaluation().getThesis().getThesisId());
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error submitting midterm feedback: " + e.getMessage());
        }
    }

    // Method to save midterm feedback to the system
    public void saveMidtermFeedback(Feedback feedback) {
        try {
            // Save the midterm feedback using the DAO
            feedbackDAO.saveMidtermFeedback(feedback); // Assuming a method to save midterm feedback
            System.out.println("Saved midterm feedback for Thesis ID: " + feedback.getEvaluation().getThesis().getThesisId());
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error saving midterm feedback: " + e.getMessage());
        }
    }
}

package org.example.thesis_management_system.BuisnessLogic;

import org.example.thesis_management_system.DAO.FeedbackDAO;
import org.example.thesis_management_system.Models.Feedback;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

public class DistributeFeedback {

    private final FeedbackDAO feedbackDAO;
    private static final Logger logger = Logger.getLogger(DistributeFeedback.class.getName()); // Logger for better logging

    // Constructor to initialize required dependencies
    public DistributeFeedback(FeedbackDAO feedbackDAO) {
        this.feedbackDAO = feedbackDAO;
    }

    // Distribute feedback on the proposal defense
    public void distributeFeedback(int proposalId) throws SQLException {
        // Retrieve feedback for the proposal
        List<Feedback> feedbackList = feedbackDAO.getFeedbackByProposalId(proposalId);

        // Check if feedback list is empty
        if (feedbackList.isEmpty()) {
            logger.warning("No feedback available for Proposal ID: " + proposalId);
            return;
        }

        // Loop through feedback and save it
        for (Feedback feedback : feedbackList) {
            saveFeedback(feedback);
            // Optional: Add notification logic here (e.g., email notification)
        }
    }

    // Retrieve feedback details by proposalId
    public List<Feedback> retrieveFeedbackDetails(int proposalId) throws SQLException {
        // Logic to retrieve feedback details for a given proposal ID
        List<Feedback> feedbackList = feedbackDAO.getFeedbackByProposalId(proposalId);

        // Check if no feedback was found
        if (feedbackList.isEmpty()) {
            logger.warning("No feedback found for Proposal ID: " + proposalId);
        }
        return feedbackList;
    }

    // Submit feedback to the system (e.g., from a supervisor or panel)
    public void submitFeedback(Feedback feedback) {
        try {
            // Logic to submit feedback to the system
            feedbackDAO.saveFeedback(feedback);
            logger.info("Feedback submitted successfully for Proposal ID: " + feedback.getEvaluation().getProposalId());
        } catch (SQLException e)
        {
            logger.severe("Error submitting feedback: " + e.getMessage());
        }
    }


    // Save the feedback to the system
    private void saveFeedback(Feedback feedback) {
        try {
            // Logic to save the feedback to the database
            feedbackDAO.saveFeedback(feedback);
            logger.info("Feedback saved for Proposal ID: " + feedback.getEvaluation().getProposalId());
        } catch (SQLException e) {
            logger.severe("Error saving feedback for Proposal ID " + feedback.getEvaluation().getProposalId() + ": " + e.getMessage());
        }
    }
}

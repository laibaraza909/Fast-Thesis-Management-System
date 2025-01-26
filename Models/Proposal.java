package org.example.thesis_management_system.Models;

import java.util.Date;
import java.util.List;  // Import List for storing multiple Feedbacks

public class Proposal {
    private int proposalId;
    private String consentForm;
    private Date defenseDate;
    private List<Feedback> feedbackList;  // List of Feedback objects

    // Constructor
    public Proposal(int proposalId, String consentForm, Date defenseDate, List<Feedback> feedbackList) {
        this.proposalId = proposalId;
        this.consentForm = consentForm;
        this.defenseDate = defenseDate;
        this.feedbackList = feedbackList;
    }

    public Proposal(int proposalId, int thesisId, String consentForm, java.sql.Date defenseDate, String feedback) {

    }

    public Proposal(int proposalId) {
    }

    // Getters and Setters
    public int getProposalId() {
        return proposalId;
    }

    public void setProposalId(int proposalId) {
        this.proposalId = proposalId;
    }

    public String getConsentForm() {
        return consentForm;
    }

    public void setConsentForm(String consentForm) {
        this.consentForm = consentForm;
    }

    public Date getDefenseDate() {
        return defenseDate;
    }

    public void setDefenseDate(Date defenseDate) {
        this.defenseDate = defenseDate;
    }

    public List<Feedback> getFeedbackList() {
        return feedbackList;
    }

    public void setFeedbackList(List<Feedback> feedbackList) {
        this.feedbackList = feedbackList;
    }

    // Add a method to add feedback to the proposal
    public void addFeedback(Feedback feedback) {
        this.feedbackList.add(feedback);
    }

    // Add a method to remove feedback from the proposal
    public void removeFeedback(Feedback feedback) {
        this.feedbackList.remove(feedback);
    }
}

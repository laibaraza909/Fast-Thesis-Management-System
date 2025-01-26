package org.example.thesis_management_system.Models;

import java.util.List;


    public class Thesis
    {
    private int thesisId;
    private Proposal proposal; // Reference to Proposal object
    private String title;
    private String status;
    private String finalReport;
    private String revisedThesis;
    private List<Evaluation> evaluations; // A thesis can have multiple evaluations

    // Constructor with Proposal object
    public Thesis(int thesisId, Proposal proposal, String title, String status, String finalReport, String revisedThesis) {
        this.thesisId = thesisId;
        this.proposal = proposal;  // Assign Proposal object
        this.title = title;
        this.status = status;
        this.finalReport = finalReport;
        this.revisedThesis = revisedThesis;
    }

    // Constructor with Proposal ID and Thesis details
    public Thesis(int thesisId, int proposalId, String title, String status, String finalReport, String revisedThesis) {
        this.thesisId = thesisId;
        this.proposal = new Proposal(proposalId);  // Create Proposal object from ID
        this.title = title;
        this.status = status;
        this.finalReport = finalReport;
        this.revisedThesis = revisedThesis;
    }

    public Thesis(int thesisId, String thesisTitle, String thesisStatus) {
    }

    public Thesis(int thesisId, int studentId, int supervisorId, String title, String status) {
    }

        public Thesis() {

        }

        public Thesis(int thesisId, int studentId, int supervisorId, String title, String status, String finalReport, String revisedThesis) {
        }

        // Getter for Thesis ID
    public int getThesisId() {
        return thesisId;
    }

    public void setThesisId(int thesisId) {
        this.thesisId = thesisId;
    }

    // Getter and Setter for Proposal object
    public Proposal getProposal() {
        return proposal;
    }

    public void setProposal(Proposal proposal) {
        this.proposal = proposal;
    }

    // Getter for Proposal ID (to access proposalId)
    public int getProposalId() {
        return proposal.getProposalId();  // Access the Proposal object's proposalId
    }

    // Other getters and setters for title, status, etc.
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFinalReport() {
        return finalReport;
    }

    public void setFinalReport(String finalReport) {
        this.finalReport = finalReport;
    }

    public String getRevisedThesis() {
        return revisedThesis;
    }

    public void setRevisedThesis(String revisedThesis) {
        this.revisedThesis = revisedThesis;
    }
}

package org.example.thesis_management_system.BuisnessLogic;
import org.example.thesis_management_system.DAO.ProposalDAO;
import org.example.thesis_management_system.Models.Proposal;

import java.sql.SQLException;
import java.util.Date;

public class SubmitThesisProposal {

    private final ProposalDAO proposalDAO;

    // Constructor to initialize DAO
    public SubmitThesisProposal(ProposalDAO proposalDAO) {
        this.proposalDAO = proposalDAO;
    }

    // Request proposal upload
    public void requestProposalUpload(int thesisId) {
        System.out.println("Requesting upload for thesis ID: " + thesisId);
        // Placeholder for actual upload process
    }

    // Validate document
    private boolean validateDocument(String documentPath) {
        System.out.println("Validating document: " + documentPath);
        return documentPath != null && documentPath.endsWith(".pdf");
    }

    // Submit thesis proposal
    public void submitThesisProposal(int thesisId, String consentForm, Date defenseDate, String feedback, String documentPath) throws SQLException {
        System.out.println("Submitting thesis proposal...");

        // Validate document
        if (!validateDocument(documentPath)) {
            System.out.println("Document validation failed. Ensure the file is a PDF.");
            return;
        }

        // Save proposal to database
        proposalDAO.addProposal(thesisId, consentForm, new java.sql.Date(defenseDate.getTime()), feedback);
        System.out.println("Proposal submitted successfully!");

        // Notify supervisor and send confirmation
        notifySupervisor(thesisId);
        sendConfirmation(thesisId);
    }

    // Notify supervisor
    private void notifySupervisor(int thesisId) {
        System.out.println("Notifying supervisor for thesis ID: " + thesisId);
        // Add logic to notify supervisor, e.g., via email or message
    }

    // Send confirmation
    private void sendConfirmation(int thesisId) {
        System.out.println("Sending confirmation for thesis ID: " + thesisId);
        // Add logic to send confirmation, e.g., via email or message
    }
}

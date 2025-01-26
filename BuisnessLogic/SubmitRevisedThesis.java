package org.example.thesis_management_system.BuisnessLogic;



import org.example.thesis_management_system.DAO.ThesisDAO;

import java.sql.SQLException;

public class SubmitRevisedThesis {

    private final ThesisDAO thesisDAO;

    public SubmitRevisedThesis() throws SQLException {
        // Initialize ThesisDAO
        this.thesisDAO = new ThesisDAO();
    }

    // Method to initiate the process of submitting a revised thesis
    public void submitRevisedThesis(int thesisId, String documentContent) {
        // Step 1: Request revised thesis document upload
        requestRevisedUpload();

        // Step 2: Upload the document
        String uploadedDocument = uploadDocument(documentContent);

        // Step 3: Validate the uploaded document
        if (validateDocument(uploadedDocument)) {
            // Step 4: Save the revised thesis in the database
            boolean isSaved = saveRevisedThesis(thesisId, uploadedDocument);
            if (isSaved) {
                System.out.println("Revised thesis submitted successfully!");
            } else {
                System.out.println("Failed to save the revised thesis.");
            }
        } else {
            System.out.println("Invalid document format.");
        }
    }

    // Method to request the upload of the revised thesis
    private void requestRevisedUpload() {
        System.out.println("Please upload the revised thesis document.");
    }

    // Method to simulate uploading the document and returning its content
    private String uploadDocument(String documentContent) {
        System.out.println("Uploading the revised thesis document...");
        return documentContent;
    }

    // Method to validate the document (check for non-empty content as an example)
    private boolean validateDocument(String document) {
        return document != null && !document.trim().isEmpty();
    }

    // Method to save the revised thesis into the database using ThesisDAO
    private boolean saveRevisedThesis(int thesisId, String revisedDocument) {
        return thesisDAO.saveRevisedThesis(thesisId, revisedDocument);
    }
}


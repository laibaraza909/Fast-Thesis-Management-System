package org.example.thesis_management_system.BuisnessLogic;
import org.example.thesis_management_system.DAO.ConsentFormDAO;
import org.example.thesis_management_system.Models.ConsentForm;
import java.sql.Connection;
import java.sql.Date;

public class ConsentFormService {
    private final ConsentFormDAO consentFormDAO;

    // Constructor initializes the DAO with a database connection
    public ConsentFormService(Connection connection) {
        this.consentFormDAO = new ConsentFormDAO(connection);
    }

    // Business logic for submitting a consent form
    public boolean submitConsentForm(int studentId, int proposalId, String consentFormFile) {
        try {
            // Request consent form upload
            System.out.println("Requesting consent form upload...");

            // Validate the consent form file
            if (!validateConsentForm(consentFormFile)) {
                System.out.println("Consent form validation failed.");
                return false;
            }

            // Save the consent form details to the database
            ConsentForm consentForm = new ConsentForm(studentId, proposalId, consentFormFile, new Date(System.currentTimeMillis()));
            consentFormDAO.saveConsentForm(consentForm);

            // Send confirmation to the student
            sendConfirmation(studentId);
            System.out.println("Consent form submitted successfully.");
            return true;
        } catch (Exception e) {
            System.out.println("Error submitting consent form: " + e.getMessage());
            e.printStackTrace();  // Provide detailed error output
            return false;
        }
    }

    // Validate the consent form
    private boolean validateConsentForm(String consentFormFile) {
        // Example validation: check if the file ends with ".pdf"
        return consentFormFile != null && consentFormFile.endsWith(".pdf");
    }

    // Send confirmation to the student
    private void sendConfirmation(int studentId) {
        System.out.println("Confirmation sent to Student ID: " + studentId);
        // Additional logic for actually sending the confirmation (e.g., email) can be added here
    }
}



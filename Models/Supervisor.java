package org.example.thesis_management_system.Models;

import org.example.thesis_management_system.BuisnessLogic.*;
import org.example.thesis_management_system.DAO.*;

import java.sql.Connection;
import java.sql.SQLException;

public class Supervisor extends User
{

    private String department;
    private ThesisDAO thesisDAO;
    private EvaluationDAO evaluationDAO;
    private FeedbackDAO feedbackDAO;
    private Connection connection;
    private GradeDAO gradeDAO;

    public Supervisor(GradeDAO gradeDAO) {
        this.gradeDAO = gradeDAO;
    }
    // Constructor to initialize the Supervisor object with necessary dependencies
    public Supervisor(int id, String username, String password, String department, ThesisDAO thesisDAO, EvaluationDAO evaluationDAO, FeedbackDAO feedbackDAO, Connection connection) {
        super(id, username, password, "supervisor"); // Call the superclass constructor
        this.department = department;
        this.thesisDAO = thesisDAO;
        this.evaluationDAO = evaluationDAO;
        this.feedbackDAO = feedbackDAO;
        this.connection = connection; // Initialize the connection
    }

    // Empty constructor to be used if necessary, make sure to handle initialization
    public Supervisor(int supervisorId, int userId, String department, String username, String password, Connection connection) {
        super(userId, username, password, "supervisor");
        this.department = department;
        this.connection = connection; // Initialize connection
    }

    public Supervisor(int supervisorId, int userId, String department, String username, String password) {
    }

    // Method to evaluate the thesis proposal defense
    public void evaluateProposalDefense(int thesisId) {
        Thesis thesis = thesisDAO.getThesisById(thesisId);
        if (thesis == null) {
            System.out.println("Error: Thesis not found!");
            return; // Exit if thesis is not found
        }

        Evaluation evaluation = new Evaluation();
        EvaluateProposalDefense proposalDefense = new EvaluateProposalDefense(thesis, evaluation, thesisDAO, evaluationDAO);
        proposalDefense.evaluateProposalDefense();
    }

    // Method to distribute feedback for a proposal
    public void distributeFeedback(int proposalId) throws SQLException {
        new DistributeFeedback(feedbackDAO).distributeFeedback(proposalId);
    }

    // Method to schedule the midterm evaluation
    public void scheduleMidtermEvaluation(int thesisId, int evaluatorId, String dateTime) {
        try {
            // Create an instance of ScheduleMidtermEvaluation with the connection
            ScheduleMidtermEvaluation scheduler = new ScheduleMidtermEvaluation(connection);

            // Schedule the evaluation
            boolean result = scheduler.scheduleMidtermEvaluation(thesisId, evaluatorId, dateTime);

            if (result) {
                System.out.println("Midterm Evaluation Scheduled successfully.");
            } else {
                System.out.println("Failed to schedule the midterm evaluation.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error while scheduling midterm evaluation.");
        }
    }

    // Method to distribute midterm feedback (ensuring proper authorization)
    public void distributeMidtermFeedback(User currentUser) {
        if (currentUser.isSupervisor()) {
            new DistributeMidtermFeedback().distributeMidtermFeedback(); // Assuming this method exists
        } else {
            System.out.println("You do not have permission to distribute feedback.");
        }
    }

    // Method to evaluate the final report of a thesis
    public void evaluateFinalReport(int reportId) throws SQLException {
        EvaluateFinalReport evaluateFinalReport = new EvaluateFinalReport();

        // Fetch report details first
        evaluateFinalReport.retrieveReportDetails(reportId);

        // Proceed with final report evaluation
        evaluateFinalReport.evaluateFinalReport(reportId);
    }


    // Method to handle final grades upload
    public void uploadFinalGrades() {
        UploadFinalGrade uploadFinalGrade = new UploadFinalGrade(gradeDAO);
        uploadFinalGrade.uploadFinalGrades();
    }


    // Method to announce final report submission details
    public void announceFinalReportSubmission(String message, String deadline, int createdBy, int thesisId, AnnouncementDAO announcementDAO) {
        AnnounceFinalReportSubmission announceService = new AnnounceFinalReportSubmission(announcementDAO);
        announceService.announceFinalReportSubmission(message, deadline, createdBy, thesisId);
    }

    // Overridden login method to validate and log supervisor login
    @Override
    public void login(String username, String password) {
        if (username.equals(getUsername()) && password.equals(getPassword())) {
            System.out.println(username + " has logged in as a supervisor.");
            long loginTime = System.currentTimeMillis();
            System.out.println("Login time: " + loginTime);
        } else {
            System.out.println("Invalid credentials! Login failed.");
        }
    }

    // Getter for department
    public String getDepartment() {
        return department;
    }

    // Setter for department (if needed for any updates in the department)
    public void setDepartment(String department) {
        this.department = department;
    }
}

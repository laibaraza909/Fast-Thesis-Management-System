package org.example.thesis_management_system.BuisnessLogic;
import org.example.thesis_management_system.DAO.EvaluationDAO; // Assuming this is where your DAO is located
import org.example.thesis_management_system.DAO.ReportDAO;
import org.example.thesis_management_system.Models.Evaluation; // Assuming you have an Evaluation model class



import org.example.thesis_management_system.DAO.EvaluationDAO;
import org.example.thesis_management_system.DAO.ReportDAO;
import org.example.thesis_management_system.Models.Evaluation;
import org.example.thesis_management_system.Models.Report;

public class EvaluateFinalReport {

    private EvaluationDAO evaluationDAO; // DAO for Evaluation
    private ReportDAO reportsDAO;      // DAO for Reports

    // Constructor
    public EvaluateFinalReport() {
        this.evaluationDAO = new EvaluationDAO(); // Instantiate Evaluation DAO
        this.reportsDAO = new ReportDAO();       // Instantiate Reports DAO
    }

    // Evaluate the final report
    public void evaluateFinalReport(int reportId) {
        // Retrieve the report using ReportsDAO
        Report report = reportsDAO.getReportById(reportId);

        if (report != null) {
            System.out.println("Evaluating report: " + reportId);

            // Retrieve evaluation by report ID
            Evaluation evaluation = evaluationDAO.getEvaluationByReportId(reportId);

            if (evaluation == null) {
                System.out.println("No evaluation found for report ID " + reportId);
                return;
            }

            // Perform evaluation logic (dummy example here)
            if (report.getContent().length() > 100) {
                System.out.println("Report content is sufficient for evaluation.");
            } else {
                System.out.println("Report content is insufficient.");
            }
        } else {
            System.out.println("Report ID " + reportId + " not found.");
        }
    }

    // Supervisor fills out the evaluation form
    public void fillEvaluationForm(Evaluation evaluation) {
        if (evaluation != null) {
            evaluationDAO.saveEvaluation(evaluation); // Save to Evaluation table
            System.out.println("Evaluation form saved.");
        } else {
            System.out.println("Evaluation form is empty!");
        }
    }

    // Validate the evaluation form
    public void validateEvaluationForm(Evaluation evaluation) {
        if (evaluation != null && evaluation.isValid()) {
            System.out.println("Evaluation form is valid.");
            saveEvaluation(evaluation);
        } else {
            System.out.println("Invalid evaluation form!");
        }
    }

    // Save the evaluation
    public void saveEvaluation(Evaluation evaluation) {
        if (evaluation != null) {
            evaluationDAO.saveEvaluation(evaluation); // Save to database
            System.out.println("Evaluation saved successfully.");
        } else {
            System.out.println("Error: Cannot save empty evaluation.");
        }
    }

    // Retrieve report details
    public void retrieveReportDetails(int reportId) {
        Report report = reportsDAO.getReportById(reportId);
        if (report != null) {
            System.out.println("Report details for ID: " + reportId);
            System.out.println("Content: " + report.getContent());
            System.out.println("Submission Date: " + report.getSubmissionDate());
        } else {
            System.out.println("Report details not found for ID: " + reportId);
        }
    }
}


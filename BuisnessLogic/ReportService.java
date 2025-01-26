package org.example.thesis_management_system.BuisnessLogic;
import org.example.thesis_management_system.DAO.ReportDAO;
import org.example.thesis_management_system.Models.Report;
import java.time.LocalDate;
import java.util.List;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReportService   // submit final report by student
{


    private static final Logger logger = Logger.getLogger(ReportService.class.getName());
    private final ReportDAO reportDAO;

    // Directory to store uploaded reports
    private static final String REPORTS_DIR = "C:/reports/";

    // Constructor to initialize ReportDAO
    public ReportService() {
        this.reportDAO = new ReportDAO();
    }

    // Add a new report with validation
    public void addReport(int thesisId, String content, LocalDate submissionDate, String reportFilePath) {
        try {
            // Validate content
            if (content == null || content.isEmpty()) {
                throw new IllegalArgumentException("Content cannot be null or empty.");
            }

            // Validate submission date
            if (submissionDate.isAfter(LocalDate.now())) {
                throw new IllegalArgumentException("Submission date cannot be in the future.");
            }

            // Handle file upload
            String uploadedFilePath = handleFileUpload(reportFilePath);

            // Save report with the file path
            Report report = new Report(0, thesisId, content, submissionDate);
            report.setReportFilePath(uploadedFilePath);  // Store the file path in the report
            reportDAO.addReport(report);

            logger.info("Report added successfully for thesis ID: " + thesisId);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error adding report: " + e.getMessage(), e);
            throw new RuntimeException("Error adding report: " + e.getMessage(), e);
        }
    }

    // Retrieve a report by ID with validation
    public Report getReportById(int reportId) {
        try {
            if (reportId <= 0) {
                throw new IllegalArgumentException("Report ID must be a positive integer.");
            }

            Report report = reportDAO.getReportById(reportId);
            if (report == null) {
                throw new IllegalArgumentException("Report with the given ID does not exist.");
            }

            return report;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error retrieving report by ID: " + e.getMessage(), e);
            throw new RuntimeException("Error retrieving report by ID: " + e.getMessage(), e);
        }
    }

    // Retrieve all reports
    public List<Report> getAllReports() {
        try {
            return reportDAO.getAllReports();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error retrieving all reports: " + e.getMessage(), e);
            throw new RuntimeException("Error retrieving all reports: " + e.getMessage(), e);
        }
    }

    // Update an existing report
    public void updateReport(int reportId, int thesisId, String content, LocalDate submissionDate, String reportFilePath) {
        try {
            // Validate inputs
            if (reportId <= 0) {
                throw new IllegalArgumentException("Report ID must be a positive integer.");
            }
            if (content == null || content.isEmpty()) {
                throw new IllegalArgumentException("Content cannot be null or empty.");
            }
            if (submissionDate.isAfter(LocalDate.now())) {
                throw new IllegalArgumentException("Submission date cannot be in the future.");
            }

            // Handle file upload
            String uploadedFilePath = handleFileUpload(reportFilePath);

            // Update report with new content and file path
            Report report = new Report(reportId, thesisId, content, submissionDate);
            report.setReportFilePath(uploadedFilePath);  // Store the new file path
            reportDAO.updateReport(report);

            logger.info("Report updated successfully for report ID: " + reportId);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error updating report: " + e.getMessage(), e);
            throw new RuntimeException("Error updating report: " + e.getMessage(), e);
        }
    }

    // Delete a report by ID with validation
    public void deleteReport(int reportId) {
        try {
            if (reportId <= 0) {
                throw new IllegalArgumentException("Report ID must be a positive integer.");
            }

            Report report = reportDAO.getReportById(reportId);
            if (report == null) {
                throw new IllegalArgumentException("Report with the given ID does not exist.");
            }

            reportDAO.deleteReport(reportId);
            logger.info("Report with ID: " + reportId + " deleted successfully.");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error deleting report: " + e.getMessage(), e);
            throw new RuntimeException("Error deleting report: " + e.getMessage(), e);
        }
    }

    // Helper method to handle file upload
    private String handleFileUpload(String reportFilePath) throws IOException {
        if (reportFilePath == null || reportFilePath.isEmpty()) {
            throw new IllegalArgumentException("Report file path cannot be null or empty.");
        }

        Path sourcePath = Paths.get(reportFilePath);
        if (!Files.exists(sourcePath)) {
            throw new IllegalArgumentException("File does not exist at the provided path.");
        }

        // Generate a unique filename for storage
        String uniqueFileName = System.currentTimeMillis() + "_" + sourcePath.getFileName().toString();
        Path targetPath = Paths.get(REPORTS_DIR, uniqueFileName);

        // Create the target directory if it doesn't exist
        Files.createDirectories(targetPath.getParent());

        // Copy the file to the target directory
        Files.copy(sourcePath, targetPath);

        logger.info("Report file uploaded successfully: " + targetPath);
        return targetPath.toString();  // Return the path where the file is stored
    }
}



package org.example.thesis_management_system.Models;
import java.time.LocalDate;
import java.time.LocalDate;

public class Report {
    private int reportId;
    private int thesisId;
    private String content;
    private LocalDate submissionDate;
    private String reportFilePath; // Add field for report file path

    // Constructor
    public Report(int reportId, int thesisId, String content, LocalDate submissionDate) {
        this.reportId = reportId;
        this.thesisId = thesisId;
        this.content = content;
        this.submissionDate = submissionDate;
    }

    // Getters and Setters
    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    public int getThesisId() {
        return thesisId;
    }

    public void setThesisId(int thesisId) {
        this.thesisId = thesisId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(LocalDate submissionDate) {
        this.submissionDate = submissionDate;
    }

    // Getter for reportFilePath
    public String getReportFilePath() {
        return reportFilePath;
    }

    // Setter for reportFilePath
    public void setReportFilePath(String reportFilePath) {
        this.reportFilePath = reportFilePath;
    }
}


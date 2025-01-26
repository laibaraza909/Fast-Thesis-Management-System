package org.example.thesis_management_system.Models;
import java.sql.Date;

public class ConsentForm {
    private int consentFormId;
    private int studentId;
    private int proposalId;
    private String consentFormFile;
    private Date submissionDate;
    private String studentName;   // Added field for student name
    private String proposalTitle; // Added field for proposal title

    // Constructor for saving the consent form (without student and proposal details)
    public ConsentForm(int studentId, int proposalId, String consentFormFile, Date submissionDate) {
        this.studentId = studentId;
        this.proposalId = proposalId;
        this.consentFormFile = consentFormFile;
        this.submissionDate = submissionDate;
    }

    // Constructor for retrieving consent form with student and proposal details
    public ConsentForm(int consentFormId, int studentId, int proposalId, String consentFormFile, Date submissionDate, String studentName, String proposalTitle) {
        this.consentFormId = consentFormId;
        this.studentId = studentId;
        this.proposalId = proposalId;
        this.consentFormFile = consentFormFile;
        this.submissionDate = submissionDate;
        this.studentName = studentName;
        this.proposalTitle = proposalTitle;
    }

    // Constructor for retrieving consent form without student and proposal details
    public ConsentForm(int consentFormId, int studentId, int proposalId, String consentFormFile, Date submissionDate) {
        this.consentFormId = consentFormId;
        this.studentId = studentId;
        this.proposalId = proposalId;
        this.consentFormFile = consentFormFile;
        this.submissionDate = submissionDate;
    }

    // Getters and Setters
    public int getConsentFormId() {
        return consentFormId;
    }

    public void setConsentFormId(int consentFormId) {
        this.consentFormId = consentFormId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getProposalId() {
        return proposalId;
    }

    public void setProposalId(int proposalId) {
        this.proposalId = proposalId;
    }

    public String getConsentFormFile() {
        return consentFormFile;
    }

    public void setConsentFormFile(String consentFormFile) {
        this.consentFormFile = consentFormFile;
    }

    public Date getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(Date submissionDate) {
        this.submissionDate = submissionDate;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getProposalTitle() {
        return proposalTitle;
    }

    public void setProposalTitle(String proposalTitle) {
        this.proposalTitle = proposalTitle;
    }
}

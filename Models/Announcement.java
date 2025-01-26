package org.example.thesis_management_system.Models;

public class Announcement {
    private int id;
    private String message;
    private String deadline;
    private int createdBy;
    private int thesisId;

    public Announcement(String message, String deadline, int createdBy, int thesisId) {
        this.message = message;
        this.deadline = deadline;
        this.createdBy = createdBy;
        this.thesisId = thesisId;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public int getThesisId() {
        return thesisId;
    }

    public void setThesisId(int thesisId) {
        this.thesisId = thesisId;
    }
}

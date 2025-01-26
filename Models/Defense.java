package org.example.thesis_management_system.Models;


import java.sql.Date;

public class Defense {
    private int defenseId;
    private Date defenseDate;
    private String feedback;
    private String result;
    private int scheduledBy;

    // Constructor
    public Defense(int defenseId, Date defenseDate, String feedback, String result, int scheduledBy) {
        this.defenseId = defenseId;
        this.defenseDate = defenseDate;
        this.feedback = feedback;
        this.result = result;
        this.scheduledBy = scheduledBy;
    }

    public Defense(int defenseId, int studentId, int panelId, Date defenseDate, String feedback, String result, int scheduledBy) {
    }

    // Getters and Setters
    public int getDefenseId() {
        return defenseId;
    }

    public void setDefenseId(int defenseId) {
        this.defenseId = defenseId;
    }

    public Date getDefenseDate() {
        return defenseDate;
    }

    public void setDefenseDate(Date defenseDate) {
        this.defenseDate = defenseDate;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getScheduledBy() {
        return scheduledBy;
    }

    public void setScheduledBy(int scheduledBy) {
        this.scheduledBy = scheduledBy;
    }

    @Override
    public String toString() {
        return "Defense{" +
                "defenseId=" + defenseId +
                ", defenseDate=" + defenseDate +
                ", feedback='" + feedback + '\'' +
                ", result='" + result + '\'' +
                ", scheduledBy=" + scheduledBy +
                '}';
    }
}

package org.example.thesis_management_system.BuisnessLogic;

import org.example.thesis_management_system.DAO.AnnouncementDAO;
import org.example.thesis_management_system.Models.Announcement;
import org.example.thesis_management_system.Models.Student;

import java.util.List;

public class AnnounceFinalReportSubmission
{
    private final AnnouncementDAO announcementDAO;

    public AnnounceFinalReportSubmission(AnnouncementDAO announcementDAO) {
        this.announcementDAO = announcementDAO;
    }

    // Announce final report submission
    public void announceFinalReportSubmission(String message, String deadline, int createdBy, int thesisId) {
        Announcement announcement = new Announcement(message, deadline, createdBy, thesisId);
        announcementDAO.saveAnnouncement(announcement);
        notifyStudents(thesisId, message);
    }

    // Update the deadline of an announcement
    public void updateSubmissionDetails(int announcementId, String newDeadline) {
        announcementDAO.updateAnnouncementDeadline(announcementId, newDeadline);
    }

    // Notify students
    public void notifyStudents(int thesisId, String message) {
        List<Student> students = announcementDAO.getStudentsForNotification(thesisId);
        for (Student student : students) {
            System.out.println("Notifying student: " + student.getEmail() + " with message: " + message);
        }
    }
}

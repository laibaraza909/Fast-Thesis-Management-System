package org.example.thesis_management_system.DAO;

import org.example.thesis_management_system.Models.Announcement;
import org.example.thesis_management_system.Models.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnnouncementDAO {
    private final Connection connection;

    public AnnouncementDAO(Connection connection) {
        this.connection = connection;
    }

    // Save announcement to database
    public void saveAnnouncement(Announcement announcement) {
        String query = "INSERT INTO announcements (message, deadline, created_by, thesis_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, announcement.getMessage());
            stmt.setString(2, announcement.getDeadline());
            stmt.setInt(3, announcement.getCreatedBy());
            stmt.setInt(4, announcement.getThesisId());
            stmt.executeUpdate();
            System.out.println("Announcement saved successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Update deadline for an existing announcement
    public void updateAnnouncementDeadline(int announcementId, String newDeadline) {
        String query = "UPDATE announcements SET deadline = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, newDeadline);
            stmt.setInt(2, announcementId);
            stmt.executeUpdate();
            System.out.println("Announcement updated successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Fetch students associated with a thesis
    public List<Student> getStudentsForNotification(int thesisId) {
        List<Student> students = new ArrayList<>();
        String query = "SELECT * FROM students WHERE thesis_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, thesisId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String email = rs.getString("email");
                students.add(new Student(id, email));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }
}

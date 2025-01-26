package org.example.thesis_management_system.DAO;



import org.example.thesis_management_system.Models.Report;
import org.example.thesis_management_system.Utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReportDAO {

    // Add a new report
    public void addReport(Report report) {
        String query = "INSERT INTO reports (thesis_id, content, submission_date) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, report.getThesisId());
            preparedStatement.setString(2, report.getContent());
            preparedStatement.setDate(3, Date.valueOf(report.getSubmissionDate()));

            preparedStatement.executeUpdate();
            System.out.println("Report added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Retrieve a report by ID
    public Report getReportById(int reportId) {
        String query = "SELECT * FROM reports WHERE report_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, reportId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return new Report(
                        resultSet.getInt("report_id"),
                        resultSet.getInt("thesis_id"),
                        resultSet.getString("content"),
                        resultSet.getDate("submission_date").toLocalDate()
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Retrieve all reports
    public List<Report> getAllReports() {
        String query = "SELECT * FROM reports";
        List<Report> reports = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                reports.add(new Report(
                        resultSet.getInt("report_id"),
                        resultSet.getInt("thesis_id"),
                        resultSet.getString("content"),
                        resultSet.getDate("submission_date").toLocalDate()
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reports;
    }

    // Update a report
    public void updateReport(Report report) {
        String query = "UPDATE reports SET thesis_id = ?, content = ?, submission_date = ? WHERE report_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, report.getThesisId());
            preparedStatement.setString(2, report.getContent());
            preparedStatement.setDate(3, Date.valueOf(report.getSubmissionDate()));
            preparedStatement.setInt(4, report.getReportId());

            preparedStatement.executeUpdate();
            System.out.println("Report updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete a report by ID
    public void deleteReport(int reportId) {
        String query = "DELETE FROM reports WHERE report_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, reportId);
            preparedStatement.executeUpdate();
            System.out.println("Report deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


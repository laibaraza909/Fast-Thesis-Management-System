package org.example.thesis_management_system.BuisnessLogic;

import org.example.thesis_management_system.DAO.GradeDAO;
import org.example.thesis_management_system.Models.Grade;
import org.example.thesis_management_system.Models.Thesis;

import java.io.*;
import java.sql.*;
import java.sql.Date;
import java.util.*;

public class UploadFinalGrade {

    private GradeDAO gradeDAO;

    // Constructor accepting GradeDAO
    public UploadFinalGrade(GradeDAO gradeDAO) {
        this.gradeDAO = gradeDAO;
    }

    // Method to request the grades file upload
    public void requestGradesFileUpload() {
        System.out.println("Please upload the grades file (CSV format).");
    }

    // Method to upload the grades file
    public File uploadFile() {
        // Simulating file upload (in a real app, use JavaFX file picker)
        File file = new File("grades.csv"); // Replace with actual file input
        if (file.exists()) {
            System.out.println("File uploaded successfully.");
        }
        return file;
    }

    // Method to validate the file format and contents
    public boolean validateFile(File file) {
        if (file == null || !file.exists()) {
            System.out.println("File not found.");
            return false;
        }

        if (!file.getName().endsWith(".csv")) {
            System.out.println("Invalid file format. Please upload a .csv file.");
            return false;
        }

        System.out.println("File validated successfully.");
        return true;
    }

    // Method to parse grades from the CSV file
    public List<Grade> parseGradesFromFile(File file) {
        List<Grade> grades = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    int thesisId = Integer.parseInt(parts[0].trim());
                    String grade = parts[1].trim();
                    Date gradeDate = Date.valueOf(parts[2].trim());

                    // Create Thesis object based on thesisId (assuming thesisId is available in your system)
                    Thesis thesis = new Thesis(thesisId, "Unknown Title", "Unknown Status");  // You can update with real data if needed

                    // Create Grade with Thesis object
                    grades.add(new Grade(0, thesis, grade, gradeDate));  // 0 for gradeId (auto-increment)
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading or parsing the file: " + e.getMessage());
        }
        return grades;
    }


    // Method to save grades into the database
    public void saveGradesToDatabase(List<Grade> grades) {
        for (Grade grade : grades) {
            try {
                // Accessing thesisId through the Thesis object in Grade
                int thesisId = grade.getThesis().getThesisId();
                gradeDAO.addGrade(thesisId, grade.getGrade(), grade.getGradeDate());
                System.out.println("Grade for Thesis ID " + thesisId + " saved successfully.");
            } catch (SQLException e) {
                System.out.println("Error saving grade for Thesis ID " + grade.getThesis().getThesisId() + ": " + e.getMessage());
            }
        }
    }


    // Main method to handle the complete upload process
    public void uploadFinalGrades() {
        requestGradesFileUpload();
        File file = uploadFile();

        if (!validateFile(file)) {
            return;
        }

        List<Grade> grades = parseGradesFromFile(file);
        saveGradesToDatabase(grades);

        System.out.println("Final grades uploaded and saved to the database successfully.");
    }
}

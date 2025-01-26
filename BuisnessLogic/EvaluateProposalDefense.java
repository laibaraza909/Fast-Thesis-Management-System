package org.example.thesis_management_system.BuisnessLogic;

import org.example.thesis_management_system.DAO.EvaluationDAO;
import org.example.thesis_management_system.DAO.ThesisDAO;
import org.example.thesis_management_system.Models.Evaluation;
import org.example.thesis_management_system.Models.Thesis;

import java.util.Scanner;

public class EvaluateProposalDefense
{

    private Thesis thesis;
    private Evaluation evaluation;
    private ThesisDAO thesisDAO;
    private EvaluationDAO evaluationDAO;

    // Constructor to initialize ThesisDAO and EvaluationDAO
    public EvaluateProposalDefense(ThesisDAO thesisDAO, EvaluationDAO evaluationDAO) {
        this.thesisDAO = thesisDAO;
        this.evaluationDAO = evaluationDAO;
    }

    // Constructor to initialize Thesis and Evaluation (if you already have objects)
    public EvaluateProposalDefense(Thesis thesis, Evaluation evaluation, ThesisDAO thesisDAO, EvaluationDAO evaluationDAO) {
        this.thesis = thesis;
        this.evaluation = evaluation;
        this.thesisDAO = thesisDAO;
        this.evaluationDAO = evaluationDAO;
    }

    public EvaluateProposalDefense(Thesis thesis, Evaluation evaluation) {
    }

    public void evaluateProposalDefense() {
        System.out.println("Starting the evaluation of Proposal Defense for Thesis ID: " + thesis.getThesisId());

        // Retrieve defense details from database
        retrieveDefenseDetails();

        // Supervisor fills the evaluation form
        fillEvaluationForm();

        // Validate the form before proceeding
        validateEvaluationForm();

        // Save the evaluation to the database
        saveEvaluation();

        // Notify both the student and supervisor about the results
        notifyStudent();
        notifySupervisor();
    }

    public void retrieveDefenseDetails() {
        Thesis thesisFromDB = thesisDAO.getThesisById(thesis.getThesisId());
        if (thesisFromDB != null) {
            this.thesis = thesisFromDB;
            System.out.println("Defense details retrieved successfully: " + thesisFromDB.getTitle());
            // Print other details like feedback or defense date
        } else {
            System.out.println("Error: Thesis details not found in the database!");
        }
    }

    public void fillEvaluationForm() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please provide a score for the defense (1-100): ");
        int score = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.println("Please provide your comments about the proposal defense: ");
        String comments = scanner.nextLine();

        evaluation.setScore(score);
        evaluation.setComments(comments);

        System.out.println("Evaluation form has been filled.");
    }

    public void validateEvaluationForm() {
        if (evaluation.getScore() == 0 || evaluation.getComments().isEmpty()) {
            System.out.println("Error: Evaluation form is invalid! Please ensure all fields are completed.");
        } else {
            System.out.println("Evaluation form validated successfully.");
        }
    }

    public void saveEvaluation() {
        try {
            evaluationDAO.saveEvaluation(evaluation);
            System.out.println("Evaluation saved to the database successfully.");
        } catch (Exception e) {
            System.out.println("Error saving evaluation: " + e.getMessage());
        }
    }

    public void notifyStudent() {
        System.out.println("Notifying student about evaluation results for Thesis ID: " + thesis.getThesisId());
        System.out.println("Student notified: Your thesis proposal defense has been evaluated.");
    }

    public void notifySupervisor() {
        System.out.println("Notifying supervisor about evaluation results for Thesis ID: " + thesis.getThesisId());
        System.out.println("Supervisor notified: Evaluation results for the thesis proposal defense have been saved.");
    }
}

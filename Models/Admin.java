package org.example.thesis_management_system.Models;

import org.example.thesis_management_system.DAO.PanelDAO;
import org.example.thesis_management_system.BuisnessLogic.FormMSRCPanel;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Admin extends User {
    private final FormMSRCPanel msrcPanelForm;
    private final PanelDAO panelDAO;  // Declare panelDAO

    // Constructor for Admin
    public Admin(int id, String username, String password, Connection connection) {
        super(id, username, password, "admin");  // Inheriting from User class
        this.panelDAO = new PanelDAO(connection);  // Initialize PanelDAO with the connection
        this.msrcPanelForm = new FormMSRCPanel(panelDAO);  // Pass PanelDAO to FormMSRCPanel
    }

    // Admin login method
    @Override
    public void login(String username, String password) {
        if (username.equals(getUsername()) && password.equals(getPassword())) {
            System.out.println(username + " has logged in as an admin.");
        } else {
            System.out.println("Invalid credentials! Login failed.");
        }
    }

    // Admin logout method
    public void logout() {
        System.out.println("Admin logged out.");
    }

    // Manage Users: Add, Remove, or Update users
    public void manageUsers(List<User> users) {
        System.out.println("Managing users:");
        users.forEach(user -> System.out.println("Managing user: " + user.getUsername()));
    }

    // View All Proposals
    public void viewAllProposals(List<Proposal> proposals) {
        System.out.println("Viewing all proposals:");
        proposals.forEach(proposal -> System.out.println("Proposal: " + proposal.getClass()));
    }

    // Set Submission Deadlines
    public void setSubmissionDeadline(String deadline) {
        System.out.println("Setting submission deadline to: " + deadline);
    }

    // Manage Supervisors
    public void manageSupervisors(List<Supervisor> supervisors) {
        System.out.println("Managing supervisors:");
        supervisors.forEach(supervisor -> System.out.println("Managing supervisor: " + supervisor.getUsername()));
    }

    // Form MSRC Panel (delegated to MSRCPanelForm class)
    public void formMSRCPanel(int thesisId, int createdBy) throws SQLException {
        msrcPanelForm.formMSRCPanel(thesisId, createdBy);  // Pass thesisId and createdBy to the formMSRCPanel method
    }
}

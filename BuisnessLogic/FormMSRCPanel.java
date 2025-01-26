package org.example.thesis_management_system.BuisnessLogic;

import org.example.thesis_management_system.DAO.PanelDAO;

import java.util.List;
import java.util.Scanner;


import java.sql.SQLException;

public class FormMSRCPanel {

    private PanelDAO panelDAO;

    public FormMSRCPanel(PanelDAO panelDAO) {
        this.panelDAO = panelDAO;
    }

    // This method initiates the process of forming the MSRC panel
    public void formMSRCPanel(int thesisId, int createdBy) throws SQLException {
        System.out.println("Administrator has requested to form the MSRC panel.");

        // Step 1: Retrieve available panel members from the database
        List<String> panelMembers = retrievePanelMembers();

        // Step 2: Display available members and let the admin select members
        selectMembers(panelMembers, thesisId, createdBy);
    }

    // Simulate retrieving a list of potential panel members from the database
    private List<String> retrievePanelMembers() throws SQLException {
        System.out.println("System is retrieving the list of potential panel members...");

        // This should be replaced with actual logic to fetch members from the database
        // Here, we just return a mock list for demonstration purposes
        return panelDAO.getAvailablePanelMembers();
    }

    // Simulate the selection of members by the administrator
    private void selectMembers(List<String> panelMembers, int thesisId, int createdBy) {
        Scanner scanner = new Scanner(System.in);

        // Display available members
        System.out.println("Available panel members:");
        for (int i = 0; i < panelMembers.size(); i++) {
            System.out.println((i + 1) + ". " + panelMembers.get(i));
        }

        // Ask admin to select two members by their index (1-based)
        System.out.println("Select two members by their number (e.g., 1 2):");
        int firstMemberIndex = scanner.nextInt() - 1;  // User input is 1-based
        int secondMemberIndex = scanner.nextInt() - 1;  // User input is 1-based

        // Add selected members to the panel (assumed that userId and thesisId are valid)
        try {
            String[] selectedMembers = { panelMembers.get(firstMemberIndex), panelMembers.get(secondMemberIndex) };
            for (String member : selectedMembers) {
                int userId = panelDAO.getUserIdByName(member);  // Get userId from member name
                panelDAO.addPanelMember(userId, thesisId, "Panel Member", createdBy);
                System.out.println("Added " + member + " to the MSRC panel.");
            }

            // Notify the members (this could be extended to a notification system)
            System.out.println("Notification sent to selected panel members.");

        } catch (SQLException e) {
            System.out.println("Error while adding panel members: " + e.getMessage());
        }
    }

}





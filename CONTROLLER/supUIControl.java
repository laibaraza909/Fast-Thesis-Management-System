package com.example.myjfxp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class supUIControl {
    @FXML
    public void userLogOut(ActionEvent event) throws IOException {
        HelloApplication m = new HelloApplication();
        m.changeScene("hello-view.fxml");
    }

    @FXML
    public void studentPage(ActionEvent event)throws IOException{
        HelloApplication m = new HelloApplication();
        m.changeScene("sup-student.fxml");
    }

    @FXML
    public void evaluate(ActionEvent event)throws IOException{
        HelloApplication m = new HelloApplication();
        m.changeScene("evaluate.fxml");
    }
    @FXML
    public void makeAnnouncement(ActionEvent event)throws IOException{
        HelloApplication m = new HelloApplication();
        m.changeScene("Sup_Ann.fxml");
    }

    @FXML
    public void postAnnouncement(ActionEvent event) throws IOException{
        System.out.println("Posted Successfully");
    }

    @FXML
    public void homePage(ActionEvent event) throws IOException{
        HelloApplication m = new HelloApplication();
        m.changeScene("SupervisorUI.fxml");
    }

    @FXML
    public void submitEvaluation(ActionEvent event)throws IOException{

        System.out.println("Evaluation Submitted Successfully");
    }

    @FXML
    public void scheduleMeet(ActionEvent event)throws IOException{
        HelloApplication m = new HelloApplication();
        m.changeScene("scheduleMeet.fxml");
    }

    @FXML
    public void schedule(ActionEvent event)throws IOException{
        System.out.println("Meeting Scheduled Successfully");
    }


    @FXML
    public void feedback(ActionEvent event)throws IOException{
        HelloApplication m = new HelloApplication();
        m.changeScene("feedback.fxml");
    }
    @FXML
    public void submitFeedback(ActionEvent event)throws IOException{
        System.out.println("Feedback Submitted Successfully");
    }

}

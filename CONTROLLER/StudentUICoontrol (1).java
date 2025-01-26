package com.example.myjfxp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class StudentUICoontrol {
    @FXML
    public void homepage(ActionEvent event)throws IOException {
        HelloApplication m = new HelloApplication();
        m.changeScene("studentUI.fxml");
    }
    @FXML
    public void userLogOut(ActionEvent event) throws IOException {
        HelloApplication m = new HelloApplication();
        m.changeScene("hello-view.fxml");
    }

    @FXML
    public void selectSupervisor(ActionEvent event)throws IOException{
        HelloApplication m = new HelloApplication();
        m.changeScene("stu_sup.fxml");
    }

    @FXML
    public void supervisorSelected(ActionEvent event)throws IOException{
        System.out.println("Supervisor Selected Successfully");
    }

    @FXML
    public void thesisPage(ActionEvent event)throws IOException{
        HelloApplication m = new HelloApplication();
        m.changeScene("thesis.fxml");
    }

    @FXML
    public void announcementPage(ActionEvent event)throws IOException{
        HelloApplication m = new HelloApplication();
        m.changeScene("announcements.fxml");

    }

    @FXML
    public void meetingPage(ActionEvent event)throws IOException{
        HelloApplication m = new HelloApplication();
        m.changeScene("meetings.fxml");
    }

    @FXML
    public void submissionPage(ActionEvent event)throws IOException{
        HelloApplication m = new HelloApplication();
        m.changeScene("submitConForm.fxml");
    }

    @FXML
    public void pannelPage(ActionEvent event)throws IOException{
        HelloApplication m = new HelloApplication();
        m.changeScene("pannel.fxml");
    }



}

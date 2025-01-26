package com.example.myjfxp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class aferLogin {
    @FXML
    Button admProButton;


    @FXML
    public void userLogOut(ActionEvent event)throws IOException{
        HelloApplication m = new HelloApplication();
        m.changeScene("hello-view.fxml");
    }

    @FXML
    public void homePage(ActionEvent event) throws IOException{
        HelloApplication m = new HelloApplication();
        m.changeScene("admiin.fxml");
    }

    @FXML
    public void settingsPage(ActionEvent event) throws IOException{
        HelloApplication m = new HelloApplication();
        //m.changeScene();
    }

    @FXML
    public void studentPage(ActionEvent event) throws IOException{
        HelloApplication m = new HelloApplication();
        m.changeScene("studentView.fxml");
    }

    @FXML
    public void supervisorPage(ActionEvent event) throws IOException{
        HelloApplication m = new HelloApplication();
        m.changeScene("supView.fxml");
    }

    @FXML
    public void schDefPage(ActionEvent event) throws IOException{
        HelloApplication m = new HelloApplication();
        m.changeScene("scheeduleDef.fxml");
    }

    @FXML
    public void pannelPage(ActionEvent event) throws IOException{
        HelloApplication m = new HelloApplication();
        m.changeScene("pannelView.fxml");
    }
    @FXML
    public void makeAnnouncement(ActionEvent event) throws IOException{
        HelloApplication m = new HelloApplication();
        m.changeScene("deadlines.fxml");

    }

    @FXML
    public void postAnnouncement(ActionEvent event)throws IOException{
        System.out.println("Posted");
    }

}

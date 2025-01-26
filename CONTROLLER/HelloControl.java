package com.example.myjfxp;

import com.sun.tools.javac.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class HelloControl {

    @FXML
    private Button loginButton;

    @FXML
    private Label wrongLoginLabel;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    public void userLogIn(ActionEvent event) throws IOException{
        checkLogin();
    }


    public void checkLogin() throws IOException{

        HelloApplication m = new HelloApplication();
        if(usernameField.getText().isEmpty() && passwordField.getText().isEmpty()){
            wrongLoginLabel.setText("Please enter username and password");

        }
        else if(usernameField.getText().isEmpty()){
            wrongLoginLabel.setText("Please enter username");
        }
        else if(passwordField.getText().isEmpty()){
            wrongLoginLabel.setText("Please enter password");
        }
        else if(usernameField.getText().equals("Admin")){
            m.changeScene("admiin.fxml");
        }
        else if(usernameField.getText().equals("Supervisor")){
            m.changeScene("SupervisorUI.fxml");
        }

        else if(usernameField.getText().equals("Student")){
            m.changeScene("studentUI.fxml");
        }


    }
}
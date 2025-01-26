package com.example.myjfxp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class SupervisorControl {
    @FXML
    public void homePage(ActionEvent event)throws IOException {
        HelloApplication m = new HelloApplication();
        m.changeScene("admiin.fxml");
    }

    @FXML
    public void removeSupervisor(ActionEvent event)throws IOException{
        System.out.println("Supervisor Removed Successfully");

    }

    @FXML
    public void addSupervisor(ActionEvent event)throws IOException{
        System.out.println("Supervisor added Successfully");

    }
}

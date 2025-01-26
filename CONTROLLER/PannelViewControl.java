package com.example.myjfxp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class PannelViewControl {
    @FXML
    public void homePage(ActionEvent event)throws IOException{
        HelloApplication m = new HelloApplication();
        m.changeScene("admiin.fxml");
    }

}

package org.example.thesis_management_system.CONTROLLER;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.thesis_management_system.Models.Student;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class viewStControl implements Initializable {
    @FXML
    private TableView<com.example.myjfxp.Student>  tableView;

    @FXML
    private TableColumn<org.example.thesis_management_system.Models.Student, String> nameColumn;

    @FXML
    private TableColumn<org.example.thesis_management_system.Models.Student, String> rollNColumn;

    @FXML
    private TableColumn<org.example.thesis_management_system.Models.Student, String> deptColumn;

    @FXML
    private TableColumn<org.example.thesis_management_system.Models.Student, String> supColumn;

    @FXML
    private TableColumn<org.example.thesis_management_system.Models.Student, Character> gradeColumn;

    @FXML
    private TableColumn<org.example.thesis_management_system.Models.Student,String> remColumn;

    @FXML
    private TextField nameF;

    @FXML
    private TextField rNF;

    @FXML
    private TextField deptF;

    @FXML
    private TextField supF;

    @FXML
    private TextField grF;

    @FXML
    private TextField remF;

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle){
       nameColumn.setCellValueFactory(new PropertyValueFactory<org.example.thesis_management_system.Models.Student, String>("name"));
       rollNColumn.setCellValueFactory(new PropertyValueFactory<org.example.thesis_management_system.Models.Student, String>("rollNo"));
       deptColumn.setCellValueFactory(new PropertyValueFactory<org.example.thesis_management_system.Models.Student, String>("department"));
       supColumn.setCellValueFactory(new PropertyValueFactory<org.example.thesis_management_system.Models.Student,String>("supervisor"));
       gradeColumn.setCellValueFactory(new PropertyValueFactory<org.example.thesis_management_system.Models.Student, Character>("grade"));
       remColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("remarks"));

    }
    @FXML
    public void homePage(ActionEvent event) throws IOException {
        com.example.myjfxp.HelloApplication m = new com.example.myjfxp.HelloApplication();
        m.changeScene("admiin.fxml");

    }

    @FXML
    public void addStudent(ActionEvent event) throws IOException{
        com.example.myjfxp.HelloApplication m1 = new com.example.myjfxp.HelloApplication();
        m1.changeScene("addSt.fxml");
    }

    @FXML
    public void add(ActionEvent event) throws IOException{


            String input = grF.getText(); // Get the text from TextField

            char firstChar = input.charAt(0); // Get the first character


            Student student = new Student(nameF.getText(),
                    rNF.getText(), deptF.getText(), supF.getText(), firstChar, remF.getText()
            );

        ObservableList<com.example.myjfxp.Student> students = tableView.getItems();
        //students.add(student);

        tableView.setItems(students);

        com.example.myjfxp.HelloApplication m = new com.example.myjfxp.HelloApplication();
        m.changeScene("studentView.fxml");
    }

    @FXML
    public void removeStudent(ActionEvent event)throws IOException{
        int selectedID = tableView.getSelectionModel().getSelectedIndex();
        tableView.getItems().remove(selectedID);
    }
}

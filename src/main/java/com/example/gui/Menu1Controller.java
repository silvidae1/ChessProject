package com.example.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Menu1Controller {

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Button quitButton;
    @FXML
    private AnchorPane scenePane;


    public void switchToLogin(ActionEvent event) throws IOException{

        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene (root);
        stage.setScene(scene);
        stage.setTitle("Login");
        stage.show();

    }

    public void switchToMenu(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene (root);

        String css =this.getClass().getResource("button.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.setTitle("JavaFX");
        stage.show();

    }

    public void switchToAcc(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("Acc.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene (root);
        stage.setScene(scene);
        stage.setTitle("Create Account");
        stage.show();

    }

    public void switchToQuit(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("Quit.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene (root);
        stage.setScene(scene);
        stage.setTitle("Quit Menu");
        stage.show();

    }
    public void quit(ActionEvent event){
        stage = (Stage) scenePane.getScene().getWindow();
        stage.close();
    }
}

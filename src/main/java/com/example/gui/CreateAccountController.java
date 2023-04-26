package com.example.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class CreateAccountController
{
    private Stage stage;
    private Scene scene;

    @FXML
    TextField nameTextField;
    @FXML
    PasswordField passwordTextField;
    @FXML
    PasswordField password2TextField;
    @FXML
    Label nameLabel;
    static String password;
    static String password2;

    static String username;

    public void switchToMenu(ActionEvent event) throws IOException {

        username = nameTextField.getText();
        password = passwordTextField.getText();
        password2 = password2TextField.getText();
        Database database= new Database();

        if(password2.isEmpty() || password.isEmpty() || username.isEmpty()){
            nameLabel.setText("Preencha todos os dados");
            nameLabel.setAlignment(Pos.CENTER);
        }
        else if(!password2.equals(password)){
            nameLabel.setText("Passwords diferentes");
            nameLabel.setAlignment(Pos.CENTER);

        }
        else if(database.checks_if_exists_Username(username)==true){
            nameLabel.setText("Username já existe");
            nameLabel.setAlignment(Pos.CENTER);
        }
        
        else if(database.checks_if_exists_Username(username)==false)
        {
            nameLabel.setText("");
            nameLabel.setAlignment(Pos.CENTER);
            database.create_Account(username, password);

            Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);

            String css = this.getClass().getResource("button.css").toExternalForm();
            scene.getStylesheets().add(css);
            stage.setScene(scene);
            stage.setTitle("JavaFX");
            stage.show();
        }

    }
    public void backToMenu(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene (root);

        String css =this.getClass().getResource("button.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.setTitle("JavaFX");
        stage.show();

    }
}

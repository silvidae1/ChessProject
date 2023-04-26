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

public class LoginController {

    @FXML
    TextField nameTextField;

    @FXML
    PasswordField passwordTextField;
    @FXML
    Label nameLabel;
    static String password;

    static String username;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void loginComplete(ActionEvent event) throws IOException {

        username = nameTextField.getText();
        password = passwordTextField.getText();
        Database database= new Database();

        System.out.println(username);
        System.out.println(password);

        if (username.isEmpty() || password.isEmpty()) {
                nameLabel.setText("Preencha todos os dados");
                nameLabel.setAlignment(Pos.CENTER);
        }
        else if(database.login_Account(username, password)==false){
            nameLabel.setText("Password ou username errados");
        }

        else if(database.login_Account(username, password)==true){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu2.fxml"));

            root = loader.load();

            Menu2Controller menu2Controller = loader.getController();
            menu2Controller.printName(username);
            menu2Controller.player=username;
            menu2Controller.password=password;
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);

            String css = this.getClass().getResource("button.css").toExternalForm();
            scene.getStylesheets().add(css);
            stage.setScene(scene);
            stage.setTitle("JavaFX");
            stage.show();
        }
    }

    public void back(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
        root =loader.load();

        Menu1Controller sceneController = loader.getController();
        sceneController.switchToMenu(event);

    }
}

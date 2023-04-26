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
import javafx.stage.Stage;

import java.io.IOException;


public class ChangePasswordController {

    private Stage stage;
    private Scene scene;

    @FXML
    PasswordField passwordTextField;
    @FXML
    PasswordField newPasswordTextField;
    @FXML
    PasswordField newPasswordTextField2;
    @FXML
    Label nameLabel;

    static String password;
    static String oldPassword;
    static String newPassword;
    static String newPassword2;

    static String username;

    Database database = new Database();

    public void back(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu2.fxml"));
        Parent root = loader.load();

        Menu2Controller menu2Controller = loader.getController();
        menu2Controller.printName(username);

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        String css = this.getClass().getResource("button.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.setTitle("JavaFX");
        stage.show();



    }

    public void changePassword(ActionEvent event) throws IOException {

        oldPassword = passwordTextField.getText();
        newPassword = newPasswordTextField.getText();
        newPassword2 = newPasswordTextField2.getText();


        if (newPassword.isEmpty() || oldPassword.isEmpty() || newPassword2.isEmpty()) {
            nameLabel.setText("Preencha todos os dados");
            nameLabel.setAlignment(Pos.CENTER);
        }
        else if(!password.equals(oldPassword)){
            nameLabel.setText("Password atual errada");
            nameLabel.setAlignment(Pos.CENTER);
        }
        else if (!newPassword.equals(newPassword2)) {
            nameLabel.setText("Confirmação de password diferente");
            nameLabel.setAlignment(Pos.CENTER);
        }
        else if (database.change_Password(username, password, newPassword)==true) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu2.fxml"));
            Parent root = loader.load();

            Menu2Controller menu2Controller = loader.getController();
            menu2Controller.password=newPassword;
            menu2Controller.printName(username);

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);

            String css = this.getClass().getResource("button.css").toExternalForm();
            scene.getStylesheets().add(css);
            stage.setScene(scene);
            stage.setTitle("JavaFX");
            stage.show();
        }
        else{
            nameLabel.setText("Erro a mudar password");
            nameLabel.setAlignment(Pos.CENTER);
        }
    }
}

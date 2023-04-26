package com.example.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class ProfileController {
    static String username;
    static String password;
    @FXML
    Label userLabel;
    @FXML
    Label ratingLabel;
    @FXML
    Label victoriesLabel;
    @FXML
    Label drawsLabel;
    @FXML
    Label defeatsLabel;
    @FXML
    Label gamesLabel;
    int [] stats;
    private Stage stage;
    private Scene scene;

    Database database = new Database();



    public void printStats(String user){


        stats=database.stats_Players(username);
        userLabel.setText("Username: "+ username);
        gamesLabel.setText("Games: " + stats[0]);
        ratingLabel.setText("Rating: "+ stats[4]);
        victoriesLabel.setText("Victories: " + stats[1]);
        defeatsLabel.setText("Defeats: " + stats[2]);
        drawsLabel.setText("Draws: " + stats[3]);


    }
    public void switchToMenu2(ActionEvent event) throws IOException {

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
    public void switchToChangePassword(ActionEvent event) throws IOException{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("PasswordChange.fxml"));
        Parent root = loader.load();

        ChangePasswordController changePasswordController = loader.getController();

        changePasswordController.username=username;
        changePasswordController.password=password;
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        String css = this.getClass().getResource("button.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.setTitle("JavaFX");
        stage.show();

    }
}

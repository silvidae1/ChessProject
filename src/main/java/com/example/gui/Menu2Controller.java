package com.example.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Menu2Controller {

    private Stage stage;
    private Scene scene;

    static String player;
    static String password;
    @FXML
    Label nameLabel;



    public void switchToPlay(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Play.fxml"));
        Parent root = loader.load();


        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        GameController gameController= new GameController();
        gameController.player1=player;

        stage.setWidth(800);
        stage.setHeight(635);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Game");
    }

    public void logout(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        String css = this.getClass().getResource("button.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.setTitle("JavaFX");
        stage.show();
    }

    public void switchToQuit2(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("Quit2.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Quit Menu");
        stage.show();

    }
    public void switchToProfile(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Profile.fxml"));

        Parent root = loader.load();

        ProfileController profileController = loader.getController();


        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        profileController.username=player;
        profileController.password=password;
        profileController.printStats(player);

        stage.setTitle("Profile");



        stage.show();

    }


    public void printName(String username){
        nameLabel.setText(username);
        System.out.println(username + "!!!");
        nameLabel.setAlignment(Pos.CENTER);
    }
}


package com.example.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
            Scene scene = new Scene(root);

            Image icon = new Image("icon.png");
            stage.getIcons().add(icon);
            stage.setTitle("Menu");
            stage.setWidth(800);
            stage.setHeight(635);
            stage.setResizable(false);

            stage.setScene(scene);
            stage.show();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}

package com.example.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class QuitController {
    private Stage stage;
    private Scene scene;

    @FXML
    private AnchorPane scenePane;

    public void switchToMenu2(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu2.fxml"));
        Parent root = loader.load();

        Menu2Controller menu2Controller = loader.getController();
        menu2Controller.printName(menu2Controller.player);

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        String css = this.getClass().getResource("button.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.setTitle("JavaFX");
        stage.show();


    }

    public void quit(ActionEvent event){
        stage = (Stage) scenePane.getScene().getWindow();
        stage.close();
    }
}

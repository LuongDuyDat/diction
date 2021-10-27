package com.example.diction;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class messageScene {
    public void setScene(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(this.getClass().getResource("Message.fxml"));
            Scene scene = new Scene(root);
            Stage primaryStage = new Stage();
            primaryStage.setTitle("SUCCESS");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

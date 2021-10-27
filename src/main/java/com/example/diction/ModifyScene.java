package com.example.diction;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ModifyScene {
    public void setScene(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(this.getClass().getResource("modifyScene.fxml"));
            Scene scene = new Scene(root);
            Stage primaryStage = new Stage();
            primaryStage.setTitle("SỬA TỪ");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

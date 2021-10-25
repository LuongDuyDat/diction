package com.example.diction;

import com.example.diction.API.FreeTTSAPI;
import com.example.diction.Entry.loadEntry;
import com.example.diction.Entry.word;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Scanner;

public class Main extends Application {
    @Override
    public void start (Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(this.getClass().getResource("main.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
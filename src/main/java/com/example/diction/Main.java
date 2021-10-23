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
    public static void commandline() {
        loadEntry l = new loadEntry();
        do {
            Scanner sc = new Scanner(System.in);
            String id = sc.nextLine().trim();
            String Word = sc.nextLine().trim();
            FreeTTSAPI.speech(Word);

            if (id.equals("1")) {
                word W = l.searchWord(Word);
                System.out.println(W.getWord() + " " + W.getDescription());
            } else if (id.equals("2")) {
                ArrayList<String> listwords = l.hintSearch(Word);
                for (int i = 0; i < listwords.size(); i++) {
                    System.out.println(listwords.get(i));
                }
            } else if (id.equals("3")) {
                String description = sc.nextLine().trim();
                l.insertWord(Word, description);
            } else if (id.equals("4")) {
                l.deleteWord(Word);
            } else if (id.equals("5")) {
                String description = sc.nextLine().trim();
                l.updateWord(Word, description);
            } else {
                break;
            }
        } while(true);
    }
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
        commandline();
        System.out.println(12);
        launch();
    }
}
package com.example.diction;

import com.example.diction.Entry.loadEntry;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class addController {

    @FXML
    private Button themTu;

    @FXML
    private TextField addWord;

    @FXML
    private TextField addDescription;

    loadEntry l = new loadEntry();

    @FXML
    void themTu(ActionEvent event) {
        String add_word = addWord.getText();
        String add_description = addDescription.getText();
        themTu.setOnMouseClicked(mouseEvent -> {
            if (l.insertWord(add_word, add_description)) {
                messageScene m = new messageScene();
                m.setScene(event);
            } else {
                messagefailScene m = new messagefailScene();
                m.setScene(event);
            }
        });
    }

}

package com.example.diction;

import com.example.diction.Entry.loadEntry;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class modifyController {

    @FXML
    private Button suaTu;

    @FXML
    private TextField modifyWord;

    @FXML
    private TextField modifyDescription;

    loadEntry l = new loadEntry();

    @FXML
    void suaTu(ActionEvent event) {
        String modify_word = modifyWord.getText();
        String modify_description = modifyDescription.getText();
        suaTu.setOnMouseClicked(mouseEvent -> {
            if (l.updateWord(modify_word, modify_description)) {
                messageScene m = new messageScene();
                m.setScene(event);
            } else {
                messagefailScene m = new messagefailScene();
                m.setScene(event);
            }
        });
    }
}


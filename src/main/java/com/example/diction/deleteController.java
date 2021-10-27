package com.example.diction;

import com.example.diction.Entry.loadEntry;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class deleteController {

        @FXML
        private Button xoaTu;

        @FXML
        private TextField deleteWord;

        loadEntry l = new loadEntry();

        @FXML
        void xoaTu(ActionEvent event) {
                String delete_word = deleteWord.getText();
                xoaTu.setOnMouseClicked(mouseEvent -> {
                        if (l.deleteWord(delete_word)) {
                                messageScene m = new messageScene();
                                m.setScene(event);
                        } else {
                                messagefailScene m = new messagefailScene();
                                m.setScene(event);
                        }
                });
        }

}

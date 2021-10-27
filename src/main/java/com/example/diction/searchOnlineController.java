package com.example.diction;

import com.example.diction.API.GoogleTranslateAPI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.io.IOException;

public class searchOnlineController {

    @FXML
    private TextArea searchTextt;

    @FXML
    private TextArea meaning;

    @FXML
    private Button dichAV;

    @FXML
    private Button dichVA;

    private GoogleTranslateAPI translation = new GoogleTranslateAPI();


    @FXML
    void dichVAAction(ActionEvent event) throws IOException {
        String text = searchTextt.getText();
        String v = translation.translate("vi", "en", text);
        meaning.setText(v);
    }

    @FXML
    void dichAVAction(ActionEvent event) throws IOException {
        String text = searchTextt.getText();
        String s = translation.translate("en", "vi", text);
        meaning.setText(s);
    }

}

package com.example.diction;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class messagefailController {

    @FXML
    private AnchorPane scene;

    @FXML
    private Button ok;

    public void OK(MouseEvent mouseEvent) {
        Stage stage = (Stage) ok.getScene().getWindow();
        stage.close();
    }
}

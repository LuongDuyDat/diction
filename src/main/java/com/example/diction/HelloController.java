package com.example.diction;

import com.example.diction.API.FreeTTSAPI;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import com.example.diction.Entry.*;
import javafx.stage.Stage;

import javax.speech.Word;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HelloController {

    @FXML
    private TextField searchText;

    @FXML
    private ImageView tudien;

    @FXML
    private Button addButton;

    @FXML
    private ImageView addImage;

    @FXML
    private Button modifyButton;

    @FXML
    private ImageView modifyImage;

    @FXML
    private Button deleteButton;

    @FXML
    private ImageView deleteImage;

    @FXML
    private Button dichOnline;

    @FXML
    private Button searchButton;

    @FXML
    private Button speakButton;

    @FXML
    private ListView<String> listView;

    @FXML
    private WebView webView;

    private loadEntry l = new loadEntry();

    private DropShadow shadow = new DropShadow();

    @FXML
    void addAction(ActionEvent event) {
        AddScene a = new AddScene();
        a.setScene(event);
    }

    @FXML
    void deleteAction(ActionEvent event) {
        DeleteScene d = new DeleteScene();
        d.setScene(event);
    }

    @FXML
    void dichOnlineAction(ActionEvent event) {
        SearchOnlineScene s = new SearchOnlineScene();
        s.setScene(event);
    }

    @FXML
    void modifyAction(ActionEvent event) {
        ModifyScene m = new ModifyScene();
        m.setScene(event);
    }

    @FXML
    void searchAction(ActionEvent event) {
        String text = searchText.getText();
        word w = l.searchWord(text);
        if (w.getException() == "Khong tim duoc tu trong tu dien") {
            messagefailScene m = new messagefailScene();
            m.setScene(event);
        }
        WebEngine webEngine = webView.getEngine();
        webEngine.loadContent(w.getHtml());
    }

    @FXML
    void speakAction(ActionEvent event) {
        String text = searchText.getText();
        FreeTTSAPI.speech(text);
    }

    @FXML
    public void textFieldAction(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            String text = searchText.getText();
            word w = l.searchWord(text);
            WebEngine webEngine = webView.getEngine();
            webEngine.loadContent(w.getHtml());
        } else {
            ObservableList<String> lists = FXCollections.observableArrayList();
            String text = searchText.getText() + keyEvent.getText();
            ArrayList<String> w = l.hintSearch(text);
            for (int i = 0; i < w.size(); i++) {
                lists.add(w.get(i));
            }
            listView.setItems(lists);
        }
    }

    @FXML
    public void listViewAction(MouseEvent mouseEvent) {
        String text = listView.getFocusModel().getFocusedItem();
        searchText.setText(text);
        word w = l.searchWord(text);
        WebEngine webEngine = webView.getEngine();
        webEngine.loadContent(w.getHtml());
    }

    @FXML
    public void enterSearchButton(MouseEvent mouseEvent) {
        if (mouseEvent.getEventType().equals(mouseEvent.MOUSE_ENTERED)) {
            searchButton.setEffect(shadow);
        }
    }

    @FXML
    public void exitSearchButton(MouseEvent mouseEvent) {
        if (mouseEvent.getEventType().equals(mouseEvent.MOUSE_EXITED)) {
            searchButton.setEffect(null);
        }
    }

    @FXML
    public void enterAddButton(MouseEvent mouseEvent) {
        if (mouseEvent.getEventType().equals(mouseEvent.MOUSE_ENTERED)) {
            addButton.setEffect(shadow);
        }
    }

    @FXML
    public void exitAddButton(MouseEvent mouseEvent) {
        if (mouseEvent.getEventType().equals(mouseEvent.MOUSE_EXITED)) {
            addButton.setEffect(null);
        }
    }

    @FXML
    public void enterModifyButton(MouseEvent mouseEvent) {
        if (mouseEvent.getEventType().equals(mouseEvent.MOUSE_ENTERED)) {
            modifyButton.setEffect(shadow);
        }
    }

    @FXML
    public void exitModifyButton(MouseEvent mouseEvent) {
        if (mouseEvent.getEventType().equals(mouseEvent.MOUSE_EXITED)) {
            modifyButton.setEffect(null);
        }
    }

    @FXML
    public void enterDeleteButton(MouseEvent mouseEvent) {
        if (mouseEvent.getEventType().equals(mouseEvent.MOUSE_ENTERED)) {
            deleteButton.setEffect(shadow);
        }
    }

    @FXML
    public void exitDeleteButton(MouseEvent mouseEvent) {
        if (mouseEvent.getEventType().equals(mouseEvent.MOUSE_EXITED)) {
            deleteButton.setEffect(null);
        }
    }

    @FXML
    public void enterSearchOnlButton(MouseEvent mouseEvent) {
        if (mouseEvent.getEventType().equals(mouseEvent.MOUSE_ENTERED)) {
            dichOnline.setEffect(shadow);
        }
    }

    @FXML
    public void exitSearchOnlButton(MouseEvent mouseEvent) {
        if (mouseEvent.getEventType().equals(mouseEvent.MOUSE_EXITED)) {
            dichOnline.setEffect(null);
        }
    }

    @FXML
    public void enterSpeakButton(MouseEvent mouseEvent) {
        if (mouseEvent.getEventType().equals(mouseEvent.MOUSE_ENTERED)) {
            speakButton.setEffect(shadow);
        }
    }

    @FXML
    public void exitSpeakButton(MouseEvent mouseEvent) {
        if (mouseEvent.getEventType().equals(mouseEvent.MOUSE_EXITED)) {
            speakButton.setEffect(null);
        }
    }
}
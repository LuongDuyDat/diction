module com.example.diction {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires javafx.graphics;
    requires freetts;
    requires java.sql;
    requires javafx.base;

    opens com.example.diction to javafx.fxml, java.sql;
    exports com.example.diction;

}
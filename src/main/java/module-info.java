module org.example.demo {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.sql;
    requires java.desktop;

    opens org.example.demo to javafx.fxml;
    exports org.example.demo;
    exports org.example.demo.controllers;
    opens org.example.demo.controllers to javafx.fxml;

    opens Clases to javafx.base;
}
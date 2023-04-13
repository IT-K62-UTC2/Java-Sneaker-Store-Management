module utc2.itk62.sneaker {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires mysql.connector.java;
    requires org.apache.poi.ooxml;

    opens utc2.itk62.sneaker to javafx.fxml;
    exports utc2.itk62.sneaker.controllers;
    exports utc2.itk62.sneaker;
    exports utc2.itk62.sneaker.models;
}
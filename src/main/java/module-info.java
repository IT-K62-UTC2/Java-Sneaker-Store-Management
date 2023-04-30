module utc2.itk62.sneaker {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires mysql.connector.java;
    requires org.apache.poi.ooxml;
    requires jasperreports;
    requires javafx.swing;

    opens utc2.itk62.store to javafx.fxml;
    exports utc2.itk62.store.controllers;
    exports utc2.itk62.store;
    exports utc2.itk62.store.models;
}
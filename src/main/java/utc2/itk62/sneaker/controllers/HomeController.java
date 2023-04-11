package utc2.itk62.sneaker.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import utc2.itk62.sneaker.Main;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class HomeController {
    @FXML
    public AnchorPane include;
    @FXML
    public ImageView image;

    public void initialize() throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("views/dashboard.fxml"));
        Node node = loader.load();
        include.getChildren().add(0,node);
    }

    @FXML
    public void handleBtnStaff(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("views/staff.fxml"));
        Node node = loader.load();
        include.getChildren().set(0,node);
    }

    @FXML
    public void handleBtnHome(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("views/dashboard.fxml"));
        Node node = loader.load();
        include.getChildren().set(0,node);
    }

    public void handleBtnCategory(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("views/category.fxml"));
        Node node = loader.load();
        include.getChildren().set(0,node);
    }
}

package utc2.itk62.store.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import utc2.itk62.store.Main;
import utc2.itk62.store.common.User;

import java.io.IOException;

public class HomeController {
    @FXML
    public AnchorPane include;
    @FXML
    public ImageView image;

    public void initialize() throws IOException {
        image.setImage(new Image(User.staff.getPathAvatar()));
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("views/dashboard.fxml"));
        Node node = loader.load();
        include.getChildren().add(0,node);
    }

    @FXML
    public void handleBtnStaff(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("views/staff.fxml"));
        Node node = null;
        try {
            node = loader.load();
            include.getChildren().set(0,node);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void handleBtnHome(ActionEvent actionEvent)  {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("views/dashboard.fxml"));
        Node node = null;
        try {
            node = loader.load();
            include.getChildren().set(0,node);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void handleBtnCategory(ActionEvent actionEvent){
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("views/category.fxml"));
        Node node = null;
        try {
            node = loader.load();
            include.getChildren().set(0,node);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void handleBtnCustomer(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("views/customer.fxml"));
        System.out.println(Main.class.getResource("views/customer.fxml"));
        Node node = null;
        try {
            node = loader.load();
            include.getChildren().set(0,node);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void handleBtnProduct(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("views/product.fxml"));
        System.out.println(Main.class.getResource("views/customer.fxml"));
        Node node = null;
        try {
            node = loader.load();
            include.getChildren().set(0,node);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void handleImportGoods(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("views/import-goods.fxml"));
        System.out.println(Main.class.getResource("views/import-goods.fxml"));
        Node node = null;
        try {
            node = loader.load();
            include.getChildren().set(0,node);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void handleHistory(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("views/history-invoice.fxml"));
        Node node = null;
        try {
            node = loader.load();
            include.getChildren().set(0,node);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void handleBtnSupplier(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("views/supplier.fxml"));
        Node node = null;
        try {
            node = loader.load();
            include.getChildren().set(0,node);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void handleBtnSell(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("views/sale.fxml"));
        Node node = null;
        try {
            node = loader.load();
            include.getChildren().set(0,node);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

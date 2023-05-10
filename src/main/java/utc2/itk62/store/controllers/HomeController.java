package utc2.itk62.store.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utc2.itk62.store.Main;
import utc2.itk62.store.common.User;
import utc2.itk62.store.models.Auth;
import utc2.itk62.store.models.Menu;

import java.io.IOException;
import java.util.List;

public class HomeController {
    @FXML
    public AnchorPane include;
    @FXML
    public VBox vbox;
    public Button btnLogout;
    public Label curentMenu;
    public Label nameUser;
    public Label positionUser;
    public ImageView imageUser;

    public void initialize() throws IOException {
        nameUser.setText(User.staff.getFullName());
        positionUser.setText(User.staff.getPosition().getName());
        try {
            imageUser.setImage(new Image(User.staff.getPathAvatar()));
        } catch (Exception e) {
            e.getMessage();
        }
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("views/dashboard.fxml"));
        Node node = null;
        try {
            node = loader.load();
            include.getChildren().add(node);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        setupBtnLogout();
        loadMenu();
    }

    private void setupBtnLogout() throws IOException {
        btnLogout.setOnAction((actionEvent) -> {
            Stage closeStage = (Stage) btnLogout.getScene().getWindow();
            closeStage.close();
            User.staff = null;
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("views/login.fxml"));
            Scene scene = null;
            try {
                Stage stage = new Stage();
                scene = new Scene(fxmlLoader.load());
                stage.setTitle("Hello!");
                stage.setScene(scene);
                stage.getIcons().add(new Image(getClass().getResourceAsStream("/utc2/itk62/store/images/icons8-shop-100.png")));
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void loadMenu() {
        vbox.getChildren().clear();
        List<Auth> listAuth = User.staff.getPosition().getAuthList();
        for (int i = 0; i < listAuth.size(); i++) {
            Menu menu = listAuth.get(i).getMenu();
            Button button = new Button(menu.getName());
            button.setPrefWidth(170);
            button.setPrefHeight(70);
            button.getStyleClass().add("menu-button");
            button.setOnAction(actionEvent -> {
                FXMLLoader loader = new FXMLLoader(Main.class.getResource(menu.getPath()));
                Node node = null;
                curentMenu.setText(menu.getName());
                try {
                    node = loader.load();
                    include.getChildren().set(0, node);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

            vbox.getChildren().add(button);
        }
    }
}

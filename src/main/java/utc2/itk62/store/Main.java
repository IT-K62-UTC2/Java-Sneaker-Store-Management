package utc2.itk62.store;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import utc2.itk62.store.util.HashedPassword;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("views/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/utc2/itk62/store/images/icons8-shop-100.png")));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
        System.out.println(HashedPassword.hashPassword("admin"));
    }

}
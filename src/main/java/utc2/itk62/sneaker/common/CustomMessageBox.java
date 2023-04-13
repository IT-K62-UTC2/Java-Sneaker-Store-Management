package utc2.itk62.sneaker.common;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class CustomMessageBox {
    public static boolean isYes(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                message,
                ButtonType.YES, ButtonType.NO);
        alert.setHeaderText(null);
        alert.setTitle(title);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.YES) {
            return true;
        }
        return false;
    }

    public static void boxError(String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Error");
        alert.setTitle("Something went wrong");
        alert.setContentText(content);
        alert.showAndWait();

    }

    public static void boxInfo(String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Notification");
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static void boxOk(String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setContentText(content);
        alert.showAndWait();
    }
}

module utc2.itk62.sneaker {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens utc2.itk62.sneaker to javafx.fxml;
    exports utc2.itk62.sneaker;
}
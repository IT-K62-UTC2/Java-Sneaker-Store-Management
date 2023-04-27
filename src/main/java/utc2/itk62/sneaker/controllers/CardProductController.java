package utc2.itk62.sneaker.controllers;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utc2.itk62.sneaker.models.Product;

public class CardProductController {
    public Button btnAdd;
    public Spinner<Integer> quantity;
    public Label priceProduct;
    public Label sizeProduct;
    public ImageView imageProduct;
    public Label nameProduct;

    public void initialize() {}

    public void setData(Product product) {
        priceProduct.setText(String.valueOf(product.getPrice()));
        sizeProduct.setText(String.valueOf(product.getSize()));
        nameProduct.setText(String.valueOf(product.getName()));
        imageProduct.setImage(new Image(product.getAvatar()));
        quantity.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, product.getQuantity(), 0));
    }

}

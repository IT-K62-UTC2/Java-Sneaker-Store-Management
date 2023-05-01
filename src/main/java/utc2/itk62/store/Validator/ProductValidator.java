package utc2.itk62.store.Validator;

import javafx.scene.image.ImageView;

public class ProductValidator {
    public static boolean validateImageAvatar(ImageView imageView) {
        if(imageView == null) {
            return false;
        }
        if(imageView.getImage() == null) {
            return false;
        }
        return true;
    }


    public static boolean validateNameProduct(String text) {
        if(text.equals("")) {
            return false;
        }

        if(text.startsWith(" ")) {
            return false;
        }
        return true;
    }

    public static boolean validatePrice(double price) {
        if(price <= 0) {
            return false;
        }
        return true;
    }

    public static boolean validateQuantity(int parseInt) {
        if(parseInt < 0) {
            return false;
        }
        return true;
    }
}

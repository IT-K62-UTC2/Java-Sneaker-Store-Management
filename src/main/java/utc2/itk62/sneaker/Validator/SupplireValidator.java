package utc2.itk62.sneaker.Validator;

import javafx.scene.image.ImageView;
import utc2.itk62.sneaker.services.StaffService;
import utc2.itk62.sneaker.services.SupplierServices;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SupplireValidator {
    public static final SupplierServices supplierServices = new SupplierServices();


    public static boolean validateEmail(String email) {
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

        Pattern p = Pattern.compile(regex);

        if (email == null) {
            return false;
        }

        Matcher m = p.matcher(email);
        return m.matches();

    }

    public static boolean isExistEmail(String email) {
        if(supplierServices.getSupplierByEmail(email) != null) {
            return true;
        }
        return false;
    }


    public static boolean validateUsername(String username) {


        if (username == null  || username.length()<2 || Character.isDigit(username.charAt(0))) {
            return false;
        }

        return true;
    }

    public static boolean isExistUsername(String username) {
        if(supplierServices.getSupplierByUserName(username) != null) {
            return true;
        }
        return false;
    }





    public static boolean validateAddress(String address) {
        if (address == null  || address.length()<2 ) {
            return false;
        }

        return true;
    }

    public static boolean validatePhoneNumber(String phoneNumber) {
        String regex = "(84|0[3|5|7|8|9])+([0-9]{8})\\b";
        Pattern p = Pattern.compile(regex);
        if (phoneNumber == null) {
            return false;
        }
        Matcher m = p.matcher(phoneNumber);
        return m.matches();
    }

    public static boolean isExistPhoneNumber(String phoneNumber) {
        if(supplierServices.getSupplierByPhoneNumber(phoneNumber) != null) {
            return true;
        }
        return false;
    }




}

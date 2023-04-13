package utc2.itk62.sneaker.Validator;

import javafx.css.Match;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StaffValidator {
    public static boolean validateEmail(String email) {
        String regex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";

        Pattern p = Pattern.compile(regex);

        if (email == null) {
            return false;
        }

        Matcher m = p.matcher(email);
        return m.matches();

    }

    public static boolean validateUsername(String username) {
        String regex = "^(?=[a-zA-Z0-9._]{8,20}$)(?!.*[_.]{2})[^_.].*[^_.]$";
        Pattern p = Pattern.compile(regex);
        if (username == null) {
            return false;
        }
        Matcher m = p.matcher(username);
        return m.matches();
    }

    public static boolean validatePhoneNumber(String phoneNumber) {
        String regex = "/(84|0[3|5|7|8|9])+([0-9]{8})\\b/g";
        Pattern p = Pattern.compile(regex);
        if (phoneNumber == null) {
            return false;
        }
        Matcher m = p.matcher(phoneNumber);
        return m.matches();
    }
}

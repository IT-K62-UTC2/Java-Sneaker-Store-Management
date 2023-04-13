package utc2.itk62.sneaker.Validator;

import javafx.css.Match;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utc2.itk62.sneaker.services.StaffService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StaffValidator {
    public static final StaffService staffService = new StaffService();


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
        if(staffService.getStaffByEmail(email) != null) {
            return true;
        }
        return false;
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

    public static boolean isExistUsername(String username) {
        if(staffService.getStaffByUserName(username) != null) {
            return true;
        }
        return false;
    }

    public static boolean validateCCCD(String cccd) {
        if(cccd.length() != 12) {
            return false;
        }

        for (int i = 0; i < cccd.length(); i++) {
            if (cccd.charAt(i) < 48 && cccd.charAt(i) > 57) {
                return false;
            }
        }
        return true;
    }

    public static boolean isExistCCCD(String cccd) {
        if(staffService.getStaffByCCCD(cccd) != null) {
            return true;
        }
        return false;
    }

    public static boolean validateAddress(String address) {
        String regex = "^[a-zA-Z0-9]+$";
        Pattern p = Pattern.compile(regex);
        if (address == null) {
            return false;
        }
        Matcher m = p.matcher(address);
        return m.matches();
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
        if(staffService.getStaffByPhoneNumber(phoneNumber) != null) {
            return true;
        }
        return false;
    }

    public static boolean validateFullname(String fullname) {
        String regex = "^[a-zA-Z]{4,}(?: [a-zA-Z]+){0,2}$";
        Pattern p = Pattern.compile(regex);
        if (fullname == null) {
            return false;
        }
        Matcher m = p.matcher(fullname);
        return m.matches();
    }

    public static boolean validateImageAvatar(ImageView imageView) {
        if(imageView == null) {
            return false;
        }
        if(imageView.getImage() == null) {
            return false;
        }
        return true;
    }
}

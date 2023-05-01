package utc2.itk62.store.Validator;

import utc2.itk62.store.services.CustomerService;
import utc2.itk62.store.services.StaffService;

public class CustomerValidator {
    public static final CustomerService customerService = new CustomerService();

    public static boolean validateFullname(String fullname) {
        return StaffValidator.validateFullname(fullname);
    }

    public static boolean validateAddress(String address) {
        return StaffValidator.validateAddress(address);
    }

    public static boolean validateEmail(String email) {
        return StaffValidator.validateEmail(email);
    }

    public static boolean validatePhoneNumber(String phoneNumber) {
        return StaffValidator.validatePhoneNumber(phoneNumber);
    }

    public static boolean valideCccd(String cccd) {
        return StaffValidator.validateCCCD(cccd);
    }
}

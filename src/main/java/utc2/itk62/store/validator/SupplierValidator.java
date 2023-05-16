package utc2.itk62.store.validator;

public class SupplierValidator {
    public static boolean validateName(String name) {
        return StaffValidator.validateFullname(name);
    }

    public static boolean validateEmail(String email) {
        return StaffValidator.validateEmail(email);
    }

    public static boolean validatePhoneNumber(String phoneNumber) {
        return StaffValidator.validatePhoneNumber(phoneNumber);
    }

    public static boolean validateAddress(String address) {
        return StaffValidator.validateAddress(address);
    }
}

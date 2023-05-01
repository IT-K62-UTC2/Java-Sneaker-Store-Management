package utc2.itk62.store.Validator;

import utc2.itk62.store.models.Customer;
import utc2.itk62.store.services.CustomerService;

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

    public static boolean isExistEmail(String email, int id) {
        Customer customer = customerService.getCustomerByEmail(email);
        if(customer != null) {
            if (customer.getId() != id) {
                return true;
            }
        }
        return false;
    }

    public static boolean validatePhoneNumber(String phoneNumber) {
        return StaffValidator.validatePhoneNumber(phoneNumber);
    }

    public static boolean isExistPhoneNumber(String phoneNumber, int id) {
        Customer customer = customerService.getCustomerByPhoneNumber(phoneNumber);
        if(customer != null) {
            if (customer.getId() !=id) {
                return true;
            }
        }
        return false;
    }

    public static boolean validateCCCD(String cccd) {
        return StaffValidator.validateCCCD(cccd);
    }

    public static boolean isExistCCCD(String cccd, int id) {
        Customer customer = customerService.getCustomerByCCCD(cccd);

        if(customer != null) {
            if(customer.getId() != id) {
                return true;
            }
        }
        return false;
    }
}

package utc2.itk62.store.services;

import utc2.itk62.store.common.Paging;
import utc2.itk62.store.models.Customer;
import utc2.itk62.store.repositories.CustomerRepo;

import java.util.List;

public class CustomerService {
    private static final CustomerRepo customerRepo = new CustomerRepo();

    public List<Customer> getAllCustomer() {
        return customerRepo.getAllCustomer(new Paging(0, 0));
    }

    public boolean createCustomer(Customer customer) {
        if(customerRepo.createCustomer(customer) <=0) {
            return false;
        }
        return true;
    }

    public boolean deleteCustomer(int id) {
        if(customerRepo.deleteCustomer(id) <=0) {
            return false;
        }
        return true;
    }

    public boolean updateCustomer(Customer customer) {
        if(customerRepo.updateCustomer(customer) <=0) {
            return false;
        }
        return true;
    }
}

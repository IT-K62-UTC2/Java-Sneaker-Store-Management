package utc2.itk62.sneaker.services;

import utc2.itk62.sneaker.common.Paging;
import utc2.itk62.sneaker.models.Customer;
import utc2.itk62.sneaker.repositories.CustomerRepo;

import java.util.List;

public class CustomerService {
    private static final CustomerRepo customerRepo = new CustomerRepo();

    public List<Customer> getAllCustomer() {
        return customerRepo.getAllCustomer(new Paging(0, 0));
    }
}

package utc2.itk62.sneaker.services;

import utc2.itk62.sneaker.common.Paging;
import utc2.itk62.sneaker.models.Supplier;
import utc2.itk62.sneaker.repositories.SupplierRepo;

import java.util.List;

public class SupplierService {
    private static final SupplierRepo supplierRepo = new SupplierRepo();

    public List<Supplier> getAllSuppliers() {
        return supplierRepo.getAllSupplier(new Paging(0, 0));
    }
}

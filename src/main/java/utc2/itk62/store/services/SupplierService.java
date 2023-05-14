package utc2.itk62.store.services;

import utc2.itk62.store.models.Supplier;
import utc2.itk62.store.repositories.SupplierRepo;

import java.util.List;

public class SupplierService {
    private static final SupplierRepo supplierRepo = new SupplierRepo();

    public List<Supplier> getAllSuppliers() {
        return supplierRepo.getAllSupplier();
    }

    public boolean createSupplier(Supplier supplier) {
        if (supplierRepo.createSupplier(supplier) <= 0) {
            return false;
        }
        return true;
    }

    public boolean updateSupplier(Supplier supplier) {
        if (supplierRepo.updateSupplier(supplier) <= 0) {
            return false;
        }
        return true;
    }

    public boolean deleteSupplier(Supplier supplier) {
        if (supplierRepo.deleteSupplier(supplier.getId()) <= 0) {
            return false;
        }
        return true;
    }
}

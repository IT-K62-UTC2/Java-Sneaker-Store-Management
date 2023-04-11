package utc2.itk62.sneaker.services;

import utc2.itk62.sneaker.common.Paging;
import utc2.itk62.sneaker.models.Staff;
import utc2.itk62.sneaker.repositories.StaffRepo;

import java.util.List;

public class StaffService {
    private static final StaffRepo staffRepo = new StaffRepo();

    public List<Staff> getAllStaff()    {
        return staffRepo.getAllStaff(new Paging(0,0));
    }

    public boolean deleteStaff(Staff staff) {
        if (staffRepo.deleteStaff(staff.getId()) <= 0) {
            return false;
        }
        return true;
    }

    public boolean updateStaff(Staff staff) {
        if (staffRepo.updateStaff(staff) <= 0) {
            return false;
        }
        return true;
    }
}

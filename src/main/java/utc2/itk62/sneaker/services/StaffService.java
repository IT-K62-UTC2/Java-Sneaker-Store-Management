package utc2.itk62.sneaker.services;

import utc2.itk62.sneaker.common.Paging;
import utc2.itk62.sneaker.constant.Status;
import utc2.itk62.sneaker.models.Staff;
import utc2.itk62.sneaker.repositories.StaffRepo;
import utc2.itk62.sneaker.util.HashedPassword;

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

    public boolean createStaff(Staff staff) {
        staff.setPassword(HashedPassword.hashPassword(staff.getPassword()));
        if(staffRepo.createStaff(staff) <= 0) {
            return false;
        }
        return true;
    }

    public Staff getStaffByUserName(String username) {
        Staff staff = staffRepo.getStaffByUsername(username);
        if (staff != null) {
            return staff;
        }
        return null;
    }

    public Staff getStaffByEmail(String email) {
        Staff staff = staffRepo.getStaffByEmail(email);
        if (staff != null) {
            return staff;
        }
        return null;
    }

    public Staff getStaffByCCCD(String cccd) {
        Staff staff = staffRepo.getStaffByCCCD(cccd);
        if (staff != null) {
            return staff;
        }
        return null;
    }

    public Staff getStaffByPhoneNumber(String phoneNumber) {
        Staff staff = staffRepo.getStaffByPhoneNumber(phoneNumber);
        if (staff != null) {
            return staff;
        }
        return null;
    }
}

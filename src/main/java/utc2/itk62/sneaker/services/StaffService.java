package utc2.itk62.sneaker.services;

import utc2.itk62.sneaker.models.Staff;
import utc2.itk62.sneaker.repositories.StaffRepo;
import utc2.itk62.sneaker.until.HashedPassword;

public class StaffService {
    private static final StaffRepo staffRepo = new StaffRepo();

    public boolean CheckLogin(String username, String password) {
        Staff staff = staffRepo.getStaffByUsername(username);
        if (staff == null) {
            return false;
        }

        if (!HashedPassword.checkPassword(password, staff.getPassword())){
            return false;
        }

        return true;
    }
}

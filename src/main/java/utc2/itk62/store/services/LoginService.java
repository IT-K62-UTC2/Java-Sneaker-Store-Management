package utc2.itk62.store.services;

import utc2.itk62.store.models.Staff;
import utc2.itk62.store.repositories.StaffRepo;
import utc2.itk62.store.util.HashedPassword;

public class LoginService {
    private static final StaffRepo staffRepo = new StaffRepo();

    public Staff CheckLogin(String username, String password) {
        Staff staff = staffRepo.getStaffByUsername(username);
        if (staff == null) {
            return null;
        }

        if (!HashedPassword.checkPassword(password, staff.getPassword())){
            return null;
        }

        return staff;
    }
}

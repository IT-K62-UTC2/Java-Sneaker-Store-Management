package utc2.itk62.sneaker.services;

import utc2.itk62.sneaker.common.CustomKeyValueCombobox;
import utc2.itk62.sneaker.common.Paging;
import utc2.itk62.sneaker.models.Position;
import utc2.itk62.sneaker.models.Staff;
import utc2.itk62.sneaker.repositories.StaffRepo;
import utc2.itk62.sneaker.until.HashedPassword;

import java.util.ArrayList;
import java.util.List;

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

    public List<Staff> getAllStaff()    {
        return staffRepo.getAllStaff(new Paging(0,0));
    }

    public List<CustomKeyValueCombobox<Integer>> getAllIdPositionName() {
        PositionServie positionServie   = new PositionServie();
        List<Position> positionList = positionServie.getAllPosition();
        List<CustomKeyValueCombobox<Integer>> customKeyValueComboboxList = new ArrayList<>();
        for (Position position : positionList) {
            CustomKeyValueCombobox<Integer> cstb = new CustomKeyValueCombobox<Integer>(position.getId(), position.getName());
            customKeyValueComboboxList.add(cstb);
        }
        return customKeyValueComboboxList;
    }
}

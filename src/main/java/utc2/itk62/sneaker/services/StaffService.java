package utc2.itk62.sneaker.services;

import javafx.collections.ObservableList;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import utc2.itk62.sneaker.common.Paging;
import utc2.itk62.sneaker.constant.Status;
import utc2.itk62.sneaker.models.Staff;
import utc2.itk62.sneaker.repositories.StaffRepo;
import utc2.itk62.sneaker.util.FormatDateTime;
import utc2.itk62.sneaker.util.HashedPassword;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Timestamp;
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

    public void exportExcel(ObservableList<Staff> staffList) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Staff");
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("STT");
        header.createCell(1).setCellValue("Chức vụ");
        header.createCell(2).setCellValue("Tên đăng nhập");
        header.createCell(3).setCellValue("Họ và tên");
        header.createCell(4).setCellValue("Địa chỉ");
        header.createCell(5).setCellValue("Email");
        header.createCell(6).setCellValue("Số điện thoại");
        header.createCell(7).setCellValue("Giới tính");
        header.createCell(8).setCellValue("Avatar");
        header.createCell(9).setCellValue("Status");
        header.createCell(10).setCellValue("Ngày tạo");
        header.createCell(11).setCellValue("Ngày cập nhật");
        sheet.autoSizeColumn(0);
        for (int i = 1; i < staffList.size(); i++) {
            Staff item = staffList.get(i);
            Row row = sheet.createRow(i);
            row.createCell(0, CellType.NUMERIC).setCellValue(i);
            row.createCell(1,CellType.STRING).setCellValue(item.getPosition().getName());
            row.createCell(2,CellType.STRING).setCellValue(item.getUsername());
            row.createCell(3,CellType.STRING).setCellValue(item.getFullName());
            row.createCell(4, CellType.STRING).setCellValue(item.getAddress());
            row.createCell(5, CellType.STRING).setCellValue(item.getEmail());
            row.createCell(6, CellType.STRING).setCellValue(item.getPhoneNumber());
            row.createCell(7, CellType.STRING).setCellValue(item.getGender());
            row.createCell(8, CellType.STRING).setCellValue(item.getPathAvatar());
            row.createCell(9, CellType.NUMERIC).setCellValue(item.getStatus());
            row.createCell(10, CellType.STRING).setCellValue(FormatDateTime.formatTimeStampToString(item.getCreatedAt()));
            row.createCell(11,CellType.STRING).setCellValue(FormatDateTime.formatTimeStampToString(item.getUpdatedAt()));
            sheet.autoSizeColumn(i);
        }

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Lưu file Excel");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Excel files (*.xlsx)", "*.xlsx");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setInitialFileName("Staff");
        File file = fileChooser.showSaveDialog(new Stage());
        if (file != null) {
            FileOutputStream outputStream = null;
            try {
                outputStream = new FileOutputStream(file);
                workbook.write(outputStream);
                workbook.close();
                outputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }



}

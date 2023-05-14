package utc2.itk62.store.services;

import javafx.collections.ObservableList;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import utc2.itk62.store.constant.Status;
import utc2.itk62.store.models.Customer;
import utc2.itk62.store.repositories.CustomerRepo;
import utc2.itk62.store.util.FormatDateTime;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class CustomerService {
    private static final CustomerRepo customerRepo = new CustomerRepo();

    public List<Customer> getAllCustomer() {
        return customerRepo.getAllCustomer();
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

    public Customer getCustomerByEmail(String email) {
        Customer customer = customerRepo.getCustomerByEmail(email);
        if (customer != null) {
            return customer;
        }
        return null;
    }

    public Customer getCustomerByPhoneNumber(String phoneNumber) {
        Customer customer = customerRepo.getCustomerByPhoneNumber(phoneNumber);
        if (customer != null) {
            return customer;
        }
        return null;
    }

    public Customer getCustomerByCCCD(String cccd) {
        Customer customer = customerRepo.getCustomerByCccd(cccd);
        if (customer != null) {
            return customer;
        }
        return null;
    }

    public void exportExcel(ObservableList<Customer> customerList, Window window) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Customer");
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("STT");
        header.createCell(1).setCellValue("Mã khách hàng");
        header.createCell(2).setCellValue("Họ và tên");
        header.createCell(3).setCellValue("Địa chỉ");
        header.createCell(4).setCellValue("Email");
        header.createCell(5).setCellValue("Số điện thoại");
        header.createCell(6).setCellValue("CCCD");
        header.createCell(7).setCellValue("Giới tính");
        header.createCell(8).setCellValue("Status");
        header.createCell(9).setCellValue("Ngày tạo");
        header.createCell(10).setCellValue("Ngày cập nhật");
        sheet.autoSizeColumn(0);
        for (int i = 1; i <= customerList.size(); i++) {
            Customer item = customerList.get(i-1);
            Row row = sheet.createRow(i);
            row.createCell(0, CellType.NUMERIC).setCellValue(i);
            row.createCell(1, CellType.NUMERIC).setCellValue(item.getId());
            row.createCell(2,CellType.STRING).setCellValue(item.getFullName());
            row.createCell(3,CellType.STRING).setCellValue(item.getAddress());
            row.createCell(4,CellType.STRING).setCellValue(item.getEmail());
            row.createCell(5, CellType.STRING).setCellValue(item.getPhoneNumber());
            row.createCell(6, CellType.STRING).setCellValue(item.getCccd());
            row.createCell(7, CellType.STRING).setCellValue(item.getGender());
            row.createCell(8, CellType.STRING).setCellValue(item.getStatus() == Status.STATUS_ACTIVE ? "Đang hoạt động" : "Không hoạt động");
            row.createCell(9, CellType.STRING).setCellValue(FormatDateTime.timestampToString(item.getCreatedAt()));
            row.createCell(10, CellType.STRING).setCellValue(FormatDateTime.timestampToString(item.getUpdatedAt()));
            sheet.autoSizeColumn(i);
        }

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Lưu file Excel");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Excel files (*.xlsx)", "*.xlsx");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setInitialFileName("Customers");
        File file = fileChooser.showSaveDialog(window);
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

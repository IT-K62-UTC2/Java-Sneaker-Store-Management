package utc2.itk62.sneaker.services;

import javafx.collections.ObservableList;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import utc2.itk62.sneaker.common.Paging;
import utc2.itk62.sneaker.constant.Status;
import utc2.itk62.sneaker.models.Supplier;
import utc2.itk62.sneaker.repositories.SupplierRepo;
import utc2.itk62.sneaker.util.FormatDateTime;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class SupplierServices {
    private static final SupplierRepo supplierRepo = new SupplierRepo();

    public List<Supplier> getAllSupplier()    {
        return supplierRepo.getAllSupplier(new Paging(0,0));
    }

    public boolean deleteSupplier(Supplier supplier) {
        if (supplierRepo.deleteSupplier(supplier.getId()) <= 0) {
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

    public boolean createSupplier(Supplier supplier) {

        if(supplierRepo.createSupplier(supplier) <= 0) {
            return false;
        }
        return true;
    }

    public Supplier getSupplierByUserName(String username) {
        Supplier supplier = supplierRepo.getSupplierByUsername(username);
        if (supplier != null) {
            return supplier;
        }
        return null;
    }

    public Supplier getSupplierByEmail(String email) {
        Supplier supplier = supplierRepo.getSupplierByEmail(email);
        if (supplier != null) {
            return supplier;
        }
        return null;
    }



    public Supplier getSupplierByPhoneNumber(String phoneNumber) {
        Supplier supplier = supplierRepo.getSupplierByPhoneNumber(phoneNumber);
        if (supplier != null) {
            return supplier;
        }
        return null;
    }

    public void exportExcel(ObservableList<Supplier> supplierList) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Supplier");
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("STT");
        header.createCell(1).setCellValue("Mã nhà cung cấp");
        header.createCell(2).setCellValue("Tên nhà cung cấp");
        header.createCell(3).setCellValue("Địa chỉ");
        header.createCell(4).setCellValue("Email");
        header.createCell(5).setCellValue("Số điện thoại");
        header.createCell(6).setCellValue("Status");
        header.createCell(7).setCellValue("Ngày tạo");
        header.createCell(8).setCellValue("Ngày cập nhật");
        sheet.autoSizeColumn(0);
        for (int i = 1; i < supplierList.size(); i++) {
            Supplier item = supplierList.get(i);
            Row row = sheet.createRow(i);
            row.createCell(0, CellType.NUMERIC).setCellValue(i);
            row.createCell(1, CellType.NUMERIC).setCellValue(item.getId());
            row.createCell(2,CellType.STRING).setCellValue(item.getName());
            row.createCell(3, CellType.STRING).setCellValue(item.getAddress());
            row.createCell(4, CellType.STRING).setCellValue(item.getEmail());
            row.createCell(5, CellType.STRING).setCellValue(item.getPhoneNumber());
            row.createCell(6, CellType.STRING).setCellValue(item.getStatus() == Status.STATUS_ACTIVE ? "Đang hoạt động" : "Không hoạt động");
            row.createCell(7, CellType.STRING).setCellValue(FormatDateTime.formatTimeStampToString(item.getCreatedAt()));
            row.createCell(8,CellType.STRING).setCellValue(FormatDateTime.formatTimeStampToString(item.getUpdatedAt()));
            sheet.autoSizeColumn(i);
        }

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Lưu file Excel");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Excel files (*.xlsx)", "*.xlsx");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setInitialFileName("Supplier");
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

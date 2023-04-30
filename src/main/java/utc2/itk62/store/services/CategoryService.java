package utc2.itk62.store.services;

import javafx.collections.ObservableList;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import utc2.itk62.store.common.Paging;
import utc2.itk62.store.constant.Status;
import utc2.itk62.store.models.Category;
import utc2.itk62.store.repositories.CategoryRepo;
import utc2.itk62.store.util.FormatDateTime;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class CategoryService {
    private static final CategoryRepo categoryRepo = new CategoryRepo();

    public List<Category> getAllCategory()    {
        return categoryRepo.getAllCategories(new Paging(0,0));
    }

    public boolean createCategory(Category category) {
        if (categoryRepo.createCategory(category) <= 0) {
            return false;
        }
        return true;
    }

    public boolean deleteCategory(Category category) {
        if(categoryRepo.deleteCategory(category.getId()) <= 0) {
            return false;
        }
        return true;
    }

    public void exportExcel(ObservableList<Category> categoryList) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Category");
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("STT");
        header.createCell(1).setCellValue("Mã thể loại");
        header.createCell(2).setCellValue("Tên thể loại");
        header.createCell(3).setCellValue("Trạng thái");
        header.createCell(4).setCellValue("Ngày tạo");
        header.createCell(5).setCellValue("Ngày cập nhật");
        sheet.autoSizeColumn(0);
        for (int i = 1; i < categoryList.size(); i++) {
            Category item = categoryList.get(i);
            Row row = sheet.createRow(i);
            row.createCell(0, CellType.NUMERIC).setCellValue(i);
            row.createCell(1,CellType.NUMERIC).setCellValue(item.getId());
            row.createCell(2,CellType.STRING).setCellValue(item.getName());
            row.createCell(3, CellType.STRING).setCellValue(item.getStatus() == Status.STATUS_ACTIVE ? "Đang hoạt động" : "Không hoạt động");
            row.createCell(4, CellType.STRING).setCellValue(FormatDateTime.timestampToString(item.getCreatedAt()));
            row.createCell(5,CellType.STRING).setCellValue(FormatDateTime.timestampToString(item.getUpdatedAt()));
            sheet.autoSizeColumn(i);
        }

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Lưu file Excel");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Excel files (*.xlsx)", "*.xlsx");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setInitialFileName("Category");
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

    public boolean updateCategory(Category currentCategory) {
        if(categoryRepo.updateCategory(currentCategory) <= 0) {
            return false;
        }
        return true;
    }
}

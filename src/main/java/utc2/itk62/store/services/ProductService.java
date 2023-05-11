package utc2.itk62.store.services;

import javafx.collections.ObservableList;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import utc2.itk62.store.common.Paging;
import utc2.itk62.store.constant.Status;
import utc2.itk62.store.models.Product;
import utc2.itk62.store.models.Staff;
import utc2.itk62.store.repositories.ProductRepo;
import utc2.itk62.store.util.FormatDateTime;
import utc2.itk62.store.util.FormatDouble;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ProductService {
    private static final ProductRepo productRepo = new ProductRepo();

    public Product getProductById(int id) {
        return productRepo.getProductById(id);
    }

    public List<Product> getProductsByIdCategory(int idCategory) {
        return productRepo.getProductListByIdCategory(idCategory);
    }

    public List<Product> getProductsByName(String name) {
        return productRepo.getProductsByName(name);
    }

    public boolean updateProduct(Product product) {
        if(productRepo.updateProduct(product) <=0) {
            return false;
        }
        return true;
    }

    public boolean updateQuantityProduct(int quantity, int idProduct) {
        Product product = new Product();
        product.setQuantity(quantity);
        product.setId(idProduct);
        if(productRepo.updateQuantityProduct(product) <= 0) {
            return  false;
        }
        return true;
    }

    public List<Product> getAllProduct() {
        return productRepo.getAllProducts(new Paging(0,0));
    }

    public boolean createProduct(Product product) {
        if(productRepo.createProduct(product) <=0) {
            return false;
        }
        return true;
    }

    public boolean deleteProduct(Product product) {
        if (productRepo.deleteProduct(product.getId()) <= 0) {
            return false;
        }
        return true;
    }

    public List<Product> getProductByIdSupplier(int id) {
        return productRepo.getProductListByIdSupplier(id);
    }

    public void exportExcel(ObservableList<Product> productList, Window window) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Product");
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("STT");
        header.createCell(1).setCellValue("Mã sản phẩm");
        header.createCell(2).setCellValue("Nhà cung cấp");
        header.createCell(3).setCellValue("Thể loại");
        header.createCell(4).setCellValue("Tên sản phẩm");
        header.createCell(5).setCellValue("Số lượng");
        header.createCell(6).setCellValue("Giá");
        header.createCell(7).setCellValue("Mô tả sản phẩm");
        header.createCell(8).setCellValue("Trạng thái");
        header.createCell(9).setCellValue("Ngày tạo");
        header.createCell(10).setCellValue("Ngày cập nhật");
        sheet.autoSizeColumn(0);
        for (int i = 1; i <= productList.size(); i++) {
            Product item = productList.get(i-1);
            Row row = sheet.createRow(i);
            row.createCell(0, CellType.NUMERIC).setCellValue(i);
            row.createCell(1, CellType.NUMERIC).setCellValue(item.getId());
            row.createCell(2,CellType.STRING).setCellValue(item.getSupplier().getName());
            row.createCell(3,CellType.STRING).setCellValue(item.getCategory().getName());
            row.createCell(4,CellType.STRING).setCellValue(item.getName());
            row.createCell(5, CellType.NUMERIC).setCellValue(item.getQuantity());
            row.createCell(6, CellType.STRING).setCellValue(FormatDouble.toString(item.getPrice()));
            row.createCell(7, CellType.STRING).setCellValue(item.getDescription());
            row.createCell(8, CellType.STRING).setCellValue(item.getStatus() == Status.STATUS_ACTIVE ? "Đang hoạt động" : "Không hoạt động");
            row.createCell(9, CellType.STRING).setCellValue(FormatDateTime.timestampToString(item.getCreatedAt()));
            row.createCell(10,CellType.STRING).setCellValue(FormatDateTime.timestampToString(item.getUpdatedAt()));
            sheet.autoSizeColumn(i);
        }

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Lưu file Excel");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Excel files (*.xlsx)", "*.xlsx");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setInitialFileName("Products");
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

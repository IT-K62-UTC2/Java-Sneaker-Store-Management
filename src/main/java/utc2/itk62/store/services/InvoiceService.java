package utc2.itk62.store.services;

import javafx.collections.ObservableList;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import utc2.itk62.store.common.FromAndToDate;
import utc2.itk62.store.models.Invoice;
import utc2.itk62.store.models.InvoiceDetail;
import utc2.itk62.store.repositories.InvoiceRepo;
import utc2.itk62.store.util.FormatDateTime;
import utc2.itk62.store.util.FormatDouble;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class InvoiceService {
    private static final InvoiceRepo invoiceRepo = new InvoiceRepo();

    public Invoice createInvoice(Invoice invoice) {
        return invoiceRepo.createInvoice(invoice);
    }

    public List<Invoice> getAllInvoice(FromAndToDate fromAndToDate) {
        return invoiceRepo.getAllInvoices( fromAndToDate);
    }

    public void exportExcel(ObservableList<Invoice> invoiceList, Window window) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Invoice");
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("STT");
        header.createCell(1).setCellValue("Staff");
        header.createCell(2).setCellValue("Customer");
        header.createCell(3).setCellValue("Delivery address");
        header.createCell(4).setCellValue("Delivery phone number");
        header.createCell(5).setCellValue("Product");
        header.createCell(6).setCellValue("Quantity");
        header.createCell(7).setCellValue("Price");
        header.createCell(8).setCellValue("Total");
        header.createCell(9).setCellValue("Image");
        header.createCell(10).setCellValue("Created at");
        sheet.autoSizeColumn(0);
        int count = 1;
        for (int i = 1; i <= invoiceList.size(); i++) {
            Invoice invoice = invoiceList.get(i-1);
            List<InvoiceDetail> listInvoiceDetail = invoice.getListInvoiceDetails();
            for(int j = 1; j <= listInvoiceDetail.size(); j++) {
                InvoiceDetail invoiceDetail = listInvoiceDetail.get(j-1);
                Row row = sheet.createRow(count);
                row.createCell(0, CellType.NUMERIC).setCellValue(count);
                row.createCell(1, CellType.STRING).setCellValue(invoice.getStaff().getFullName());
                row.createCell(2,CellType.STRING).setCellValue(invoice.getCustomer().getFullName());
                row.createCell(3,CellType.STRING).setCellValue(invoice.getDeliveryAddress());
                row.createCell(4,CellType.STRING).setCellValue(invoice.getDeliveryPhoneNumber());
                row.createCell(5, CellType.STRING).setCellValue(invoiceDetail.getProduct().toString());
                row.createCell(6, CellType.STRING).setCellValue(invoiceDetail.getProductQuantity());
                row.createCell(7, CellType.STRING).setCellValue(FormatDouble.toString(invoiceDetail.getProduct().getPrice()));
                row.createCell(8, CellType.STRING).setCellValue(FormatDouble.toString(invoiceDetail.getMoneyTotal()));
                row.createCell(9, CellType.STRING).setCellValue(invoiceDetail.getProduct().getAvatar());
                row.createCell(10, CellType.STRING).setCellValue(FormatDateTime.timestampToString(invoice.getCreatedAt()));
                count++;
            }
        }

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Lưu file Excel");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Excel files (*.xlsx)", "*.xlsx");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setInitialFileName("Invoice");
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

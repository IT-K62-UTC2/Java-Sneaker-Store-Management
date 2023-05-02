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
import utc2.itk62.store.models.Invoice;
import utc2.itk62.store.models.InvoiceDetail;
import utc2.itk62.store.models.Staff;
import utc2.itk62.store.repositories.InvoiceDetailRepo;
import utc2.itk62.store.util.FormatDateTime;
import utc2.itk62.store.util.FormatDouble;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class InvoiceDetailsService {
    private static final InvoiceDetailRepo invoiceDetailRepo = new InvoiceDetailRepo();

    public boolean createInvoiceDetail(InvoiceDetail invoiceDetail) {
        if(invoiceDetailRepo.createInvoiceDetail(invoiceDetail) <= 0) {
            return false;
        }
        return true;
    }

    public List<InvoiceDetail> getInvoiceDetailByIdInvoice(int id) {
        return invoiceDetailRepo.getAllInvoicesDetailByInvoiceId(id);
    }

}

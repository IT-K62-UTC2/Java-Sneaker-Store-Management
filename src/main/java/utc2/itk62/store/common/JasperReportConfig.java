package utc2.itk62.store.common;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import utc2.itk62.store.models.Invoice;
import utc2.itk62.store.models.InvoiceDetail;
import utc2.itk62.store.util.FormatDateTime;
import utc2.itk62.store.util.FormatDouble;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JasperReportConfig {
    private static final String PATH_EXPORT_INVOICE = "src/main/resources/utc2/itk62/store/report/invoice.jrxml";

    public static JasperPrint createJasperPrintInvoice(Invoice invoice) {
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("idInvoice", invoice.getId());
            params.put("staff", invoice.getStaff().getFullName());
            params.put("customer",invoice.getCustomer().getFullName());
            params.put("deliveryAddress", invoice.getDeliveryAddress());
            params.put("deliveryPhoneNumber", invoice.getDeliveryPhoneNumber());
            params.put("tax", "0");
            params.put("discount", "-0");
            params.put("subTotal", FormatDouble.toString(invoice.getMoneyTotal()));
            params.put("totalDue", FormatDouble.toString(invoice.getMoneyTotal()));
            params.put("date", FormatDateTime.dateToString(invoice.getCreatedAt()));
            params.put("time", FormatDateTime.timeToString(invoice.getCreatedAt()));
            List<InvoiceDetail> invoiceDetailList = invoice.getListInvoiceDetails();
            File fileTemp = new File(PATH_EXPORT_INVOICE);
            JasperReport jasperReport = JasperCompileManager.compileReport(fileTemp.getAbsolutePath());
            JasperPrint jasperPrint;
            if(invoiceDetailList.isEmpty()) {
                jasperPrint = JasperFillManager.fillReport(jasperReport, params, new JREmptyDataSource());
            } else {
                JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(invoiceDetailList);
                jasperPrint = JasperFillManager.fillReport(jasperReport, params, jrBeanCollectionDataSource);
            };
            return  jasperPrint;
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }
}

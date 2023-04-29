package utc2.itk62.sneaker.services;

import utc2.itk62.sneaker.common.Paging;
import utc2.itk62.sneaker.models.Invoice;
import utc2.itk62.sneaker.repositories.InvoiceRepo;

import java.util.List;

public class InvoiceService {
    private static final InvoiceRepo invoiceRepo = new InvoiceRepo();

    public Invoice createInvoice(Invoice invoice) {
        return invoiceRepo.createInvoice(invoice);
    }

    public List<Invoice> getAllInvoice() {
        return invoiceRepo.getAllInvoices(new Paging(0, 0));
    }

}

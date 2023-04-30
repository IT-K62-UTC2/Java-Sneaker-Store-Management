package utc2.itk62.store.services;

import utc2.itk62.store.common.Paging;
import utc2.itk62.store.models.Invoice;
import utc2.itk62.store.repositories.InvoiceRepo;

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

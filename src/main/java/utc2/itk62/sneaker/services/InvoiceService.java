package utc2.itk62.sneaker.services;

import utc2.itk62.sneaker.models.Invoice;
import utc2.itk62.sneaker.repositories.InvoiceRepo;

public class InvoiceService {
    private static final InvoiceRepo invoiceRepo = new InvoiceRepo();

    public Invoice createInvoice(Invoice invoice) {
        return invoiceRepo.createInvoice(invoice);
    }

}

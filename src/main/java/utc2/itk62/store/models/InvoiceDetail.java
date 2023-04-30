package utc2.itk62.store.models;

import java.sql.Timestamp;

public class InvoiceDetail {
    private int id;
    private Invoice invoice;
    private Product product;
    private int productQuantity;
    private double moneyTotal;
    private int status;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public InvoiceDetail() {
    }


    public InvoiceDetail(Invoice invoice, Product product, int productQuantity, double moneyTotal) {
        this.invoice = invoice;
        this.product = product;
        this.productQuantity = productQuantity;
        this.moneyTotal = moneyTotal;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public Product getProduct() {
        return product;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public void setMoneyTotal(double moneyTotal) {
        this.moneyTotal = moneyTotal;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public double getMoneyTotal() {
        return moneyTotal;
    }

    public int getStatus() {
        return status;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }
}

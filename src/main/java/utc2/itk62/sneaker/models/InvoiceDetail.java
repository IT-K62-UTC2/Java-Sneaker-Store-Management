package utc2.itk62.sneaker.models;

import java.sql.Timestamp;

public class InvoiceDetail {
    private int id;
    private int idInvoice;
    private int idProduct;
    private int productQuantity;
    private double moneyTotal;
    private int status;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public InvoiceDetail() {
    }

    public InvoiceDetail(int idInvoice, int idProduct, int productQuantity, double moneyTotal) {
        this.idInvoice = idInvoice;
        this.idProduct = idProduct;
        this.productQuantity = productQuantity;
        this.moneyTotal = moneyTotal;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdInvoice() {
        return idInvoice;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
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

    public void setIdInvoice(int idInvoice) {
        this.idInvoice = idInvoice;
    }

    public int getIdProduct() {
        return idProduct;
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

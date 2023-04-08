package utc2.itk62.sneaker.models;

import java.security.Timestamp;

public class InvoiceDetail {
    private int id;
    private int idStaff;
    private int idCustomer;
    private int idProduct;
    private int productQuantity;
    private double moneyTotal;
    private int status;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public InvoiceDetail() {
    }

    public InvoiceDetail(int idStaff, int idCustomer, int idProduct, int productQuantity, double moneyTotal) {
        this.idStaff = idStaff;
        this.idCustomer = idCustomer;
        this.idProduct = idProduct;
        this.productQuantity = productQuantity;
        this.moneyTotal = moneyTotal;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdStaff(int idStaff) {
        this.idStaff = idStaff;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
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

    public int getIdStaff() {
        return idStaff;
    }

    public int getIdCustomer() {
        return idCustomer;
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

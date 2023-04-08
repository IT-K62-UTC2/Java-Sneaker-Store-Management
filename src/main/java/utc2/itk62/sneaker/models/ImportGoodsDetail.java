package utc2.itk62.sneaker.models;

import java.sql.Timestamp;

public class ImportGoodsDetail {
    private int id;
    private int idImportGoods;
    private int idProduct;
    private int quantity;
    private double unitPrice;
    private double moneyTotal;
    private int status;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public ImportGoodsDetail() {
    }

    public ImportGoodsDetail(int idImportGoods, int idProduct, int quantity, double unitPrice, double moneyTotal) {
        this.idImportGoods = idImportGoods;
        this.idProduct = idProduct;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.moneyTotal = moneyTotal;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdImportGoods(int idImportGoods) {
        this.idImportGoods = idImportGoods;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
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

    public int getIdImportGoods() {
        return idImportGoods;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
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

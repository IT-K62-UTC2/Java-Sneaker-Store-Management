package utc2.itk62.store.models;

import java.sql.Timestamp;

public class ImportGoodsDetail {
    private int id;
    private ImportGoods importGoods;
    private Product product;
    private int quantity;
    private double moneyTotal;
    private int status;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public ImportGoodsDetail() {
    }

    public ImportGoodsDetail(int id, ImportGoods importGoods, Product product, int quantity, double moneyTotal, int status, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.importGoods = importGoods;
        this.product = product;
        this.quantity = quantity;
        this.moneyTotal = moneyTotal;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }



    public void setId(int id) {
        this.id = id;
    }




    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

    public ImportGoods getImportGoods() {
        return importGoods;
    }

    public void setImportGoods(ImportGoods importGoods) {
        this.importGoods = importGoods;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
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

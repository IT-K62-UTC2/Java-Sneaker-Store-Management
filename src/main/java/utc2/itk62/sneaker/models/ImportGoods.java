package utc2.itk62.sneaker.models;

import java.security.Timestamp;

public class ImportGoods {
    private int id;
    private int idStaff;
    private int quantity;
    private int moneyTotal;
    private int status;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public ImportGoods() {
    }

    public ImportGoods(int idStaff, int quantity, int moneyTotal) {
        this.idStaff = idStaff;
        this.quantity = quantity;
        this.moneyTotal = moneyTotal;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdStaff(int idStaff) {
        this.idStaff = idStaff;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setMoneyTotal(int moneyTotal) {
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

    public int getQuantity() {
        return quantity;
    }

    public int getMoneyTotal() {
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

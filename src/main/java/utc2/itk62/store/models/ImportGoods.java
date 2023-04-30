package utc2.itk62.store.models;

import java.sql.Timestamp;
import java.util.List;

public class ImportGoods {
    private int id;
    private Staff staff;
    private int quantity;
    private int moneyTotal;
    private int status;
    private List<ImportGoodsDetail> importGoodsDetailList;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public ImportGoods() {
    }

    public ImportGoods(int id, Staff staff, int quantity, int moneyTotal, int status, List<ImportGoodsDetail> importGoodsDetailList, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.staff = staff;
        this.quantity = quantity;
        this.moneyTotal = moneyTotal;
        this.status = status;
        this.importGoodsDetailList = importGoodsDetailList;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public List<ImportGoodsDetail> getImportGoodsDetailList() {
        return importGoodsDetailList;
    }

    public void setImportGoodsDetailList(List<ImportGoodsDetail> importGoodsDetailList) {
        this.importGoodsDetailList = importGoodsDetailList;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Staff getStaff() {
        return staff;
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

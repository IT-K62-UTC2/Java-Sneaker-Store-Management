package utc2.itk62.sneaker.models;

import java.sql.Timestamp;

public class Invoice {
    private int id;
    private int idStaff;
    private int idCustomer;
    private double moneyTotal;
    private String deliveryAddress;
    private String deliveryPhoneNumber;
    private int status;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public Invoice() {
    }

    public Invoice(int idStaff, int idCustomer, double moneyTotal, String deliveryAddress, String deliveryPhoneNumber) {
        this.idStaff = idStaff;
        this.idCustomer = idCustomer;
        this.moneyTotal = moneyTotal;
        this.deliveryAddress = deliveryAddress;
        this.deliveryPhoneNumber = deliveryPhoneNumber;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public void setDeliveryPhoneNumber(String deliveryPhoneNumber) {
        this.deliveryPhoneNumber = deliveryPhoneNumber;
    }

    public void setIdStaff(int idStaff) {
        this.idStaff = idStaff;
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

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public String getDeliveryPhoneNumber() {
        return deliveryPhoneNumber;
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

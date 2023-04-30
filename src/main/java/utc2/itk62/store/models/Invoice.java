package utc2.itk62.store.models;

import java.sql.Timestamp;
import java.util.List;

public class Invoice {
    private int id;
    private Staff staff;
    private Customer customer;
    private int totalQuantity;
    private double moneyTotal;
    private String deliveryAddress;
    private String deliveryPhoneNumber;
    private int status;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private List<InvoiceDetail> listInvoiceDetails;

    public Invoice() {
    }

    public List<InvoiceDetail> getListInvoiceDetails() {
        return listInvoiceDetails;
    }

    public void setListInvoiceDetails(List<InvoiceDetail> listInvoiceDetails) {
        this.listInvoiceDetails = listInvoiceDetails;
    }

    public Invoice(int id, Staff staff, Customer customer, int totalQuantity, double moneyTotal, String deliveryAddress, String deliveryPhoneNumber, int status, Timestamp createdAt, Timestamp updatedAt, List<InvoiceDetail> listInvoiceDetails) {
        this.id = id;
        this.staff = staff;
        this.customer = customer;
        this.totalQuantity = totalQuantity;
        this.moneyTotal = moneyTotal;
        this.deliveryAddress = deliveryAddress;
        this.deliveryPhoneNumber = deliveryPhoneNumber;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.listInvoiceDetails = listInvoiceDetails;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public void setId(int id) {
        this.id = id;
    }


    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public void setDeliveryPhoneNumber(String deliveryPhoneNumber) {
        this.deliveryPhoneNumber = deliveryPhoneNumber;
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

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Staff getStaff() {
        return staff;
    }

    public Customer getCustomer() {
        return customer;
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

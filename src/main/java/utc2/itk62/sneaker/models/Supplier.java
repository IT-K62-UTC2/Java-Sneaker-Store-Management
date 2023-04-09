package utc2.itk62.sneaker.models;

import java.sql.Timestamp;

public class Supplier {
    private int id;
    private String address;
    private String phoneNumber;
    private int productQuantity;
    private int status;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public Supplier() {
    }

    public Supplier(String address, String phoneNumber, int productQuantity) {
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.productQuantity = productQuantity;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setQuantityProduct(int quantityProduct) {
        this.productQuantity = quantityProduct;
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

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getQuantityProduct() {
        return productQuantity;
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

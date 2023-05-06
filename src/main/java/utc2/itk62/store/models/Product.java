package utc2.itk62.store.models;

import utc2.itk62.store.util.FormatDouble;

import java.sql.Timestamp;

public class Product {
    private int id;
    private Supplier supplier;
    private Category category;
    private String name;
    private String description;
    private int quantity;
    private double price;
    private double importPrice;
    private String avatar;
    private int status;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    private String strPrice;

    public Product() {
    }

    public Product(int id, Supplier supplier, Category category, String name, String description, int quantity, double price, double importPrice, String avatar, int status, Timestamp createdAt, Timestamp updatedAt, String strPrice) {
        this.id = id;
        this.supplier = supplier;
        this.category = category;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.importPrice = importPrice;
        this.avatar = avatar;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.strPrice = strPrice;
    }

    public double getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(double importPrice) {
        this.importPrice = importPrice;
    }

    public void setStrPrice(String strPrice) {
        this.strPrice = strPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Supplier getSupplier() {
        return supplier;
    }


    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
        strPrice = FormatDouble.toString(this.price);
    }

    public String getStrPrice() {
        return strPrice;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public String getName() {
        return name;
    }


    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public String getAvatar() {
        return avatar;
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

    @Override
    public String toString() {
        return name;
    }
}

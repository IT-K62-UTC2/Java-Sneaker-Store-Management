package utc2.itk62.sneaker.models;

import java.sql.Timestamp;

public class Product {
    private int id;
    private Supplier supplier;
    private Category category;
    private String name;
    private double size;
    private String description;
    private double price;
    private String avatar;
    private int status;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public Product() {
    }

    public Product(Supplier supplier, Category category, String name, double size, String description, double price, String avatar) {
        this.supplier = supplier;
        this.category = category;
        this.name = name;
        this.size = size;
        this.description = description;
        this.price = price;
        this.avatar = avatar;
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

    public void setSize(double size) {
        this.size = size;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
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

    public double getSize() {
        return size;
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
}

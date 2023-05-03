package utc2.itk62.store.models;

import java.sql.Timestamp;

public class Customer {
    private int id;
    private String fullName;
    private String address;
    private String email;
    private String phoneNumber;
    private String cccd;
    private String gender;
    private int status;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public Customer() {
    }

    public Customer(String fullName, String address, String email, String phoneNumber, String cccd, String gender) {
        this.fullName = fullName;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.cccd = cccd;
        this.gender = gender;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public String getFullName() {
        return fullName;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getCccd() {
        return cccd;
    }

    public String getGender() {
        return gender;
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
        return fullName+" - " + email;
    }
}

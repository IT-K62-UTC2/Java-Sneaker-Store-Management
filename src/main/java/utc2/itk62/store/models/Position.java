package utc2.itk62.store.models;

import java.sql.Timestamp;
import java.util.List;

public class Position {
    private int id;
    private String name;
    private int status;
    private List<Staff> staffList;
    private List<Auth> authList;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public Position() {
    }

    public Position(String name, List<Staff> staffList, List<Auth> authList) {
        this.name = name;
        this.staffList = staffList;
        this.authList = authList;
    }

    public List<Auth> getAuthList() {
        return authList;
    }

    public void setAuthList(List<Auth> authList) {
        this.authList = authList;
    }

    public List<Staff> getStaffList() {
        return staffList;
    }

    public void setStaffList(List<Staff> staffList) {
        this.staffList = staffList;
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

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}

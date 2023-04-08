package utc2.itk62.sneaker.models;

import java.sql.Timestamp;

public class Position {
    private int id;
    private String name;
    private int status;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public Position() {
    }

    public Position(String name) {
        this.name = name;
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


}

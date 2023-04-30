package utc2.itk62.store.models;

import java.sql.Timestamp;
import java.util.Date;

public class Discount {
    private int id;
    private String name;
    private int remaining;
    private Date timeExpiry;
    private int status;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public Discount() {
    }

    public Discount(String name, int remaining, Date timeExpiry) {
        this.name = name;
        this.remaining = remaining;
        this.timeExpiry = timeExpiry;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRemaining(int remaining) {
        this.remaining = remaining;
    }

    public void setTimeExpiry(Date timeExpiry) {
        this.timeExpiry = timeExpiry;
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

    public String getName() {
        return name;
    }

    public int getRemaining() {
        return remaining;
    }

    public Date getTimeExpiry() {
        return timeExpiry;
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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDateTime;

/**
 *
 * @author laptop 368
 */
public class Service {

    private int packageID;
    private String packageName;
    private String description;
    private String type;
    private double price;
    private int duration;
    private String createdAt;

    public Service() {
    }

    public Service(int packageID, String packageName, String description, String type, double price, int duration, String createdAt) {
        this.packageID = packageID;
        this.packageName = packageName;
        this.description = description;
        this.type = type;
        this.price = price;
        this.duration = duration;
        this.createdAt = createdAt;
    }

    public int getPackageID() {
        return packageID;
    }

    public void setPackageID(int packageID) {
        this.packageID = packageID;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Service{" + "packageID=" + packageID + ", packageName=" + packageName + ", description=" + description + ", type=" + type + ", price=" + price + ", duration=" + duration + ", createdAt=" + createdAt + '}';
    }

   
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Gigabyte
 */
public class Invoice {
    private int invoiceID;
    private Customer customerID;
    private int examinationID;
    private double totalAmount;
    private String paymentStatus;
    private String paymentDate;
    private String paymentMethod;
    private String createdAt;

    public Invoice() {
    }

    public Invoice(int invoiceID, Customer customerID, int examinationID, double totalAmount, String paymentStatus, String paymentDate, String paymentMethod, String createdAt) {
        this.invoiceID = invoiceID;
        this.customerID = customerID;
        this.examinationID = examinationID;
        this.totalAmount = totalAmount;
        this.paymentStatus = paymentStatus;
        this.paymentDate = paymentDate;
        this.paymentMethod = paymentMethod;
        this.createdAt = createdAt;
    }

    public int getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(int invoiceID) {
        this.invoiceID = invoiceID;
    }

    public Customer getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Customer customerID) {
        this.customerID = customerID;
    }

    public int getExaminationID() {
        return examinationID;
    }

    public void setExaminationID(int examinationID) {
        this.examinationID = examinationID;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    
}

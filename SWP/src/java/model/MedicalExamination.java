/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Gigabyte
 */
public class MedicalExamination {
    private int examinationID;
    private String examinationDate;
    private String consultationType;
    private Customer customerId;
    private String status;
    private Professional consultantId;
    private String note;
    private String createdAt;

    public MedicalExamination() {
    }

    public MedicalExamination(int examinationID, String examinationDate, String consultationType, Customer customerId, String status, Professional consultantId, String note, String createdAt) {
        this.examinationID = examinationID;
        this.examinationDate = examinationDate;
        this.consultationType = consultationType;
        this.customerId = customerId;
        this.status = status;
        this.consultantId = consultantId;
        this.note = note;
        this.createdAt = createdAt;
    }

    public int getExaminationID() {
        return examinationID;
    }

    public void setExaminationID(int examinationID) {
        this.examinationID = examinationID;
    }

    public String getExaminationDate() {
        return examinationDate;
    }

    public void setExaminationDate(String examinationDate) {
        this.examinationDate = examinationDate;
    }

    public String getConsultationType() {
        return consultationType;
    }

    public void setConsultationType(String consultationType) {
        this.consultationType = consultationType;
    }

    public Customer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Customer customerId) {
        this.customerId = customerId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Professional getConsultantId() {
        return consultantId;
    }

    public void setConsultantId(Professional consultantId) {
        this.consultantId = consultantId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
    
    
}

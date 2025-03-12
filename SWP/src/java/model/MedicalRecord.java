/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
public class MedicalRecord {
    
    
    private int examinationID;
    private String diagnosis;
    private String treatmentPlan;
    private String medicationsPrescribed;
    private String createdAt; // Sử dụng String để hiển thị định dạng dd/MM/yyyy HH:mm
    private String notes;

    public MedicalRecord() {
    }

    public MedicalRecord(int examinationID, String diagnosis, String treatmentPlan, String medicationsPrescribed, String createdAt, String notes) {
        this.examinationID = examinationID;
        this.diagnosis = diagnosis;
        this.treatmentPlan = treatmentPlan;
        this.medicationsPrescribed = medicationsPrescribed;
        this.createdAt = createdAt;
        this.notes = notes;
    }

    public int getExaminationID() {
        return examinationID;
    }

    public void setExaminationID(int examinationID) {
        this.examinationID = examinationID;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getTreatmentPlan() {
        return treatmentPlan;
    }

    public void setTreatmentPlan(String treatmentPlan) {
        this.treatmentPlan = treatmentPlan;
    }

    public String getMedicationsPrescribed() {
        return medicationsPrescribed;
    }

    public void setMedicationsPrescribed(String medicationsPrescribed) {
        this.medicationsPrescribed = medicationsPrescribed;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "MedicalRecord{" + "examinationID=" + examinationID + ", diagnosis=" + diagnosis + ", treatmentPlan=" + treatmentPlan + ", medicationsPrescribed=" + medicationsPrescribed + ", createdAt=" + createdAt + ", notes=" + notes + '}';
    }
    
    
    
    
}

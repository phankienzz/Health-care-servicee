package model;

import java.io.Serializable;

/**
 * Model đại diện cho bảng MedicalService trong cơ sở dữ liệu
 */
public class Medicalservice implements Serializable {
    private int examinationID; // Khóa ngoại tham chiếu đến MedicalExamination
    private int packageID;    // Khóa ngoại tham chiếu đến Service

    // Constructor mặc định
    public Medicalservice() {
    }

    // Constructor đầy đủ
    public Medicalservice(int examinationID, int packageID) {
        this.examinationID = examinationID;
        this.packageID = packageID;
    }

    // Getter và Setter
    public int getExaminationID() {
        return examinationID;
    }

    public void setExaminationID(int examinationID) {
        this.examinationID = examinationID;
    }

    public int getPackageID() {
        return packageID;
    }

    public void setPackageID(int packageID) {
        this.packageID = packageID;
    }

    // Override toString để dễ debug
    @Override
    public String toString() {
        return "MedicalService{" +
                "examinationID=" + examinationID +
                ", packageID=" + packageID +
                '}';
    }

    // Override equals và hashCode vì đây là khóa chính ghép
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Medicalservice that = (Medicalservice) o;
        return examinationID == that.examinationID && packageID == that.packageID;
    }

    @Override
    public int hashCode() {
        int result = examinationID;
        result = 31 * result + packageID;
        return result;
    }
}
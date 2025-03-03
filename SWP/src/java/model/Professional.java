
package model;

import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 *
 * @author Phan Huu Kien
 */
public class Professional extends Staff {

    private String specialization;
    private String officeHours;
    private String qualification;
    private String biography;
    private String status;
    private Date createdAt;

    public Professional() {
    }

    

    // Constructor có tham số
    public Professional(int staffID, String fullName, String email, String password, Date dateOfBirth, String gender, String address, String phone, Date hireDate, String status, byte[] profilePicture, String specialization, String officeHours, String qualification, String biography, Date createdAt) {
        // Chuyển Date thành String khi gọi super() cho dateOfBirth và hireDate
        //int staffID, String fullName, String email, String password, String phone, String gender, String dateOfBirth, String address, String hireDate, int roleID, String status, String profilePicture
        super(staffID, fullName, email, password, phone,gender,dateOfBirth.toString(),address, hireDate.toString(), 0, status, new String(profilePicture, StandardCharsets.ISO_8859_1));  // convert to String
        this.specialization = specialization;
        this.officeHours = officeHours;
        this.qualification = qualification;
        this.biography = biography;
        this.createdAt = createdAt;
        this.status=status;
    }
    public int getStaffID(){
        return super.getStaffID();
    }
        public String getName(){
        return super.getFullName();
    }
        public String getEmail(){
            return super.getEmail();
        }
    public String getPhone(){
        return super.getPhone();
    }
    public String getAddress(){
        return super.getAddress();
    }
    public String getDOB(){
        return super.getDateOfBirth();
    }
    public String getGender(){
        return super.getGender();
    }
    // Getters và Setters (lấy và gán giá trị)
    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getOfficeHours() {
        return officeHours;
    }

    public void setOfficeHours(String officeHours) {
        this.officeHours = officeHours;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getDepartment() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

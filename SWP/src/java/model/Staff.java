/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author jaxbo
 */
public class Staff {
    private int staffID;
    private String fullName;
    private String email;
    private String password;
    private String phone;
    private String hireDate;
    private int roleID;
    private String status;
    private String department;
    private String profilePicture;

    public Staff() {
    }

    public Staff(int staffID, String fullName, String email, String password, String phone, String hireDate, int roleID, String status, String department, String profilePicture) {
        this.staffID = staffID;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.hireDate = hireDate;
        this.roleID = roleID;
        this.status = status;
        this.department = department;
        this.profilePicture = profilePicture;
    }

    public int getStaffID() {
        return staffID;
    }

    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHireDate() {
        return hireDate;
    }

    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    @Override
    public String toString() {
        return "Staff{" + "staffID=" + staffID + ", fullName=" + fullName + ", email=" + email + ", password=" + password + ", phone=" + phone + ", hireDate=" + hireDate + ", roleID=" + roleID + ", status=" + status + ", department=" + department + ", profilePicture=" + profilePicture + '}';
    }
}

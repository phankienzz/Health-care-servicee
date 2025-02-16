/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Staff;

/**
 *
 * @author jaxbo
 */
public class StaffDAO extends DBContext {
    public Staff staffLogin(String email, String password){
        String sql = "select * from Staff where email = ? and password = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, email);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return new Staff(
                        rs.getInt("staffID"),
                        rs.getString("fullName"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("phone"),
                        rs.getString("gender"),
                        rs.getString("dateOfBirth"),
                        rs.getString("address"),
                        rs.getString("hireDate"),
                        rs.getInt("roleID"),
                        rs.getString("status"),
                        rs.getString("profilePicture"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;   
    }
    
     public Staff getStaffByID(int staffID){
        String sql = "select * from Staff where staffID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, staffID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return new Staff(
                        rs.getInt("staffID"),
                        rs.getString("fullName"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("phone"),
                        rs.getString("gender"),
                        rs.getString("dateOfBirth"),
                        rs.getString("address"),
                        rs.getString("hireDate"),
                        rs.getInt("roleID"),
                        rs.getString("status"),
                        rs.getString("profilePicture"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;   
    }
    
     public void updateStaff( int staffID, String fullName,String email, String phone, String hireDate, int roleID, String status){
        String sql = "update staff set fullName = ?, email = ?, phone= ?, hireDate = CONVERT(DATETIME, ?, 103), roleID = ?, status = ? where staffID = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, fullName);
            pre.setString(2, email);
            pre.setString(3, phone);
            pre.setString(4, hireDate);
            pre.setInt(5, roleID);
            pre.setString(6, status);
            pre.setInt(7, staffID);
            pre.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
     public List<Staff> getStaffByName(String  name){
         List<Staff> listStaff = new ArrayList<>();
        String sql = "select * from staff where fullName like ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
             st.setString(1, "%"+name+"%");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int staffID = rs.getInt("staffID");
                String fullName = rs.getString("fullName");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String phone = rs.getString("phone");
                String gender = rs.getString("gender");
                String dateOfBirth = rs.getString("dateOfBirth");
                String address = rs.getString("address");
                String hireDate = rs.getString("hireDate");
                int roleID = rs.getInt("roleID");
                String status = rs.getString("status");
                String profilePicture = rs.getString("profilePicture");
                Staff staff = new Staff(staffID, fullName, email, password, phone,gender,dateOfBirth,address, hireDate,roleID, status, profilePicture);
                listStaff.add(staff); // Thêm customer vào danh sách
            }

        } catch (SQLException e) {
        }
        return listStaff; // Trả về danh sách khách hàng
         
     }
     public List<Staff> getStaffByRole(int  number_roleID){
         List<Staff> listStaff = new ArrayList<>();
        String sql = "select * from staff where roleID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
             st.setInt(1, number_roleID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int staffID = rs.getInt("staffID");
                String fullName = rs.getString("fullName");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String phone = rs.getString("phone");
                String gender = rs.getString("gender");
                String dateOfBirth = rs.getString("dateOfBirth");
                String address = rs.getString("address");
                String hireDate = rs.getString("hireDate");
                int roleID = rs.getInt("roleID");
                String status = rs.getString("status");
                String profilePicture = rs.getString("profilePicture");
                Staff staff = new Staff(staffID, fullName, email, password, phone,gender,dateOfBirth,address, hireDate,roleID, status, profilePicture);
                listStaff.add(staff); // Thêm customer vào danh sách
            }

        } catch (SQLException e) {
        }
        return listStaff; // Trả về danh sách khách hàng
         
     }
     public void deleteByStaffID( int staffID){
        String sql = "delete from staff where staffID = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);   
            pre.setInt(1, staffID);
            pre.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public List<Staff> getAllStaff() {
        List<Staff> listStaff = new ArrayList<>();
        String sql = "SELECT * FROM Staff";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int staffID = rs.getInt("staffID");
                String fullName = rs.getString("fullName");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String phone = rs.getString("phone");
                String gender = rs.getString("gender");
                String dateOfBirth = rs.getString("dateOfBirth");
                String address = rs.getString("address");
                String hireDate = rs.getString("hireDate");
                int roleID = rs.getInt("roleID");
                String status = rs.getString("status");
                String profilePicture = rs.getString("profilePicture");
                Staff staff = new Staff(staffID, fullName, email, password, phone,gender,dateOfBirth,address, hireDate,roleID, status, profilePicture);
                listStaff.add(staff); // Thêm customer vào danh sách
            }

        } catch (SQLException e) {
        }
        return listStaff; // Trả về danh sách khách hàng
    }
    
    public void createStaff(String fullName, String email, String password, String phone, String hireDate, int roleID, String status) {
        String sql = "INSERT [dbo].[Staff] ( [fullName], [email], [password], [phone], [hireDate], [roleID], [status], [profilePicture]) VALUES ( ?, ?, ?, ?, CONVERT(DATETIME, ?, 103), ?, ?, NULL)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, fullName);
            st.setString(2, email);
            st.setString(3, password);
            st.setString(4, phone);
            st.setString(5, hireDate);
            st.setInt(6, roleID);
            st.setString(7, status);

            st.executeUpdate();
        } catch (SQLException e) {
        }
    }
    
    public static void main(String[] args) {
        StaffDAO dao = new StaffDAO();
        Staff s = dao.staffLogin("john.smith@medical.com", "Thang@2303");
        System.out.println(s.getFullName());
    }
}

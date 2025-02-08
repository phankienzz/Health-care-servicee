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
                        rs.getString("hireDate"),
                        rs.getInt("roleID"),
                        rs.getString("status"),
                        rs.getString("department"),
                        rs.getString("profilePicture"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;   
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
                String hireDate = rs.getString("hireDate");
                int roleID = rs.getInt("roleID");
                String status = rs.getString("status");
                String department = rs.getString("department");
                String profilePicture = rs.getString("profilePicture");

                Staff staff = new Staff(staffID, fullName, email, password, phone, hireDate,roleID, status, department, profilePicture);
                listStaff.add(staff); // Thêm customer vào danh sách
            }

        } catch (SQLException e) {
        }
        return listStaff; // Trả về danh sách khách hàng
    }
    
    public void createStaff(String fullName, String email, String password, String phone, String hireDate, int roleID, String status, String department) {
        String sql = "INSERT [dbo].[Staff] ( [fullName], [email], [password], [phone], [hireDate], [roleID], [status], [department], [profilePicture]) VALUES (?, ?, ?, ?, CONVERT(DATETIME, ?, 103), ? , ?, ?, NULL)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, fullName);
            st.setString(2, email);
            st.setString(3, password);
            st.setString(4, phone);
            st.setString(5, hireDate);
            st.setInt(6, roleID);
            st.setString(7, status);
            st.setString(8, department);

            st.executeUpdate();
        } catch (SQLException e) {
        }
    }
    
    public static void main(String[] args) {
        StaffDAO dao = new StaffDAO();
        Staff staff = dao.staffLogin("sarah.j@medical.com", "hash456");
        System.out.println(staff);
    }
}

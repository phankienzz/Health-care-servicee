/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    
    public static void main(String[] args) {
        StaffDAO dao = new StaffDAO();
        Staff staff = dao.staffLogin("sarah.j@medical.com", "hash456");
        System.out.println(staff);
    }
}

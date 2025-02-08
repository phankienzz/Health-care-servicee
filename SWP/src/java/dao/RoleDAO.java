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
import model.Role;
import model.Staff;

/**
 *
 * @author Gigabyte
 */
public class RoleDAO extends DBContext {
    public List<Role> getAllRole() {
        List<Role> listRole = new ArrayList<>();
        String sql = "SELECT * FROM role";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int roleID = rs.getInt("roleID");
                String roleName = rs.getString("roleName");
                String description = rs.getString("description");
                Role role = new Role(roleID,roleName,description);
                listRole.add(role); // Thêm customer vào danh sách
            }

        } catch (SQLException e) {
        }
        return listRole; // Trả về danh sách khách hàng
    }
    
    
    public static void main(String[] args) {
        RoleDAO dao = new RoleDAO();
       
        System.out.println(dao.getAllRole().get(0).getRoleName());
    }
}

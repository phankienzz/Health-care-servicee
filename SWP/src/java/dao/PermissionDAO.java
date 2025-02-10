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
import model.Permission;

/**
 *
 * @author Gigabyte
 */
public class PermissionDAO extends DBContext{
    public List<Permission> getAllPermission() {
        List<Permission> listPermission = new ArrayList<>();
        String sql = "SELECT * FROM permission";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int permissionID = rs.getInt("permissionID");
                String permissionName = rs.getString("permissionName");
                String description = rs.getString("description");
                Permission permission = new Permission(permissionID,permissionName,description);
                listPermission.add(permission); // Thêm customer vào danh sách
            }

        } catch (SQLException e) {
        }
        return listPermission; // Trả về danh sách khách hàng
    }
    
    
    public static void main(String[] args) {
        PermissionDAO dao = new PermissionDAO();
       
        System.out.println(dao.getAllPermission().get(0).getPermissionName());
    }
}

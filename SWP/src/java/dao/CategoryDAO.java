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
import model.Category;

/**
 *
 * @author jaxbo
 */
public class CategoryDAO extends DBContext {

    public List<Category> getAllCategory() {
        List<Category> cate = new ArrayList<>();
        String sql = "select * from Categories where status = 1";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Category c = new Category(
                        rs.getInt("category_id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getInt("status"));
                cate.add(c);
            }
        } catch (SQLException e) {
        }
        return cate;
    }

    public static void main(String[] args) {
        CategoryDAO dao = new CategoryDAO();
        List<Category> cateList = dao.getAllCategory();
        for (Category category : cateList) {
            System.out.println(category);
        }
    }
}

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
import model.News;
import java.util.List;

/**
 *
 * @author jaxbo
 */
public class NewsDAO extends DBContext {

    public List<News> getAllNews() {
        List<News> news = new ArrayList<>();
        String sql = "select post_id, title, content, created_by, category_id, status, created_at, updated_at from Posts where status = 1";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String createdAt = (rs.getTimestamp("created_at") != null) ? rs.getTimestamp("created_at").toString() : null;
                String updatedAt = (rs.getTimestamp("updated_at") != null) ? rs.getTimestamp("updated_at").toString() : null;

                News n = new News(
                        rs.getInt("post_id"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getInt("created_by"),
                        rs.getInt("category_id"),
                        rs.getInt("status"),
                        createdAt,
                        updatedAt);
//                        rs.getString("created_at"),
//                        rs.getString("updated_at"));
                news.add(n);
            }
        } catch (SQLException e) {
        }
        return news;
    }

    public static void main(String[] args) {
        NewsDAO dao = new NewsDAO();
        List<News> newsList = dao.getAllNews();
        for (News news : newsList) {
            System.out.println(news);
        }
    }
}

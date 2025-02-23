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
import model.News;

/**
 *
 * @author Hoang
 */
public class NewsDAO extends DBContext {

    public List<Category> getAllCategoryNews() {
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

    public List<News> getNewsByCategory(String category_id) {
        List<News> news = new ArrayList<>();
        String sql = "select * from Posts where category_id = ? and status = 1";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, category_id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String createdAt = (rs.getTimestamp("created_at") != null) ? rs.getTimestamp("created_at").toString() : null;
                String updatedAt = (rs.getTimestamp("updated_at") != null) ? rs.getTimestamp("updated_at").toString() : null;

                News n = new News(rs.getInt("post_id"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getInt("created_by"),
                        rs.getInt("category_id"),
                        rs.getInt("status"),
                        rs.getString("image"),
                        rs.getString("detail"),
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

    
    //dem
    public int getTotalNews() {
        String sql = "SELECT COUNT(*) FROM Posts WHERE status = 1";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getTotalNewsByCategory(String category_id) {
        String sql = "SELECT COUNT(*) FROM Posts WHERE status = 1 AND category_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, category_id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getTotalNewsBySearch(String title) {
        int totalNews = 0;
        String sql = "SELECT COUNT(*) FROM Posts WHERE status = 1 AND title LIKE ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, "%" + title + "%");
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                totalNews = rs.getInt(1);
            }
        } catch (SQLException e) {
        }
        return totalNews;
    }

    //phan trang
    public List<News> pagingAllNews(int index, int pageSize) {
        List<News> list = new ArrayList<>();
        String sql = "select * from Posts where status = 1 order by post_id offset ? rows  fetch  next ? rows only";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            int offset = (index - 1) * pageSize;
            pre.setInt(1, offset);
            pre.setInt(2, pageSize);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                String createdAt = (rs.getTimestamp("created_at") != null) ? rs.getTimestamp("created_at").toString() : null;
                String updatedAt = (rs.getTimestamp("updated_at") != null) ? rs.getTimestamp("updated_at").toString() : null;

                News n = new News(rs.getInt("post_id"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getInt("created_by"),
                        rs.getInt("category_id"),
                        rs.getInt("status"),
                        rs.getString("image"),
                        rs.getString("detail"),
                        createdAt,
                        updatedAt);
//                        rs.getString("created_at"),
//                        rs.getString("updated_at"));
                list.add(n);
            }
        } catch (SQLException e) {
        }
        return list;
    }

    public List<News> pagingNewsByCategory(String categoryID, int index, int pageSize) {
        List<News> list = new ArrayList<>();
        String sql = "select * from Posts where status = 1 and category_id = ? order by post_id OFFSET ? rows fetch next ? rows only";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            int offset = (index - 1) * pageSize;
            pre.setString(1, categoryID);
            pre.setInt(2, offset);
            pre.setInt(3, pageSize);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                String createdAt = (rs.getTimestamp("created_at") != null) ? rs.getTimestamp("created_at").toString() : null;
                String updatedAt = (rs.getTimestamp("updated_at") != null) ? rs.getTimestamp("updated_at").toString() : null;

                News n = new News(rs.getInt("post_id"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getInt("created_by"),
                        rs.getInt("category_id"),
                        rs.getInt("status"),
                        rs.getString("image"),
                        rs.getString("detail"),
                        createdAt,
                        updatedAt);
//                        rs.getString("created_at"),
//                        rs.getString("updated_at"));
                list.add(n);
            }
        } catch (SQLException e) {
        }
        return list;
    }

    public List<News> searchNewsByTitle(String title, int index, int pageSize) {
        List<News> list = new ArrayList<>();
        String sql = "SELECT * FROM Posts WHERE status = 1 AND title LIKE ? ORDER BY post_id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            int offset = (index - 1) * pageSize;
            pre.setString(1, "%" + title + "%");
            pre.setInt(2, offset);
            pre.setInt(3, pageSize);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                String createdAt = (rs.getTimestamp("created_at") != null) ? rs.getTimestamp("created_at").toString() : null;
                String updatedAt = (rs.getTimestamp("updated_at") != null) ? rs.getTimestamp("updated_at").toString() : null;

                News n = new News(rs.getInt("post_id"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getInt("created_by"),
                        rs.getInt("category_id"),
                        rs.getInt("status"),
                        rs.getString("image"),
                        rs.getString("detail"),
                        createdAt,
                        updatedAt);
                list.add(n);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void addBlogPost(String title, String content, int createdBy, int categoryId, boolean status, String detail, String imagePaths) {
        String sql = "INSERT INTO Posts (title, content, created_by, category_id, status, detail, image) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, title);
            st.setString(2, content);
            st.setInt(3, createdBy);
            st.setInt(4, categoryId);
            st.setBoolean(5, status);
            st.setString(6, detail);
            st.setString(7, imagePaths);

            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateBlogPost(int postId, String title, String content, String status, String image, String detail) {
        String sql = "UPDATE BlogPosts SET title = ?, content = ?, status = ?, image = ?, detail = ?, updated_at = GETDATE() WHERE post_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, title);
            st.setString(2, content);
            st.setString(3, status);
            st.setString(4, image);
            st.setString(5, detail);
            st.setInt(6, postId);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<News> getAllBlogs() {
        List<News> blogs = new ArrayList<>();
        String sql = "SELECT post_id, title, content, image, detail FROM Posts";

        try (PreparedStatement st = connection.prepareStatement(sql); ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                News blog = new News();
                blog.setPost_id(rs.getInt("post_id"));
                blog.setTitle(rs.getString("title"));
                blog.setContent(rs.getString("content"));
                blog.setImage(rs.getString("image"));  // Lấy đường dẫn ảnh
                blog.setDetail(rs.getString("detail"));

                blogs.add(blog);
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Ghi log lỗi
        }
        return blogs;
    }

    public static void main(String[] args) {
        NewsDAO dao = new NewsDAO();
        List<News> newsList = dao.searchNewsByTitle("h", 1,3);
        for (News news : newsList) {
            System.out.println(news);
        }
//        int count = dao.getTotalNewsByCategory("1");
//        System.out.println(count);

    }
}

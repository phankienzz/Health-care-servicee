/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import model.Category;
import model.News;

/**
 *
 * @author Hoang
 */
public class NewsDAO extends DBContext {

    public List<News> getAllNews() {
        List<News> news = new ArrayList<>();
        String sql = "select * from Posts where status = 1";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                // Kiểm tra giá trị Timestamp
                java.sql.Timestamp createdTimestamp = rs.getTimestamp("created_at");
                java.sql.Timestamp updatedTimestamp = rs.getTimestamp("updated_at");

                // Định dạng ngày nếu không null
                String createdAt = (createdTimestamp != null) ? dateFormat.format(createdTimestamp) : null;
                String updatedAt = (updatedTimestamp != null) ? dateFormat.format(updatedTimestamp) : null;

                // Tạo đối tượng News
                News n = new News(
                        rs.getInt("post_id"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getInt("created_by"),
                        rs.getInt("category_id"),
                        rs.getInt("status"),
                        rs.getString("image"),
                        rs.getString("detail"),
                        createdAt,
                        updatedAt
                );
                news.add(n);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Ghi log lỗi để kiểm tra
        }
        return news;
    }

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
        String sql = "select * from Posts where status = 1";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                // Kiểm tra giá trị Timestamp
                java.sql.Timestamp createdTimestamp = rs.getTimestamp("created_at");
                java.sql.Timestamp updatedTimestamp = rs.getTimestamp("updated_at");

                // Định dạng ngày nếu không null
                String createdAt = (createdTimestamp != null) ? dateFormat.format(createdTimestamp) : null;
                String updatedAt = (updatedTimestamp != null) ? dateFormat.format(updatedTimestamp) : null;

                // Tạo đối tượng News
                News n = new News(
                        rs.getInt("post_id"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getInt("created_by"),
                        rs.getInt("category_id"),
                        rs.getInt("status"),
                        rs.getString("image"),
                        rs.getString("detail"),
                        createdAt,
                        updatedAt
                );
                news.add(n);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Ghi log lỗi để kiểm tra
        }
        return news;
    }

    public List<News> searchByTitle(String title) {
        List<News> news = new ArrayList<>();
        String sql = "  SELECT * FROM Posts WHERE status = 1 AND title LIKE ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, "%" + title + "%");
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                News n = new News(
                        rs.getInt("post_id"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getInt("created_by"),
                        rs.getInt("category_id"),
                        rs.getInt("status"),
                        rs.getString("image"),
                        rs.getString("detail"),
                        rs.getString("created_at"),
                        rs.getString("updated_at")
                );
                news.add(n);
            }
        } catch (Exception e) {
        }
        return news;

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
                blog.setPostId(rs.getInt("post_id"));
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
        List<News> newsList = dao.getNewsByCategory("2");
        for (News news : newsList) {
            System.out.println(news);
        }
    }
}

package dao;

import context.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Blog;
import model.Customer;

public class BlogDAO extends DBContext {

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


public List<Blog> getAllBlogs() {
    List<Blog> blogs = new ArrayList<>();
    String sql = "SELECT post_id, title, content, image, detail FROM Posts";

    try (PreparedStatement st = connection.prepareStatement(sql);
         ResultSet rs = st.executeQuery()) {

        while (rs.next()) {
            Blog blog = new Blog();
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


//  public static void main(String[] args) {
//    BlogDAO blogDAO = new BlogDAO(); // Create a BlogDAO object
//
//    // Add a new blog post
//    String title = "Example Title";
//    String content = "Example Content";
//    int createdBy = 1;
//    int categoryId = 1;
//    String status = "Published";
//
//    blogDAO.addBlogPost(title, content, createdBy, categoryId, true, "This is a detailed description.", "path/to/image.jpg");
//
//    System.out.println("New Blog Post Added Successfully: " + title);
//
//    // Check all blog posts
//    List<Blog> blogs = blogDAO.getAllBlogs();
//    if (blogs.isEmpty()) {
//        System.out.println("No blog posts available.");
//    } else {
//        for (Blog blog : blogs) {
//            System.out.println("Retrieved Blog Post:");
//            System.out.println("ID: " + blog.getPostId());
//            System.out.println("Title: " + blog.getTitle());
//            System.out.println("Content: " + blog.getContent());
//            System.out.println("Image: " + blog.getImage());
//            System.out.println("Detail: " + blog.getDetail());
//            System.out.println("-------------------------------------");
//        }
//    }
//}
}


    

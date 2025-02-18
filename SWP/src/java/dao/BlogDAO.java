package dao;

import java.sql.Blob;
import context.DBContext;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.News;
import model.Customer;

public class BlogDAO extends DBContext {

    private static final Logger LOGGER = Logger.getLogger(BlogDAO.class.getName());

    public void addBlogPost(String title, String content, int createdBy, int categoryId, boolean status, String detail, InputStream imageStream) {
        String sql = "INSERT INTO Posts (title, content, created_by, category_id, status, detail, image, created_at) VALUES (?, ?, ?, ?, ?, ?, ?, GETDATE())";

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, title);
            st.setString(2, content);
            st.setInt(3, createdBy);
            st.setInt(4, categoryId);
            st.setBoolean(5, status);
            st.setString(6, detail);

            if (imageStream != null) {
                st.setBinaryStream(7, imageStream);
            } else {
                st.setNull(7, java.sql.Types.BLOB);
            }

            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                LOGGER.info("Blog post added successfully.");
            } else {
                LOGGER.warning("No rows inserted, check the input data.");
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error adding blog post", e);
        }
    }

    public boolean updateBlogPost(int postId, String title, String content, boolean status, InputStream imageStream, String detail) {
        String sql = "UPDATE Posts SET title = ?, content = ?, status = ?, detail = ?, updated_at = GETDATE()";
        if (imageStream != null) {
            sql += ", image = ?";
        }
        sql += " WHERE post_id = ?";

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, title);
            st.setString(2, content);
            st.setBoolean(3, status);
            st.setString(4, detail);

            int paramIndex = 5;
            if (imageStream != null) {
                st.setBinaryStream(paramIndex++, imageStream);
            }
            st.setInt(paramIndex, postId);

            int rowsAffected = st.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error updating blog post", e);
            return false;
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

                Blob blob = rs.getBlob("image");
                if (blob != null) {
                    blog.setImage("LoadBlogImage?postId=" + blog.getPost_id());
                } else {
                    blog.setImage("default.jpg"); // Ảnh mặc định nếu không có ảnh
                }

                blog.setDetail(rs.getString("detail"));
                blogs.add(blog);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("❌ Error fetching blogs: " + e.getMessage());
        }
        return blogs;
    }

    public News getBlogById(int id) {
        String sql = "SELECT post_id, title, content, image, detail FROM Posts WHERE post_id = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    News blog = new News();
                    blog.setPost_id(rs.getInt("post_id"));
                    blog.setTitle(rs.getString("title"));
                    blog.setContent(rs.getString("content"));

                    Blob blob = rs.getBlob("image");
                    blog.setImage(blob != null ? "LoadBlogImage?postId=" + blog.getPost_id() : "default.jpg");

                    blog.setDetail(rs.getString("detail"));
                    return blog;
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error fetching blog by ID", e);
        }
        return null;
    }

   public void deleteBlogPost(int postId) throws SQLException {
    // Step 1: Delete all StaffReplies related to the comments of the post
    String deleteStaffRepliesSql = "DELETE FROM dbo.StaffReplies WHERE comment_id IN (SELECT comment_id FROM dbo.Comments WHERE post_id = ?)";
    try (PreparedStatement deleteStaffRepliesStmt = connection.prepareStatement(deleteStaffRepliesSql)) {
        deleteStaffRepliesStmt.setInt(1, postId);
        deleteStaffRepliesStmt.executeUpdate();
    }

    // Step 2: Delete all comments related to the post
    String deleteCommentsSql = "DELETE FROM dbo.Comments WHERE post_id = ?";
    try (PreparedStatement deleteCommentsStmt = connection.prepareStatement(deleteCommentsSql)) {
        deleteCommentsStmt.setInt(1, postId);
        deleteCommentsStmt.executeUpdate();
    }

    // Step 3: Delete the post itself
    String deletePostSql = "DELETE FROM Posts WHERE post_id = ?";
    try (PreparedStatement deletePostStmt = connection.prepareStatement(deletePostSql)) {
        deletePostStmt.setInt(1, postId);
        int rowsAffected = deletePostStmt.executeUpdate();

        if (rowsAffected == 0) {
            throw new SQLException("No blog post was deleted. Please check the postId.");
        }
        LOGGER.info("Blog post deleted successfully.");
    }
}

   
   
   public List<News> getBlogsByPage(int start, int total) {
    List<News> blogs = new ArrayList<>();
    String sql = "SELECT post_id, title, content, image, detail FROM Posts ORDER BY post_id DESC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

    try (PreparedStatement st = connection.prepareStatement(sql)) {
        st.setInt(1, start);
        st.setInt(2, total);
        try (ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                News blog = new News();
                blog.setPost_id(rs.getInt("post_id"));
                blog.setTitle(rs.getString("title"));
                blog.setContent(rs.getString("content"));

                Blob blob = rs.getBlob("image");
                blog.setImage(blob != null ? "LoadBlogImage?postId=" + blog.getPost_id() : "default.jpg");

                blog.setDetail(rs.getString("detail"));
                blogs.add(blog);
            }
        }
    } catch (SQLException e) {
        LOGGER.log(Level.SEVERE, "Error fetching paginated blogs", e);
    }
    return blogs;
}

   
   public int getTotalBlogCount() {
    int count = 0;
    String sql = "SELECT COUNT(*) FROM Posts";

    try (PreparedStatement st = connection.prepareStatement(sql);
         ResultSet rs = st.executeQuery()) {
        if (rs.next()) {
            count = rs.getInt(1);
        }
    } catch (SQLException e) {
        LOGGER.log(Level.SEVERE, "Error fetching total blog count", e);
    }
    return count;
}






    
//    public static void main(String[] args) {
//        BlogDAO blogDAO = new BlogDAO();
//
//        // Kiểm tra với postId thực tế trong cơ sở dữ liệu của bạn
//        int postIdToDelete = 4;  // Thay thế với postId mà bạn muốn kiểm tra
//
//        try {
//            // Gọi phương thức xóa bài viết
//            blogDAO.deleteBlogPost(postIdToDelete);
//            System.out.println("Blog post with ID " + postIdToDelete + " has been deleted successfully.");
//        } catch (SQLException e) {
//            // Xử lý lỗi khi xóa bài viết
//            LOGGER.log(Level.SEVERE, "Error deleting blog post with ID " + postIdToDelete, e);
//            System.err.println("Error deleting blog post: " + e.getMessage());
//        }
//    }



}

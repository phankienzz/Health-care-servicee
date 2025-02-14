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


//    public void updateBlogPost(int postId, String title, String content, String status, String image, String detail) {
//        String sql = "UPDATE BlogPosts SET title = ?, content = ?, status = ?, image = ?, detail = ?, updated_at = GETDATE() WHERE post_id = ?";
//        try {
//            PreparedStatement st = connection.prepareStatement(sql);
//            st.setString(1, title);
//            st.setString(2, content);
//            st.setString(3, status);
//            st.setString(4, image);
//            st.setString(5, detail);
//            st.setInt(6, postId);
//            st.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
    public List<News> getAllBlogs() {
    List<News> blogs = new ArrayList<>();
    String sql = "SELECT post_id, title, content, image, detail FROM Posts";

    try (PreparedStatement st = connection.prepareStatement(sql);
         ResultSet rs = st.executeQuery()) {

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

    
    
   public News getBlogbyid(String id) {
    String sql = "SELECT post_id, title, image, detail FROM Posts WHERE post_id = ?";

    try (PreparedStatement st = connection.prepareStatement(sql)) {
        st.setString(1, id);

        try (ResultSet rs = st.executeQuery()) {
            if (rs.next()) {
                News blog = new News();
                blog.setPost_id(rs.getInt("post_id")); 
                blog.setTitle(rs.getString("title"));

                Blob blob = rs.getBlob("image");
                if (blob != null) {
                    blog.setImage("LoadBlogImage?postId=" + blog.getPost_id()); // Tải hình ảnh động
                } else {
                    blog.setImage("default.jpg"); // Ảnh mặc định nếu không có hình
                }

                blog.setDetail(rs.getString("detail"));
                return blog;
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}




public static void main(String[] args) {
        BlogDAO blogDAO = new BlogDAO();
        List<News> blogs = blogDAO.getAllBlogs();

        if (blogs.isEmpty()) {
            System.out.println("⚠️ No blogs found in the database.");
        } else {
            System.out.println("✅ Blogs loaded successfully!");
            for (News blog : blogs) {
                System.out.println("📝 Title: " + blog.getTitle());
                System.out.println("📸 Image: " + blog.getImage());
                System.out.println("📝 Content: " + blog.getContent());
                System.out.println("--------------------------------");
            }
        }
    }
}

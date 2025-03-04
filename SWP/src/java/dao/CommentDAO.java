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
import model.Comment;
import model.Customer;

/**
 *
 * @author Hoang
 */
public class CommentDAO extends DBContext {

    public List<Comment> getCommentsByPostId(int postId) {
        List<Comment> comments = new ArrayList<>();
        CustomerDAO customerDAO = new CustomerDAO();
        String sql = "SELECT * FROM Comments WHERE post_id = ? AND status = 1 ORDER BY created_at desc";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, postId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int commentID = rs.getInt("comment_id");
                int postID = rs.getInt("post_id");
                int customerID = rs.getInt("customerID");
                Customer customer = customerDAO.getCustomerByID(customerID);
                String content = rs.getString("content");
                int status = rs.getInt("status");
                String createdAt = rs.getString("created_at");
                int parentCommentID = rs.getInt("parent_comment_id");
                Comment comment = new Comment(commentID, postID, customer, content, status, createdAt, parentCommentID);
                comments.add(comment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comments;
    }

    public boolean addComment(Comment comment) {
        String query = "INSERT INTO Comments (post_id, customerID, content,status, parent_comment_id) VALUES (?, ?,?, 1, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, comment.getPost_id());
            ps.setInt(2, comment.getCustomerID().getCustomerID());
            ps.setString(3, comment.getContent());

            ps.setObject(4, comment.getParent_comment_id() == 0 ? null : comment.getParent_comment_id());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Comment getCommentById(int commentId) {
        Comment comment = null;
        try {
            String sql = "SELECT * FROM Comments WHERE comment_id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, commentId);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                comment = new Comment();
                comment.setComment_id(rs.getInt("comment_id"));
                comment.setPost_id(rs.getInt("post_id"));
                comment.setContent(rs.getString("content"));
                comment.setParent_comment_id(rs.getInt("parent_comment_id"));

                // Lấy thông tin Customer
                CustomerDAO customerDAO = new CustomerDAO();
                Customer customer = customerDAO.getCustomerByID(rs.getInt("customerID"));
                comment.setCustomerID(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comment;
    }

    public static void main(String[] args) {
        CommentDAO dao = new CommentDAO();
        Comment comment = new Comment();
        comment.setPost_id(1); // ID bài viết 
        comment.setCustomerID(new Customer(2)); // ID Customer
        comment.setContent("Bình luận con 4");
        comment.setParent_comment_id(9); // Không có bình luận cha

        System.out.println(dao.getCommentById(1));
        
        // Gọi hàm addComment
        boolean isAdded = dao.addComment(comment);

    }

}

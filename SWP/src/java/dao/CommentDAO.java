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
        String sql = "SELECT * FROM Comments WHERE post_id = ? AND status = 1 ORDER BY created_at ASC";
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

    public boolean addComment(int postId, int customerId, String content, int parentCommentId) {
        String sql = "INSERT INTO Comments (post_id, customerID, content, status, created_at, parent_comment_id) "
                + "VALUES (?, ?, ?, ?, GETDATE(), ?)";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, postId);
            pre.setInt(2, customerId);
            pre.setString(3, content);
            pre.setInt(4, 1);
            pre.setInt(5, parentCommentId);

            return pre.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        CommentDAO dao = new CommentDAO();
        int postId = 1;
        List<Comment> comments = dao.getCommentsByPostId(1);
        for (Comment comment : comments) {
            System.out.println(comment);
        }
//        System.out.println(dao.addComment(1, 1, "hahahahahahahahaha", 0));

    }

}

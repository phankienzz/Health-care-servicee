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
import model.Staff;

/**
 *
 * @author Hoang
 */
public class CommentDAO extends DBContext {

    public Comment getCommentById(int commentId) {
        Comment comment = null;
        CustomerDAO customerDAO = new CustomerDAO();
        StaffDAO staffDAO = new StaffDAO();
        try {
            String sql = "SELECT * FROM Comments WHERE comment_id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, commentId);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                comment = new Comment();
                Customer customer = customerDAO.getCustomerByID(rs.getInt("customerID"));
                Staff staff = staffDAO.getStaffByID(rs.getInt("staff_id"));
                comment.setStaff_id(staff);
                comment.setCustomerID(customer);
                comment.setComment_id(rs.getInt("comment_id"));
                comment.setPost_id(rs.getInt("post_id"));
                comment.setContent(rs.getString("content"));
                comment.setParent_comment_id(rs.getInt("parent_comment_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comment;
    }

    public List<Comment> getCommentsByPostId(int postId) {
        List<Comment> comments = new ArrayList<>();
        CustomerDAO cusDAO = new CustomerDAO();
        StaffDAO staffDAO = new StaffDAO();
        String sql = "SELECT * FROM Comments WHERE post_id = ? and status = 1 ORDER BY created_at desc";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, postId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int commentID = rs.getInt("comment_id");
                int postID = rs.getInt("post_id");
                int customerID = rs.getInt("customerID");
                Customer customer = cusDAO.getCustomerByID(customerID);
                int staffID = rs.getInt("staff_id");
                Staff staff = staffDAO.getStaffByID(staffID);
                String content = rs.getString("content");
                int status = rs.getInt("status");
                String createdAt = rs.getString("created_at");
                int parentCommentID = rs.getInt("parent_comment_id");
                Comment comment = new Comment(commentID, postID, customer, staff, content, status, createdAt, parentCommentID);
                comments.add(comment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comments;
    }

    public boolean insertComment(Comment comment) {
        String sql = "INSERT INTO Comments (post_id, customerID, staff_id, content, status, parent_comment_id) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, comment.getPost_id());

            if (comment.getCustomerID() != null) {
                ps.setInt(2, comment.getCustomerID().getCustomerID());
                ps.setNull(3, java.sql.Types.INTEGER);
            } else if (comment.getStaff_id() != null) {
                ps.setNull(2, java.sql.Types.INTEGER);
                ps.setInt(3, comment.getStaff_id().getStaffID());
            } else {
                return false;
            }

            ps.setString(4, comment.getContent());
            ps.setInt(5, comment.getStatus());

            if (comment.getParent_comment_id() != 0) {
                ps.setInt(6, comment.getParent_comment_id());
            } else {
                ps.setNull(6, java.sql.Types.INTEGER);
            }

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean editComment(Comment comment) {
        String sql = "UPDATE Comments SET post_id = ?, customerID = ?, staff_id = ?, content = ?, status = ?, created_at = ?, parent_comment_id = ? WHERE comment_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, comment.getPost_id());
            if (comment.getCustomerID() != null) {
                ps.setInt(2, comment.getCustomerID().getCustomerID());
            } else {
                ps.setNull(2, java.sql.Types.INTEGER);
            }
            if (comment.getStaff_id() != null) {
                ps.setInt(3, comment.getStaff_id().getStaffID());
            } else {
                ps.setNull(3, java.sql.Types.INTEGER);
            }
            ps.setString(4, comment.getContent());
            ps.setInt(5, comment.getStatus());
            ps.setString(6, comment.getCreate_at());
            ps.setInt(7, comment.getParent_comment_id());
            ps.setInt(8, comment.getComment_id());

            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteComment(int commentId) {
        String sql = "UPDATE Comments SET status = 0 WHERE comment_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, commentId);
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        CommentDAO dao = new CommentDAO();

        boolean del = dao.deleteComment(2);
        System.out.println("delete :" + del);
        // Bình luận của khách hàng
//        Customer customer = new Customer();
//        customer.setCustomerID(1); // Đảm bảo customerID tồn tại
//
//        Comment comment1 = new Comment();
//        comment1.setPost_id(1);
//        comment1.setCustomerID(customer);
//        comment1.setContent("Cus gốc");
//        comment1.setStatus(1);
//        comment1.setParent_comment_id(0);
//
//        boolean result1 = dao.insertComment(comment1);
//        System.out.println("Thêm bình luận khách hàng: " + result1);
        // Phản hồi của nhân viên
//        Staff staff = new Staff();
//        staff.setStaffID(1); // Đảm bảo staff_id tồn tại
//
//        Comment comment2 = new Comment();
//        comment2.setPost_id(3);
//        comment2.setStaff_id(staff);
//        comment2.setContent("Staff");
//        comment2.setStatus(1);
//        comment2.setParent_comment_id(23); // Trả lời bình luận của khách hàng
//
//        boolean result2 = dao.insertComment(comment2);
//        System.out.println("Thêm phản hồi của nhân viên: " + result2);
//        List<Comment> list = dao.getCommentsByPostId(1);
//        for (Comment comment : list) {
//            System.out.println(comment);
//        }
//        System.out.println(dao.getCommentById(10));
//        System.out.println(dao.getCommentById(9));

    }
}

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
import model.StaffReply;

/**
 *
 * @author Hoang
 */
public class StaffReplyDAO extends DBContext {

    public List<StaffReply> getStaffRepliesByCommentId(int commentId) {
        List<StaffReply> list = new ArrayList<>();
        String sql = "SELECT * FROM StaffReplies WHERE comment_id = ? AND status = 1 ORDER BY created_at ASC";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, commentId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                StaffReply reply = new StaffReply();
                reply.setReply_id(rs.getInt("reply_id"));
                reply.setComment_id(rs.getInt("comment_id"));
                reply.setStaff_id(rs.getInt("staff_id"));
                reply.setContent(rs.getString("content"));
                reply.setStatus(rs.getInt("status"));
                reply.setCreated_at(rs.getString("created_at"));
                list.add(reply);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<StaffReply> getStaffRepliesByPostId(int postId) {
        List<StaffReply> list = new ArrayList<>();
        String sql = "SELECT * FROM StaffReplies\n"
                + "JOIN Comments  ON StaffReplies.comment_id = Comments.comment_id\n"
                + "WHERE Comments.post_id = ? AND StaffReplies.status = 1\n"
                + "ORDER BY StaffReplies.created_at ASC";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, postId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int replyID = rs.getInt("reply_id");
                int commentID = rs.getInt("comment_id");
                int staffID = rs.getInt("staff_id");
                String content = rs.getString("content");
                int status = rs.getInt("status");
                String createdAt = rs.getString("created_at");
                StaffReply reply = new StaffReply(replyID, commentID, staffID, content, status, createdAt);
                list.add(reply);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean addStaffReply(StaffReply reply) {
        String sql = "INSERT INTO StaffReplies (comment_id, staff_id, content, status) VALUES (?, ?, ?, 1)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, reply.getComment_id());
            ps.setInt(2, reply.getStaff_id());
            ps.setString(3, reply.getContent());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        StaffReplyDAO dao = new StaffReplyDAO();
        List<StaffReply> list = dao.getStaffRepliesByPostId(1);
        for (StaffReply staffReply : list) {
            System.out.println(staffReply);
        }
    }
}

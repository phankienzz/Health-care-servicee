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
import model.Customer;
import model.Feedback;
import model.Invoice;
import model.MedicalExamination;

/**
 *
 * @author Hoang
 */
public class FeedbackDAO extends DBContext {

    public List<Feedback> getAllFeedbackByCustomer() {
        List<Feedback> list = new ArrayList<>();
        InvoiceDAO inDAO = new InvoiceDAO();
        String sql = "select * from Feedback";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new Feedback(
                        rs.getInt("feedbackID"),
                        inDAO.getInvoiceByID(rs.getInt("invoiceID")),
                        rs.getInt("rating"),
                        rs.getString("comment"),
                        rs.getString("date")));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Feedback> getAllFeedback5StarByCustomer() {
        List<Feedback> list = new ArrayList<>();
        InvoiceDAO inDAO = new InvoiceDAO();
        String sql = "select * from Feedback where rating = 5";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new Feedback(
                        rs.getInt("feedbackID"),
                        inDAO.getInvoiceByID(rs.getInt("invoiceID")),
                        rs.getInt("rating"),
                        rs.getString("comment"),
                        rs.getString("date")));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean addFeedback(int invoiceID, int rating, String comment) {
        String sql = "INSERT INTO Feedback (invoiceID, rating, comment) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql);) {

            stmt.setInt(1, invoiceID);
            stmt.setInt(2, rating);
            stmt.setString(3, comment);

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0; // Trả về true nếu thêm thành công
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Trả về false nếu xảy ra lỗi
    }

    public static void main(String[] args) {
        FeedbackDAO feedbackDAO = new FeedbackDAO();
        List<Feedback> feedbacks = feedbackDAO.getAllFeedback5StarByCustomer();
        for (Feedback fb : feedbacks) {
            System.out.println(fb.getInvoice().getExaminationID().getCustomerId().getFullName() + ", " + fb.getComment() + ", "
                    + fb.getRating() +", " + fb.getDate());
        }

//        int invoiceID = 7;
//        int rating = 5;
//        String comment = "Dịch vụ rất tuyệt vời!";
//
//        // Gọi hàm DAO để thêm feedback
//        boolean isInserted = feedbackDAO.addFeedback(invoiceID, rating, comment);
//
//        // In kết quả kiểm tra
//        if (isInserted) {
//            System.out.println("Feedback đã được chèn thành công vào cơ sở dữ liệu!");
//        } else {
//            System.out.println("Có lỗi xảy ra khi chèn Feedback.");
//        }
    }
}

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

    public List<Feedback> getAllFeedback5StarByCustomer() {
        List<Feedback> feedbackList = new ArrayList<>();
        String query = "SELECT f.feedbackID, c.customerID, c.fullName, c.email, "
                + "f.rating, f.comment, f.date, "
                + "i.invoiceID, me.examinationID "
                + "FROM Feedback f "
                + "JOIN Invoice i ON f.invoiceID = i.invoiceID "
                + "JOIN MedicalExamination me ON i.examinationID = me.examinationID "
                + "JOIN Customer c ON me.customerID = c.customerID "
                + "WHERE f.rating = 5 "
                + "ORDER BY f.date DESC";

        try (PreparedStatement stmt = connection.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                // Tạo đối tượng Customer
                Customer customer = new Customer();
                customer.setCustomerID(rs.getInt("customerID"));
                customer.setFullName(rs.getString("fullName"));
                customer.setEmail(rs.getString("email"));

                MedicalExamination examination = new MedicalExamination();
                examination.setExaminationID(rs.getInt("examinationID"));
                examination.setCustomerId(customer);

                Invoice invoice = new Invoice();
                invoice.setInvoiceID(rs.getInt("invoiceID"));
                invoice.setExaminationID(examination);

                Feedback feedback = new Feedback();
                feedback.setFeedbackID(rs.getInt("feedbackID"));
                feedback.setInvoice(invoice);
                feedback.setRating(rs.getInt("rating"));
                feedback.setComment(rs.getString("comment"));
                feedback.setDate(rs.getString("date"));

                feedbackList.add(feedback);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return feedbackList;
    }

    public List<Feedback> getAllFeedbackByCustomer() {
        List<Feedback> feedbackList = new ArrayList<>();
        String query = "SELECT f.feedbackID, c.customerID, c.fullName, c.email, "
                + "f.rating, f.comment, f.date, "
                + "i.invoiceID, me.examinationID "
                + "FROM Feedback f "
                + "JOIN Invoice i ON f.invoiceID = i.invoiceID "
                + "JOIN MedicalExamination me ON i.examinationID = me.examinationID "
                + "JOIN Customer c ON me.customerID = c.customerID "
                + "ORDER BY f.date DESC";

        try (PreparedStatement stmt = connection.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                // Tạo đối tượng Customer
                Customer customer = new Customer();
                customer.setCustomerID(rs.getInt("customerID"));
                customer.setFullName(rs.getString("fullName"));
                customer.setEmail(rs.getString("email"));

                MedicalExamination examination = new MedicalExamination();
                examination.setExaminationID(rs.getInt("examinationID"));
                examination.setCustomerId(customer);

                Invoice invoice = new Invoice();
                invoice.setInvoiceID(rs.getInt("invoiceID"));
                invoice.setExaminationID(examination);

                Feedback feedback = new Feedback();
                feedback.setFeedbackID(rs.getInt("feedbackID"));
                feedback.setInvoice(invoice);
                feedback.setRating(rs.getInt("rating"));
                feedback.setComment(rs.getString("comment"));
                feedback.setDate(rs.getString("date"));

                feedbackList.add(feedback);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return feedbackList;
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
//        List<Feedback> feedbacks = feedbackDAO.getAllFeedbackByCustomer();
//        for (Feedback fb : feedbacks) {
//            System.out.println(fb);
//        }

        int invoiceID = 7; 
        int rating = 5; 
        String comment = "Dịch vụ rất tuyệt vời!"; 

        // Gọi hàm DAO để thêm feedback
        boolean isInserted = feedbackDAO.addFeedback(invoiceID, rating, comment);

        // In kết quả kiểm tra
        if (isInserted) {
            System.out.println("Feedback đã được chèn thành công vào cơ sở dữ liệu!");
        } else {
            System.out.println("Có lỗi xảy ra khi chèn Feedback.");
        }
    }
}

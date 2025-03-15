/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    public List<Feedback> getAllFeedbacksByCustomer() {
        List<Feedback> feedbackList = new ArrayList<>();
        String query = "SELECT f.feedbackID, c.customerID, c.fullName, c.email, "
                + "f.rating, f.comment, f.date, f.serviceType, "
                + "i.invoiceID, me.examinationID "
                + "FROM Feedback f "
                + "JOIN Invoice i ON f.invoiceID = i.invoiceID "
                + "JOIN MedicalExamination me ON i.examinationID = me.examinationID "
                + "JOIN Customer c ON me.customerID = c.customerID "
                + "where rating = 5"
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
                feedback.setServiceType(rs.getString("serviceType"));

                feedbackList.add(feedback);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return feedbackList;
    }

    public static void main(String[] args) {
        FeedbackDAO feedbackDAO = new FeedbackDAO();
        List<Feedback> feedbacks = feedbackDAO.getAllFeedbacksByCustomer();
        for (Feedback fb : feedbacks) {
            System.out.println(fb);
        }
    }
}

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
import model.Invoice;

/**
 *
 * @author Gigabyte
 */
public class InvoiceDAO extends DBContext{
    public List<Invoice> getAllInvoice() {
        List<Invoice> invoiceList = new ArrayList<>();
        String sql = "SELECT * FROM invoice ";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int invoiceID = rs.getInt("invoiceID");
                int customerID = rs.getInt("customerID");
                int examinationID = rs.getInt("examinationID");
                double totalAmount = rs.getDouble("totalAmount");
                String paymentStatus = rs.getString("paymentStatus");
                String paymentDate = rs.getString("paymentDate");
                String paymentMethod = rs.getString("paymentMethod");
                String createdAt = rs.getString("createdAt");
                Invoice inv = new Invoice(invoiceID,customerID, examinationID,totalAmount,paymentStatus,paymentDate,paymentMethod,createdAt);
                invoiceList.add(inv);
            }

        } catch (SQLException e) {
        }
        return invoiceList; // Trả về danh sách khách hàng
    }
}

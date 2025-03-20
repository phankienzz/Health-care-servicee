/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package FeedbackController;

import dao.FeedbackDAO;
import dao.InvoiceDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Hoang
 */
@WebServlet(name = "AddFeedback", urlPatterns = {"/addFeedback"})
public class AddFeedback extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Lấy dữ liệu từ form
            int customerId = Integer.parseInt(request.getParameter("customerId"));
            int rating = Integer.parseInt(request.getParameter("rating"));
            String comment = request.getParameter("comment");

            // Khởi tạo DAO
            InvoiceDAO inDAO = new InvoiceDAO();
            FeedbackDAO feedbackDAO = new FeedbackDAO();

            // Lấy invoiceID dựa trên customerID
            int invoiceID = inDAO.getInvoiceByCustomerID(customerId);
            if (invoiceID == -1) {
                // Trường hợp không tìm thấy invoiceID
                request.getSession().setAttribute("msg", "Không tìm thấy hóa đơn cho khách hàng này.");
                response.sendRedirect("loadfeedback");
                return;
            }

            // Thêm feedback
            boolean isAdded = feedbackDAO.addFeedback(invoiceID, rating, comment);

            // Kiểm tra kết quả và gửi phản hồi
            if (isAdded) {
                request.getSession().setAttribute("msg", "Cảm ơn bạn đã để lại phản hồi.");
            } else {
                request.getSession().setAttribute("msg", "Có lỗi xảy ra khi gửi Feedback. Vui lòng thử lại sau.");
            }
        } catch (NumberFormatException e) {
            request.getSession().setAttribute("msg", "Dữ liệu không hợp lệ. Vui lòng thử lại.");
            e.printStackTrace();
        } catch (Exception e) {
            // Xử lý lỗi chung
            request.getSession().setAttribute("msg", "Đã xảy ra lỗi không mong muốn. Vui lòng thử lại.");
            e.printStackTrace();
        }
        // Chuyển hướng về trang phản hồi
        response.sendRedirect("loadfeedback");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

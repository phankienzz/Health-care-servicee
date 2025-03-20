/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package FeedbackController;

import dao.FeedbackDAO;
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
        int invoiceID = Integer.parseInt(request.getParameter("invoiceId"));
        int rating = Integer.parseInt(request.getParameter("rating"));
        String comment = request.getParameter("comment");
        FeedbackDAO dao = new FeedbackDAO();
        boolean isAdded = dao.addFeedback(invoiceID, rating, comment);

        if (isAdded) {
            request.getSession().setAttribute("msg", "Cảm ơn bạn đã để lại phản hồi.");
        } else {
            request.getSession().setAttribute("msg", "Có lỗi xảy ra khi gửi Feedback. Vui lòng thử lại sau.");
        }
        response.sendRedirect("loadfeedback");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

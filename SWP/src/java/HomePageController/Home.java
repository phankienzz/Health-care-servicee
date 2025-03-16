/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package HomePageController;

import context.ValidFunction;
import dao.FeedbackDAO;
import dao.HomePageDAO;
import dao.VisitCounterDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Feedback;
import model.Service;

/**
 *
 * @author Hoang
 */
@WebServlet(name = "Home", urlPatterns = {"/home"})
public class Home extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Home</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Home at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ValidFunction valid = new ValidFunction();
        HomePageDAO dao = new HomePageDAO();
        FeedbackDAO feedbackDAO = new FeedbackDAO();
        VisitCounterDAO visitDAO = new VisitCounterDAO();

        HttpSession session = request.getSession();

        // Kiểm tra xem session đã có "visited" chưa
        if (session.getAttribute("visited") == null) {
            visitDAO.increaseVisitCount(); // Chỉ tăng khi chưa có
            session.setAttribute("visited", true); // Đánh dấu đã ghé thăm
        }
        // Lưu số lượt truy cập trong session
        int sessionVisitCount = (session.getAttribute("sessionVisitCount") == null) ? 1
                : (int) session.getAttribute("sessionVisitCount") + 1;
        session.setAttribute("sessionVisitCount", sessionVisitCount);

        List<Service> listService = dao.get4Service();
        List<Feedback> listFeedback = feedbackDAO.getAllFeedbacksByCustomer();

        for (Feedback feedback : listFeedback) {
            feedback.setDate(valid.formatDateNews(feedback.getDate()));
        }

        request.setAttribute("listService", listService);
        request.setAttribute("listFeedback", listFeedback);
        request.getRequestDispatcher("index_1.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

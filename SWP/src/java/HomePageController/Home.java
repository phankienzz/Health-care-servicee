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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ValidFunction valid = new ValidFunction();
        HomePageDAO dao = new HomePageDAO();
        FeedbackDAO feedbackDAO = new FeedbackDAO();
        VisitCounterDAO visitDAO = new VisitCounterDAO();

        // Kiểm tra session để tránh tăng lượt truy cập 2 lần
        HttpSession session = request.getSession();
        if (session.getAttribute("visited") == null) {
            visitDAO.updateVisitCount(); // Chỉ tăng khi chưa có session
            session.setAttribute("visited", true); // Đánh dấu đã ghé thăm
        }
        System.out.println("HomeServlet được gọi!");

        List<Service> listService = dao.get4Service();
        List<Feedback> listFeedback = feedbackDAO.getAllFeedback5StarByCustomer();

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
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

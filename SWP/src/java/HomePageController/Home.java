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
        List<Service> listService = dao.get4Service();
        List<Feedback> listFeedback = feedbackDAO.getAllFeedbacksByCustomer();

        for (Feedback feedback : listFeedback) {
            feedback.setDate(valid.formatDateNews(feedback.getDate()));
        }

        // Lấy session của người dùng
        HttpSession session = request.getSession();

        // Kiểm tra nếu session chưa có biến "visited"
        if (session.getAttribute("visited") == null) {
            // Nếu chưa có, tức là đây là lần đầu tiên truy cập trong phiên
            VisitCounterDAO daoV = new VisitCounterDAO();
            daoV.increaseVisitCount(); // Tăng số lượt truy cập trong database
            int visitCount = daoV.getVisitCount(); // Lấy số lượt truy cập mới

            // Đánh dấu rằng phiên này đã được tính lượt truy cập
            session.setAttribute("visited", true);

            // Lưu lượt truy cập vào ServletContext (tùy chọn)
            Integer contextVisitCount = (Integer) getServletContext().getAttribute("visitCount");
            if (contextVisitCount == null) {
                contextVisitCount = 0;
            }
            contextVisitCount++;
            getServletContext().setAttribute("visitCount", contextVisitCount);

            // Gửi số lượt truy cập đến JSP
            request.setAttribute("visitCount", visitCount);
            request.setAttribute("contextVisitCount", contextVisitCount);
        } else {
            // Nếu đã có "visited" trong session, chỉ lấy số lượt truy cập hiện tại mà không tăng
            VisitCounterDAO daoV = new VisitCounterDAO();
            int visitCount = daoV.getVisitCount();
            request.setAttribute("visitCount", visitCount);
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

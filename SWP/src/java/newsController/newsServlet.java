/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package newsController;

import dao.NewsDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import model.Category;
import model.News;

/**
 *
 * @author jaxbo
 */
@WebServlet(name = "newsServlet", urlPatterns = {"/news"})
public class newsServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet newsServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet newsServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    private String formatDate(String date) {
        if (date == null || date.isEmpty()) {
            return null;
        }
        try {
            // Chuyển từ chuỗi ngày ban đầu (yyyy-MM-dd HH:mm:ss) sang Timestamp
            java.sql.Timestamp timestamp = java.sql.Timestamp.valueOf(date);
            // Định dạng Timestamp thành chuỗi dd/MM/yyyy
            java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("dd/MM/yyyy");
            return dateFormat.format(timestamp);
        } catch (IllegalArgumentException e) {
            // Xử lý nếu chuỗi ngày không đúng định dạng ban đầu
            System.err.println("Error formatting date: " + e.getMessage());
            return null;
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String indexPage = request.getParameter("index");
        if (indexPage == null) {
            indexPage = "1";
        }

        int index = Integer.parseInt(indexPage);
        NewsDAO dao = new NewsDAO();
        List<Category> cateList = dao.getAllCategoryNews();
        List<News> pagingPage = dao.pagingNews(index);

        int count = dao.getTotalNews();
        int endPage = count / 3;
        if (count % 3 != 0) {
            endPage++;
        }

        // Định dạng ngày sử dụng phương thức tiện ích
        for (News news : pagingPage) {
            news.setCreated_at(formatDate(news.getCreated_at()));
            news.setUpdated_at(formatDate(news.getUpdated_at()));
        }

        request.setAttribute("pagingPage", pagingPage);
        request.setAttribute("endPage", endPage);
        request.setAttribute("cateList", cateList);
        request.setAttribute("tag", index);
        request.getRequestDispatcher("blog-sidebar.jsp").forward(request, response);
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

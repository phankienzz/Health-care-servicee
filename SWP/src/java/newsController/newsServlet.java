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
import java.util.List;
import model.Category;
import model.News;
import context.ValidFunction;
import java.util.ArrayList;

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
    
    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ValidFunction valid = new ValidFunction();
        NewsDAO dao = new NewsDAO();
        String indexPage = request.getParameter("page");
        String categoryID = request.getParameter("categoryID");
        String search = request.getParameter("search");

        if (search == null) {
            search = "";
        }
        if (indexPage == null) {
            indexPage = "1";
        }

        int page = Integer.parseInt(indexPage);
        List<News> pagingPage = new ArrayList<>();
        int totalNews = 0;
        int pageSize = 3; //so bai viet tren 1 page

        

        int endPage = totalNews / pageSize;
        if (totalNews % pageSize != 0) {
            endPage++;
        }

        // Định dạng ngày sử dụng phương thức tiện ích
        for (News news : pagingPage) {
            news.setCreated_at(valid.formatDateNews(news.getCreated_at()));
            news.setUpdated_at(valid.formatDateNews(news.getUpdated_at()));
        }

        List<Category> listCate = dao.getAllCategoryNews();

        request.setAttribute("pagingPage", pagingPage);
        request.setAttribute("endPage", endPage);
        request.setAttribute("listCate", listCate);
        request.setAttribute("categoryID", categoryID);
        request.setAttribute("search", search);
        request.setAttribute("page", page);
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

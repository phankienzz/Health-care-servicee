/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package blog;

import dao.NewsDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.News;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "HomeBlogSeverlet", urlPatterns = {"/homeblogseverlet"})
public class HomeBlogSeverlet extends HttpServlet {

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
        NewsDAO dao = new NewsDAO();

        int page = 1;
    int recordsPerPage = 6; 

    // Validate page parameter
    String pageParam = request.getParameter("page");
    if (pageParam != null) {
        try {
            page = Integer.parseInt(pageParam);
            if (page < 1) page = 1; // Minimum page is 1
        } catch (NumberFormatException e) {
            page = 1; // Default to page 1 if invalid
        }
    }

    // Fetch blogs and total count
    List<News> blogs = dao.getBlogsByPage((page - 1) * recordsPerPage, recordsPerPage);
    int totalRecords = dao.getTotalBlogCount();
    int totalPages = (int) Math.ceil((double) totalRecords / recordsPerPage);

    // Ensure totalPages is at least 1 even if no records
    if (totalRecords == 0) totalPages = 1;

    // Validate page against totalPages
    if (page > totalPages) page = totalPages;

    // Set attributes for JSP
    request.setAttribute("blogs", blogs);
    request.setAttribute("currentPage", page);
    request.setAttribute("totalPages", totalPages);
    request.getRequestDispatcher("blog.jsp").forward(request, response);
}

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

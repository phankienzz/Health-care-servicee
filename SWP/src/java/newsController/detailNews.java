
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package newsController;

import context.ValidFunction;
import dao.CommentDAO;
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
import model.Comment;
import model.News;

/**
 *
 * @author Hoang
 */
@WebServlet(name = "detailNews", urlPatterns = {"/detailNews"})
public class detailNews extends HttpServlet {

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
            out.println("<title>Servlet detailNews</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet detailNews at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ValidFunction valid = new ValidFunction();
        NewsDAO dao = new NewsDAO();
        CommentDAO commentDAO = new CommentDAO();
        String newsIdStr = request.getParameter("newsID");
        String parentCommentIdStr = request.getParameter("parent_comment_id");

        if (newsIdStr == null || newsIdStr.isEmpty()) {
            response.sendRedirect("allNews");
            return;
        }

        int newsID = Integer.parseInt(newsIdStr);
        News news = dao.getNewsByID(newsID);

        if (news == null) {
            response.sendRedirect("allNews");
            return;
        }

        List<Comment> comments = commentDAO.getCommentsByPostId(newsID);
        List<Category> listCate = dao.getAllCategoryNews();

//        for (Comment cm : comments) {
//            cm.setCreate_at(valid.formatDateNews(cm.getCreate_at()));
//        }

        news.setCreated_at(valid.formatDateNews(news.getCreated_at()));
//        news.setUpdated_at(valid.formatDateNews(news.getUpdated_at()));
        news.setUpdated_at(valid.formatDateTime(news.getUpdated_at(), "dd/MM/yyyy HH:mm"));

        int parentCommentId = 0;
        if (parentCommentIdStr != null && !parentCommentIdStr.isEmpty()) {
            try {
                parentCommentId = Integer.parseInt(parentCommentIdStr);
                Comment parentComment = commentDAO.getCommentById(parentCommentId);
                if (parentComment != null) {
                    request.setAttribute("parent_comment_name", parentComment.getCustomerID().getFullName());
                    request.setAttribute("parent_comment_id", parentCommentId);
                }
            } catch (NumberFormatException e) {
                parentCommentId = 0;
            }
        }

        request.setAttribute("newsDetail", news);
        request.setAttribute("listCate", listCate);
        request.setAttribute("comments", comments);

        request.getRequestDispatcher("detail-news.jsp").forward(request, response);
    }

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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package newsController;

import dao.CommentDAO;
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
@WebServlet(name = "deleteComment", urlPatterns = {"/deleteComment"})
public class deleteComment extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet deleteComment</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet deleteComment at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int commentID = Integer.parseInt(request.getParameter("comment_id"));
        CommentDAO dao = new CommentDAO();
        boolean isDeleted = dao.deleteCommentRecursively(commentID);
        if (isDeleted) {
            // Xóa thành công, chuyển hướng trở lại trang hiện tại
            response.sendRedirect(request.getHeader("Referer"));
        } else {
            // Xóa thất bại, hiển thị thông báo lỗi
            response.getWriter().println("Failed to delete comment.");
        }
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

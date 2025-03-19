/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package ProfessionalController;

import dao.CommentCustomerDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Comments;

/**
 *
 * @author Win11
 */
public class EditCommentServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
            out.println("<title>Servlet EditCommentServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditCommentServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
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
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
       try {
            int commentId = Integer.parseInt(request.getParameter("commentId"));
            String newText = request.getParameter("commentText").trim();

            // Kiểm tra nếu commentText không rỗng
            if (newText.isEmpty()) {
                request.setAttribute("error", "Comment cannot be empty.");
                request.getRequestDispatcher("comments.jsp").forward(request, response);
                return;
            }
            Cookie[] cookies = request.getCookies();
            String id="";
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("staffID".equals(cookie.getName())) {
                    id = cookie.getValue();
                    System.out.println("Sender Email from Cookie: " + id);
                }
            }
        } else {
            System.out.println("No cookies found!");
            response.sendRedirect("login.jsp");
        }
            CommentCustomerDAO dao = new CommentCustomerDAO();
            // Gọi DAO để cập nhật bình luận
            boolean updated = dao.updateComment(commentId, newText);
            HttpSession session = request.getSession();
        List<Comments> listc = dao.getRootComments();
        for (Comments comments : listc) {
            comments.replies=dao.getCommentsByReplyToCommentID(comments.getCommentId(),Integer.parseInt(id));
        }
        session.setAttribute("comments", listc);
        session.setAttribute("list", listc);
            if (updated) {
                response.sendRedirect("Comment.jsp"); // Load lại trang sau khi cập nhật thành công
            } else {
                request.setAttribute("error", "Failed to update comment.");
                request.getRequestDispatcher("Comment.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("comments.jsp?error=Invalid request");
        }
    }

    /**
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package newsController;

import dao.StaffReplyDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.StaffReply;

/**
 *
 * @author Hoang
 */
@WebServlet(name = "staffReply", urlPatterns = {"/staffReply"})
public class staffReply extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet staffReply</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet staffReply at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String commentIdParam = request.getParameter("comment_id");
        String staffIdParam = request.getParameter("staff_id");
        String content = request.getParameter("content");
        String newsId = request.getParameter("newsID");

        if (commentIdParam == null || staffIdParam == null || content == null
                || commentIdParam.isEmpty() || staffIdParam.isEmpty() || content.isEmpty()) {
            response.sendRedirect("detailNews?newsID=" + newsId);
            return;
        }

        int commentId = Integer.parseInt(commentIdParam);
        int staffId = Integer.parseInt(staffIdParam);

        // Tạo đối tượng StaffReply
        StaffReply reply = new StaffReply();
        reply.setComment_id(commentId);
        reply.setStaff_id(staffId);
        reply.setContent(content);
        reply.setStatus(1);

        StaffReplyDAO dao = new StaffReplyDAO();
        boolean isAdded = dao.addStaffReply(reply);

        if (isAdded) {
            response.sendRedirect("detailNews?newsID=" + newsId + "&replySuccess=true");
        } else {
            request.setAttribute("error", "Unable to add staff reply. Please try again.");
            request.getRequestDispatcher("detailNews?newsID=" + newsId).forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

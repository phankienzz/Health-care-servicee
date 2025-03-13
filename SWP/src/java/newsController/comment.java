/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package newsController;

import dao.CommentDAO;
import dao.CustomerDAO;
import dao.StaffDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Comment;
import model.Customer;
import model.Staff;

/**
 *
 * @author Hoang
 */
@WebServlet(name = "comment", urlPatterns = {"/comment"})
public class comment extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet comment</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet comment at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String content = request.getParameter("content");
        String postIdParam = request.getParameter("newsID");
        String customerIdParam = request.getParameter("customerId");
        String staffIdParam = request.getParameter("staffId");
        String parentCommentIdParam = request.getParameter("parent_comment_id");

        try {
            if (postIdParam == null || postIdParam.isEmpty() || (customerIdParam == null || customerIdParam.isEmpty()) && (staffIdParam == null || staffIdParam.isEmpty())) {
                response.getWriter().println("Post ID, Customer ID, or Staff ID is missing.");
                return;
            }

            int postId = Integer.parseInt(postIdParam);
            int parentCommentId = (parentCommentIdParam != null && !parentCommentIdParam.isEmpty()) ? Integer.parseInt(parentCommentIdParam) : 0;

            Customer customer = null;
            Staff staff = null;

            if (customerIdParam != null && !customerIdParam.isEmpty()) {
                int customerId = Integer.parseInt(customerIdParam);
                CustomerDAO customerDAO = new CustomerDAO();
                customer = customerDAO.getCustomerByID(customerId);
            }

            if (staffIdParam != null && !staffIdParam.isEmpty()) {
                int staffId = Integer.parseInt(staffIdParam);
                StaffDAO staffDAO = new StaffDAO();
                staff = staffDAO.getStaffByID(staffId);
            }

            if (customer != null || staff != null) {
                Comment comment = new Comment();
                comment.setPost_id(postId);
                comment.setCustomerID(customer);
                comment.setStaff_id(staff);
                comment.setContent(content.trim());
                comment.setParent_comment_id(parentCommentId);
                comment.setStatus(1);

                CommentDAO dao = new CommentDAO();

                if (parentCommentId != 0) {
                    Comment parentComment = dao.getCommentById(parentCommentId);
                    if (parentComment != null) {
                        request.setAttribute("parent_comment_name", parentComment.getCustomerID() != null
                                ? parentComment.getCustomerID().getFullName()
                                : "[Staff]" + parentComment.getStaff_id().getFullName());
                    }
                }
                boolean isAdded = dao.insertComment(comment);
                if (isAdded) {
                    response.sendRedirect("detailNews?newsID=" + postId + "&commentSuccess=true&parent_comment_id=" + parentCommentId);
                } else {
                    request.setAttribute("errorMessage", "Unable to add your comment. Please try again.");
                    request.getRequestDispatcher("detailNews?newsID=" + postId).forward(request, response);
                }
            } else {
                request.setAttribute("errorMessage", "Invalid customer or staff. Please login.");
                request.getRequestDispatcher("detailNews?newsID=" + postId).forward(request, response);
            }
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid input: Post ID, Customer ID, or Staff ID is not valid.");
            request.getRequestDispatcher("error-page.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.CustomerDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Customer;

/**
 *
 * @author jaxbo
 */
public class RegisterServet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RegisterServet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegisterServet at " + request.getContextPath() + "</h1>");
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
        response.setContentType("text/html;charset=UTF-8");
        try {
            String username = request.getParameter("username");
            String fullname = request.getParameter("fullname");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String password = request.getParameter("password");
            String confirm_password = request.getParameter("confirm_password");
            String address = request.getParameter("address");
            String dateOfBirth = request.getParameter("dateOfBirth");
            String gender = request.getParameter("gender");
            if (!password.equals(confirm_password)) {
                request.setAttribute("error", "Passwords do not match !");
                request.getRequestDispatcher("register.jsp").forward(request, response);
            } else {
                CustomerDAO dao = new CustomerDAO();
                Customer account = dao.checkCustomerAccountExist(username, email);
                if (account == null) {
                    dao.customerSignup(username, password, fullname, email, phone, address, dateOfBirth, gender);
                    response.sendRedirect("login.jsp");
                } else {
                    request.setAttribute("error", "Account Exist !");
                    request.getRequestDispatcher("register.jsp").forward(request, response);
                }
            }
        } catch (Exception e) {
        }
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

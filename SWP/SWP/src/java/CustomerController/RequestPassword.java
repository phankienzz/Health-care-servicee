/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package CustomerController;

import dao.CustomerDAO;
import dao.TokenForgetDAO;
import forgotPassword.resetService;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import model.Customer;
import model.TokenForgetPassword;

/**
 *
 * @author jaxbo
 */
public class RequestPassword extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RequestPassword</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RequestPassword at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("forgot-password.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CustomerDAO dao = new CustomerDAO();
        String email = request.getParameter("email");
        Customer customer = dao.getCustomerByEmail(email);
        if (customer == null) {
            request.setAttribute("mess", "Email khong ton tai");
            request.getRequestDispatcher("forgot-password.jsp").forward(request, response);
            return;
        }
        resetService service = new resetService();
        String token = service.generateToken();
        String linkreset = "http://localhost:8080/SWP/resetPassword?token=" + token;
        TokenForgetPassword newTokenForget = new TokenForgetPassword(customer.getCustomerID(), false, token, service.expireDateTime());

        TokenForgetDAO daoToken = new TokenForgetDAO();
        boolean isInsert = daoToken.insertTokenForget(newTokenForget);
        if (!isInsert) {
            request.setAttribute("mess", "have error in server");
            request.getRequestDispatcher("forgot-password.jsp").forward(request, response);
            return;
        }
        boolean isSend = service.sendEmail(email, linkreset, customer.getUsername());
        if (!isSend) {
            request.setAttribute("mess", "can not send request");
            request.getRequestDispatcher("forgot-password.jsp").forward(request, response);
            return;
        }
        request.setAttribute("mess", "send request successfully");
        request.getRequestDispatcher("forgot-password.jsp").forward(request, response);
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

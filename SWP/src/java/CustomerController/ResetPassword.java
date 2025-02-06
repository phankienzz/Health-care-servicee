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
import jakarta.servlet.http.HttpSession;
import model.Customer;
import model.TokenForgetPassword;

public class ResetPassword extends HttpServlet {

    CustomerDAO daoCustomer = new CustomerDAO();
    TokenForgetDAO daoToken = new TokenForgetDAO();

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
            out.println("<title>Servlet ResetPassword</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ResetPassword at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String token = request.getParameter("token");
        HttpSession session = request.getSession();
        if (token != null) {
            TokenForgetPassword tokenForgetPassword = daoToken.getTokenPassword(token);
            resetService service = new resetService();
            if (tokenForgetPassword.isIsUsed()) {
                request.setAttribute("mess", "token is used");
                request.getRequestDispatcher("forgot-password.jsp").forward(request, response);
                return;
            }
            if (service.isExpireTime(tokenForgetPassword.getExpiryTime())) {
                request.setAttribute("mess", "token is expiry time");
                request.getRequestDispatcher("forgot-password.jsp").forward(request, response);
                return;
            }
            Customer customer = daoCustomer.getCustomerByID(tokenForgetPassword.getCustomerID());
            request.setAttribute("email", customer.getEmail());
            session.setAttribute("token", tokenForgetPassword.getToken());
            request.getRequestDispatcher("reset-password.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("reset-password.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirm-password");
        if (!password.equals(confirmPassword)) {
            request.setAttribute("mess", "confirm password must same password");
            request.setAttribute("email", email);
            request.getRequestDispatcher("reset-password.jsp").forward(request, response);
            return;
        }
        HttpSession session = request.getSession();
        TokenForgetPassword tokenForgetPassword = new TokenForgetPassword();
        tokenForgetPassword.setToken((String) session.getAttribute("token"));
        tokenForgetPassword.setIsUsed(true);
        daoCustomer.updatePassword(email, password);
        daoToken.updateStatus(tokenForgetPassword);
        
        request.getRequestDispatcher("index_1.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

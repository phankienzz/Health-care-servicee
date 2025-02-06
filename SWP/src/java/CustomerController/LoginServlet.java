/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package CustomerController;

import dao.CustomerDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Customer;

/**
 *
 * @author jaxbo
 */
public class LoginServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoginServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginServlet at " + request.getContextPath() + "</h1>");
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
        String usernameOrEmail = request.getParameter("usernameOrEmail");
        String password = request.getParameter("password");
        String rememberMe = request.getParameter("rememberMe");

        CustomerDAO dao = new CustomerDAO();
        Customer customerAccount = dao.customerLogin(usernameOrEmail, password);

        if (customerAccount == null) {
            request.setAttribute("error", "Invalid username, email, or password !");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("customerAccount", customerAccount);

            if (rememberMe != null) {
                Cookie usernameCookie = new Cookie("usernameOrEmail", usernameOrEmail);
                Cookie passwordCookie = new Cookie("password", usernameOrEmail);

                usernameCookie.setMaxAge(30 * 24 * 60 * 60);
                passwordCookie.setMaxAge(30 * 24 * 60 * 60);

                response.addCookie(usernameCookie);
                response.addCookie(passwordCookie);
            } else {
                // Nếu "Remember Me" không được chọn, xóa cookies
                Cookie usernameCookie = new Cookie("usernameOrEmail", "");
                Cookie passwordCookie = new Cookie("password", "");
                usernameCookie.setMaxAge(0);
                passwordCookie.setMaxAge(0);
                response.addCookie(usernameCookie);
                response.addCookie(passwordCookie );
            }
            response.sendRedirect("index_1.jsp");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

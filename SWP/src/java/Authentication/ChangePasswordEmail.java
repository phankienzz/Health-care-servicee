/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Authentication;

import util.ValidFunction;
import dao.CustomerDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Customer;

/**
 *
 * @author Phan Huu Kien
 */
@WebServlet(name = "ChangePassword", urlPatterns = {"/changePassword"})
public class ChangePasswordEmail extends HttpServlet {

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
            out.println("<title>Servlet ChangePassword</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ChangePassword at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");
        String email = request.getParameter("email");
        ValidFunction valid = new ValidFunction();
        // Kiểm tra nếu mật khẩu mới và mật khẩu xác nhận giống nhau

        try {
            if (newPassword.equals(confirmPassword)) {
                // Cập nhật mật khẩu mới cho người dùng trong cơ sở dữ liệu              
                CustomerDAO customerDAO = new CustomerDAO();
                Customer customer = customerDAO.getCustomerByEmail(email);

                
                if (customer != null) {
//                    String hashashedPassword = valid.hashPassword(newPassword);
//                    
//                     customer.setPassword(hashedPassword); 
                 String hashedPassword = valid.hashPassword(newPassword); // Khai báo và băm mật khẩu
                 customer.setPassword(hashedPassword);
                      boolean result = customerDAO.updateCustomerbyId(customer);
                    //customerDAO.updateCustomerbyId(customer);
                    if(result){
                        System.out.println("New Password: " + newPassword);
                        System.out.println("Confirm Password: " + confirmPassword);
                        System.out.println("Email: " + email);
                         response.sendRedirect("login.jsp");
                         System.out.println("Hashed Password: " + hashedPassword);
                    }
                    else{
                    request.setAttribute("err", "Không thể cập nhật mật khẩu. Vui lòng thử lại.");
                    request.getRequestDispatcher("new-password.jsp").forward(request, response);
                    }
                }

            } else {
                // Nếu mật khẩu mới và mật khẩu xác nhận không khớp
                request.setAttribute("err", "Mật khẩu xác nhận không khớp.");
                request.setAttribute("emailr", email);
                request.getRequestDispatcher("new-password.jsp").forward(request, response);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
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

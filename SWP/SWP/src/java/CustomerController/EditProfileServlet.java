/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package CustomerController;

import dao.CustomerDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Customer;

/**
 *
 * @author jaxbo
 */
public class EditProfileServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EditProfileServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditProfileServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        HttpSession session = request.getSession();
//        Customer customerProfile = (Customer) session.getAttribute("customerAccount");
//
//        // Kiểm tra nếu chưa đăng nhập
//        if (customerProfile == null) {
//            response.sendRedirect("login.jsp");
//            return;
//        }
//
//        // Chuyển tiếp tới trang editProfile.jsp để hiển thị thông tin cũ
//        request.setAttribute("customerProfile", customerProfile);
//        request.getRequestDispatcher("edit-profile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String dateOfBirth = request.getParameter("dateOfBirth");
        String gender = request.getParameter("gender");

        HttpSession session = request.getSession();
        Customer customerProfile = (Customer) session.getAttribute("customerAccount");

        if (customerProfile == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        int customerID = customerProfile.getCustomerID();

        CustomerDAO dao = new CustomerDAO();
        dao.updateCutomerProfile(fullName, email, phone, address, dateOfBirth, gender, customerID);

        customerProfile.setFullName(fullName);
        customerProfile.setEmail(email);
        customerProfile.setPhone(phone);
        customerProfile.setAddress(address);
        customerProfile.setDateOfBirth(dateOfBirth);
        customerProfile.setGender(gender);

        session.setAttribute("customerAccount", customerProfile);
        response.sendRedirect("profile");

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

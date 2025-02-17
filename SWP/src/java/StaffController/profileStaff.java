/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package StaffController;

import context.ValidFunction;
import dao.StaffDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Staff;

/**
 *
 * @author Gigabyte
 */
@WebServlet(name = "profileStaff", urlPatterns = {"/profileStaff"})
public class profileStaff extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet profileStaff</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet profileStaff at " + request.getContextPath() + "</h1>");
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
        String fullName = request.getParameter("fullName");
        String dateOfBirth = request.getParameter("dateOfBirth");
        String gender = request.getParameter("gender");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        StaffDAO staffDAO = new StaffDAO();
        ValidFunction valid = new ValidFunction();
        if (valid.containsDigitOrSpecialChar(fullName) || fullName.isEmpty()) {
            request.setAttribute("error", "Name cannot contain digit or special character");
            request.getRequestDispatcher("edit-profile.jsp").forward(request, response);
            return;
        }
        if (!valid.isValidPhoneNumber(phone)) {
            request.setAttribute("error", "Phone number is not exist, please check again");
            request.getRequestDispatcher("edit-profile.jsp").forward(request, response);
            return;
        }
        HttpSession session = request.getSession();
        Staff s = (Staff) session.getAttribute("staffAccount");
        session.removeAttribute("staffAccount");

        staffDAO.updateStaffInfo(s.getStaffID(), valid.normalizeName(fullName), email, phone, dateOfBirth, gender, address);
        Staff staff = staffDAO.getStaffByID(s.getStaffID());
        Staff st = new Staff(staff.getStaffID(), staff.getFullName(), staff.getEmail(), staff.getPassword(), staff.getPhone(), staff.getGender(), valid.formatDate(staff.getDateOfBirth()), staff.getAddress(), valid.formatDate(staff.getHireDate()), staff.getRoleID(), staff.getStatus(), staff.getProfilePicture());
        session.setAttribute("staffAccount", st);
        request.setAttribute("mess", "Update staff succesfully");

        request.getRequestDispatcher("edit-profile.jsp").forward(request, response);
    }

}

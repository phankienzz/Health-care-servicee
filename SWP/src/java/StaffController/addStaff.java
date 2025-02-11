/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package StaffController;

import context.ValidFunction;
import dao.RoleDAO;
import dao.StaffDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Role;
import model.Staff;

/**
 *
 * @author Gigabyte
 */
@WebServlet(name = "addStaff", urlPatterns = {"/addStaff"})
public class addStaff extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        StaffDAO staffDAO = new StaffDAO();
        List<Staff> listStaff = staffDAO.getAllStaff();
        RoleDAO roleDAO = new RoleDAO();
        List<Role> listRole = roleDAO.getAllRole();
        request.setAttribute("listStaff", listStaff);
        request.setAttribute("listRole", listRole);
        request.getRequestDispatcher("add-staff.jsp").forward(request, response);
    }

    

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ValidFunction valid = new ValidFunction();
        StaffDAO staffDAO = new StaffDAO();
        RoleDAO roleDAO = new RoleDAO();
        List<Role> listRole = roleDAO.getAllRole();
        request.setAttribute("listRole", listRole);
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String roleID = request.getParameter("roleID");
        String hireDate = request.getParameter("hireDate");
        String password = request.getParameter("password");
        String confirmPass = request.getParameter("confirmPass");
        String status = request.getParameter("status");
        request.setAttribute("firstName", firstName);
        request.setAttribute("lastName", lastName);
        request.setAttribute("phone", phone);
        request.setAttribute("email", email);
        request.setAttribute("roleID", roleID);
        request.setAttribute("status", status);
        request.setAttribute("hireDate", hireDate);
        request.setAttribute("password", password);
        request.setAttribute("confirmPass", confirmPass);
        if (valid.containsDigitOrSpecialChar(firstName) || valid.containsDigitOrSpecialChar(lastName)) {
            request.setAttribute("error", "First Name or Last Name cannot contain digit or special character");
            request.getRequestDispatcher("add-staff.jsp").forward(request, response);
            return;
        }
        if (!valid.isValidPassword(password)) {
            request.setAttribute("error", "Password must contain at least 8 character, uppercase letter, digit and special character");
            request.getRequestDispatcher("add-staff.jsp").forward(request, response);
            return;
        }
        if (!password.equals(confirmPass)) {
            request.setAttribute("error", "Confirm password incorrect");
            request.getRequestDispatcher("add-staff.jsp").forward(request, response);
            return;
        }
        if (!valid.isValidPhoneNumber(phone)) {
            request.setAttribute("error", "Phone number is not exist, please check again");
            request.getRequestDispatcher("add-staff.jsp").forward(request, response);
            return;
        }
        String fullName = valid.normalizeName(firstName) + " " + valid.normalizeName(lastName);
        staffDAO.createStaff(fullName, email, password, phone, hireDate, Integer.parseInt(roleID), status);
        request.setAttribute("mess", "Add staff succesfully");
        request.removeAttribute("firstName");
        request.removeAttribute("lastName");
        request.removeAttribute("phone");
        request.removeAttribute("email");
        request.removeAttribute("roleID");
        request.removeAttribute("hireDate");
        request.removeAttribute("status");
        request.removeAttribute("password");
        request.removeAttribute("confirmPass");
        request.getRequestDispatcher("add-staff.jsp").forward(request, response);

    }

}

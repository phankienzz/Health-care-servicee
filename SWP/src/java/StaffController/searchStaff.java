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
import java.util.ArrayList;
import java.util.List;
import model.Role;
import model.Staff;

/**
 *
 * @author Gigabyte
 */
@WebServlet(name = "searchStaff", urlPatterns = {"/searchStaff"})
public class searchStaff extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String staffID_raw = request.getParameter("staffID");
        String name = request.getParameter("name");
        String roleID = request.getParameter("roleID");

        ValidFunction valid = new ValidFunction();
        RoleDAO roleDAO = new RoleDAO();
        List<Role> listRole = roleDAO.getAllRole();
        request.setAttribute("listRole", listRole);
        StaffDAO staffDAO = new StaffDAO();
        List<Staff> listStaff = new ArrayList<>();
        if (staffID_raw.isEmpty() && name.isEmpty() && roleID.equalsIgnoreCase("Select role")) {
            listStaff = staffDAO.getAllStaff();
        }
        if (!staffID_raw.isEmpty()) {
            try {
                int staffID = Integer.parseInt(staffID_raw);
                Staff s = new Staff();
                s = staffDAO.getStaffByID(staffID);
                request.setAttribute("staffID", staffID);
                listStaff.add(s);
            } catch (Exception e) {
                listStaff = staffDAO.getAllStaff();
                request.setAttribute("listStaff", listStaff);
                request.getRequestDispatcher("staff.jsp").forward(request, response);
            }
        }
        if ( !name.isEmpty()) {
            request.setAttribute("name", name);
            listStaff = staffDAO.getStaffByName(valid.normalizeName(name));
        }
        if (!roleID.equalsIgnoreCase("Select role")) {
            request.setAttribute("roleID", roleID);
            listStaff = staffDAO.getStaffByRole(Integer.parseInt(roleID));
        }

        request.setAttribute("listStaff", listStaff);

        request.getRequestDispatcher("staff.jsp").forward(request, response);
    }

}

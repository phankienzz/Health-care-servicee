/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package StaffController;

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
    public static String normalizeName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return "";
        }
        name = name.trim().toLowerCase(); // Loại bỏ khoảng trắng đầu cuối và chuyển về chữ thường
        String[] words = name.split("\\s+"); // Tách các từ dựa vào khoảng trắng
        StringBuilder normalized = new StringBuilder();

        for (String word : words) {
            if (!word.isEmpty()) {
                normalized.append(Character.toUpperCase(word.charAt(0))) // Viết hoa chữ cái đầu
                        .append(word.substring(1)) // Giữ lại phần còn lại
                        .append(" "); // Thêm khoảng trắng giữa các từ
            }
        }

        return normalized.toString().trim(); // Loại bỏ khoảng trắng cuối cùng
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String staffID_raw = request.getParameter("staffID");
        String name = request.getParameter("name");
        String roleID = request.getParameter("roleID");
        
        RoleDAO roleDAO = new RoleDAO();
        List<Role> listRole = roleDAO.getAllRole();
        request.setAttribute("listRole", listRole);
        StaffDAO staffDAO = new StaffDAO();
        List<Staff> listStaff = new ArrayList<>();
        if (staffID_raw.isEmpty() && name.isEmpty() && roleID.equalsIgnoreCase("Select role")) {
            listStaff = staffDAO.getAllStaff();
        }
        if (staffID_raw != null && !staffID_raw.isEmpty()) {
            try {
                int staffID = Integer.parseInt(staffID_raw);
                Staff s = new Staff();
                s = staffDAO.getStaffByID(staffID);
                listStaff.add(s);
            } catch (Exception e) {
                listStaff = staffDAO.getAllStaff();
                request.setAttribute("listStaff", listStaff);
                request.getRequestDispatcher("staff.jsp").forward(request, response);
            }
        }
        if (name != null && !name.isEmpty()) {
            listStaff = staffDAO.getStaffByName(normalizeName(name));
        }
        if (!roleID.equalsIgnoreCase("Select role")) {
            listStaff = staffDAO.getStaffByRole(Integer.parseInt(roleID));
        }

        request.setAttribute("listStaff", listStaff);

        request.getRequestDispatcher("staff.jsp").forward(request, response);
    }

}

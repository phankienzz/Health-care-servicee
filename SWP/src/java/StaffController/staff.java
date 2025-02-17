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
@WebServlet(name = "staff", urlPatterns = {"/staff"})
public class staff extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        StaffDAO staffDAO = new StaffDAO();
        List<Staff> listStaff = staffDAO.getAllStaff();
        List<Staff> list = new ArrayList<>();
        ValidFunction valid = new ValidFunction();
        RoleDAO roleDAO = new RoleDAO();
        List<Role> listRole = roleDAO.getAllRole();
        request.setAttribute("listRole", listRole);
        int page = 1;
        int recordsPerPage = 5;

        int totalRecords = listStaff.size();
        int totalPages = (int) Math.ceil(totalRecords * 1.0 / recordsPerPage);
        if (request.getParameter("page") != null) {
            try {
                page = Integer.parseInt(request.getParameter("page"));
                // Ngăn truy cập trang không hợp lệ
                if (page < 1) {
                    page = 1;
                }
                if (page > totalPages) {
                    page = totalPages;
                }
            } catch (NumberFormatException e) {
                page = 1; // Nếu nhập sai định dạng, về trang đầu
            }
        }
        int start = (page - 1) * recordsPerPage;
        List<Staff> staffs = staffDAO.getStaffs(start, recordsPerPage);

        for (Staff s : staffs) {
            String dateBirth;
            if (s.getDateOfBirth() != null) {
                dateBirth = valid.formatDate(s.getDateOfBirth());
            } else {
                dateBirth = s.getDateOfBirth();
            }
            Staff r = new Staff(s.getStaffID(), s.getFullName(), s.getEmail(), s.getPassword(), s.getPhone(), s.getGender(), dateBirth, s.getAddress(), valid.formatDate(s.getHireDate()), s.getRoleID(), s.getStatus(), s.getProfilePicture());
            list.add(r);
        }
        request.setAttribute("size", listStaff.size());
        request.setAttribute("listStaff", list);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.getRequestDispatcher("staff.jsp").forward(request, response);

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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

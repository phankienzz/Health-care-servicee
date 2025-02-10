/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package StaffController;

import static StaffController.addStaff.containsDigitOrSpecialChar;
import static StaffController.addStaff.isValidPassword;
import static StaffController.addStaff.isValidPhoneNumber;
import static StaffController.addStaff.normalizeName;
import dao.RoleDAO;
import dao.StaffDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import model.Role;
import model.Staff;

/**
 *
 * @author Gigabyte
 */
@WebServlet(name = "editStaff", urlPatterns = {"/editStaff"})
public class editStaff extends HttpServlet {

    public static String formatDate(String input) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDateTime dateTime = LocalDateTime.parse(input, inputFormatter);
        return dateTime.format(outputFormatter);
    }

    public static boolean containsDigitOrSpecialChar(String str) {
        return str.matches(".*[^a-zA-Z\\s].*"); // Kiểm tra nếu có ký tự không phải chữ cái hoặc khoảng trắng
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

    public static boolean isValidPhoneNumber(String input) {
        // Kiểm tra chuỗi có đúng 10 ký tự số không
        if (!input.matches("\\d{10}")) {
            return false;
        }

        // Kiểm tra chuỗi có bắt đầu bằng '0' không
        return input.startsWith("0");
    }

    public static boolean isValidPassword(String password) {
        // Kiểm tra độ dài ít nhất 8 ký tự
        if (password.length() < 8) {
            return false;
        }

        // Biểu thức chính quy kiểm tra điều kiện
        String regex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$";

        return password.matches(regex);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String staffID_raw = request.getParameter("staffID");
        StaffDAO staffDAO = new StaffDAO();
        int staffID = Integer.parseInt(staffID_raw);
        Staff s = staffDAO.getStaffByID(staffID);
        String fullName = s.getFullName();
        String[] split = fullName.split("\\s+");
        int i = 0;
        String firstName = "";
        String lastName = split[split.length - 1];
        for (String str : split) {
            if (i != str.length() - 1) {
                firstName += " " + str;
            }
        }
        request.setAttribute("staffID", staffID);
        request.setAttribute("firstName", firstName.trim());
        request.setAttribute("lastName", lastName.trim());
        request.setAttribute("phone", s.getPhone());
        request.setAttribute("email", s.getEmail());
        request.setAttribute("roleID", s.getRoleID());
        request.setAttribute("status", s.getStatus());
        request.setAttribute("hireDate", formatDate(s.getHireDate()));
        request.setAttribute("password", s.getPassword());
        request.setAttribute("confirmPass", s.getPassword());
        RoleDAO roleDAO = new RoleDAO();
        List<Role> listRole = roleDAO.getAllRole();
        request.setAttribute("listRole", listRole);
        request.getRequestDispatcher("edit-staff.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String staffID_raw = request.getParameter("staffID");
        int staffID = Integer.parseInt(staffID_raw);
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
        request.setAttribute("staffID", staffID_raw);
        if (containsDigitOrSpecialChar(firstName) || containsDigitOrSpecialChar(lastName)) {
            request.setAttribute("error", "First Name or Last Name cannot contain digit or special character");
            request.getRequestDispatcher("edit-staff.jsp").forward(request, response);
            return;
        }
        if (!isValidPassword(password)) {
            request.setAttribute("error", "Password must contain at least 8 character, uppercase letter, digit and special character");
            request.getRequestDispatcher("edit-staff.jsp").forward(request, response);
            return;
        }
        if (!password.equals(confirmPass)) {
            request.setAttribute("error", "Confirm password incorrect");
            request.getRequestDispatcher("edit-staff.jsp").forward(request, response);
            return;
        }
        if (!isValidPhoneNumber(phone)) {
            request.setAttribute("error", "Phone number is not exist, please check again");
            request.getRequestDispatcher("edit-staff.jsp").forward(request, response);
            return;
        }
        String fullName = normalizeName(firstName) + " " + normalizeName(lastName);
        staffDAO.updateStaff(staffID, fullName, email, password, phone, hireDate, Integer.parseInt(roleID), status);
        request.setAttribute("mess", "Update staff succesfully");
        request.getRequestDispatcher("edit-staff.jsp").forward(request, response);
    }

}

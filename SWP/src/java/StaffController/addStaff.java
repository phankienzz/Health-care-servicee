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
import java.util.List;
import model.Role;
import model.Staff;

/**
 *
 * @author Gigabyte
 */
@WebServlet(name="addStaff", urlPatterns={"/addStaff"})
public class addStaff extends HttpServlet {

   

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        StaffDAO staffDAO = new StaffDAO();
        List<Staff> listStaff = staffDAO.getAllStaff();
        RoleDAO roleDAO = new RoleDAO();
        List<Role> listRole = roleDAO.getAllRole();
        request.setAttribute("listStaff", listStaff);
        request.setAttribute("listRole", listRole );
        request.getRequestDispatcher("add-staff.jsp").forward(request, response);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String staffID = request.getParameter("staffID");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String roleID = request.getParameter("roleID");
        String hireDate = request.getParameter("hireDate");
        String password = request.getParameter("password");
        String confirmPass = request.getParameter("confirmPass");
        if(containsDigitOrSpecialChar(firstName) || containsDigitOrSpecialChar(lastName)){
            request.setAttribute("error", "First Name or Last Name cannot contain digit or special character");
            request.getRequestDispatcher("add-staff.jsp").forward(request, response);
            return;
        }
        if(!isValidPhoneNumber(phone)){
            request.setAttribute("error", "Phone number is not exist, please check again");
            request.getRequestDispatcher("add-staff.jsp").forward(request, response);
            return;
        }
        if(!isValidPassword(password)){
            request.setAttribute("error", "Password must contain at least 8 character, digit and special character");
            request.getRequestDispatcher("add-staff.jsp").forward(request, response);
            return;
        }
        if(!password.equals(confirmPass)){
            request.setAttribute("error", "Confirm password incorrect");
            request.getRequestDispatcher("add-staff.jsp").forward(request, response);
            return;
        }
        String fullName = normalizeName(firstName) + " " + normalizeName(lastName);
        
        
    }


}

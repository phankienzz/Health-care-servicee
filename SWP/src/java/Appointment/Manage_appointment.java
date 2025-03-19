/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Appointment;

import dao.MedicalExaminationDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.MedicalExamination;
import model.Professional;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "Manage_appointment", urlPatterns = {"/manage_appointment"})
public class Manage_appointment extends HttpServlet {

    private MedicalExaminationDAO medicalExaminationDAO = new MedicalExaminationDAO();

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy tham số từ form lọc
        String patientName = request.getParameter("patientName");
        String ageSort = request.getParameter("ageSort");
        String appointmentDate = request.getParameter("appointmentDate");
        String timeCreatedSort = request.getParameter("timeCreatedSort");
        String status = request.getParameter("status");
        String doctorName= "";
        // Tham số phân trang
        int page = 1; // Trang mặc định
        int pageSize = 10; // Số bản ghi mỗi trang (có thể thay đổi)
        String pageStr = request.getParameter("page");
        if (pageStr != null && !pageStr.isEmpty()) {
            page = Integer.parseInt(pageStr);
        }
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("staffID".equals(cookie.getName())) {
                     doctorName = cookie.getValue();
                    System.out.println("Doctor ID from Cookie: " + doctorName);
                }
            }
        } else {
            System.out.println("No cookies found!");
        }
        // Tính tổng số bản ghi để xác định số trang
        int totalRecords = medicalExaminationDAO.getTotalFilteredRecords2(
                patientName, ageSort,Integer.parseInt(doctorName) , appointmentDate, timeCreatedSort, status);
        int totalPages = (int) Math.ceil((double) totalRecords / pageSize);

        // Đảm bảo page không vượt quá giới hạn
        if (page < 1) page = 1;
        if (page > totalPages && totalPages > 0) page = totalPages;
        
        
        // Lấy danh sách đã lọc với phân trang
        List<MedicalExamination> list = medicalExaminationDAO.getFilteredExaminations2(
                patientName, ageSort, Integer.parseInt(doctorName), appointmentDate, timeCreatedSort, status, page, pageSize);

        // Lấy danh sách tất cả bác sĩ để hiển thị trong dropdown
        List<Professional> allProfessionals = medicalExaminationDAO.getAllProfessionals();

        // Đặt dữ liệu vào request
        request.setAttribute("list", list);
        request.setAttribute("allProfessionals", allProfessionals);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("pageSize", pageSize);

        // Chuyển hướng đến JSP
        request.getRequestDispatcher("manage_appointment.jsp").forward(request, response);
    }

   
    
}
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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Manage_appointment</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Manage_appointment at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

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
        // Lấy tham số từ form lọc
        String patientName = request.getParameter("patientName");
        String ageSort = request.getParameter("ageSort");
        String doctorName = request.getParameter("doctorName");
        String appointmentDate = request.getParameter("appointmentDate");
        String timeCreatedSort = request.getParameter("timeCreatedSort");
        String status = request.getParameter("status");

        // Lấy danh sách đã lọc
        List<MedicalExamination> list = medicalExaminationDAO.getFilteredExaminations(
                patientName, ageSort, doctorName, appointmentDate, timeCreatedSort, status);

        // Lấy danh sách tất cả bác sĩ để hiển thị trong dropdown
        List<Professional> allProfessionals = medicalExaminationDAO.getAllProfessionals();

        // Đặt dữ liệu vào request
        request.setAttribute("list", list);
        request.setAttribute("allProfessionals", allProfessionals);

        // Chuyển hướng đến JSP
        request.getRequestDispatcher("manage_appointment.jsp").forward(request, response);
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
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
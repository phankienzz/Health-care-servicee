/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package CustomerController;

import dao.CustomerDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Customer;

/**
 *
 * @author Hoang
 */
@WebServlet(name = "patient", urlPatterns = {"/patient"})
public class patient extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet patient</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet patient at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CustomerDAO dao = new CustomerDAO();
        List<Customer> listPatient = dao.getAllCustomer();
        String patientIdStr = request.getParameter("patientID");
        String patientName = request.getParameter("patientName");

        try {
            if (patientIdStr != null && !patientIdStr.isEmpty()) {
                // Tìm kiếm theo ID
                int patientID = Integer.parseInt(patientIdStr);
                Customer customer = dao.getCustomerByID(patientID);
                if (customer != null) {
                    listPatient.add(customer); // Thêm bệnh nhân vào danh sách để hiển thị
                    request.setAttribute("patientID", patientIdStr);
                } else {
                    request.setAttribute("error", "Patient not found with ID: " + patientIdStr);
                }
            } else if (patientName != null && !patientName.isEmpty()) {
                // Tìm kiếm theo tên
                listPatient = dao.getCustomerByName(patientName);

                if (listPatient.isEmpty()) {
                    request.setAttribute("error", "No patients found with name: " + patientName);
                }
                request.setAttribute("patientName", patientName);
            } else {
                // Nếu không có tham số tìm kiếm, trả về danh sách tất cả bệnh nhân
                listPatient = dao.getAllCustomer();
            }
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid Patient ID format.");
        }

        request.setAttribute("listPatient", listPatient);
        request.getRequestDispatcher("patient.jsp").forward(request, response);

    }

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
    }// </editor-fold>

}

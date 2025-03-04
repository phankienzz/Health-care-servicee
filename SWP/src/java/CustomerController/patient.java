/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package CustomerController;

import context.ValidFunction;
import dao.CustomerDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
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
        ValidFunction valid = new ValidFunction();
        CustomerDAO dao = new CustomerDAO();
        String indexPage = request.getParameter("page");
        int page;
        int totalNews = 0;
        int pageSize = 1;

        try {
            page = Integer.parseInt(indexPage);
            if (page <= 0) {
                page = 1;
            }
        } catch (NumberFormatException e) {
            page = 1;
        }

        List<Customer> listPatient = new ArrayList<>();
        String patientIdStr = request.getParameter("patientID");
        String patientName = request.getParameter("patientName");

        try {
            if (patientIdStr != null && !patientIdStr.isEmpty() && patientName != null && !patientName.isEmpty()) {// ID && Name
                int patientID = Integer.parseInt(patientIdStr);
                Customer customer = dao.getCustomerByIdAndName(patientID, patientName);
                if (customer != null) {
                    request.setAttribute("customer", customer);
                    request.setAttribute("patientID", patientIdStr);
                    request.setAttribute("patientName", patientName);
                } else {
                    request.setAttribute("error", "No patient found with ID: " + patientIdStr + " and name: " + patientName);
                }
            } else if (patientIdStr != null && !patientIdStr.isEmpty()) {//  ID

                int patientID = Integer.parseInt(patientIdStr);
                Customer customer = dao.getCustomerByID(patientID);
                if (customer != null) {
                    request.setAttribute("customer", customer);
                    request.setAttribute("patientID", patientIdStr);
                } else {
                    request.setAttribute("error", "Patient not found with ID: " + patientIdStr);
                }
                
            } else if (patientName != null && !patientName.isEmpty()) {// Name
                listPatient = dao.getCustomerByName(valid.normalizeName(patientName));
                if (listPatient.isEmpty()) {
                    request.setAttribute("error", "No patients found with name: " + patientName);
                } else {
                    request.setAttribute("listPatient", listPatient);
                }
                request.setAttribute("patientName", patientName);
            } else {
                listPatient = dao.getAllCustomer(page, pageSize);
                totalNews = 5;
                request.setAttribute("listPatient", listPatient);
            }

            int endPage = (int) Math.ceil((double) totalNews / pageSize);
            request.setAttribute("endPage", endPage);
            request.setAttribute("page", page);

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

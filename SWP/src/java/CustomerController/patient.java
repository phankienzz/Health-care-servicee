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
        String status = request.getParameter("status");

        if (status == null || status.isEmpty()) {
            status = "active";
        }
        int page;
        int totalPatient = 0;
        int pageSize = 10;

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
            if (patientIdStr != null && !patientIdStr.isEmpty() && patientName != null && !patientName.isEmpty()) {
                // ID và Tên
                int patientID = Integer.parseInt(patientIdStr);
                Customer customer = dao.getCustomerByIdAndName(patientID, patientName);
                if (customer != null) {
                    listPatient.add(customer);
                    totalPatient = 1;
                } else {
                    request.setAttribute("error", "No patient found with ID: " + patientIdStr + " and name: " + patientName);
                }
            } else if (patientIdStr != null && !patientIdStr.isEmpty()) {
                //ID
                int patientID = Integer.parseInt(valid.normalizeName(patientIdStr));
                Customer customer = dao.getCustomerByID(patientID);
                if (customer != null) {
                    listPatient.add(customer);
                    totalPatient = 1;
                } else {
                    request.setAttribute("error", "Patient not found with ID: " + patientIdStr);
                }
            } else if (patientName != null && !patientName.isEmpty()) {
                //Name
                listPatient = dao.getCustomerByName(valid.normalizeName(patientName), page, pageSize);
                totalPatient = dao.getCustomerByName(valid.normalizeName(patientName)).size();
                if (listPatient.isEmpty()) {
                    request.setAttribute("error", "No patients found with name: " + patientName);
                }
            } else if (!status.isEmpty()) {
                if (status.equals("active")) {
                    listPatient = dao.getAllCustomerActive();

                } else {
                    listPatient = dao.getAllCustomerInactive();
                }
            } else {
                listPatient = dao.getAllCustomer(page, pageSize);
                totalPatient = dao.getAllCustomer().size();
            }

//            for (Customer cus : listPatient) {
//                cus.setRegistrationDate(valid.formatDateTime(cus.getRegistrationDate(), "dd/MM/yyyy"));
//            }
            int endPage = (int) Math.ceil((double) totalPatient / pageSize);
            request.setAttribute("listPatient", listPatient);
            request.setAttribute("totalPatient", totalPatient);
            request.setAttribute("currentEntries", listPatient.size());
            request.setAttribute("endPage", endPage);
            request.setAttribute("page", page);
            request.setAttribute("status", status);
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid Patient ID format.");
        }

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

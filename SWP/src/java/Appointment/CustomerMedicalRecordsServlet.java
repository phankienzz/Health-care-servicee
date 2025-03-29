package Appointment;

import dao.MedicalExaminationDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Customer;
import model.MedicalExamination;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "CustomerMedicalRecordsServlet", urlPatterns = {"/customer-medical-records"})
public class CustomerMedicalRecordsServlet extends HttpServlet {

 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
        MedicalExaminationDAO medicalExaminationDAO = new MedicalExaminationDAO();
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customerAccount");

        if (customer == null) {
            response.sendRedirect("login.jsp");
            return;
        }

      
        List<MedicalExamination> appointments = medicalExaminationDAO.getAppointmentsByCustomerId(customer.getCustomerID());
        request.setAttribute("appointments", appointments);
        request.getRequestDispatcher("medical_records.jsp").forward(request, response);
    }
}
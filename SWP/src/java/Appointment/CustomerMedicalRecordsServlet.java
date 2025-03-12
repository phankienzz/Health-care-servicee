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

    private MedicalExaminationDAO medicalExaminationDAO = new MedicalExaminationDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy thông tin khách hàng từ session với key "customerAccount"
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customerAccount");

        // Kiểm tra xem khách hàng đã đăng nhập chưa
        if (customer == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Lấy danh sách các cuộc hẹn của khách hàng
        List<MedicalExamination> appointments = medicalExaminationDAO.getAppointmentsByCustomerId(customer.getCustomerID());
        request.setAttribute("appointments", appointments);
        request.getRequestDispatcher("medical_records.jsp").forward(request, response);
    }
}
package Appointment;

import dao.MedicalExaminationDAO;
import dao.ProfessionalDAO;
import dao.ServiceDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import model.Customer;
import model.MedicalExamination;
import model.Professional;
import model.Service;

/**
 *
 * @author ADMIN
 */
public class Appointment extends HttpServlet {

    private ServiceDAO serviceDAO = new ServiceDAO();
    private ProfessionalDAO professionalDAO = new ProfessionalDAO();
    private MedicalExaminationDAO medicalExaminationDAO = new MedicalExaminationDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Service> services = serviceDAO.getAllService();
        List<Professional> doctors = professionalDAO.getAllDoctors();

        request.setAttribute("services", services);
        request.setAttribute("doctors", doctors);

        request.getRequestDispatcher("appointment.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Customer customerProfile = (Customer) session.getAttribute("customerAccount");

        if (customerProfile == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Retrieve form data
        String[] serviceIds = request.getParameterValues("serviceIds[]");
        String doctorId = request.getParameter("doctorId");
        String dateStr = request.getParameter("date"); // Expected format: dd/MM/yyyy
        String timeStr = request.getParameter("time"); // Expected format: HH:mm
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String message = request.getParameter("message");

        // Define formatters
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        try {
            // Combine date and time and parse it
            String examinationDateTimeStr = dateStr + " " + timeStr;
            LocalDateTime examinationDateTime = LocalDateTime.parse(examinationDateTimeStr, inputFormatter);
            
            // Get current date/time
            LocalDateTime now = LocalDateTime.now();
            
            // Validate date - must not be in the past
            if (examinationDateTime.isBefore(now)) {
                request.setAttribute("error", "Appointment date must be in the future. Please select a valid date.");
                // Preserve form data
                preserveFormData(request, serviceIds, doctorId, dateStr, timeStr, name, phone, message);
                // Reload services and doctors
                request.setAttribute("services", serviceDAO.getAllService());
                request.setAttribute("doctors", professionalDAO.getAllDoctors());
                request.getRequestDispatcher("appointment.jsp").forward(request, response);
                return;
            }

            String examinationDate = examinationDateTime.format(outputFormatter);
            String createdAt = LocalDateTime.now().format(outputFormatter);

            // Fetch Professional object
            Professional doctor = professionalDAO.getProfessionalbyID(Integer.parseInt(doctorId));

            // Fetch selected services
            List<Service> selectedServices = new ArrayList<>();
            if (serviceIds != null) {
                for (String serviceId : serviceIds) {
                    Service service = serviceDAO.getServiceById(Integer.parseInt(serviceId));
                    if (service != null) {
                        selectedServices.add(service);
                    }
                }
            }

            // Create MedicalExamination object
            MedicalExamination examination = new MedicalExamination();
            examination.setExaminationID(0);
            examination.setExaminationDate(examinationDate);
            examination.setCustomerId(customerProfile);
            examination.setStatus("Pending");
            examination.setConsultantId(doctor);
            examination.setNote(message);
            examination.setCreatedAt(createdAt);
            examination.setList(selectedServices);

            // Save to database
            boolean success = medicalExaminationDAO.saveMedicalExamination(examination);

            if (success) {
                response.sendRedirect("confirmation.jsp");
            } else {
                request.setAttribute("error", "Failed to book appointment. Please try again.");
                preserveFormData(request, serviceIds, doctorId, dateStr, timeStr, name, phone, message);
                request.setAttribute("services", serviceDAO.getAllService());
                request.setAttribute("doctors", professionalDAO.getAllDoctors());
                request.getRequestDispatcher("appointment.jsp").forward(request, response);
            }

        } catch (DateTimeParseException e) {
            request.setAttribute("error", "Invalid date or time format. Please use correct format (dd/MM/yyyy and HH:mm)");
            preserveFormData(request, serviceIds, doctorId, dateStr, timeStr, name, phone, message);
            request.setAttribute("services", serviceDAO.getAllService());
            request.setAttribute("doctors", professionalDAO.getAllDoctors());
            request.getRequestDispatcher("appointment.jsp").forward(request, response);
        }
    }

    // Helper method to preserve form data when validation fails
    private void preserveFormData(HttpServletRequest request, String[] serviceIds, String doctorId, 
            String dateStr, String timeStr, String name, String phone, String message) {
        request.setAttribute("selectedServiceIds", serviceIds);
        request.setAttribute("selectedDoctorId", doctorId);
        request.setAttribute("date", dateStr);
        request.setAttribute("time", timeStr);
        request.setAttribute("name", name);
        request.setAttribute("phone", phone);
        request.setAttribute("message", message);
    }

    @Override
    public String getServletInfo() {
        return "Handles appointment booking for medical examinations";
    }
}
package CustomerController;

import dao.CustomerDAO;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import model.Customer;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50 // 50MB
)
public class EditProfileServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Part filePart = request.getPart("profileImage");
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String dateOfBirth = request.getParameter("dateOfBirth");
        String gender = request.getParameter("gender");

        try {
            InputStream imageStream = null;
            if (filePart != null && filePart.getSize() > 0) {
                imageStream = filePart.getInputStream();
            }
            HttpSession session = request.getSession();
            Customer customerProfile = (Customer) session.getAttribute("customerAccount");
            if (customerProfile == null) {
                response.sendRedirect("login.jsp");
                return;
            }
            int customerID = customerProfile.getCustomerID();

            CustomerDAO dao = new CustomerDAO();
            dao.updateCustomerProfile(fullName, email, phone, address, dateOfBirth, gender, imageStream, customerID);

            // Cập nhật session
            customerProfile.setFullName(fullName);
            customerProfile.setEmail(email);
            customerProfile.setPhone(phone);
            customerProfile.setAddress(address);
            customerProfile.setDateOfBirth(dateOfBirth);
            customerProfile.setGender(gender);

            session.setAttribute("customerAccount", customerProfile);
            response.sendRedirect("profile.jsp");
        } catch (ParseException ex) {
            Logger.getLogger(EditProfileServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public String getServletInfo() {
        return "Servlet for editing user profile.";
    }
}

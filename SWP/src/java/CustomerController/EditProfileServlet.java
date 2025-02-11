package CustomerController;

import dao.CustomerDAO;
import java.io.IOException;
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
import java.io.File;
import java.nio.file.Paths;
import model.Customer;

@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 2, // 2MB
    maxFileSize = 1024 * 1024 * 10,      // 10MB
    maxRequestSize = 1024 * 1024 * 50    // 50MB
)
public class EditProfileServlet extends HttpServlet {

    private static final String IMAGE_UPLOAD_DIR = "uploads";

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

        HttpSession session = request.getSession();
        Customer customerProfile = (Customer) session.getAttribute("customerAccount");
        if (customerProfile == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        int customerID = customerProfile.getCustomerID();

        // Xử lý upload ảnh
        String imagePath = customerProfile.getProfilePicture();
        String uploadPath = getServletContext().getRealPath("") + File.separator + IMAGE_UPLOAD_DIR;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        if (filePart != null && filePart.getSize() > 0) {
            String fileName = "profile_" + customerID + ".jpg";
            String filePath = uploadPath + File.separator + fileName;
            filePart.write(filePath);
            imagePath = IMAGE_UPLOAD_DIR + "/" + fileName;
        }

        CustomerDAO dao = new CustomerDAO();
        try {
            dao.updateCustomerProfile(fullName, email, phone, address, dateOfBirth, gender, uploadPath, customerID);
        } catch (ParseException ex) {
            Logger.getLogger(EditProfileServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Cập nhật session
        customerProfile.setFullName(fullName);
        customerProfile.setEmail(email);
        customerProfile.setPhone(phone);
        customerProfile.setAddress(address);
        customerProfile.setDateOfBirth(dateOfBirth);
        customerProfile.setGender(gender);
        customerProfile.setProfilePicture(imagePath);

        session.setAttribute("customerAccount", customerProfile);
        response.sendRedirect("profile");
    }

    @Override
    public String getServletInfo() {
        return "Servlet for editing user profile.";
    }
}

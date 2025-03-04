package ProfessionalController;

import context.DBContext;
import dao.ProfessionalDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.xml.bind.DatatypeConverter.parseDate;
import model.Professional;
import util.FileUploadHelper;

/**
 *
 * @author Win11
 */
@MultipartConfig(maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 10)
public class AddProfessionalServlet extends HttpServlet {

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
            out.println("<title>Servlet AddProfessionalServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddProfessionalServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        processRequest(request, response);
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

        // Lấy dữ liệu từ form
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String gender = request.getParameter("gender");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String status = request.getParameter("status");
        String specialization = request.getParameter("specialization");
        String officeHours = request.getParameter("officeHours");
        String qualification = request.getParameter("qualification");
        String biography = request.getParameter("biography");
        String dateOfBirth = request.getParameter("dateOfBirth");
        String hireDate = request.getParameter("hireDate");
//            PrintWriter out  = response.getWriter();
//            out.print(fullName+email+password+gender+address+phone+dateOfBirth+ hireDate);
        Date createdAt = new Date(System.currentTimeMillis());
        boolean success=false;
 ProfessionalDAO dbHelper = new ProfessionalDAO();
        // Xử lý file ảnh
        try {
            Part filePart = request.getPart("profilePicture");
            if (filePart != null && filePart.getSize() > 0) {
                if (!isValidImageFile(extractFileName(filePart))) {
                    request.setAttribute("errorMessage", "Chỉ được tải lên file ảnh có định dạng JPG, JPEG, PNG, GIF, WEBP!");
                request.getRequestDispatcher("edit-doctor.jsp").forward(request, response);
                return;
            }
            
                String imagePath = "uploads/" + FileUploadHelper.saveProfilePicture(filePart); // Lưu file
                Professional newProfessional = new Professional(
                        0, fullName, email, password, Date.valueOf(dateOfBirth), gender, address, phone,
                        Date.valueOf(hireDate), status, imagePath.getBytes(), specialization, officeHours, qualification,
                        biography, createdAt
                );
                newProfessional.setRoleID(3);
                // Gọi DAO để thêm vào database
               
                success=dbHelper.addProfessional(newProfessional);

                // Lấy danh sách cập nhật và lưu vào session
                HttpSession session = request.getSession();
                session.setAttribute("specializations", dbHelper.getallSpecialization());
                session.setAttribute("professionals", dbHelper.getAllProfessionals());
                response.getWriter().write("Upload thành công: " + imagePath);
            } else {
                response.getWriter().write("Vui lòng chọn ảnh!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("Lỗi upload file: " + e.getMessage());
        }

        // Tạo đối tượng Professional
        // Chuyển hướng đến trang quản lý
        List<Professional> list = dbHelper.getAllProfessionals();
        HttpSession session = request.getSession();
        if (success) {
             session.setAttribute("specializations", dbHelper.getallSpecialization());
            session.setAttribute("professionals", list);
            response.sendRedirect("manage-doctor.jsp");
        } else {
            request.setAttribute("errorMessage", "Update failed!");
            request.getRequestDispatcher("add-doctor.jsp").forward(request, response);
        }
//response.sendRedirect("test.jsp");

    }

    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        for (String content : contentDisp.split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf("=") + 2, content.length() - 1);
            }
        }
        return "1.jpg";
    }
// Hàm xử lý file ảnh -> byte[]
     public static boolean isValidImageFile(String fileName) {
        if (fileName == null || !fileName.contains(".")) {
            return false; // Tên file không hợp lệ
        }
        
        String[] allowedExtensions = {".jpg", ".jpeg", ".png", ".gif", ".webp"};
        
        int lastDotIndex = fileName.lastIndexOf(".");
        if (lastDotIndex == -1 || lastDotIndex == fileName.length() - 1) {
            return false; // Không có phần mở rộng hoặc chỉ có dấu "."
        }
        
        String fileExtension = fileName.substring(lastDotIndex).toLowerCase();
        return Arrays.asList(allowedExtensions).contains(fileExtension);
    }

    private byte[] processProfilePicture(Part filePart) throws IOException {
        if (filePart == null || filePart.getSize() <= 0) {
            return null;
        }
        try (InputStream inputStream = filePart.getInputStream()) {
            return inputStream.readAllBytes();
        }
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
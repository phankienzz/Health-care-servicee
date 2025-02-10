/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package CustomerController;

import dao.CustomerDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import model.Customer;

/**
 *
 * @author jaxbo
 */
public class EditProfileServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EditProfileServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditProfileServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        HttpSession session = request.getSession();
//        Customer customerProfile = (Customer) session.getAttribute("customerAccount");
//
//        // Kiểm tra nếu chưa đăng nhập
//        if (customerProfile == null) {
//            response.sendRedirect("login.jsp");
//            return;
//        }
//
//        // Chuyển tiếp tới trang editProfile.jsp để hiển thị thông tin cũ
//        request.setAttribute("customerProfile", customerProfile);
//        request.getRequestDispatcher("edit-profile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
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

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsedDate = sdf.parse(dateOfBirth);
            java.util.Date currentDate = new java.util.Date();

            // Kiểm tra nếu ngày sinh lớn hơn ngày hiện tại
            if (parsedDate.after(currentDate)) {
                request.setAttribute("error", "Date of Birth cannot be in the future!");
                request.getRequestDispatcher("edit-profile.jsp").forward(request, response);
                return;
            }

            if (!phone.matches("^0[0-9]{9}$")) {
                request.setAttribute("error", "Invalid phone number! Must be 10 digits and start with 0.");
                request.getRequestDispatcher("edit-profile.jsp").forward(request, response);
                return;
            }

            // Kiểm tra định dạng email hợp lệ
            if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")) {
                request.setAttribute("error", "Email invalid !! (ex: example@gmail.com).");
                request.getRequestDispatcher("edit-profile.jsp").forward(request, response);
                return;
            }
            
            CustomerDAO dao = new CustomerDAO();
            dao.updateCustomerProfile(fullName, email, phone, address, dateOfBirth, gender, customerID);

            // Cập nhật lại session
            customerProfile.setFullName(fullName);
            customerProfile.setEmail(email);
            customerProfile.setPhone(phone);
            customerProfile.setAddress(address);
            customerProfile.setDateOfBirth(dateOfBirth);
            customerProfile.setGender(gender);

            session.setAttribute("customerAccount", customerProfile);
            response.sendRedirect("profile");

        } catch (Exception e) {
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

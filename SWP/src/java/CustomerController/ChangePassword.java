package CustomerController;

import dao.CustomerDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Customer;

@WebServlet(name = "ChangePassword", urlPatterns = {"/changePassword"})
public class ChangePassword extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
         String oldPassword = request.getParameter("old_password");
        String newPassword = request.getParameter("new_password");
        String confirmPassword = request.getParameter("confirm_new_password");

        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customerAccount");

      
        if (customer == null) {
            request.setAttribute("error", "You must login.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }
        CustomerDAO dao = new CustomerDAO();
        int customerID = customer.getCustomerID();

       
        if (!dao.checkOldPassword(customerID, oldPassword)) {
            request.setAttribute("error", "Old Password is not correct");
            request.getRequestDispatcher("change-password.jsp").forward(request, response);
            return;
        }

       
        if (newPassword == null || newPassword.isEmpty() || !newPassword.equals(confirmPassword)) {
            request.setAttribute("error", "The new password doesn't match or is empty!");
            request.getRequestDispatcher("change-password.jsp").forward(request, response);
            return;
        }

       
        dao.changeCustomerPassword(customerID, newPassword);

        customer.setPassword(newPassword);

        response.sendRedirect("profile.jsp");
    }
    
    
 
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package StaffController;

import dao.RoleDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Role;
import model.Staff;

/**
 *
 * @author Gigabyte
 */
@WebServlet(name="roleStaff", urlPatterns={"/roleStaff"})
public class roleStaff extends HttpServlet {
   
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet roleStaff</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet roleStaff at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        HttpSession session = request.getSession();
        Staff s = (Staff)session.getAttribute("staffAccount");
        RoleDAO rDAO = new RoleDAO();
        Role r = rDAO.getRoleByID(s.getRoleID());
        session.setAttribute("role", r);
        if(s.getRoleID() == 1){
            response.sendRedirect("appointment");
        }
        if(s.getRoleID() == 2){
            response.sendRedirect("invoice");
        }
        if(s.getRoleID() == 3){
            response.sendRedirect("homeblogseverlet");
        }
        if(s.getRoleID() == 4){
            response.sendRedirect("schedule.html");
        }
        if(s.getRoleID() == 5){
            response.sendRedirect("schedule.html");
        }
        if(s.getRoleID() == 6){
            response.sendRedirect("dashboard.jsp");
        }
        if(s.getRoleID() == 7){
            response.sendRedirect("staff");
        }
        if(s.getRoleID() == 8){
            response.sendRedirect("dashboard.jsp");
        }
        
    } 

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }


}

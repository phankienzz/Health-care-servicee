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
import java.util.List;
import model.Permission;
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
        List<Permission> listPer = r.getPermission();
        for(Permission p : listPer){
            if(p.getPermissionID() == 21){
                response.sendRedirect("dashboard.jsp");
                return;
            }
            if(p.getPermissionID() == 22){
                response.sendRedirect("dashboard.jsp");
                return;
            }
            if(p.getPermissionID() == 24){
                response.sendRedirect("staff");
                return;
            }
           
            if(p.getPermissionID() == 28){
                response.sendRedirect("patient");
                return;
            }
            if(p.getPermissionID() == 1){
                response.sendRedirect("manage_appointment");
                return;
            }
            if(p.getPermissionID() == 15){
                response.sendRedirect("loadstaffforschedule");
                return;
            }
            if(p.getPermissionID() == 18){
                response.sendRedirect("loadmange");
                return;
            }
            
            if(p.getPermissionID() == 4){
                response.sendRedirect("invoice");
                return;
            }
            if(p.getPermissionID() == 29){
                response.sendRedirect("dashboard.jsp");
                return;
            }
            if(p.getPermissionID() == 8){
                response.sendRedirect("homeblogseverlet");
                return;
            }
            if(p.getPermissionID() == 27){
                response.sendRedirect("rolePermission");
                return;
            }
        }
        
    } 

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }


}

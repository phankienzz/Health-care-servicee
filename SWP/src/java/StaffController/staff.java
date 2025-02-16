/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package StaffController;

import context.ValidFunction;
import dao.RoleDAO;
import dao.StaffDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import model.Role;
import model.Staff;

/**
 *
 * @author Gigabyte
 */
@WebServlet(name="staff", urlPatterns={"/staff"})
public class staff extends HttpServlet {
   
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        StaffDAO staffDAO = new StaffDAO();
        List<Staff> listStaff = staffDAO.getAllStaff();
        List<Staff> list = new ArrayList<>();
        ValidFunction valid = new ValidFunction();
        for(Staff s : listStaff){
            String dateBirth;
            if(s.getDateOfBirth() != null){
                 dateBirth = valid.formatDate(s.getDateOfBirth());
            }else{
                dateBirth = s.getDateOfBirth();
            }
            Staff r = new Staff(s.getStaffID(),s.getFullName(), s.getEmail(), s.getPassword(), s.getPhone(), s.getGender(),dateBirth, s.getAddress(), valid.formatDate(s.getHireDate()), s.getRoleID(), s.getStatus(),s.getProfilePicture());
            list.add(r);
        }
        RoleDAO roleDAO = new RoleDAO();
        List<Role> listRole = roleDAO.getAllRole();
        request.setAttribute("listStaff", list);
         request.setAttribute("listRole", listRole );
        request.getRequestDispatcher("staff.jsp").forward(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

 

}

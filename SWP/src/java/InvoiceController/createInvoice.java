/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package InvoiceController;

import dao.MedicalExaminationDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import model.MedicalExamination;

/**
 *
 * @author Gigabyte
 */
@WebServlet(name="createInvoice", urlPatterns={"/createInvoice"})
public class createInvoice extends HttpServlet {
   
  


    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        MedicalExaminationDAO medDAO = new MedicalExaminationDAO();
        List<MedicalExamination> listMedicalExam = medDAO.getAllMedicalExamination();
        List<MedicalExamination> list = new ArrayList<>();
        for(MedicalExamination x : listMedicalExam){
            if(x.getStatus().equals("Pending")){
                list.add(x);
            }
        }
        request.setAttribute("listMedicalExam", list);
        request.getRequestDispatcher("create-invoice.jsp").forward(request, response);
    } 

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
    }

 

}

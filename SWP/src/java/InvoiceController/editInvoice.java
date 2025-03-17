/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package InvoiceController;

import context.ValidFunction;
import dao.DiscountDAO;
import dao.InvoiceDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Discount;
import model.Invoice;
import model.Service;

/**
 *
 * @author Gigabyte
 */
@WebServlet(name="editInvoice", urlPatterns={"/editInvoice"})
public class editInvoice extends HttpServlet {
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String invoiceID = request.getParameter("invoiceID");
        InvoiceDAO invDAO = new InvoiceDAO();
        DiscountDAO disDAO = new DiscountDAO();
        List<Discount> listDis = disDAO.getAllDiscount();
        request.setAttribute("listDis", listDis);
        Invoice in = invDAO.getInvoiceByID(Integer.parseInt(invoiceID));
        ValidFunction valid = new ValidFunction();
        in.getExaminationID().getCustomerId().setDateOfBirth(valid.formatDateNews(in.getExaminationID().getCustomerId().getDateOfBirth()));
        request.setAttribute("invoice", in);
        double total = 0;
        for(Service s : in.getExaminationID().getList()){
            total += s.getPrice();
        }
        request.setAttribute("total", total);
        request.setAttribute("discount", total-in.getTotalAmount());
        request.getRequestDispatcher("edit-invoice.jsp").forward(request, response);
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String invoiceID = request.getParameter("invoiceID");
        InvoiceDAO invDAO = new InvoiceDAO();
        Invoice in = invDAO.getInvoiceByID(Integer.parseInt(invoiceID));
        ValidFunction valid = new ValidFunction();
        in.getExaminationID().getCustomerId().setDateOfBirth(valid.formatDateNews(in.getExaminationID().getCustomerId().getDateOfBirth()));
        request.setAttribute("invoice", in);
        double total = 0;
        for(Service s : in.getExaminationID().getList()){
            total += s.getPrice();
        }
        request.setAttribute("total", total);
        request.setAttribute("discount", total-in.getTotalAmount());
        request.getRequestDispatcher("edit-invoice.jsp").forward(request, response);
    }

}

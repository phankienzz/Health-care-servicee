package WorkingScheduleController;

import dao.WorkingScheduleDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import model.WorkingSchedule;

@WebServlet(name = "LoadStaff_ForSchedule", urlPatterns = {"/loadstaffforschedule"})
public class LoadStaff_ForSchedule extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        WorkingScheduleDAO workingDAO = new WorkingScheduleDAO();

        List<WorkingSchedule> professionalList = workingDAO.getProfessionalSchedules_haveName();
        request.setAttribute("professionalList", professionalList);
        
        request.getRequestDispatcher("listDoctor-demo.jsp").forward(request, response);
    } 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet để tải danh sách chuyên gia theo vai trò.";
    }
}

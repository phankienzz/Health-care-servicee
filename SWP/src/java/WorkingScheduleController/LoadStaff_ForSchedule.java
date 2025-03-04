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

@WebServlet(name = "LoadStaff_ForSchedule", urlPatterns = {"/loadstaffforschedule"})
public class LoadStaff_ForSchedule extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        WorkingScheduleDAO workingDAO = new WorkingScheduleDAO();
        List<Integer> roles = new ArrayList<>();
        roles.add(4);
        roles.add(5);

        List<String> professionalList = workingDAO.getProfessionalsByRole(roles);
        request.setAttribute("professionalList", professionalList);
        
        List<String> professional_infor_List = workingDAO.extractProfessionalInfo(professionalList);
        request.setAttribute("professional_infor_List", professional_infor_List);
        
        request.getRequestDispatcher("createWorkingSchedule.jsp").forward(request, response);
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

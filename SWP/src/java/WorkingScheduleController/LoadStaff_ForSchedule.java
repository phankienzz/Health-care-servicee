package WorkingScheduleController;

import context.ValidFunction;
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

        List<WorkingSchedule> professionalList = workingDAO.getListProfessionalSchedules();
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
        WorkingScheduleDAO workingDAO = new WorkingScheduleDAO();
        ValidFunction val = new ValidFunction();

        // Lấy dữ liệu từ request
        String searchName = request.getParameter("searchName");
        String workDate = request.getParameter("workDate");
        String dayFilter = request.getParameter("dayFilter");
        String shiftFilter = request.getParameter("shiftFilter");
        String searchType = request.getParameter("searchType");
        List<WorkingSchedule> professionalList = workingDAO.getAllSchedules();

        switch (searchType) {
            case "name":
                if (searchName != null && !searchName.trim().isEmpty()) {
                    professionalList = workingDAO.searchSchedulesByName(val.normalizeName(searchName));
                }
                break;
            case "date":
                if (workDate != null && !workDate.trim().isEmpty()) {
                    professionalList = workingDAO.getSchedulesByDate(workDate,shiftFilter);
                }
                break;
            case "dayandshift":
                int dayFilterInt = Integer.parseInt(dayFilter);
                professionalList = workingDAO.getSchedulesByShiftAndDay(shiftFilter, dayFilterInt);
                break;
            default:
                throw new AssertionError();
        }

        // Gửi dữ liệu về trang JSP
        request.setAttribute("professionalList", professionalList);
        request.getRequestDispatcher("listDoctor-demo.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet để tải danh sách chuyên gia theo vai trò.";
    }
}

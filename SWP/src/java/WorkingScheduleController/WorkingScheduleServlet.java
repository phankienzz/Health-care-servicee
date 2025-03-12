
import dao.WorkingScheduleDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.WorkingSchedule;

import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/saveSchedule")
public class WorkingScheduleServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        try {
            WorkingScheduleDAO workingDAO = new WorkingScheduleDAO();
            List<WorkingSchedule> schedules = new ArrayList<>();
            int ID = Integer.parseInt(request.getParameter("ID"));

            int[] dayValues = {2, 3, 4, 5, 6, 7, 1};
            String[] shifts = {"MORNING", "AFTERNOON", "EVENING"};

            for (int day : dayValues) {
                for (String shift : shifts) {
                    String startParam = request.getParameter("shift-" + day + "-" + shift + "-start");
                    String endParam = request.getParameter("shift-" + day + "-" + shift + "-end");

                    if (startParam != null && endParam != null && !startParam.isEmpty() && !endParam.isEmpty()) {
                        Time startTime = Time.valueOf(startParam + ":00");
                        Time endTime = Time.valueOf(endParam + ":00");

                        WorkingSchedule schedule = new WorkingSchedule(ID, day, startTime, endTime, shift);

                        // Kiểm tra nếu lịch đã tồn tại
                        if (workingDAO.isScheduleExists(ID, day, shift)) {
                            workingDAO.updateScheduleByKey(schedule); // Cập nhật nếu đã tồn tại
                        } else {
                            workingDAO.addSchedule(schedule); // Thêm mới nếu chưa có
                        }
                    }
                }
            }

            response.sendRedirect("loadstaffforschedule");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        WorkingScheduleDAO workingDAO = new WorkingScheduleDAO();
        String fullName = request.getParameter("fullName");
        String idStr = request.getParameter("professionalID");
        
        int ID = Integer.parseInt(idStr);
        
        request.setAttribute("ID", ID);
        request.setAttribute("fullName", fullName);
        request.getRequestDispatcher("createWorkingSchedule.jsp").forward(request, response);
    }

}

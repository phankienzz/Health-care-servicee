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
            int professionalID = Integer.parseInt(request.getParameter("professionalID"));
            List<WorkingSchedule> schedules = new ArrayList<>();

            // Các ngày trong tuần tương ứng với form JSP
            int[] dayValues = {2, 3, 4, 5, 6, 7, 1};
            String[] shifts = {"MORNING", "AFTERNOON", "EVENING"};

            for (int day : dayValues) {
                for (String shift : shifts) {
                    String startParam = request.getParameter("shift-" + day + "-" + shift + "-start");
                    String endParam = request.getParameter("shift-" + day + "-" + shift + "-end");
                    
                    if (startParam != null && endParam != null && !startParam.isEmpty() && !endParam.isEmpty()) {
                        Time startTime = Time.valueOf(startParam + ":00");
                        Time endTime = Time.valueOf(endParam + ":00");
                        
                        WorkingSchedule schedule = new WorkingSchedule(professionalID, day, startTime, endTime, shift);
                        schedules.add(schedule);
                    }
                }
            }

            // Lưu vào database
            WorkingScheduleDAO scheduleDAO = new WorkingScheduleDAO();
            for (WorkingSchedule schedule : schedules) {
                scheduleDAO.addWorkingSchedule(schedule);
            }

            response.sendRedirect("schedule_success.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}

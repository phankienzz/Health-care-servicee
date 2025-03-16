package WorkingScheduleController;

import dao.WorkingScheduleDAO;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ProfessionalLeave;
import model.WorkingSchedule;
import java.sql.Date;
import java.sql.Time;

@WebServlet(name = "ViewPersonalSchedule", urlPatterns = {"/viewpersonalschedule"})
public class ViewPersonalSchedule extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        WorkingScheduleDAO workingDAO = new WorkingScheduleDAO();

        try {
            int professionalID = Integer.parseInt(request.getParameter("professionalID"));
            String selectedDateStr = request.getParameter("selectedDate");

            LocalDate selectedDate = (selectedDateStr != null && !selectedDateStr.isEmpty())
                    ? LocalDate.parse(selectedDateStr)
                    : LocalDate.now();

            LocalDate firstMondayOfWeek = selectedDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
            LocalDate lastSundayOfWeek = firstMondayOfWeek.plusDays(6);

            List<WorkingSchedule> professionalList = workingDAO.getAllSchedulesByProfessionalID(professionalID);
            List<ProfessionalLeave> leaveList = workingDAO.getProfessionalLeavesByProfessionalID(professionalID);

            List<Date> leaveDates = new ArrayList<>();
            for (ProfessionalLeave leave : leaveList) {
                if ("Approved".equals(leave.getStatus())) {
                    leaveDates.add((Date) leave.getLeaveDate());
                }
            }

            List<WorkingSchedule> updatedSchedule = new ArrayList<>();

            for (WorkingSchedule schedule : professionalList) {
                LocalDate scheduleDate = firstMondayOfWeek.plusDays(schedule.getDayOfWeek() - 2);
                if (leaveDates.contains(Date.valueOf(scheduleDate))) {
                    schedule.setStartTime(Time.valueOf("00:00:00"));
                    schedule.setEndTime(Time.valueOf("00:00:00"));
                    for (ProfessionalLeave leave : leaveList) {
                        if (leave.getLeaveDate().equals(Date.valueOf(scheduleDate))) {
                            schedule.setStatus(leave.getReason());
                            break;
                        }
                    }
                }
                updatedSchedule.add(schedule);
            }

            request.setAttribute("leaveList", leaveList);
            request.setAttribute("professionalList", updatedSchedule);
            request.setAttribute("firstMondayOfWeek", firstMondayOfWeek);
            request.setAttribute("selectedDate", selectedDate);

            request.getRequestDispatcher("viewPersonalSchedule.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Handle POST if needed
    }
}

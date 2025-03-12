package WorkingScheduleController;

import context.ValidFunction;
import dao.WorkingScheduleDAO;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.WorkingSchedule;

@WebServlet(name = "LoadStaff_ForSchedule", urlPatterns = {"/loadstaffforschedule"})
public class LoadStaff_ForSchedule extends HttpServlet {

    private static final int DOCTORS_PER_PAGE = 2; // Mỗi trang 2 bác sĩ

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        WorkingScheduleDAO workingDAO = new WorkingScheduleDAO();

        List<WorkingSchedule> professionalList = workingDAO.getListProfessionalSchedules();
        paginateAndForward(request, response, professionalList);
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

        // Nếu searchType null, gán giá trị mặc định
        if (searchType == null) {
            searchType = "";
        }
        if (dayFilter == null) {
            dayFilter = null;
        }

        List<WorkingSchedule> professionalList;

        switch (searchType) {
            case "name":
                professionalList = (searchName != null && !searchName.trim().isEmpty())
                        ? workingDAO.searchSchedulesByName(val.normalizeName(searchName))
                        : workingDAO.getAllSchedules();
                break;
            case "date":
                professionalList = (workDate != null && !workDate.trim().isEmpty())
                        ? workingDAO.getSchedulesByDate(workDate, shiftFilter)
                        : workingDAO.getAllSchedules();
                break;
            case "dayandshift":
                professionalList = (dayFilter != null && shiftFilter != null)
                        ? workingDAO.getSchedulesByShiftAndDay(shiftFilter, Integer.parseInt(dayFilter))
                        : workingDAO.getAllSchedules();
                break;
            default:
                // Nếu không có tìm kiếm, lấy tất cả lịch làm việc
                professionalList = workingDAO.getListProfessionalSchedules();
                break;
        }

        paginateAndForward(request, response, professionalList);
    }

    private void paginateAndForward(HttpServletRequest request, HttpServletResponse response, List<WorkingSchedule> fullList)
            throws ServletException, IOException {
        // Lấy danh sách các professionalID duy nhất và sắp xếp tăng dần
        List<Integer> doctorIDs = fullList.stream()
                .map(WorkingSchedule::getProfessionalID)
                .distinct()
                .sorted()
                .collect(Collectors.toList());

        int totalDoctors = doctorIDs.size();
        int totalPages = (int) Math.ceil((double) totalDoctors / DOCTORS_PER_PAGE);

        // Tránh lỗi khi không có dữ liệu
        if (totalPages == 0) {
            request.setAttribute("professionalList", List.of());
            request.setAttribute("totalPages", 1);
            request.setAttribute("currentPage", 1);
            request.getRequestDispatcher("listDoctor-demo.jsp").forward(request, response);
            return;
        }

        // Xác định trang hiện tại
        int currentPage = 1;
        String pageParam = request.getParameter("page");
        if (pageParam != null && pageParam.matches("\\d+")) {
            currentPage = Integer.parseInt(pageParam);
        }

        // Đảm bảo currentPage trong phạm vi hợp lệ
        if (currentPage < 1) {
            currentPage = 1;
        } else if (currentPage > totalPages) {
            currentPage = totalPages;
        }

        // Xác định chỉ mục bác sĩ trên trang hiện tại
        int startIndex = (currentPage - 1) * DOCTORS_PER_PAGE;
        int endIndex = Math.min(startIndex + DOCTORS_PER_PAGE, totalDoctors);

        // Lấy danh sách professionalID của trang hiện tại
        List<Integer> pageDoctorIDs = doctorIDs.subList(startIndex, endIndex);

        // Lọc và sắp xếp danh sách lịch làm việc
        List<WorkingSchedule> paginatedList = fullList.stream()
                .filter(schedule -> pageDoctorIDs.contains(schedule.getProfessionalID()))
                .sorted(Comparator.comparing(WorkingSchedule::getProfessionalID))
                .collect(Collectors.toList());

        // Gửi dữ liệu về JSP
        request.setAttribute("professionalList", paginatedList);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("currentPage", currentPage);
        request.getRequestDispatcher("listDoctor-demo.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet để tải danh sách chuyên gia theo lịch làm việc, có phân trang và sắp xếp theo ID tăng dần.";
    }
}

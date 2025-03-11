package serviceController;

import dao.ServiceDAO;
import model.Service;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Load_Manage", urlPatterns = {"/loadmanage"})
public class Load_Manage extends HttpServlet {

    // Thiết lập số dịch vụ mỗi trang
    private static final int PAGE_SIZE = 6;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        ServiceDAO serviceDao = new ServiceDAO();
        List<Service> serviceList = new ArrayList<>();
        int pageNumber = 1;
        String action = request.getParameter("action");

        // Xử lý phân trang
        if (request.getParameter("pageNumber") != null) {
            pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
        }

        // Kiểm tra session có danh sách lọc không
        List<Service> filteredServices = (List<Service>) request.getSession().getAttribute("filteredServices");

        if ("search".equalsIgnoreCase(action)) {
            String searchKeyword = request.getParameter("searchKeyword");
            if (searchKeyword != null && !searchKeyword.trim().isEmpty()) {
                serviceList = serviceDao.getAllServicesWithSearch(searchKeyword.trim());
            } else {
                serviceList = serviceDao.getAllService();
            }
            request.getSession().setAttribute("filteredServices", serviceList);
        } else if ("filter".equalsIgnoreCase(action)) {
            String sortBy = request.getParameter("sortBy");
            if (sortBy != null && !sortBy.isEmpty()) {
                serviceList = serviceDao.getAllServicesWithFilter(sortBy);
            } else {
                serviceList = serviceDao.getAllService();
            }
            request.getSession().setAttribute("filteredServices", serviceList);
        } else {
            if (filteredServices != null) {
                serviceList = filteredServices;
            } else {
                serviceList = serviceDao.getAllService();
            }
        }

        // Tính số lượng trang
        int totalServices = serviceList.size();
        int totalPages = (int) Math.ceil((double) totalServices / PAGE_SIZE);
        int startIndex = (pageNumber - 1) * PAGE_SIZE;
        int endIndex = Math.min(startIndex + PAGE_SIZE, totalServices);

        // Lấy danh sách dịch vụ cho trang hiện tại
        List<Service> pagedServices = serviceList.subList(startIndex, endIndex);

        // Đặt các thuộc tính cho JSP
        request.setAttribute("serviceList", pagedServices);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("pageNumber", pageNumber);
        request.setAttribute("searchKeyword", request.getParameter("searchKeyword"));
        request.setAttribute("sortBy", request.getParameter("sortBy"));

        // Chuyển đến JSP
        request.getRequestDispatcher("manage_Service.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Thực hiện xong thao tác (add, update, etc.) và yêu cầu tải lại trang.
        // Giả sử thao tác add hoặc update service đã xong tại đây

        // Thực hiện redirect về trang loadmanage để tải lại dữ liệu mới
        response.sendRedirect("loadmanage");
    }

    @Override
    public String getServletInfo() {
        return "Servlet for managing services with pagination and filter.";
    }
}

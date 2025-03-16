package blog;

import dao.ServiceDAO;
import model.Service;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/manageIntroduce")
public class ManageIntroduceServlet extends HttpServlet {
    private ServiceDAO serviceDAO;

    @Override
    public void init() throws ServletException {
        serviceDAO = new ServiceDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy danh sách tất cả dịch vụ
        List<Service> serviceList = serviceDAO.getAllService();
        request.setAttribute("serviceList", serviceList);

        // Kiểm tra nếu đang ở chế độ chỉnh sửa
        String edit = request.getParameter("edit");
        if ("true".equals(edit)) {
            int packageID = Integer.parseInt(request.getParameter("packageID"));
            Service selectedService = serviceDAO.getServiceById(packageID);
            request.setAttribute("selectedService", selectedService);
            request.setAttribute("editMode", true);
        }

        // Chuyển tiếp đến JSP
        request.getRequestDispatcher("manage-introduce.jsp").forward(request, response);
    }
    
    
}
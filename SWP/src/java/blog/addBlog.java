package blog;

import dao.NewsDAO;
import java.io.IOException;
import java.io.InputStream;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet(name = "addBlog", urlPatterns = {"/addblog"})
@MultipartConfig(maxFileSize = 50 * 1024 * 1024) // Giới hạn ảnh tối đa 50MB
public class addBlog extends HttpServlet {


   @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    // Lấy dữ liệu từ form
    String title = request.getParameter("name");
    String description = request.getParameter("description");
    String detail = request.getParameter("descriptiondetail");
    int status = "active".equalsIgnoreCase(request.getParameter("status")) ? 1 : 2;

    // Giả định người dùng ID = 1
    int createdBy = 1;

    // Lấy category từ form (trong form phải có name="category")
    String categoryIdStr = request.getParameter("categoryId");
            if (categoryIdStr == null || categoryIdStr.trim().isEmpty()) {
                response.sendRedirect("add-blog.jsp?error=Category+ID+is+required");
                return;
            }
            int categoryId = Integer.parseInt(categoryIdStr);

    // Lấy file ảnh từ form
    Part filePart = request.getPart("image");
    InputStream imageStream = null;

    if (filePart != null && filePart.getSize() > 0) {
        String contentType = filePart.getContentType();
        if (!contentType.equals("image/png") && !contentType.equals("image/jpeg") &&
            !contentType.equals("image/gif") && !contentType.equals("image/jpg")) {
            response.sendRedirect("edit-blog.jsp?error=Only+PNG,+JPEG,+JPG,+and+GIF+files+are+allowed");
            return;
        }
        imageStream = filePart.getInputStream();
    }

    // Gọi DAO để thêm blog vào database
    NewsDAO blogDAO = new NewsDAO();
    blogDAO.addBlogPost(title, description, createdBy, categoryId, status, detail, imageStream);

    // Chuyển hướng sau khi thêm blog thành công
    response.sendRedirect("homeblogseverlet?success=Blog+added");
}
}
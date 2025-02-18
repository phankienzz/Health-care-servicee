package blog;

import dao.BlogDAO;
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
        boolean status = "true".equals(request.getParameter("status"));

        // Giả định người dùng ID = 1, category ID = 1 (có thể thay đổi)
        int createdBy = 1;
        int categoryId = 1;

        // Lấy file ảnh từ form
        Part filePart = request.getPart("image");
        InputStream imageStream = null;

        if (filePart != null && filePart.getSize() > 0) {
                String contentType = filePart.getContentType();
                // Kiểm tra loại file cho phép: PNG, JPEG, GIF
                if (!contentType.equals("image/png") && !contentType.equals("image/jpeg") && !contentType.equals("image/gif")) {
                    response.sendRedirect("edit-blog.jsp?error=Only+PNG,+JPEG,+and+GIF+files+are+allowed");
                    return;
                }
                imageStream = filePart.getInputStream();
            }

        // Gọi DAO để thêm blog vào database
        BlogDAO blogDAO = new BlogDAO();
        blogDAO.addBlogPost(title, description, createdBy, categoryId, status, detail, imageStream);

        // Chuyển hướng sau khi thêm blog thành công
        response.sendRedirect("homeblogseverlet");
    }
}

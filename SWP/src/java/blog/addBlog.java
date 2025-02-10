package blog;

import dao.BlogDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.nio.file.Paths;
import java.util.Collection;

/**
 * Servlet implementation for adding blog posts.
 */
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 2, // 2MB
    maxFileSize = 1024 * 1024 * 50,      // 50MB
    maxRequestSize = 1024 * 1024 * 100   // 100MB
)
public class addBlog extends HttpServlet {

    private static final String IMAGE_UPLOAD_DIR = "uploads"; // Directory for uploaded images

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Lấy thông tin từ form
        String title = request.getParameter("name");
        String content = request.getParameter("description");
        int createdBy = 2; // Thay thế bằng logic lấy user thực tế
        int categoryId = 1; // Xác định dựa trên logic ứng dụng
        String statusParam = request.getParameter("status");
        String detail = request.getParameter("descriptiondetail");

        // Chuyển đổi status thành boolean (nếu database dùng BIT)
        boolean status = "Published".equalsIgnoreCase(statusParam) || "true".equalsIgnoreCase(statusParam);

        // Validate required fields
        if (title == null || title.isEmpty() || content == null || content.isEmpty()) {
            request.setAttribute("errorMessage", "Title and content are required.");
            request.getRequestDispatcher("add-blog.jsp").forward(request, response);
            return;
        }

        // Xử lý upload ảnh
        String uploadPath = getServletContext().getRealPath("") + File.separator + IMAGE_UPLOAD_DIR;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        StringBuilder imagePaths = new StringBuilder();
        Collection<Part> fileParts = request.getParts();
        for (Part filePart : fileParts) {
            if (filePart.getName().equals("images") && filePart.getSize() > 0) {
                String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                String filePath = uploadPath + File.separator + fileName;
                filePart.write(filePath);

                // Lưu đường dẫn ảnh vào database (nếu nhiều ảnh, dùng dấu phẩy để ngăn cách)
                if (imagePaths.length() > 0) {
                    imagePaths.append(",");
                }
                imagePaths.append(IMAGE_UPLOAD_DIR).append("/").append(fileName);
            }
        }

        // Gọi DAO để lưu bài viết vào DB
        BlogDAO blogPostDAO = new BlogDAO();
        blogPostDAO.addBlogPost(title, content, createdBy, categoryId, status, detail, imagePaths.toString());

        // Redirect sau khi đăng bài
        response.sendRedirect("blog.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Servlet for adding blog posts.";
    }
}
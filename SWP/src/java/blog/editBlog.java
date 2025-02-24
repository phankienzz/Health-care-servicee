package blog;

import dao.NewsDAO;
import model.News;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.InputStream;

@WebServlet(name = "editBlog", urlPatterns = {"/editblog"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50 // 50MB
)
public class editBlog extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String postIdStr = request.getParameter("postId");
        if (postIdStr == null || postIdStr.trim().isEmpty()) {
            response.sendRedirect("edit-blog.jsp?error=Missing+post+ID");
            return;
        }

        try {
            int postId = Integer.parseInt(postIdStr);
            NewsDAO dao = new NewsDAO();
            News blog = dao.getBlogById(postId);

            if (blog == null) {
                response.sendRedirect("edit-blog.jsp?error=Blog+not+found");
                return;
            }

            request.setAttribute("blog", blog);
            request.getRequestDispatcher("edit-blog.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            response.sendRedirect("edit-blog.jsp?error=Invalid+post+ID");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String postIdStr = request.getParameter("postId");
        if (postIdStr == null || postIdStr.trim().isEmpty()) {
            response.sendRedirect("edit-blog.jsp?error=Missing+post+ID");
            return;
        }

        try {
            int postId = Integer.parseInt(postIdStr);
            String title = request.getParameter("title");
            String description = request.getParameter("description").trim();
            String detail = request.getParameter("detail").trim();
            boolean status = "active".equalsIgnoreCase(request.getParameter("status"));

            Part filePart = request.getPart("image");
            InputStream imageStream = null;

            // Kiểm tra nếu có file ảnh được tải lên và xác nhận định dạng ảnh
            if (filePart != null && filePart.getSize() > 0) {
                String contentType = filePart.getContentType();
                // Kiểm tra loại file cho phép: PNG, JPEG, GIF
                if (!contentType.equals("image/png") && !contentType.equals("image/jpeg")
                        && !contentType.equals("image/gif") && !contentType.equals("image/jpg")) {
                    response.sendRedirect("edit-blog.jsp?error=Only+PNG,+JPEG,+JPG,+and+GIF+files+are+allowed");
                    return;
                }

                imageStream = filePart.getInputStream();
            }

            // Cập nhật blog vào cơ sở dữ liệu
            NewsDAO dao = new NewsDAO();
            boolean success = dao.updateBlogPost(postId, title, description, status, imageStream, detail);

            if (success) {
                response.sendRedirect("homeblogseverlet?success=Blog+updated");
            } else {
                response.sendRedirect("homeblogseverlet?error=Update+failed");
            }

        } catch (NumberFormatException e) {
            response.sendRedirect("edit-blog.jsp?error=Invalid+post+ID");
        }
    }
}

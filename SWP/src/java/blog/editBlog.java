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
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "editBlog", urlPatterns = {"/editblog"})
@MultipartConfig(
        fileSizeThreshold = 2 * 1024 * 1024, // 2MB
        maxFileSize = 10 * 1024 * 1024, // 10MB
        maxRequestSize = 50 * 1024 * 1024 // 50MB
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
            String description = request.getParameter("description");
            String detail = request.getParameter("detail");
            int status = "1".equals(request.getParameter("status")) ? 1 : 2;

            if (title == null || title.trim().isEmpty()
                    || description == null || description.trim().isEmpty()
                    || detail == null || detail.trim().isEmpty()) {
                response.sendRedirect("edit-blog.jsp?error=Title,+description,+and+detail+cannot+be+empty");
                return;
            }

            title = title.trim();
            description = description.trim();
            detail = detail.trim();

            Part filePart = request.getPart("image");
            InputStream imageStream = null;

            if (filePart != null && filePart.getSize() > 0) {
                String contentType = filePart.getContentType();
                // Kiểm tra loại file cho phép: PNG, JPEG, GIF, JPG
                if (!contentType.equals("image/png") && !contentType.equals("image/jpeg")
                        && !contentType.equals("image/gif") && !contentType.equals("image/jpg")) {
                    response.sendRedirect("edit-blog.jsp?error=Only+PNG,+JPEG,+JPG,+and+GIF+files+are+allowed");
                    return;
                }
                imageStream = filePart.getInputStream();
            }

            NewsDAO dao = new NewsDAO();
            boolean success = dao.updateBlogPost(postId, title, description, status, imageStream, detail);

            if (success) {
                response.sendRedirect("homeblogseverlet?success=Blog+updated");
            } else {
                response.sendRedirect("edit-blog.jsp?error=Update+failed");
            }

        } catch (NumberFormatException e) {
            response.sendRedirect("edit-blog.jsp?error=Invalid+post+ID");
        }
    }
}

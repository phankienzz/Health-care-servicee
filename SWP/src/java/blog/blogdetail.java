package blog;

import dao.BlogDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Blog;

@WebServlet(name = "blogdetail", urlPatterns = {"/blogdetail"})
public class blogdetail extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("postId");

        if (id != null) {
            BlogDAO dao = new BlogDAO();
            Blog blog = dao.getBlogbyid(id);

            if (blog != null) {
                request.setAttribute("blogdetail", blog);
                request.getRequestDispatcher("blog-details.jsp").forward(request, response);
            } else {
                response.sendRedirect("error.jsp"); // Chuyển hướng nếu không tìm thấy bài viết
            }
        } else {
            response.sendRedirect("homeblogseverlet"); // Nếu thiếu postId, quay về danh sách blog
        }
    }

    @Override
    public String getServletInfo() {
        return "Blog Detail Servlet";
    }
}

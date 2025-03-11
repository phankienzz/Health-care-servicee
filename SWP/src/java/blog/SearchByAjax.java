/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package blog;

import dao.NewsDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.News;

/**
 *
 * @author ADMIN
 */
@WebServlet(name="SearchByAjax", urlPatterns={"/SearchByAjax"})
public class SearchByAjax extends HttpServlet {
   
 



   @Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    request.setCharacterEncoding("UTF-8");
    response.setContentType("text/html;charset=UTF-8");

    String keyword = request.getParameter("keyword");
    if (keyword == null || keyword.trim().isEmpty()) {
        response.getWriter().write("");
        return;
    }

    NewsDAO dao = new NewsDAO();
    List<News> list = dao.searchBlogs(keyword);

    PrintWriter out = response.getWriter();
    for (News o : list) {
        out.println("<div class=\"blog-item col-12 col-md-6 col-lg-4\">");
        out.println("    <div class=\"card h-100 shadow-sm\">");
        out.println("        <img class=\"card-img-top img-fluid\" src=\"" + o.getImage() + "\" alt=\"" + o.getTitle() + "\">");
        out.println("        <div class=\"card-body d-flex flex-column\">");
        out.println("            <h4 class=\"card-title text-truncate\"><a href=\"blogdetail?postId=" + o.getPost_id() + "\" class=\"text-decoration-none text-dark\" title=\"View Blog\">" + o.getTitle() + "</a></h4>");
        out.println("            <p class=\"card-text text-muted\">" + o.getContent() + "</p>");
        out.println("            <div class=\"mt-auto\">");
        out.println("                <a href=\"blogdetail?postId=" + o.getPost_id() + "\" class=\"btn btn-primary w-100\">Read More</a>");
        out.println("            </div>");
        out.println("        </div>");
        // Thêm nút Update vào mỗi blog
        out.println("        <div class=\"card-footer\">");
        out.println("            <a href=\"editblog?postId=" + o.getPost_id() + "\" class=\"btn btn-warning w-100\">Update</a>");
        out.println("        </div>");
        out.println("    </div>");
        out.println("</div>");
    }
}
}

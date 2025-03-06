<%-- 
    Document   : comment-reply
    Created on : Mar 5, 2025, 1:13:14 AM
    Author     : Hoang
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<c:forEach var="reply" items="${comments}">
    <c:if test="${reply.parent_comment_id == param.parentId}">
        <li class="mb-3">
            <div class="comment-area-box">
                <div class="comment-thumb float-left">
                    <img style="width: 50px; height: 50px; border-radius: 50%;" alt="" src="pictureprofile?customerID=${reply.customerID.customerID}" class="img-fluid">
                </div>
                <div class="comment-info">
                    <h5 class="mb-1">${reply.customerID.fullName}</h5>
                    <span>${reply.create_at}</span>
                </div>
                <div class="comment-meta mt-2">
                    <c:if test="${sessionScope.customerAccount == null}">
                        <a href="login.jsp?newsID=${newsDetail.post_id}&parent_comment_id=${comment.comment_id}#comment-form"
                           onclick="return confirm('Bạn cần đăng nhập để trả lời bình luận!');">
                            <i class="icofont-reply mr-2 text-muted"></i>Reply
                        </a>
                    </c:if>
                    <c:if test="${sessionScope.customerAccount != null}">
                        <a href="?newsID=${newsDetail.post_id}&parent_comment_id=${comment.comment_id}#comment-form">
                            <i class="icofont-reply mr-2 text-muted"></i>Reply
                        </a>
                    </c:if>
                </div>
                <div class="comment-content mt-3">
                    <p>
                        <strong>Reply to ${reply.customerID.fullName}:</strong>
                        ${reply.content}
                    </p>
                </div>
            </div>

            <%-- Gọi đệ quy tiếp tục hiển thị bình luận con của bình luận con --%>
            <ul class="child-comments list-unstyled ml-5">
                <jsp:include page="comment-reply.jsp">
                    <jsp:param name="parentId" value="${reply.comment_id}" />
                </jsp:include>
            </ul>
        </li>
    </c:if>
</c:forEach>


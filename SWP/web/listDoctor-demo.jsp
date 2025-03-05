<%-- 
    Document   : listDoctor-demo
    Created on : Mar 5, 2025, 8:31:30 AM
    Author     : laptop 368
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Tạo Lịch Làm Việc</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    </head>
    <body>
        <div class="container mt-5">
            <h2 class="text-center">Tạo Lịch Làm Việc Cho Bác Sĩ</h2>
            <form id="scheduleForm" action="saveSchedule" method="GET">
                <div class="mb-3">
                    <label class="form-label">Chọn bác sĩ:</label>
                    <select name="professionalID" class="form-select" required>
                        <c:forEach var="infor" items="${professional_infor_List}">
                            <option value="${infor}">${infor}</option>
                        </c:forEach>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary">Select</button>
            </form>
        </div>
    </body>
</html>


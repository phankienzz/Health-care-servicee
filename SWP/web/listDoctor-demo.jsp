<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Danh Sách Lịch Làm Việc</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
        <style>
            body {
                background-color: #f0f8ff;
                font-family: Arial, sans-serif;
            }
            .container {
                max-width: 900px;
                background: #ffffff;
                padding: 20px;
                border-radius: 10px;
                box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            }
            h2 {
                color: #007bff;
                text-align: center;
                margin-bottom: 20px;
            }
            .table {
                margin-top: 20px;
                border: 1px solid #ddd;
            }
            .table th {
                background-color: #007bff;
                color: white;
                text-align: center;
            }
            .table tbody tr:nth-child(odd) {
                background-color: #e3f2fd;
            }
            .doctor-header {
                background-color: #0056b3;
                color: white;
                font-size: 18px;
                font-weight: bold;
                padding: 10px;
                margin-top: 20px;
                border-radius: 5px;
                display: flex;
                justify-content: space-between;
                align-items: center;
                padding: 10px 15px;
            }
            .manage-btn {
                background-color: #28a745;
                color: white;
                border: none;
                padding: 5px 10px;
                border-radius: 5px;
                cursor: pointer;
                font-size: 14px;
            }
            .manage-btn:hover {
                background-color: #218838;
            }
        </style>
    </head>
    <body>
        <div class="container mt-5">
            <h2>Danh Sách Lịch Làm Việc</h2>

            <c:set var="prevID" value="-1" />
            <c:forEach var="schedule" items="${professionalList}">
                <c:if test="${schedule.professionalID ne prevID}">
                    <c:if test="${prevID ne -1}">
                    </tbody>
                </table>
            </c:if>

            <div class="doctor-header">
                <span>Bác sĩ: ${schedule.fullName} (ID: ${schedule.professionalID})</span>
                <form action="saveSchedule" method="GET">
                    <input type="hidden" name="professionalID" value="${schedule.professionalID}">
                    <button type="submit" class="manage-btn">Manage Lịch</button>
                </form>
            </div>
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>Ngày</th>
                        <th>Ca</th>
                        <th>Giờ bắt đầu</th>
                        <th>Giờ kết thúc</th>
                    </tr>
                </thead>
                <tbody>
                </c:if>

                <tr>
                    <td>${schedule.dayName}</td>
                    <td>${schedule.shift}</td>
                    <td>${schedule.startTime}</td>
                    <td>${schedule.endTime}</td>
                </tr>

                <c:set var="prevID" value="${schedule.professionalID}" />
            </c:forEach>

            <c:if test="${not empty professionalList}">
            </tbody>
        </table>
    </c:if>
</div>
</body>
</html>

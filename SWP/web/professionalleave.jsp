<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>Professional Leave List</title>
        <link rel="stylesheet" type="text/css" href="style.css">
    </head>
    <body>
        <h1>Professional Leave List</h1>
        <table border="1">
            <tr>
                <th>Leave ID</th>
                <th>Professional ID</th>
                <th>Leave Date</th>
                <th>Reason</th>
                <th>Status</th>
            </tr>
            <c:choose>
                <c:when test="${not empty leaveList}">
                    <c:forEach var="leave" items="${leaveList}">
                        <tr>
                            <td>${leave.leaveID}</td>
                            <td>${leave.professionalID}</td>
                            <td>${leave.leaveDate}</td>
                            <td>${leave.reason}</td>
                            <td>${leave.status}</td>
                        </tr>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <tr>
                        <td colspan="6">No records found</td>
                    </tr>
                </c:otherwise>
            </c:choose>
        </table>
    </body>
</html>

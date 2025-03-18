<%@ page import="java.time.LocalDate, java.time.temporal.TemporalAdjusters, java.time.DayOfWeek" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <title>Doctor's Weekly Schedule</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #e6f7ff;
                margin: 0;
                padding: 20px;
            }
            h1 {
                color: #005580;
                text-align: center;
            }
            table {
                width: 100%;
                border-collapse: collapse;
                margin-bottom: 20px;
            }
            th, td {
                border: 1px solid #99ccff;
                text-align: center;
                padding: 10px;
            }
            th {
                background-color: #b3e0ff;
                color: #005580;
            }
            .shift {
                height: 50px;
            }
            .on-leave {
                background-color: #ffcccc;
            }
            #leave-section {
                display: flex;
                justify-content: center;
                align-items: flex-start;
                gap: 20px;
            }
            #leaveForm {
                background-color: #d9f2ff;
                padding: 20px;
                border: 1px solid #99ccff;
                border-radius: 10px;
                width: 45%;
            }
            #leaveTable {
                width: 50%;
                background-color: #ffffff;
                box-shadow: 0 2px 5px rgba(0,0,0,0.1);
            }
            #leaveTable th {
                background-color: #b3e0ff;
                color: #005580;
                padding: 12px;
                font-weight: bold;
            }
            #leaveTable td {
                padding: 8px;
                vertical-align: middle;
            }
            #leaveTable th:nth-child(3), #leaveTable td:nth-child(3) { /* Cột Leave Date */
                width: 150px; /* Tăng chiều rộng cho cột Leave Date */
                min-width: 150px; /* Đảm bảo không bị co lại */
            }
            #leaveTable th:nth-child(4), #leaveTable td:nth-child(4) { /* Cột Reason */
                width: 250px; /* Tăng chiều rộng cho cột Reason */
                min-width: 250px; /* Đảm bảo không bị co lại */
                text-align: left; /* Căn trái nội dung Reason */
                word-wrap: break-word; /* Cho phép xuống dòng */
            }
            #leaveTable select {
                padding: 5px;
                border: 1px solid #99ccff;
                border-radius: 4px;
                background-color: #f0faff;
                width: 100px;
            }
            input, button {
                margin: 5px 0;
                padding: 8px 12px;
                border: 1px solid #99ccff;
                border-radius: 5px;
                width: 100%;
            }
            button {
                background-color: #007acc;
                color: white;
                cursor: pointer;
            }
            button:hover {
                background-color: #005580;
            }
            #leaveTable button {
                width: 80px;
                padding: 6px;
                font-size: 14px;
            }
            #dateForm {
                display: flex;
                align-items: center;
                justify-content: flex-start;
                gap: 10px;
                margin-bottom: 20px;
            }
            #dateForm input[type="date"] {
                padding: 5px 10px;
                width: 150px;
                font-size: 14px;
            }
            #dateForm button {
                padding: 5px 15px;
                font-size: 14px;
                background-color: #007acc;
                color: white;
                border: none;
                border-radius: 5px;
                width: auto;
                cursor: pointer;
            }
            #dateForm button:hover {
                background-color: #005580;
            }
            .filter-section {
                display: flex;
                align-items: center;
                justify-content: flex-end;
                gap: 10px;
                margin-bottom: 15px;
            }
            .filter-section select {
                padding: 5px 10px;
                border: 1px solid #99ccff;
                border-radius: 4px;
                background-color: #f0faff;
                width: 120px;
            }
            .filter-section button {
                padding: 5px 15px;
                background-color: #007acc;
                color: white;
                border: none;
                border-radius: 5px;
                width: auto;
                cursor: pointer;
            }
        </style>
    </head>
    <body>
        <h1>Doctor's Weekly Schedule</h1>

        <form id="dateForm" method="GET" action="professionalleave">
            <input type="hidden" name="professionalID" value="${param.professionalID}" />
            <label for="selectedDate">Select Date:</label>
            <input type="date" id="selectedDate" name="selectedDate" value="${selectedDate}">
            <button type="submit">Submit</button>
        </form>

        <table>
            <thead>
                <tr>
                    <th>Shift</th>
                        <c:forEach var="day" begin="0" end="6">
                            <c:set var="date" value="${firstMondayOfWeek.plusDays(day)}" />
                        <th>${date.getDayOfWeek()} (${date})</th>
                        </c:forEach>
                </tr>
            </thead>
            <tbody>
                <c:set var="shifts" value="${'MORNING,AFTERNOON,EVENING'.split(',')}" />
                <c:forEach var="shift" items="${shifts}">
                    <tr class="shift">
                        <td>${shift}</td>
                        <c:forEach var="day" begin="0" end="6">
                            <td>
                                <c:forEach var="schedule" items="${professionalList}">
                                    <c:set var="scheduleDate" value="${firstMondayOfWeek.plusDays(day)}" />
                                    <c:if test="${schedule.dayOfWeek == day + 2 && schedule.shift == shift}">
                                        <c:choose>
                                            <c:when test="${schedule.startTime == '00:00:00' && schedule.endTime == '00:00:00'}">
                                                <span class="on-leave">On Leave: ${schedule.status}</span>
                                            </c:when>
                                            <c:otherwise>
                                                ${schedule.startTime} - ${schedule.endTime}
                                            </c:otherwise>
                                        </c:choose>
                                    </c:if>
                                </c:forEach>
                            </td>
                        </c:forEach>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <h1>Professional Leave List</h1>

        <c:if test="${not empty errorMessage}">
            <div style="color: red; margin-bottom: 10px;">
                ${errorMessage}
            </div>
        </c:if>

        <!-- Hiển thị thông báo thành công -->
        <c:if test="${not empty successMessage}">
            <div style="color: green; margin-bottom: 10px;">
                ${successMessage}
            </div>
        </c:if>

        <!-- Add Status Filter -->
        <div class="filter-section">
            <form method="GET" action="professionalleave">
                <input type="hidden" name="professionalID" value="${param.professionalID}" />
                <input type="hidden" name="selectedDate" value="${selectedDate}" />
                <label for="statusFilter">Filter by Status:</label>
                <select name="statusFilter" id="statusFilter">
                    <option value="All" ${param.statusFilter == 'All' || empty param.statusFilter ? 'selected' : ''}>All</option>
                    <option value="Missed time!" ${param.statusFilter == 'Missed time!' ? 'selected' : ''}>Missed time!</option>
                    <option value="Pending" ${param.statusFilter == 'Pending' ? 'selected' : ''}>Pending</option>
                    <option value="Approved" ${param.statusFilter == 'Approved' ? 'selected' : ''}>Approved</option>
                    <option value="Rejected" ${param.statusFilter == 'Rejected' ? 'selected' : ''}>Rejected</option>
                </select>
                <button type="submit">Filter</button>
            </form>
        </div>

        <div id="leave-section">
            <table id="leaveTable">
                <tr>
                    <th>Leave ID</th>
                    <th>Professional ID</th>
                    <th>Leave Date</th>
                    <th>Reason</th>
                    <th>Status</th>
                    <th>Action</th>
                </tr>
                <c:choose>
                    <c:when test="${not empty leaveList}">
                        <c:forEach var="leave" items="${leaveList}">
                            <c:if test="${param.statusFilter == 'All' || empty param.statusFilter || leave.status == param.statusFilter}">
                                <tr>
                                    <td>${leave.leaveID}</td>
                                    <td>${leave.professionalID}</td>
                                    <td>${leave.leaveDate}</td>
                                    <td>${leave.reason}</td>
                                    <td>
                                        <form method="POST" action="professionalleave">
                                            <select name="status">
                                                <option value="Missed time!" ${leave.status == 'Missed time!' ? 'selected' : ''}>Missed time!</option>
                                                <option value="Pending" ${leave.status == 'Pending' ? 'selected' : ''}>Pending</option>
                                                <option value="Approved" ${leave.status == 'Approved' ? 'selected' : ''}>Approved</option>
                                                <option value="Rejected" ${leave.status == 'Rejected' ? 'selected' : ''}>Rejected</option>
                                            </select>
                                            <input type="hidden" name="oldStatus" value="${leave.status}" />
                                            <input type="hidden" name="leaveID" value="${leave.leaveID}" />
                                            <input type="hidden" name="professionalID" value="${leave.professionalID}" />
                                            <input type="hidden" name="leaveDate" value="${leave.leaveDate}" />
                                    </td>
                                    <td>
                                        <button type="submit">Update</button>
                                    </td>
                                    </form>
                                </tr>
                            </c:if>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td colspan="6">No records found</td>
                        </tr>
                    </c:otherwise>
                </c:choose>
            </table>
        </div>
    </body>
</html>
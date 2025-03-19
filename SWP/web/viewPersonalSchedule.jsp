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
                justify-content: space-between;
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
            #dateForm {
                display: flex;
                align-items: center;
                justify-content: flex-start; /* Căn trái */
                gap: 10px;
                margin-bottom: 20px;
            }

            #dateForm input[type="date"] {
                padding: 5px 10px; /* Thu nhỏ padding */
                width: 150px; /* Giảm độ rộng */
                font-size: 14px; /* Giảm kích thước chữ */
            }

            #dateForm button {
                padding: 5px 15px; /* Điều chỉnh padding */
                font-size: 14px; /* Giảm kích thước chữ */
                background-color: #007acc;
                color: white;
                border: none;
                border-radius: 5px;
                width: auto; /* Để kích thước tự động theo nội dung */
                cursor: pointer;
            }

            #dateForm button:hover {
                background-color: #005580;
            }


        </style>
    </head>
    <body>
        <h1>Doctor's Weekly Schedule</h1>

        <form id="dateForm" method="GET" action="viewpersonalschedule">
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
        <!-- Hiển thị thông báo lỗi -->
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

        <div id="leave-section">
            <form id="leaveForm" method="POST" action="viewpersonalschedule">
                <input type="hidden" id="professionalID" name="professionalID" value="${param.professionalID}" readonly><br>

                <label for="leaveDate">Leave Date:</label>
                <input type="date" id="leaveDate" name="leaveDate"><br>

                <label for="reason">Reason:</label>
                <select id="reason" name="reason" required="">
                    <option value="">-- Select Reason --</option>
                    <option value="Work Related">Work-Related</option>
                    <option value="Personal reason">Personal</option>
                    <option value="other">Other</option>
                </select><br>

                <label>Note:</label>
                <input type="text" id="note" name="note"><br>

                <button type="submit">Submit Leave Request</button>
            </form>

            <table id="leaveTable">
                <tr>
                    <th>Leave Date</th>
                    <th>Reason</th>
                    <th>Status</th>
                </tr>
                <c:choose>
                    <c:when test="${not empty leaveList}">
                        <c:forEach var="leave" items="${leaveList}">
                            <tr>
                                <td>${leave.leaveDate}</td>
                                <td>${leave.reason}</td>
                                <td>${leave.status}</td>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td colspan="5">No records found</td>
                        </tr>
                    </c:otherwise>
                </c:choose>
            </table>
        </div>
    </body>

</html>

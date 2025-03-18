<%@ page import="java.time.LocalDate, java.time.temporal.TemporalAdjusters, java.time.DayOfWeek" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <title>Doctor's Weekly Schedule</title>
        <link rel="stylesheet" type="text/css" href="assets/css/css_viewPersonalSchedule.css">
    </head>
    <body>
        <!-- Include phần header -->
        <jsp:include page="headerStaff.jsp"></jsp:include>

            <div class="main-container">
                <!-- Include phần sidebar -->
            <jsp:include page="sidebar.jsp"></jsp:include>

                <!-- Nội dung chính -->
                <div class="content">
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
                        <input type="date" id="leaveDate" required="" name="leaveDate"><br>

                        <label for="reason">Reason:</label>
                        <select id="reason" name="reason" required="">
                            <option value="">-- Select Reason --</option>
                            <option value="Work Related">Work-Related</option>
                            <option value="Personal reason">Personal</option>
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
            </div>
        </div>
    </body>
</html>

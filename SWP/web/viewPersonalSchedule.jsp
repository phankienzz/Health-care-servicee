<%@ page import="java.time.LocalDate, java.time.temporal.TemporalAdjusters, java.time.DayOfWeek" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <title>Doctor's Weekly Schedule</title>
        <style>
            table {
                width: 100%;
                border-collapse: collapse;
            }
            th, td {
                border: 1px solid black;
                text-align: center;
                padding: 8px;
            }
            th {
                background-color: #f2f2f2;
            }
            .shift {
                height: 50px;
            }
            .on-leave {
                background-color: #ffcccc;
            }
        </style>
    </head>
    <body>
        <h1>Doctor's Weekly Schedule</h1>

        <form method="GET" action="viewpersonalschedule">
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
                        <td colspan="5">No records found</td>
                    </tr>
                </c:otherwise>
            </c:choose>
        </table>
    </body>
</html>

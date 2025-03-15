<%@ page import="java.time.LocalDate, java.time.temporal.TemporalAdjusters, java.time.DayOfWeek" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    // Lấy năm hiện tại hoặc từ request
    String selectedYear = request.getParameter("year") != null ? request.getParameter("year") : String.valueOf(LocalDate.now().getYear());

    // Tính ngày thứ Hai đầu tiên của năm
    LocalDate firstMonday = LocalDate.of(Integer.parseInt(selectedYear), 1, 1)
                            .with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));

    // Lưu vào request để sử dụng trong JSTL
    request.setAttribute("firstMonday", firstMonday);
    request.setAttribute("selectedYear", selectedYear);
%>

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
        </style>
    </head>
    <body>
        <h1>Doctor's Weekly Schedule</h1>

        <form method="GET" action="viewPersonalSchedule.jsp">
            <label for="year">Year:</label>
            <select name="year" id="year" onchange="this.form.submit()">
                <c:forEach var="year" begin="2020" end="2030">
                    <option value="${year}" ${year == selectedYear ? 'selected' : ''}>${year}</option>
                </c:forEach>
            </select>

            <label for="weekRange">Week:</label>
            <select name="weekRange">
                <c:forEach var="i" begin="0" end="51">
                    <c:set var="weekStart" value="${firstMonday.plusWeeks(i)}" />
                    <c:set var="weekEnd" value="${weekStart.plusDays(6)}" />
                    <c:if test="${weekStart.year == selectedYear}">
                        <option value="${weekStart} To ${weekEnd}">
                            ${weekStart} To ${weekEnd}
                        </option>
                    </c:if>
                </c:forEach>
            </select>

            <button type="submit">Submit</button>
        </form>

        <table>
            <thead>
                <tr>
                    <th>Shift</th>
                    <c:forEach var="day" begin="0" end="6">
                        <th>
                            <c:set var="date" value="${firstMonday.plusWeeks(0).plusDays(day)}" />
                            ${date.getDayOfWeek()} (${date})
                        </th>
                    </c:forEach>
                </tr>
            </thead>
            <tbody>
                <c:set var="shifts" value="${'MORNING,AFTERNOON,EVENING'.split(',')}" />
                <c:forEach var="shift" items="${shifts}">
                    <tr class="shift">
                        <td>${shift}</td>
                        <c:forEach var="day" begin="2" end="8">
                            <td>
                                <c:forEach var="schedule" items="${professionalList}">
                                    <c:set var="scheduleDate" value="${firstMonday.plusWeeks(0).plusDays(day)}" />
                                    <c:if test="${schedule.dayOfWeek == day && schedule.shift == shift}">
                                        ${schedule.startTime} - ${schedule.endTime}
                                    </c:if>
                                </c:forEach>
                            </td>
                        </c:forEach>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

    </body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="java.time.LocalDate, java.time.temporal.TemporalAdjusters, java.time.DayOfWeek" %>

<%
    LocalDate currentDate = LocalDate.now();
    LocalDate startOfWeek = currentDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
    LocalDate endOfWeek = startOfWeek.plusDays(6);

    String selectedYear = request.getParameter("year") != null ? request.getParameter("year") : String.valueOf(currentDate.getYear());
    String startDateParam = request.getParameter("startDate");
    String endDateParam = request.getParameter("endDate");

    if (startDateParam != null && endDateParam != null) {
        startOfWeek = LocalDate.parse(startDateParam);
        endOfWeek = LocalDate.parse(endDateParam);
    }

    request.setAttribute("startOfWeek", startOfWeek);
    request.setAttribute("endOfWeek", endOfWeek);
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

        <form method="GET" action="viewpersonalschedule">
            <label for="year">Year:</label>
            <select name="year" id="year">
                <c:forEach var="year" begin="2020" end="2030">
                    <option value="${year}" ${year == selectedYear ? 'selected' : ''}>${year}</option>
                </c:forEach>
            </select>

            <label for="startDate">Week:</label>
            <input type="date" name="startDate" value="${startOfWeek}" />
            <input type="date" name="endDate" value="${endOfWeek}" />

            <button type="submit">Submit</button>
        </form>

        <table>
            <thead>
                <tr>
                    <th>Shift</th>
                        <c:forEach var="day" begin="0" end="6">
                        <th>
                            <c:set var="date" value="${startOfWeek.plusDays(day)}" />
                            ${date.getDayOfWeek()} (${date})
                        </th>
                    </c:forEach>
                </tr>
            </thead>
            <tbody>
                <c:set var="shifts" value="${fn:split('MORNING,AFTERNOON,EVENING', ',')}" />
                <c:forEach var="shift" items="${shifts}">
                    <tr class="shift">
                        <td>${shift}</td>
                        <c:forEach var="day" begin="2" end="8">
                            <td>
                                <c:forEach var="schedule" items="${professionalList}">
                                    <c:set var="scheduleDate" value="${startOfWeek.plusDays(day)}" />
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

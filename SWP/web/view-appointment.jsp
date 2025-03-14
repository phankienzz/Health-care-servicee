<%@page import=" java.util.List, model.MedicalExamination, dao.MedicalExaminationDAO" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Danh sách cuộc hẹn</title>
    <link rel="stylesheet" href="plugins/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="plugins/icofont/icofont.min.css">
    <link rel="stylesheet" href="assets/css/style.css">
</head>
<body>

<%
    // Lấy danh sách cuộc hẹn nếu chưa có trong session
    if (session.getAttribute("appointments") == null) {
        MedicalExaminationDAO dao = new MedicalExaminationDAO();
        List<MedicalExamination> appointments = dao.getAllMedicalExamination();
        session.setAttribute("appointments", appointments);
    }
%>

<div class="container mt-5">
    <h2 class="text-center">Danh sách cuộc hẹn</h2>
    
    <!-- Bảng hiển thị danh sách cuộc hẹn -->
    <table class="table table-bordered table-striped">
        <thead class="thead-dark">
            <tr>
                <th>ID</th>
                <th>Khách hàng</th>
                <th>Bác sĩ</th>
                <th>Ngày hẹn</th>
                <th>Trạng thái</th>
                <th>Ghi chú</th>
                <th>Hành động</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="appointment" items="${sessionScope.appointments}">
                <tr>
                    <td>${appointment.getExaminationID()}</td>
                    <td>${appointment.getCustomerId().getFullName()}</td>
                    <td>${appointment.getConsultantId().getFullName()}</td>
                    <td>${appointment.getExaminationDate()}</td>
                    <td>
                        <span class="badge badge-${appointment.getStatus() eq 'Pending' ? 'warning' : 'success'}">
                            ${appointment.getStatus()}
                        </span>
                    </td>
                    <td>${appointment.getNote()}</td>
                    <td>
                        <a href="DetailAppointmentServlet?id=${appointment.getExaminationID()}" class="btn btn-info btn-sm">Chi tiết</a>
                        <a href="CancelAppointmentServlet?id=${appointment.getExaminationID()}" class="btn btn-danger btn-sm"
                           onclick="return confirm('Bạn có chắc muốn hủy cuộc hẹn này?');">Hủy</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

<script src="plugins/jquery/jquery.js"></script>
<script src="plugins/bootstrap/js/bootstrap.min.js"></script>

</body>
</html>
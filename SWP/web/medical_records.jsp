<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">
        <link rel="shortcut icon" type="image/x-icon" href="assets/img/favicon.ico">
        <title>Medical Records - Customer</title>
        <link rel="stylesheet" type="text/css" href="assets/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="assets/css/font-awesome.min.css">
        <link rel="stylesheet" type="text/css" href="assets/css/select2.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker.min.css">
        <link rel="stylesheet" type="text/css" href="assets/css/style.css">
    </head>
    <body>
        <div class="main-wrapper">
            <div class="header">
                <div class="header-left">
                    <a href="customer-dashboard.jsp" class="logo">
                        <img src="assets/img/logo.png" width="35" height="35" alt=""> <span>Preclinic</span>
                    </a>
                </div>
                            <ul class="nav user-menu float-right">
                                <li class="nav-item dropdown has-arrow">
                                    <a href="#" class="dropdown-toggle nav-link user-link" data-toggle="dropdown">
                                        <span class="user-img"><img class="rounded-circle" src="assets/img/user.jpg" width="40" alt="Customer">
                                            <span class="status online"></span></span>
                                        <span>${sessionScope.customerAccount.fullName}</span>
                                    </a>
                                    <div class="dropdown-menu">
                                        <a class="dropdown-item" href="profile.jsp">My Profile</a>
                                         <a class="dropdown-item" href="customer-medical-records">Medical record</a>
                                        <a class="dropdown-item" href="logout">Logout</a>
                                    </div>
                                </li>
                            </ul>

                
                </div>

                <div class="page-wrapper">
                    <div class="content container-fluid">
                        <div class="row align-items-center mb-4">
                            <div class="col-md-6">
                                <h4 class="page-title">Your Appointments</h4>
                            </div>
                        </div>
                        <!-- Hi?n th? th�ng b�o -->
                    <c:if test="${not empty message}">
                        <div class="alert alert-success">${message}</div>
                        <c:remove var="message" scope="session"/>
                    </c:if>
                    <c ??c hi?u
                       <c:if test="${not empty error}">
                           <div class="alert alert-danger">${error}</div>
                       <c:remove var="error" scope="session"/>
                    </c:if>
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Appointment List</h5>
                        <div class="table-responsive">
                            <table class="table table-hover">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Doctor</th>
                                        <th>Service</th>
                                        <th>Date</th>
                                        <th>Status</th>
                                        <th>Actions</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:choose>
                                        <c:when test="${empty appointments}">
                                            <tr>
                                                <td colspan="6" class="text-center">No appointments found.</td>
                                            </tr>
                                        </c:when>
                                        <c:otherwise>
                                            <c:forEach var="exam" items="${appointments}">
                                                <tr>
                                                    <td>${exam.examinationID}</td>
                                                    <td>${exam.consultantId.fullName}</td>
                                                    <td>
                                                        <c:forEach var="service" items="${exam.list}" varStatus="loop">
                                                            ${service.packageName}<c:if test="${!loop.last}">, </c:if>
                                                        </c:forEach>
                                                    </td>
                                                    <td>${exam.examinationDate}</td>
                                                    <td><c:choose>
                                                            <c:when test="${exam.status == 'Pending'}">
                                                                <span class="badge" style="background-color: #ff9800; color: white;">${exam.status}</span>
                                                            </c:when>
                                                            <c:when test="${exam.status == 'In process'}">
                                                                <span class="badge" style="background-color: #2196f3; color: white;">${exam.status}</span>
                                                            </c:when>
                                                            <c:when test="${exam.status == 'Confirmed'}">
                                                                <span class="badge" style="background-color: #4caf50; color: white;">${exam.status}</span>
                                                            </c:when>
                                                            <c:when test="${exam.status == 'Completed'}">
                                                                <span class="badge" style="background-color: #8bc34a; color: white;">${exam.status}</span>
                                                            </c:when>
                                                            <c:when test="${exam.status == 'Rejected'}">
                                                                <span class="badge" style="background-color: #f44336; color: white;">${exam.status}</span>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <span class="badge" style="background-color: #757575; color: white;">${exam.status}</span>
                                                            </c:otherwise>
                                                        </c:choose></td>
                                                    <td>
                                                        <a href="view-medical-record?examId=${exam.examinationID}" class="btn btn-sm btn-info">View Details</a>
                                                        <c:if test="${exam.status == 'Pending'}">
                                                            <a href="cancel-appointment?examId=${exam.examinationID}" class="btn btn-sm btn-danger" onclick="return confirm('Are you sure you want to cancel this appointment?');">Cancel</a>
                                                        </c:if>
                                                        <c:if test="${exam.status == 'Confirmed' || exam.status == 'Completed'}">
                                                            <a href="view-medical-record?examId=${exam.examinationID}" class="btn btn-sm btn-success">View Medical Record</a>
                                                        </c:if>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </c:otherwise>
                                    </c:choose>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
     <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="assets/js/bootstrap.min.js"></script>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/js/bootstrap-datetimepicker.min.js"></script>
    <script>
                                                                $(document).ready(function () {
                                                                    $('.datetimepicker').datetimepicker({format: 'DD/MM/YYYY'});
                                                                    $('.select2').select2();
                                                                });
    </script>
</body>
</html>
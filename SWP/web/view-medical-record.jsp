<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">
    <link rel="shortcut icon" type="image/x-icon" href="assets/img/favicon.ico">
    <title>Medical Record Details</title>
    <link rel="stylesheet" type="text/css" href="assets/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="assets/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="assets/css/style.css">
</head>
<body>
    <div class="main-wrapper">
        <div class="header">
            <div class="header-left">
                <a href="customer-dashboard.html" class="logo">
                    <img src="assets/img/logo.png" width="35" height="35" alt=""> <span>Preclinic</span>
                </a>
            </div>
            <ul class="nav user-menu float-right">
                <li class="nav-item dropdown has-arrow">
                    <a href="#" class="dropdown-toggle nav-link user-link" data-toggle="dropdown">
                        <span class="user-img"><img class="rounded-circle" src="assets/img/user.jpg" width="40" alt="Customer">
                            <span class="status online"></span></span>
                        <span>${sessionScope.customer.fullName}</span>
                    </a>
                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="customer-profile.jsp">My Profile</a>
                        <a class="dropdown-item" href="logout">Logout</a>
                    </div>
                </li>
            </ul>
        </div>
        <div class="sidebar" id="sidebar">
            <div class="sidebar-inner slimscroll">
                <div id="sidebar-menu" class="sidebar-menu">
                    <ul>
                        <li class="menu-title">Customer Menu</li>
                        <li>
                            <a href="customer-dashboard.jsp"><i class="fa fa-dashboard"></i> <span>Dashboard</span></a>
                        </li>
                        <li class="active">
                            <a href="customer-medical-records.jsp"><i class="fa fa-file-text-o"></i> <span>Medical Records</span></a>
                        </li>
                        <li>
                            <a href="book-appointment.jsp"><i class="fa fa-calendar"></i> <span>Book Appointment</span></a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="page-wrapper">
            <div class="content container-fluid">
                <div class="row">
                    <div class="col-lg-8 offset-lg-2">
                        <h4 class="page-title">Medical Record - Appointment #${exam.examinationID}</h4>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-8 offset-lg-2">
                        <div class="card">
                            <div class="card-body">
                                <h5 class="card-title">Appointment Details</h5>
                                <p><strong>Doctor:</strong> ${exam.consultantId.fullName}</p>
                                <p><strong>Services:</strong> 
                                    <c:forEach var="service" items="${exam.list}" varStatus="loop">
                                        ${service.packageName}<c:if test="${!loop.last}">, </c:if>
                                    </c:forEach>
                                </p>
                                <p><strong>Date:</strong> ${exam.examinationDate}</p>
                                <p><strong>Status:</strong> <span class="badge badge-info">${exam.status}</span></p>
                                <p><strong>Notes:</strong> ${exam.note}</p>
                            </div>
                        </div>
                        <c:if test="${medicalRecord != null}">
                            <div class="card">
                                <div class="card-body">
                                    <h5 class="card-title">Medical Record</h5>
                                    <p><strong>Diagnosis:</strong> ${medicalRecord.diagnosis}</p>
                                    <p><strong>Treatment Plan:</strong> ${medicalRecord.treatmentPlan}</p>
                                    <p><strong>Medications Prescribed:</strong> ${medicalRecord.medicationsPrescribed}</p>
                                    <p><strong>Doctor's Advice/Notes:</strong> ${medicalRecord.notes}</p>
                                    <p><strong>Created At:</strong> ${medicalRecord.createdAt}</p>
                                </div>
                            </div>
                        </c:if>
                        <div class="text-center">
                            <a href="customer-medical-records" class="btn btn-primary">Back to Records</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="assets/js/jquery-3.2.1.min.js"></script>
    <script src="assets/js/bootstrap.min.js"></script>
</body>
</html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="description" content="Orbitor, business, company, agency, modern, bootstrap4, tech, software">
        <meta name="author" content="themefisher.com">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Novena- Health & Care Medical template</title>
        <!-- Favicon -->
        <link rel="shortcut icon" type="image/x-icon" href="/images/favicon.ico">
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="plugins/bootstrap/css/bootstrap.min.css">
        <!-- Icon Font CSS -->
        <link rel="stylesheet" href="plugins/icofont/icofont.min.css">
        <!-- Slick Slider CSS -->
        <link rel="stylesheet" href="plugins/slick-carousel/slick/slick.css">
        <link rel="stylesheet" href="plugins/slick-carousel/slick/slick-theme.css">
        <!-- Main Stylesheet -->
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="assets/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/css/font-awesome.min.css">
        <link rel="stylesheet" href="assets/css/style.css">
        <style>
            .navbar {
                margin-top: 40px;
            }

            .login-container {
                text-align: right; /* Căn nội dung bên phải */
                margin-right: 20px; /* Khoảng cách từ lề phải (tuỳ chỉnh theo thiết kế) */
            }

            .btn-login {
                padding: 10px 20px; /* Tuỳ chỉnh kích thước nút */
                font-size: 14px; /* Tuỳ chỉnh kích thước chữ */
            }

        </style>
    </style>
</head>
<body>
    <header>
        <div class="main-wrapper">
            <div class="header">
                <div class="header-left">
                    <a href="index_1.jsp" class="logo">
                        <img src="assets/img/logo.png" width="35" height="35" alt="Preclinic">
                        <span>Preclinic</span>
                    </a>
                </div>
                <!-- Improved Login Button -->
                <c:if test="${sessionScope.customerAccount == null && sessionScope.staffAccount == null}">
                    <div class="login-container">
                        <a href="login.jsp" class="btn btn-primary btn-login">Đăng Nhập</a>
                    </div>
                </c:if>
                <c:if test="${sessionScope.customerAccount != null}">
                    <div class="header-right float-right">
                        <ul class="nav user-menu">
                            <li class="nav-item dropdown has-arrow">
                                <a href="#" class="dropdown-toggle nav-link user-link" data-toggle="dropdown">
                                    <span class="user-img">
                                        <img class="rounded-circle" src="pictureprofile?customerID=${sessionScope.customerAccount.customerID}" width="50" height="35"  >
                                        <span class="status online"></span>
                                    </span>
                                    <span>${sessionScope.customerAccount.fullName}</span>
                                </a>
                                <div class="dropdown-menu">                                  
                                    <a class="dropdown-item" href="profile.jsp">My Profile</a>

                                    <a class="dropdown-item" href="settings.html">Settings</a>
                                    <a class="dropdown-item" href="logout">Logout</a>
                                </div>
                            </li>
                        </ul>
                    </div>
                    <div class="dropdown mobile-user-menu float-right">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false"><i class="fa fa-ellipsis-v"></i></a>
                        <div class="dropdown-menu dropdown-menu-right">
                            <a class="dropdown-item" href="profile.jsp">My Profile</a>

                            <a class="dropdown-item" href="settings.html">Settings</a>
                            <a class="dropdown-item" href="logout">Logout</a>
                        </div>
                    </div>
                </c:if>
                <c:if test="${sessionScope.staffAccount != null}">
                    <div class="header-right float-right">
                        <ul class="nav user-menu">
                            <li class="nav-item dropdown has-arrow">
                                <a href="#" class="dropdown-toggle nav-link user-link" data-toggle="dropdown">
                                    <span class="user-img">
                                        <img class="rounded-circle" src="pictureprofile?customerID=${sessionScope.staffAccount.staffID}"   width="45" height="30" >
                                        <span class="status online"></span>
                                    </span>
                                    <span>${sessionScope.staffAccount.fullName}</span>
                                </a>
                                <div class="dropdown-menu">                                  
                                    <a class="dropdown-item" href="profile.jsp">My Profile</a>
                                    <!--<a class="dropdown-item" href="settings.html">Settings</a>-->
                                    <a class="dropdown-item" href="logout">Logout</a>
                                </div>
                            </li>
                        </ul>
                    </div>
                    <div class="dropdown mobile-user-menu float-right">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false"><i class="fa fa-ellipsis-v"></i></a>
                        <div class="dropdown-menu dropdown-menu-right">
                            <a class="dropdown-item" href="profile.jsp">My Profile</a>

                            <a class="dropdown-item" href="settings.html">Settings</a>
                            <a class="dropdown-item" href="logout">Logout</a>
                        </div>
                    </div>
                </c:if>
            </div>
        </div>
    </header>
    <nav class="navbar navbar-expand-lg navigation" id="navbar">
        <div class="container">
            <a class="navbar-brand" href="index_1.jsp">
                <img src="images/logo.png" alt="" class="img-fluid">
            </a>
            <button class="navbar-toggler collapsed" type="button" data-toggle="collapse" data-target="#navbarmain" aria-controls="navbarmain" aria-expanded="false" aria-label="Toggle navigation">
                <span class="icofont-navigation-menu"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarmain">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="index_1.jsp">Home</a>
                    </li>
                    <li class="nav-item"><a class="nav-link" href="about.jsp">About</a></li>
                    <li class="nav-item"><a class="nav-link" href="loadservice">Services</a></li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="department.html" id="dropdown02" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Department <i class="icofont-thin-down"></i></a>
                        <ul class="dropdown-menu" aria-labelledby="dropdown02">
                            <li><a class="dropdown-item" href="department.jsp">Departments</a></li>
                            <li><a class="dropdown-item" href="department-single.jsp">Department Single</a></li>
                        </ul>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="doctor.jsp" id="dropdown03" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Doctors <i class="icofont-thin-down"></i></a>
                        <ul class="dropdown-menu" aria-labelledby="dropdown03">
                            <li><a class="dropdown-item" href="doctor.jsp">Doctors</a></li>
                            <li><a class="dropdown-item" href="doctor-single.jsp">Doctor Single</a></li>
                            <li><a class="dropdown-item" href="appoinment.jsp">Appointment</a></li>
                        </ul>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="blog-sidebar.html" id="dropdown05" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">News<i class="icofont-thin-down"></i></a>
                        <ul class="dropdown-menu" aria-labelledby="dropdown05">
                            <li><a class="dropdown-item" href="allNews">All News</a></li>
                            <li><a class="dropdown-item" href="blog-single.html">Blog Single</a></li>
                        </ul>
                    </li>
                    <li class="nav-item"><a class="nav-link" href="contact.html">Contact</a></li>
                </ul>
            </div>
        </div>
    </nav>
</body>
</html>
<%-- 
    Document   : editseting
    Created on : Jan 14, 2025, 11:02:30 PM
    Author     : ADMIN
--%>

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
                <c:if test="${sessionScope.customerAccount != null}">
                    <div class="header-right float-right">
                        <ul class="nav user-menu">
                            <li class="nav-item dropdown has-arrow">
                                <a href="#" class="dropdown-toggle nav-link user-link" data-toggle="dropdown">
                                    <span class="user-img">
                                        <img class="rounded-circle" src="pictureprofile?customerID=${sessionScope.customerAccount.customerID}"  width="24" >
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

            </div>
        </div>
    </header>
</html>
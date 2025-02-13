<%-- 
    Document   : edit-profile
    Created on : Jan 14, 2025, 11:59:11 PM
    Author     : jaxbo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">


<!-- edit-profile23:03-->

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">
    <link rel="shortcut icon" type="image/x-icon" href="assets/img/favicon.ico">
    <title>Preclinic - Medical & Hospital - Bootstrap 4 Admin Template</title>
    <link rel="stylesheet" type="text/css" href="assets/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="assets/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="assets/css/select2.min.css">
    <link rel="stylesheet" type="text/css" href="assets/css/bootstrap-datetimepicker.min.css">
    <link rel="stylesheet" type="text/css" href="assets/css/style.css">
    <!--[if lt IE 9]>
		<script src="assets/js/html5shiv.min.js"></script>
		<script src="assets/js/respond.min.js"></script>
	<![endif]-->
</head>

<body>
    <div class="main-wrapper">
        <div class="header">
            <div class="header-left">
                <a href="dashboard.html" class="logo">
                    <img src="assets/img/logo.png" width="35" height="35" alt=""> <span>Preclinic</span>
                </a>
            </div>
            <ul class="nav user-menu float-right">
                <li class="nav-item dropdown has-arrow">
                    <a href="#" class="dropdown-toggle nav-link user-link" data-toggle="dropdown">
                        <span class="user-img"><img class="rounded-circle" src="assets/img/user.jpg" width="40"
                                                    alt="Admin">
                            <span class="status online"></span></span>
                            <span>${sessionScope.customerAccount.fullName}</span>
                    </a>
                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="profile.jsp">My Profile</a>
                        <a class="dropdown-item" href="edit-profile.jsp">Edit Profile</a>
                        <a class="dropdown-item" href="settings.jsp">Settings</a>
                        <a class="dropdown-item" href="logout">Logout</a>
                    </div>
                </li>
            </ul>
            <div class="dropdown mobile-user-menu float-right">
                <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown" aria-expanded="false"><i
                        class="fa fa-ellipsis-v"></i></a>
                <div class="dropdown-menu dropdown-menu-right">
                    <a class="dropdown-item" href="profile.html">My Profile</a>
                    <a class="dropdown-item" href="edit-profile.html">Edit Profile</a>
                    <a class="dropdown-item" href="settings.html">Settings</a>
                    <a class="dropdown-item" href="login.html">Logout</a>
                </div>
            </div>
        </div>

        <div class="page-wrapper-profile">
            <div class="content">
                <div class="row">
                    <div class="col-sm-12">
                        <h4 class="page-title">Edit Profile</h4>
                    </div>
                </div>
                <form action="editprofile" method="post" enctype="multipart/form-data">
                    <div class="card-box">
                        <h3 class="card-title">Basic Informations</h3>
                         <h4 class="center">${error}</h4>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="profile-img-wrap">
                                    <img class="inline-block" src="pictureprofile?customerID=${sessionScope.customerAccount.customerID}">
                                    <div class="fileupload btn">
                                        <span class="btn-text">edit</span>
                                        <input class="upload" type="file">
                                    </div>
                                </div>
                                <div class="profile-basic">
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group form-focus">
                                                <label class="focus-label">Full Name</label>
                                                <input type="text" class="form-control floating" value="${sessionScope.customerAccount.fullName}    ">
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group form-focus">
                                                <label class="focus-label">Email</label>
                                                <input type="text" class="form-control floating" value="${sessionScope.customerAccount.email}">
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group form-focus">
                                                <label class="focus-label">Birth of Date</label>
                                                <div class="cal-icon">
                                                    <input class="form-control floating datetimepicker" type="text"
                                                        value="${sessionScope.customerAccount.dateOfBirth}">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group form-focus select-focus">
                                                <label class="focus-label">Gender</label>
                                                <select class="select form-control floating" name="gender">
                                                    <option value="Male" ${sessionScope.customerAccount.gender == "Male" ? "selected" : ""}>Male</option>
                                                    <option value="Female" ${sessionScope.customerAccount.gender == "Female" ? "selected" : ""}>Female</option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="card-box">
                        <h3 class="card-title">Contact Informations</h3>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group form-focus">
                                    <label class="focus-label">Address</label>
                                    <input type="text" class="form-control floating" value="${sessionScope.customerAccount.address}">
                                </div>
                            </div>
                            
                            
                            <div class="col-md-12">
                                <div class="form-group form-focus">
                                    <label class="focus-label">Phone Number</label>
                                    <input type="text" class="form-control floating" value="${sessionScope.customerAccount.phone}">
                                </div>
                            </div>
                        </div>
                    </div>                    
                    <div class="text-center m-t-20">
                        <button class="btn btn-primary submit-btn" type="submit">Save</button>
                    </div>
                </form>
            </div>
        </div>
    </div>


    <div class="sidebar-overlay" data-reff=""></div>
    <script src="assets/js/jquery-3.2.1.min.js"></script>
    <script src="assets/js/popper.min.js"></script>
    <script src="assets/js/bootstrap.min.js"></script>
    <script src="assets/js/jquery.slimscroll.js"></script>
    <script src="assets/js/select2.min.js"></script>
    <script src="assets/js/moment.min.js"></script>
    <script src="assets/js/bootstrap-datetimepicker.min.js"></script>
    <script src="assets/js/app.js"></script>
</body>


<!-- edit-profile23:05-->

</html>

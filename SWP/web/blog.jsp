<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="model.News" %> <!-- Thay your.package bằng tên gói thực tế -->
<!DOCTYPE html>
<html lang="en">

    <!-- blog23:34-->
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">
        <link rel="shortcut icon" type="image/x-icon" href="assets/img/favicon.ico">
        <title>Preclinic - Medical & Hospital - Bootstrap 4 Admin Template</title>
        <link rel="stylesheet" type="text/css" href="assets/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="assets/css/font-awesome.min.css">
        <link rel="stylesheet" type="text/css" href="assets/css/style.css">



        <!--[if lt IE 9]>
                    <script src="assets/js/html5shiv.min.js"></script>
                    <script src="assets/js/respond.min.js"></script>
            <![endif]-->
    </head>

    <style>
        .btn-update {
            display: inline-flex;
            align-items: flex-start;
            justify-content: flex-end;
            background-color: #007bff;
            color: white;
            padding: 10px 15px;
            text-decoration: none;
            border-radius: 5px;
            font-size: 16px;
            border: none;
            cursor: pointer;
        }
        .btn-update:hover {
            background-color: #0056b3;
        }



    </style>
    <body>
        <div class="main-wrapper">
            <div class="header">
                <div class="header-left">
                    <a href="dashboard.html" class="logo">
                        <img src="assets/img/logo.png" width="35" height="35" alt=""> <span>Preclinic</span>
                    </a>
                </div>
                <a id="toggle_btn" href="javascript:void(0);"><i class="fa fa-bars"></i></a>
                <a id="mobile_btn" class="mobile_btn float-left" href="#sidebar"><i class="fa fa-bars"></i></a>
                <ul class="nav user-menu float-right">
                    <li class="nav-item dropdown d-none d-sm-block">
                        <a href="#" class="dropdown-toggle nav-link" data-toggle="dropdown"><i class="fa fa-bell-o"></i> <span class="badge badge-pill bg-danger float-right">3</span></a>
                        <div class="dropdown-menu notifications">
                            <div class="topnav-dropdown-header">
                                <span>Notifications</span>
                            </div>
                            <div class="drop-scroll">
                                <ul class="notification-list">
                                    <li class="notification-message">
                                        <a href="activities.html">
                                            <div class="media">
                                                <span class="avatar">
                                                    <img alt="John Doe" src="assets/img/user.jpg" class="img-fluid rounded-circle">
                                                </span>
                                                <div class="media-body">
                                                    <p class="noti-details"><span class="noti-title">John Doe</span> added new task <span class="noti-title">Patient appointment booking</span></p>
                                                    <p class="noti-time"><span class="notification-time">4 mins ago</span></p>
                                                </div>
                                            </div>
                                        </a>
                                    </li>
                                    <li class="notification-message">
                                        <a href="activities.html">
                                            <div class="media">
                                                <span class="avatar">V</span>
                                                <div class="media-body">
                                                    <p class="noti-details"><span class="noti-title">Tarah Shropshire</span> changed the task name <span class="noti-title">Appointment booking with payment gateway</span></p>
                                                    <p class="noti-time"><span class="notification-time">6 mins ago</span></p>
                                                </div>
                                            </div>
                                        </a>
                                    </li>
                                    <li class="notification-message">
                                        <a href="activities.html">
                                            <div class="media">
                                                <span class="avatar">L</span>
                                                <div class="media-body">
                                                    <p class="noti-details"><span class="noti-title">Misty Tison</span> added <span class="noti-title">Domenic Houston</span> and <span class="noti-title">Claire Mapes</span> to project <span class="noti-title">Doctor available module</span></p>
                                                    <p class="noti-time"><span class="notification-time">8 mins ago</span></p>
                                                </div>
                                            </div>
                                        </a>
                                    </li>
                                    <li class="notification-message">
                                        <a href="activities.html">
                                            <div class="media">
                                                <span class="avatar">G</span>
                                                <div class="media-body">
                                                    <p class="noti-details"><span class="noti-title">Rolland Webber</span> completed task <span class="noti-title">Patient and Doctor video conferencing</span></p>
                                                    <p class="noti-time"><span class="notification-time">12 mins ago</span></p>
                                                </div>
                                            </div>
                                        </a>
                                    </li>
                                    <li class="notification-message">
                                        <a href="activities.html">
                                            <div class="media">
                                                <span class="avatar">V</span>
                                                <div class="media-body">
                                                    <p class="noti-details"><span class="noti-title">Bernardo Galaviz</span> added new task <span class="noti-title">Private chat module</span></p>
                                                    <p class="noti-time"><span class="notification-time">2 days ago</span></p>
                                                </div>
                                            </div>
                                        </a>
                                    </li>
                                </ul>
                            </div>
                            <div class="topnav-dropdown-footer">
                                <a href="activities.html">View all Notifications</a>
                            </div>
                        </div>
                    </li>
                    <li class="nav-item dropdown d-none d-sm-block">
                        <a href="javascript:void(0);" id="open_msg_box" class="hasnotifications nav-link"><i class="fa fa-comment-o"></i> <span class="badge badge-pill bg-danger float-right">8</span></a>
                    </li>
                    <li class="nav-item dropdown has-arrow">
                        <a href="#" class="dropdown-toggle nav-link user-link" data-toggle="dropdown">
                            <span class="user-img"><img class="rounded-circle" src="assets/img/user.jpg" width="40" alt="Admin">
                                <span class="status online"></span></span>
                            <span>Admin</span>
                        </a>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="profile.html">My Profile</a>
                            <a class="dropdown-item" href="edit-profile.html">Edit Profile</a>
                            <a class="dropdown-item" href="settings.html">Settings</a>
                            <a class="dropdown-item" href="login.html">Logout</a>
                        </div>
                    </li>
                </ul>
                <div class="dropdown mobile-user-menu float-right">
                    <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown" aria-expanded="false"><i class="fa fa-ellipsis-v"></i></a>
                    <div class="dropdown-menu dropdown-menu-right">
                        <a class="dropdown-item" href="profile.html">My Profile</a>
                        <a class="dropdown-item" href="edit-profile.html">Edit Profile</a>
                        <a class="dropdown-item" href="settings.html">Settings</a>
                        <a class="dropdown-item" href="login.html">Logout</a>
                    </div>
                </div>
            </div>
            <div class="sidebar" id="sidebar">
                <div class="sidebar-inner slimscroll">
                    <div id="sidebar-menu" class="sidebar-menu">
                        <ul>
                            <li class="menu-title">Main</li>
                            <li>
                                <a href="dashboard.html"><i class="fa fa-dashboard"></i> <span>Dashboard</span></a>
                            </li>
                            <li>
                                <a href="doctors.html"><i class="fa fa-user-md"></i> <span>Doctors</span></a>
                            </li>
                            <li>
                                <a href="patients.html"><i class="fa fa-wheelchair"></i> <span>Patients</span></a>
                            </li>
                            <li>
                                <a href="appointments.html"><i class="fa fa-calendar"></i> <span>Appointments</span></a>
                            </li>
                            <li>
                                <a href="schedule.html"><i class="fa fa-calendar-check-o"></i> <span>Doctor Schedule</span></a>
                            </li>
                            <li>
                                <a href="departments.html"><i class="fa fa-hospital-o"></i> <span>Departments</span></a>
                            </li>
                            <li class="submenu">
                                <a href="#"><i class="fa fa-user"></i> <span> Employees </span> <span class="menu-arrow"></span></a>
                                <ul style="display: none;">
                                    <li><a href="employees.html">Employees List</a></li>
                                    <li><a href="leaves.html">Leaves</a></li>
                                    <li><a href="holidays.html">Holidays</a></li>
                                    <li><a href="attendance.html">Attendance</a></li>
                                </ul>
                            </li>
                            <li class="submenu">
                                <a href="#"><i class="fa fa-money"></i> <span> Accounts </span> <span class="menu-arrow"></span></a>
                                <ul style="display: none;">
                                    <li><a href="invoices.html">Invoices</a></li>
                                    <li><a href="payments.html">Payments</a></li>
                                    <li><a href="expenses.html">Expenses</a></li>
                                    <li><a href="taxes.html">Taxes</a></li>
                                    <li><a href="provident-fund.html">Provident Fund</a></li>
                                </ul>
                            </li>
                            <li class="submenu">
                                <a href="#"><i class="fa fa-book"></i> <span> Payroll </span> <span class="menu-arrow"></span></a>
                                <ul style="display: none;">
                                    <li><a href="salary.html"> Employee Salary </a></li>
                                    <li><a href="salary-view.html"> Payslip </a></li>
                                </ul>
                            </li>
                            <li>
                                <a href="chat.html"><i class="fa fa-comments"></i> <span>Chat</span> <span class="badge badge-pill bg-primary float-right">5</span></a>
                            </li>
                            <li class="submenu">
                                <a href="#"><i class="fa fa-video-camera camera"></i> <span> Calls</span> <span class="menu-arrow"></span></a>
                                <ul style="display: none;">
                                    <li><a href="voice-call.html">Voice Call</a></li>
                                    <li><a href="video-call.html">Video Call</a></li>
                                    <li><a href="incoming-call.html">Incoming Call</a></li>
                                </ul>
                            </li>
                            <li class="submenu">
                                <a href="#"><i class="fa fa-envelope"></i> <span> Email</span> <span class="menu-arrow"></span></a>
                                <ul style="display: none;">
                                    <li><a href="compose.html">Compose Mail</a></li>
                                    <li><a href="inbox.html">Inbox</a></li>
                                    <li><a href="mail-view.html">Mail View</a></li>
                                </ul>
                            </li>
                            <li class="submenu">
                                <a href="#"><i class="fa fa-commenting-o"></i> <span> Blog</span> <span class="menu-arrow"></span></a>
                                <ul style="display: none;">
                                    <li><a class="active" href="homeblogseverlet">Blog</a></li>
                                    <li><a href="blog-details.jsp">Blog View</a></li>
                                    <li><a href="add-blog.jsp">Add Blog</a></li>
                                    <li><a href="edit-blog.html">Edit Blog</a></li>
                                </ul>
                            </li>
                            <li>
                                <a href="assets.html"><i class="fa fa-cube"></i> <span>Assets</span></a>
                            </li>
                            <li>
                                <a href="activities.html"><i class="fa fa-bell-o"></i> <span>Activities</span></a>
                            </li>
                            <li class="submenu">
                                <a href="#"><i class="fa fa-flag-o"></i> <span> Reports </span> <span class="menu-arrow"></span></a>
                                <ul style="display: none;">
                                    <li><a href="expense-reports.html"> Expense Report </a></li>
                                    <li><a href="invoice-reports.html"> Invoice Report </a></li>
                                </ul>
                            </li>
                            <li>
                                <a href="settings.html"><i class="fa fa-cog"></i> <span>Settings</span></a>
                            </li>
                            <li class="menu-title">UI Elements</li>
                            <li class="submenu">
                                <a href="#"><i class="fa fa-laptop"></i> <span> Components</span> <span class="menu-arrow"></span></a>
                                <ul style="display: none;">
                                    <li><a href="uikit.html">UI Kit</a></li>
                                    <li><a href="typography.html">Typography</a></li>
                                    <li><a href="tabs.html">Tabs</a></li>
                                </ul>
                            </li>
                            <li class="submenu">
                                <a href="#"><i class="fa fa-edit"></i> <span> Forms</span> <span class="menu-arrow"></span></a>
                                <ul style="display: none;">
                                    <li><a href="form-basic-inputs.html">Basic Inputs</a></li>
                                    <li><a href="form-input-groups.html">Input Groups</a></li>
                                    <li><a href="form-horizontal.html">Horizontal Form</a></li>
                                    <li><a href="form-vertical.html">Vertical Form</a></li>
                                </ul>
                            </li>
                            <li class="submenu">
                                <a href="#"><i class="fa fa-table"></i> <span> Tables</span> <span class="menu-arrow"></span></a>
                                <ul style="display: none;">
                                    <li><a href="tables-basic.html">Basic Tables</a></li>
                                    <li><a href="tables-datatables.html">Data Table</a></li>
                                </ul>
                            </li>
                            <li>
                                <a href="calendar.html"><i class="fa fa-calendar"></i> <span>Calendar</span></a>
                            </li>
                            <li class="menu-title">Extras</li>
                            <li class="submenu">
                                <a href="#"><i class="fa fa-columns"></i> <span>Pages</span> <span class="menu-arrow"></span></a>
                                <ul style="display: none;">
                                    <li><a href="login.html"> Login </a></li>
                                    <li><a href="register.html"> Register </a></li>
                                    <li><a href="forgot-password.html"> Forgot Password </a></li>
                                    <li><a href="change-password2.html"> Change Password </a></li>
                                    <li><a href="lock-screen.html"> Lock Screen </a></li>
                                    <li><a href="profile.html"> Profile </a></li>
                                    <li><a href="gallery.html"> Gallery </a></li>
                                    <li><a href="error-404.html">404 Error </a></li>
                                    <li><a href="error-500.html">500 Error </a></li>
                                    <li><a href="blank-page.html"> Blank Page </a></li>
                                </ul>
                            </li>
                            <li class="submenu">
                                <a href="javascript:void(0);"><i class="fa fa-share-alt"></i> <span>Multi Level</span> <span class="menu-arrow"></span></a>
                                <ul style="display: none;">
                                    <li class="submenu">
                                        <a href="javascript:void(0);"><span>Level 1</span> <span class="menu-arrow"></span></a>
                                        <ul style="display: none;">
                                            <li><a href="javascript:void(0);"><span>Level 2</span></a></li>
                                            <li class="submenu">
                                                <a href="javascript:void(0);"> <span> Level 2</span> <span class="menu-arrow"></span></a>
                                                <ul style="display: none;">
                                                    <li><a href="javascript:void(0);">Level 3</a></li>
                                                    <li><a href="javascript:void(0);">Level 3</a></li>
                                                </ul>
                                            </li>
                                            <li><a href="javascript:void(0);"><span>Level 2</span></a></li>
                                        </ul>
                                    </li>
                                    <li>
                                        <a href="javascript:void(0);"><span>Level 1</span></a>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="page-wrapper">
                <div class="content">
                    <div class="row">
                        <div class="col-sm-8 col-4">
                            <h4 class="page-title">Blog</h4>
                        </div>
                        <div class="col-sm-4 col-8 text-right m-b-30">
                            <a class="btn btn-primary btn-rounded float-right" href="add-blog.jsp">
                                <i class="fa fa-plus"></i> Add Blog
                            </a>
                        </div>
                    </div>



                    <!-- Search Bar -->
                    <form action="Search_block" method="get" class="d-flex mx-auto my-2 my-lg-0">
                        <input type="text" name="keyword" id="searchKeyword" placeholder="Search blogs..." class="form-control" value="${keyword}">
                        <button type="submit" class="btn btn-primary">Search</button>
                    </form>

                    <!-- Hiển thị kết quả AJAX -->
                    <div id="searchResults" class="row mt-3"></div>



                    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
                    <script>
                        $(document).ready(function () {
                            $("#searchKeyword").keyup(function () {
                                let keyword = $(this).val().trim();
                                if (keyword.length > 0) {
                                    $.ajax({
                                        url: "SearchByAjax",
                                        method: "GET",
                                        data: {keyword: keyword},
                                        success: function (response) {
                                            $("#searchResults").html(response);
                                        }
                                    });
                                } else {
                                    // Hiển thị lại danh sách gốc nếu không có từ khóa tìm kiếm
                                    $.ajax({
                                        url: "homeblogseverlet",
                                        method: "GET",
                                        success: function (response) {
                                            $("#searchResults").html($(response).find("#searchResults").html());
                                        }
                                    });
                                }
                            });
                        });

                    </script>



                    <div id="searchResults" class="row">
                        <c:if test="${empty blogs}">
                            <div class="col-12">
                                <p style="color: red;">⚠️ No blogs available!</p>
                            </div>
                        </c:if>

                        <c:forEach var="blog" items="${blogs}">
                            <div class="col-sm-6 col-md-6 col-lg-4">
                                <div class="blog grid-blog">
                                    <div class="blog-image">
                                        <a href="blogdetail?postId=${blog.post_id}">
                                            <img style="width: 470px; height: 315px;" class="img-fluid" src="${blog.image}" alt="${blog.title}">
                                        </a>
                                    </div>
                                    <div class="blog-content">
                                        <h3 class="blog-title">
                                            <a href="blogdetail?postId=${blog.post_id}">${blog.title}</a>
                                        </h3>
                                        <p>${blog.content}</p>
                                        <a href="blogdetail?postId=${blog.post_id}" class="read-more">
                                            <i class="fa fa-long-arrow-right"></i> Read More
                                        </a>
                                    </div>
                                    <a href="editblog?postId=${blog.post_id}" class="btn btn-warning">
                                        Update
                                    </a>
                                </div>
                            </div>
                        </c:forEach>
                    </div>

                    <!-- Pagination -->
                    <div class="pagination">
                        <c:if test="${totalPages > 1}">
                            <ul class="pagination justify-content-center">
                                <!-- Previous Link -->
                                <c:if test="${currentPage > 1}">
                                    <li class="page-item">
                                        <a class="page-link" href="homeblogseverlet?page=${currentPage - 1}">Previous</a>
                                    </li>
                                </c:if>

                                <!-- First Page -->
                                <li class="page-item ${currentPage == 1 ? 'active' : ''}">
                                    <a class="page-link" href="homeblogseverlet?page=1">1</a>
                                </li>

                                <!-- Ellipsis and Middle Pages -->
                                <c:if test="${currentPage > 3 && totalPages > 5}">
                                    <li class="page-item disabled">
                                        <span class="page-link">...</span>
                                    </li>
                                </c:if>

                                <!-- Pages Around Current Page -->
                                <c:forEach begin="${currentPage - 1}" end="${currentPage + 1}" var="i">
                                    <c:if test="${i > 1 && i < totalPages}">
                                        <li class="page-item ${i == currentPage ? 'active' : ''}">
                                            <a class="page-link" href="homeblogseverlet?page=${i}">${i}</a>
                                        </li>
                                    </c:if>
                                </c:forEach>

                                <!-- Ellipsis Before Last Page -->
                                <c:if test="${currentPage < totalPages - 2 && totalPages > 5}">
                                    <li class="page-item disabled">
                                        <span class="page-link">...</span>
                                    </li>
                                </c:if>

                                <!-- Last Page (if more than 1 page) -->
                                <c:if test="${totalPages > 1}">
                                    <li class="page-item ${currentPage == totalPages ? 'active' : ''}">
                                        <a class="page-link" href="homeblogseverlet?page=${totalPages}">${totalPages}</a>
                                    </li>
                                </c:if>

                                <!-- Next Link -->
                                <c:if test="${currentPage < totalPages}">
                                    <li class="page-item">
                                        <a class="page-link" href="homeblogseverlet?page=${currentPage + 1}">Next</a>
                                    </li>
                                </c:if>
                            </ul>
                        </c:if>
                    </div>
                </div>
            </div>

        </div>

        <div class="sidebar-overlay" data-reff=""></div>
        <script src="assets/js/jquery-3.2.1.min.js"></script>
        <script src="assets/js/popper.min.js"></script>
        <script src="assets/js/bootstrap.min.js"></script>
        <script src="assets/js/jquery.slimscroll.js"></script>
        <script src="assets/js/app.js"></script>
    </body>


    <!-- blog23:51-->
</html>
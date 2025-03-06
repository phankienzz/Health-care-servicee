<%-- 
    Document   : employees
    Created on : Feb 8, 2025, 10:15:46 PM
    Author     : Gigabyte
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">


    <!-- employees23:21-->
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">
        <link rel="shortcut icon" type="image/x-icon" href="assets/img/favicon.ico">
        <title>Preclinic - Medical & Hospital - Bootstrap 4 Admin Template</title>
        <link rel="stylesheet" type="text/css" href="assets/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="assets/css/font-awesome.min.css">
        <link rel="stylesheet" type="text/css" href="assets/css/dataTables.bootstrap4.min.css">
        <link rel="stylesheet" type="text/css" href="assets/css/select2.min.css">
        <link rel="stylesheet" type="text/css" href="assets/css/bootstrap-datetimepicker.min.css">
        <link rel="stylesheet" type="text/css" href="assets/css/style.css">
        <style>
            .pagination {
                float: right;
                margin: 0 0 5px;
            }
            .pagination li a {
                border: none;
                font-size: 13px;
                min-width: 30px;
                min-height: 30px;
                color: #999;
                margin: 0 2px;
                line-height: 30px;
                border-radius: 2px !important;
                text-align: center;
                padding: 0 6px;
            }
            .pagination li a:hover {
                color: #666;
            }
            .pagination li.active a, .pagination li.active a.page-link {
                background: #03A9F4;
            }
            .pagination li.active a:hover {
                background: #0397d6;
            }
            .pagination li.disabled i {
                color: #ccc;
            }
            .pagination li i {
                font-size: 16px;
                padding-top: 6px
            }
        </style>
    </head>

    <body>
        <div class="main-wrapper">
            <jsp:include page="headerStaff.jsp"></jsp:include>
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
                                    <a href="patient"><i class="fa fa-wheelchair"></i> <span>Patients</span></a>
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
                                        <li><a class="active" href="employees.html">Employees List</a></li>
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
                                        <li><a href="blog.html">Blog</a></li>
                                        <li><a href="blog-details.html">Blog View</a></li>
                                        <li><a href="add-blog.html">Add Blog</a></li>
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
                            <div class="col-sm-4 col-3">
                                <h4 class="page-title">Staff</h4> 
                            </div>
                        <c:forEach var="permission" items="${listPermission}">
                            <c:if test="${permission.permissionID == 23}">
                                <div class="col-sm-8 col-9 text-right m-b-20">
                                    <a href="addStaff" class="btn btn-primary float-right btn-rounded"><i class="fa fa-plus"></i>Add Staff</a>
                                </div>
                            </c:if>
                        </c:forEach>
                    </div>

                    <form action="staff" method="get">
                        <div class="row filter-row">
                            <div class="col-sm-6 col-md-3">
                                <div class="form-group form-focus">
                                    <label class="focus-label">Staff ID</label>
                                    <input name="staffID"   type="text" class="form-control floating" <c:if test="${staffID != null}"> value="${staffID}"</c:if>>
                                    </div>
                                </div>
                                <div class="col-sm-6 col-md-3">
                                    <div class="form-group form-focus">
                                        <label class="focus-label">Staff Name</label>
                                        <input name="name" type="text" class="form-control floating" <c:if test="${name != null}"> value="${name}"</c:if>>
                                    </div>
                                </div>
                                <div class="col-sm-6 col-md-3">
                                    <div class="form-group form-focus select-focus">
                                        <label class="focus-label">Role</label>
                                        <select name="roleID" class="select floating">
                                            <option value = "Select Role" >Select Role</option>
                                        <c:forEach var = "role" items="${listRole}">
                                            <option value="${role.roleID}" <c:if test="${roleID == role.roleID}"> selected</c:if>>${role.roleName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="col-sm-6 col-md-3">
                                <input type="submit" value="Search" class="btn btn-success btn-block"/>
                            </div>
                        </div>
                    </form>

                    <div class="row">
                        <div class="col-md-12">
                            <div class="table-responsive">
                                <table class="table table-striped custom-table  ">
                                    <thead>
                                        <tr>
                                            <th style="min-width:200px;">Name</th>
                                            <th>Staff ID</th>
                                            <th>Email</th>
                                            <th>Mobile</th>
                                            <th style="min-width: 110px;">Join Date</th>
                                            <th>Role</th>
                                                <c:set var="isChecked" value="false"/>
                                                <c:forEach var="permission" items="${listPermission}">
                                                    <c:if test="${permission.permissionID == 25 || permission.permissionID == 26}">
                                                        <c:set var="isChecked" value="true"/>
                                                    </c:if>
                                                </c:forEach>
                                                <c:if test="${isChecked}">
                                                <th class="text-right">Action</th>
                                                </c:if>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="staff" items="${listStaff}">
                                            <tr>
                                                <td>
                                                    <img width="28" height="28" src="assets/img/user.jpg" class="rounded-circle" alt=""> <h2>${staff.fullName}</h2>
                                                </td>
                                                <td>${staff.staffID}</td>
                                                <td>${staff.email}</td>
                                                <td>${staff.phone}</td>
                                                <td>${staff.hireDate}</td>
                                                <td>
                                                    <c:forEach var="role" items="${listRole}">
                                                        <c:if test = "${role.roleID == staff.roleID}">
                                                            <c:if test = "${staff.status == 'Active'}">
                                                                <span class="custom-badge status-green">${role.roleName}</span>
                                                            </c:if>
                                                            <c:if test = "${staff.status == 'Inactive'}">
                                                                <span class="custom-badge status-red">${role.roleName}</span>
                                                            </c:if>
                                                        </c:if>
                                                    </c:forEach>
                                                </td>
                                                <c:if test="${isChecked}">
                                                    <td class="text-right">
                                                        <div class="dropdown dropdown-action">
                                                            <a href="#" class="action-icon dropdown-toggle" data-toggle="dropdown" aria-expanded="false"><i class="fa fa-ellipsis-v"></i></a>
                                                            <div class="dropdown-menu dropdown-menu-right">
                                                                <c:forEach var="permission" items="${listPermission}">
                                                                    <c:if test="${permission.permissionID == 25}">
                                                                        <a class="dropdown-item" href="editStaff?staffID=${staff.staffID}"><i class="fa fa-pencil m-r-5"></i> Edit</a>
                                                                    </c:if>

                                                                    <c:if test="${permission.permissionID == 26}">
                                                                        <a class="dropdown-item" href="#" data-toggle="modal" data-target="#delete_employee" data-id="${staff.staffID}"><i class="fa fa-trash-o m-r-5"></i> Delete</a>
                                                                    </c:if>
                                                                </c:forEach>
                                                            </div>
                                                        </div>
                                                    </td>
                                                </c:if>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>

                    <div class="clearfix">
                        <div class="hint-text">Showing <b>${show}</b> out of <b>${size}</b> entries</div>
                        <ul class="pagination">
                            <c:if test="${currentPage != 1}">
                                <li class="page-item page-item ${currentPage == 1 ? 'disabled' : ''}"><a href="staff?page=${currentPage - 1}&staffID=${staffID}&name=${name}&roleID=${roleID}">Previous</a></li>
                                </c:if>

                            <c:forEach var="i" begin="1" end="${totalPages}">
                                <li class="page-item ${i == currentPage ? 'active' : ''}" ><a href="staff?page=${i}&staffID=${staffID}&name=${name}&roleID=${roleID}">${i}</a></li>
                                </c:forEach>
                                <c:if test="${currentPage != totalPages}">
                                <li class="page-item ${currentPage == totalPages ? 'disabled' : ''}"><a href="staff?page=${currentPage + 1}&staffID=${staffID}&name=${name}&roleID=${roleID}">Next</a></li>
                                </c:if>
                        </ul>
                    </div>
                </div>

                <div class="notification-box">
                    <div class="msg-sidebar notifications msg-noti">
                        <div class="topnav-dropdown-header">
                            <span>Messages</span>
                        </div>
                        <div class="drop-scroll msg-list-scroll" id="msg_list">
                            <ul class="list-box">
                                <li>
                                    <a href="chat.html">
                                        <div class="list-item">
                                            <div class="list-left">
                                                <span class="avatar">R</span>
                                            </div>
                                            <div class="list-body">
                                                <span class="message-author">Richard Miles </span>
                                                <span class="message-time">12:28 AM</span>
                                                <div class="clearfix"></div>
                                                <span class="message-content">Lorem ipsum dolor sit amet, consectetur adipiscing</span>
                                            </div>
                                        </div>
                                    </a>
                                </li>
                                <li>
                                    <a href="chat.html">
                                        <div class="list-item new-message">
                                            <div class="list-left">
                                                <span class="avatar">J</span>
                                            </div>
                                            <div class="list-body">
                                                <span class="message-author">John Doe</span>
                                                <span class="message-time">1 Aug</span>
                                                <div class="clearfix"></div>
                                                <span class="message-content">Lorem ipsum dolor sit amet, consectetur adipiscing</span>
                                            </div>
                                        </div>
                                    </a>
                                </li>
                                <li>
                                    <a href="chat.html">
                                        <div class="list-item">
                                            <div class="list-left">
                                                <span class="avatar">T</span>
                                            </div>
                                            <div class="list-body">
                                                <span class="message-author"> Tarah Shropshire </span>
                                                <span class="message-time">12:28 AM</span>
                                                <div class="clearfix"></div>
                                                <span class="message-content">Lorem ipsum dolor sit amet, consectetur adipiscing</span>
                                            </div>
                                        </div>
                                    </a>
                                </li>
                                <li>
                                    <a href="chat.html">
                                        <div class="list-item">
                                            <div class="list-left">
                                                <span class="avatar">M</span>
                                            </div>
                                            <div class="list-body">
                                                <span class="message-author">Mike Litorus</span>
                                                <span class="message-time">12:28 AM</span>
                                                <div class="clearfix"></div>
                                                <span class="message-content">Lorem ipsum dolor sit amet, consectetur adipiscing</span>
                                            </div>
                                        </div>
                                    </a>
                                </li>
                                <li>
                                    <a href="chat.html">
                                        <div class="list-item">
                                            <div class="list-left">
                                                <span class="avatar">C</span>
                                            </div>
                                            <div class="list-body">
                                                <span class="message-author"> Catherine Manseau </span>
                                                <span class="message-time">12:28 AM</span>
                                                <div class="clearfix"></div>
                                                <span class="message-content">Lorem ipsum dolor sit amet, consectetur adipiscing</span>
                                            </div>
                                        </div>
                                    </a>
                                </li>
                                <li>
                                    <a href="chat.html">
                                        <div class="list-item">
                                            <div class="list-left">
                                                <span class="avatar">D</span>
                                            </div>
                                            <div class="list-body">
                                                <span class="message-author"> Domenic Houston </span>
                                                <span class="message-time">12:28 AM</span>
                                                <div class="clearfix"></div>
                                                <span class="message-content">Lorem ipsum dolor sit amet, consectetur adipiscing</span>
                                            </div>
                                        </div>
                                    </a>
                                </li>
                                <li>
                                    <a href="chat.html">
                                        <div class="list-item">
                                            <div class="list-left">
                                                <span class="avatar">B</span>
                                            </div>
                                            <div class="list-body">
                                                <span class="message-author"> Buster Wigton </span>
                                                <span class="message-time">12:28 AM</span>
                                                <div class="clearfix"></div>
                                                <span class="message-content">Lorem ipsum dolor sit amet, consectetur adipiscing</span>
                                            </div>
                                        </div>
                                    </a>
                                </li>
                                <li>
                                    <a href="chat.html">
                                        <div class="list-item">
                                            <div class="list-left">
                                                <span class="avatar">R</span>
                                            </div>
                                            <div class="list-body">
                                                <span class="message-author"> Rolland Webber </span>
                                                <span class="message-time">12:28 AM</span>
                                                <div class="clearfix"></div>
                                                <span class="message-content">Lorem ipsum dolor sit amet, consectetur adipiscing</span>
                                            </div>
                                        </div>
                                    </a>
                                </li>
                                <li>
                                    <a href="chat.html">
                                        <div class="list-item">
                                            <div class="list-left">
                                                <span class="avatar">C</span>
                                            </div>
                                            <div class="list-body">
                                                <span class="message-author"> Claire Mapes </span>
                                                <span class="message-time">12:28 AM</span>
                                                <div class="clearfix"></div>
                                                <span class="message-content">Lorem ipsum dolor sit amet, consectetur adipiscing</span>
                                            </div>
                                        </div>
                                    </a>
                                </li>
                                <li>
                                    <a href="chat.html">
                                        <div class="list-item">
                                            <div class="list-left">
                                                <span class="avatar">M</span>
                                            </div>
                                            <div class="list-body">
                                                <span class="message-author">Melita Faucher</span>
                                                <span class="message-time">12:28 AM</span>
                                                <div class="clearfix"></div>
                                                <span class="message-content">Lorem ipsum dolor sit amet, consectetur adipiscing</span>
                                            </div>
                                        </div>
                                    </a>
                                </li>
                                <li>
                                    <a href="chat.html">
                                        <div class="list-item">
                                            <div class="list-left">
                                                <span class="avatar">J</span>
                                            </div>
                                            <div class="list-body">
                                                <span class="message-author">Jeffery Lalor</span>
                                                <span class="message-time">12:28 AM</span>
                                                <div class="clearfix"></div>
                                                <span class="message-content">Lorem ipsum dolor sit amet, consectetur adipiscing</span>
                                            </div>
                                        </div>
                                    </a>
                                </li>
                                <li>
                                    <a href="chat.html">
                                        <div class="list-item">
                                            <div class="list-left">
                                                <span class="avatar">L</span>
                                            </div>
                                            <div class="list-body">
                                                <span class="message-author">Loren Gatlin</span>
                                                <span class="message-time">12:28 AM</span>
                                                <div class="clearfix"></div>
                                                <span class="message-content">Lorem ipsum dolor sit amet, consectetur adipiscing</span>
                                            </div>
                                        </div>
                                    </a>
                                </li>
                                <li>
                                    <a href="chat.html">
                                        <div class="list-item">
                                            <div class="list-left">
                                                <span class="avatar">T</span>
                                            </div>
                                            <div class="list-body">
                                                <span class="message-author">Tarah Shropshire</span>
                                                <span class="message-time">12:28 AM</span>
                                                <div class="clearfix"></div>
                                                <span class="message-content">Lorem ipsum dolor sit amet, consectetur adipiscing</span>
                                            </div>
                                        </div>
                                    </a>
                                </li>
                            </ul>
                        </div>
                        <div class="topnav-dropdown-footer">
                            <a href="chat.html">See all messages</a>
                        </div>
                    </div>
                </div>
            </div>
            <div id="delete_employee" class="modal fade delete-modal" role="dialog">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content">
                        <div class="modal-body text-center">
                            <img src="assets/img/sent.png" alt="" width="50" height="46">
                            <h3>Are you sure want to delete this Employee?</h3>
                            <form id="deleteForm" action="deleteStaff" method="GET">
                                <input type="hidden" name="staffID" id="deleteStaffID">
                                <div class="m-t-20">
                                    <a href="#" class="btn btn-white" data-dismiss="modal">Close</a>
                                    <button type="submit" class="btn btn-danger">Delete</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="sidebar-overlay" data-reff=""></div>
        <script src="assets/js/jquery-3.2.1.min.js"></script>
        <script src="assets/js/popper.min.js"></script>
        <script src="assets/js/bootstrap.min.js"></script>
        <script src="assets/js/jquery.dataTables.min.js"></script>
        <script src="assets/js/dataTables.bootstrap4.min.js"></script>
        <script src="assets/js/jquery.slimscroll.js"></script>
        <script src="assets/js/select2.min.js"></script>
        <script src="assets/js/moment.min.js"></script>
        <script src="assets/js/bootstrap-datetimepicker.min.js"></script>
        <script src="assets/js/app.js"></script>
        <script>
            $(document).ready(function () {
                $('#delete_employee').on('show.bs.modal', function (event) {
                    var button = $(event.relatedTarget); // Lấy nút vừa bấm
                    var staffID = button.data('id'); // Lấy staffID từ data-id
                    $('#deleteStaffID').val(staffID); // Gán vào input trong form
                });
            });
        </script>
    </body>


    <!-- employees23:22-->
</html>
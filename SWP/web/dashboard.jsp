<%-- 
    Document   : dashboard
    Created on : Mar 5, 2025, 11:47:26 PM
    Author     : Hoang
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">


    <!-- index22:59-->
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">
        <link rel="shortcut icon" type="image/x-icon" href="assets/img/favicon.ico">
        <title>Preclinic - Medical & Hospital - Bootstrap 4 Admin Template</title>
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="assets/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="assets/css/font-awesome.min.css">
        <link rel="stylesheet" type="text/css" href="assets/css/style.css">
        <!--[if lt IE 9]>
                    <script src="assets/js/html5shiv.min.js"></script>
                    <script src="assets/js/respond.min.js"></script>
            <![endif]-->
    </head>

    <body>
        <div class="main-wrapper">
            <jsp:include page="headerStaff.jsp"></jsp:include>
            <jsp:include page="sidebar.jsp"></jsp:include>
                <div class="page-wrapper">
                    <div class="content">
                        <div class="row">
                            <div class="col-md-6 col-sm-6 col-lg-6 col-xl-3">
                                <div class="dash-widget">
                                    <span class="dash-widget-bg2"><i class="fa fa-user-o"></i></span>
                                    <div class="dash-widget-info text-right">
                                        <h3>${visitCount}</h3>
                                    <span class="widget-title2">Visits <i class="fa fa-check" aria-hidden="true"></i></span>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6 col-sm-6 col-lg-6 col-xl-3">
                            <div class="dash-widget">
                                <span class="dash-widget-bg1"><i class="fa fa-stethoscope" aria-hidden="true"></i></span>
                                <div class="dash-widget-info text-right">
                                    <h3>${docCount}</h3>
                                    <span class="widget-title1">Doctors <i class="fa fa-check" aria-hidden="true"></i></span>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6 col-sm-6 col-lg-6 col-xl-3">
                            <div class="dash-widget">
                                <span class="dash-widget-bg3"><i class="fa fa-user-md" aria-hidden="true"></i></span>
                                <div class="dash-widget-info text-right">
                                    <h3>72</h3>
                                    <span class="widget-title3">Attend <i class="fa fa-check" aria-hidden="true"></i></span>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6 col-sm-6 col-lg-6 col-xl-3">
                            <div class="dash-widget">
                                <span class="dash-widget-bg4"><i class="fa fa-heartbeat" aria-hidden="true"></i></span>
                                <div class="dash-widget-info text-right">
                                    <h3>${pending}</h3>
                                    <span class="widget-title4">Pending <i class="fa fa-check" aria-hidden="true"></i></span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12 col-md-6 col-lg-6 col-xl-6">
                            <div class="card">
                                <div class="card-body">
                                    <div class="chart-title">
                                        <h4>Patient Total</h4>
                                        <span class="float-right"><i class="fa fa-caret-up" aria-hidden="true"></i> 15% Higher than Last Month</span>
                                    </div>	
                                    <canvas id="linegraph"></canvas>
                                </div>
                            </div>
                        </div>
                        <div class="col-12 col-md-6 col-lg-6 col-xl-6">
                            <div class="card">
                                <div class="card-body">
                                    <div class="chart-title">
                                        <h4>Patients In</h4>
                                        <div class="float-right">
                                            <ul class="chat-user-total">
                                                <li><i class="fa fa-circle current-users" aria-hidden="true"></i>ICU</li>
                                                <li><i class="fa fa-circle old-users" aria-hidden="true"></i> OPD</li>
                                            </ul>
                                        </div>
                                    </div>	
                                    <canvas id="bargraph"></canvas>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12 col-md-6 col-lg-8 col-xl-8">
                            <div class="card">
                                <div class="card-header">
                                    <h4 class="card-title d-inline-block">Upcoming Appointments</h4> <a href="appointments.html" class="btn btn-primary float-right">View all</a>
                                </div>
                                <div class="card-body p-0">
                                    <div class="table-responsive" style="max-height: 350px; overflow-y: auto;">
                                        <table class="table mb-0">
                                            <thead class="d-none">
                                                <tr>
                                                    <th>Patient Name</th>
                                                    <th>Doctor Name</th>
                                                    <th>Timing</th>
                                                    <th class="text-right">Status</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach var="apm" items="${listAppointment}">
                                                    <tr>
                                                        <td style="min-width: 200px;">
                                                            <a class="avatar" href="profile.html">B</a>
                                                            <h2><a href="profile.html">${apm.customerId.fullName}<span>${apm.customerId.address}</span></a></h2>
                                                        </td>                 
                                                        <td>
                                                            <h5 class="time-title p-0">Appointment With</h5>
                                                            <p>${apm.consultantId.fullName}</p>
                                                        </td>
                                                        <td>
                                                            <h5 class="time-title p-0">Timing</h5>
                                                            <p>${apm.examinationDate}</p>
                                                        </td>
                                                        <td class="text-right">
                                                            <a href="appointments.html" class="btn btn-outline-primary take-btn">Take up</a>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-12 col-md-6 col-lg-4 col-xl-4">
                            <div class="card member-panel">
                                <div class="card-header bg-white">
                                    <h4 class="card-title mb-0">Doctors</h4>
                                </div>
                                <div class="card-body">
                                    <ul class="contact-list">
                                        <c:forEach var="doc" items="${listDoctor}">
                                            <li>
                                                <div class="contact-cont">
                                                    <div class="float-left user-img m-r-10">
                                                        <a href="profile.html" title="${doc.getName()}"><img src="assets/img/user.jpg" alt="" class="w-40 rounded-circle"><span class="status online"></span></a>
                                                    </div>
                                                    <div class="contact-info">
                                                        <span class="contact-name text-ellipsis">${doc.getName()}</span>
                                                        <span class="contact-date">${doc.qualification}</span>
                                                    </div>
                                                </div>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                </div>
                                <div class="card-footer text-center bg-white">
                                    <a href="doctors.html" class="text-muted">View all Doctors</a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12 col-md-6 col-lg-8 col-xl-8">
                            <div class="card">
                                <div class="card-header">
                                    <h4 class="card-title d-inline-block">New Patients </h4> <a href="patients.html" class="btn btn-primary float-right">View all</a>
                                </div>
                                <div class="card-block">
                                    <div class="table-responsive">
                                        <table class="table mb-0 new-patient-table">
                                            <tbody>
                                                <c:forEach var="cus" items="${listCustomer}">
                                                    <tr>
                                                        <td>
                                                            <c:choose>
                                                                <c:when test="${cus.customerID != null}">                                                
                                                                    <img width="28" height="28" class="rounded-circle" src="pictureprofile?customerID=${cus.customerID}" alt=""> 
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <img width="28" height="28" class="rounded-circle" src="assets/img/user.jpg" alt=""> 
                                                                </c:otherwise>
                                                            </c:choose>                                                                    
                                                            <h2>${cus.fullName}</h2>
                                                        </td>
                                                        <td>${cus.email}</td>
                                                        <td>${cus.phone}</td>
                                                        <td>${cus.gender}</td>
                                                        <!--<td><button class="btn btn-primary btn-primary-four float-right">Fever</button></td>-->
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-12 col-md-6 col-lg-4 col-xl-4">
                            <div class="hospital-barchart">
                                <h4 class="card-title d-inline-block">Hospital Management</h4>
                            </div>
                            <div class="bar-chart">
                                <div class="legend">
                                    <div class="item">
                                        <h4>Level1</h4>
                                    </div>

                                    <div class="item">
                                        <h4>Level2</h4>
                                    </div>
                                    <div class="item text-right">
                                        <h4>Level3</h4>
                                    </div>
                                    <div class="item text-right">
                                        <h4>Level4</h4>
                                    </div>
                                </div>
                                <div class="chart clearfix">
                                    <div class="item">
                                        <div class="bar">
                                            <span class="percent">16%</span>
                                            <div class="item-progress" data-percent="16">
                                                <span class="title">OPD Patient</span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="item">
                                        <div class="bar">
                                            <span class="percent">71%</span>
                                            <div class="item-progress" data-percent="71">
                                                <span class="title">New Patient</span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="item">
                                        <div class="bar">
                                            <span class="percent">82%</span>
                                            <div class="item-progress" data-percent="82">
                                                <span class="title">Laboratory Test</span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="item">
                                        <div class="bar">
                                            <span class="percent">67%</span>
                                            <div class="item-progress" data-percent="67">
                                                <span class="title">Treatment</span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="item">
                                        <div class="bar">
                                            <span class="percent">30%</span>									
                                            <div class="item-progress" data-percent="30">
                                                <span class="title">Discharge</span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
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
        </div>
        <div class="sidebar-overlay" data-reff=""></div>
        <script src="assets/js/jquery-3.2.1.min.js"></script>
        <script src="assets/js/popper.min.js"></script>
        <script src="assets/js/bootstrap.min.js"></script>
        <script src="assets/js/jquery.slimscroll.js"></script>
        <script src="assets/js/Chart.bundle.js"></script>

        <!-- 2 cái bảng thống kê-->
        <!--<script src="assets/js/chart.js"></script>-->

        <script>
            $(document).ready(function () {

// Bar Chart    
                var barChartData = {
                    labels: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul'], // Đảm bảo đủ số lượng nhãn
                    datasets: [{
                            label: 'Dataset 1',
                            backgroundColor: 'rgba(0, 158, 251, 0.5)',
                            borderColor: 'rgba(0, 158, 251, 1)',
                            borderWidth: 1,
                            data: [35, 59, 80, 81, 56, 55, 40] // Đảm bảo đúng số lượng giá trị
                        }, {
                            label: 'Dataset 2',
                            backgroundColor: 'rgba(255, 188, 53, 0.5)',
                            borderColor: 'rgba(255, 188, 53, 1)',
                            borderWidth: 1,
                            data: [28, 48, 40, 19, 86, 27, 90] // Đảm bảo đúng số lượng giá trị
                        }]
                };

                var ctx = document.getElementById('bargraph').getContext('2d');
                window.myBar = new Chart(ctx, {
                    type: 'bar',
                    data: barChartData,
                    options: {
                        responsive: true,
                        plugins: {
                            legend: {
                                display: true, // Hiển thị chú thích cho dễ hiểu
                            }
                        }
                    }
                });


                // Line Chart
                var lineChartData = {
                    labels: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"],
                    datasets: [{
                            label: "My First dataset",
                            backgroundColor: "rgba(0, 158, 251, 0.5)",
                            data: [90, 70, 20, 100, 120, 50, 70, 50, 50, 100, 50, 90]
                        }, {
                            label: "My Second dataset",
                            backgroundColor: "rgba(255, 188, 53, 0.5)",
                            fill: true,
                            data: [28, 48, 40, 19, 86, 27, 20, 90, 50, 20, 90, 20]
                        }]
                };

                var linectx = document.getElementById('linegraph').getContext('2d');
                window.myLine = new Chart(linectx, {
                    type: 'line',
                    data: lineChartData,
                    options: {
                        responsive: true,
                        legend: {
                            display: false,
                        },
                        tooltips: {
                            mode: 'index',
                            intersect: false,
                        }
                    }
                });

                // Bar Chart 2
                barChart();

                $(window).resize(function () {
                    barChart();
                });

                function barChart() {
                    $('.bar-chart').find('.item-progress').each(function () {
                        var itemProgress = $(this),
                                itemProgressWidth = $(this).parent().width() * ($(this).data('percent') / 100);
                        itemProgress.css('width', itemProgressWidth);
                    });
                }
                ;
            });
        </script>

        <script src="assets/js/app.js"></script>

    </body>


    <!-- index22:59-->
</html>

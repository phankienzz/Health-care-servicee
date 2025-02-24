<%-- 
    Document   : edit-profile
    Created on : Jan 14, 2025, 11:59:11 PM
    Author     : jaxbo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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


        <script>
            document.addEventListener("DOMContentLoaded", function () {
                const fileInput = document.getElementById("profileImage");

                fileInput.addEventListener("change", function () {
                    const file = fileInput.files[0];

                    if (file) {
                        const fileType = file.type;
                        const allowedTypes = ["image/png", "image/jpeg", "image/gif"];

                        if (!allowedTypes.includes(fileType)) {
                            alert("Only PNG, JPEG, and GIF files are allowed!");
                            fileInput.value = ''; // Clear the file input
                        }
                    }
                });
            });


        </script>


        <jsp:include page="editseting.jsp"></jsp:include>

            <div class="page-wrapper-profile">
                <div class="content">
                    <div class="row">
                        <div class="col-sm-12">
                            <h4 class="page-title">Edit Profile</h4>
                        </div>
                    </div>
                <c:if test="${sessionScope.customerAccount != null}">
                    <form action="editprofile" method="post" enctype="multipart/form-data">
                        <div class="card-box">
                            <h3 class="card-title">Basic Informations</h3>
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="profile-img-wrap">
                                        <img class="inline-block" src="pictureprofile?customerID=${sessionScope.customerAccount.customerID}" >
                                        <div class="fileupload btn">
                                            <span class="btn-text">Edit</span>
                                            <input class="upload" type="file" name="profileImage" id="profileImage">
                                        </div>
                                    </div>
                                    <div class="profile-basic">
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group form-focus">
                                                    <label class="focus-label">Full Name</label>
                                                    <input type="text" class="form-control floating" name="fullName" value="${sessionScope.customerAccount.fullName}">
                                                </div>
                                            </div>

                                            <div class="col-sm-6">
                                                <div class="form-group">
                                                    <label>Birth Date <span class="text-danger">*</span></label>
                                                    <div class="cal-icon">
                                                        <!-- Set the value of the input field dynamically -->
                                                        <input name="dateOfBirth" class="form-control datetimepicker" type="text"
                                                               value="${sessionScope.customerAccount.dateOfBirth != null ? sessionScope.customerAccount.dateOfBirth : ''}" />
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
                                            <div class="col-md-6">
                                                <div class="form-group form-focus">
                                                    <label class="focus-label">Email</label>
                                                    <input type="text" class="form-control floating" name="email" value="${sessionScope.customerAccount.email}" >

                                                </div>
                                                <div><p class="text-danger">${error}</p>
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
                                        <input type="text" class="form-control floating" name="address" value="${sessionScope.customerAccount.address}">
                                    </div>
                                </div>

                                <div class="col-md-12">
                                    <div class="form-group form-focus">
                                        <label class="focus-label">Phone Number</label>
                                        <input type="text" class="form-control floating" name="phone" value="${sessionScope.customerAccount.phone}">
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="text-center m-t-20">
                            <button class="btn btn-primary submit-btn" type="submit">Save</button>
                        </div>
                    </form>
                </c:if>
                <c:if test="${sessionScope.staffAccount != null}">
                    <form action="profileStaff" method="post">
                        <div class="card-box">
                            <h3 class="card-title">Basic Informations</h3>
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="profile-img-wrap">
                                        <img class="inline-block" src="assets/img/user.jpg" alt="user">
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
                                                    <input type="text" class="form-control floating" name="fullName" value="${sessionScope.staffAccount.fullName}">
                                                </div>
                                            </div>

                                            <div class="col-md-6">
                                                <div class="form-group form-focus">
                                                    <label class="focus-label">Birth Date</label>
                                                    <div class="cal-icon">
                                                        <input class="form-control floating datetimepicker" type="text" name="dateOfBirth"  value="${sessionScope.staffAccount.dateOfBirth}">
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="col-md-6">
                                                <div class="form-group form-focus select-focus">
                                                    <label class="focus-label">Gender</label>
                                                    <select class="select form-control floating" name="gender">
                                                        <option value="Male" ${sessionScope.staffAccount.gender == "Male" ? "selected" : ""}>Male</option>
                                                        <option value="Female" ${sessionScope.staffAccount.gender == "Female" ? "selected" : ""}>Female</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group form-focus">
                                                    <label class="focus-label">Email</label>
                                                    <input type="text" class="form-control floating" name="email" value="${sessionScope.staffAccount.email}">
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
                                        <input type="text" class="form-control floating" name="address" value="${sessionScope.staffAccount.address}">
                                    </div>
                                </div>
                                <!--                            <div class="col-md-6">
                                                                <div class="form-group form-focus">
                                                                    <label class="focus-label">Country</label>
                                                                    <input type="text" class="form-control floating" value="United States">
                                                                </div>
                                                            </div>-->
                                <div class="col-md-12">
                                    <div class="form-group form-focus">
                                        <label class="focus-label">Phone Number</label>
                                        <input type="text" class="form-control floating" name="phone" value="${sessionScope.staffAccount.phone}">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <c:if test="${error != null}">
                            <h4><i style="color: red">${error}</i></h4>
                            </c:if>
                            <c:if test="${mess != null}">
                            <h4><i style="color: green">${mess}</i></h4>
                            </c:if>
                        <div class="text-center m-t-20">
                            <button class="btn btn-primary submit-btn" type="submit">Save</button>
                        </div>
                    </form>

                </c:if>
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

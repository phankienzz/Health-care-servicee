<%-- 
    Document   : manage-doctor
    Created on : Feb 21, 2025, 8:17:28 PM
    Author     : Win11
--%>
<%@ page import=" java.util.List, model.Professional,dao.ProfessionalDAO" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    if (session.getAttribute("professionals") != null){
    session.removeAttribute("professionals");
    }
    if (session.getAttribute("professionals") == null) {
        ProfessionalDAO dao = new ProfessionalDAO();
        List<Professional> professionals = dao.getAllProfessionals();
        session.setAttribute("professionals", professionals);
    }
%>
<!DOCTYPE html>
<html lang="en">


    <!-- doctors23:12-->
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
        <link rel="stylesheet" type="text/css" href="css/pagination.css">
        <!--[if lt IE 9]>
                    <script src="assets/js/html5shiv.min.js"></script>
                    <script src="assets/js/respond.min.js"></script>
            <![endif]-->
        <style>
            .doctor-item {
                margin-bottom: 30px;
            }

            .profile-widget {
                width: 100%;
                height: 100%;
                display: flex;
                flex-direction: column;
                justify-content: space-between;
            }

            .doctor-img img {
                width: 100px;
                height: 100px;
                border-radius: 50%;
                object-fit: cover; /* Đảm bảo ảnh không bị méo */
            }

            .doctor-name {
                font-size: 18px;
                font-weight: bold;
                white-space: nowrap;
                overflow: hidden;
                text-overflow: ellipsis;
                max-width: 90%;
            }

            .doc-prof, .user-country {
                font-size: 14px;
                color: #666;
                white-space: nowrap;
                overflow: hidden;
                text-overflow: ellipsis;
                max-width: 90%;
            }

            .user-country {
                display: flex;
                align-items: center;
                justify-content: center;
                gap: 5px;
            }

            .profile-action {
                position: absolute;
                top: 10px;
                right: 10px;
            }

        </style>
    </head>

    <body>
        <div class="main-wrapper">
            <jsp:include page="headerStaff.jsp"></jsp:include>
            <jsp:include page="sidebar.jsp"></jsp:include>
                <div class="page-wrapper">
                    <div class="content">
                        <div class="row">
                            <div class="col-sm-4 col-3">
                                <h4 class="page-title">Doctors</h4>
                            </div>
                        </div>

                        <!-- Search & Filter -->
                        <div class="row mb-3">
                            <div class="col-md-6">
                                <input type="text" id="searchName" class="form-control" placeholder="Search by name...">
                            </div>
                            <div class="col-md-6">
                                <div class="filter-options">
                                    <label><strong>Filter by Specialization:</strong></label><br>
                                <c:forEach var="specialization" items="${sessionScope.specializations}">
                                    <label class="mr-3">
                                        <input type="checkbox" class="filter-specialization" value="${specialization}"> 
                                        ${specialization}
                                    </label>
                                </c:forEach>
                            </div>
                        </div>
                    </div>

                       
                    <!-- Doctor Grid -->
                    <div class="row doctor-grid" id="doctorList">
                        <c:forEach var="professional" items="${sessionScope.professionals}">
                            <div class="col-md-4 col-sm-4 col-lg-3 doctor-item " data-name="${professional.getName().toLowerCase()}" data-specialization="${professional.getSpecialization()}">
                                <div class="profile-widget">
                                    <div class="doctor-img">
                                        <a class="avatar" href="DetailDoctorServlet?id=${professional.getStaffID()}">

                                            <img src="assets/img/${professional.getPicture()}" alt="" class="img-fluid"/>
                                        </a>
                                    </div>
                                    <div class="dropdown profile-action">
                                        <a href="#" class="action-icon dropdown-toggle" data-toggle="dropdown">
                                            <i class="fa fa-ellipsis-v"></i>
                                        </a>
                                        <div class="dropdown-menu dropdown-menu-right">
                                            <a class="dropdown-item" href="EditProfessionalServlet?id=${professional.getStaffID()}">
                                                <i class="fa fa-pencil m-r-5"></i> Edit
                                            </a>
                                            <a class="dropdown-item" href="DeleteProfessionalServlet?id=${professional.getStaffID()}" 
                                               onclick="return confirm('Are you sure you want to delete this professional?');">
                                                <i class="fa fa-trash-o m-r-5"></i> Delete
                                            </a>
                                        </div>
                                    </div>
                                    <h4 class="doctor-name text-ellipsis">
                                        <a href="DetailDoctorServlet?id=${professional.getStaffID()}">${professional.getName()}</a>
                                    </h4>
                                    <div class="doc-prof">${professional.getSpecialization()}</div>
                                    <div class="user-country">
                                        <i class="fa fa-map-marker"></i>
                                        ${professional.getAddress()}, ${professional.getBiography()}
                                    </div>
                                </div>
                            </div>
                        </c:forEach>

                    </div>
                    </div>
                    <div id="pagination-container" class="pagination text-center"></div>
                </div>
            </div>

            <!-- JavaScript for Filtering -->
            <script>
                document.addEventListener("DOMContentLoaded", function () {
                    const searchInput = document.getElementById("searchName");
                    const specializationFilters = document.querySelectorAll(".filter-specialization");
                    const doctorItems = document.querySelectorAll(".doctor-item");

                    function filterDoctors() {
                        const searchText = searchInput.value.toLowerCase();
                        const selectedSpecializations = Array.from(specializationFilters)
                                .filter(checkbox => checkbox.checked)
                                .map(checkbox => checkbox.value);

                        doctorItems.forEach(item => {
                            const name = item.getAttribute("data-name");
                            const specialization = item.getAttribute("data-specialization");

                            const matchesName = name.includes(searchText);
                            const matchesSpecialization = selectedSpecializations.length === 0 || selectedSpecializations.includes(specialization);

                            item.style.display = (matchesName && matchesSpecialization) ? "block" : "none";
                        });
                    }

                    searchInput.addEventListener("input", filterDoctors);
                    specializationFilters.forEach(checkbox => checkbox.addEventListener("change", filterDoctors));
                });
            </script>
            
        </div>
        <div id="delete_doctor" class="modal fade delete-modal" role="dialog">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-body text-center">
                        <img src="assets/img/sent.png" alt="" width="50" height="46">
                        <h3>Are you sure want to delete this Doctor?</h3>
                        <div class="m-t-20"> <a href="#" class="btn btn-white" data-dismiss="modal">Close</a>
                            <button type="submit" class="btn btn-danger">Delete</button>
                        </div>
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
    <script src="assets/js/select2.min.js"></script>
    <script src="assets/js/moment.min.js"></script>
    <script src="assets/js/bootstrap-datetimepicker.min.js"></script>
    <script src="assets/js/app.js"></script>
    <script src="js/pagination.js"></script>
    <script>
               document.addEventListener("DOMContentLoaded", function () {
                   paginateItems('.card', 8, '#pagination-container');
               });
    </script>
</body>


<!-- doctors23:17-->
</html>

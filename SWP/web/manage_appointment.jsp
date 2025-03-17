<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">
        <link rel="shortcut icon" type="image/x-icon" href="assets/img/favicon.ico">
        <title>Preclinic - Medical & Hospital - Bootstrap 4 Admin Template</title>
        <link rel="stylesheet" type="text/css" href="assets/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="assets/css/font-awesome.min.css">
        <link rel="stylesheet" type="text/css" href="assets/css/select2.min.css">
        <!-- Thêm CSS cho Datetimepicker -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker.min.css">
        <link rel="stylesheet" type="text/css" href="assets/css/style.css">
        <!--[if lt IE 9]>
            <script src="assets/js/html5shiv.min.js"></script>
            <script src="assets/js/respond.min.js"></script>
        <![endif]-->
    </head>
    <body>
        <div class="main-wrapper">
            <!-- Header -->
            <jsp:include page="headerStaff.jsp"></jsp:include>
            <jsp:include page="sidebar.jsp"></jsp:include>

                <!-- Page Content -->
                <div class="page-wrapper">
                    <div class="content">
                        <div class="row">
                            <div class="col-sm-4 col-3">
                                <h4 class="page-title">Appointments</h4>
                            </div>
                            <div class="col-sm-8 col-9 text-right m-b-20">
                                <a href="add-appointment.jsp" class="btn btn btn-primary btn-rounded float-right"><i class="fa fa-plus"></i> Add Appointment</a>
                            </div>
                        </div>

                    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
                    <div class="container">
                        <h2>Medical Examinations</h2>

                        <!-- Form tìm ki?m và l?c -->
                        <form method="get" action="manage_appointment" class="mb-4">
                            <div class="row">
                                <div class="col-md-3">
                                    <label for="patientName">Patient Name</label>
                                    <input type="text" class="form-control" id="patientName" name="patientName" value="${param.patientName}" placeholder="Enter patient name">
                                </div>
                                <div class="col-md-2">
                                    <label for="ageSort">Sort by Age</label>
                                    <select class="form-control" id="ageSort" name="ageSort">
                                        <option value="">No Sort</option>
                                        <option value="asc" ${param.ageSort == 'asc' ? 'selected' : ''}>Ascending</option>
                                        <option value="desc" ${param.ageSort == 'desc' ? 'selected' : ''}>Descending</option>
                                    </select>
                                </div>

                                <div class="col-md-2">
                                    <label for="appointmentDate">Appointment Date</label>
                                    <input type="text" class="form-control" id="appointmentDate" name="appointmentDate" value="${param.appointmentDate}">
                                </div>
                                <div class="col-md-2">
                                    <label for="timeCreatedSort">Sort by Time Created</label>
                                    <select class="form-control" id="timeCreatedSort" name="timeCreatedSort">
                                        <option value="">No Sort</option>
                                        <option value="latest" ${param.timeCreatedSort == 'latest' ? 'selected' : ''}>Latest First</option>
                                        <option value="oldest" ${param.timeCreatedSort == 'oldest' ? 'selected' : ''}>Oldest First</option>
                                    </select>
                                </div>
                                <div class="col-md-2">
                                    <label for="status">Status</label>
                                    <select class="form-control" id="status" name="status">
                                        <option value="">All Status</option>
                                        <option value="Pending" ${param.status == 'Pending' ? 'selected' : ''}>Pending</option>
                                        <option value="In process" ${param.status == 'In process' ? 'selected' : ''}>In process</option>
                                        <option value="Confirmed" ${param.status == 'Confirmed' ? 'selected' : ''}>Confirmed</option>
                                        <option value="Completed" ${param.status == 'Completed' ? 'selected' : ''}>Completed</option>
                                        <option value="Rejected" ${param.status == 'Rejected' ? 'selected' : ''}>Rejected</option>
                                    </select>
                                </div>
                            </div>
                            <div class="text-center mt-3">
                                <button type="submit" class="btn btn-primary">Apply Filters</button>
                            </div>
                        </form>

                        <!-- B?ng danh sách -->
                        <div class="row">
                            <div class="col-md-12">
                                <div class="table-responsive">
                                    <table class="table table-striped custom-table">
                                        <thead>
                                            <tr>
                                                <th>Appointment ID</th>
                                                <th>Patient Name</th>
                                                <th>Age</th>
                                                <th>Doctor Name</th>
                                                <th>Service</th>
                                                <th>Appointment Date</th>
                                                <th>Time Created</th>
                                                <th>Status</th>
                                                <th class="text-right">Action</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:if test="${list == null || empty list}">
                                                <tr><td colspan="9">No data available</td></tr>
                                            </c:if>
                                            <c:forEach var="exam" items="${list}">
                                                <tr>
                                                    <td>${exam.examinationID}</td>
                                                    <td>${exam.customerId.fullName}</td>
                                                    <td>${exam.customerId.dateOfBirth}</td>
                                                    <td>${exam.consultantId.fullName}</td>
                                                    <td>
                                                        <c:forEach var="service" items="${exam.list}">
                                                            <span>${service.packageName}</span><br>
                                                        </c:forEach>
                                                    </td>
                                                    <td>${exam.examinationDate}</td>
                                                    <td>${exam.createdAt}</td>
                                                    <td>
                                                        <c:choose>
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
                                                        </c:choose>
                                                    </td>
                                                    <td class="text-right">
                                                        <div class="dropdown dropdown-action">
                                                            <a href="#" class="action-icon dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                                                                <i class="fa fa-ellipsis-v"></i>
                                                            </a>
                                                            <div class="dropdown-menu dropdown-menu-right">
                                                                <a class="dropdown-item" href="#" data-toggle="modal" 
                                                                   data-target="#medicalRecordModal"
                                                                   onclick="openMedicalRecordModal(${exam.examinationID})">
                                                                    <i class="fa fa-pencil m-r-5"></i> Create/Edit Medical Record
                                                                </a>
                                                                <a class="dropdown-item" href="#" data-toggle="modal" 
                                                                   data-target="#delete_appointment" 
                                                                   onclick="setDeleteId(${exam.examinationID})">
                                                                    <i class="fa fa-trash-o m-r-5"></i> Delete
                                                                </a>
                                                            </div>
                                                        </div>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div class="modal fade" id="medicalRecordModal" tabindex="-1" role="dialog" aria-hidden="true">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title">Medical Record</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <form id="medicalRecordForm" action="saveMedicalRecord" method="POST">
                                            <input type="hidden" id="examinationID" name="examinationID">

                                            <div class="form-group">
                                                <label>Diagnosis</label>
                                                <textarea class="form-control" id="diagnosis" name="diagnosis" required></textarea>
                                            </div>

                                            <div class="form-group">
                                                <label>Treatment Plan</label>
                                                <textarea class="form-control" id="treatmentPlan" name="treatmentPlan" required></textarea>
                                            </div>

                                            <div class="form-group">
                                                <label>Medications Prescribed</label>
                                                <textarea class="form-control" id="medicationsPrescribed" name="medicationsPrescribed"></textarea>
                                            </div>

                                            <button type="submit" class="btn btn-primary" >Save</button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- Phân trang -->
                        <c:if test="${totalPages > 1}">
                            <nav aria-label="Page navigation" class="mt-3">
                                <ul class="pagination justify-content-center">
                                    <li class="page-item ${currentPage == 1 ? 'disabled' : ''}">
                                        <a class="page-link" href="manage_appointment?page=${currentPage - 1}&patientName=${param.patientName}&ageSort=${param.ageSort}&doctorName=${param.doctorName}&appointmentDate=${param.appointmentDate}&timeCreatedSort=${param.timeCreatedSort}&status=${param.status}" aria-label="Previous">
                                            <span aria-hidden="true">«</span>
                                        </a>
                                    </li>
                                    <c:forEach begin="1" end="${totalPages}" var="i">
                                        <li class="page-item ${currentPage == i ? 'active' : ''}">
                                            <a class="page-link" href="manage_appointment?page=${i}&patientName=${param.patientName}&ageSort=${param.ageSort}&doctorName=${param.doctorName}&appointmentDate=${param.appointmentDate}&timeCreatedSort=${param.timeCreatedSort}&status=${param.status}">${i}</a>
                                        </li>
                                    </c:forEach>
                                    <li class="page-item ${currentPage == totalPages ? 'disabled' : ''}">
                                        <a class="page-link" href="manage_appointment?page=${currentPage + 1}&patientName=${param.patientName}&ageSort=${param.ageSort}&doctorName=${param.doctorName}&appointmentDate=${param.appointmentDate}&timeCreatedSort=${param.timeCreatedSort}&status=${param.status}" aria-label="Next">
                                            <span aria-hidden="true">»</span>
                                        </a>
                                    </li>
                                </ul>
                            </nav>
                        </c:if>

                        <!-- Delete Modal -->
                        <div id="delete_appointment" class="modal fade delete-modal" role="dialog">
                            <div class="modal-dialog modal-dialog-centered">
                                <div class="modal-content">
                                    <div class="modal-body text-center">
                                        <img src="assets/img/sent.png" alt="" width="50" height="46">
                                        <h3>Are you sure you want to delete this Appointment?</h3>
                                        <form id="deleteForm" action="deleteappointment" method="POST">
                                            <input type="hidden" id="deleteExamId" name="examinationID">
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
                </div>
            </div>
        </div>
        <div class="sidebar-overlay" data-reff=""></div>

        <!-- Scripts -->
        <script src="assets/js/jquery-3.2.1.min.js"></script>
        <script src="assets/js/popper.min.js"></script>
        <script src="assets/js/bootstrap.min.js"></script>
        <script src="assets/js/jquery.slimscroll.js"></script>
        <script src="assets/js/select2.min.js"></script>
        <!-- Thêm Moment.js và Bootstrap Datetimepicker -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/js/bootstrap-datetimepicker.min.js"></script>
        <script src="assets/js/app.js"></script>
        <script>
                                                $(document).ready(function () {
                                                    // Kh?i t?o datetimepicker cho appointmentDate
                                                    $('#appointmentDate').datetimepicker({
                                                        format: 'DD/MM/YYYY', // ??nh d?ng ngày
                                                        useCurrent: false
                                                    });
                                                });

                                                function setDeleteId(examId) {
                                                    document.getElementById("deleteExamId").value = examId;
                                                }
                                                function openMedicalRecordModal(examinationID) {
                                                    document.getElementById("examinationID").value = examinationID;

                                                    // G?i API ?? l?y d? li?u n?u có
                                                    fetch("getMedicalRecord?examinationID=" + examinationID)
                                                            .then(response => response.json())
                                                            .then(data => {
                                                                if (data) {
                                                                    document.getElementById("diagnosis").value = data.diagnosis || "";
                                                                    document.getElementById("treatmentPlan").value = data.treatmentPlan || "";
                                                                    document.getElementById("medicationsPrescribed").value = data.medicationsPrescribed || "";
                                                                }
                                                            });

                                                    $("#medicalRecordModal").modal("show");
                                                }
                                                


        </script>
    </body>
</html>
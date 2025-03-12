<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Danh Sách Lịch Làm Việc</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
        <title>Preclinic - Medical & Hospital - Bootstrap 4 Admin Template</title>
        <style>
            body {
                background-color: #f0f8ff;
                font-family: Arial, sans-serif;
            }
            .container {
                max-width: 900px;
                background: #ffffff;
                padding: 20px;
                border-radius: 10px;
                box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            }
            h2 {
                color: #007bff;
                text-align: center;
                margin-bottom: 20px;
            }
            .table {
                margin-top: 20px;
                border: 1px solid #ddd;
            }
            .table th {
                background-color: #007bff;
                color: white;
                text-align: center;
            }
            .table tbody tr:nth-child(odd) {
                background-color: #e3f2fd;
            }
            .doctor-header {
                background-color: #0056b3;
                color: white;
                font-size: 18px;
                font-weight: bold;
                padding: 10px;
                margin-top: 20px;
                border-radius: 5px;
                display: flex;
                justify-content: space-between;
                align-items: center;
                padding: 10px 15px;
            }
            .manage-btn {
                background-color: #28a745;
                color: white;
                border: none;
                padding: 5px 10px;
                border-radius: 5px;
                cursor: pointer;
                font-size: 14px;
            }
            .manage-btn:hover {
                background-color: #218838;
            }
            .search-container {
                margin-bottom: 15px;
                background-color: #e9ecef;
                padding: 15px;
                border-radius: 5px;
            }
            .filter-container {
                margin-bottom: 20px;
                background-color: #f8f9fa;
                padding: 15px;
                border-radius: 5px;
                border-top: 3px solid #6c757d;
            }
            .search-btn {
                background-color: #007bff;
                color: white;
            }
            .search-btn:hover {
                background-color: #0069d9;
            }
            .filter-btn {
                background-color: #6c757d;
                color: white;
            }
            .filter-btn:hover {
                background-color: #5a6268;
            }
            .reset-btn {
                background-color: #dc3545;
                color: white;
                margin-left: 10px;
            }
            .reset-btn:hover {
                background-color: #c82333;
            }
            .control-label {
                font-weight: bold;
            }
            .btn-sm {
                padding: 0.25rem 0.5rem;
                font-size: 0.875rem;
                border-radius: 0.2rem;
            }
        </style>
    </head>
    <body>
        <div class="main-wrapper">
            <jsp:include page="headerStaff.jsp"></jsp:include>
            <jsp:include page="sidebar.jsp"></jsp:include>
                <div class="container mt-5">
                    <h2>Danh Sách Lịch Làm Việc</h2>

                    <!-- Search Container -->
                    <div class="search-container">
                        <form action="loadstaffforschedule" method="POST" class="row g-3">
                            <input type="hidden" name="searchType" value="name">

                            <div class="col-md-8">
                                <label for="doctorName" class="form-label control-label">
                                    <i class="fas fa-search"></i> Tìm kiếm theo tên
                                </label>
                                <input type="text" class="form-control" id="doctorName" name="searchName" placeholder="Nhập tên bác sĩ" value="${param.searchName}">
                        </div>

                        <div class="col-md-4 d-flex align-items-end gap-2">
                            <button type="submit" class="btn search-btn w-50">
                                <i class="fas fa-search"></i> Tìm kiếm
                            </button>
                            <a href="loadstaffforschedule" class="btn reset-btn w-50">
                                <i class="fas fa-sync-alt"></i> Làm mới
                            </a>
                        </div>
                    </form>
                </div>

                <!-- Search by Date and shift-->
                <div class="search-container">
                    <form action="loadstaffforschedule" method="POST" class="row g-3">
                        <input type="hidden" name="searchType" value="date">

                        <div class="col-md-4">
                            <label for="workDate" class="form-label control-label">Tìm kiếm theo ngày</label>
                            <input type="date" class="form-control" id="workDate" name="workDate" required="" value="${param.workDate}">                       
                        </div>

                        <div class="col-md-4">
                            <label for="shift" class="form-label">Ca trong ngày</label>
                            <select class="form-select" id="shift" name="shiftFilter">
                                <option value="">Tất cả</option>
                                <option value="MORNING" ${param.shiftFilter == 'MORNING' ? 'selected' : ''}>MORNING</option>
                                <option value="AFTERNOON" ${param.shiftFilter == 'AFTERNOON' ? 'selected' : ''}>AFTERNOON</option>
                                <option value="EVENING" ${param.shiftFilter == 'EVENING' ? 'selected' : ''}>EVENING</option>
                            </select>
                        </div>

                        <div class="col-md-4 d-flex align-items-end">
                            <button type="submit" class="btn search-btn w-100">
                                <i class="fas fa-search"></i> Tìm kiếm
                            </button>
                        </div>
                    </form>
                </div>

                <!-- Filter Container -->
                <div class="filter-container">
                    <form action="loadstaffforschedule" method="POST" class="row g-3">
                        <input type="hidden" name="searchType" value="dayandshift">

                        <div class="col-12">
                            <label class="form-label control-label">
                                <i class="fas fa-filter"></i> Lọc lịch làm việc
                            </label>
                        </div>

                        <div class="col-md-5">
                            <label for="dayName" class="form-label">Ngày trong tuần</label>
                            <select class="form-select" id="dayName" name="dayFilter" required="">
                                <option value="">Chọn ngày</option>
                                <option value="2" ${param.dayFilter == '2' ? 'selected' : ''}>Monday</option>
                                <option value="3" ${param.dayFilter == '3' ? 'selected' : ''}>Tuesday</option>
                                <option value="4" ${param.dayFilter == '4' ? 'selected' : ''}>Wednesday</option>
                                <option value="5" ${param.dayFilter == '5' ? 'selected' : ''}>Thursday</option>
                                <option value="6" ${param.dayFilter == '6' ? 'selected' : ''}>Friday</option>
                                <option value="7" ${param.dayFilter == '7' ? 'selected' : ''}>Saturday</option>
                                <option value="1" ${param.dayFilter == '1' ? 'selected' : ''}>Sunday</option>
                            </select>
                        </div>

                        <div class="col-md-4">
                            <label for="shift" class="form-label">Ca trong ngày</label>
                            <select class="form-select" id="shift" name="shiftFilter">
                                <option value="">Tất cả</option>
                                <option value="MORNING" ${param.shiftFilter == 'MORNING' ? 'selected' : ''}>MORNING</option>
                                <option value="AFTERNOON" ${param.shiftFilter == 'AFTERNOON' ? 'selected' : ''}>AFTERNOON</option>
                                <option value="EVENING" ${param.shiftFilter == 'EVENING' ? 'selected' : ''}>EVENING</option>
                            </select>
                        </div>

                        <div class="col-md-3 d-flex align-items-end">
                            <button type="submit" class="btn filter-btn w-100">
                                <i class="fas fa-filter"></i> Lọc
                            </button>
                        </div>

                        <!-- Hidden input để giữ lại tên tìm kiếm khi lọc -->
                        <input type="hidden" name="searchName" value="${param.searchName}">
                    </form>
                </div>


                <c:set var="prevID" value="-1" />
                <c:forEach var="schedule" items="${professionalList}">
                    <c:if test="${schedule.professionalID ne prevID}">
                        <c:if test="${prevID ne -1}">
                            </tbody>
                            </table>
                        </c:if>
                        <div class="doctor-header">
                            <span>Bác sĩ: ${schedule.fullName} (ID: ${schedule.professionalID})</span>
                            <form action="saveSchedule" method="GET">
                                <input type="hidden" name="fullName" value="${schedule.fullName}">
                                <input type="hidden" name="professionalID" value="${schedule.professionalID}">
                                <button type="submit" class="manage-btn">Manage Lịch</button>
                            </form>
                        </div>
                        <table class="table table-bordered">
                            <thead>
                                <tr>
                                    <th>Ngày</th>
                                    <th>Ca</th>
                                    <th>Giờ bắt đầu</th>
                                    <th>Giờ kết thúc</th>
                                    <th>Trạng thái</th>
                                </tr>
                            </thead>
                            <tbody>
                            </c:if>
                            <tr>
                                <td>${schedule.dayName}</td>
                                <td>${schedule.shift}</td>
                                <td>${schedule.startTime}</td>
                                <td>${schedule.endTime}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${schedule.status == 'On'}">
                                            <a href="saveSchedule?update=statusUpdate&professionalID=${schedule.professionalID}&dayOfWeek=${schedule.dayOfWeek}&shift=${schedule.shift}&status=On" 
                                               class="btn btn-success btn-sm" 
                                               onclick="return confirm('Bạn có chắc chắn muốn tắt trạng thái này không?')">On</a>
                                        </c:when>
                                        <c:otherwise>
                                            <a href="saveSchedule?update=statusUpdate&professionalID=${schedule.professionalID}&dayOfWeek=${schedule.dayOfWeek}&shift=${schedule.shift}&status=Off" 
                                               class="btn btn-danger btn-sm" 
                                               onclick="return confirm('Bạn có chắc chắn muốn bật trạng thái này không?')">Off</a>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                            <c:set var="prevID" value="${schedule.professionalID}" />
                        </c:forEach>
                        <c:if test="${not empty professionalList}">
                        </tbody>
                    </table>
                </c:if>

                <!-- No Results Message -->
                <c:if test="${empty professionalList}">
                    <div class="alert alert-info text-center mt-4">
                        <i class="fas fa-info-circle"></i> Không tìm thấy kết quả phù hợp.
                    </div>
                </c:if>
            </div>

            <!-- Pagination -->
            <div class="d-flex justify-content-center align-items-center mt-4">
                <nav aria-label="Pagination">
                    <ul class="pagination">
                        <c:if test="${currentPage > 1}">
                            <li class="page-item">
                                <form action="loadstaffforschedule" method="POST">
                                    <input type="hidden" name="page" value="${currentPage - 1}">
                                    <input type="hidden" name="searchType" value="${param.searchType}">
                                    <input type="hidden" name="searchName" value="${param.searchName}">
                                    <input type="hidden" name="workDate" value="${param.workDate}">
                                    <input type="hidden" name="dayFilter" value="${param.dayFilter}">
                                    <input type="hidden" name="shiftFilter" value="${param.shiftFilter}">
                                    <button type="submit" class="page-link">&laquo; Trước</button>
                                </form>
                            </li>
                        </c:if>

                        <c:forEach var="i" begin="1" end="${totalPages}">
                            <li class="page-item ${i == currentPage ? 'active' : ''}">
                                <form action="loadstaffforschedule" method="POST">
                                    <input type="hidden" name="page" value="${i}">
                                    <input type="hidden" name="searchType" value="${param.searchType}">
                                    <input type="hidden" name="searchName" value="${param.searchName}">
                                    <input type="hidden" name="workDate" value="${param.workDate}">
                                    <input type="hidden" name="dayFilter" value="${param.dayFilter}">
                                    <input type="hidden" name="shiftFilter" value="${param.shiftFilter}">
                                    <button type="submit" class="page-link">${i}</button>
                                </form>
                            </li>
                        </c:forEach>

                        <c:if test="${currentPage < totalPages}">
                            <li class="page-item">
                                <form action="loadstaffforschedule" method="POST">
                                    <input type="hidden" name="page" value="${currentPage + 1}">
                                    <input type="hidden" name="searchType" value="${param.searchType}">
                                    <input type="hidden" name="searchName" value="${param.searchName}">
                                    <input type="hidden" name="workDate" value="${param.workDate}">
                                    <input type="hidden" name="dayFilter" value="${param.dayFilter}">
                                    <input type="hidden" name="shiftFilter" value="${param.shiftFilter}">
                                    <button type="submit" class="page-link">Sau &raquo;</button>
                                </form>
                            </li>
                        </c:if>
                    </ul>
                </nav>
            </div>
        </div>

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
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
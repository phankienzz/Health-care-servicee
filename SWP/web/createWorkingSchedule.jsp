<%@page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Tạo lịch làm việc</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container mt-4">
            <h2 class="text-center">Tạo Lịch Làm Việc Cho Bác Sĩ</h2>
            <form id="scheduleForm" action="save_schedule.jsp" method="POST">
                <div class="mb-3">
                    <label class="form-label">Chọn bác sĩ:</label>
                    <select name="professionalID" class="form-select" required>
                        <option value="1">Bác sĩ Nguyễn Văn A</option>
                        <option value="2">Bác sĩ Trần Thị B</option>
                        <option value="3">Bác sĩ Lê Văn C</option>
                        <option value="4">Bác sĩ Hoàng Thị D</option>
                        <option value="5">Bác sĩ Phạm Văn E</option>
                    </select>
                </div>
                <div class="mb-3">
                    <label class="form-label">Chọn lịch làm việc:</label>
                    <c:set var="days" value="Thứ Hai,Thứ Ba,Thứ Tư,Thứ Năm,Thứ Sáu,Thứ Bảy,Chủ Nhật"/>
                    <c:set var="dayValues" value="2,3,4,5,6,7,1"/>
                    <c:forEach var="day" items="${fn:split(days, ',')}" varStatus="status">
                        <div class="border p-3 mb-3">
                            <h5>${day}</h5>
                            <div class="row">
                                <c:set var="dayValue" value="${fn:split(dayValues, ',')[status.index]}" />
                                <div class="col-md-4">
                                    <input type="checkbox" class="shift-checkbox" data-day="${dayValue}" data-shift="MORNING">
                                    <label>Sáng (07:00 - 12:00)</label>
                                    <div class="shift-time d-none">
                                        <label>Bắt đầu:</label>
                                        <input type="time" name="shift-${dayValue}-MORNING-start" class="form-control morning-start">
                                        <label>Kết thúc:</label>
                                        <input type="time" name="shift-${dayValue}-MORNING-end" class="form-control morning-end">
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <input type="checkbox" class="shift-checkbox" data-day="${dayValue}" data-shift="AFTERNOON">
                                    <label>Chiều (13:00 - 18:00)</label>
                                    <div class="shift-time d-none">
                                        <label>Bắt đầu:</label>
                                        <input type="time" name="shift-${dayValue}-AFTERNOON-start" class="form-control afternoon-start">
                                        <label>Kết thúc:</label>
                                        <input type="time" name="shift-${dayValue}-AFTERNOON-end" class="form-control afternoon-end">
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <input type="checkbox" class="shift-checkbox" data-day="${dayValue}" data-shift="EVENING">
                                    <label>Tối (18:00 - 23:00)</label>
                                    <div class="shift-time d-none">
                                        <label>Bắt đầu:</label>
                                        <input type="time" name="shift-${dayValue}-EVENING-start" class="form-control evening-start">
                                        <label>Kết thúc:</label>
                                        <input type="time" name="shift-${dayValue}-EVENING-end" class="form-control evening-end">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <button type="submit" class="btn btn-primary w-100 mt-3">Lưu lịch làm việc</button>
            </form>
        </div>

        <script>
            document.addEventListener("DOMContentLoaded", function () {
                document.querySelectorAll(".shift-checkbox").forEach(checkbox => {
                    checkbox.addEventListener("change", function () {
                        let shiftContainer = this.parentElement.querySelector(".shift-time");
                        if (this.checked) {
                            shiftContainer.classList.remove("d-none");
                        } else {
                            shiftContainer.classList.add("d-none");
                        }
                    });
                });

                document.getElementById("scheduleForm").addEventListener("submit", function (event) {
                    let isValid = true;
                    let errorMsg = "";
                    document.querySelectorAll(".shift-time:not(.d-none)").forEach(shift => {
                        let startInput = shift.querySelector("input[name*='start']");
                        let endInput = shift.querySelector("input[name*='end']");
                        let shiftType = startInput.name.includes("MORNING") ? "MORNING" : startInput.name.includes("AFTERNOON") ? "AFTERNOON" : "EVENING";
                        let minTime = shiftType === "MORNING" ? "07:00" : shiftType === "AFTERNOON" ? "13:00" : "18:00";
                        let maxTime = shiftType === "MORNING" ? "12:00" : shiftType === "AFTERNOON" ? "18:00" : "23:00";

                        if (!startInput.value || !endInput.value) {
                            isValid = false;
                            errorMsg += "Vui lòng nhập giờ làm đầy đủ.\n";
                        } else if (startInput.value < minTime || startInput.value > maxTime || endInput.value < minTime || endInput.value > maxTime) {
                            isValid = false;

                            errorMsg += "Giờ làm cho ca " + shiftType + " chỉ từ " + minTime + " đến " + maxTime + ".\n";
                        } else if (startInput.value >= endInput.value) {
                            isValid = false;
                            errorMsg += "Giờ bắt đầu phải sớm hơn giờ kết thúc.\n";
                        }
                    });

                    if (!isValid) {
                        event.preventDefault();
                        alert(errorMsg);
                    }
                });
            });
        </script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>

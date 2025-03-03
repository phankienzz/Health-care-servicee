<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Chi Tiết Dịch Vụ</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <style>
        body {
            background-color: #e3f2fd;
            font-family: Arial, sans-serif;
        }
        .container {
            max-width: 700px;
            background: white;
            padding: 30px;
            margin-top: 50px;
            border-radius: 12px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
        }
        h2 {
            color: #007bff;
            text-align: center;
            font-weight: bold;
        }
        .info {
            font-size: 18px;
            margin-bottom: 15px;
        }
        .info span {
            font-weight: bold;
            color: #0056b3;
        }
        .service-img {
            display: block;
            max-width: 100%;
            height: auto;
            border-radius: 8px;
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.2);
        }
        .btn-custom {
            width: 100%;
            font-size: 18px;
            font-weight: bold;
            padding: 10px;
        }
    </style>
</head>
<body>

<div class="container">
    <h2>Chi Tiết Dịch Vụ</h2>
    
    <!-- Hiển thị hình ảnh -->
    <div class="text-center my-3">
        <img src="getimage?packageID=${service.packageID}" alt="Service Image" class="service-img">
    </div>

    <div class="info"><span>Tên Gói:</span> ${service.packageName}</div>
    <div class="info"><span>Mô Tả:</span> ${service.description}</div>
    <div class="info"><span>Loại Dịch Vụ:</span> ${service.type}</div>
    <div class="info"><span>Giá:</span> $${service.price}</div>
    <div class="info"><span>Thời Gian:</span> ${service.duration} phút</div>

    <!-- Nút đặt dịch vụ -->
    <a href="appoinment.html" class="btn btn-primary btn-custom mt-3">Đặt Dịch Vụ</a>
    <!-- Nút quay lại danh sách -->
    <a href="loadservice" class="btn btn-secondary btn-custom mt-3">Quay Lại Danh Sách</a>

</div>

</body>
</html>

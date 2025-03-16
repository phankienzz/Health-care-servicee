<%-- 
    Document   : vnpay_return
    Created on : Feb 24, 2025, 3:52:56 AM
    Author     : Gigabyte
--%>

<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<title>Result Payment</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .container {
            background: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            text-align: center;
            width: 350px;
        }
        h2 {
            color: #333;
            margin-bottom: 10px;
        }
        p {
            font-size: 16px;
            color: #555;
        }
        .status {
            font-weight: bold;
            font-size: 18px;
        }
        .success {
            color: green;
        }
        .failed {
            color: red;
        }
        .btn {
            display: inline-block;
            margin-top: 15px;
            padding: 10px 20px;
            font-size: 16px;
            color: white;
            background-color: #007bff;
            border: none;
            border-radius: 5px;
            text-decoration: none;
            transition: 0.3s;
        }
        .btn:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Kết quả thanh toán VNPAY</h2>
        <p><strong>Invoice ID:</strong> ${invoiceID}</p>
        <p><strong>Total Amount:</strong> ${amount} VND</p>
        <p class="status ${message == 'Thanh toán thành công!' ? 'success' : 'failed'}">
            <i>${message}</i>
        </p>
        <a href="invoiceCustomer" class="btn">Back to invoice</a>
    </div>
</body>
</html>


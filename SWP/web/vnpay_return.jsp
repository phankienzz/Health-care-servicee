<%-- 
    Document   : vnpay_return
    Created on : Feb 24, 2025, 3:52:56 AM
    Author     : Gigabyte
--%>

<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>Kết quả thanh toán</title>
</head>
<body>
    <h2>Kết quả thanh toán VNPAY</h2>
    <p>Mã giao dịch: <%= request.getParameter("vnp_TxnRef") %></p>
    <p>Số tiền: <%= Integer.parseInt(request.getParameter("vnp_Amount")) / 100 %> VND</p>
    <p>Trạng thái: 
        <% 
            String responseCode = request.getParameter("vnp_ResponseCode");
            if ("00".equals(responseCode)) {
                out.print("<b style='color:green'>Thanh toán thành công!</b>");
            } else {
                out.print("<b style='color:red'>Thanh toán thất bại! Mã lỗi: " + responseCode + "</b>");
            }
        %>
    </p>
    <a href="testVNPay.jsp">Quay về trang chủ</a>
</body>
</html>


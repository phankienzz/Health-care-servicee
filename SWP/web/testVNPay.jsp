<%-- 
    Document   : testVNPay
    Created on : Feb 24, 2025, 3:30:03 AM
    Author     : Gigabyte
--%>

<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>Thanh toán VNPAY</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
    <h2>Thanh toán qua VNPAY</h2>
    
    <form action="vnpay" method="post" id="frmCreateOrder">
        <label>Nhập số tiền:</label>
        <input type="text" name="amount" required><br>
        
        <label>Nội dung:</label>
        <input type="text" name="orderInfo" required><br>
        
        <button type="submit">Thanh toán VNPAY</button>
    </form>

    
    
     <script type="text/javascript">
            $("#frmCreateOrder").submit(function () {
                var postData = $("#frmCreateOrder").serialize();
                var submitUrl = $("#frmCreateOrder").attr("action");
                $.ajax({
                    type: "POST",
                    url: submitUrl,
                    data: postData,
                    dataType: 'JSON',
                    success: function (x) {
                        if (x.code === '00') {
                            if (window.vnpay) {
                                vnpay.open({width: 768, height: 600, url: x.data});
                            } else {
                                location.href = x.data;
                            }
                            return false;
                        } else {
                            alert(x.Message);
                        }
                    }
                });
                return false;
            });
        </script>  
</body>
</html>


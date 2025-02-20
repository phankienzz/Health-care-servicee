<%-- 
    Document   : register
    Created on : Jan 14, 2025, 1:30:08 AM
    Author     : jaxbo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">


    <!-- register24:03-->
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">
        <link rel="shortcut icon" type="image/x-icon" href="assets/img/favicon.ico">
        <title>Preclinic</title>
        <link rel="stylesheet" type="text/css" href="assets/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="assets/css/font-awesome.min.css">
        <link rel="stylesheet" type="text/css" href="assets/css/style.css">
    </head>

    <body>
        <div class="main-wrapper  account-wrapper">
            <div class="account-page">
                <div class="account-center">
                    <div class="account-box">
                        <form action="register" method="post" class="form-signin">
                            <div class="account-logo">
                                <a href="dashboard.html"><img src="assets/img/logo-dark.png" alt=""></a>
                            </div>
                            <p class="center">${error}</p>
                            <div class="form-group">
                                <label>Username</label>
                                <input type="text" name="username" class="form-control">
                            </div>
                            <div class="form-group">
                                <label>FullName</label>
                                <input type="text" name="fullname" class="form-control">
                            </div>
                            <div class="form-group">
                                <label>Email Address</label>
                                <input type="email" name="email" class="form-control">
                            </div>
                            <div class="form-group">
                                <label>Mobile Number</label>
                                <input type="text" name="phone" class="form-control" pattern="0\d{9}" title="Số điện thoại phải có 10 số và bắt đầu bằng 0">
                            </div>
                            <div class="form-group">
                                <label>Address</label>
                                <input type="text" name="address" class="form-control" >
                            </div>
                            <div class="form-group">
                                <label>Date of Birth</label>
                                <input type="date" name="dateOfBirth" class="form-control" placeholder="YYYY-MM-DD" pattern="\d{4}-\d{2}-\d{2}" required>
                            </div>
                            <div class="form-group">
                                <label for="gender">Gender</label>
                                <select name="gender" id="gender" class="form-control" required>
                                    <option value="" disabled selected>Select your gender</option>
                                    <option value="Male">Male</option>
                                    <option value="Female">Female</option>
                                    <!--<option value="Other">Other</option>-->
                                </select>
                            </div>
                            <div class="form-group">
                                <label>Password</label>
                                <input type="password" name="password" class="form-control" pattern=".{8,}" title="Mật khẩu phải nhiều hơn 8 ký tự">
                            </div>
                            <div class="form-group">
                                <label>Confirm Password</label>
                                <input type="password" name="confirm_password" class="form-control">
                            </div>
<!--                            <div class="form-group checkbox">
                                <label>
                                    <input type="checkbox"> I have read and agree the Terms & Conditions
                                </label>
                            </div>-->
                            <div class="form-group text-center">
                                <button class="btn btn-primary account-btn" type="submit">Signup</button>
                            </div>
                            <div class="text-center login-link">
                                Already have an account? <a href="login.jsp">Login</a>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <script src="assets/js/jquery-3.2.1.min.js"></script>
        <script src="assets/js/popper.min.js"></script>
        <script src="assets/js/bootstrap.min.js"></script>
        <script src="assets/js/app.js"></script>
    </body>
</html>

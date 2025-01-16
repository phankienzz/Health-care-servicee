<%-- 
    Document   : login
    Created on : Jan 14, 2025, 1:03:59 AM
    Author     : jaxbo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <!-- login23:11-->
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">
        <link rel="shortcut icon" type="image/x-icon" href="assets/img/favicon.ico">
        <title>Preclinic - Medical & Hospital - Bootstrap 4 Admin Template</title>
        <link rel="stylesheet" type="text/css" href="assets/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="assets/css/font-awesome.min.css">
        <link rel="stylesheet" type="text/css" href="assets/css/style.css">
        <!--[if lt IE 9]>
                    <script src="assets/js/html5shiv.min.js"></script>
                    <script src="assets/js/respond.min.js"></script>
            <![endif]-->
    </head>

    <%
        // Lấy cookies từ request
    Cookie[] cookies = request.getCookies();
    String savedUsername = "";
    String savedPassword = "";
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if ("usernameOrEmail".equals(cookie.getName())) {
                        savedUsername = cookie.getValue();
                    }
            if ("password".equals(cookie.getName())) {
                        savedPassword = cookie.getValue();
            }
        }
    }
    %>

    <body>
        <div class="main-wrapper account-wrapper">
            <div class="account-page">
                <div class="account-center">
                    <div class="account-box">
                        <form action="login" method="post" class="form-signin">
                            <div class="account-logo">
                                <a href="index-2.html"><img src="assets/img/logo-dark.png" alt=""></a>
                            </div>
                            <p class="text-danger">${error}</p>
                            <div class="form-group">
                                <label>Username or Email</label>
                                <input type="text" name="usernameOrEmail" value="<%= savedUsername %>" autofocus="" class="form-control">
                            </div>
                            <div class="form-group">
                                <label>Password</label>
                                <div class="input-group">
                                    <input type="password" name="password" id="passwordField" value="<%= savedPassword %>" class="form-control">
                                    <div class="input-group-append">
                                        <button type="button" class="btn btn-outline-secondary" id="togglePassword" tabindex="-1">
                                            <i class="fa fa-eye"></i> <!-- Icon -->
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group checkbox">
                                <label><input type="checkbox" name="rememberMe" <% if (!savedUsername.isEmpty()) { %> checked <% } %>> Remember me</label>
                            </div>
                            <div class="form-group text-right">
                                <a href="forgot-password.jsp">Forgot your password?</a>
                            </div>
                            <div class="form-group text-center">
                                <button type="submit" class="btn btn-primary account-btn">Login</button>
                            </div>
                            <div class="text-center register-link">
                                Don’t have an account? <a href="register.jsp">Register Now</a>
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


    <!-- login23:12-->
</html>

<script>
    document.getElementById('togglePassword').addEventListener('click', function () {
        const passwordField = document.getElementById('passwordField');
        const icon = this.querySelector('i');

        // Chuyển đổi type giữa 'password' và 'text'
        if (passwordField.type === 'password') {
            passwordField.type = 'text';
            icon.classList.remove('fa-eye');
            icon.classList.add('fa-eye-slash');
        } else {
            passwordField.type = 'password';
            icon.classList.remove('fa-eye-slash');
            icon.classList.add('fa-eye');
        }
    });
</script>

<!--<style>
    .input-group-append button {
        border: none;
        background: none;
        cursor: pointer;
    }

    .input-group-append button:focus {
        outline: none;
    }
</style>-->

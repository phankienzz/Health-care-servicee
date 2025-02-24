<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">
        <link rel="shortcut icon" type="image/x-icon" href="assets/img/favicon.ico">
        <title>Login</title>
        <link rel="stylesheet" type="text/css" href="assets/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="assets/css/font-awesome.min.css">
        <link rel="stylesheet" type="text/css" href="assets/css/style.css">
        <script>
            function toggleForm() {
                const userType = document.getElementById('userType').value;
                document.getElementById('userForm').style.display = userType === 'user' ? 'block' : 'none';
                document.getElementById('staffForm').style.display = userType === 'staff' ? 'block' : 'none';
                document.getElementById('googleLoginBtn').style.display = userType === 'staff' ? 'none' : 'block';
            }

            function togglePasswordVisibility(passwordFieldId, toggleButtonId) {
                const passwordField = document.getElementById(passwordFieldId);
                const icon = document.getElementById(toggleButtonId).querySelector('i');

                if (passwordField.type === 'password') {
                    passwordField.type = 'text';
                    icon.classList.remove('fa-eye');
                    icon.classList.add('fa-eye-slash');
                } else {
                    passwordField.type = 'password';
                    icon.classList.remove('fa-eye-slash');
                    icon.classList.add('fa-eye');
                }
            }
        </script>
    </head>
    <body class="bg-light">
        <div class="container d-flex justify-content-center align-items-center vh-100">
            <div class="card shadow-lg p-4" style="max-width: 400px; width: 100%;">
                <div class="card-body">

                    <% String savedUsername = (String) request.getAttribute("savedUsername");
                           if (savedUsername == null) savedUsername = ""; %>
                    <% String savedPassword = (String) request.getAttribute("savedPassword");
                           if (savedPassword == null) savedPassword = ""; %>

                    <div class="text-center mb-4">
                        <a href="index-2.html">
                            <img src="assets/img/logo-dark.png" alt="Logo" class="mb-3" style="max-width: 120px;">
                        </a>
                        <h5 class="card-title">Welcome Back</h5>
                    </div>

                    <div id="userForm" style="display: block;">
                        <!-- User Login -->
                        <form action="login" method="post" class="form-signin">
                            <p class="text-danger text-center">${error}</p>
                            <div class="form-group">
                                <label for="userType">Select User Type</label>
                                <select class="form-control" id="userType" name="userType" onchange="toggleForm()">
                                    <option value="customer" <%= "customer".equals(request.getAttribute("userType")) ? "selected" : "" %>>Customer</option>
                                    <option value="staff" <%= "staff".equals(request.getAttribute("userType")) ? "selected" : "" %>>Staff</option>
                                </select>

                            </div>

                            <div class="form-group">
                                <label id="userLabel" for="user">Username</label>
                                <input type="text" name="user" id="user" value="<%= savedUsername %>" class="form-control" placeholder="Enter your username" autofocus>                            </div>

                            <div class="form-group">
                                <label for="password">Password</label>
                                <div class="input-group">
                                    <input type="password" name="password" id="password" value="<%= savedPassword %>" class="form-control" placeholder="Enter your password">
                                    <div class="input-group-append">
                                        <button type="button" class="btn btn-outline-secondary" id="togglePasswordUser" tabindex="-1" onclick="togglePasswordVisibility('password', 'togglePasswordUser')">
                                            <i class="fa fa-eye"></i>
                                        </button>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group checkbox">
                                <label><input type="checkbox" name="rememberMe" <% if (!savedUsername.isEmpty()) { %> checked <% } %>> Remember me</label>
                            </div>

                            <div class="form-group text-right">
                                <a href="forgot-password.jsp" class="small">Forgot your password?</a>
                            </div>

                            <div class="form-group text-center">
                                <button type="submit" class="btn btn-primary btn-block">Login</button>
                            </div>

                            <div class="text-center mt-3">
                                <p class="small">Don't have an account? <a href="register.jsp">Register Now</a></p>
                            </div>
                        </form>
                    </div>

                    <!-- User Login -->
                    <div id="staffForm" style="display: none;">
                        <form action="login" method="post" class="form-signin">
                            <p class="text-danger text-center">${error}</p>

                            <div class="form-group">
                                <label for="staffId">Username</label>
                                <input type="text" name="staffId" id="staffId" class="form-control" placeholder="Enter your staff ID" autofocus>
                            </div>

                            <div class="form-group">
                                <label for="staffPassword">Password</label>
                                <div class="input-group">
                                    <input type="password" name="staffPassword" id="staffPassword" class="form-control" placeholder="Enter your password">
                                    <div class="input-group-append">
                                        <button type="button" class="btn btn-outline-secondary" id="togglePasswordStaff" tabindex="-1" onclick="togglePasswordVisibility('staffPassword', 'togglePasswordStaff')">
                                            <i class="fa fa-eye"></i>
                                        </button>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group text-right">
                                <a href="forgot-password.jsp" class="small">Forgot your password?</a>
                            </div>

                            <div class="form-group text-center">
                                <button type="submit" class="btn btn-primary btn-block">Login</button>
                            </div>
                        </form>
                    </div>

                    <div class="mt-4 text-center">
                        <button type="button" class="btn btn-danger btn-block mb-2" id="googleLoginBtn">
                            <i class="fa fa-google me-2"></i>Login with Google
                        </button>
                    </div>

                </div>
            </div>
        </div>

        <script src="assets/js/jquery-3.2.1.min.js"></script>
        <script src="assets/js/popper.min.js"></script>
        <script src="assets/js/bootstrap.min.js"></script>
        <script src="assets/js/app.js"></script>
        <script>
                                            function toggleForm() {
                                                var userType = document.getElementById("userType").value;
                                                var userLabel = document.getElementById("userLabel");
                                                var userInput = document.getElementById("user");
                                                var googleLogin = document.getElementById("googleLoginBtn");

                                                if (userType === "customer") {
                                                    userLabel.innerText = "Username";
                                                    userInput.placeholder = "Enter your username";
                                                    userInput.type = "text";
                                                    googleLogin.style.display = "block";
                                                } else {
                                                    userLabel.innerText = "Email";
                                                    userInput.placeholder = "Enter your email";
                                                    userInput.type = "email";
                                                    googleLogin.style.display = "none";
                                                }
                                            }
                                            window.onload = function () {
                                                toggleForm();
                                            };
        </script>
    </body>
</html>

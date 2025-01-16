<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">
    <link rel="shortcut icon" type="image/x-icon" href="assets/img/favicon.ico">
    <title>Preclinic - Login</title>
    <link rel="stylesheet" type="text/css" href="assets/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="assets/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="assets/css/style.css">
    <script>
        function toggleForm() {
            const userType = document.getElementById('userType').value;
            document.getElementById('userForm').style.display = userType === 'user' ? 'block' : 'none';
            document.getElementById('employeeForm').style.display = userType === 'employee' ? 'block' : 'none';
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
                <div class="text-center mb-4">
                    <a href="index-2.html">
                        <img src="assets/img/logo-dark.png" alt="Logo" class="mb-3" style="max-width: 120px;">
                    </a>
                    <h5 class="card-title">Welcome Back</h5>
                </div>

                <div class="form-group">
                    <label for="userType">Select User Type</label>
                    <select class="form-control" id="userType" onchange="toggleForm()">
                        <option value="user">User</option>
                        <option value="employee">Employee</option>
                    </select>
                </div>

                <div id="userForm" style="display: block;">
                    <% String savedUsername = (String) request.getAttribute("savedUsername");
                       if (savedUsername == null) savedUsername = ""; %>
                    <% String savedPassword = (String) request.getAttribute("savedPassword");
                       if (savedPassword == null) savedPassword = ""; %>

                    <form action="login" method="post" class="form-signin">
                        <p class="text-danger text-center">${error}</p>

                        <div class="form-group">
                            <label for="usernameOrEmail">Username or Email</label>
                            <input type="text" name="usernameOrEmail" id="usernameOrEmail" value="<%= savedUsername %>" class="form-control" placeholder="Enter your username or email" autofocus>
                        </div>

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
                            <p class="small">Don?t have an account? <a href="register.jsp">Register Now</a></p>
                        </div>
                    </form>
                </div>

                <div id="employeeForm" style="display: none;">
                    <form action="employeeLogin" method="post" class="form-signin">
                        <p class="text-danger text-center">${error}</p>

                        <div class="form-group">
                            <label for="employeeId">Employee ID</label>
                            <input type="text" name="employeeId" id="employeeId" class="form-control" placeholder="Enter your employee ID" autofocus>
                        </div>

                        <div class="form-group">
                            <label for="employeePassword">Password</label>
                            <div class="input-group">
                                <input type="password" name="employeePassword" id="employeePassword" class="form-control" placeholder="Enter your password">
                                <div class="input-group-append">
                                    <button type="button" class="btn btn-outline-secondary" id="togglePasswordEmployee" tabindex="-1" onclick="togglePasswordVisibility('employeePassword', 'togglePasswordEmployee')">
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
                    <button type="button" class="btn btn-danger btn-block mb-2">
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
</body>
</html>

<%-- 
    Document   : register
    Created on : Jan 19, 2024, 8:02:47 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Html.html to edit this template
-->
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Register</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
        <style>
            .login-form {
                width: 450px;
                margin: 50px auto;
                font-size: 15px;
            }
            .login-form form {
                margin-bottom: 15px;
                background: #f7f7f7;
                box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
                padding: 30px;
            }
            .login-form h2 {
                margin: 0 0 15px;
            }
            .form-control, .btn {
                min-height: 38px;
                border-radius: 2px;
            }
            .btn {
                font-size: 15px;
                font-weight: bold;
            }
            .is-valid {
                border-color: #28a745;
            }
            .is-invalid {
                border-color: #dc3545;
            }
            .validation-feedback {
                font-size: 80%;
                color: #dc3545;
            }
        </style>
    </head>
    <body>
        <%  String error = (String)request.getAttribute("error"); 
        %>
        <div class="login-form">
            <!--<form id="registrationForm">-->
                <form action="/Project_2/register" method="post">
<!--                    <input type="hidden" name="service" value="create">-->
                    <h2 class="text-center">Create new Account</h2>       
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Fullname" name="name" required="required">
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Phone" name="phone" required="required">
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Email" name="email" required="required">
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Username" name="username" required="required">
                        <span style="color: red"><%=error%></span>
                    </div>

                    <!-- Trường Mật khẩu -->
                    <div class="form-group">
                        <input type="password" class="form-control" id="password" placeholder="Password" name="password" required="required">
                    </div>

                    <!-- Trường Xác nhận Mật khẩu -->
                    <div class="form-group">
                        <input type="password" class="form-control" id="confirmPassword" placeholder="Password again" required="required">
                        <div id="passwordFeedback" class="validation-feedback"></div>
                    </div>

                    <div class="form-group">
                        <button name="submit" type="" value="" class="btn btn-primary btn-block" onclick="validatePassword()">Create Account</button>
                    </div>
                    <script>
                        function validatePassword() {
                            var password = document.getElementById("password");
                            var confirmPassword = document.getElementById("confirmPassword");
                            var feedback = document.getElementById("passwordFeedback");

                            if (password.value === confirmPassword.value) {
                                // Mật khẩu khớp
                                password.classList.add("is-valid");
                                password.classList.remove("is-invalid");
                                confirmPassword.classList.add("is-valid");
                                confirmPassword.classList.remove("is-invalid");
                                feedback.textContent = ''; // Không có thông báo lỗi
                            } else {
                                // Mật khẩu không khớp
                                password.classList.remove("is-valid");
                                password.classList.add("is-invalid");
                                confirmPassword.classList.remove("is-valid");
                                confirmPassword.classList.add("is-invalid");
                                feedback.textContent = 'Passwords do not match';
                            }
                        }

                        // Thêm sự kiện kiểm tra mỗi khi người dùng nhập vào trường xác nhận mật khẩu
                        document.getElementById("confirmPassword").onkeyup = validatePassword;
                    </script>
                    <p class="text-center"><a href="login.jsp">Login</a></p>
                </form>
<!--            </form>-->
        </div>
    </body>
</html>



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
            .open-button {
                padding: 0 10px;
                border: none;
                background-color: #555;
                cursor: pointer;
                display: inline-block; /* Hiển thị cùng dòng với trường nhập */
                vertical-align: middle; /* Căn giữa theo chiều dọc với trường nhập */
                margin-left: 10px; /* Khoảng cách với trường nhập */
            }  
            /* The popup form - hidden by defulat */
            .form-popup {
            display: none;
            position: fixed;
            bottom: 0;
            right: 15px;
            border: 3px solid #f1f1f1;
            z-index: 9;
            }
            /*ChatGPT*/
            .popup {
                display: none;
                position: fixed;
                left: 50%;
                top: 50%;
                transform: translate(-50%, -50%);
                background-color: #E8E8E8;  /*Mau popup*/
                padding: 20px;
                border: 1px solid red; /* màu viền xung quanh popup */
                box-shadow: 0px 0px 10px rgba(0,0,0,0.5);
                z-index: 1000;
            }

            /* Add styles to the form container */
            .form-container {
            max-width: 300px;
            padding: 10px;
            background-color: white;
            }

            /* Set a style for the submit/login button */
            .form-container .btn {
            background-color: #04AA6D;
            color: white;
            padding: 16px 20px;
            border: none;
            cursor: pointer;
            width: 100%;
            margin-bottom:10px;
            opacity: 0.8;
            }
  
            .overlay {
                display: none; /* Ẩn lớp phủ ban đầu */
                position: fixed; /* Phủ kín toàn bộ trang */
                top: 0;
                left: 0;
                right: 0;
                bottom: 0;
                background-color: rgba(0, 0, 0, 0.5); /* Màu nền mờ */
                z-index: 999; /* Đảm bảo lớp phủ nằm dưới popup */
            }
        </style>
    </head>
    <body>
         <%  String error = (String)request.getAttribute("error");  %>
        <%  String error1 = (String)request.getAttribute("error1"); %> 
        <div class="login-form">
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
                        <input type="text" class="form-control" id="email" placeholder="Email" name="email" required="required">
                        <!-- Nút để mở popup -->
                    </div>
                    <div id="myPopup" class="popup">
                        <!-- Nội dung popup -->
                        <p>
                            We sent code to confirm email. Please check email!
                        </p>
                        <div class="form-group">
                            <!--<input type="text" class="form-control" placeholder="Enter code" required="required">-->
                        </div>
                        <!-- Nút đóng popup -->
                        <button class="btn-primary" onclick="closeForm()">Close</button>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Username" name="username" required="required">
                         <span style="color: red">${error}</span> 
                    </div>

                    <!-- Trường Mật khẩu -->
                    <div class="form-group">
                        <input type="password" class="form-control" id="password" placeholder="Password" name="password" required="required">
                    </div>

                    <!-- Trường Xác nhận Mật khẩu -->
                    <div class="form-group">
                        <input type="password" class="form-control" id="confirmPassword" placeholder="Password again" name="confirmPass" required="required">
                        <span id="passwordFeedback" style="color: red">${error1}</span> 
                    </div>
                    <div id="overlay" class="overlay"></div>

                    <div class="form-group">
                        <button type="submit" class="open-button - btn btn-primary btn-block " onclick="validatePassword()">Create Account</button>
                    </div>
                    <p class="text-center"><a href="login.jsp">Login</a></p>
                </form>
        </div>
        <script>
                    function validatePassword() {
                        var password = document.getElementById("password");
                        var confirmPassword = document.getElementById("confirmPassword");
                        var feedback = document.getElementById("passwordFeedback");
                        var fullname = document.getElementsByName("name")[0];
                        var phone = document.getElementsByName("phone")[0];
                        var email = document.getElementsByName("email")[0];
                        var username = document.getElementsByName("username")[0];
                        var password = document.getElementsByName("password")[0];
                        var confirmPassword = document.getElementsByName("confirmPass")[0];

                        if(fullname && phone && email && username && password && confirmPassword) {
                            //openForm(); // Call the function to open the popup
                        }
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
                        
                        function openForm() {
                        document.getElementById("myPopup").style.display = "block";
                        document.getElementById("overlay").style.display = "block";
                        }

                        function closeForm() {
                            document.getElementById("myPopup").style.display = "none";
                              document.getElementById("overlay").style.display = "none";
                        }
//                        }

                    // Thêm sự kiện kiểm tra mỗi khi người dùng nhập vào trường xác nhận mật khẩu
                    document.getElementById("confirmPassword").onkeyup = validatePassword;
                    
                    
                }
                </script>
              
    </body>
</html>


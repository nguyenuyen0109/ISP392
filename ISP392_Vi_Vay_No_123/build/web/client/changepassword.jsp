<%-- 
    Document   : changepassword
    Created on : Jan 31, 2024, 1:51:45 AM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="/ISP392_Vi_Vay_No_123/assets/css/changepass.css"/>
    </head>
    <body onload="loadCap()">
        <script>
            function loadCap() {
                var myImage = document.getElementById("imgCap");
                var curDateTime = new Date().getTime();
                myImage.src = 'captcha?ac=' + curDateTime;
                var myInput = document.getElementById("capchaKeyInput");
                myInput.value = curDateTime;
            }
        </script>
        <jsp:include page="/navigator/toast.jsp" />
        <jsp:include page="/navigator/header.jsp" />
        <div class="mainDiv">
            <div class="cardStyle">
                <form action="/ISP392_Vi_Vay_No_123/verify" method="post" name="signupForm" id="signupForm">
                    <img src="" id="signupLogo"/>

                    <h2 class="formTitle">
                        Change Password
                    </h2>
                    <div class="inputDiv">
                        <label class="inputLabel" for="oldPassword">Old Password</label>
                        <input type="password" id="password1" name="oldPassword" placeholder="Enter OldPassword" required>
                    </div>

                    <div class="inputDiv">
                        <label class="inputLabel" for="password">New Password</label>
                        <input type="password" id="pass" name="password" placeholder="Enter password" required>
                        <div id="passwordConditionsFeedback" class="validation-feedback"></div>
                    </div>

                    <div class="inputDiv">
                        <label class="inputLabel" for="confirmPassword">Confirm Password</label>
                        <input type="password" id="re_pass" name="confirmPassword" placeholder="Re-enter password">
                        <div id="passwordFeedback" class="validation-feedback"></div>
                    </div>


                    <div class="captcha" style="width: 70%;display: flex;flex-direction: column;margin: auto;"> 
                        <label class="inputLabel" for="confirmPassword">Capcha</label><br>
                        <input type="hidden" name="capchaKey" id="capchaKeyInput">
                        <input type="text" name="captcha" placeholder="Enter captcha" required>
                        <img  id="imgCap" alt="CAPTCHA Image" style="border: 1px solid #000; width: 40%">
                        <img src="./assets/images/refresh.png" alt="Refresh Captcha" onclick="refreshCaptcha()" style="width: 10%; margin-left: 5 px">
                    </div>
                    <input type="hidden" value="/client/changepassword.jsp" name="uri">

                    <div class="buttonWrapper">
                        <button type="submit" id="submitButton"  class="submitButton pure-button pure-button-primary">
                            <span>Continue</span>
                        </button>
                    </div>

                </form>
            </div>
        </div>
        <script src="./assets/js/changepass.js"></script>
        <script>
                            function refreshCaptcha() {
                                var captchaImage = document.querySelector('.captcha img');
                                var curDateTime = new Date().getTime();

                                captchaImage.src = 'captcha?ac=' + curDateTime;
                                var myInput = document.getElementById("capchaKeyInput");
                                myInput.value = curDateTime;

                            }
        </script>
        <script>
            function validatePasswordConditions() {
                var password = document.getElementById("pass");
                var feedback = document.getElementById("passwordConditionsFeedback");

                // Điều kiện mật khẩu: ít nhất 8 ký tự, bao gồm chữ thường, chữ hoa, số và ký tự đặc biệt
                var re = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;

                if (!re.test(password.value)) {
                    password.classList.add("is-invalid");
                    feedback.textContent = 'Password must be at least 8 characters long and include lowercase and uppercase letters, numbers, and special characters';
                    // event.preventDefault();
                } else {
                    password.classList.remove("is-invalid");
                    feedback.textContent = ''; // Clear feedback
                }
            }

            function validatePassword() {
                var password = document.getElementById("pass");
                var confirmPassword = document.getElementById("re_pass");
                var feedback = document.getElementById("passwordFeedback");

                if (password.value !== confirmPassword.value) {
                    password.classList.add("is-invalid");
                    confirmPassword.classList.add("is-invalid");
                    feedback.textContent = 'Passwords do not match';

                } else {
                    password.classList.remove("is-invalid");
                    confirmPassword.classList.remove("is-invalid");
                    feedback.textContent = ''; // Clear feedback
                }
            }
            document.getElementById("pass").onblur = validatePasswordConditions;
            document.getElementById("re_pass").onblur = validatePassword;
            document.getElementById("signupForm").addEventListener("submit", function (event) {
                // Kiểm tra điều kiện của biểu mẫu ở đây
                var pw = document.getElementById("passwordFeedback").textContent;
                var pwc = document.getElementById("passwordConditionsFeedback").textContent;
                var o = document.getElementById("password1").textContent;
                if (pw !== '' || pwc !== '' ) {
                    event.preventDefault();
                    // Hiển thị thông báo hoặc xử lý lỗi khác tùy thuộc vào trường hợp của bạn
                    alert('Please correct the information!'); // Thay bằng thông báo hoặc xử lý lỗi khác
                }


            });

        </script>
    </body>
</html>

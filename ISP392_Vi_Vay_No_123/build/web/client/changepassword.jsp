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
    <body>
      <jsp:include page="/navigator/toast.jsp" />
        <jsp:include page="/navigator/header.jsp" />
        <div class="mainDiv">
            <div class="cardStyle">
                <form action="/ISP_Vi_Vay_No_123/verify" method="post" name="signupForm" id="signupForm">
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
                        <input type="password" id="password2" name="password" placeholder="Enter password" required>
                    </div>

                    <div class="inputDiv">
                        <label class="inputLabel" for="confirmPassword">Confirm Password</label>
                        <input type="password" id="confirmPassword" name="confirmPassword" placeholder="Re-enter password">
                    </div>
                    <div class="inputDiv">
                        <label class="inputLabel">Captcha</label>
                        <input type="text" id="captcha" name="captcha" placeholder="Enter captcha" required>
                    </div>

                    <div class="inputCaptcha"> 
                        <img src="/ISP392_Vi_Vay_No_123/captcha" alt="CAPTCHA Image" style="border: 1px solid #000;">
                        <img src="/ISP392_Vi_Vay_No_123/assets/images/refresh.png" alt="Refresh Captcha" onclick="refreshCaptcha()" style="width: 8%; margin-left: 15px">
                    </div>
                    <div class="buttonWrapper">
                        <button type="submit" id="submitButton" onclick="validateSignupForm()" class="submitButton pure-button pure-button-primary">
                            <span>Continue</span>
                        </button>
                    </div>

                </form>
            </div>
        </div>
        <script src="/ISP392_Vi_Vay_No_123/assets/js/changepass.js"></script>
        <script src="/ISP392_Vi_Vay_No_123/assets/js/captcha.js"></script>
    </body>
</html>

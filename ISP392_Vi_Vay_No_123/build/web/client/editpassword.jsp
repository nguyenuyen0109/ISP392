<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Change Password</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="../assets/css/changepass.css"/>
    </head>
    <body>
        <jsp:include page="/navigator/toast.jsp" />
        <div class="mainDiv">
            <div class="cardStyle">
                <form action="changepassword" method="post" name="signupForm" id="signupForm">

                    <img src="" id="signupLogo"/>

                    <h2 class="formTitle">
                        Login to your account
                    </h2>

                    <!-- Old Password Input -->
                    <div class="inputDiv">
                        <label class="inputLabel" for="oldPassword">Old Password</label>
                        <input type="password" id="oldPassword" name="oldPassword" placeholder="Enter old password" required>
                    </div>

                    <div class="inputDiv">
                        <label class="inputLabel" for="password">New Password</label>
                        <input type="password" id="password" name="password" placeholder="Enter new password" required>
                    </div>

                    <div class="inputDiv">
                        <label class="inputLabel" for="confirmPassword">Confirm New Password</label>
                        <input type="password" id="confirmPassword" name="confirmPassword" placeholder="Re-enter new password" required>
                    </div>

                    <div class="inputDiv">
                        <label class="inputLabel">Captcha</label>
                        <input type="text" id="captcha" name="captcha" placeholder="Enter captcha" required>
                    </div>

                    <div class="inputCaptcha"> 
                        <img src="captcha" alt="CAPTCHA Image" style="border: 1px solid #000;">
                        <img src="../assets/images/refresh.png" alt="Refresh Captcha" onclick="refreshCaptcha()" style="width: 8%; margin-left: 15px">
                    </div>

                    <div class="buttonWrapper">
                        <button type="submit" id="submitButton" onclick="validateSignupForm()" class="submitButton pure-button pure-button-primary">
                            <span>Continue</span>
                        </button>
                    </div>

                </form>
            </div>
        </div>
        <script src="../assets/js/changepass.js"></script>
    </body>
</html>

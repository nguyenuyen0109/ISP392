<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Change Password</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="./asset/css/changepass.css"/>
        <link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/css/bootstrap.min.css'>
    </head>
    <body>
        <div class="mainDiv">
            <div class="cardStyle">
                <form action="changepassword" method="post" name="signupForm" id="signupForm">

                    <img src="" id="signupLogo"/>

                    <h2 class="formTitle">
                        Change password
                    </h2>

                    <c:if test="${param.success ne null}">
                        <div class="alert alert-success" role="alert">
                            Update success!
                        </div>
                    </c:if>
                    <c:if test="${param.wrongold ne null}">
                        <div class="alert alert-danger" role="alert">
                            Wrong old password
                        </div>
                    </c:if>
                    <c:if test="${param.wrong2 ne null}">
                        <div class="alert alert-danger" role="alert">
                            2 password not match
                        </div>
                    </c:if>

                    <input type="hidden" name="id" value="${account.id}">

                    <div class="inputDiv">
                        <label class="inputLabel" for="oldPassword">Account:</label>
                        <input class="form-control" type="text" name="name" value="${account.username}" disabled>
                    </div>

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
                        <div class="h-captcha" data-sitekey="de38ed28-e8a8-48a6-890f-640dd1bd2553"></div>
                    </div>

                    <div class="buttonWrapper">
                        <button type="submit" id="submitButton" onclick="validateSignupForm()" class="submitButton pure-button pure-button-primary">
                            <span>Continue</span>
                        </button>
                    </div>

                </form>
            </div>
        </div>
        <script src="../asset/js/changepass.js"></script>
        <script src="https://hcaptcha.com/1/api.js" async defer></script>
    </body>
</html>

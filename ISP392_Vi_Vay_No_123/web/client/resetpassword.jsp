<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Forgot Password</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
        <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
        <!-- Style to create space at the top of the form -->
        <style>
            .form-gap {
                padding-top: 70px;
            }
        </style>
    </head>
    <body>

        <div class="form-gap"></div>
        <div class="container">
            <div class="row">
                <div class="col-md-4 col-md-offset-4">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="text-center">
                                <h3><i class="fa fa-lock fa-4x"></i></h3>
                                <h2 class="text-center">Reset Password?</h2>
                                <p>You can reset your password here.</p>
                                <div class="panel-body">

                                    <c:if test="${msg ne null}">
                                        <div class="alert mb-3" role="alert">
                                            ${msg}
                                        </div>
                                    </c:if>

                                    <form action="reset-password" id="register-form" role="form" autocomplete="off" class="form" method="post">

                                        <input type="hidden" name="uri" value="/client/resetpassword.jsp">
                                        <input type="hidden" name="token" value="${requestScope.token}">
                                        <input type="hidden" name="email" value="${requestScope.email}">

                                        <div class="form-group">
                                            <div class="input-group">
                                                <input id="email" name="password" placeholder="Password" class="form-control" type="password">
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="input-group">
                                                <input id="email" name="retypePassword" placeholder="Retype password" class="form-control" type="password">
                                            </div>
                                        </div>
                                        <div class="captcha"> 
                                            <input type="text" name="captcha" placeholder="Enter captcha" required>
                                            <img src="captcha" alt="CAPTCHA Image" style="border: 1px solid #000; width: 60%">
                                            <img src="./assets/images/refresh.png" alt="Refresh Captcha" onclick="refreshCaptcha()" style="width: 15%; margin-left: 10px">
                                        </div>
                                        <div class="form-group">
                                            <input name="recover-submit" class="btn btn-lg btn-primary btn-block" value="Reset Password" type="submit">
                                        </div>
                                        <input type="hidden" class="hide" name="token" id="token" value="">

                                    </form>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="./assets/js/captcha.js"></script>
    </body>
</html>

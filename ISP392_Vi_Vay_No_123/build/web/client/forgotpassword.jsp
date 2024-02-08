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
        <link rel="stylesheet" href="./assets/css/captcha.css"/>
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
                                <h2 class="text-center">Forgot Password?</h2>
                                <p>You can reset your password here.</p>
                                <div class="panel-body">

                                    <c:if test="${requestScope.msg ne null}">
                                        <div class="alert mb-3" role="alert">
                                            ${requestScope.msg}
                                        </div>
                                    </c:if>

                                    <form action="verify" id="register-form" role="form" autocomplete="off" class="form" method="post">
                                        <div class="form-group">
                                            <div class="input-group">
                                                <span class="input-group-addon"><i class="glyphicon glyphicon-envelope color-blue"></i></span>
                                                <input id="email" name="email" placeholder="email address" class="form-control" type="email">
                                            </div>
                                        </div>
                                        <div class="form-group captcha"> 
                                            <div class="input-group">
                                                <span class="input-group-addon"><i class="glyphicon glyphicon-picture color-blue"></i></span>
                                                <input type="text" name="captcha" placeholder="Enter captcha" class="form-control" required>
                                            </div>
                                            <div style="margin-top: 10px; display: flex; align-items: center;">
                                                <img src="captcha" alt="CAPTCHA Image" style="border: 1px solid #000; width: 60%">
                                                <img src="./assets/images/refresh.png" alt="Refresh Captcha" onclick="refreshCaptcha()" style="width: 15%; margin-left: 10px">
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <input name="recover-submit" class="btn btn-lg btn-primary btn-block" value="Reset Password" type="submit">
                                        </div>
                                        <input type="hidden" class="hide" name="token" id="token" value="">

                                        <input type="hidden" name="uri" value="/client/forgotpassword.jsp">

                                    </form>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </body>

    <!-- Add this in the head section of your HTML document -->
    <script src="https://hcaptcha.com/1/api.js" async defer></script>
    <script src="./assets/js/captcha.js"></script>

</html>

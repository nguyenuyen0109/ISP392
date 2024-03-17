<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Login Page</title>
        <!-- Font Icon -->
        <link rel="stylesheet" href="./assets/css/login.css"/>

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
        <div class="main">
            <section class="sign-in">
                <div class="container">
                    <div class="signin-content">
                        <div class="signin-image">
                            <figure><img src="./assets/boostrap/images/signin-image.jpg" alt="sing up image" style ="margin-left: 150px"></figure>
                            <a href="register" class="signup-image-link">Create an account</a>
                            <a href="forgot" class="signup-image-link">Forgot password</a>
                        </div>

                        <div class="signin-form">
                            <h2 class="form-title">Login</h2>
                            <form action="verify" method="POST" class="register-form" id="login-form">

                                <div class="form-group">
                                    <label for="username"><i class="zmdi zmdi-account material-icons-name"></i></label>
                                    <input type="text" name="username" id="username" placeholder="Username"/>
                                </div>
                                <div class="form-group">
                                    <label for="password"><i class="zmdi zmdi-lock"></i></label>
                                    <input type="password" name="password" id="password" placeholder="Password"/>
                                </div>
                                <!--<div class="h-captcha" data-sitekey="de38ed28-e8a8-48a6-890f-640dd1bd2553"></div>-->
                                <div class="captcha"> 
                                    <input type="hidden" name="capchaKey" id="capchaKeyInput">
                                    <input type="text" name="captcha" placeholder="Enter captcha" required>
                                    <img  id="imgCap" alt="CAPTCHA Image" style="border: 1px solid #000; width: 40%">
                                    <img src="./assets/images/refresh.png" alt="Refresh Captcha" onclick="refreshCaptcha()" style="width: 10%; margin-left: 5 px">
                                </div>

                                <div class="form-group form-button">
                                    <input type="submit" name="signin" id="signin" class="form-submit" value="Log in"/>
                                </div>

                                <input type="hidden" name="uri" value="/client/login.jsp">
                            </form>

                        </div>
                    </div>
                </div>
            </section>
        </div>

        <script src="./assets/boostrap/vendor/jquery/jquery.min.js"></script>
        <script src="./assets/boostrap/js/main.js"></script>

        <!-- Add this in the head section of your HTML document -->
        <script src="https://hcaptcha.com/1/api.js" async defer></script>
        <script src="./assets/js/captcha.js"></script>

    </body><!-- This templates was made by Colorlib (https://colorlib.com) -->
</html>
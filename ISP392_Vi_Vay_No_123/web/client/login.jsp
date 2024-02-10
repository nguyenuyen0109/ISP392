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
    <body>
       <jsp:include page="/navigator/toast.jsp" />
        <div class="main">
            <section class="sign-in">
                <div class="container">
                    <div class="signin-content">
                        <div class="signin-image">
                            <figure><img src="./assets/boostrap/images/signin-image.jpg" alt="sing up image"></figure>
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
                                    <input type="text" name="captcha" placeholder="Enter captcha" required>
                                    <img src="captcha" alt="CAPTCHA Image" style="border: 1px solid #000; width: 60%">
                                    <img src="./assets/images/refresh.png" alt="Refresh Captcha" onclick="refreshCaptcha()" style="width: 15%; margin-left: 10px">
                                </div>
                                
                                <div class="form-group">
                                    <input type="checkbox" name="remember-me" id="remember-me" class="agree-term" />
                                    <label for="remember-me" class="label-agree-term"><span><span></span></span>Remember me</label>
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
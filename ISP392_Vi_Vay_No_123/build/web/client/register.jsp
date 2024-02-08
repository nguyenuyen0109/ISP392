<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Sign Up</title>

        <link rel="stylesheet" href="./assets/css/register.css">
        
    </head>
    <body>

        <div class="main">

            <!-- Sign up form -->
            <section class="signup">
                <div class="container">
                    <div class="signup-content">
                        <div class="signup-form">
                            <h2 class="form-title">Sign up</h2>
                            <form action="verify" method="POST" class="register-form" id="register-form">

                                <c:if test="${requestScope.msg ne null}">
                                    <div class="alert alert-danger mb-3" role="alert">
                                        ${requestScope.msg}
                                    </div>
                                </c:if>

                                <div class="form-group">
                                    <label for="username"><i class="zmdi zmdi-account material-icons-name"></i></label>
                                    <input type="text" name="username" id="username" placeholder="Username"/>
                                </div>

                                <div class="form-group">
                                    <label for="name"><i class="zmdi zmdi-account material-icons-name"></i></label>
                                    <input type="text" name="name" id="name" placeholder="Your Name"/>
                                </div>
                                <div class="form-group">
                                    <label for="email"><i class="zmdi zmdi-email"></i></label>
                                    <input type="email" name="email" id="email" placeholder="Your Email"/>
                                </div>
                                <div class="form-group">
                                    <label for="pass"><i class="zmdi zmdi-lock"></i></label>
                                    <input type="password" name="pass" id="pass" placeholder="Password"/>
                                </div>
                                <div class="form-group">
                                    <label for="re-pass"><i class="zmdi zmdi-lock-outline"></i></label>
                                    <input type="password" name="re_pass" id="re_pass" placeholder="Repeat your password"/>
                                </div>
                                <div class="captcha"> 
                                    <input type="text" name="captcha" placeholder="Enter captcha" required>
                                    <img src="captcha" alt="CAPTCHA Image" style="border: 1px solid #000; width: 60%">
                                    <img src="./assets/images/refresh.png" alt="Refresh Captcha" onclick="refreshCaptcha()" style="width: 15%; margin-left: 10px">
                                </div>

                                <div class="form-group">
                                    <input type="checkbox" name="agree-term" id="agree-term" class="agree-term" />
                                    <label for="agree-term" class="label-agree-term"><span><span></span></span>I agree all statements in  <a href="#" class="term-service">Terms of service</a></label>
                                </div>
                                <div class="form-group form-button">
                                    <input type="submit" name="signup" id="signup" class="form-submit" value="Register"/>
                                </div>

                                <input type="hidden" name="uri" value="/client/register.jsp">

                            </form>
                        </div>
                        <div class="signup-image">
                            <figure><img src="./assets/boostrap/images/signup-image.jpg" alt="sing up image"></figure>
                            <a href="login" class="signup-image-link">I am already member</a>
                        </div>
                    </div>
                </div>
            </section>


        </div>

        <!-- JS -->
        <script src="./assets/boostrap/vendor/jquery/jquery.min.js"></script>
        <script src="./assets/boostrap/js/main.js"></script>

        <!-- Add this in the head section of your HTML document -->
        <script src="https://hcaptcha.com/1/api.js" async defer></script>
        <script src="./assets/js/captcha.js"></script>

    </body><!-- This templates was made by Colorlib (https://colorlib.com) -->
</html>
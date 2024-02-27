<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Sign Up</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="/ISP392_Vi_Vay_No_123/assets/css/register.css">
        
    </head>
    <body>


        <jsp:include page="/navigator/toast.jsp" />
        <div class="main">
            <!-- Sign up form -->
            <section class="signup">
                <div class="container">
                    <div class="signup-content">
                        <div class="signup-form">
                            <h2 class="form-title">Sign up</h2>
                            <form action="verify" method="POST" class="register-form" id="register-form">

                                <div class="form-group">
                                    <label for="name"><i class="zmdi zmdi-account material-icons-name"></i></label>
                                    <input type="text" name="name" id="name" placeholder="Your Name"/>
                                </div>
                                <div class="form-group">
                                    <label for="email"><i class="zmdi zmdi-email"></i></label>
                                    <input type="email" name="email" id="email" placeholder="Your Email"/>
                                    <div id="emailFeedback" class="validation-feedback"></div>
                                </div>
                                
                                <div class="form-group">
                                    <label for="username"><i class="zmdi zmdi-account material-icons-name"></i></label>
                                    <input type="text" name="username" id="username" placeholder="Username"/>
                                    <div id="usernameFeedback" class="validation-feedback"></div>
                                </div>
                                
                                <div class="form-group">
                                    <label for="pass"><i class="zmdi zmdi-lock"></i></label>
                                    <input type="password" name="pass" id="pass" placeholder="Password" required="required"/>
                                    <div id="passwordConditionsFeedback" class="validation-feedback"></div>
                                </div>
                                <div class="form-group">
                                    <label for="re-pass"><i class="zmdi zmdi-lock-outline"></i></label>
                                    <input type="password" name="re_pass" id="re_pass" placeholder="Repeat your password"required="required"/>
                                    <div id="passwordFeedback" class="validation-feedback"></div>
                                </div>
                                <div class="captcha"> 
                                    <input type="text" name="captcha" placeholder="Enter captcha" required>
                                    <img src="captcha" alt="CAPTCHA Image" style="border: 1px solid #000; width: 60%">
                                    <img src="./assets/images/refresh.png" alt="Refresh Captcha" onclick="refreshCaptcha()" style="width: 15%; margin-left: 10px">
                                </div>
                                <div class="form-group form-button">
                                    <input type="submit" name="signup" id="signup" class="form-submit" value="Register"/>
                                </div>

                                <input type="hidden" name="uri" value="/client/register.jsp">

                            </form>
                        </div>
                    </div>
                </div>
            </section>


        </div>

        <!-- Add this in the head section of your HTML document -->
        <script src="https://hcaptcha.com/1/api.js" async defer></script>
        <script src="./assets/js/captcha.js"></script>
        <!-- JS -->
        
        <script src="/ISP392_Vi_Vay_No_123/assets/boostrap/vendor/jquery/jquery.min.js"></script>
        <script src="/ISP392_Vi_Vay_No_123/assets/boostrap/js/main.js"></script>
        <script src="/ISP392_Vi_Vay_No_123/assets/js/validate.js"></script>

    </body><!-- This templates was made by Colorlib (https://colorlib.com) -->
</html>
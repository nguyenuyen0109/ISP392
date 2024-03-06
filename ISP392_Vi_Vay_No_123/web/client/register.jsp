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
        <script>

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
                                        function validateEmail() {
                                            var email = document.getElementById("email");
                                            var feedback = document.getElementById("emailFeedback");
                                            var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

                                            if (!re.test(email.value)) {
                                                email.classList.add("is-invalid");
                                                feedback.textContent = 'Invalid email address';

                                            } else {
                                                email.classList.remove("is-invalid");
                                                feedback.textContent = ''; // Clear feedback
                                            }
                                        }
         
                                        function validateUsername() {
                                            var username = document.getElementById("username");
                                            var feedback = document.getElementById("usernameFeedback");
                                            var re = /^[a-zA-Z0-9._]+$/;
                                            

                                            if (!re.test(username.value) || username.value.includes(" ") || username.value.trim() !== username.value) {
                                                username.classList.add("is-invalid");
                                                feedback.textContent = 'Invalid username. Username cannot contain spaces or special characters';
                
                                            } else {
                                                username.classList.remove("is-invalid");
                                                feedback.textContent = ''; // Clear feedback
               

                                            }
                                        }

                                        function validatePasswordConditions() {
                                            var password = document.getElementById("pass");
                                            var feedback = document.getElementById("passwordConditionsFeedback");
                                            var re = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
                                            if (!re.test(password.value)) {
                                                password.classList.add("is-invalid");
                                                feedback.textContent = 'Password must be at least 8 characters long and include lowercase and uppercase letters, numbers, and special characters';
                                               
                                            } else {
                                                password.classList.remove("is-invalid");
                                                feedback.textContent = ''; // Clear feedback
                                            }
                                        }



                                        document.getElementById("pass").onblur = validatePasswordConditions;
                                        document.getElementById("re_pass").onblur = validatePassword;
                                        document.getElementById("email").onblur = validateEmail;
                                        document.getElementById("username").onblur = validateUsername;

                                        document.getElementById("register-form").addEventListener("submit", function (event) {
                                            var passwordFeedback = document.getElementById("passwordFeedback").textContent;
                                            var emailFeedback = document.getElementById("emailFeedback").textContent;
//                                            var phoneFeedback = document.getElementById("phoneFeedback").textContent;
                                            var usernameFeedback = document.getElementById("usernameFeedback").textContent;
                                            var passwordConditionsFeedback = document.getElementById("passwordConditionsFeedback").textContent;
                                            if (passwordFeedback !== '' || emailFeedback !== ''||
                                                    usernameFeedback !== '' || passwordConditionsFeedback !== '') {
                                                event.preventDefault();
                                                alert('Please correct the information!');
                                            }
                                        });
        </script>

    </body><!-- This templates was made by Colorlib (https://colorlib.com) -->
</html>
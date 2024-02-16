<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Bootstrap Simple Login Form</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
        <style>
            .login-form {
                width: 340px;
                margin: 80px auto;
                font-size: 20px;
            }
            .login-form form {
                margin-bottom: 15px;
                background: #f7f7f7;
                box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
                padding: 30px;
            }
            .login-form h2 {
                margin: 0 0 15px;
            }
            .form-control, .btn {
                min-height: 38px;
                border-radius: 2px;
            }
            .btn {
                font-size: 15px;
                font-weight: bold;
            }
            .btn capcha{
                font-size: 25px;
                font-weight: bold;
            }
            .login-container {
                width: 300px;
                padding: 20px;
                border: 1px solid #ddd;
                border-radius: 5px;
            }

            .form-group {
                margin-bottom: 15px;
            }

            .form-group label {
                display: block;
            }
<<<<<<< HEAD

            .forgot-pass {
                display: block; /* Makes the link take up the full width of its container */
                text-align: right; /* Aligns the text to the right */
                padding-top: 10px; /* Adds some space between the login button and this link */
                font-size: 0.85em;
                margin-top: 10px;
            }

=======
>>>>>>> main

            #captcha {
                display: inline-block;
                padding: 8px 13px;
                margin-right: 15px;
                border: 1px solid #ddd;
                background-color: #f7f7f7;
            </style>
        </head>
        <body>
            <div class="login-form">
                <form action="/Project_2/login" method="post">
                    <h2 class="text-center">Log in</h2>       
                    <div class="form-group">
                        <input type="text" class="form-control" name="username" placeholder="Username" required="required">
                    </div>

                    <div class="form-group">
                        <input type="password" class="form-control" name="password" placeholder="Password" required="required">
                    </div>
<<<<<<< HEAD

                    <div class="form-group">

                        <!--                        <label for="captcha">CAPTCHA:</label>
                                                <span id="captcha"></span>
                                                <button type="submit" onclick="generateCaptcha()">Refresh</button>
                                                <input type="text" class="form-control captcha-input" name="captcha" placeholder="Enter CAPTCHA" required="required">-->
                        <label for="captcha">CAPTCHA:</label>
                        <span id="captcha">${generatedCaptcha}</span>
                        <button type="button" onclick="location.reload();">Refresh</button>
                        <input type="text" class="form-control captcha-input" name="captcha" placeholder="Enter CAPTCHA" required="required">
                    </div>

                    <div class="form-group">
                        <button type="submit" class="btn btn-primary btn-block">Log in</button>
                    </div>

                    <a href="#" class="forgot-pass forgetpass">Forgot Password?</a>
                    <div class="form-group">
                        <span style="color: red">${error}</span>
                        </div>

                        <script>
                            function generateCaptcha() {
                                const characters = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
                                let result = '';
                                const charactersLength = characters.length;
                                for (let i = 0; i < 6; i++) {
                                    result += characters.charAt(Math.floor(Math.random() * charactersLength));
                                }
                                document.getElementById('captcha').textContent = result;
                            }

                            // Generate initial CAPTCHA on page load
                            window.onload = generateCaptcha;
                        </script>
                    </form>
                    <p class="text-center"><a href="/Project_2/client/register.jsp">Create an Account</a></p>
                </div>
            </body>
        </html>
=======
>>>>>>> main

                    <div class="form-group">

                        
                        <label for="captcha">CAPTCHA:</label>
                        <span id="captcha">${generatedCaptcha}</span>
                        <button type="button" onclick="location.reload();">Refresh</button>
                        <input type="text" class="form-control captcha-input" name="captcha" placeholder="Enter CAPTCHA" required="required">
                    </div>

                    <div class="form-group">
                        <button type="submit" class="btn btn-primary btn-block">Log in</button>
                    </div>
                    <div class="form-group">
                        <span style="color: red">${error}</span>
                        </div>
                    </form>
                    <a href="/Project_2/client/forgotpassword.jsp" class="forgetpass">Forgot Password?</a>

                    <p class="text-center"><a href="/Project_2/client/register.jsp">Create an Account</a></p>

                </div>
            </body>

        </html>
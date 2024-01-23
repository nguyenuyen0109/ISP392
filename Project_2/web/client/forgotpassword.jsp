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
                font-size: 15px;
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
        </style>
    </head>
    <body>
        <div class="login-form">
            <form action="/Project_2/forgotpassword" method="post" novalidate="">
                <h2 class="text-center">Forgot Password</h2>       
                <div class="form-group">
                    <label for="username">Username</label>
                    <input id="username" type="username" class="form-control" name="username" value="" required autofocus>
                </div>
                
                <div class="form-group">
                    <label for="emailAddress">Email                                        
                    </label>
                    <input id="emailAddress" type="emailAddress" class="form-control" name="emailAddress" required data-eye>
                    <div class="invalid-feedback">
                        Email is required
                    </div>
                </div>
                                             
                <div class="form-group">
                    <span style="color: red">${error}</span>
                </div>
                
                <div class="form-group m-0">
                    <button type="submit" class="btn btn-primary btn-block">
                        Submit
                    </button>

                </div>

            </form>
             
        </div>
    </body>
</html>
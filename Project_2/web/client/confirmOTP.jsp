
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
            .form-popup {
                display: none;
                position: fixed;
                bottom: 0;
                right: 15px;
                border: 3px solid #f1f1f1;
                z-index: 9;
            }
            .popup {
                display: none;
                position: fixed;
                left: 50%;
                top: 50%;
                transform: translate(-50%, -50%);
                background-color: #E8E8E8;  /*Mau popup*/
                padding: 20px;
                border: 1px solid red; /* màu viền xung quanh popup */
                box-shadow: 0px 0px 10px rgba(0,0,0,0.5);
                z-index: 1000;
            }
            .overlay {
                display: none; /* Ẩn lớp phủ ban đầu */
                position: fixed; /* Phủ kín toàn bộ trang */
                top: 0;
                left: 0;
                right: 0;
                bottom: 0;
                background-color: rgba(0, 0, 0, 0.5); /* Màu nền mờ */
                z-index: 999; /* Đảm bảo lớp phủ nằm dưới popup */
            }

            .rotate {
                -webkit-animation: spin 1s linear infinite;
                -moz-animation: spin 1s linear infinite;
                animation: spin 1s linear infinite;
            }

            @-webkit-keyframes spin {
                100% {
                    -webkit-transform: rotate(360deg);
                }
            }
            @-moz-keyframes spin {
                100% {
                    -moz-transform: rotate(360deg);
                }
            }
            @keyframes spin {
                100% {
                    transform: rotate(360deg);
                }
            }

        </style>
    </head>
    <body>
        <div class="login-form">
            <form action="/Project_2/verify" method="post" onsubmit="return openPopup()">
                <h2 class="text-center">Confirm OTP</h2>       
                <div class="form-group">
                    <input type="hidden" class="form-control" name="email" value="${email}">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="otp" placeholder="OTP" required="required">
                </div>               
                <div class="form-group text-center">
                    <i class="fa fa-refresh" id="resendIcon" style="font-size: 24px; cursor: pointer;" onclick="resendOTP()"></i>
                </div>

                <div class="form-group">
                    <button type="submit" class="btn btn-primary btn-block">Confirm</button>
                </div>
                <div class="form-group">
                    <span style="color: red">${error}</span>
                </div>
<!--                <div id="successPopup" class="popup" style="display: none;">
                    <p>Register Successful!</p>
                    <button class="btn-primary" onclick="closePopupAndRedirect()">Close</button>
                </div>-->

<!--                <div id="errorPopup" class="popup" style="display: none;">
                    <p>OTP incorrect. Please re-enter!</p>
                    <button class="btn-primary" onclick="closeForm('errorPopup')">Close</button>
                    <button class="btn-secondary" onclick="resendOTP()">Resend OTP</button>
                </div>-->

            </form>
        </div>

    <script>
//            function validateOTP(otp) {
//                var correctOtp = document.getElementsByName("otp")[0].value;
//                return correctOtp;
//            }

            function closePopupAndRedirect() {
                document.getElementById("successPopup").style.display = "none";
                window.location.href = "/Project_2/client/login.jsp";
            }

            function openPopup() {
                var otp = document.getElementsByName("otp")[0].value;
                if (validateOTP(otp)) {
                    return false;
                } else {
                    openForm();
                    setTimeout(closePopupAndRedirect, 10000);
                    return true;
                }
            }

            function openForm() {
                document.getElementById("successPopup").style.display = "block";
            }

            function closeForm() {
                document.getElementById("errorPopup").style.display = "none";
            }

           function resendOTP() {
                var icon = document.getElementById("resendIcon");
                icon.classList.add("rotate");

                var email = document.getElementsByName("email")[0].value;
                console.log("Sending OTP to:", email);

                $.ajax({
                    type: "POST",
                    url: "/Project_2/resendotp",
                    data: {email: email},
                    success: function (response) {
                        console.log("Response received:", response);
                        alert("OTP has been resent to your email.");
                        icon.classList.remove("rotate");
                    },
                    error: function (xhr, status, error) {
                        console.log("Error occurred:", error);
                        alert("An error occurred while resending OTP.");
                        icon.classList.remove("rotate");
                    }
                });
            }

//            window.onload = function () {
//                var otpVerified = "<%= request.getAttribute("otpVerified") %>";
//                if (otpVerified === "false") {
//                    document.getElementById("errorPopup").style.display = "block";
//                }
//            };

        </script>

    </body>
</html>
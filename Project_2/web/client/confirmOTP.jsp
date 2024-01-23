
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
        </style>
    </head>
    <body>
        <div class="login-form">
            <form action="/Project_2/verify" method="post" onsubmit="openPopup()()">
                <h2 class="text-center">Confirm OTP</h2>       
                <div class="form-group">
                    <input type="hidden" class="form-control" name="email" value="${email}">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="otp" placeholder="OTP" required="required">
                </div>
                <div id="myPopup" class="popup">
<!--                     Nội dung popup -->
                    <p>
                        Register Success full!
                    </p>
                </div>
                <div id="overlay" class="overlay"></div>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary btn-block">Confirm</button>
                </div>
                <div class="form-group">
                    <span style="color: red">${error}</span>
                </div>
            </form>
            
            <script>
//                function openPopup() {
//                    var otp = document.getElementsByName("otp")[0].values;
//
//                    if (otp) {
//                        openForm();
//                    }
//                    function openForm() {
//                        document.getElementById("myPopup").style.display = "block";
//                        document.getElementById("overlay").style.display = "block";
//                    }
//                    function closeForm() {
//                        document.getElementById("myPopup").style.display = "none";
//                        document.getElementById("overlay").style.display = "none";
//                    }
//                }
//                    function openPopup() {
//                        var otp = document.getElementsByName("otp")[0].value;
//                        var delayInMilliseconds = 10000; // 5 giây
//
//                        if (otp) {
//                            openForm(); // Mở form ngay lập tức
//
//                            setTimeout(function () {
//                                closeForm(); // Tự động đóng form sau 5 giây
//                                window.location.href = "/client/login.jsp";
//                            }, delayInMilliseconds);
//
//                            return false; // Ngăn không cho form submit
//                        }
//                        return true; // Nếu không có giá trị OTP, cho phép form submit
//                    }
//
//                    function openForm() {
//                        document.getElementById("myPopup").style.display = "block";
//                        document.getElementById("overlay").style.display = "block";
//                    }
//
//                    function closeForm() {
//                        document.getElementById("myPopup").style.display = "none";
//                        document.getElementById("overlay").style.display = "none";
//                    }
                $(document).ready(function() {
        $("#otpForm").submit(function(event) {
            event.preventDefault();
            var otp = $("input[name=otp]").val();
            var email = $("input[name=email]").val();

            $.post("/Project_2/verify", { otp: otp, email: email }, function(response) {
                // Giả sử server trả về JSON với trường 'success'
                if (response.success) {
                    alert("Register Successful!");
                    setTimeout(function() {
                        window.location.href = 'login.jsp';
                    }, 10000); // Chuyển hướng sau 10 giây
                } else {
                    alert("Wrong OTP!");
                }
            });
        });
    });

            </script>
        </div>
    </body>
</html>
/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

function validateForm2() {
            var name = document.forms["debtorForm"]["name"].value.trim();
            var email = document.forms["debtorForm"]["email"].value.trim();
            var address = document.forms["debtorForm"]["address"].value.trim();
            var phone = document.forms["debtorForm"]["phone"].value.trim();
            
            if (name === "" || email === "" || address === "" || phone === "") {
                alert("Please fill in all fields.");
                return false;
            }

            var nameRegex = /^[a-zA-Z\s]+$/;
            if (!nameRegex.test(name)) {
                alert("Name must contain only letters and spaces.");
                return false;
            }
            
            var nameRegex = /^[a-zA-Z0-9\s]+$/;
            if (!nameRegex.test(address)) {
                alert("Address must contain only letters and spaces.");
                return false;
            }

            var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
            if (!emailRegex.test(email)) {
                alert("Please enter a valid email address.");
                return false;
            }

            var phoneRegex = /^\d{10,}$/;
            if (!phoneRegex.test(phone)) {
                alert("Phone must be a number with at least 10 digits.");
                return false;
            }

            return true;
        }

    
    
   // Kiểm tra mật khẩu
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

// Kiểm tra email
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

// Kiểm tra số điện thoại
function validatePhone() {
    var phone = document.getElementById("phone");
    var feedback = document.getElementById("phoneFeedback");
    var re = /^\d{10,11}$/;

    if (!re.test(phone.value)) {
        phone.classList.add("is-invalid");
        feedback.textContent = 'Invalid phone number';
    } else {
        phone.classList.remove("is-invalid");
        feedback.textContent = ''; // Clear feedback
    }
}

// Kiểm tra tên người dùng
function validateUsername() {
    var username = document.getElementById("username");
    var feedback = document.getElementById("usernameFeedback");
    var re = /^[a-zA-Z0-9._]{5,}$/;

    if (!re.test(username.value) || username.value.includes(" ")) {
        username.classList.add("is-invalid");
        feedback.textContent = 'Invalid username';
    } else {
        username.classList.remove("is-invalid");
        feedback.textContent = ''; 
    }
}

function validatePasswordConditions() {
    var password = document.getElementById("pass");
    var feedback = document.getElementById("passwordConditionsFeedback");

    // Điều kiện mật khẩu: ít nhất 8 ký tự, bao gồm chữ thường, chữ hoa, số và ký tự đặc biệt
    var re = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;

    if (!re.test(password.value)) {
        password.classList.add("is-invalid");
        feedback.textContent = 'Password must be at least 8 characters long and include lowercase and uppercase letters, numbers, and special characters';
    } else {
        password.classList.remove("is-invalid");
        feedback.textContent = ''; // Clear feedback
    }
}

document.getElementById("pass").onblur= validatePasswordConditions;
document.getElementById("re_pass").onblur = validatePassword;
document.getElementById("email").onblur = validateEmail;
document.getElementById("phone").onblur = validatePhone;
document.getElementById("username").oninput = validateUsername;


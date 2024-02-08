/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

var password = document.getElementById("password")
        , confirm_password = document.getElementById("confirmPassword")
        , captcha = document.getElementById("captcha");

function validatePassword() {
    if (password.value != confirm_password.value) {
        confirm_password.setCustomValidity("Passwords Don't Match");
        return false;
    } else {
        confirm_password.setCustomValidity('');
        return true;
    }
}

password.onchange = validatePassword;
confirm_password.onkeyup = validatePassword;

function validateSignupForm() {
    var form = document.getElementById('signupForm');

    for (var i = 0; i < form.elements.length; i++) {
        if (form.elements[i].value === '' && form.elements[i].hasAttribute('required')) {
            console.log('There are some required fields!');
            return false;
        }
    }

    if (!validatePassword()) {
        return false;
    }

    onSignup();
}

function refreshCaptcha() {
    var captchaImage = document.querySelector('.inputCaptcha img');
    captchaImage.src = '/ISP392_Vi_Vay_No/captcha?' + new Date().getTime();
}

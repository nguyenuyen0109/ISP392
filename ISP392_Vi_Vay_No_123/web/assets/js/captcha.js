/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
function refreshCaptcha() {
    var captchaImage = document.querySelector('.captcha img');
    var curDateTime = new Date().getTime();
    
    captchaImage.src = 'captcha?ac=' + curDateTime;
    var myInput = document.getElementById("capchaKeyInput");
    myInput.value = curDateTime;

}

function validateSignupForm() {
    var form = document.getElementById('signup-form');

    for (var i = 0; i < form.elements.length; i++) {
        if (form.elements[i].value === '' && form.elements[i].hasAttribute('required')) {
            console.log('There are some required fields!');
            return false;
        }
    }
}

function validateSigninForm() {
    var form = document.getElementById('signin-form');

    for (var i = 0; i < form.elements.length; i++) {
        if (form.elements[i].value === '' && form.elements[i].hasAttribute('required')) {
            console.log('There are some required fields!');
            return false;
        }
    }
}

function validateForgotForm() {
    var form = document.getElementById('register-form');

    for (var i = 0; i < form.elements.length; i++) {
        if (form.elements[i].value === '' && form.elements[i].hasAttribute('required')) {
            console.log('There are some required fields!');
            return false;
        }
    }
}






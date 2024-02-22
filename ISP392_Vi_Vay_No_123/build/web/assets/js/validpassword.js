/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


function validatePassword() {
    var password = document.getElementById("pass").value;

    // Kiểm tra mật khẩu theo các điều kiện
    var hasNumber = /\d/.test(password);
    var hasUpperCase = /[A-Z]/.test(password);
    var hasLowerCase = /[a-z]/.test(password);
    var hasSpecialChar = /[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]+/.test(password);
    var minLength = 8;
    var maxLength = 20;

    if (password.length < minLength || password.length > maxLength) {
        alert("Password must be between " + minLength + " and " + maxLength + " characters.");
        return false;
    }
    if (!hasNumber || !hasUpperCase || !hasLowerCase || !hasSpecialChar) {
        alert("Password must contain at least one number, one uppercase letter, one lowercase letter, and one special character.");
        return false;
    }

    return true;
}

/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

document.addEventListener("DOMContentLoaded", function() {
    var descriptions = document.querySelectorAll('.description');
    descriptions.forEach(function(description) {
        var maxLength = 10; // Giới hạn số ký tự bạn muốn hiển thị
        if (description.innerText.length > maxLength) {
            description.innerText = description.innerText.substring(0, maxLength) + '...';
        }
    });
});


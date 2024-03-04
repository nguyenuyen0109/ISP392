/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

document.addEventListener("DOMContentLoaded", function () {
    function calculateTotal() {
        var amount = document.querySelector('[name="amount"]').value;
        var interest = document.querySelector('[name="interest"]').value;
        var feeRate = 0.01; // Phí 1%
        var due = document.querySelector('[name="due"]').value;
        var x = -amount;
        // Chuy?n ??i lãi su?t t? ph?n tr?m sang d?ng s? th?p phân và tính t?ng ti?n
        var total = (Number(amount) * feeRate) + (Number(amount) * (1 + (Number(interest) / 100) * (Number(due) / 12)));

        // C?p nh?t giá tr? vào tr??ng totalAmount
        document.getElementById('totalAmount').value = total.toFixed(2);
    }

    // L?ng nghe s? ki?n thay ??i trên các tr??ng input và g?i hàm calculateTotal
    document.querySelector('[name="amount"]').addEventListener('input', calculateTotal);
    document.querySelector('[name="interest"]').addEventListener('input', calculateTotal);
    document.querySelector('[name="due"]').addEventListener('input', calculateTotal);                                                                                                                                                                                                                                  

    function handleFileSelect(event) {
        const file = event.target.files[0];
        const reader = new FileReader();
        const img = document.getElementById('previewimage');

        reader.onload = function (event) {
            img.src = event.target.result;
            document.querySelector('input[name="Image"]').value = event.target.result;
        };

        reader.readAsDataURL(file);
    }
    document.getElementById('FileInput').addEventListener('input', handleFileSelect);
});

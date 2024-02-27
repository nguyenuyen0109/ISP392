<!DOCTYPE html>
<html lang="en">
<head>
    <title>Bootstrap Example</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    <style>
        /* Thêm CSS ?? hi?n th? toast ? góc trên */
        .toast-container {
            position: fixed;
            top: 20px; /* ?i?u ch?nh v? trí top tùy ý */
            right: 20px; /* ?i?u ch?nh v? trí right tùy ý */
            z-index: 9999; /* ??m b?o toast hi?n th? trên các ph?n t? khác */
            width: 300px; /* ?i?u ch?nh chi?u r?ng c?a toast container */
        }
    </style>
</head>
<body>
<script>
    document.addEventListener("DOMContentLoaded", function () {
        var alertValue = '<%= request.getAttribute("alert") %>';
        if (alertValue && alertValue.trim() !== '') {
            // Hi?n th? toast n?u có giá tr? alert
            var toastEl = document.querySelector('.toast');
            var toast = new bootstrap.Toast(toastEl, {delay: 7000}); // ?i?u ch?nh th?i gian hi?n th? tùy ý
            toast.show();
            setTimeout(function () {
                toast.hide();
            }, 7000); // ?i?u ch?nh th?i gian hi?n th? tùy ý
        }
    });
</script>

<div class="toast-container position-fixed top-0 start-0 p-3"> <!-- Chuy?n class bottom-0 end-0 thành top-0 start-0 -->
    <% if (request.getAttribute("alert") != null && !((String)request.getAttribute("alert")).isEmpty()) { %>
    <div class="toast">
        <div class="toast-header">
            <strong class="me-auto">Notification</strong>
            <button type="button" class="btn-close" data-bs-dismiss="toast"></button>
        </div>
        <div class="toast-body">
            <%= request.getAttribute("alert") %>
        </div>
    </div>
    <div class="toast-progress"></div>
    <% } %>
</div>
</body>
</html>

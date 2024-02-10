<%-- 
    Document   : toast
    Created on : Feb 5, 2024, 2:09:30 PM
    Author     : MINIMONIE
--%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Bootstrap Example</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    </head>
    <body>

        <%-- JavaScript ?? ki?m tra gi� tr? c?a alert v� hi?n th? toast t??ng ?ng --%>
        <script>
            document.addEventListener("DOMContentLoaded", function () {
                var alertValue = '<%= request.getAttribute("alert") %>';
                if (alertValue && alertValue.trim() !== '') {
                    // Hi?n th? toast n?u alert c� gi� tr?
                    var toastEl = document.querySelector('.toast');
                    var toast = new bootstrap.Toast(toastEl, {delay: 5000}); // Thi?t l?p th?i gian hi?n th? l� 5 gi�y
                    toast.show();

                    // T? ??ng ?�ng toast sau khi hi?n th? trong 5 gi�y
                    setTimeout(function () {
                        toast.hide();
                    }, 5000);
                }
            });
        </script>

        <div class="container mt-3">
            <%-- Ki?m tra gi� tr? c?a alert trong JSP v� ch? hi?n th? toast khi c� gi� tr? --%>
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

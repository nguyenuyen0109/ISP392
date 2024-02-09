<%-- 
    Document   : toast
    Created on : Feb 5, 2024, 2:09:30 PM
    Author     : MINIMONIE
--%>

<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>
 Ki?m tra xem c� th�ng b�o toast hi?n th? kh�ng 
<% if (request.getAttribute("toastMessage") != null) { %>
<div class="toast" style="position: fixed; top: 20px; right: 20px; z-index: 1050;">
    <div class="toast-header">
        <strong class="mr-auto text-primary">Th�ng b�o</strong>
        <button type="button" class="ml-2 mb-1 close" data-dismiss="toast" aria-label="Close">&times;</button> <!-- Th�m s? ki?n click v�o n�t "x" -->
    </div>
    <div class="toast-body">
        <%= request.getAttribute("msg") %>
    </div>
</div>
<% } %>--%>

<<link rel="stylesheet" href="./assets/css/toast.css"/>
<div aria-live="polite" aria-atomic="true" style="position: relative; min-height: 200px;">
  <!-- Position it -->
  <div style="position: absolute; top: 0; right: 0;">

    <!-- Then put toasts within -->
    <div class="toast" role="alert" aria-live="assertive" aria-atomic="true">
      <div class="toast-header">
        <img src="" class="rounded mr-2" alt="...">
        <strong class="mr-auto">Notifications</strong>
        <small class="text-muted">just now</small>
        <button type="button" class="ml-2 mb-1 close" data-dismiss="toast" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="toast-body">
        <%= request.getAttribute("msg") %>
      </div>
    </div>
  </div>
</div>

<script>
  // Khi t�i li?u ???c t?i, t? ??ng m? v� t?t toast sau m?t kho?ng th?i gian
  $(document).ready(function () {
    // Th�m hi?u ?ng m? toast
    $('.toast').toast({ delay: 5000 });
    $('.toast').toast('show');

    // T? ?�ng toast sau m?t kho?ng th?i gian
    setTimeout(function() {
      $('.toast').toast('hide');
    }, 5000);

    // Th�m s? ki?n click v�o n�t "x" ?? ?�ng toast
    $('.close').on('click', function() {
      $(this).closest('.toast').toast('hide');
    });
  });
</script>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="model.Account"%> <!-- Replace 'your.package' with the actual package name where the 'Account' class is defined -->

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Debit Note</title>

        <link href="https://fonts.googleapis.com/css2?family=Work+Sans:wght@400;600;700&display=swap" rel="stylesheet">


        <link rel="stylesheet" href="fonts/icomoon/style.css">
        <link rel="stylesheet" href="fonts/flaticon/font/flaticon.css">

        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">

        <link rel="stylesheet" href="/ISP392_Vi_Vay_No_123/assets/css/tiny-slider.css">
        <link rel="stylesheet" href="/ISP392_Vi_Vay_No_123/assets/css/aos.css">
        <link rel="stylesheet" href="/ISP392_Vi_Vay_No_123/assets/css/glightbox.min.css">
        <link rel="stylesheet" href="/ISP392_Vi_Vay_No_123/assets/css/homepage.css">

        <link rel="stylesheet" href="/ISP392_Vi_Vay_No_123/assets/css/flatpickr.min.css">


        <link rel="stylesheet" href="/ISP392_Vi_Vay_No_123/assets/css/header.css">

    </head>
    <body>
        <header class="header-container">
            <div class="container">
                <h1><a href="/ISP392_Vi_Vay_No_123/client/homepage.jsp" class="logo">Debit Note<span class="text-primary">.</span></a></h1>
                <nav>
                    <ul>
                        <li class="active"><a href="client/homepage.jsp">Home</a></li>
                         <a href="/ISP392_Vi_Vay_No_123/changepassword">Change Password</a>
                        <a href="/ISP392_Vi_Vay_No_123/editprofile">Edit Profile</a>
                        <a href="/ISP392_Vi_Vay_No_123/debtor">View Debtor List</a>
                        
                    </ul>
                </nav>
                <% if (session != null && session.getAttribute("USER") != null) {
                    Account user = (Account) session.getAttribute("USER");
                    Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
                %>
                <div class="dropdown">
                    <button class="dropbtn">Hello, <%= user.getUsername() %></button>
                    <div class="dropdown-content">
                        <a href="/ISP392_Vi_Vay_No_123/changepassword">Change Password</a>
                        <a href="/ISP392_Vi_Vay_No_123/editprofile">Edit Profile</a>
                        <a href="/ISP392_Vi_Vay_No_123/debtor">View Debtor List</a>
                        <% if (isAdmin != null && isAdmin) { %>
                        <a href="dashboard.jsp">Dashboard</a>
                        <% } %>
                        <a href="/ISP392_Vi_Vay_No_123/logout">Logout</a>
                    </div>
                </div>
                <% } else { %>
                <a href="../login">
                    <button class="login-button">
                        Login
                    </button>
                </a>
                <% } %>
            </div>
        </header>

        <script src="ISP392_Vi_Vay_No_123/assets/js/bootstrap.bundle.min.js"></script>
        <script src="ISP392_Vi_Vay_No_123/assets/js/tiny-slider.js"></script>
        <script src="ISP392_Vi_Vay_No_123/assets/js/flatpickr.min.js"></script>
        <script src="ISP392_Vi_Vay_No_123/assets/js/aos.js"></script>
        <script src="ISP392_Vi_Vay_No_123/assets/js/glightbox.min.js"></script>
        <script src="ISP392_Vi_Vay_No_123/assets/js/navbar.js"></script>
        <script src="ISP392_Vi_Vay_No_123/assets/js/counter.js"></script>
        <script src="ISP392_Vi_Vay_No_123/assets/js/custom.js"></script>
    </body>
</html>
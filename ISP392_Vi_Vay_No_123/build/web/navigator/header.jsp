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

        <link rel="stylesheet" href="../assets/css/tiny-slider.css">
        <link rel="stylesheet" href="../assets/css/aos.css">
        <link rel="stylesheet" href="../assets/css/glightbox.min.css">
        <link rel="stylesheet" href="../assets/css/homepage.css">

        <link rel="stylesheet" href="../assets/css/flatpickr.min.css">


        <link rel="stylesheet" href="../assets/css/header.css">

    </head>
    <body>
        <nav class="site-nav">
            <div class="container">
                <div class="menu-bg-wrap">
                    <div class="site-navigation">
                        <div class="row g-0 align-items-center">
                            <div class="col-2">
                                <a href="homepage.jsp" class="logo m-0 float-start">Debit Note<span class="text-primary">.</span></a>
                            </div>
                            <div class="col-8 text-center">
                                <ul class="js-clone-nav d-none d-lg-inline-block text-start site-menu mx-auto">
                                    <li class="active"><a href="client/homepage.jsp">Home</a></li>
                                    <li><a href="blog.html">Blog</a></li>
                                    <li><a href="services.html">Services</a></li>
                                    <li><a href="about.html">About</a></li>
                                    <li><a href="contact.html">Contact Us</a></li>
                                </ul>
                            </div>
                            <div class="col-2 text-end">
                                <a href="#" class="burger ms-auto float-end site-menu-toggle js-menu-toggle d-inline-block d-lg-none light"><span></span></a>
                                        <%
                                            if (session != null) {
                                                Account user = (Account) session.getAttribute("USER");
                                                Boolean isAdmin = (Boolean) session.getAttribute("Admin");
                                                if (user != null) {
                                        %>
                                <div class="dropdown">
                                    <button class="dropbtn">Hello, <%= user.getUsername() %></button>
                                    <div class="dropdown-content">
                                        <a href="changepassword.jsp">Change Password</a>
                                        <a href="/ISP392_Vi_Vay_No_123/editprofile">Edit Profile</a>
                                        <a href="/ISP392_Vi_Vay_No_123/debtor">View Debtor List</a>
                                        <% if (isAdmin != null && isAdmin) { %>
                                        <a href="dashboard.jsp">Dashboard</a>
                                        <% } %>
                                        <a href="../logout">Logout</a>
                                    </div>
                                </div>
                                <% 
                                        } else { 
                                %>
                                <a href="../login">
                                    <button class="login-button d-flex align-items-center">
                                        <span class="icon-login" style="font-size: 24px; margin-right: 8px;"></span>
                                        <span style="font-weight: bold; font-size: 16px;">Login</span>
                                    </button>
                                </a>
                                <%
                                        }
                                    }
                                %>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </nav>

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
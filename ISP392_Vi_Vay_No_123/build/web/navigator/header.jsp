<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Account" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="author" content="Untree.co">
        <link rel="shortcut icon" href="favicon.png">

        <meta name="description" content="" />
        <meta name="keywords" content="bootstrap, bootstrap5" />

        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
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



        <title>DEBIT NOTE</title>
        <style>
            .container {
                margin-bottom:  100px;
            }
       
        
        </style>

    </head>
    <body>
        <jsp:include page="/navigator/toast.jsp" />
<!--            <div class="hero overlay">-->
<div class="container">
    <nav class="site-nav">
            <div class="container">
                <div class="menu-bg-wrap">
                    <div class="site-navigation">
                        <div class="row g-0 align-items-center">
                            <div class="col-2">
                                <a href="homepage.jsp" class="logo m-0 float-start" style="color : black">DEBIT NOTE<span class="text-primary">.</span></a>
                            </div>
                            <div class="col-8 text-center">
                                <ul class="js-clone-nav d-none d-lg-inline-block text-start site-menu mx-auto">
                                    <li style="color : black" class="active"><a href="/ISP392_Vi_Vay_No_123/client/homepage.jsp">Home</a></li>
                                    <li class="has-children">
                                        <a href="#">Pages</a>
                                        <ul class="dropdown">
                                            <li><a href="#">Financing</a></li>
                                            <li><a href="#">Blog Single</a></li>
                                            <li><a href="#">Case Study Detail</a></li>
                                            <li><a href="#">Menu One</a></li>
                                            <li><a href="#">Menu Two</a></li>
                                            <li class="has-children">
                                                <a href="#">Dropdown</a>
                                                <ul class="dropdown">
                                                    <li><a href="#">Sub Menu One</a></li>
                                                    <li><a href="#">Sub Menu Two</a></li>
                                                    <li><a href="#">Sub Menu Three</a></li>
                                                </ul>
                                            </li>
                                        </ul>
                                    </li>
                                    <li><a style="color : black" href="blog.html">Blog</a></li>
                                    <li><a style="color : black"  href="services.html">Services</a></li>
                                    <li><a style="color : black" href="about.html">About</a></li>
                                    <li><a style="color : black" href="contact.html">Contact Us</a></li>
                                </ul>
                            </div>
                            <div class="col-2 text-end">
                                <a href="#" class="burger ms-auto float-end site-menu-toggle js-menu-toggle d-inline-block d-lg-none light">
                                    <span></span>
                                </a>
                                <% if (session != null && session.getAttribute("USER") != null) {
                                    Account user = (Account) session.getAttribute("USER");
                                    Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
                                %>
                                <div class="dropdown">
                                    <button class="btn btn-primary dropdown-toggle" type="button" id="dropdownMenuButton" data-bs-toggle="dropdown" aria-expanded="false">
                                        Hello, <%= user.getUsername() %>
                                    </button>
                                    <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                        <li><a class="dropdown-item" href="/ISP392_Vi_Vay_No_123/changepassword">Change Password</a></li>
                                        <li><a class="dropdown-item" href="/ISP392_Vi_Vay_No_123/editprofile">Edit Profile</a></li>
                                        <li><a class="dropdown-item" href="/ISP392_Vi_Vay_No_123/debtor">View Debtor List</a></li>

                                        <% if (isAdmin != null && isAdmin) { %>
                                        <li><a class="dropdown-item" href="/ISP392_Vi_Vay_No_123/account">Dashboard</a></li>
                                            <% } %>
                                        <li><a class="dropdown-item" href="/ISP392_Vi_Vay_No_123/logout">Logout</a></li>
                                    </ul>
                                </div>
                                <% } else { %>
                                <a href="../login">
                                    <button class="login-button d-flex align-items-center" style="background-color: #3056E8; color: #ffffff; padding: 10px 20px; border: 2px solid #ffffff; border-radius: 5px; cursor: pointer; transition: all 0.3s ease;">
                                        <span class="bi bi-box-arrow-in-right" style="font-size: 24px; margin-right: 8px;"></span>
                                        <span style="font-weight: bold; font-size: 16px;">Login</span>
                                    </button>
                                </a>
                                <% } %>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </nav>
</div>
        
    

    <script src="ISP392_Vi_Vay_No_123/assets/js/bootstrap.bundle.min.js"></script>
    <script src="ISP392_Vi_Vay_No_123/assets/js/tiny-slider.js"></script>
    <script src="ISP392_Vi_Vay_No_123/assets/js/flatpickr.min.js"></script>
    <script src="ISP392_Vi_Vay_No_123/assets/js/aos.js"></script>
    <script src="ISP392_Vi_Vay_No_123/assets/js/glightbox.min.js"></script>
    <script src="ISP392_Vi_Vay_No_123/assets/js/navbar.js"></script>
    <script src="ISP392_Vi_Vay_No_123/assets/js/counter.js"></script>
    <script src="ISP392_Vi_Vay_No_123/assets/js/custom.js"></script>
</body>

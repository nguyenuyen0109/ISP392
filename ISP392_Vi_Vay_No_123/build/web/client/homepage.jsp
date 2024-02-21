<!-- /*
* Template URI: https://untree.co/
* License: https://creativecommons.org/licenses/by/3.0/
*/ -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Account" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
    <head>
        <style>
            /* CSS for dropdown menu */
            .dropdown {
                position: relative;
                display: inline-block;
            }

            .dropdown-content {
                display: none;
                position: absolute;
                background-color: #f9f9f9;
                min-width: 160px;
                box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
                z-index: 1;
                border-radius: 20px; /* Rounded corners like the login button */
            }

            .dropdown-content a {
                color: black;
                padding: 12px 16px;
                text-decoration: none;
                display: block;
            }

            .dropdown:hover .dropdown-content {
                display: block;
            }

            .dropbtn {
                background-color: #3053E8;
                color: white;
                padding: 16px;
                font-size: 16px;
                border: 1px solid #dddddd; /* Adding a border similar to the dropdown */
                border-radius: 20px; /* Increasing border radius for rounded edges */
                cursor: pointer;
                transition: all 0.3s ease;
                text-transform: uppercase; /* Uppercase text */
                letter-spacing: 1px; /* Spacing between letters */
            }

            .dropbtn:hover {
                background-color: #2036B4;
                border-color: #dddddd; /* Keeping the border color consistent on hover */
            }

            /* CSS for the login button */
            .login-button {
                background-color: #3053E8;
                color: #ffffff;
                padding: 10px 30px;
                border: 2px solid #ffffff; /* White border */
                border-radius: 20px; /* Rounded corners like the dropdown button */
                cursor: pointer;
                transition: all 0.3s ease;
                text-transform: uppercase; /* Uppercase text */
                letter-spacing: 1px; /* Spacing between letters */
            }

            .login-button:hover {
                background-color: #2036B4;
                border-color: #ffffff; /* White border on hover */
            }

        </style>


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

        <link rel="stylesheet" href="../assets/css/tiny-slider.css">
        <link rel="stylesheet" href="../assets/css/aos.css">
        <link rel="stylesheet" href="../assets/css/glightbox.min.css">
        <link rel="stylesheet" href="../assets/css/homepage.css">

        <link rel="stylesheet" href="../assets/css/flatpickr.min.css">


        <title>Financing &mdash; Free Bootstrap 5 Website Template by Untree.co</title>

    </head>
    <body>
        <jsp:include page="/navigator/toast.jsp" />
        <div class="site-mobile-menu site-navbar-target">
            <div class="site-mobile-menu-header">
                <div class="site-mobile-menu-close">
                    <span class="icofont-close js-menu-toggle"></span>
                </div>
            </div>
            <div class="site-mobile-menu-body"></div>
        </div>

        <nav class="site-nav">
            <div class="container">
                <div class="menu-bg-wrap">
                    <div class="site-navigation">
                        <div class="row g-0 align-items-center">
                            <div class="col-2">
                                <a href="index.html" class="logo m-0 float-start">Debit Note<span class="text-primary">.</span></a>
                            </div>
                            <div class="col-8 text-center ">
                                <ul class="js-clone-nav d-none d-lg-inline-block text-start site-menu mx-auto">
                                    <li class="active"><a href="#">Home</a></li>
                                    <li><a href="blog.html">Blog</a></li>
                                    <li><a href="services.html">Services</a></li>
                                    <li><a href="about.html">About</a></li>
                                    <li><a href="contact.html">Contact Us</a></li>
                                </ul>
                            </div>
                            <div class="col-2 text-end">
                                <a href="#" class="burger ms-auto float-end site-menu-toggle js-menu-toggle d-inline-block d-lg-none light">
                                    <span></span>
                                </a>

                                <%
                                    Account user = (Account) session.getAttribute("USER");
                                    Boolean isAdmin = (Boolean) session.getAttribute("Admin");
                                    if (user != null) {
                                %>
                                <div class="dropdown">
                                    <button class="dropbtn">Hello, <%= user.getUsername() %></button>
                                    <div class="dropdown-content">
                                        <a href="changepassword.jsp">Change Password</a>
                                        <a href="/ISP392_Vi_Vay_No_123/editprofile">Edit Profile</a>
                                        <a href ="../debtor"> View Debtor List </a>
                                        <% if (isAdmin != null && isAdmin) { %>
                                        <a href="dashboard.jsp">Dashboard</a>
                                        <% } %>
                                        <a href="../logout">Logout</a>
                                    </div>
                                </div>
                                <% } else { %>
                                <a href="../login">
                                    <button class="login-button d-flex align-items-center">
                                        <span class="icon-login" style="font-size: 24px; margin-right: 8px;"></span>
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

    <script src="../assets/js/bootstrap.bundle.min.js"></script>
    <script src="../assets/js/tiny-slider.js"></script>

    <script src="../assets/js/flatpickr.min.js"></script>


    <script src="../assets/js/aos.js"></script>
    <script src="../assets/js/glightbox.min.js"></script>
    <script src="../assets/js/navbar.js"></script>
    <script src="../assets/js/counter.js"></script>
    <script src="../assets/js/custom.js"></script>
</body>
</html>

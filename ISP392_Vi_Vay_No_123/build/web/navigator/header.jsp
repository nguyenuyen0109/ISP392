
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="model.Account"%> <!-- Replace 'your.package' with the actual package name where the 'Account' class is defined -->

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Debit Note</title>
        <link rel="stylesheet" href="/ISP392_Vi_Vay_No_123/assets/css/header.css">
        <link rel="stylesheet" href="/ISP392_Vi_Vay_No_123/assets/css/homepage.css">
        <style>

            /* CSS for dropdown menu */
            /*            .dropdown {
                            position: relative;
                            display: inline-block;
                        }*/

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


        </style>

    </head>

    <body>
        <header>
            <div class="container">
                <div class="row g-0 align-items-center">
                    <div class="col-2">
                        <a href="#" class="logo m-0 float-start">Debit Note<span class="text-primary"></span></a>
                    </div>
                    <!--<div class="col-2 text-end">-->
                    <!--                        <a href="#" class="burger ms-auto float-end site-menu-toggle js-menu-toggle d-inline-block d-lg-none light"><span></span></a>-->
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
                                <a href="/ISP392_Vi_Vay_No_123/logout">Logout</a>
                            </div>
                        </div>      
                    
                    <% 
                            } else { 
                    %>
                    <a href="/ISP392_Vi_Vay_No_123/login">
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
    </header>
</body>
</html>

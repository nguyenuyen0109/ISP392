
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <!--
Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Html.html to edit this template
    -->
    <body>
<<<<<<< HEAD
        <nav class="nvarbar">
            <div class="brand-title">LUMHT</div>
                <div class="rightbar">
                <c:choose>
                    <c:when test="${not empty sessionScope.USER}">
=======
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <c:choose>
                <c:when test="${not empty sessionScope.USER}">
                    <a class="navbar-brand" href="#">Dashboard</a>
                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
                            aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarNav">
                        <ul class="navbar-nav">
                            <li class="nav-item">
                                <a class="nav-link" href="/Project_2/account">Accounts</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="">Debt List</a>
                            </li>
                            <!-- Add more navigation links for the admin dashboard as needed -->
                        </ul>
                    </div>
                    <div class="rightbar">

>>>>>>> main
                        <li>
                            <div class="dropdown">
                                <button class="btn btn-secondary dropdown-toggle" type="button"
                                        id="dropdownMenu2" data-bs-toggle="dropdown" aria-haspopup="true"
                                        aria-expanded="false">
                                    <c:out value="${sessionScope.USER.username}"/>
                                </button>
                                <div class="dropdown-menu" aria-labelledby="dropdownMenu2">
                                    <c:choose>
                                        <c:when test="${sessionScope.Admin}">
                                            <!-- Dropdown cho Admin -->
                                            <form action="/Project_2/logout"><button class="dropdown-item" type="submit" name="userAction" value="logout">Log out</button></form>
                                            <button class="dropdown-item" type="button">View User List</button>
                                            <button class="dropdown-item" type="button">View Debt List</button>
                                            <button class="dropdown-item" type="button">View profile</button>
                                            <button class="dropdown-item" type="button">Logout</button>
                                            <form action="#"><button class="dropdown-item" type="submit" name="userAction" value="logout">View Profile </button></form>
                                            <form action="#"><button class="dropdown-item" type="submit" name="userAction" value="logout">View User List</button></form>
                                            <form action="#"><button class="dropdown-item" type="submit" name="userAction" value="logout">View Debt List</button></form>
                                            <form action="/Project_2/logout"method="get"><button class="dropdown-item" type="submit" name="userAction" value="logout">Log out</button></form>
                                        </c:when>
                                        <c:otherwise>
                                            <!-- Dropdown cho User bình thường -->
                                            <form action="#"><button class="dropdown-item" type="submit" name="userAction" value="logout">View Profile</button></form>
                                            <form action="#"><button class="dropdown-item" type="submit" name="userAction" value="logout">View Debt List</button></form>
                                            <form action="/Project_2/logout"><button class="dropdown-item" type="submit" name="userAction" value="logout">Log out</button></form>

                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                        </li>
                    </c:when>
                    <c:otherwise>
<<<<<<< HEAD
                        <ul>
                            <li><a href="/Project_2/login">Login</a></li>
                        </ul>
=======
                        <div class="d-flex justify-content-between align-items-center w-100 navbar-custom-container">
                            <a class="navbar-brand navbar-custom-brand" href="/Project_2/home">Dashboard</a>
                            <div class="login-container">
                                <a href="/Project_2/login">Login</a>
                            </div>
                        </div>
>>>>>>> main
                    </c:otherwise>


                </c:choose>
            </div>
        </nav>
        <nav class="category">

        </nav>
        <script src="/Project_2/bootstrap-5.3.2-dist/js/bootstrap.bundle.min.js"></script>
    </body>

</html>
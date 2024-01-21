
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <!--
Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Html.html to edit this template
    -->
    <body>
        <nav class="nvarbar">
            <div class="brand-title">LUMHT</div>
                <div class="rightbar">
                <c:choose>
                    <c:when test="${not empty sessionScope.USER}">
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
                        <ul>
                            <li><a href="/Project_2/login">Login</a></li>
                        </ul>
                    </c:otherwise>
                </c:choose>
            </div>
        </nav>
        <nav class="category">

        </nav>
        <script src="/Project_2/bootstrap-5.3.2-dist/js/bootstrap.bundle.min.js"></script>
    </body>

</html>
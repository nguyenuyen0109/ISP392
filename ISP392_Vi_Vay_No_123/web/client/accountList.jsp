<%-- 
    Document   : accountList
    Created on : Mar 4, 2024, 11:59:32 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="/ISP392_Vi_Vay_No_123/assets/css/styleAccountList.css"/>
        <title>Account List</title>
    </head>
    <body>
        <div class="container">
            <div class="main-body">

                <!-- Breadcrumb -->
                <nav aria-label="breadcrumb" class="main-breadcrumb">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="index.html">Home</a></li>
                        <li class="breadcrumb-item"><a href="javascript:void(0)">User</a></li>
                        <li class="breadcrumb-item active" aria-current="page">User Grid</li>
                    </ol>
                </nav>
                <!-- /Breadcrumb -->

                <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 row-cols-xl-4 gutters-sm">
                    <c:forEach items="${accounts}" var="acc">
                        <div class="col mb-3">
                            <div class="card">
                                <img src="/ISP392_Vi_Vay_No_123/assets/images/cover.jpg" alt="Cover" class="card-img-top">
                                <div class="card-body text-center">
                                    <img src="${!empty acc.avatarUrl ? acc.avatarUrl : '//bootdey.com/img/Content/avatar/avatar7.png'}" style="width:100px;margin-top:-65px" alt="User" class="img-fluid img-thumbnail rounded-circle border-0 mb-3">
                                    <h5 class="card-title">${acc.username}</h5>
                                    <p class="text-secondary mb-1">${acc.name}</p>
                                    <p class="text-muted font-size-sm" style="color: red !important">${acc.isActive ? 'Active':'Deactive'}</p>
                                </div>
                                <div class="card-footer">
                                    <a href="/ISP392_Vi_Vay_No_123/dashboardadmin?action=viewDetail&idAccount=${acc.id}" class="btn btn-light btn-sm bg-white has-icon btn-block" id="viewAccountDetail"><i class="material-icons">View Detail</i></a>
                                    <button class="btn btn-light btn-sm bg-white has-icon ml-2" type="button"><svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-message-circle"><path d="M21 11.5a8.38 8.38 0 0 1-.9 3.8 8.5 8.5 0 0 1-7.6 4.7 8.38 8.38 0 0 1-3.8-.9L3 21l1.9-5.7a8.38 8.38 0 0 1-.9-3.8 8.5 8.5 0 0 1 4.7-7.6 8.38 8.38 0 0 1 3.8-.9h.5a8.48 8.48 0 0 1 8 8v.5z"></path></svg></button>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </body>
</html>

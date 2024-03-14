<%-- 
    Document   : accountDetail
    Created on : Mar 7, 2024, 10:47:23 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="/ISP392_Vi_Vay_No_123/assets/css/styleAccountDetail.css"/>
    </head>
    <body>
        <% 
            ResultSet rName = (ResultSet)request.getAttribute("roleName");
        %>
        <div id="viewAccountDetail" class="container">
            <div class="row gutters">
                <div class="col-xl-3 col-lg-3 col-md-12 col-sm-12 col-12">
                    <div class="card h-100">
                        <div class="card-body" style="background-color:  #e3b7bc">
                            <div class="account-settings">
                                <div class="user-profile">
                                    <div class="user-avatar">
                                        <img src="${!empty accDetail.avatarUrl ? accDetail.avatarUrl : '//bootdey.com/img/Content/avatar/avatar7.png'}" alt="Maxwell Admin">
                                    </div>
                                    <i><h5 class="user-name" name="username">${accDetail.username}</h5></i>
                                    <h6 class="user-email" style="color: black">Role: <%if(rName.next()){%>
                                        <%=rName.getString("r.name")%>
                                        <%}%>
                                    </h6>
                                    <h6 class="user-email" style="color: black">Gender: ${accDetail.gender ? "Male" : "Female"}</h6>
                                </div>
                                <div class="about">
                                    <h5>Debit Note</h5>
                                    <p style="color:red">Status: ${accDetail.isActive ? "Active" : "Deactive"}</p>
                                    <p ><a href="dashboardadmin?action=adminViewDebtor&idAccounts=${accDetail.id}" style="color:red">Total Debtor: ${totalDebtor}</a></p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-xl-9 col-lg-9 col-md-12 col-sm-12 col-12">
                    <div class="card h-100">
                        <div class="card-body">
                            <div class="row gutters">
                                <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                                    <h6 class="mb-2 text-primary">Personal Details</h6>
                                </div>
                                <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                    <div class="form-group">
                                        <label for="fullName">Full Name</label>
                                        <input type="text" class="form-control" id="fullName" value="${accDetail.name}" disabled>
                                    </div>
                                </div>
                                <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                    <div class="form-group">
                                        <label for="eMail">Email</label>
                                        <input type="email" class="form-control" id="eMail" value="${accDetail.emailAddress}" disabled>
                                    </div>
                                </div>
                                <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                    <div class="form-group">
                                        <label for="phone">Phone</label>
                                        <input type="text" class="form-control" id="phone" value="${accDetail.mobileNumber}" disabled>
                                    </div>
                                </div>
                                <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                    <div class="form-group">
                                        <label for="website">Address</label>
                                        <input type="url" class="form-control" id="website" value="${accDetail.address}" disabled>
                                    </div>
                                </div>
                            </div>
                            <div class="row gutters">
                                <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                                    <h6 class="mt-3 mb-2 text-primary">Time</h6>
                                </div>
                                <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                    <div class="form-group">
                                        <label for="Street">Create At</label>
                                        <input type="name" class="form-control" id="Street" value="${accDetail.createAt}">
                                    </div>
                                </div>
                                <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                    <div class="form-group">
                                        <label for="ciTy">Update At</label>
                                        <input type="name" class="form-control" id="ciTy" value="${accDetail.updateAt}">
                                    </div>
                                </div>
                                <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                    <div class="form-group">

                                        <label for="sTate">Delete At</label>
                                        <input type="text" class="form-control" id="sTate" value="${accDetail.deleteAt}">
                                    </div>
                                </div>

                                <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                    <div class="form-group">
                                        <label for="zIp">Status:</label>
                                        <form id="updateStatusForm" action="account" method="post">
                                            <input type="hidden" name="action" value="updateIsActive">
                                            <input type="hidden" name="userName" value="${accDetail.username}">
                                            <input type="hidden" name="idAccount" value="${accDetail.id}">
                                            <select class="form-control" id="statusSelect" name="status">
                                                <option value="false" ${!accDetail.isActive ? "selected" : ""}>Deactive</option>
                                                <option value="true" ${accDetail.isActive ? "selected" : ""}>Active</option>
                                            </select>
                                            <div class="row gutters">
                                                <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                                                    <div class="text-right" style="margin-top: 35px;">
                                                        <a href="/ISP392_Vi_Vay_No_123/dashboardadmin" class="btn btn-secondary">Cancel</a>
                                                        <button type="submit" id="submitButton" class="btn btn-primary">Update</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
    </body>
</html>

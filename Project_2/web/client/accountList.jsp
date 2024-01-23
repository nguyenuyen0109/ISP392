<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

        <!-- DataTables CSS -->
        <link rel="stylesheet" href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.min.css">

        <!-- jQuery -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>

        <!-- Popper.js -->
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>

        <!-- Bootstrap JS -->
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

        <!-- DataTables JS -->
        <script src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.min.js"></script>

        <title>Account List</title>
    </head>
    <body>


        <%@ include file="/navigator/header.jsp" %>


        <%-- Check for update success/failure and display alert messages --%>
        <c:if test="${param.updateSuccess ne null}">
            <div class="alert alert-success" role="alert">
                Account updated successfully!
            </div>
        </c:if>
        <c:if test="${param.updateFailed ne null}">
            <div class="alert alert-danger" role="alert">
                Failed to update account.
            </div>
        </c:if>

        <div class="container mt-5">
            <div class="row">
                <div class="col-12">
                    <h2 class="mb-4">Account List</h2>
                </div>
            </div>
            <table id="accountTable" class="table table-striped table-borderless" style="width:100%">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Username</th>
                        <th>Name</th>
                        <th>Email Address</th>
                        <th>Role</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${accounts}" var="account">
                        <tr>
                            <td>${account.idAccount}</td>
                            <td>${account.username}</td>
                            <td>${account.name}</td>
                            <td>${account.emailAddress}</td>
                            <td>${account.roleIdRole == 1 ? 'Admin' : 'User'}</td>
                            <td>
                                <!-- Edit Button -->
                                <button type="button" class="btn btn-secondary btn-sm" data-toggle="modal" data-target="#editModal-${account.idAccount}">
                                    Edit
                                </button>

                                <!-- Edit Modal -->
                                <div class="modal fade" id="editModal-${account.idAccount}" tabindex="-1" role="dialog" aria-labelledby="editModalLabel-${account.idAccount}" aria-hidden="true">
                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title" id="editModalLabel-${account.idAccount}">Edit Account</h5>
                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>
                                            </div>
                                            <div class="modal-body">
                                                <!-- Editable Form Fields -->
                                                <form action="account?action=update" method="post">
                                                    <input type="hidden" name="id" value="${account.idAccount}">

                                                    <!-- Editable Fields -->
                                                    <div class="form-group" style="display: none;">
                                                        <label for="editUsername">Username:</label>
                                                        <input type="text" class="form-control" name="username" value="${account.username}">
                                                    </div>

                                                    <div class="form-group" style="display: none;">
                                                        <label for="editAmount">Amount:</label>
                                                        <input type="text" class="form-control" name="amount" value="${account.amount}">
                                                    </div>

                                                    <div class="form-group" style="display: none;">
                                                        <label for="editPassword">Password:</label>
                                                        <input type="password" class="form-control" name="password" value="${account.password}">
                                                    </div>

                                                    <!-- Add more editable fields as needed -->

                                                    <div class="form-group">
                                                        <label for="editName">Name:</label>
                                                        <input type="text" class="form-control" name="name" value="${account.name}">
                                                    </div>

                                                    <div class="form-group">
                                                        <label for="editMobileNumber">Mobile Number:</label>
                                                        <input type="text" class="form-control" name="mobileNumber" value="${account.mobileNumber}">
                                                    </div>

                                                    <div class="form-group" style="display: none;">
                                                        <label for="editEmailAddress">Email Address:</label>
                                                        <input type="text" class="form-control" name="emailAddress" value="${account.emailAddress}">
                                                    </div>

                                                    <div class="form-group">
                                                        <label for="editAddress">Address:</label>
                                                        <input type="text" class="form-control" name="address" value="${account.address}">
                                                    </div>

                                                    <div class="form-group" style="display: none;">
                                                        <label for="editAvatarUrl">Avatar URL:</label>
                                                        <input type="text" class="form-control" name="avatarUrl" value="${account.avatarUrl}">
                                                    </div>

                                                    <div class="form-group">
                                                        <label for="editGender">Gender:</label>
                                                        <select class="form-control" name="gender">
                                                            <option value="true" ${account.gender ? 'selected' : ''}>Male</option>
                                                            <option value="false" ${!account.gender ? 'selected' : ''}>Female</option>
                                                        </select>
                                                    </div>

                                                    <div class="form-group">
                                                        <label for="editIsActive">Active:</label>
                                                        <select class="form-control" name="isActive">
                                                            <option value="true" ${account.isActive ? 'selected' : ''}>Active</option>
                                                            <option value="false" ${!account.isActive ? 'selected' : ''}>Deactivate</option>
                                                        </select>
                                                    </div>

                                                    <div class="form-group">
                                                        <label for="editRoleIdRole">Role:</label>
                                                        <select class="form-control" name="roleIdRole">
                                                            <option value="1" ${account.roleIdRole == 1 ? 'selected' : ''}>Admin</option>
                                                            <option value="2" ${account.roleIdRole == 2 ? 'selected' : ''}>User</option>
                                                        </select>
                                                    </div>

                                                    <button type="submit" class="btn btn-primary">Update</button>
                                                </form>
                                              </div>
                                            <!-- Add additional modal content as needed -->
                                        </div>
                                    </div>
                                </div>

                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
        <script src="https://cdn.datatables.net/1.10.22/js/jquery.dataTables.min.js"></script>
        <script src="https://cdn.datatables.net/1.10.22/js/dataTables.bootstrap4.min.js"></script>

        <script>
            $(document).ready(function () {
                $('#accountTable').DataTable({
                    "pageLength": 10,
                    "lengthChange": false,
                    "info": false
                });
            });
        </script>

    </body>
</html>

<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Bootstrap CRUD Data Table for Database with Modal Form</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
        <style>

            .actions {
                display: flex;
                align-items: center;
                justify-content: center; /* ?i?u ch?nh n y d?a theo c ch b?n mu?n c c bi?u t??ng ???c hi?n th? */
            }
            .action-btn {
                margin: 0 5px; /* ?i?u ch?nh kho?ng c ch gi?a c c bi?u t??ng n?u c?n */
            }
            /* CSS ?? thay ??i m u c?a bi?u t??ng th m (gi? s? bi?u t??ng th m c  class 'add') */
            .action-btn.add .material-icons {
                color: #4CAF50; /* M u xanh l  */
            }
            /* CSS ?? thay ??i m u c?a bi?u t??ng con m?t (gi? s? bi?u t??ng con m?t c  class 'view') */
            .action-btn.view .material-icons {
                color: #2196F3; /* M u xanh d??ng */
            }
            .button {
                background-color: #2196F3; /* M u n?n ban ??u c?a n t l  m u xanh d??ng */
            }

            /* CSS ?? thay ??i m u c?a n t 'Add New Debtor' */
            .btn-success {
                background-color: #ff69b4; /* M  m u h?ng */
                border-color: #ff69b4; /* ??m b?o vi?n c?ng l  m u h?ng */
            }

            /* N?u b?n mu?n thay ??i m u khi ng??i d ng di chu?t qua n t */
            .btn-success:hover {
                background-color: #ff5c8a; /* M?t m u h?ng ??m h?n khi hover */
                border-color: #ff5c8a;
            }
            /* CSS ?? thay ??i m u c?a n t c  ID 'add-new-debtor-btn' */
            #add-new-debtor-btn {
                background-color: #ff69b4; /* M  m u h?ng */
                border-color: #ff69b4; /* ??m b?o vi?n c?ng l  m u h?ng */
            }

            /* Thay ??i m u khi hover */
            #add-new-debtor-btn:hover {
                background-color: #ff5c8a; /* M?t m u h?ng ??m h?n khi hover */
                border-color: #ff5c8a;
            }



            body {
                color: #566787;
                background: #f5f5f5;
                font-family: 'Varela Round', sans-serif;
                font-size: 13px;
            }
            .table-responsive {
                margin: 30px 0;
            }
            .table-wrapper {
                background: #fff;
                padding: 20px 25px;
                border-radius: 3px;
                min-width: 1000px;
                box-shadow: 0 1px 1px rgba(0,0,0,.05);
            }
            .table-title {
                padding-bottom: 15px;
                background: #435d7d;
                color: #fff;
                padding: 16px 30px;
                min-width: 100%;
                margin: -20px -25px 10px;
                border-radius: 3px 3px 0 0;
            }
            .table-title h2 {
                margin: 5px 0 0;
                font-size: 24px;
            }
            .table-title .btn-group {
                float: right;
            }
            .table-title .btn {
                color: #fff;
                float: right;
                font-size: 13px;
                border: none;
                min-width: 50px;
                border-radius: 2px;
                border: none;
                outline: none !important;
                margin-left: 10px;
            }
            .table-title .btn i {
                float: left;
                font-size: 21px;
                margin-right: 5px;
            }
            .table-title .btn span {
                float: left;
                margin-top: 2px;
            }
            table.table tr th, table.table tr td {
                border-color: #e9e9e9;
                padding: 12px 15px;
                vertical-align: middle;
            }
            table.table tr th:first-child {
                width: 60px;
            }
            table.table tr th:last-child {
                width: 100px;
            }
            table.table-striped tbody tr:nth-of-type(odd) {
                background-color: #fcfcfc;
            }
            table.table-striped.table-hover tbody tr:hover {
                background: #f5f5f5;
            }
            table.table th i {
                font-size: 13px;
                margin: 0 5px;
                cursor: pointer;
            }
            table.table td:last-child i {
                opacity: 0.9;
                font-size: 22px;
                margin: 0 5px;
            }
            table.table td a {
                font-weight: bold;
                color: #566787;
                display: inline-block;
                text-decoration: none;
                outline: none !important;
            }
            table.table td a:hover {
                color: #2196F3;
            }
            table.table td a.edit {
                color: #FFC107;
            }
            table.table td a.delete {
                color: #F44336;
            }
            table.table td i {
                font-size: 19px;
            }
            table.table .avatar {
                border-radius: 50%;
                vertical-align: middle;
                margin-right: 10px;
            }
            .pagination {
                float: right;
                margin: 0 0 5px;
            }
            .pagination li a {
                border: none;
                font-size: 13px;
                min-width: 30px;
                min-height: 30px;
                color: #999;
                margin: 0 2px;
                line-height: 30px;
                border-radius: 2px !important;
                text-align: center;
                padding: 0 6px;
            }
            .pagination li a:hover {
                color: #666;
            }
            .pagination li.active a, .pagination li.active a.page-link {
                background: #03A9F4;
            }
            .pagination li.active a:hover {
                background: #0397d6;
            }
            .pagination li.disabled i {
                color: #ccc;
            }
            .pagination li i {
                font-size: 16px;
                padding-top: 6px
            }
            .hint-text {
                float: left;
                margin-top: 10px;
                font-size: 13px;
            }
            /* Custom checkbox */
            .custom-checkbox {
                position: relative;
            }
            .custom-checkbox input[type="checkbox"] {
                opacity: 0;
                position: absolute;
                margin: 5px 0 0 3px;
                z-index: 9;
            }
            .custom-checkbox label:before{
                width: 18px;
                height: 18px;
            }
            .custom-checkbox label:before {
                content: '';
                margin-right: 10px;
                display: inline-block;
                vertical-align: text-top;
                background: white;
                border: 1px solid #bbb;
                border-radius: 2px;
                box-sizing: border-box;
                z-index: 2;
            }
            .custom-checkbox input[type="checkbox"]:checked + label:after {
                content: '';
                position: absolute;
                left: 6px;
                top: 3px;
                width: 6px;
                height: 11px;
                border: solid #000;
                border-width: 0 3px 3px 0;
                transform: inherit;
                z-index: 3;
                transform: rotateZ(45deg);
            }
            .custom-checkbox input[type="checkbox"]:checked + label:before {
                border-color: #03A9F4;
                background: #03A9F4;
            }
            .custom-checkbox input[type="checkbox"]:checked + label:after {
                border-color: #fff;
            }
            .custom-checkbox input[type="checkbox"]:disabled + label:before {
                color: #b8b8b8;
                cursor: auto;
                box-shadow: none;
                background: #ddd;
            }
            /* Modal styles */
            .modal .modal-dialog {
                max-width: 400px;
            }
            .modal .modal-header, .modal .modal-body, .modal .modal-footer {
                padding: 20px 30px;
            }
            .modal .modal-content {
                border-radius: 3px;
                font-size: 14px;
            }
            .modal .modal-footer {
                background: #ecf0f1;
                border-radius: 0 0 3px 3px;
            }
            .modal .modal-title {
                display: inline-block;
            }
            .modal .form-control {
                border-radius: 2px;
                box-shadow: none;
                border-color: #dddddd;
            }
            .modal textarea.form-control {
                resize: vertical;
            }
            .modal .btn {
                border-radius: 2px;
                min-width: 100px;
            }
            .modal form label {
                font-weight: normal;
            }
        </style>
        <script>
            $(document).ready(function () {
                // Activate tooltip
                $('[data-toggle="tooltip"]').tooltip();

                // Select/Deselect checkboxes
                var checkbox = $('table tbody input[type="checkbox"]');
                $("#selectAll").click(function () {
                    if (this.checked) {
                        checkbox.each(function () {
                            this.checked = true;
                        });
                    } else {
                        checkbox.each(function () {
                            this.checked = false;
                        });
                    }
                });
                checkbox.click(function () {
                    if (!this.checked) {
                        $("#selectAll").prop("checked", false);
                    }
                });
            });
        </script>



    </head>
    <jsp:include page="/navigator/toast.jsp" />
    <div class="container-xl">
        <div class="table-responsive">
            <div class="table-wrapper">
                <div class="table-title">
                    <div class="row">
                        <div class="col-sm-6">
                            <h2>Debtor <b>List</b></h2>
                        </div>

                        <div class="col-sm-6">
                            <a href="#addEmployeeModal" class="btn btn-success" id="add-new-debtor-btn" data-toggle="modal"><i class="material-icons">&#xE147;</i> <span>Add New Debtor</span></a>
                            <br> </br>

                        </div>
                    </div>
                </div>

                
                <div class="home-filter">
                    <div class="selection-input">
                        <div class="btn-group">
                                        <button type="button" class="btn btn-info dropdown-toggle sort-button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
    
                                Sorted by
                            </button>
                            <div class="dropdown-menu">
                                <a class="dropdown-item" href="sort?action=sortByOldest">Oldest</a>
                                <a class="dropdown-item" href="sort?action=sortByNewest">Newest</a>
                                <a class="dropdown-item" href="sort?action=sortByLargest">Total Debt from high to low</a>
                                <a class="dropdown-item" href="sort?action=sortBySmallest">Total Debt from low to high</a>
                            </div>
                        </div>
                    </div>
                </div>
                





                <table class="table table-striped table-hover">
                    <thead>
                        <tr>                       
                            <th>ID</th>
                            <th>Name</th>
                            <th>Address</th>
                            <th>Phone</th>
                            <th>Email</th>
                            <th>Total Debt</th>
                            <th>Created At</th>
                            <th>Update At</th>
                            <th>Actions</th>
                        </tr>
                    <form action="debtor" method="post" class="mt-2">
                        <div class="input-group">
                            <div class="row">
                                <div class="col">
                                    <input type="text" name="searchName" class="form-control" placeholder="Name">
                                    <input type="hidden" name="action" value="search"/>
                                </div>
                                <div class="col">
                                    <input type="text" name="searchAddress" class="form-control" placeholder="Address">
                                    <input type="hidden" name="action" value="search"/>
                                </div>
                                <div class="col">
                                    <input type="text" name="searchPhone" class="form-control" placeholder="Phone">
                                    <input type="hidden" name="action" value="search"/>
                                </div>
                                <div class="col">
                                    <input type="text" name="searchMail" class="form-control" placeholder="Email">
                                    <input type="hidden" name="action" value="search"/>
                                </div>
                                <div class="col">
                                    <button type="submit" class="btn btn-primary">Search</button>
                                </div>
                            </div>
                        </div>
                    </form>
                    </thead>
                    <c:forEach items="${listDebtor}" var="debtor">
                        
              
                     <tbody>
                            <tr>						
                                <td>${debtor.id}</td>
                                <td>${debtor.name}</td>
                                <td>${debtor.address}</td>
                                <td>${debtor.phone}</td>
                                <td>${debtor.email}</td>
                                <td>${debtor.totalDebt}</td>
                                <td><fmt:formatDate value="${debtor.createdAt}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                <td><fmt:formatDate value="${debtor.updatedAt}" pattern="yyyy-MM-dd HH:mm:ss"/></td>                                                                                                

                                <td class="actions">
                                    <!--<a href="./debt" class="view action-btn" title="View Details" data-toggle="tooltip"><i class="material-icons">&#xE417;</i></a>-->
                                    <!--<a href="/ISP392_Vi_Vay_No_123/debt?debtorid=${debtor.id}" class="view action-btn" title="View Details" data-toggle="tooltip"><i class="material-icons">&#xE417;</i></a>-->
                                    <!--<a href="/ISP392_Vi_Vay_No_123/debt" class="view action-btn" title="View Details" data-toggle="tooltip"><i class="material-icons">&#xE417;</i></a>-->
                                    <a href="/ISP392_Vi_Vay_No_123/debt" class="view action-btn" title="View Details" data-toggle="tooltip"><i class="material-icons">&#xE417;</i></a>
                                    <a href="debtNotePath/${debtor.id}" class="add action-btn" title="Add Debt Note" data-toggle="tooltip"><i class="material-icons">&#xE147;</i></a>
                                    <a href="editPath/${debtor.id}" class="edit action-btn" title="Edit" data-toggle="tooltip"><i class="material-icons">&#xE254;</i></a>
                                </td>

                            </tr>					
                         </tbody>
                    </c:forEach>
                </table>
                <div class="clearfix">
                    <ul class="pagination">
                        <li class="page-item disabled"><a href="#">Previous</a></li>
                        <li class="page-item"><a href="#" class="page-link">1</a></li>
                        <li class="page-item"><a href="#" class="page-link">2</a></li>
                        <li class="page-item active"><a href="#" class="page-link">3</a></li>
                        <li class="page-item"><a href="#" class="page-link">4</a></li>
                        <li class="page-item"><a href="#" class="page-link">5</a></li>
                        <li class="page-item"><a href="#" class="page-link">Next</a></li>
                    </ul>
                </div>
            </div>
        </div>        
    </div>
    <!-- Edit Modal HTML -->
    <div id="addEmployeeModal" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <form action="debtor" method="POST">
                    <div class="modal-header">						
                        <h4 class="modal-title">Add New Debtor</h4>
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    </div>
                    <div class="modal-body">					
                        <div class="form-group">
                            <label>Name</label>
                            <input type="text" class="form-control" name="name" required>
                        </div>
                        <div class="form-group">
                            <label>Email</label>
                            <input type="email" class="form-control" name="email" required>
                        </div>
                        <div class="form-group">
                            <label>Address</label>
                            <textarea class="form-control" name="address" required></textarea>
                        </div>
                        <div class="form-group">
                            <label>Phone</label>
                            <input type="text" class="form-control" name="phone" required>
                        </div>	
                        <!-- New textbox for "Total" with a default value of 0 -->
                        <div class="form-group">
                            <label>Total</label>
                            <input type="text" class="form-control" required name="totalDebt" value="0">

                            <c:if test="${not empty successMessage}">
                                <script type="text/javascript">
                                    alert('${successMessage}');
                                </script>
                            </c:if>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                        <input type="submit" class="btn btn-success" value="Add">
                        <input type="hidden" name="action" value="add"/>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <!-- Edit Modal HTML -->
    <div id="editEmployeeModal" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <form>
                    <div class="modal-header">						
                        <h4 class="modal-title">Edit Debtor</h4>
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    </div>
                    <div class="modal-body">					
                        <div class="form-group">
                            <label>Name</label>
                            <input type="text" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label>Email</label>
                            <input type="email" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label>Address</label>
                            <textarea class="form-control" required></textarea>
                        </div>
                        <div class="form-group">
                            <label>Phone</label>
                            <input type="text" class="form-control" required>
                        </div>					
                    </div>
                    <div class="modal-footer">
                        <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                        <input type="submit" class="btn btn-info" value="Save">
                    </div>
                </form>
            </div>
        </div>
    </div>
    <!-- Delete Modal HTML -->
    <div id="deleteEmployeeModal" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <form>
                    <div class="modal-header">						
                        <h4 class="modal-title">Delete Debtor</h4>
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    </div>
                    <div class="modal-body">					
                        <p>Are you sure you want to delete these Records?</p>
                        <p class="text-warning"><small>This action cannot be undone.</small></p>
                    </div>
                    <div class="modal-footer">
                        <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                        <input type="submit" class="btn btn-danger" value="Delete">
                    </div>
                </form>
            </div>
        </div>
    </div>
    
    <script>
        function validateForm() {
        var name = $('#name').val();
        var address = $('#address').val();
        var phone = $('#phone').val();
        var email = $('#email').val();
        var valid = true;

        // Validate name (only letters)
        if (!/^[a-zA-Z\s]+$/.test(name)) {
            $('#nameError').html('Name must contain only letters');
            valid = false;
        } else {
            $('#nameError').html('');
        }

        // Validate address (letters and numbers)
        if (!/^[a-zA-Z0-9\s]+$/.test(address)) {
            $('#addressError').html('Address must contain only letters and numbers');
            valid = false;
        } else {
            $('#addressError').html('');
        }

        // Validate phone (only numbers)
        if (!/^[0-9]+$/.test(phone)) {
            $('#phoneError').html('Phone must contain only numbers');
            valid = false;
        } else {
            $('#phoneError').html('');
        }

        // Validate email format
        if (!/^[\w-]+(\.[\w-]+)*@([\w-]+\.)+[a-zA-Z]{2,7}$/.test(email)) {
            $('#emailError').html('Invalid email address');
            valid = false;
        } else {
            $('#emailError').html('');
        }

        return valid;
    }
    </script>
    
    
    
    
   
    
</body>
</html>
<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Debt List</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <!--<link rel="stylesheet" href="asset/css/styleDebtList.css">-->
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
        <script src="https://kit.fontawesome.com/5bd22a55e3.js" crossorigin="anonymous"></script>
        <style>
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
                display: flex;
                align-items: center;
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
            /* table.table td a.delete {
                    color: #F44336;
            } */
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

            /* tu viet them phan tim kiem */
            .table-title .search{
                height: 33.8px;
                flex: 1;
                background-color: #fff;
                margin: 0 35px;
                border-radius: 2px;
                display: flex;
                align-items: center;
            }
            .search-input{
                flex: 1;
                height: 100%;
                border: none;
                outline: none;
                padding: 0 16px;
                border-radius: 2px;
            }
            .search-selection{
                    color: #757575;
                    position: relative;
                    border-left: 1px solid #ccc;
                    padding-left: 14px;
            }
            .search-selection-label{
                    font-size: 14px;
                    color: #757575;
                    border: none !important;
                    outline: none !important;
            } 
            
            .search-selection-item{
                background-color: #fff;
                padding: 8px 8px;
            }
            .search-selection-icon{
                font-size: 10px;
                margin: 0 8px 0 2px;
                /* ben phai 8, ben trai 4 */
                position: relative;
                top: 0.5px
            }
            .search-btn{
                background-color: #435d7d;
                border: none;
                margin: 0 3px 0 5px;
                border-radius: 3px;
                height: 30px;
                width: 40px;
                outline: none !important;
                /* khong hien thi ai vien khi bam o search */
            }
            .search-btn-icon{
                color: white;
                font-size: 15px;
            }
            .search-btn:hover{
                background-color: #4D6380;
            }
            
            /* tu viet them phan sap xep */
            .home-filter{
                font-size: 13px;
                display: flex;
                align-items: center;
                padding: 12px 20px;
                border-radius: 2px;

            }
            .selection-input{
                position: relative;
                min-width: 200px;
                height: 13px;
                display: flex;
                align-items: center;
                /* justify-content: khoang cach chu va icon o 2 dau */
            }
            .selection-input:hover .select-input__list{
                display: block;
            }
            .select-input__label{
                position: relative;
                min-width: 70px;
                height: 13px;
                padding: 0;
                display: flex;
                align-items: center;
                margin-left: 0;
                font-size: 13px;
                /* color: #566787; */

            }
            .sort-icon{
                font-size: 0.6rem;
                margin: 0 16px 0 8px;
            }
            .select-input_item{
                margin-top: 7px;
                list-style: none;
            }
            .select-input__list{
                position: absolute;
                left: 0;
                right: 0;
                top: 24px;
                border-radius: 2px;
                background-color: #f5f5f5;
                padding: 8px 16px;
                list-style: none;
                /* list style: bo dau cham cua the li */
                display: none;
                box-shadow: 0 1px 3px #ccc;
            }
            .select-input__list::after{
                /* day la cau noi giua sortby voi cac option */
                content: "";
                display: block;
                width: 100%;
                height: 10px;
                position: absolute;
                top: -10px;
                left: 0;
            }
            .select-input__link{
                color: var(--text-color);
                padding: 4px 0;
                text-decoration: none;
                display: block;
                list-style: none;
            }
            .btn-filter{
                min-width: 85px;
                height: 34px;
                text-decoration: none;
                /* text-decoration: loai bo gach chan tu van ban */
                border: none;
                border-radius: 2px;
                font-size: 13px;
                padding: 0 12px;
                outline: none !important;
                background: white;
                color: #566787;
                align-items: center;
                justify-content: center;
                /* line-height: 1.6rem; */
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
    <body>
        <div class="container-xl">
            <div class="table-responsive">
                <div class="table-wrapper">
                    <div class="table-title">
                        <div>
                            <h2>Debt List</h2>
                        </div>
                        <form action="debt" method="get" class="search">
                            <input type="text" class="search-input" placeholder="Search Debt" name="searchQuery">
                            <div class="search-selection">
                                <select class="search-selection-label" name="searchType" >
                                    <option class="search-selection-item">Search by</option>
                                    <option class="search-selection-item" value="description">Description</option>
                                    <option class="search-selection-item" value="amount">Amount</option>
                                </select>
                            </div>
                            <button class="search-btn" type="submit" name="action" value="search">
                                <i class="search-btn-icon fa-solid fa-magnifying-glass"></i>
                            </button>
                        </form>
                        <div>
                            <a href="#addEmployeeModal" class="btn btn-success" data-toggle="modal"><i class="material-icons">&#xE147;</i> <span>Add New Debt</span></a>
                        </div>

                    </div>

                    <div class="home-filter">
                        <div class="selection-input">
                            <span class="select-input__label">Sorted by</span>
                            <i class="sort-icon fa-solid fa-angle-down"></i>	
                            <ul class="select-input__list">
                                <li class="select-input_item">
                                    <a href="debt?action=sortByOldest" class="select-input__link">Oldest</a>
                                </li>
                                <li class="select-input_item">
                                    <a href="debt?action=sortByNewest" class="select-input__link">Newest</a>
                                </li>
                                <li class="select-input_item">
                                    <a href="debt?action=sortByHighLow" class="select-input__link">Amount from high to low</a>
                                </li>
                                <li class="select-input_item">
                                    <a href="debt?action=sortByLowHigh" class="select-input__link">Amount from low to high</a>
                                </li>
                                <li class="select-input_item">
                                    <a href="debt?action=Receivable" class="select-input__link">Receivable</a>
                                </li>
                                <li class="select-input_item">
                                    <a href="debt?action=Debt" class="select-input__link">Debt</a>
                                </li>
                            </ul>
                        </div>

                    </div>

                    <table class="table table-striped table-hover">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Description</th>						
                                <th>Debt Type</th>
                                <th>Amount</th>
                                <th>Create At</th>
                            </tr>
                        </thead>
                        <c:forEach items="${debtList}" var="debt">
                            <tbody>
                                <tr>						
                                    <td>${debt.id}</td>
                                    <td>${debt.description}</td>
                                    <td>${debt.deptType == false ? 'Debt' : 'Receivable'}</td>
                                    <td>${debt.amount}</td>
                                    <td>${debt.creatAt}</td>                                                                                             
                                    <td>
                                        <a href="#editEmployeeModal" class="edit" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i></a>
                                        <!--<a href="#deleteEmployeeModal" class="delete" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i></a>-->
                                    </td>
                                </tr>					
                            </tbody>
                        </c:forEach>
                    </table>

                </div>
            </div>        
        </div>
        <!-- search -->

        <!-- Edit Modal HTML -->
        <div id="addEmployeeModal" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form action="debt" method="post">
                        <div class="modal-header">						
                            <h4 class="modal-title">Add New Debt</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        </div>
                        <div class="modal-body">					
                            <div class="form-group">
                                <label>Description</label>
                                <input type="text" class="form-control" name="description" required>
                            </div>
                            <div class="form-group">
                                <label>Debt type</label>
                                <!--<input type="text" class="form-control" required>-->
                                <select class="form-control" name="debtType" required>
                                    <option value="0">Receivable</option>  
                                    <option value="1">Debt</option>  
                                    <!--                                                    Debt: Mình n? ngkhac
                                                                                        Receivable: ngkhac n? mình -->
                                </select>
                            </div>
                            <div class="form-group">
                                <label>Amount</label>
                                <input type="number" class="form-control" name="amount" required>
                            </div>
                            <div class="form-group">
                                <label>Interest rate</label>
                                <select class="form-control" name="interest_rate" required>
                                    <option value="1">10%</option>  
                                    <option value="2">Debt</option>  
                                    <!--                                                    Debt: Mình n? ngkhac
                                                                                        Receivable: ngkhac n? mình -->
                                </select>
                            </div>					
                        </div>
                        <div class="modal-footer">
                            <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                            <input name="submit" type="submit" class="btn btn-success" value="Add">
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <!-- Pagination -->
         <jsp:include page="../client/pagination.jsp"></jsp:include>

    </body>
</html>
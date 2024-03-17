<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>DEBTOR LIST</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
        <script src="https://kit.fontawesome.com/5bd22a55e3.js" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="/ISP392_Vi_Vay_No_123/assets/css/styleDebtor.css">
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
        <jsp:include page="/navigator/toast.jsp" />
        <jsp:include page="/navigator/header.jsp" />
        <div class="container-xl">
            <div class="table-responsive">
                <div class="table-wrapper">
                    <div class="table-title">
                        <div class="row">
                            <div class="col-sm-6">
                                <h2>Debtor <b>List</b></h2>
                            </div>
                            <form action="dashboardadmin" method="get" class="search">
                                <input type="text" class="search-input" placeholder="Search Debtor" name="searchQuery">
                                <input type="hidden" class="search-input" name="idAccounts" value="${idAccount}">
<!--                                <div class="search-selection">
                                    <select class="search-selection-label" name="searchType" >
                                        <option class="search-selection-item">Search by</option>
                                        <option class="search-selection-item" value="name">Name</option>
                                        <option class="search-selection-item" value="address">Address</option>
                                        <option class="search-selection-item" value="phone">Phone</option>
                                        <option class="search-selection-item" value="email">Email</option>
                                    </select>
                                </div>-->
                                <button class="search-btn" type="submit" name="action" value="search">
                                    <i class="search-btn-icon fa-solid fa-magnifying-glass"></i>
                                </button>
                            </form>
                        </div>

                    </div>

                    <div class="home-filter">
                        <div class="selection-input">
                            <span class="select-input__label">Sorted by</span>
                            <i class="sort-icon fa-solid fa-angle-down"></i>	
                            <ul class="select-input__list">
                                <li class="select-input_item">
                                    <a href="dashboardadmin?action=sortByOldest&idAccounts=${idAccount}" class="select-input__link">Oldest</a>
                                </li>
                                <li class="select-input_item">
                                    <a href="dashboardadmin?action=sortByNewest&idAccounts=${idAccount}" class="select-input__link">Newest</a>
                                </li>
                                <li class="select-input_item">
                                    <a href="dashboardadmin?action=sortByHighLow&idAccounts=${idAccount}" class="select-input__link">Amount from high to low</a>
                                </li>
                                <li class="select-input_item">
                                    <a href="dashboardadmin?action=sortByLowHigh&idAccounts=${idAccount}" class="select-input__link">Amount from low to high</a>
                                </li>
                            </ul>
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
                                        <a href="/ISP392_Vi_Vay_No_123/dashboardadmin?action=adminViewDebt&idAccountDebtor=${debtor.account_id}&debtorid=${debtor.id}" class="view action-btn" title="View Details" data-toggle="tooltip"><i class="material-icons">&#xE417;</i></a>
                                        <a href="debtNotePath/${debtor.id}" class="add action-btn" title="Add Debt Note" data-toggle="tooltip"><i class="material-icons">&#xE147;</i></a>
                                        <!-- Edit Debtor detail -->
                                        <i class="fa fa-edit fa-2x"
                                           style="color: #469408"
                                           data-toggle="modal"
                                           data-target="#editDebtorDetailModal"
                                           onclick="editDebtorDetailModal(
                                                           `${debtor.id}`,
                                                           `${debtor.name}`,
                                                           `${debtor.address}`,
                                                           `${debtor.phone}`,
                                                           `${debtor.email}`,
                                           ${debtor.totalDebt})">
                                        </i>


                                        &nbsp;&nbsp;&nbsp;
                                    </td>

                                </tr>					
                            </tbody>
                        </c:forEach>
                    </table>
                    <input type="hidden" name="uri" value="/client/debtor.jsp">
                </div>
            </div>        
        </div>

            <!-- Edit Debtor detail Modal  -->
        <jsp:include page="../client/editDebtorDetailModal.jsp"></jsp:include>

            <!-- Pagination  -->
        <jsp:include page="../client/pagination.jsp"></jsp:include>

    </body>
</html>
<%@ page language="java" %>
<%@page import="java.sql.ResultSet" %>
<%@ page import="java.text.NumberFormat" %>
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
        <link rel="stylesheet" href="./assets/css/styleDebtList.css">
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
        <script src="https://kit.fontawesome.com/5bd22a55e3.js" crossorigin="anonymous"></script>
        <script src="./assets/js/aboutDebtList.js"></script>
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
        <jsp:include page="../navigator/header.jsp" />
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
                                <th>Create At</th>
                                <th>Amount</th>
                                <th>Interest rate</th>
                                <th>Total Amount</th>
                                <th>Due</th>
                            </tr>
                        </thead>
                        <%
                            ResultSet rs = (ResultSet)request.getAttribute("rs");
                        %>
                        <c:forEach items="${debtList}" var="debt">
                            <c:set var="totalAmount" value="${debt.amount*0.01+debt.amount*(1+(debt.interestRate/100)*((debt.due)/12))}"/>
                            <tbody>
                                <tr>						
                                    <td>${debt.id}</td>
                                    <td>${debt.description}</td>
                                    <td>${debt.debtType == true ? 'Debt' : 'Receivable'}</td>
                                    <td><fmt:formatDate value="${debt.createAt}" pattern="yyyy-MM-dd" /></td>
                                    <td><fmt:formatNumber value="${debt.amount}" pattern="#,##0"/></td>
                                    <td>${debt.interestRate}</td>
                                    <td><fmt:formatNumber value="${totalAmount}" pattern="#,##0.0"/></td>
                                    <td>${debt.due}</td>
                                    <td>
                                        <a href="#editEmployeeModal" class="edit" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i></a>
                                    </td>
                                </tr>
                            </tbody>
                        </c:forEach>
                        <th>Total: </th>
                        <td>
                            <% if (rs.next()) { 
                                double totalDebt = rs.getDouble("totalDebt");
                                NumberFormat formatter = NumberFormat.getNumberInstance();
                                formatter.setMinimumFractionDigits(1);
                                formatter.setMaximumFractionDigits(1); 
                                formatter.setGroupingUsed(true);
                                String formattedTotalDebt = formatter.format(totalDebt);
                            %>
                            <%= formattedTotalDebt %>
                            <% } %>
                        </td>
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
                                <select class="form-control" name="debtType" required>
                                    <option value="false">Receivable</option>  
                                    <option value="true">Debt</option>  
                                    <!--                                                    Debt: Mình n? ngkhac = true
                                                                                        Receivable: ngkhac n? mình = false -->
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="image">Image</label>
                                <!-- Image element to display the selected image -->
                                <img class="w-100 rounded mt-2 mb-2" id="previewImage" src="" alt="Preview Image">

                                <input type="file" class="form-control" id="fileInput" onchange="handleFileSelect(event)">
                                <input type="hidden" name="image">
                            </div>
                            <div class="form-group">
                                <label>Amount</label>
                                <input type="number" class="form-control" name="amount" required>
                            </div>
                            <div class="form-group">
                                <label>Interest Rate (%/year)</label>
                                <input type="number" class="form-control" name="interest" required>
                            </div>
                            <div>
                                <label>Due(month)</label>
                                <input type="number" class="form-control" name="due" required>
                            </div>
                            <div class="form-group">
                                <label>Total Amount</label>
                                <input type="double" class="form-control" id="totalAmount" readonly>
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
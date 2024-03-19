<%@ page language="java" %>
<%@page import="java.sql.ResultSet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>DEBT LIST</title>
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
        <script src="./assets/js/description.js"></script>
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
        <jsp:include page="/navigator/header.jsp" />
        <div class="container-xl">
            <div class="table-responsive">
                <div class="table-wrapper">
                    <div class="table-title">
                        <div>
                            <h2>Debt List</h2>
                            <c:if test="${not empty debtList}">
                                <c:set var="firstDebt" value="${debtList[0]}" />
                                <h3 style="font-size: 13px; margin-top: 9px">Debtor Name: ${firstDebt.debtorName}</h3>
                            </c:if>
                        </div>
                        <form action="debt" method="get" class="search">
                            <input type="text" class="search-input" placeholder="Search Debt" name="searchQuery">
                           
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
                                <li class="select-input_item">
                                    <a href="debt?action=Loan" class="select-input__link">Loan</a>
                                </li>
                                <li class="select-input_item">
                                    <a href="debt?action=Len" class="select-input__link">Lend</a>
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
                                <th>Interest Rate</th>
                                <th>Due</th>
                                <th>Total Amount</th>
                                <th>Debt issuance</th>

                            </tr>
                        </thead>
                        <c:forEach items="${debtList}" var="debt">
                            <tr>                        
                                <td>${debt.id}</td>
                                <td class="description">${debt.description}</td>
                                <td>${debt.debtTypeName}</td>
                                <td><fmt:formatNumber value="${debt.amount}" pattern="#,##0"/></td>
                                <td>${debt.interestRate}</td>
                                <td>${debt.due}</td>
                                <td><fmt:formatNumber value="${debt.totalAmount}" pattern="#,##0"/></td>
                                <td><fmt:formatDate value="${debt.debtIssuance}" pattern="yyyy-MM-dd" /></td>       

                                <td>
                                    <button class="btn btn-info view-details-btn" 
                                            data-id="${debt.id}" 
                                            data-description="${debt.description}" 
                                            data-amount="${debt.amount}" 
                                            data-debttype="${debt.debtTypeName}" 
                                            data-img="${debt.image}"
                                            data-debtissuance="<fmt:formatDate value='${debt.debtIssuance}' pattern='yyyy-MM-dd' />">
                                        View Details
                                    </button>

                                    <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#reportModal${debt.id}">Delete </button>
                                </td>

                            </tr>	

                            <!-- Report Modal -->
                            <div id="reportModal${debt.id}" class="modal fade">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <form action="IsDeleteDebt" method="get" >
                                            <div class="modal-header">                        
                                                <h4 class="modal-title">Are you sure to delete this records?</h4>
                                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                            </div>
                                            <div class="modal-body">    
                                                <input type="hidden" name="debtId" id="debtId" value="${debt.id}">
                                                <input type="hidden" name="debtorid" id="debtorid" value="${param.debtorid}">
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                                <button type="submit" class="btn btn-danger">Delete</button>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>

                    </table>


                    <!-- Report Modal -->
                    <script>
                        $(document).ready(function () {
                            function readURL(input) {
                                if (input.files && input.files[0]) {
                                    var reader = new FileReader();

                                    reader.onload = function (e) {
                                        $('#previewImage').attr('src', e.target.result);
                                        $('#previewImage').show();
                                    }

                                    reader.readAsDataURL(input.files[0]);
                                }
                            }

                            $("#imageUpload").change(function () {
                                readURL(this);
                            });
                        });
                    </script>

                </div>
            </div>        
        </div>
        <!-- search -->
        <!-- Edit Modal HTML -->

        <!-- Pagination -->
        <jsp:include page="../client/pagination.jsp"></jsp:include>

            <script>
                $(document).ready(function () {
                    $('.view-details-btn').click(function () {
                        var description = $(this).data('description');
                        var amount = $(this).data('amount');
                        var debtType = $(this).data('debttype');
                        var debtIssuance = $(this).data('debtissuance');
                        var img = $(this).data('img');

                        // C?p nh?t modal
                        $('#viewDebtDetailModal').find('.modal-body .description').text(description);
                        $('#viewDebtDetailModal').find('.modal-body .amount').text(amount);
                        $('#viewDebtDetailModal').find('.modal-body .debt-type').text(debtType);
                        $('#viewDebtDetailModal').find('.modal-body .debt-issuance').text(debtIssuance);
                        $('#viewDebtDetailModal').find('.modal-body .img').attr('src', img);

                        // Hi?n th? modal
                        $('#viewDebtDetailModal').modal('show');
                    });
                });
            </script>
            <!-- View Debt Details Modal -->
            <div id="viewDebtDetailModal" class="modal fade">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">                        
                            <h4 class="modal-title">Debt Details</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        </div>
                        <div class="modal-body">                    
                            <div class="form-group">
                                <p>Description:</p>
                                <p class="form-control-static description"></p>
                            </div>
                            <div class="form-group">
                                <p>Amount:</p>
                                <p class="form-control-static amount"></p>
                            </div>
                            <div class="form-group">
                                <p>Debt Type:</p>
                                <p class="form-control-static debt-type"></p>
                            </div>
                            <div class="form-group">
                                <p>Debt Issuance:</p>
                                <p class="form-control-static debt-issuance"></p>
                            </div>

                            <div class="form-group">
                                <p>Image:</p>
                                <image  class="form-control-static img" src="" style="
                                        height: 100%;
                                        width: 100%;
                                        ">
                            </div> 
                        </div>

                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>

                        </div>

                    </div>
                </div>
            </div>

        <%
            ResultSet rsDebtname = (ResultSet)request.getAttribute("debtTypeName");
        %>
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
                                <p>Description</p>
                                <input type="text" class="form-control" name="description" required>
                            </div>
                            <div class="search-selection-item">
                                <p>Debt Type:</p>
                                <select name="debtType">
                                    <%while(rsDebtname.next()){%>
                                    <option value="<%=rsDebtname.getInt(1)%>"><%=rsDebtname.getString(2)%></option>
                                    <%}%>
                                </select>
                            </div>
                            <div class="form-group">
                                <p for="Image">Image</p>
                                <img class="w-100 rounded mt-2 mb-2" id="previewimage" src="" alt="Preview Image">
                                <input type="file" class="form-control-file" id="FileInput" onchange="handleFileSelect(event)">
                                <input type="hidden" name="Image">
                            </div>
                            <div class="form-group">
                                <p>Amount</p>
                                <input type="number" class="form-control" name="amount" required>
                            </div>
                            <div class="form-group">
                                <p>Interest Rate (%/year)</p>
                                <input type="number" class="form-control" name="interest" required>
                            </div>
                            <div class="form-group">
                                <p>Due(month)</p>
                                <input type="number" class="form-control" name="due" required>
                            </div>
                            <div class="form-group">
                                <p>Date issuance </p>
                                <input type="date" class="form-control" name="dateIssuance">
                            </div>
                            <div class="form-group">
                                <p>Total Amount</p>
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

    </body>
</html>
</html>
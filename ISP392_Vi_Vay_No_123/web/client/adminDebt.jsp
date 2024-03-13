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
                        <div><h2>DebtList</h2></div>
                        <form action="dashboardadmin" method="get" class="search">
                            <input type="text" class="search-input" placeholder="Search Debt" name="searchQuery">
                            <input type="hidden" class="search-input" name="idAccountDebtor" value="${idAccount}">
                            <input type="hidden" class="search-input" name="debtorid" value="${idDebtor}">
                            <div class="search-selection">
                                <select class="search-selection-label" name="searchType" >
                                    <option class="search-selection-item">Search by</option>
                                    <option class="search-selection-item" value="description">Description</option>
                                    <option class="search-selection-item" value="amount">Amount</option>
                                </select>
                            </div>
                            <button class="search-btn" type="submit" name="action" value="searchDebt">
                                <i class="search-btn-icon fa-solid fa-magnifying-glass"></i>
                            </button>
                        </form>

                    </div>

                    <div class="home-filter">
                        <div class="selection-input">
                            <span class="select-input__label">Sorted by</span>
                            <i class="sort-icon fa-solid fa-angle-down"></i>	
                            <ul class="select-input__list">
                                <li class="select-input_item">
                                    <a href="dashboardadmin?action=sortByOldestDebt&idAccountDebtor=${idAccount}&debtorid=${idDebtor}" class="select-input__link">Oldest</a>
                                </li>
                                <li class="select-input_item">
                                    <a href="dashboardadmin?action=sortByNewestDebt&idAccountDebtor=${idAccount}&debtorid=${idDebtor}" class="select-input__link">Newest</a>
                                </li>
                                <li class="select-input_item">
                                    <a href="dashboardadmin?action=sortByHighLowDebt&idAccountDebtor=${idAccount}&debtorid=${idDebtor}" class="select-input__link">Amount from high to low</a>
                                </li>
                                <li class="select-input_item">
                                    <a href="dashboardadmin?action=sortByLowHighDebt&idAccountDebtor=${idAccount}&debtorid=${idDebtor}" class="select-input__link">Amount from low to high</a>
                                </li>
                                <li class="select-input_item">
                                    <a href="dashboardadmin?action=ReceivableOfDebt&idAccountDebtor=${idAccount}&debtorid=${idDebtor}" class="select-input__link">Receivable</a>
                                </li>
                                <li class="select-input_item">
                                    <a href="dashboardadmin?action=DebtOfDebt&idAccountDebtor=${idAccount}&debtorid=${idDebtor}" class="select-input__link">Debt</a>
                                </li>
                                <li class="select-input_item">
                                    <a href="dashboardadmin?action=LoanOfDebt&idAccountDebtor=${idAccount}&debtorid=${idDebtor}" class="select-input__link">Loan</a>
                                </li>
                                <li class="select-input_item">
                                    <a href="dashboardadmin?action=LendOfDebt&idAccountDebtor=${idAccount}&debtorid=${idDebtor}" class="select-input__link">Lend</a>
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
                        <c:forEach items="${listDebt}" var="debt">
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
                                            data-debttype="${debt.debtTypeId}" 
                                            data-createat="<fmt:formatDate value="${debt.createAt}" pattern="yyyy-MM-dd HH:mm:ss" />">
                                        View Details
                                    </button>
                                    <button type="button" class="btn btn-warning" data-toggle="modal" data-target="#reportModal">Report <i class="fa fa-flag"></i></button>
                                </td>

                            </tr>					
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
                    <script>
                        $(document).ready(function () {
                            $('.btn-report').click(function () {
                                var debtId = $(this).attr('data-id');
                                $('#debtId').val(debtId);
                                $('#reportModal').modal('show');
                            });
                        });
                    </script>
                    <!-- Report Modal -->
                    <div id="reportModal" class="modal fade">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <form action="report" method="post" enctype="multipart/form-data">
                                    <div class="modal-header">                        
                                        <h4 class="modal-title">Report Issue</h4>
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                    </div>
                                    <div class="modal-body">    
                                        <input type="hidden" name="debtId" id="debtId" value="">
                                        <div class="form-group">
                                            <label>Description</label>
                                            <textarea class="form-control" name="description" required></textarea>
                                        </div>
                                        <div class="form-group">
                                            <label for="imageUpload">Choose an image:</label>
                                            <input type="file" id="imageUpload" name="img" class="form-control-file" accept="image/*">
                                        </div>
                                        <div class="form-group text-center">
                                            <img id="previewImage" src="#" alt="Image preview" class="img-fluid" style="display: none; max-width: 100%; max-height: 200px; margin-top: 10px;"/>
                                        </div>             
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                        <button type="submit" class="btn btn-danger">Submit Report</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>


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
                        var createAt = $(this).data('createat');

                        // C?p nh?t modal
                        $('#viewDebtDetailModal').find('.modal-body .description').text(description);
                        $('#viewDebtDetailModal').find('.modal-body .amount').text(amount);
                        $('#viewDebtDetailModal').find('.modal-body .debt-type').text(debtType);
                        $('#viewDebtDetailModal').find('.modal-body .create-at').text(createAt);

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
                                <label>Description:</label>
                                <p class="form-control-static description"></p>
                            </div>
                            <div class="form-group">
                                <label>Amount:</label>
                                <p class="form-control-static amount"></p>
                            </div>
                            <div class="form-group">
                                <label>Debt Type:</label>
                                <p class="form-control-static amount"></p>
                            </div>
                            <div class="form-group">
                                <label>Create At:</label>
                                <p class="form-control-static create-at"></p>
                            </div>                  
                        </div>

                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>

                        </div>

                    </div>
                </div>
            </div>
    </body>
</html>
</html>
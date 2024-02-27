<!DOCTYPE html>
<html>
<head>
    <title>Add New Debtor</title>
    <script src="ISP392_Vi_Vay_No_123/assets/js/validate.js"></script>
</head>
<body>
    <div id="addDebtorModal" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <form name="debtorForm" action="debtor?action=add" method="POST" onsubmit="return validateForm2()">
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
                            <input type="email" class="form-control" name="email" id="email" required>
                            <div id="emailFeedback" class="validation-feedback"></div>
                        </div>
                        <div class="form-group">
                            <label>Address</label>
                            <textarea class="form-control" name="address" required></textarea>
                        </div>
                        <div class="form-group">
                            <label>Phone</label>
                            <input type="text" class="form-control" name="phone" id="phone" required>
                             <div id="phoneFeedback" class="validation-feedback"></div>
                        </div>	
                        <!-- New textbox for "Total" with a default value of 0 -->
                        <div class="form-group">
                            <label>Total</label>
                            <input type="text" class="form-control" required name="totalDebt" value="0" readonly>
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
</body>
</html>

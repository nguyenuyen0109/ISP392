<!-- Add your CSS and JavaScript files here -->
<link rel="stylesheet" href="bootstrap.min.css">
<script src="jquery.min.js"></script>
<script src="bootstrap.min.js"></script>
<script src="ISP392_Vi_Vay_No_123/assets/js/validate.js"></script>
<!-- Modal -->
<div class="modal fade" id="editDebtorDetailModal" tabindex="-1"
    role="dialog" aria-labelledby="editDebtorDetailModalLabel"
    aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="editDebtorDetailModalLabel">Edit Debtor Detail</h5>
                <button type="button" class="close" data-dismiss="modal"
                    aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <!-- Your form for editing debtor details -->
                <form id="editDebtorDetailForm" action="debtor?action=update" method="post">
                    <input type="hidden" id="id" name="id">
                    <div class="form-group">
                        <label for="name">Name:</label>
                        <input type="text" class="form-control" id="name"
                            name="name" placeholder="Enter name" required>
                        <div id="nameError" class="error" style="color: #FF0000"></div>
                    </div>
                    <div class="form-group">
                        <label for="address">Address:</label>
                        <input type="text" class="form-control" id="address"
                            name="address" placeholder="Enter address" required>
                        <div id="addressError" class="error" style="color: #FF0000"></div>
                    </div>
                    <div class="form-group">
                        <label for="phone">Phone:</label>
                        <input type="text" class="form-control" id="phone"
                            name="phone" placeholder="Enter phone" required>
                         <div id="phoneFeedback" class="validation-feedback"></div>
                        <div id="phoneError" class="error" style="color: #FF0000"></div>
                    </div>
                    <div class="form-group">
                        <label for="email">Email:</label>
                        <input type="email" class="form-control" id="email"
                            name="email" placeholder="Enter email" required>
                         <div id="emailFeedback" class="validation-feedback"></div>
                        <div id="emailError" class="error" style="color: #FF0000"></div>
                    </div>
                    <div class="form-group">
                        <label for="totalDebt">Total Debt:</label>
                        <input type="text" class="form-control" id="totalDebt"
                            name="totalDebt" readonly>
                    </div>

                    <button type="submit" class="btn btn-primary">Save changes</button>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary"
                    data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

<!-- JavaScript code to set the values of fields in the modal and validate -->


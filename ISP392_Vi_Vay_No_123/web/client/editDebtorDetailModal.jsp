<!-- Add your CSS and JavaScript files here -->
<link rel="stylesheet" href="bootstrap.min.css">
<script src="jquery.min.js"></script>
<script src="bootstrap.min.js"></script>

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
                <form id="editDebtorDetailForm" action="debtorlist?action=update" method="post">
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
                        <div id="phoneError" class="error" style="color: #FF0000"></div>
                    </div>
                    <div class="form-group">
                        <label for="email">Email:</label>
                        <input type="email" class="form-control" id="email"
                            name="email" placeholder="Enter email" required>
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
<script>
    function editDebtorDetailModal(id, name, address, phone, email, totalDebt) {
        document.getElementById('id').value = id;
        document.getElementById('name').value = name;
        document.getElementById('address').value = address;
        document.getElementById('phone').value = phone;
        document.getElementById('email').value = email;
        document.getElementById('totalDebt').value = totalDebt;
    }

    // Function to validate form
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
        if (!/^[a-zA-Z\s]+$/.test(address)) {
            $('#addressError').html('Address must contain only letters');
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

    // Attach validation function to form submission
    $('#editDebtorDetailForm').submit(function () {
        return validateForm();
    });
</script>

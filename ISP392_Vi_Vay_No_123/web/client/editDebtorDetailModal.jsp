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
                <form id="editDebtorDetailForm" action="debtor?action=update" method="post"id ="validForm">
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
                               name="address" placeholder="Enter address" >
                        <div id="addressError" class="error" style="color: #FF0000"></div>
                    </div>
                    <div class="form-group">
                        <label for="phone">Phone:</label>
                        <input type="text" class="form-control" id="phone"
                               name="phone" placeholder="Enter phone" >
                        <div id="phoneError" class="error" style="color: #FF0000"></div>
                    </div>
                    <div class="form-group">
                        <label for="email">Email:</label>
                        <input type="text" class="form-control" id="email"
                               name="email" placeholder="Enter email" >
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

<script>
    function editDebtorDetailModal(id, name, address, phone, email, totalDebt) {
        document.getElementById('id').value = id;
        document.getElementById('name').value = name;
        document.getElementById('address').value = address;
        document.getElementById('phone').value = phone;
        document.getElementById('email').value = email;
        document.getElementById('totalDebt').value = totalDebt;
    }
function validateEmail(email) {
    if (email.trim() === "")
        return true; 
    var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(email)) {
        alert("Please enter a valid email address.");
        return false;
    }
    return true;
}

function validatePhone(phone) {
    if (phone.trim() === "")
        return true; 
    var phoneRegex = /^\d{10,}$/;
    if (!phoneRegex.test(phone)) {
        alert("Phone must be a number with at least 10 digits.");
        return false;
    }
    return true;
}

document.getElementById("validForm").addEventListener("submit", function (event) {
    var email = document.forms["debtorForm"]["email"].value.trim();
    var phone = document.forms["debtorForm"]["phone"].value.trim();
    
    if (!validateEmail(email) || !validatePhone(phone)) {
        event.preventDefault();
        alert("Please correct the information");
    }
});
</script>




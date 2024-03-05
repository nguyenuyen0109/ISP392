<!DOCTYPE html>
<html>
<head>
    <title>Add New Debtor</title>
</head>
<body>
<div id="addDebtorModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <form name="debtorForm" action="debtor?action=add" method="POST" id="debtorForm">
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
                        <input type="text" class="form-control" name="email">
                    </div>
                    <div class="form-group">
                        <label>Address</label>
                        <textarea class="form-control" name="address"></textarea>
                    </div>
                    <div class="form-group">
                        <label>Phone</label>
                        <input type="text" class="form-control" name="phone">
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
<!--<script>
    function validateEmail(email) {
        if (email === "")
            return true; // Email có th? tr?ng
        var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        return emailRegex.test(email);
    }

    function validatePhone(phone) {
        if (phone === "")
            return true; // Phone có th? tr?ng
        var phoneRegex = /^\d{10,}$/;
        return phoneRegex.test(phone);
    }

    function validateAddress(address) {
        if (address === "")
            return true; // Address có th? tr?ng
        var addressRegex = /^[a-zA-Z0-9\s]+$/;
        return addressRegex.test(address);
    }

    function validateForm() {
        var name = document.forms["debtorForm"]["name"].value.trim();
        var email = document.forms["debtorForm"]["email"].value.trim();
        var address = document.forms["debtorForm"]["address"].value.trim();
        var phone = document.forms["debtorForm"]["phone"].value.trim();

        if (name === "") {
            alert("Please fill in the name field");
            return false;
        }

        // Thêm ?i?u ki?n ki?m tra tr??ng r?ng cho các tr??ng email, phone và address
        if (email !== "" && !validateEmail(email)) {
            alert("Please enter a valid email address.");
            return false;
        }

        if (phone !== "" && !validatePhone(phone)) {
            alert("Phone must be a number with at least 10 digits.");
            return false;
        }

        if (address !== "" && !validateAddress(address)) {
            alert("Address must contain letters and numbers only.");
            return false;
        }

        return true;
    }

    document.getElementById("debtorForm").addEventListener("submit", function (event) {
        if (!validateForm()) {
            event.preventDefault();
            alert("Please correct the information");
        }
    });
</script>-->

<script>
    function validateEmail() {
        var email = document.forms["debtorForm"]["email"].value.trim();
        if (email === "")
            return true; 
        var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if(!emailRegex.test(email)){
            alert("Please enter a valid email address.");
            return false;
        }
        return true;
    }
    
    function validatePhone() {
        var phone = document.forms["debtorForm"]["phone"].value.trim();
        if (phone === "")
            return true; 
        var phoneRegex = /^\d{10,}$/;
        if(!phoneRegex.test(phone)){
            alert("Phone must be a number with at least 10 digits.");
           return false;
        }
        return true;
    }

    document.getElementById("debtorForm").addEventListener("submit", function (event) {
        if (!validateEmail()|| !validatePhone()) {
            event.preventDefault();
            alert("Please correct the information");
        }
    });
    
    
    </script>
</body>
</html>

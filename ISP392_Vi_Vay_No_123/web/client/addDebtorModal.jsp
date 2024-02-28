<!DOCTYPE html>
<html>
<head>
    <title>Add New Debtor</title>
    <script>
        function validateForm2() {
            var name = document.forms["debtorForm"]["name"].value.trim();
            var email = document.forms["debtorForm"]["email"].value.trim();
            var address = document.forms["debtorForm"]["address"].value.trim();
            var phone = document.forms["debtorForm"]["phone"].value.trim();
            

            if (name === "" || email === "" || address === "" || phone === "") {
                alert("Please fill in all fields.");
                return false;
            }

            
            var nameRegex = /^[a-zA-Z0-9\s]+$/;
            if (!nameRegex.test(address)) {
                alert("Address must contain letters and number.");
                return false;
            }

            var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
            if (email !== '' && !emailRegex.test(email)) {
                alert("Please enter a valid email address.");
                return false;
            }

 
            var phoneRegex = /^\d{10,}$/;
            if (!phoneRegex.test(phone)) {
                alert("Phone must be a number with at least 10 digits.");
                return false;
            }

            return true;
        }
    </script>
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
                            <input type="text" class="form-control" name="email" required>
                        </div>
                        <div class="form-group">
                            <label>Address</label>
                            <textarea class="form-control" name="address" required></textarea>
                        </div>
                        <div class="form-group">
                            <label>Phone</label>
                            <input type="text" class="form-control" name="phone" required>
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

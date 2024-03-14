<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Edit Profile</title>
        <link rel="stylesheet" href="../css/editprofile.css">
        <link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/css/bootstrap.min.css'>
    </head>
    <body>
        <jsp:include page="/navigator/toast.jsp" />
        <jsp:include page="/navigator/header.jsp" />
        <form action="editprofile" method="post" id = "editProfile">
            <div class="container bootstrap snippets bootdey">
                <h1 class="text-primary">Edit Profile</h1>
                <div class="row">
                    <!--left column for avatar--> 
                    <div class="col-md-3">
                        <div class="text-center">
                            <img id="avatarImage" src="${!empty account.avatarUrl ? account.avatarUrl : '//bootdey.com/img/Content/avatar/avatar7.png'}" class="avatar img-circle img-thumbnail" alt="avatar">
                            <input type="file" class="form-control" onchange="updateAvatar(this)">
                            <input type="hidden" class="form-control mt-3" id="avatarInput" name="avatar" value="${account.avatarUrl}"> 
                        </div>

                        <script>
                            function updateAvatar(input) {
                                if (input.files && input.files[0]) {
                                    var reader = new FileReader();

                                    reader.onload = function (e) {
                                        document.getElementById('avatarImage').setAttribute('src', e.target.result);
                                        document.getElementById('avatarInput').value = e.target.result;
                                    }

                                    reader.readAsDataURL(input.files[0]);
                                }
                            }
                        </script>

                    </div>

                    <!--edit form column--> 
                    <div class="col-md-9 personal-info">
                        <h3>Personal info</h3>

                        <input type="hidden" name="id" value="${sessionScope.account.id}">

                        <div class="form-group">
                            <label class="col-lg-3 control-label">Full name:</label>
                            <div class="col-lg-8">
                                <input class="form-control" type="text" name="name" id="name" value="${sessionScope.account.name}" required>
                            </div>
                        </div>
                        <!--Username form group--> 
                        <div class="form-group">
                            <label class="col-lg-3 control-label">Username:</label>
                            <div class="col-lg-8">
                                <input class="form-control" type="text" name="username" value="${sessionScope.account.username}" disabled>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-lg-3 control-label">Phone:</label>
                            <div class="col-lg-8">
                                <input class="form-control" type="text" name="phone" id="phone" value="${sessionScope.account.mobileNumber}" >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-lg-3 control-label">Address:</label>
                            <div class="col-lg-8">
                                <input class="form-control" type="text" name="address" id ="address" value="${sessionScope.account.address}" >
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-lg-3 control-label">Email:</label>
                            <div class="col-lg-8">
                                <input class="form-control" type="text" name="email" id = "email" value="${sessionScope.account.emailAddress}" disabled>
                            </div>
                        </div>

                        <!--Submit and Cancel buttons--> 
                        <div class="form-group">
                            <label class="col-md-3 control-label"></label>
                            <div class="col-md-8">
                                <input type="submit" class="btn btn-primary" value="Save Changes">
                                <span></span>
                                <input type="reset" class="btn btn-default" value="Cancel">
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </form>
        <script>
        function editDebtorDetailModal(address, phone) {
            document.getElementById('phone').value = phone;
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

        document.getElementById("editProfile").addEventListener("submit", function (event) {
            var phone = document.forms["editProfile"]["phone"].value.trim();

            if (!validatePhone(phone)) {
                event.preventDefault();
                alert("Please correct the information");
            }
        });
    </script>                      

    </body>
</html>

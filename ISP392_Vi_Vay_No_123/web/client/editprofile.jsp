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
        <div class="container bootstrap snippets bootdey">
            <h1 class="text-primary">Edit Profile</h1>
            <hr>
            <form action="editprofile" method="post" enctype="multipart/form-data">
                <div class="row">
                    <!--                     left column for avatar -->
                    <div class="col-md-3">
                        <div class="text-center">
                            <img id="avatarImage" src="${account.avatarUrl == null ? '//bootdey.com/img/Content/avatar/avatar7.png' : account.avatarUrl}" class="avatar img-circle img-thumbnail" alt="avatar">
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

                    <!--                     edit form column -->
                    <div class="col-md-9 personal-info">
                        <h3>Personal info</h3>

                        <input type="hidden" name="id" value="${account.id}">

                        <div class="form-group">
                            <label class="col-lg-3 control-label">Full name:</label>
                            <div class="col-lg-8">
                                <input class="form-control" type="text" name="name" value="${account.name}" required>
                            </div>
                        </div>
                        <!--                         Username form group -->
                        <div class="form-group">
                            <label class="col-lg-3 control-label">Username:</label>
                            <div class="col-lg-8">
                                <input class="form-control" type="text" name="username" value="${account.username}" disabled>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-lg-3 control-label">Phone:</label>
                            <div class="col-lg-8">
                                <input class="form-control" type="text" name="phone" value="${requestScope.account.getMobileNumber()}" required>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-lg-3 control-label">Address:</label>
                            <div class="col-lg-8">
                                <input class="form-control" type="text" name="address" value="${requestScope.account.getAddress()}" required>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-lg-3 control-label">Email:</label>
                            <div class="col-lg-8">
                                <input class="form-control" type="email" name="email" value="${account.emailAddress}" disabled>
                            </div>
                        </div>

                        <!--                         Submit and Cancel buttons -->
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

            </form>
        </div>
        <hr>
    </body>
</html>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">
        <link rel="shortcut icon" type="image/x-icon" href="assets/img/favicon.ico">
        <title>Add Blog - Preclinic</title>
        <link rel="stylesheet" type="text/css" href="assets/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="assets/css/font-awesome.min.css">
        <link rel="stylesheet" type="text/css" href="assets/css/select2.min.css">
        <link rel="stylesheet" type="text/css" href="assets/css/tagsinput.css">
        <link rel="stylesheet" type="text/css" href="assets/css/style.css">


        <script src="https://cdn.ckeditor.com/ckeditor5/40.0.0/classic/ckeditor.js"></script>



    </head>
    <body>
        <div class="main-wrapper">
            
            <jsp:include page="headerStaff.jsp"></jsp:include>
            <jsp:include page="sidebar.jsp"></jsp:include>




            <div class="page-wrapper-profile">
                <div class="content">
                    <div class="row">
                        <div class="col-lg-8 offset-lg-2">
                            <h4 class="page-title">Add Blog</h4>
                            <form method="post" action="addblog" enctype="multipart/form-data">
                                <div class="form-group">
                                    <label>Blog Name</label>
                                    <input class="form-control" type="text" name="name" required>
                                </div>

                                <div class="form-group">
                                    <label>Description</label>
                                    <textarea cols="30" rows="2" class="form-control" name="description" ></textarea>
                                </div>

                                <div class="form-group">
                                    <label>Category</label>
                                    <select class="form-control" name="categoryId" required>
                                        <option value="">Select Category</option>
                                        <option value="1">General Health</option>
                                        <option value="2">Cardiology</option>
                                        <option value="3">Pediatrics</option>
                                        <option value="4">Nutrition</option>
                                        <option value="5">Mental Health</option>
                                    </select>
                                </div>

                                <div class="form-group">
                                    <label>Blog Images</label>
                                    <input class="form-control" type="file" name="image" id="image" accept="image/png, image/jpeg, image/gif, image/jpg">
                                    <c:if test="${not empty blog.image}">
                                        <img src="${blog.image}" width="100">
                                    </c:if>
                                </div>

                                <div class="form-group">
                                    <label>Blog Content</label>
                                    <textarea cols="30" rows="6" class="form-control" id="descriptiondetail" name="descriptiondetail"></textarea>
                                </div>

                                <div class="form-group">
                                    <label class="display-block">Blog Status</label>
                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input" type="radio" name="status" id="blog_active" value="active" checked>
                                        <label class="form-check-label" for="blog_active">Active</label>
                                    </div>
                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input" type="radio" name="status" id="blog_inactive" value="inactive">
                                        <label class="form-check-label" for="blog_inactive">Inactive</label>
                                    </div>
                                </div>

                                <div class="m-t-20 text-center">
                                    <button class="btn btn-primary submit-btn">Publish Blog</button>
                                </div>
                            </form>


                            <script>
                                document.addEventListener("DOMContentLoaded", function () {
                                    ClassicEditor
                                            .create(document.querySelector('#descriptiondetail'), {
                                                ckfinder: {
                                                    uploadUrl: '/SWP/uploadckedittor' // Tr? ??n servlet x? l� upload
                                                }
                                            })
                                            .catch(error => console.error(error));
                                });
                            </script>


                            <script>
                                document.addEventListener("DOMContentLoaded", function () {
                                    document.getElementById("image").addEventListener("change", function () {
                                        const file = this.files[0];
                                        if (file) {
                                            const allowedTypes = ["image/png", "image/jpeg", "image/gif", "image/jpg"];
                                            if (!allowedTypes.includes(file.type) || file.size > 50 * 1024 * 1024) {
                                                alert("Only PNG, JPEG, JPG, GIF files are allowed and must be under 50MB.");
                                                this.value = ''; // X�a file n?u kh�ng h?p l?
                                            }
                                        }
                                    });
                                });

                            </script>
                        </div>
                    </div>
                </div>
            </div>



        </div>
    </div>
</div>
</div>

<div class="sidebar-overlay" data-reff=""></div>
<script src="assets/js/jquery-3.2.1.min.js"></script>
<script src="assets/js/popper.min.js"></script>
<script src="assets/js/bootstrap.min.js"></script>
<script src="assets/js/jquery.slimscroll.js"></script>
<script src="assets/js/select2.min.js"></script>
<script src="assets/js/tagsinput.js"></script>
<script src="assets/js/app.js"></script>
</body>
</html>
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
            <div class="header">
                <div class="header-left">
                    <a href="dashboard.html" class="logo">
                        <img src="assets/img/logo.png" width="35" height="35" alt=""> <span>Preclinic</span>
                    </a>
                </div>
                <ul class="nav user-menu float-right">
                    <li class="nav-item dropdown d-none d-sm-block">
                        <a href="#" class="dropdown-toggle nav-link" data-toggle="dropdown"><i class="fa fa-bell-o"></i> <span class="badge badge-pill bg-danger float-right">3</span></a>
                    </li>
                    <li class="submenu">
                        <a href="#"><i class="fa fa-commenting-o"></i> <span> Blog</span> <span class="menu-arrow"></span></a>
                        <ul style="display: none;">
                            <li><a href="homeblogseverlet">Blog</a></li>
                            <li><a href="blog-details.jsp">Blog View</a></li>
                            <li><a class="active" href="add-blog.jsp">Add Blog</a></li>
                            <li><a href="edit-blog.html">Edit Blog</a></li>
                        </ul>
                    </li>
                </ul>
            </div>




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
                                    <textarea cols="30" rows="2" class="form-control" name="description" required></textarea>
                                </div>
                                <div class="form-group">
                                    <label>Blog Images</label>
                                    <!-- Adding the 'accept' attribute to limit selection to PNG files -->
                                    <input class="form-control" type="file" name="image" id="image" accept="image/png, image/jpeg, image/gif, image/jpg">


                                    <c:if test="${not empty blog.image}">
                                        <img src="${blog.image}" width="100">
                                    </c:if>
                                </div>


                                <div class="form-group">
                                    <label>Blog content</label>
                                    <textarea cols="30" rows="6" class="form-control" id="descriptiondetail" name="descriptiondetail" ></textarea>
                                </div>
                                <div class="form-group">
                                    <label class="display-block">Blog Status</label>
                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input" type="radio" name="status" id="blog_active" value="true" checked>
                                        <label class="form-check-label" for="blog_active">Active</label>
                                    </div>
                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input" type="radio" name="status" id="blog_inactive" value="false">
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
                                                    uploadUrl: '/SWP/uploadckedittor' // Tr? ??n servlet x? lý upload
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
                                                this.value = ''; // Xóa file n?u không h?p l?
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
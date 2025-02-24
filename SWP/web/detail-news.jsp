<%-- 
    Document   : blog-single
    Created on : Feb 9, 2025, 11:15:20 PM
    Author     : jaxbo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%><!DOCTYPE html>
<!DOCTYPE html>
<html lang="zxx">

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="description" content="Orbitor,business,company,agency,modern,bootstrap4,tech,software">
        <meta name="author" content="themefisher.com">

        <title>Novena- Health & Care Medical template</title>

        <!-- Favicon -->
        <link rel="shortcut icon" type="image/x-icon" href="/images/favicon.ico" />

        <!-- bootstrap.min css -->
        <link rel="stylesheet" href="assets2/plugins/bootstrap/css/bootstrap.min.css">
        <!-- Icon Font Css -->
        <link rel="stylesheet" href="assets2/plugins/icofont/icofont.min.css">
        <!-- Slick Slider  CSS -->
        <link rel="stylesheet" href="assets2/plugins/slick-carousel/slick/slick.css">
        <link rel="stylesheet" href="assets2/plugins/slick-carousel/slick/slick-theme.css">

        <!-- Main Stylesheet -->
        <link rel="stylesheet" href="assets2/css/style.css">

    </head>

    <body id="top">
        <%--<jsp:include page="headerCustomer.jsp"></jsp:include>--%>
                        <jsp:include page="headerHome.jsp"></jsp:include>


        <section class="page-title bg-1">
            <div class="overlay"></div>
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <div class="block text-center">
                            <span class="text-white">News details</span>
                            <h1 class="text-capitalize mb-5 text-lg">Blog Single</h1>
                        </div>
                    </div>
                </div>
            </div>
        </section>



        <section class="section blog-wrap">
            <div class="container">
                <div class="row">
                    <div class="col-lg-8">
                        <div class="row">
                            <div class="col-lg-12 mb-5">
                                <div class="single-blog-item">
                                    <img src="assets2/images/blog/blog-1.jpg" alt="" class="img-fluid">
                                    <div class="blog-item-content mt-5">
                                        <div class="blog-item-meta mb-3">
                                            <span class="text-color-2 text-capitalize mr-3"><i
                                                    class="icofont-book-mark mr-2"></i> Equipment</span>
                                            <span class="text-muted text-capitalize mr-3"><i
                                                    class="icofont-comment mr-2"></i>${comments.size()} Comments</span>
                                            <span class="text-black text-capitalize mr-3"><i
                                                    class="icofont-calendar mr-2"></i>${newsDetail.created_at}</span>
                                        </div>

                                        <h2 style="color:#009efb " class="mb-4 text-md">${newsDetail.title}</h2>


                                        <p class="lead mb-4">${newsDetail.detail}</p>

                                        <p>${newsDetail.content}</p>

                                        <blockquote class="quote">
                                            A brand for a company is like a reputation for a person. You earn reputation by
                                            trying to do hard things well.
                                        </blockquote>


                                        <p class="lead mb-4 font-weight-normal text-black">The same is true as we experience
                                            the emotional sensation of stress from our first instances of social rejection
                                            ridicule. We quickly learn to fear and thus automatically.</p>

                                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Iste, rerum beatae
                                            repellat tenetur incidunt quisquam libero dolores laudantium. Nesciunt quis
                                            itaque quidem, voluptatem autem eos animi laborum iusto expedita sapiente.</p>

                                        <div class="mt-5 clearfix">
                                            <ul class="float-left list-inline tag-option">
                                                <li class="list-inline-item"><a href="#">Advancher</a></li>
                                                <li class="list-inline-item"><a href="#">Landscape</a></li>
                                                <li class="list-inline-item"><a href="#">Travel</a></li>
                                            </ul>

                                            <ul class="float-right list-inline">
                                                <li class="list-inline-item"> Share: </li>
                                                <li class="list-inline-item"><a href="#" target="_blank"><i
                                                            class="icofont-facebook" aria-hidden="true"></i></a></li>
                                                <li class="list-inline-item"><a href="#" target="_blank"><i
                                                            class="icofont-twitter" aria-hidden="true"></i></a></li>
                                                <li class="list-inline-item"><a href="#" target="_blank"><i
                                                            class="icofont-pinterest" aria-hidden="true"></i></a></li>
                                                <li class="list-inline-item"><a href="#" target="_blank"><i
                                                            class="icofont-linkedin" aria-hidden="true"></i></a></li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="col-lg-12">
                                <div class="comment-area mt-4 mb-5">
                                    <h4 class="mb-4">${comments.size()} Comments on ${newsDetail.title}</h4>
                                    <ul class="comment-tree list-unstyled">
                                        <c:forEach var="comment" items="${comments}">
                                            <c:if test="${comment.parent_comment_id == 0}">
                                                <li class="mb-5">
                                                    <div class="comment-area-box">
                                                        <div class="comment-thumb float-left">
                                                            <img alt="" src="assets2/images/blog/testimonial1.jpg"
                                                                 class="img-fluid">
                                                        </div>
                                                        <div class="comment-info">
                                                            <h5 class="mb-1">${comment.customerID.fullName}</h5>
                                                            <span>${comment.create_at}</span>
                                                        </div>
                                                        <div class="comment-meta mt-2">
                                                            <a href="#"><i
                                                                    class="icofont-reply mr-2 text-muted"></i>Reply</a>
                                                        </div>
                                                        <div class="comment-content mt-3">
                                                            <p>${comment.content}</p>
                                                        </div>
                                                    </div>
                                                    <ul>
                                                        <c:forEach var="rep" items="${comments}">
                                                            <c:if test="${rep.parent_comment_id == comment.comment_id}">
                                                                <li class="mb-5">
                                                                    <div class="comment-area-box">
                                                                        <div class="comment-thumb float-left">
                                                                            <img alt=""
                                                                                 src="assets2/images/blog/testimonial2.jpg"
                                                                                 class="img-fluid">
                                                                        </div>

                                                                        <div class="comment-info">
                                                                            <h5 class="mb-1">${rep.customerID.fullName}</h5>
                                                                            <span>${rep.create_at}</span>
                                                                        </div>
                                                                        <div class="comment-meta mt-2">
                                                                            <a href="#"><i
                                                                                    class="icofont-reply mr-2 text-muted"></i>Reply</a>
                                                                        </div>
                                                                        <div class="comment-content mt-3">
                                                                            <p>${rep.content}</p>
                                                                        </div>
                                                                    </div>
                                                                </li>
                                                            </c:if>
                                                        </c:forEach>
                                                    </ul>
                                                </li>
                                            </c:if>
                                        </c:forEach>
                                    </ul>
                                </div>
                            </div>


                            <div class="col-lg-12">
                                <form action="" method="" class="comment-form my-5" id="comment-form">
                                    <h4 class="mb-4">Write a comment</h4>
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <input class="form-control" type="text" name="name" id="name" placeholder="Name:">
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <input class="form-control" type="text" name="mail" id="mail" placeholder="Email:">
                                            </div>
                                        </div>
                                    </div>
                                    <textarea class="form-control mb-4" name="comment" id="comment" cols="30" rows="5" placeholder="Comment"></textarea>
                                    <input class="btn btn-main-2 btn-round-full" type="submit" name="submit-contact" id="submit_contact" value="Submit Message">
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4">
                        <div class="sidebar-wrap pl-lg-4 mt-5 mt-lg-0">
                            <div class="sidebar-widget search  mb-3 ">
                                <h5>Search Here</h5>
                                <form action="allNews" method="get" class="search-form">
                                    <input type="text" name="search" class="form-control" placeholder="search" value="${search}">
                                    <i class="ti-search"></i>
                                </form>
                            </div>


                            <div class="sidebar-widget latest-post mb-3">
                                <h5>Popular Posts</h5>

                                <div class="py-2">
                                    <span class="text-sm text-muted">03 Mar 2018</span>
                                    <h6 class="my-2"><a href="#">Thoughtful living in los Angeles</a></h6>
                                </div>

                                <div class="py-2">
                                    <span class="text-sm text-muted">03 Mar 2018</span>
                                    <h6 class="my-2"><a href="#">Vivamus molestie gravida turpis.</a></h6>
                                </div>

                                <div class="py-2">
                                    <span class="text-sm text-muted">03 Mar 2018</span>
                                    <h6 class="my-2"><a href="#">Fusce lobortis lorem at ipsum semper sagittis</a></h6>
                                </div>
                            </div>

                            <div class="sidebar-widget category mb-3">
                                <h5 class="mb-4">Categories</h5>

                                <ul class="list-unstyled">
                                    <li class="align-items-center">
                                        <a href="allNews">All</a>
                                        <!--<span>(14)</span>-->
                                    </li>
                                    <c:forEach var="cate" items="${listCate}">
                                        <li class="align-items-center">
                                            <a href="allNews?categoryID=${cate.category_id}">${cate.name}</a>
                                            <!--<span>(14)</span>-->
                                        </li>
                                    </c:forEach>
                                </ul>
                            </div>


                            <div class="sidebar-widget tags mb-3">
                                <h5 class="mb-4">Tags</h5>

                                <a href="#">Doctors</a>
                                <a href="#">agency</a>
                                <a href="#">company</a>
                                <a href="#">medicine</a>
                                <a href="#">surgery</a>
                                <a href="#">Marketing</a>
                                <a href="#">Social Media</a>
                                <a href="#">Branding</a>
                                <a href="#">Laboratory</a>
                            </div>


                            <div class="sidebar-widget schedule-widget mb-3">
                                <h5 class="mb-4">Time Schedule</h5>

                                <ul class="list-unstyled">
                                    <li class="d-flex justify-content-between align-items-center">
                                        <a href="#">Monday - Friday</a>
                                        <span>9:00 - 17:00</span>
                                    </li>
                                    <li class="d-flex justify-content-between align-items-center">
                                        <a href="#">Saturday</a>
                                        <span>9:00 - 16:00</span>
                                    </li>
                                    <li class="d-flex justify-content-between align-items-center">
                                        <a href="#">Sunday</a>
                                        <span>Closed</span>
                                    </li>
                                </ul>

                                <div class="sidebar-contatct-info mt-4">
                                    <p class="mb-0">Need Urgent Help?</p>
                                    <h3>+23-4565-65768</h3>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </section>


        <!-- footer Start -->
        <footer class="footer section gray-bg">
            <div class="container">
                <div class="row">
                    <div class="col-lg-4 mr-auto col-sm-6">
                        <div class="widget mb-5 mb-lg-0">
                            <div class="logo mb-4">
                                <img src="assets2/images/logo.png" alt="" class="img-fluid">
                            </div>
                            <p>Tempora dolorem voluptatum nam vero assumenda voluptate, facilis ad eos obcaecati tenetur
                                veritatis eveniet distinctio possimus.</p>

                            <ul class="list-inline footer-socials mt-4">
                                <li class="list-inline-item"><a href="https://www.facebook.com/themefisher"><i
                                            class="icofont-facebook"></i></a></li>
                                <li class="list-inline-item"><a href="https://twitter.com/themefisher"><i
                                            class="icofont-twitter"></i></a></li>
                                <li class="list-inline-item"><a href="https://www.pinterest.com/themefisher/"><i
                                            class="icofont-linkedin"></i></a></li>
                            </ul>
                        </div>
                    </div>

                    <div class="col-lg-2 col-md-6 col-sm-6">
                        <div class="widget mb-5 mb-lg-0">
                            <h4 class="text-capitalize mb-3">Department</h4>
                            <div class="divider mb-4"></div>

                            <ul class="list-unstyled footer-menu lh-35">
                                <li><a href="#">Surgery </a></li>
                                <li><a href="#">Wome's Health</a></li>
                                <li><a href="#">Radiology</a></li>
                                <li><a href="#">Cardioc</a></li>
                                <li><a href="#">Medicine</a></li>
                            </ul>
                        </div>
                    </div>

                    <div class="col-lg-2 col-md-6 col-sm-6">
                        <div class="widget mb-5 mb-lg-0">
                            <h4 class="text-capitalize mb-3">Support</h4>
                            <div class="divider mb-4"></div>

                            <ul class="list-unstyled footer-menu lh-35">
                                <li><a href="#">Terms & Conditions</a></li>
                                <li><a href="#">Privacy Policy</a></li>
                                <li><a href="#">Company Support </a></li>
                                <li><a href="#">FAQuestions</a></li>
                                <li><a href="#">Company Licence</a></li>
                            </ul>
                        </div>
                    </div>

                    <div class="col-lg-3 col-md-6 col-sm-6">
                        <div class="widget widget-contact mb-5 mb-lg-0">
                            <h4 class="text-capitalize mb-3">Get in Touch</h4>
                            <div class="divider mb-4"></div>

                            <div class="footer-contact-block mb-4">
                                <div class="icon d-flex align-items-center">
                                    <i class="icofont-email mr-3"></i>
                                    <span class="h6 mb-0">Support Available for 24/7</span>
                                </div>
                                <h4 class="mt-2"><a href="tel:+23-345-67890">Support@email.com</a></h4>
                            </div>

                            <div class="footer-contact-block">
                                <div class="icon d-flex align-items-center">
                                    <i class="icofont-support mr-3"></i>
                                    <span class="h6 mb-0">Mon to Fri : 08:30 - 18:00</span>
                                </div>
                                <h4 class="mt-2"><a href="tel:+23-345-67890">+23-456-6588</a></h4>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="footer-btm py-4 mt-5">
                    <div class="row align-items-center justify-content-between">
                        <div class="col-lg-6">
                            <div class="copyright">
                            </div>
                        </div>
                        <div class="col-lg-6">
                            <div class="subscribe-form text-lg-right mt-5 mt-lg-0">
                                <form action="#" class="subscribe">
                                    <input type="text" class="form-control" placeholder="Your Email address">
                                    <a href="#" class="btn btn-main-2 btn-round-full">Subscribe</a>
                                </form>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-lg-4">
                            <a class="backtop js-scroll-trigger" href="#top">
                                <i class="icofont-long-arrow-up"></i>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </footer>


        <!-- 
    Essential Scripts
    =====================================-->


        <!-- Main jQuery -->
        <script src="assets2/plugins/jquery/jquery.js"></script>
        <!-- Bootstrap 4.3.2 -->
        <script src="assets2/plugins/bootstrap/js/popper.js"></script>
        <script src="assets2/plugins/bootstrap/js/bootstrap.min.js"></script>
        <script src="assets2/plugins/counterup/jquery.easing.js"></script>
        <!-- Slick Slider -->
        <script src="assets2/plugins/slick-carousel/slick/slick.min.js"></script>
        <!-- Counterup -->
        <script src="assets2/plugins/counterup/jquery.waypoints.min.js"></script>

        <script src="assets2/plugins/shuffle/shuffle.min.js"></script>
        <script src="assets2/plugins/counterup/jquery.counterup.min.js"></script>
        <!-- Google Map -->
        <script src="assets2/plugins/google-map/map.js"></script>
        <script
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAkeLMlsiwzp6b3Gnaxd86lvakimwGA6UA&callback=initMap"></script>

        <script src="assets2/js/script.js"></script>
        <script src="assets2/js/contact.js"></script>

    </body>

</html>

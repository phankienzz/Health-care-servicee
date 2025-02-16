<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <link rel="stylesheet" href="plugins/bootstrap/css/bootstrap.min.css">
        <!-- Icon Font Css -->
        <link rel="stylesheet" href="plugins/icofont/icofont.min.css">
        <!-- Slick Slider  CSS -->
        <link rel="stylesheet" href="plugins/slick-carousel/slick/slick.css">
        <link rel="stylesheet" href="plugins/slick-carousel/slick/slick-theme.css">

        <!-- Main Stylesheet -->
        <link rel="stylesheet" href="css/style.css">

    </head>

    <body id="top">

        <jsp:include page="headerCustomer.jsp"></jsp:include>

            <section class="page-title bg-1">
                <div class="overlay"></div>
                <div class="container">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="block text-center">
                                <span class="text-white">Our News</span>
                                <h1 class="text-capitalize mb-5 text-lg">New articles</h1>
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
                            <c:if test="${empty newsList}">
                                <p>No news available.</p>
                            </c:if>
                            <c:forEach var="news" items="${newsList}">  
                                <div class="col-lg-12 col-md-12 mb-5">
                                    <div class="blog-item">
                                        <div class="blog-thumb">
                                            <img src="images/blog/blog-1.jpg" alt="" class="img-fluid ">
                                        </div>
                                        <div class="blog-item-content">
                                            <div class="blog-item-meta mb-3 mt-4">
                                                <span class="text-muted text-capitalize mr-3"><i class="icofont-comment mr-2"></i>5 Comments</span>
                                                <span class="text-black text-capitalize mr-3"><i class="icofont-calendar mr-1"></i>${news.created_at}</span>
                                            </div> 

                                            <h2 class="mt-3 mb-3"><a href="blog-single.html">${news.title}</a></h2>

                                            <p class="mb-4">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Blanditiis aliquid architecto facere commodi cupiditate omnis voluptatibus inventore atque velit cum rem id assumenda quam recusandae ipsam ea porro, dicta ad.</p>

                                            <a href="blog-single.html" target="_blank" class="btn btn-main btn-icon btn-round-full">Read More <i class="icofont-simple-right ml-2  "></i></a>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="col-lg-4">
                        <div class="sidebar-wrap pl-lg-4 mt-5 mt-lg-0">
                            <div class="sidebar-widget search  mb-3 ">
                                <h5>Search Here</h5>
                                <form action="searchNews" method="get" class="search-form">
                                    <input type="text" name="search" class="form-control" placeholder="search" value="${searchValue}">
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
                                        <a href="news">All</a>
                                        <!--<span>(14)</span>-->
                                    </li>
                                    <c:forEach var="o" items="${cateList}">
                                        <li class="align-items-center">
                                            <a href="categoryNews?categoryID=${o.category_id}">${o.name}</a>
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

                <div class="row mt-5">
                    <div class="col-lg-8">
                        <nav class="pagination py-2 d-inline-block">
                            <div class="nav-links">
                                <c:forEach begin="1" end="${endPage}" var="i">
                                    <!--<span aria-current="page" class="page-numbers current">1</span>-->
                                    <a class="page-numbers" href="#">${i}</a>
                                </c:forEach>
                                <!--<a class="page-numbers" href="#"><i class="icofont-thin-double-right"></i></a>-->
                            </div>
                        </nav>
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
                                <img src="images/logo.png" alt="" class="img-fluid">
                            </div>
                            <p>Tempora dolorem voluptatum nam vero assumenda voluptate, facilis ad eos obcaecati tenetur veritatis eveniet distinctio possimus.</p>

                            <ul class="list-inline footer-socials mt-4">
                                <li class="list-inline-item"><a href="https://www.facebook.com/themefisher"><i class="icofont-facebook"></i></a></li>
                                <li class="list-inline-item"><a href="https://twitter.com/themefisher"><i class="icofont-twitter"></i></a></li>
                                <li class="list-inline-item"><a href="https://www.pinterest.com/themefisher/"><i class="icofont-linkedin"></i></a></li>
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
                                &copy; Copyright Reserved to <span class="text-color">Novena</span> by <a href="https://themefisher.com/" target="_blank">Themefisher</a>
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
        <script src="plugins/jquery/jquery.js"></script>
        <!-- Bootstrap 4.3.2 -->
        <script src="plugins/bootstrap/js/popper.js"></script>
        <script src="plugins/bootstrap/js/bootstrap.min.js"></script>
        <script src="plugins/counterup/jquery.easing.js"></script>
        <!-- Slick Slider -->
        <script src="plugins/slick-carousel/slick/slick.min.js"></script>
        <!-- Counterup -->
        <script src="plugins/counterup/jquery.waypoints.min.js"></script>

        <script src="plugins/shuffle/shuffle.min.js"></script>
        <script src="plugins/counterup/jquery.counterup.min.js"></script>
        <!-- Google Map -->
        <script src="plugins/google-map/map.js"></script>
        <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAkeLMlsiwzp6b3Gnaxd86lvakimwGA6UA&callback=initMap"></script>    

        <script src="js/script.js"></script>
        <script src="js/contact.js"></script>

    </body>
</html>
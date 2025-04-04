<%-- 
    Document   : doctor-single
    Created on : Mar 3, 2025, 7:16:36 AM
    Author     : Win11
--%>

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
        <jsp:include page="headerHome.jsp"></jsp:include>
            <section class="page-title bg-1">
                <div class="overlay"></div>
                <div class="container">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="block text-center">
                                <span class="text-white">Doctor Details</span>
                                <h1 class="text-capitalize mb-5 text-lg">${professional.getName()}</h1>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <section class="section doctor-single">
            <div class="container">
                <div class="row">
                    <div class="col-lg-4 col-md-6">
                        <div class="doctor-img-block">
                            <img src="assets/img/${professional.getPicture()}" alt="" class="img-fluid w-100">
                            <div class="info-block mt-4">
                                <h4 class="mb-0">${professional.getName()}</h4>
                                <p>${professional.getSpecialization()}</p>
                                <ul class="list-inline mt-4 doctor-social-links">
                                    <li class="list-inline-item"><a href="#"><i class="icofont-facebook"></i></a></li>
                                    <li class="list-inline-item"><a href="#"><i class="icofont-twitter"></i></a></li>
                                    <li class="list-inline-item"><a href="#"><i class="icofont-skype"></i></a></li>
                                    <li class="list-inline-item"><a href="#"><i class="icofont-linkedin"></i></a></li>
                                    <li class="list-inline-item"><a href="#"><i class="icofont-pinterest"></i></a></li>
                                </ul>
                            </div>
                        </div>
                    </div>

                    <div class="col-lg-8 col-md-6">
                        <div class="doctor-details mt-4 mt-lg-0">
                            <h2 class="text-md">Introducing to myself</h2>
                            <div class="divider my-4"></div>
                            <p>${professional.getBiography()}</p>
                            <p>${professional.getQualification()}</p>
                            <a href="appoinment.html" class="btn btn-main-2 btn-round-full mt-3">
                                Make an Appointment <i class="icofont-simple-right ml-2"></i>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </section>


        <section class="section doctor-qualification gray-bg">
            <div class="container">
                <div class="row">
                    <div class="col-lg-6">
                        <div class="section-title">
                            <h3>My Educational Qualifications</h3>
                            <div class="divider my-4"></div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-lg-6">
                        <div class="edu-block mb-5">
                            <span class="h6 text-muted">Year(2005-2007) </span>
                            <h4 class="mb-3 title-color">MBBS, M.D at University of Wyoming</h4>
                            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Nisi doloremque harum, mollitia, soluta maxime porro veritatis fuga autem impedit corrupti aperiam sint, architecto, error nesciunt temporibus! Vel quod, dolor aliquam!</p>
                        </div>

                        <div class="edu-block">
                            <span class="h6 text-muted">Year(2007-2009) </span>
                            <h4 class="mb-3 title-color">M.D. of Netherland Medical College</h4>
                            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Nisi doloremque harum, mollitia, soluta maxime porro veritatis fuga autem impedit corrupti aperiam sint, architecto, error nesciunt temporibus! Vel quod, dolor aliquam!</p>
                        </div>
                    </div>

                    <div class="col-lg-6">
                        <div class="edu-block mb-5">
                            <span class="h6 text-muted">Year(2009-2010) </span>
                            <h4 class="mb-3 title-color">MBBS, M.D at University of Japan</h4>
                            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Nisi doloremque harum, mollitia, soluta maxime porro veritatis fuga autem impedit corrupti aperiam sint, architecto, error nesciunt temporibus! Vel quod, dolor aliquam!</p>
                        </div>

                        <div class="edu-block">
                            <span class="h6 text-muted">Year(2010-2011) </span>
                            <h4 class="mb-3 title-color">M.D. of Canada Medical College</h4>
                            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Nisi doloremque harum, mollitia, soluta maxime porro veritatis fuga autem impedit corrupti aperiam sint, architecto, error nesciunt temporibus! Vel quod, dolor aliquam!</p>
                        </div>
                    </div>
                </div>
            </div>
        </section>


        <section class="section doctor-skills">
            <div class="container">
                <div class="row">
                    <div class="col-lg-4">
                        <h3>My skills</h3>
                        <div class="divider my-4"></div>
                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. In architecto voluptatem alias, aspernatur voluptatibus corporis quisquam? Consequuntur, ad, doloribus, doloremque voluptatem at consectetur natus eum ipsam dolorum iste laudantium tenetur.</p>
                    </div>
                    <div class="col-lg-4">
                        <div class="skill-list">
                            <h5 class="mb-4">Expertise area</h5>
                            <ul class="list-unstyled department-service">
                                <li><i class="icofont-check mr-2"></i>International Drug Database</li>
                                <li><i class="icofont-check mr-2"></i>Stretchers and Stretcher Accessories</li>
                                <li><i class="icofont-check mr-2"></i>Cushions and Mattresses</li>
                                <li><i class="icofont-check mr-2"></i>Cholesterol and lipid tests</li>
                                <li><i class="icofont-check mr-2"></i>Critical Care Medicine Specialists</li>
                                <li><i class="icofont-check mr-2"></i>Emergency Assistance</li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-lg-4">
                        <div class="sidebar-widget  gray-bg p-4">
                            <h5 class="mb-4">Make Appoinment</h5>

                            <ul class="list-unstyled lh-35">
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
                                <h3 class="text-color-2">+23-4565-65768</h3>
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

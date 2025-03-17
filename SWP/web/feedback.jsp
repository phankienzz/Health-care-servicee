<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page language="java" %>
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
        <style>
            .feed {
                /*font-family: Arial, sans-serif;*/
                text-align: center;
                /*padding: 20px;*/
            }

            /* Nút mở feedback & về home */
            .open-feedback, .back-home {
                background-color: #007bff;
                color: white;
                padding: 10px 20px;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                transition: background 0.3s;
                margin: 10px;
            }
            .open-feedback:hover {
                background-color: #0056b3;
            }
            .back-home {
                background-color: #dc3545;
            }
            .back-home:hover {
                background-color: #c82333;
            }

            /* Hộp thoại feedback */
            .modal {
                display: none;
                position: fixed;
                z-index: 1000;
                left: 0;
                top: 0;
                width: 100%;
                height: 100%;
                background-color: rgba(0, 0, 0, 0.4);
                opacity: 0;
                transition: opacity 0.3s ease-in-out;
            }
            .modal.show {
                display: block;
                opacity: 1;
            }
            .modal-content {
                background-color: white;
                margin: 10% auto;
                padding: 20px;
                border: 1px solid #ddd;
                border-radius: 10px;
                width: 50%;
                text-align: center;
                transform: translateY(-20px);
                opacity: 0;
                transition: transform 0.3s ease-in-out, opacity 0.3s ease-in-out;
            }
            .modal.show .modal-content {
                transform: translateY(0);
                opacity: 1;
            }
            .closed {
                color: red;
                /*float: right;*/
                font-size: 28px;
                font-weight: bold;
                /*cursor: pointer;*/
                text-align: right;
            }

            /* Hiển thị sao */
            .stars {
                display: flex;
                flex-direction: row; /* Chọn từ trái qua phải */
                justify-content: center;
                gap: 10px;
            }
            .stars input {
                display: none;
            }
            .stars label {
                font-size: 30px;
                color: #ccc;
                cursor: pointer;
            }
            .stars input:checked ~ label {
                color: #ffcc00;
            }

            /* Thông báo lỗi */
            .error {
                color: red;
                font-size: 14px;
                display: none;
                margin-top: 5px;
            }

            textarea {
                width: 100%;
                height: 100px;
                margin-top: 10px;
                padding: 5px;
            }

            button {
                background-color: #28a745;
                color: white;
                padding: 10px 20px;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                transition: background 0.3s;
            }
            button:hover {
                background-color: #218838;
            }
        </style>
    </head>

    <body id="top">

        <jsp:include page="headerHome.jsp"></jsp:include>
            <section class="page-title bg-1">
                <div class="overlay"></div>
                <div class="container">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="block text-center">
                                <span class="text-white">Feedback Us</span>
                                <h1 class="text-capitalize mb-5 text-lg">Feedback</h1>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
            <!-- contact form start -->

            <section class="section pb-0 feed">
                <div class="container"> 
                    <h2>Cảm ơn bạn đã mua hàng!</h2>
                    <p>Vui lòng để lại phản hồi của bạn để chúng tôi cải thiện dịch vụ.</p>

                    <!-- Nút mở hộp thoại feedback -->
                    <button class="open-feedback" onclick="openFeedback()">Để lại phản hồi</button>
                    <button class="back-home" onclick="goHome()">Trở về trang chủ</button>

                    <!-- Hộp thoại feedback -->
                    <div id="feedbackModal" class="modal">
                        <div class="modal-content">
                            <div class="closed">
                                <span style="cursor: pointer;" onclick="closeFeedback()">&times;</span>
                            </div>
                            <h2>Phản hồi của bạn</h2>

                            <form id="feedbackForm" action="SubmitFeedbackServlet" method="post" onsubmit="return validateFeedback()">
                                <div class="stars">
                                    <input type="radio" id="star1" name="rating" value="1"><label for="star1">★</label>
                                    <input type="radio" id="star2" name="rating" value="2"><label for="star2">★</label>
                                    <input type="radio" id="star3" name="rating" value="3"><label for="star3">★</label>
                                    <input type="radio" id="star4" name="rating" value="4"><label for="star4">★</label>
                                    <input type="radio" id="star5" name="rating" value="5"><label for="star5">★</label>
                                </div>

                                <!-- Thông báo lỗi khi chưa chọn sao -->
                                <p class="error" id="ratingError">Chọn số sao tương ứng với mức độ hài lòng nhé !</p>

                                <textarea name="comment" placeholder="Nhập phản hồi của bạn..."></textarea>
                                <br>
                                <button type="submit">Gửi phản hồi</button>
                            </form>
                        </div>
                    </div>
                </div>
            </section>

            <section class="contact-form-wrap section">
                <div class="container">
                    <!--                        <div class="row justify-content-center">
                                                <div class="col-lg-6">
                                                    <div class="section-title text-center">
                                                        <h2 class="text-md mb-2">Contact us</h2>
                                                        <div class="divider mx-auto my-4"></div>
                                                        <p class="mb-5">Laboriosam exercitationem molestias beatae eos pariatur, similique, excepturi mollitia sit perferendis maiores ratione aliquam?</p>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-lg-12 col-md-12 col-sm-12">
                                                    <form id="contact-form" class="contact__form " method="post" action="mail.php">
                                                         form message 
                                                        <div class="row">
                                                            <div class="col-12">
                                                                <div class="alert alert-success contact__msg" style="display: none" role="alert">
                                                                    Your message was sent successfully.
                                                                </div>
                                                            </div>
                                                        </div>
                            
                                                        <div class="row">
                                                            <div class="col-lg-6">
                                                                <div class="form-group">
                                                                    <input name="name" id="name" type="text" class="form-control" placeholder="Your Full Name" >
                                                                </div>
                                                            </div>
                            
                                                            <div class="col-lg-6">
                                                                <div class="form-group">
                                                                    <input name="email" id="email" type="email" class="form-control" placeholder="Your Email Address">
                                                                </div>
                                                            </div>
                                                            <div class="col-lg-6">
                                                                <div class="form-group">
                                                                    <input name="subject" id="subject" type="text" class="form-control" placeholder="Your Query Topic">
                                                                </div>
                                                            </div>
                                                            <div class="col-lg-6">
                                                                <div class="form-group">
                                                                    <input name="phone" id="phone" type="text" class="form-control" placeholder="Your Phone Number">
                                                                </div>
                                                            </div>
                                                        </div>
                            
                                                        <div class="form-group-2 mb-4">
                                                            <textarea name="message" id="message" class="form-control" rows="8" placeholder="Your Message"></textarea>
                                                        </div>
                            
                                                        <div class="text-center">
                                                            <input class="btn btn-main btn-round-full" name="submit" type="submit" value="Send Messege"></input>
                                                        </div>
                                                    </form>
                                                </div>
                                            </div>-->
                </div>
            </section>


            <!-- footer Start -->
        <jsp:include page="footer.jsp"></jsp:include>


        <!-- 
        Essential Scripts
        =====================================-->


        <!-- Main jQuery -->
        <script>
            // Mở hộp thoại feedback
            function openFeedback() {
                let modal = document.getElementById("feedbackModal");

                // Reset feedback mỗi lần mở lại
                resetFeedback();

                modal.style.display = "block";
                setTimeout(() => {
                    modal.classList.add("show");
                }, 10);
            }

            // Đóng hộp thoại feedback
            function closeFeedback() {
                let modal = document.getElementById("feedbackModal");
                modal.classList.remove("show");

                // Đợi 300ms trước khi ẩn modal
                setTimeout(() => {
                    modal.style.display = "none";
                }, 300);
            }

            // Reset form feedback khi mở lại
            function resetFeedback() {
                document.querySelectorAll('input[name="rating"]').forEach((radio) => {
                    radio.checked = false;
                });
                document.querySelector('textarea').value = "";
                document.getElementById("ratingError").style.display = "none";
            }

            // Điều hướng về trang chủ
            function goHome() {
                window.location.href = "home"; // Hoặc "home" nếu dùng servlet
            }

            // Kiểm tra xem khách hàng đã chọn sao chưa
            function validateFeedback() {
                let ratingChecked = document.querySelector('input[name="rating"]:checked');
                let errorText = document.getElementById("ratingError");

                if (!ratingChecked) {
                    errorText.style.display = "block"; // Hiển thị lỗi nếu chưa chọn sao
                    return false; // Ngăn không cho submit form
                }
                return true; // Cho phép submit form nếu đã chọn sao
            }
        </script>
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

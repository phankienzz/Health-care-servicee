<%-- 
    Document   : viewmedical-customer
    Created on : Mar 15, 2025, 9:19:36 PM
    Author     : Win11
--%>

<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="zxx">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="description" content="Medical History">
    <meta name="author" content="Your Company">
    <title>Patient Medical History</title>
    <link rel="stylesheet" href="plugins/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/style.css">
</head>
<body id="top">
    <jsp:include page="headerHome.jsp"></jsp:include>
<header>
    <nav class="navbar navbar-expand-lg navigation">
        <div class="container">
            <a class="navbar-brand" href="index.jsp">
                <img src="images/logo.png" alt="Logo" class="img-fluid">
            </a>
        </div>
    </nav>
</header>

<section class="page-title bg-1">
    <div class="overlay"></div>
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="block text-center">
                    <span class="text-white">Medical History</span>
                    <h1 class="text-capitalize mb-5 text-lg">Your Medical History</h1>
                </div>
            </div>
        </div>
    </div>
</section>

<section class="section contact-info pb-0">
    <div class="container">
        <div class="row">
            <!-- Dynamically generate medical records -->
            <c:forEach var="record" items="${medicalRecords}">
                <div class="col-lg-4 col-sm-6 col-md-6">
                    <div class="contact-block mb-4 mb-lg-0">
                        <i class="icofont-prescription"></i>
                        <h5>Examination ID: ${record.examinationID}</h5>
                        <p><strong>Diagnosis:</strong> ${record.diagnosis}</p>
                        <p><strong>Doctor:</strong> ${record.doctorName}</p>
                        <p><strong>Phone:</strong> ${record.phone}</p>
                        <p><strong>Treatment Plan:</strong> ${record.treatmentPlan}</p>
                        <p><strong>Medications:</strong> ${record.medicationsPrescribed}</p>
                        <p><strong>Date:</strong> ${record.createdAt}</p>
                        <p><strong>Notes:</strong> ${record.notes}</p>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</section>

<footer class="footer section gray-bg">
    <div class="container">
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
        </div>
    </div>
</footer>

<script src="plugins/jquery/jquery.js"></script>
<script src="plugins/bootstrap/js/popper.js"></script>
<script src="plugins/bootstrap/js/bootstrap.min.js"></script>
<script src="js/script.js"></script>
</body>
</html>


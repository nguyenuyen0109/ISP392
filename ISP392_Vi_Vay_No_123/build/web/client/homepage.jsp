<!-- /*
* Template URI: https://untree.co/
* License: https://creativecommons.org/licenses/by/3.0/
*/ -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Account" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="author" content="Untree.co">
        <link rel="shortcut icon" href="favicon.png">

        <meta name="description" content="" />
        <meta name="keywords" content="bootstrap, bootstrap5" />

        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Work+Sans:wght@400;600;700&display=swap" rel="stylesheet">


        <link rel="stylesheet" href="fonts/icomoon/style.css">
        <link rel="stylesheet" href="fonts/flaticon/font/flaticon.css">

        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">

        <link rel="stylesheet" href="../assets/css/tiny-slider.css">
        <link rel="stylesheet" href="../assets/css/aos.css">
        <link rel="stylesheet" href="../assets/css/glightbox.min.css">
        <link rel="stylesheet" href="../assets/css/homepage.css">

        <link rel="stylesheet" href="../assets/css/flatpickr.min.css">


        <title>Financing &mdash; Free Bootstrap 5 Website Template by Untree.co</title>

    </head>
    <body>
        <jsp:include page="/navigator/toast.jsp" />
        <jsp:include page="/navigator/header.jsp" />

        <div class="hero overlay">
            <img src="../assets/images/blob.svg" alt="" class="img-fluid blob">
            <div class="container">
                <div class="row align-items-center justify-content-between pt-5">
                    <div class="col-lg-6 text-center text-lg-start pe-lg-5">
                        <h1 class="heading text-white mb-3" data-aos="fade-up">Smart banking for financial freedom.</h1>
                        <p class="text-white mb-5" data-aos="fade-up" data-aos-delay="100">Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts.</p>
                        <div class="align-items-center mb-5 mm" data-aos="fade-up" data-aos-delay="200">
                            <a href="contact.html" class="btn btn-outline-white-reverse me-4">Contact us</a>
                            <a href="https://www.youtube.com/watch?v=Mb1zrW_zra4" class="text-white glightbox">Watch the video</a>
                        </div>
                    </div>
                    <div class="col-lg-6" data-aos="fade-up" data-aos-delay="300">
                        <div class="img-wrap">
                            <img src="../assets/images/img-1.jpg" alt="Image" class="img-fluid rounded">
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <div class="section">
            <div class="container">
                <div class="row justify-content-between">
                    <div class="col-lg-7 mb-4 mb-lg-0">
                        <img src="../assets/images/img-3.jpg" alt="Image" class="img-fluid rounded
                             ">
                    </div>
                    <div class="col-lg-4 ps-lg-2">
                        <div class="mb-5">
                            <h2 class="text-black h4">Make payment fast and smooth.</h2>
                            <p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts.</p>
                        </div>
                        <div class="d-flex mb-3 service-alt">
                            <div>
                                <span class="bi-wallet-fill me-4"></span>
                            </div>
                            <div>
                                <h3>Build financial</h3>
                                <p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts.</p>
                            </div>
                        </div>

                        <div class="d-flex mb-3 service-alt">
                            <div>
                                <span class="bi-pie-chart-fill me-4"></span>
                            </div>
                            <div>
                                <h3>Invest for the future</h3>
                                <p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts.</p>
                            </div>
                        </div>

                    </div>

                </div>
            </div>
        </div>

        <div class="section sec-features">
            <div class="container">
                <div class="row g-5">
                    <div class="col-12 col-sm-6 col-md-6 col-lg-4" data-aos="fade-up" data-aos-delay="0">
                        <div class="feature d-flex">
                            <span class="bi-bag-check-fill"></span>
                            <div>
                                <h3>Build financial</h3>
                                <p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts. </p>
                            </div>
                        </div>
                    </div>
                    <div class="col-12 col-sm-6 col-md-6 col-lg-4" data-aos="fade-up" data-aos-delay="100">
                        <div class="feature d-flex">
                            <span class="bi-wallet-fill"></span>
                            <div>
                                <h3>Invest for the future</h3>
                                <p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts. </p>
                            </div>
                        </div>
                    </div>
                    <div class="col-12 col-sm-6 col-md-6 col-lg-4" data-aos="fade-up" data-aos-delay="200">
                        <div class="feature d-flex">
                            <span class="bi-pie-chart-fill"></span>
                            <div>
                                <h3>Responsible banking</h3>
                                <p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts. </p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="section">
            <div class="container">
                <div class="row">
                    <div class="col-lg-7 order-lg-2 mb-4 mb-lg-0">
                        <img src="../assets/images/img-1.jpg" alt="Image" class="img-fluid">
                    </div>
                    <div class="col-lg-5 pe-lg-5">
                        <div class="mb-5">
                            <h2 class="text-black h4">Straight-forward way of financing</h2>
                        </div>
                        <div class="d-flex mb-3 service-alt">
                            <div>
                                <span class="bi-wallet-fill me-4"></span>
                            </div>
                            <div>
                                <h3>Build financial</h3>
                                <p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts.</p>
                            </div>
                        </div>

                        <div class="d-flex mb-3 service-alt">
                            <div>
                                <span class="bi-pie-chart-fill me-4"></span>
                            </div>
                            <div>
                                <h3>Invest for the future</h3>
                                <p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts.</p>
                            </div>
                        </div>

                        <div class="d-flex mb-3 service-alt">
                            <div>
                                <span class="bi-bag-check-fill me-4"></span>
                            </div>
                            <div>
                                <h3>Responsible banking</h3>
                                <p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts.</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>


    <div class="section sec-services">
        <div class="container">
            <div class="row mb-5">
                <div class="col-lg-5 mx-auto text-center" data-aos="fade-up">
                    <h2 class="heading text-primary">Our Services</h2>
                    <p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts. </p>
                </div>
            </div>

            <div class="row">
                <div class="col-12 col-sm-6 col-md-6 col-lg-4" data-aos="fade-up">

                    <div class="service text-center">
                        <span class="bi-cash-coin"></span>
                        <div>
                            <h3>Faster payments</h3>
                            <p class="mb-4">Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts. </p>
                            <p><a href="#" class="btn btn-outline-primary py-2 px-3">Read more</a></p>
                        </div>
                    </div>

                </div>
                <div class="col-12 col-sm-6 col-md-6 col-lg-4" data-aos="fade-up" data-aos-delay="100">
                    <div class="service text-center">
                        <span class="bi-chat-text"></span>
                        <div>
                            <h3>Grow your business</h3>
                            <p class="mb-4">Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts. </p>
                            <p><a href="#" class="btn btn-outline-primary py-2 px-3">Read more</a></p>
                        </div>
                    </div>
                </div>
                <div class="col-12 col-sm-6 col-md-6 col-lg-4" data-aos="fade-up" data-aos-delay="200">
                    <div class="service text-center">
                        <span class="bi-fingerprint"></span>
                        <div>
                            <h3>Investments</h3>
                            <p class="mb-4">Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts. </p>
                            <p><a href="#" class="btn btn-outline-primary py-2 px-3">Read more</a></p>
                        </div>
                    </div>
                </div>

                <div class="col-12 col-sm-6 col-md-6 col-lg-4" data-aos="fade-up" data-aos-delay="100">

                    <div class="service text-center">
                        <span class="bi-gear"></span>
                        <div>
                            <h3>Payment & Cards</h3>
                            <p class="mb-4">Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts. </p>
                            <p><a href="#" class="btn btn-outline-primary py-2 px-3">Read more</a></p>
                        </div>
                    </div>

                </div>
                <div class="col-12 col-sm-6 col-md-6 col-lg-4" data-aos="fade-up" data-aos-delay="200">
                    <div class="service text-center">
                        <span class="bi-graph-up-arrow"></span>
                        <div>
                            <h3>Strategic Finance</h3>
                            <p class="mb-4">Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts. </p>
                            <p><a href="#" class="btn btn-outline-primary py-2 px-3">Read more</a></p>
                        </div>
                    </div>
                </div>
                <div class="col-12 col-sm-6 col-md-6 col-lg-4" data-aos="fade-up" data-aos-delay="300">
                    <div class="service text-center">
                        <span class="bi-layers"></span>
                        <div>
                            <h3>Digital Currency</h3>
                            <p class="mb-4">Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts. </p>
                            <p><a href="#" class="btn btn-outline-primary py-2 px-3">Read more</a></p>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>


    <div class="section sec-cta overlay" style="background-image: url('images/img-3.jpg')">
        <div class="container">
            <div class="row justify-content-between align-items-center">
                <div class="col-lg-5" data-aos="fade-up" data-aos-delay="0">
                    <h2 class="heading">Wanna Talk To Us?</h2>
                    <p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts. </p>
                </div>
                <div class="col-lg-5 text-end" data-aos="fade-up" data-aos-delay="100">
                    <a href="#" class="btn btn-outline-white-reverse">Contact us</a>
                </div>
            </div>
        </div>
    </div>


    <div class="section sec-portfolio bg-light pb-5	">
        <div class="container">
            <div class="row mb-5">
                <div class="col-lg-5 mx-auto text-center ">
                    <h2 class="heading text-primary mb-3" data-aos="fade-up" data-aos-delay="0">Case Studies</h2>
                    <p class="mb-4" data-aos="fade-up" data-aos-delay="100">Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts. </p>

                    <div id="post-slider-nav" data-aos="fade-up" data-aos-delay="200">
                        <button class="btn btn-primary py-2" class="prev" data-controls="prev">Prev</button>
                        <button class="btn btn-primary py-2" class="next" data-controls="next">Next</button>
                    </div>

                </div>
            </div>
        </div>

        <div class="post-slider-wrap" data-aos="fade-up" data-aos-delay="300">



            <div id="post-slider" class="post-slider">
                <div class="item">
                    <a href="../assets/case-study.html" class="card d-block">
                        <img src="../assets/images/img-1.jpg" class="card-img-top" alt="Image">
                        <div class="card-body">
                            <h5 class="card-title">Behind the word mountains</h5>
                            <p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts. </p>
                        </div>
                    </a>
                </div>

                <div class="item">
                    <a href="case-study.html" class="card">
                        <img src="../assets/images/img-2.jpg" class="card-img-top" alt="Image">
                        <div class="card-body">
                            <h5 class="card-title">Behind the word mountains</h5>
                            <p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts. </p>
                        </div>
                    </a>
                </div>

                <div class="item">
                    <a href="../assets/case-study.html" class="card">
                        <img src="images/img-3.jpg" class="card-img-top" alt="Image">
                        <div class="card-body">
                            <h5 class="card-title">Behind the word mountains</h5>
                            <p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts. </p>
                        </div>
                    </a>
                </div>

                <div class="item">
                    <a href="../assets/case-study.html" class="card">
                        <img src="../assets/images/img-4.jpg" class="card-img-top" alt="Image">
                        <div class="card-body">
                            <h5 class="card-title">Behind the word mountains</h5>
                            <p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts. </p>
                        </div>
                    </a>
                </div>

                <div class="item">
                    <a href="../assets/case-study.html" class="card">
                        <img src="../assets/images/img-1.jpg" class="card-img-top" alt="Image">
                        <div class="card-body">
                            <h5 class="card-title">Behind the word mountains</h5>
                            <p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts. </p>
                        </div>
                    </a>
                </div>
            </div>
        </div>


    </div>

    <div class="section sec-testimonial bg-light">
        <div class="container">
            <div class="row mb-5 justify-content-center">
                <div class="col-lg-6 text-center">
                    <h2 class="heading text-primary">Testimonials</h2>
                </div>

            </div>


            <div class="testimonial-slider-wrap">
                <div class="testimonial-slider" id="testimonial-slider">
                    <div class="item">
                        <div class="testimonial-half d-lg-flex bg-white">
                            <div class="img" style="background-image: url('images/img-4.jpg')">

                            </div>
                            <div class="text">
                                <blockquote>
                                    <p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts. Separated they live in Bookmarksgrove right at the coast of the Semantics, a large language ocean.</p>
                                </blockquote>
                                <div class="author">
                                    <strong class="d-block text-black">John Campbell</strong>
                                    <span>CEO & Co-founder</span>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="item">
                        <div class="testimonial-half d-lg-flex bg-white">
                            <div class="img" style="background-image: url('images/img-3.jpg')">

                            </div>
                            <div class="text">
                                <blockquote>
                                    <p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts. Separated they live in Bookmarksgrove right at the coast of the Semantics, a large language ocean.</p>
                                </blockquote>
                                <div class="author">
                                    <strong class="d-block text-black">John Campbell</strong>
                                    <span>CEO & Co-founder</span>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="item">
                        <div class="testimonial-half d-lg-flex bg-white">
                            <div class="img" style="background-image: url('images/img-2.jpg')">

                            </div>
                            <div class="text">
                                <blockquote>
                                    <p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts. Separated they live in Bookmarksgrove right at the coast of the Semantics, a large language ocean.</p>
                                </blockquote>
                                <div class="author">
                                    <strong class="d-block text-black">John Campbell</strong>
                                    <span>CEO & Co-founder</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>



    <div class="section sec-news">
        <div class="container">
            <div class="row mb-5">
                <div class="col-lg-7">
                    <h2 class="heading text-primary">Latest News</h2>
                </div>
            </div>

            <div class="row">
                <div class="col-lg-4">
                    <div class="card post-entry">
                        <a href="single.html"><img src="../assets/images/img-1.jpg" class="card-img-top" alt="Image"></a>
                        <div class="card-body">
                            <div><span class="text-uppercase font-weight-bold date">Jan 20, 2021</span></div>
                            <h5 class="card-title"><a href="single.html">Behind the word mountains</a></h5>
                            <p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts. </p>
                            <p class="mt-5 mb-0"><a href="#">Read more</a></p>
                        </div>
                    </div>
                </div>

                <div class="col-lg-4">
                    <div class="card post-entry">
                        <a href="single.html"><img src="../assets/images/img-2.jpg" class="card-img-top" alt="Image"></a>
                        <div class="card-body">
                            <div><span class="text-uppercase font-weight-bold date">Jan 20, 2021</span></div>
                            <h5 class="card-title"><a href="single.html">Behind the word mountains</a></h5>
                            <p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts. </p>
                            <p class="mt-5 mb-0"><a href="#">Read more</a></p>
                        </div>
                    </div>
                </div>

                <div class="col-lg-4">
                    <div class="card post-entry">
                        <a href="single.html"><img src="../assets/images/img-3.jpg" class="card-img-top" alt="Image"></a>
                        <div class="card-body">
                            <div><span class="text-uppercase font-weight-bold date">Jan 20, 2021</span></div>
                            <h5 class="card-title"><a href="single.html">Behind the word mountains</a></h5>
                            <p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts. </p>
                            <p class="mt-5 mb-0"><a href="single.html">Read more</a></p>
                        </div>
                    </div>
                </div>


            </div>
        </div>
    </div>

    <div class="site-footer">
        <div class="container">
            <div class="row">
                <div class="col-lg-4">
                    <div class="widget">
                        <h3>About</h3>
                        <p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts.</p>
                    </div> <!-- /.widget -->
                    <div class="widget">
                        <address>43 Raymouth Rd. Baltemoer, <br> London 3910</address>
                        <ul class="list-unstyled links">
                            <li><a href="tel://11234567890">+1(123)-456-7890</a></li>
                            <li><a href="tel://11234567890">+1(123)-456-7890</a></li>
                            <li><a href="mailto:info@mydomain.com">info@mydomain.com</a></li>
                        </ul>
                    </div> <!-- /.widget -->
                </div> <!-- /.col-lg-4 -->
                <div class="col-lg-4">
                    <div class="widget">
                        <h3>Company</h3>
                        <ul class="list-unstyled float-start links">
                            <li><a href="#">About us</a></li>
                            <li><a href="#">Services</a></li>
                            <li><a href="#">Vision</a></li>
                            <li><a href="#">Mission</a></li>
                            <li><a href="#">Terms</a></li>
                            <li><a href="#">Privacy</a></li>
                        </ul>
                        <ul class="list-unstyled float-start links">
                            <li><a href="#">Partners</a></li>
                            <li><a href="#">Business</a></li>
                            <li><a href="#">Careers</a></li>
                            <li><a href="#">Blog</a></li>
                            <li><a href="#">FAQ</a></li>
                            <li><a href="#">Creative</a></li>
                        </ul>
                    </div> <!-- /.widget -->
                </div> <!-- /.col-lg-4 -->
                <div class="col-lg-4">
                    <div class="widget">
                        <h3>Navigation</h3>
                        <ul class="list-unstyled links mb-4">
                            <li><a href="#">Our Vision</a></li>
                            <li><a href="#">About us</a></li>
                            <li><a href="#">Contact us</a></li>
                        </ul>

                        <h3>Social</h3>
                        <ul class="list-unstyled social">
                            <li><a href="#"><span class="icon-instagram"></span></a></li>
                            <li><a href="#"><span class="icon-twitter"></span></a></li>
                            <li><a href="#"><span class="icon-facebook"></span></a></li>
                            <li><a href="#"><span class="icon-linkedin"></span></a></li>
                            <li><a href="#"><span class="icon-pinterest"></span></a></li>
                            <li><a href="#"><span class="icon-dribbble"></span></a></li>
                        </ul>
                    </div> <!-- /.widget -->
                </div> <!-- /.col-lg-4 -->
            </div> <!-- /.row -->

            <div class="row mt-5">
                <div class="col-12 text-center">
                    <!-- 
**==========
NOTE: 
Please don't remove this copyright link unless you buy the license here https://untree.co/license/  
**==========
                    -->

                    <p>Copyright &copy;<script>document.write(new Date().getFullYear());</script>. All Rights Reserved. &mdash; Designed with love by <a href="https://untree.co">Untree.co</a> Distributed By <a href="https://themewagon.com">ThemeWagon</a> <!-- License information: https://untree.co/license/ -->
                    </p>
                </div>
            </div>
        </div> <!-- /.container -->
    </div> <!-- /.site-footer -->

    <!-- Preloader -->
    <div id="overlayer"></div>
    <div class="loader">
        <div class="spinner-border text-primary" role="status">
            <span class="visually-hidden">Loading...</span>
        </div>
    </div>

    <script src="../assets/js/bootstrap.bundle.min.js"></script>
    <script src="../assets/js/tiny-slider.js"></script>

    <script src="../assets/js/flatpickr.min.js"></script>


    <script src="../assets/js/aos.js"></script>
    <script src="../assets/js/glightbox.min.js"></script>
    <script src="../assets/js/navbar.js"></script>
    <script src="../assets/js/counter.js"></script>
    <script src="../assets/js/custom.js"></script>
</body>
</html>

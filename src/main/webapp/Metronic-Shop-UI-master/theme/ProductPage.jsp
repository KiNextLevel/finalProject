<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
<head>
    <link rel="icon" type="image/png" sizes="16x16"  href="/favicon-32x32.png">
    <meta charset="utf-8">
    <title>결제 상품 페이지</title>
    <link href="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/css/ProductPage.css" rel="stylesheet">

    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

    <meta content="Metronic Shop UI description" name="description">
    <meta content="Metronic Shop UI keywords" name="keywords">
    <meta content="keenthemes" name="author">

    <meta property="og:site_name" content="-CUSTOMER VALUE-">
    <meta property="og:title" content="-CUSTOMER VALUE-">
    <meta property="og:description" content="-CUSTOMER VALUE-">
    <meta property="og:type" content="website">
    <meta property="og:image" content="-CUSTOMER VALUE-"><!-- link to image for socio -->
    <meta property="og:url" content="-CUSTOMER VALUE-">

    <link rel="shortcut icon" href="favicon.ico">

    <!-- Fonts START -->
    <link
            href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700|PT+Sans+Narrow|Source+Sans+Pro:200,300,400,600,700,900&amp;subset=all"
            rel="stylesheet" type="text/css">
    <!-- Fonts END -->

    <!-- Global styles START -->
    <link href="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/plugins/font-awesome/css/font-awesome.min.css"
          rel="stylesheet">
    <link href="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/plugins/bootstrap/css/bootstrap.min.css"
          rel="stylesheet">
    <!-- Global styles END -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Page level plugin styles START -->
    <link href="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/plugins/fancybox/source/jquery.fancybox.css"
          rel="stylesheet">
    <link href="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/plugins/owl.carousel/assets/owl.carousel.css"
          rel="stylesheet">
    <!-- Page level plugin styles END -->

    <!-- Theme styles START -->
    <link href="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/pages/css/components.css"
          rel="stylesheet">
    <link href="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/corporate/css/style.css"
          rel="stylesheet">
    <link href="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/pages/css/style-shop.css"
          rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/corporate/css/style-responsive.css"
          rel="stylesheet">
    <link href="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/corporate/css/themes/red.css"
          rel="stylesheet" id="style-color">
    <link href="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/corporate/css/custom.css"
          rel="stylesheet">
    <!-- Theme styles END -->
</head>
<!-- Head END -->

<!-- Body BEGIN -->

<body class="ecommerce">
<!-- BEGIN TOP BAR -->
<div class="pre-header">
    <div class="container">
        <div class="row">
            <!-- BEGIN TOP BAR LEFT PART -->
            <div class="col-md-6 col-sm-6 additional-shop-info">
                <ul class="list-unstyled list-inline">
                    <li><i class="fa fa-phone"></i><span>010 - 1234 - 1234</span></li>
                    <!-- BEGIN LANGS -->
                    <li class="langs-block">
                        <a href="productPage.do" class="current"> 플러스샵 </a>
                    </li>
                    <!-- END LANGS -->
                </ul>
            </div>
            <!-- END TOP BAR LEFT PART -->
            <!-- BEGIN TOP BAR MENU -->
            <div class="col-md-6 col-sm-6 additional-nav">
                <ul class="list-unstyled list-inline pull-right">
                    <c:if test="${userRole==1}">
                        <li><a href="adminPage.do">관리자페이지</a></li>
                    </c:if>
                    <li><a href="myPage.do">마이페이지</a></li>
                    <li>메시지</li>
                    <li><a href="logout.do">로그아웃</a></li>
                </ul>
            </div>
            <!-- END TOP BAR MENU -->
        </div>
    </div>
</div>
<!-- END TOP BAR -->

<!-- BEGIN HEADER -->
<div class="header">
    <div class="container">
        <a class="site-logo" href="mainPage.do"><img
                src="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/corporate/img/logos/3.png"
                alt="Metronic Shop UI"></a>

        <a href="javascript:void(0);" class="mobi-toggler"><i class="fa fa-bars"></i></a>

        <!-- BEGIN TOP BAR MENU -->
        <div class="top-bar-right">
            <ul class="top-bar-list">
            </ul>
        </div>
        <!-- END TOP BAR MENU -->
    </div>
</div>
<!-- Header END -->


<div class="main">
    <div class="container">
        <!-- BEGIN SIDEBAR & CONTENT -->
        <div class="row margin-bottom-40">
            <!-- BEGIN CONTENT -->
            <div class="col-md-9 col-sm-7">
                <h1>결제 상품 페이지</h1>
                <div class="content-page">
                    <h3>Token</h3>

                    <!-- TOKEN 섹션 -->
                    <h3 class="section-title"><i class="fa fa-diamond"></i> 프리미엄 토큰</h3>
                    <div class="payment-section token-section">
                        <div class="token-info">
                            <div class="content-box">
                                <h4 class="feature-title">Next Love의 특별한 화폐, 토큰</h4>
                                <p class="feature-description">
                                    토큰은 Next Love에서 제공하는 프리미엄 서비스를 이용하기 위한 특별한 화폐입니다.
                                    토큰을 사용하여 마음에 드는 상대방과 1:1 채팅을 시작하고, 더 깊은 인연으로 발전시켜 보세요.
                                </p>
                                <div class="feature-list">
                                    <div class="feature-item">
                                        <i class="fa fa-check-circle"></i>
                                        <span>실시간 1:1 채팅 서비스</span>
                                    </div>
                                    <div class="feature-item">
                                        <i class="fa fa-check-circle"></i>
                                        <span>상대방당 1개의 토큰만 사용</span>
                                    </div>
                                    <div class="feature-item">
                                        <i class="fa fa-check-circle"></i>
                                        <span>토큰 구매 시 할인 혜택</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="token-options">
                            <div class="option-box">
                                <h5 class="option-title">토큰 패키지 선택</h5>
                                <!-- 기본 패키지 -->
                                <form action="tossPaymentPage.do" method="POST">
                                    <input type="hidden" name="productNumber" value="2">
                                    <input type="hidden" name="productName" value="기본 패키지">
                                    <input type="hidden" name="productPrice" value="1100">
                                    <input type="hidden" name="action" value="TOKENPAYMENT">
                                    <button type="submit" class="btn btn-primary btn-lg">
                                        <i class="fa fa-credit-card"></i> 기본 패키지 - 1개 (₩1,100)
                                    </button>
                                </form>

                                <!-- 인기 패키지 -->
                                <form action="tossPaymentPage.do" method="POST">
                                    <input type="hidden" name="productNumber" value="3">
                                    <input type="hidden" name="productName" value="인기 패키지">
                                    <input type="hidden" name="productPrice" value="5000">
                                    <input type="hidden" name="action" value="TOKENPAYMENT">
                                    <button type="submit" class="btn btn-primary btn-lg">
                                        <i class="fa fa-credit-card"></i> 인기 패키지 - 5개 (₩5,000)
                                    </button>
                                </form>
                                <!-- 프리미엄 패키지 -->
                                <form action="tossPaymentPage.do" method="POST">
                                    <input type="hidden" name="productNumber" value="4">
                                    <input type="hidden" name="productName" value="프리미엄 패키지">
                                    <input type="hidden" name="productPrice" value="8800">
                                    <input type="hidden" name="action" value="TOKENPAYMENT">
                                    <button type="submit" class="btn btn-primary btn-lg">
                                        <i class="fa fa-credit-card"></i> 프리미엄 패키지 - 10개 (₩8,800)
                                    </button>
                                </form>
                            </div>
                        </div>

                    </div>

                    <!-- PREMIUM 섹션 -->
                    <h3 class="section-title"><i class="fa fa-star"></i> 프리미엄 멤버십</h3>
                    <div class="payment-section premium-section">
                        <div class="premium-content">
                            <div class="premium-header">
                                <h4 class="premium-title">광고 없는 쾌적한 서비스 경험</h4>
                                <div class="premium-price">
                                    <span class="price-tag">₩9,900</span>
                                    <span class="price-period">평생 이용권</span>
                                </div>
                            </div>
                            <div class="premium-features">
                                <div class="premium-feature">
                                    <i class="fa fa-ban"></i>
                                    <div class="feature-text">
                                        <h5>광고 완전 제거</h5>
                                        <p>모든 페이지에서 광고를 제거하여 쾌적한 사용 환경 제공</p>
                                    </div>
                                </div>
                                <div class="premium-feature">
                                    <i class="fa fa-bolt"></i>
                                    <div class="feature-text">
                                        <h5>빠른 페이지 로딩</h5>
                                        <p>광고 로딩 없이 더 빠르고 쾌적한 서비스 이용 가능</p>
                                    </div>
                                </div>
                                <div class="premium-feature">
                                    <i class="fa fa-heart"></i>
                                    <div class="feature-text">
                                        <h5>프리미엄 뱃지</h5>
                                        <p>프로필에 특별한 프리미엄 뱃지로 더 많은 관심 유도</p>
                                    </div>
                                </div>
                            </div>
                            <div class="premium-action">
                                <!-- 프리미엄 멤버십 소유한 상태라면 버튼 비활성화 -->
                                <form action="tossPaymentPage.do" method="post">
                                    <input type="hidden" name="productNumber" value="1">
                                    <input type="hidden" name="productName" value="프리미엄 멤버쉽 결제">
                                    <input type="hidden" name="productPrice" value="9900">
                                    <input type="hidden" name="action" value="PREMIUM">

                                    <button type="submit" class="btn btn-primary btn-lg payment-button"
                                    ${sessionScope.userPremium ? 'disabled' : ''}>
                                        <i class="fa fa-crown"></i>
                                        ${sessionScope.userPremium ? '이미 프리미엄 회원입니다' : '프리미엄 멤버십 가입하기'}
                                    </button>
                                </form>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
            <!-- END CONTENT -->
        </div>
        <!-- END SIDEBAR & CONTENT -->
    </div>
</div>

<!-- BEGIN PRE-FOOTER -->
<div class="pre-footer">
    <div class="container">
        <div class="row">
            <!-- BEGIN BOTTOM ABOUT BLOCK -->
            <div class="col-md-3 col-sm-6 pre-footer-col">
                <h2>Next Level</h2>
                <p>
                    우리는 인연과 연인을 중시합니다.
                </p>
            </div>
            <!-- END BOTTOM ABOUT BLOCK -->
            <!-- BEGIN BOTTOM INFO BLOCK -->
            <div class="col-md-3 col-sm-6 pre-footer-col">
                <h2>Information</h2>
                <p>
                    진정한 인연을 찾아주는 플랫폼, 2025년부터 여러분의 특별한 만남을 응원합니다. 인연을 만드는 새로운 방식으로,
                    모든 만남이 소중한 인연으로 이어지길 바랍니다.
                </p>
            </div>
            <!-- END INFO BLOCK -->

            <!-- BEGIN TWITTER BLOCK -->
            <div class="col-md-3 col-sm-6 pre-footer-col">
                <h2 class="margin-bottom-0">Github</h2>
                <a class="twitter-timeline" href="https://github.com/KiNextLevel" data-tweet-limit="2" data-theme="dark"
                   data-link-color="#57C8EB" data-widget-id="455411516829736961"
                   data-chrome="noheader nofooter noscrollbar noborders transparent">https://github.com/KiNextLevel</a>
            </div>
            <!-- END TWITTER BLOCK -->

            <!-- BEGIN BOTTOM CONTACTS -->
            <div class="col-md-3 col-sm-6 pre-footer-col">
                <h2>Our Contacts</h2>
                <address class="margin-bottom-40">
                    서울 강남구 테헤란로26길 12<br>
                    (우) 06236 (지번) 역삼동 736-56<br>
                    Notion: <a
                        href="https://sheer-sundial-325.notion.site/1b5c9677015480c4a9ebfba7bbc63185">Notion</a><br>
                    Email: <a href="0414minyoung@naver.com">0414minyoung@naver.com</a>
                </address>
            </div>
            <!-- END BOTTOM CONTACTS -->
        </div>
        <hr>
        <div class="row">
            <!-- BEGIN COPYRIGHT -->
            <div class="col-md-12 col-sm-12 padding-top-10">
                2025 © Next Level. ALL Rights Reserved.
            </div>
            <!-- END COPYRIGHT -->
        </div>
        <!-- END BOTTOM CONTACTS -->
    </div>
    <hr>
    <div class="row">
        <!-- Load javascripts at bottom, this will reduce page load time -->
        <!-- BEGIN CORE PLUGINS(REQUIRED FOR ALL PAGES) -->
        <!--[if lt IE 9]>
            <script src="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/plugins/respond.min.js"></script>
            <![endif]-->
        <script src="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/plugins/jquery.min.js"
                type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/plugins/jquery-migrate.min.js"
                type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/plugins/bootstrap/js/bootstrap.min.js"
                type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/plugins/jquery-slimscroll/jquery.slimscroll.min.js"
                type="text/javascript"></script>
        <!-- END CORE PLUGINS -->

        <!-- BEGIN PAGE LEVEL JAVASCRIPTS (REQUIRED ONLY FOR CURRENT PAGE) -->
        <script src="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/plugins/fancybox/source/jquery.fancybox.pack.js"
                type="text/javascript"></script>
        <!-- pop up -->
        <script src="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/plugins/owl.carousel/owl.carousel.min.js"
                type="text/javascript"></script>
        <!-- slider for products -->

        <script src="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/corporate/scripts/layout.js"
                type="text/javascript"></script>
        <script type="text/javascript">
            jQuery(document).ready(function () {
                Layout.init();
                Layout.initOWL();
                Layout.initTwitter();
            });
        </script>
        <!-- END PAGE LEVEL JAVASCRIPTS -->
    </div>
</div>
</div>
</body>
<!-- END BODY -->

</html>
<%--<%@ page import="org.example.webapp.model.dto.UserDTO" %>--%>
<%--<%@ page import="java.util.ArrayList" %>--%>
<%--<%@ page import="org.example.webapp.model.dto.AlertDTO" %>--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<!--
Template: Metronic Frontend Freebie - Responsive HTML Template Based On Twitter Bootstrap 3.3.4
Version: 1.0.0
Author: KeenThemes
Website: http://www.keenthemes.com/
Contact: support@keenthemes.com
Follow: www.twitter.com/keenthemes
Like: www.facebook.com/keenthemes
Purchase Premium Metronic Admin Theme: http://themeforest.net/item/metronic-responsive-admin-dashboard-template/4021469?ref=keenthemes
-->
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->

<!-- Head BEGIN -->
<head>
    <link rel="icon" type="image/png" sizes="16x16"  href="/favicon-32x32.png">
    <meta charset="utf-8">
    <title>메인 페이지</title>
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
    <link href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700|PT+Sans+Narrow|Source+Sans+Pro:200,300,400,600,700,900&amp;subset=all"
          rel="stylesheet" type="text/css">
    <!-- Fonts END -->

    <!-- Global styles START -->
    <link href="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/plugins/font-awesome/css/font-awesome.min.css"
          rel="stylesheet">
    <link href="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/plugins/bootstrap/css/bootstrap.min.css"
          rel="stylesheet">
    <!-- Global styles END -->

    <!-- Page level plugin styles START -->
    <link href="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/plugins/fancybox/source/jquery.fancybox.css"
          rel="stylesheet">
    <link href="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/plugins/owl.carousel/assets/owl.carousel.css"
          rel="stylesheet">
    <link href="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/plugins/uniform/css/uniform.default.css"
          rel="stylesheet" type="text/css">
    <link href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" rel="stylesheet" type="text/css">
    <!-- for slider-range -->
    <link href="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/plugins/rateit/src/rateit.css"
          rel="stylesheet" type="text/css">
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
                alt="mainPage"/></a>

        <a href="javascript:void(0);" class="mobi-toggler"><i class="fa fa-bars"></i></a>

        <!-- BEGIN CART 읽지 않은 알림이 있으면 비동기로 "새 알림이 있습니다", 없으면 그냥 없음-->
        <div class="top-cart-block">
            <!-- 새 알림이 있습니다 칸 (기본 숨김) -->
            <div class="top-cart-info" style="display: none;">
                <p>새 알림이 있습니다</p>
            </div>

            <i class="fa fa-bell"></i>

            <div class="top-cart-content-wrapper">
                <div class="top-cart-content">
                    <ul class="scroller" style="height: 250px;">
                        <!-- 알림 목록은 JavaScript로 동적 생성 -->
                    </ul>
                </div>
            </div>
        </div>
        <!--END CART -->
    </div>
</div>
<!-- Header END -->
<div class="title-wrapper"
     style="margin: -23px 0 23px; position: relative; overflow: hidden; background: #72c2ff url('${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/corporate/img/logos/123.png') no-repeat 100% 100%; min-height: 280px; padding-top: 79px; width: 100%;">
    <div class="container">
        <div class="container-inner">
            <a href="boardPage.do">
                <h1><span>이벤트!</span></h1>
                <em>온/오프라인 이벤트 참여하기</em>
            </a>
        </div>
    </div>
</div>


<div class="main">
    <div class="container">
        <ul class="breadcrumb">
            <li class="active">Home</li>
        </ul>
        <!-- BEGIN SIDEBAR & CONTENT -->
        <div class="row margin-bottom-40">
            <!-- BEGIN SIDEBAR -->
            <div class="sidebar col-md-3 col-sm-5">
                <div class="sidebar-filter margin-bottom-25">
                    <h2>Filter</h2>
                    <!-- 성별 필터 -->
                    <h3>성별</h3>
                    <div class="checkbox-list" id="gender-filters">
                        <label><input type="checkbox" name="gender" value="여" > 남</label>
                        <label><input type="checkbox" name="gender" value="남" > 여</label>
                    </div>

                    <!-- 거리 필터 -->
                    <h3>거주지역</h3>
                    <div class="checkbox-list" id="distance-filters">
                        <label><input type="radio" name="distance" value="10"> 10km 이내</label>
                        <label><input type="radio" name="distance" value="50"> 50km</label>
                        <label><input type="radio" name="distance" value="100" checked="checked"> 전체선택</label>
                    </div>
                    <!-- 나이 슬라이더 -->
                    <h3>나이</h3>
                    <p>
                        <label for="ageAmount">나이</label>
                        <input type="text" id="ageAmount" style="border:0; color:#f6931f; font-weight:bold;">
                    </p>
                    <div id="age-slider-range" class="age"></div>

                    <!-- 키 슬라이더 -->
                    <h3>키</h3>
                    <p>
                        <label for="heightAmount">키</label>
                        <input type="text" id="heightAmount" style="border:0; color:#f6931f; font-weight:bold;">
                    </p>
                    <div id="height-slider-range" class="height"></div>

                    <!-- 종교 -->
                    <h3>종교</h3>
                    <div class="checkbox-list" id="religion-filters">
                        <label><input type="checkbox" name="religion" value="무교"> 무교</label>
                        <label><input type="checkbox" name="religion" value="기독교"> 기독교</label>
                        <label><input type="checkbox" name="religion" value="불교"> 불교</label>
                        <label><input type="checkbox" name="religion" value="천주교"> 천주교</label>
                        <label><input type="checkbox" name="religion" value="이슬람교"> 이슬람교</label>
                        <label><input type="checkbox" name="religion" value="힌두교"> 힌두교</label>
                        <label><input type="checkbox" name="religion" value="기타"> 기타</label>
                    </div>
                    <!-- 흡연 -->
                    <h3>흡연</h3>
                    <div class="checkbox-list" id="smoking-filters">
                        <label><input type="checkbox" name="smoking" value="흡연"> 흡연</label>
                        <label><input type="checkbox" name="smoking" value="비흡연"> 비흡연</label>
                    </div>
                </div>

                <c:if test="${not empty userEmail and !userPremium}">
                    <div class="sidebar-products clearfix">
                        <h2>구글 광고</h2>
                        <div class="item">
                            <a href="productPage.do"><img
                                    src="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/pages/img/products/k1.jpg"
                                    alt="Some Shoes in Animal with Cut Out"></a>
                            <h3><a href="productPage.do">Some Shoes in Animal with Cut Out</a></h3>
                            <div class="price">$31.00</div>
                        </div>
                        <div class="item">
                            <a href="productPage.do"><img
                                    src="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/pages/img/products/k4.jpg"
                                    alt="Some Shoes in Animal with Cut Out"></a>
                            <h3><a href="productPage.do">Some Shoes in Animal with Cut Out</a></h3>
                            <div class="price">$23.00</div>
                        </div>
                        <div class="item">
                            <a href="productPage.do"><img
                                    src="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/pages/img/products/k3.jpg"
                                    alt="Some Shoes in Animal with Cut Out"></a>
                            <h3><a href="productPage.do">Some Shoes in Animal with Cut Out</a></h3>
                            <div class="price">$86.00</div>
                        </div>
                    </div>
                </c:if>

            </div>
            <!-- END SIDEBAR -->

            <!-- BEGIN CONTENT -->
            <div class="col-md-9 col-sm-7">
                <div class="row list-view-sorting clearfix">
                    <div class="col-md-2 col-sm-2 list-view">
                        <a href="javascript:;"><i class="fa fa-th-large"></i></a>
                        <a href="javascript:;"><i class="fa fa-th-list"></i></a>
                    </div>
                    <!--
                    최프때 추후 사용 가능
                    <div class="col-md-10 col-sm-10">
                        <div class="pull-right">
                            <label class="control-label">Show:</label>
                            <select class="form-control input-sm">
                                <option value="#?limit=24" selected="selected">24</option>
                                <option value="#?limit=25">25</option>
                                <option value="#?limit=50">50</option>
                                <option value="#?limit=75">75</option>
                                <option value="#?limit=100">100</option>
                            </select>
                        </div>
                        <div class="pull-right">
                            <label class="control-label">Sort&nbsp;By:</label>
                            <select class="form-control input-sm">
                                <option value="#?sort=p.sort_order&amp;order=ASC" selected="selected">Default</option>
                                <option value="#?sort=pd.name&amp;order=ASC">Name (A - Z)</option>
                                <option value="#?sort=pd.name&amp;order=DESC">Name (Z - A)</option>
                                <option value="#?sort=p.price&amp;order=ASC">Price (Low &gt; High)</option>
                                <option value="#?sort=p.price&amp;order=DESC">Price (High &gt; Low)</option>
                                <option value="#?sort=rating&amp;order=DESC">Rating (Highest)</option>
                                <option value="#?sort=rating&amp;order=ASC">Rating (Lowest)</option>
                                <option value="#?sort=p.model&amp;order=ASC">Model (A - Z)</option>
                                <option value="#?sort=p.model&amp;order=DESC">Model (Z - A)</option>
                            </select>
                        </div>
                    </div>
                    -->
                </div>
                <!-- BEGIN PRODUCT LIST -->
                <div class="row product-list" id="product-list">
                    <!-- 여기에 초기 데이터가 렌더링 됩니다. -->
                </div>
                <!-- END PRODUCT LIST -->

                <!-- 더 보기 버튼 -->
                <div id="load-more-container">
                    <button id="load-more-btn" class="btn btn-primary">더 보기</button>
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
                    Notion: <a href="https://sheer-sundial-325.notion.site/1b5c9677015480c4a9ebfba7bbc63185">Notion</a><br>
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
    </div>
</div>
<!-- END FOOTER -->

<!-- Load javascripts at bottom, this will reduce page load time -->
<!-- BEGIN CORE PLUGINS(REQUIRED FOR ALL PAGES) -->
<!--[if lt IE 9]>
<script src="assets/plugins/respond.min.js"></script>
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
        type="text/javascript"></script><!-- pop up -->
<script src="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/plugins/owl.carousel/owl.carousel.min.js"
        type="text/javascript"></script><!-- slider for products -->
<script src='${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/plugins/zoom/jquery.zoom.min.js'
        type="text/javascript"></script><!-- product zoom -->
<script src="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/plugins/bootstrap-touchspin/bootstrap.touchspin.js"
        type="text/javascript"></script><!-- Quantity -->
<script src="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/plugins/uniform/jquery.uniform.min.js"
        type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/plugins/rateit/src/jquery.rateit.js"
        type="text/javascript"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js" type="text/javascript"></script><!-- for slider-range -->

<script src="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/corporate/scripts/layout.js"
        type="text/javascript"></script>
<script type="text/javascript">
    // 전역 변수 설정 (JSP에서 JavaScript로 데이터 전달)
    window.allUsers = ${userDatas};
    window.currentUserEmail = "${sessionScope.userEmail}";
    window.currentUserLatitude = "${sessionScope.userLatitude}";
    window.currentUserLongitude = "${sessionScope.userLongitude}";
    window.alertDatasJson = '${sessionScope.alertDatasJson}';

    console.log("Raw userDatas:", window.allUsers);
</script>
<script src="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/js/MainPage.js"></script>
<!-- END PAGE LEVEL JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>
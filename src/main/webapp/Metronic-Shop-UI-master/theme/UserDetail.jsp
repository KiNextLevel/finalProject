<%@ page import="org.example.webapp.model.dto.UserDTO" %>
<%@ page import="org.example.webapp.model.dto.PreferenceDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!-- 날짜 등 포맷용 -->
<%@ page isELIgnored="false" %>

<html>
<head>
    <link rel="icon" type="image/png" sizes="16x16" href="/favicon-32x32.png">
    <meta charset="utf-8">
    <title>사용자 상세 페이지${param.userEmail}</title>
    <link href="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/css/UserDetail.css" rel="stylesheet">

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
    <!-- Font Awesome 5 추가 -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">

    <style>
        /* Information 탭 스타일 */
        .user-info-container {
            background-color: #f9f9f9;
            border-radius: 8px;
            padding: 20px;
            margin-top: 15px;
        }

        .user-info-row {
            margin-bottom: 15px;
        }

        .info-item {
            padding: 12px 15px;
            margin-bottom: 10px;
            background-color: white;
            border-radius: 6px;
            box-shadow: 0 1px 3px rgba(0, 0, 0, 0.1);
            transition: all 0.3s ease;
        }

        .info-item:hover {
            transform: translateY(-2px);
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }

        .info-item i {
            margin-right: 10px;
            color: #e84d1c;
            font-size: 18px;
            width: 20px;
            text-align: center;
        }

        .info-label {
            font-weight: 600;
            color: #555;
            margin-right: 8px;
        }

        .info-value {
            color: #333;
        }

        /* Favorite 탭 스타일 */
        .user-preference-container {
            background-color: #f9f9f9;
            border-radius: 8px;
            padding: 20px;
            margin-top: 15px;
        }

        .preference-header {
            margin-bottom: 20px;
            text-align: center;
        }

        .preference-header h3 {
            color: #e84d1c;
            font-size: 22px;
            margin: 0;
        }

        .preference-row {
            display: flex;
            flex-wrap: wrap;
            justify-content: space-between;
        }

        .preference-item {
            background-color: white;
            border-radius: 8px;
            padding: 20px;
            margin-bottom: 15px;
            display: flex;
            align-items: center;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            transition: all 0.3s ease;
            height: 100%;
        }

        .preference-item:hover {
            transform: translateY(-3px);
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
        }

        .preference-icon {
            background-color: #f5f5f5;
            width: 60px;
            height: 60px;
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
            margin-right: 15px;
        }

        .preference-icon i {
            font-size: 24px;
            color: #e84d1c;
        }

        .preference-content h4 {
            margin-top: 0;
            margin-bottom: 5px;
            color: #333;
            font-size: 16px;
        }

        .preference-content p {
            margin: 0;
            font-size: 18px;
            color: #e84d1c;
            font-weight: 600;
        }

        /* 반응형 스타일 */
        @media (max-width: 767px) {
            .preference-row .col-md-4 {
                width: 100%;
            }

            .preference-item {
                margin-bottom: 15px;
            }
        }

    </style>

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
        <!-- BEGIN CONTENT -->
        <div class="col-md-9 col-sm-7">
            <div class="product-page">
                <div class="row">
                    <div class="col-md-6 col-sm-6">
                        <div class="product-main-image">
                            <img src="${userDTO.userProfile}" alt="User Profile"
                                 class="img-responsive" data-BigImgsrc="${userDTO.userProfile}">
                        </div>
                    </div>
                    <div class="col-md-6 col-sm-6">
                        <h1>${userDTO.userNickname}의 프로필</h1>
                        <div class="price-availability-block clearfix">
                            <div class="price">
                                <strong>이름 : ${userDTO.userName}</strong><br>
                                <p>닉네임 : ${userDTO.userNickname}</p>
                            </div>
                            <div class="availability">
                                <!-- 앞에 2글자만 자르기 -->
                                지역 : <strong>${fn:split(userDTO.userRegion, ' ')[0]}</strong>
                            </div>

                        </div>
                        <div class="description">
                            <p>${userDTO.userDescription}</p>
                        </div>
                        <div class="product-page-cart">
                            <div class="row">
                                <div class="col-md-6">
                                    <!-- 채팅 구현은 NOT YET -->
                                    <button class="btn btn-primary btn-block" type="submit">1:1 채팅하기</button>
                                </div>
                                <div class="col-md-6">
                                    <a href="reportPage.do?userEmail=${userDTO.userEmail}"
                                       class="btn btn-danger btn-block">
                                        ${userDTO.userNickname} 신고하기
                                    </a>
                                </div>
                            </div>
                        </div>

                        <!-- 지도 div -->
                        <div id="map" style="width: 100%; height: 400px;"></div>

                        <!-- 스크립트 파일 불러오기 -->
                        <script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=16e5b4c908303629d0e034ffce98abc8&libraries=services"></script>
                        <script src="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/js/MapView.js"></script>

                        <script>
                            initUserMap(${userDTO.userLatitude}, ${userDTO.userLongitude});
                            console.log("위도:", ${userDTO.userLatitude});
                            console.log("경도:", ${userDTO.userLongitude});
                        </script>
                    </div>

                    <div class="product-page-content">
                        <ul id="myTab" class="nav nav-tabs">
                            <li><a href="#Information" data-toggle="tab">Information</a></li>
                            <li class="active"><a href="#favorite" data-toggle="tab">Favorite</a></li>
                        </ul>
                        <div id="myTabContent" class="tab-content">
                            <div class="tab-pane fade" id="Information">
                                <div class="user-info-container">
                                    <c:if test="${not empty userDTO}">
                                        <div class="row user-info-row">
                                            <div class="col-md-6">
                                                <div class="info-item">
                                                    <i class="fas fa-birthday-cake"></i>
                                                    <span class="info-label">생년월일:</span>
                                                    <span class="info-value">${userDTO.userBirth}</span>
                                                </div>
                                                <div class="info-item">
                                                    <i class="fas fa-arrows-alt-v"></i>
                                                    <span class="info-label">키:</span>
                                                    <span class="info-value">${userDTO.userHeight}</span>
                                                </div>
                                                <div class="info-item">
                                                    <i class="fas fa-user"></i>
                                                    <span class="info-label">체형:</span>
                                                    <span class="info-value">${userDTO.userBody}</span>
                                                </div>
                                                <div class="info-item">
                                                    <i class="fas fa-brain"></i>
                                                    <span class="info-label">MBTI:</span>
                                                    <span class="info-value">${userDTO.userMbti}</span>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="info-item">
                                                    <i class="fas fa-graduation-cap"></i>
                                                    <span class="info-label">학력:</span>
                                                    <span class="info-value">${userDTO.userEducation}</span>
                                                </div>
                                                <div class="info-item">
                                                    <i class="fas fa-heart"></i>
                                                    <span class="info-label">종교:</span>
                                                    <span class="info-value">${userDTO.userReligion}</span>
                                                </div>
                                                <div class="info-item">
                                                    <i class="fas fa-glass-cheers"></i>
                                                    <span class="info-label">음주:</span>
                                                    <span class="info-value">
                            <c:choose>
                                <c:when test="${userDTO.userDrink == 0}">전혀 안함</c:when>
                                <c:when test="${userDTO.userDrink == 1}">가끔</c:when>
                                <c:when test="${userDTO.userDrink == 2}">자주</c:when>
                                <c:otherwise>입력 안됨</c:otherwise>
                            </c:choose>
                        </span>
                                                </div>
                                                <div class="info-item">
                                                    <i class="fas fa-smoking"></i>
                                                    <span class="info-label">흡연:</span>
                                                    <span class="info-value">
                            <c:choose>
                                <c:when test="${userDTO.userSmoke}">흡연</c:when>
                                <c:otherwise>비흡연</c:otherwise>
                            </c:choose>
                        </span>
                                                </div>
                                                <div class="info-item">
                                                    <i class="fas fa-briefcase"></i>
                                                    <span class="info-label">직업:</span>
                                                    <span class="info-value">${userDTO.userJob}</span>
                                                </div>
                                            </div>
                                        </div>
                                    </c:if>

                                    <c:if test="${empty userDTO}">
                                        <div class="alert alert-info">사용자 정보를 찾을 수 없습니다.</div>
                                    </c:if>
                                </div>
                            </div>
                            <div class="tab-pane fade in active" id="favorite">
                                <div class="user-preference-container">
                                    <c:choose>
                                        <c:when test="${not empty preferenceDTO}">
                                            <div class="preference-header">
                                                <h3>선호하는 조건</h3>
                                            </div>
                                            <div class="row preference-row">
                                                <div class="col-md-4">
                                                    <div class="preference-item">
                                                        <div class="preference-icon">
                                                            <i class="fas fa-arrows-alt-v"></i>
                                                        </div>
                                                        <div class="preference-content">
                                                            <h4>선호 키</h4>
                                                            <p>${preferenceDTO.preferenceHeight}</p>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-md-4">
                                                    <div class="preference-item">
                                                        <div class="preference-icon">
                                                            <i class="fas fa-user-alt"></i>
                                                        </div>
                                                        <div class="preference-content">
                                                            <h4>선호 체형</h4>
                                                            <p>${preferenceDTO.preferenceBody}</p>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-md-4">
                                                    <div class="preference-item">
                                                        <div class="preference-icon">
                                                            <i class="fas fa-calendar-check"></i>
                                                        </div>
                                                        <div class="preference-content">
                                                            <h4>선호 나이</h4>
                                                            <p>${preferenceDTO.preferenceAge}</p>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:when>
                                        <c:otherwise>
                                            <div class="alert alert-info">사용자의 선호 정보를 찾을 수 없습니다.</div>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>

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

<!-- BEGIN fast view of a product -->
<div id="product-pop-up" style="display: none; width: 700px;">
    <div class="product-page product-pop-up">
        <div class="row">
            <div class="col-md-6 col-sm-6 col-xs-3">
                <div class="product-main-image">
                    <img src="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/pages/img/products/model7.jpg"
                         alt="Cool green dress with red bell"
                         class="img-responsive">
                </div>
            </div>
            <div class="col-md-6 col-sm-6 col-xs-9">
                <h2>Cool green dress with red bell</h2>
                <div class="description">
                    <p>Lorem ipsum dolor ut sit ame dolore adipiscing elit, sed nonumy nibh sed euismod laoreet dolore
                        magna
                        aliquarm erat volutpat
                        Nostrud duis molestie at dolore.</p>
                </div>
            </div>
            <div class="product-page-cart">
                <div class="product-quantity">
                    <input id="product-quantity2" type="text" value="1" readonly class="form-control input-sm">
                </div>
                <button class="btn btn-primary" type="submit">Add to cart</button>
                <a href="shop-item.html" class="btn btn-default">More details</a>
            </div>
        </div>

        <div class="sticker sticker-sale"></div>
    </div>
</div>
</div>
<!-- END fast view of a product -->

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
<script src="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/corporate/scripts/back-to-top.js"
        type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/plugins/jquery-slimscroll/jquery.slimscroll.min.js"
        type="text/javascript"></script>
<!-- END CORE PLUGINS -->

<!-- BEGIN PAGE LEVEL JAVASCRIPTS (REQUIRED ONLY FOR CURRENT PAGE) -->
<script src="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/plugins/fancybox/source/jquery.fancybox.pack.js"
        type="text/javascript"></script><!-- pop up -->
<script src="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/plugins/owl.carousel/owl.carousel.min.js"
        type="text/javascript"></script>
<!-- slider for products -->
<script src='${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/plugins/zoom/jquery.zoom.min.js'
        type="text/javascript"></script><!-- product zoom -->
<script src="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/plugins/bootstrap-touchspin/bootstrap.touchspin.js"
        type="text/javascript"></script>
<!-- Quantity -->
<script src="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/plugins/uniform/jquery.uniform.min.js"
        type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/plugins/rateit/src/jquery.rateit.js"
        type="text/javascript"></script>

<script src="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/corporate/scripts/layout.js"
        type="text/javascript"></script>
<!-- END PAGE LEVEL JAVASCRIPTS -->


</body>
<!-- END BODY -->

</html>
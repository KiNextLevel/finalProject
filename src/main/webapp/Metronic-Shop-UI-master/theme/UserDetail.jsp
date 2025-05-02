<%@ page import="com.example.common.biz.user.UserVO" %>
<%@ page import="com.example.common.biz.preference.PreferenceVO" %>
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
    <title>사용자 상세 페이지</title>
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

        /* 로딩 스타일 */
        .loading-container {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 300px;
        }

        .spinner {
            border: 5px solid #f3f3f3;
            border-top: 5px solid #e84d1c;
            border-radius: 50%;
            width: 50px;
            height: 50px;
            animation: spin 1s linear infinite;
        }

        @keyframes spin {
            0% { transform: rotate(0deg); }
            100% { transform: rotate(360deg); }
        }

        /* 에러 메시지 스타일 */
        .error-message {
            text-align: center;
            padding: 20px;
            background-color: #f8d7da;
            color: #721c24;
            border-radius: 5px;
            margin-top: 20px;
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
                        <a href="/productPage.do" class="current"> 플러스샵 </a>
                    </li>
                    <!-- END LANGS -->
                </ul>
            </div>
            <!-- END TOP BAR LEFT PART -->
            <!-- BEGIN TOP BAR MENU -->
            <div class="col-md-6 col-sm-6 additional-nav">
                <ul class="list-unstyled list-inline pull-right">
                    <c:if test="${userRole==1}">
                        <li><a href="/adminPage.do">관리자페이지</a></li>
                    </c:if>
                    <li><a href="/myPage.do">마이페이지</a></li>
                    <li>메시지</li>
                    <li><a href="/logout.do">로그아웃</a></li>
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
        <a class="site-logo" href="/mainPage.do"><img
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
                    <!-- 초기 로딩 상태 표시 -->
                    <div id="loading-container" class="loading-container">
                        <div class="spinner"></div>
                    </div>

                    <!-- 에러 메시지 표시 영역 -->
                    <div id="error-container" style="display: none;">
                        <div class="error-message">
                            <h3><i class="fas fa-exclamation-triangle"></i> 오류 발생</h3>
                            <p id="error-message-text">사용자 정보를 불러오는 중 오류가 발생했습니다.</p>
                        </div>
                    </div>

                    <!-- 사용자 정보 표시 영역 - 초기에는 숨김 -->
                    <div id="user-profile-container" style="display: none;">
                        <div class="col-md-6 col-sm-6">
                            <div class="product-main-image">
                                <img id="user-profile-image" src="" alt="User Profile"
                                     class="img-responsive">
                            </div>
                        </div>
                        <div class="col-md-6 col-sm-6">
                            <h1 id="user-nickname-title"></h1>
                            <div class="price-availability-block clearfix">
                                <div class="price">
                                    <strong>이름: <span id="user-name"></span></strong><br>
                                    <p>닉네임: <span id="user-nickname"></span></p>
                                </div>
                                <div class="availability">
                                    지역: <strong id="user-region"></strong>
                                </div>
                            </div>
                            <div class="description">
                                <p id="user-description"></p>
                            </div>
                            <div class="product-page-cart">
                                <div class="row">
                                    <div class="col-md-6">
                                        <button class="btn btn-primary btn-block" type="button">1:1 채팅하기</button>
                                    </div>
                                    <div class="col-md-6">
                                        <a id="report-link" href="" class="btn btn-danger btn-block">
                                            <span id="report-nickname"></span> 신고하기
                                        </a>
                                    </div>
                                </div>
                            </div>

                            <!-- 지도 div -->
                            <div id="map" style="width: 100%; height: 400px; margin-top: 20px;"></div>
                        </div>

                        <div class="product-page-content">
                            <ul id="myTab" class="nav nav-tabs">
                                <li><a href="#Information" data-toggle="tab">Information</a></li>
                                <li class="active"><a href="#favorite" data-toggle="tab">Favorite</a></li>
                            </ul>
                            <div id="myTabContent" class="tab-content">
                                <div class="tab-pane fade" id="Information">
                                    <div class="user-info-container">
                                        <div class="row user-info-row">
                                            <div class="col-md-6">
                                                <div class="info-item">
                                                    <i class="fas fa-birthday-cake"></i>
                                                    <span class="info-label">생년월일:</span>
                                                    <span id="user-birth" class="info-value"></span>
                                                </div>
                                                <div class="info-item">
                                                    <i class="fas fa-arrows-alt-v"></i>
                                                    <span class="info-label">키:</span>
                                                    <span id="user-height" class="info-value"></span>
                                                </div>
                                                <div class="info-item">
                                                    <i class="fas fa-user"></i>
                                                    <span class="info-label">체형:</span>
                                                    <span id="user-body" class="info-value"></span>
                                                </div>
                                                <div class="info-item">
                                                    <i class="fas fa-brain"></i>
                                                    <span class="info-label">MBTI:</span>
                                                    <span id="user-mbti" class="info-value"></span>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="info-item">
                                                    <i class="fas fa-graduation-cap"></i>
                                                    <span class="info-label">학력:</span>
                                                    <span id="user-education" class="info-value"></span>
                                                </div>
                                                <div class="info-item">
                                                    <i class="fas fa-heart"></i>
                                                    <span class="info-label">종교:</span>
                                                    <span id="user-religion" class="info-value"></span>
                                                </div>
                                                <div class="info-item">
                                                    <i class="fas fa-glass-cheers"></i>
                                                    <span class="info-label">음주:</span>
                                                    <span id="user-drink" class="info-value"></span>
                                                </div>
                                                <div class="info-item">
                                                    <i class="fas fa-smoking"></i>
                                                    <span class="info-label">흡연:</span>
                                                    <span id="user-smoke" class="info-value"></span>
                                                </div>
                                                <div class="info-item">
                                                    <i class="fas fa-briefcase"></i>
                                                    <span class="info-label">직업:</span>
                                                    <span id="user-job" class="info-value"></span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="tab-pane fade in active" id="favorite">
                                    <div class="user-preference-container">
                                        <div id="preference-content">
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
                                                            <p id="preference-height"></p>
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
                                                            <p id="preference-body"></p>
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
                                                            <p id="preference-age"></p>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div id="no-preference-message" style="display: none;">
                                            <div class="alert alert-info">사용자의 선호 정보를 찾을 수 없습니다.</div>
                                        </div>
                                    </div>
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
    </div>
</div>

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

<!-- 카카오맵 API -->
<script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=16e5b4c908303629d0e034ffce98abc8&libraries=services"></script>
<script src="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/js/MapView.js"></script>

<script type="text/javascript">
    jQuery(document).ready(function () {
        Layout.init();
        Layout.initOWL();
        Layout.initTwitter();

        // 로딩 타임아웃 설정
        var loadingTimeout = setTimeout(function() {
            $('#loading-container').hide();
            showError("데이터 로딩 시간이 초과되었습니다. 페이지를 새로고침 해주세요.");
        }, 15000); // 15초 후 타임아웃

        // URL에서 userEmail 파라미터 가져오기
        var userEmail = getParameterByName('userEmail');

        if (!userEmail) {
            clearTimeout(loadingTimeout);
            $('#loading-container').hide();
            showError("사용자 이메일 정보가 없습니다.");
            return;
        }

        // 사용자 정보 가져오기
        $.ajax({
            url: '/userDetailData.do',
            type: 'GET',
            data: { userEmail: userEmail },
            dataType: 'json',
            timeout: 10000, // 10초 타임아웃 설정
            success: function(data) {
                clearTimeout(loadingTimeout);
                $('#loading-container').hide();

                console.log("서버 응답 데이터:", data); // 디버깅용

                if (!data || data.flag === false) {
                    showError(data && data.msg ? data.msg : "사용자 정보를 찾을 수 없습니다.");
                    return;
                }

                // 사용자 정보 표시
                renderUserData(data);
                $('#user-profile-container').show();
            },
            error: function(xhr, status, error) {
                clearTimeout(loadingTimeout);
                $('#loading-container').hide();
                console.error('사용자 정보 로딩 실패:', status, error);

                if (status === 'timeout') {
                    showError("서버 응답 시간이 초과되었습니다. 네트워크 연결을 확인해주세요.");
                } else if (status === 'parsererror') {
                    showError("서버 응답을 처리할 수 없습니다. 관리자에게 문의해주세요.");
                } else {
                    showError("서버 오류가 발생했습니다. 잠시 후 다시 시도해주세요.");
                }
            }
        });

        // URL 파라미터 추출 함수
        function getParameterByName(name) {
            var url = window.location.href;
            name = name.replace(/[\[\]]/g, '\\$&');
            var regex = new RegExp('[?&]' + name + '(=([^&#]*)|&|#|$)'),
                results = regex.exec(url);
            if (!results) return null;
            if (!results[2]) return '';
            return decodeURIComponent(results[2].replace(/\+/g, ' '));
        }

        // 에러 메시지 표시 함수
        function showError(message) {
            $('#error-message-text').text(message);
            $('#error-container').show();
        }

        // 사용자 데이터 렌더링 함수
        function renderUserData(data) {
            try {
                var userVO = data.userVO;
                var preferenceVO = data.preferenceVO;

                if (!userVO) {
                    showError("사용자 정보를 찾을 수 없습니다.");
                    return;
                }

                // 사용자 프로필 정보 설정
                $('#user-profile-image').attr('src', userVO.userProfile || '/default-profile.jpg');
                $('#user-nickname-title').text((userVO.userNickname || '사용자') + '의 프로필');
                $('#user-name').text(userVO.userName || '정보 없음');
                $('#user-nickname').text(userVO.userNickname || '정보 없음');

                // 지역 정보 설정
                if (userVO.userRegion) {
                    var regionParts = userVO.userRegion.split(' ');
                    $('#user-region').text(regionParts[0] || '정보 없음');
                } else {
                    $('#user-region').text('정보 없음');
                }

                $('#user-description').text(userVO.userDescription || '자기소개가 없습니다.');

                // 신고 링크 설정
                $('#report-link').attr('href', '/reportPage.do?userEmail=' + userVO.userEmail);
                $('#report-nickname').text(userVO.userNickname || '사용자');

                // 사용자 상세 정보 설정
                $('#user-birth').text(userVO.userBirth || '정보 없음');
                $('#user-height').text(userVO.userHeight || '정보 없음');
                $('#user-body').text(userVO.userBody || '정보 없음');
                $('#user-mbti').text(userVO.userMbti || '정보 없음');
                $('#user-education').text(userVO.userEducation || '정보 없음');
                $('#user-religion').text(userVO.userReligion || '정보 없음');
                $('#user-job').text(userVO.userJob || '정보 없음');

                // 음주 정보
                var drinkText = '정보 없음';
                if (userVO.userDrink === 0) drinkText = '전혀 안함';
                else if (userVO.userDrink === 1) drinkText = '가끔';
                else if (userVO.userDrink === 2) drinkText = '자주';
                $('#user-drink').text(drinkText);

                // 흡연 정보
                var smokeText = userVO.userSmoke === 1 ? '흡연' : '비흡연';
                $('#user-smoke').text(smokeText);

                // 선호 정보 설정
                if (preferenceVO) {
                    $('#preference-height').text(preferenceVO.preferenceHeight || '정보 없음');
                    $('#preference-body').text(preferenceVO.preferenceBody || '정보 없음');
                    $('#preference-age').text(preferenceVO.preferenceAge || '정보 없음');
                    $('#preference-content').show();
                    $('#no-preference-message').hide();
                } else {
                    $('#preference-content').hide();
                    $('#no-preference-message').show();
                }

                // 지도 초기화 (좌표가 있는 경우)
                if (userVO.userLatitude && userVO.userLongitude) {
                    try {
                        initUserMap(userVO.userLatitude, userVO.userLongitude);
                        console.log("지도 초기화 - 위도:", userVO.userLatitude, "경도:", userVO.userLongitude);
                    } catch (e) {
                        console.error("지도 초기화 오류:", e);
                    }
                }
            } catch (e) {
                console.error("데이터 렌더링 오류:", e);
                showError("데이터 표시 중 오류가 발생했습니다.");
            }
        }
    });
</script>
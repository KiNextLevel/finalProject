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
                    <li><a href="/myChatRoomList.do">메시지</li>
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
                                        <!-- 1:1 채팅하기 버튼  id추가 -->
                                        <button id="chatButton" class="btn btn-primary btn-block" type="button">1:1
                                            채팅하기
                                        </button>
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
    // 채팅 대상 이메일 전역 변수 선언
    let targetEmail = "";

    // URL에서 파라미터 값을 추출하는 함수 정의
    function getParameterByName(name) {
        const url = window.location.href;   // 현재 URL 가져오기
        name = name.replace(/[\[\]]/g, "\\$&"); // 이름에 특수문자 있으면 escape 처리
        const regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"); // 파라미터 찾는 정규식
        const results = regex.exec(url);  // 정규식 실행
        if (!results) return null;
        if (!results[2]) return "";
        return decodeURIComponent(results[2].replace(/\+/g, " "));  // 디코딩해서 반환
    }

    jQuery(document).ready(function () {
        Layout.init();
        Layout.initOWL();
        Layout.initTwitter();

        // 로딩 타임아웃 설정
        const loadingTimeout = setTimeout(function () {
            $('#loading-container').hide();
            showError("데이터 로딩 시간이 초과되었습니다. 페이지를 새로고침 해주세요.");
        }, 15000);

        // URL에서 userEmail 파라미터 가져오기
        const userEmail = getParameterByName('userEmail');

        // userEmail이 없으면 에러 처리
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
            data: {userEmail: userEmail},
            dataType: 'json',
            timeout: 10000,
            success: function (data) {
                clearTimeout(loadingTimeout);
                $('#loading-container').hide();

                if (!data || data.flag === false) {
                    showError(data && data.msg ? data.msg : "사용자 정보를 찾을 수 없습니다.");
                    return;
                }

                renderUserData(data);
                $('#user-profile-container').show();
            },
            error: function (xhr, status, error) {
                clearTimeout(loadingTimeout);
                $('#loading-container').hide();
                showError("서버 오류가 발생했습니다. 잠시 후 다시 시도해주세요.");
            }
        });

        // 에러 메시지 표시 함수
        function showError(message) {
            $('#error-message-text').text(message);
            $('#error-container').show();
        }

        // 사용자 데이터 렌더링 함수
        function renderUserData(data) {
            try {
                const userVO = data.userVO;
                const preferenceVO = data.preferenceVO;

                if (!userVO) {
                    showError("사용자 정보를 찾을 수 없습니다.");
                    return;
                }

                // 채팅 대상 이메일 저장
                targetEmail = userVO.userEmail;

                $('#user-profile-image').attr('src', userVO.userProfile || '/default-profile.jpg');
                $('#user-nickname-title').text((userVO.userNickname || '사용자') + '의 프로필');
                $('#user-name').text(userVO.userName || '정보 없음');
                $('#user-nickname').text(userVO.userNickname || '정보 없음');

                const regionParts = (userVO.userRegion || '').split(' ');
                $('#user-region').text(regionParts[0] || '정보 없음');

                $('#user-description').text(userVO.userDescription || '자기소개가 없습니다.');
                $('#report-link').attr('href', '/reportPage.do?userEmail=' + userVO.userEmail);
                $('#report-nickname').text(userVO.userNickname || '사용자');

                $('#user-birth').text(userVO.userBirth || '정보 없음');
                $('#user-height').text(userVO.userHeight || '정보 없음');
                $('#user-body').text(userVO.userBody || '정보 없음');
                $('#user-mbti').text(userVO.userMbti || '정보 없음');
                $('#user-education').text(userVO.userEducation || '정보 없음');
                $('#user-religion').text(userVO.userReligion || '정보 없음');
                $('#user-job').text(userVO.userJob || '정보 없음');

                const drinkText = userVO.userDrink === 0 ? '전혀 안함' : userVO.userDrink === 1 ? '가끔' : userVO.userDrink === 2 ? '자주' : '정보 없음';
                $('#user-drink').text(drinkText);

                const smokeText = userVO.userSmoke === 1 ? '흡연' : '비흡연';
                $('#user-smoke').text(smokeText);

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

                if (userVO.userLatitude && userVO.userLongitude) {
                    initUserMap(userVO.userLatitude, userVO.userLongitude);
                }
            } catch (e) {
                showError("데이터 표시 중 오류가 발생했습니다.");
            }
        }

        // 채팅 버튼 클릭 이벤트
        $('#chatButton').on('click', function () {
            if (confirm("대화를 시작하시겠습니까? ('확인'을 누르면 토큰이 1개 차감됩니다)")) {
                // 단순히 이동만 시키면 됨 (토큰 체크와 차감은 서버에서 함)
                window.location.href = '/deductToken.do?targetEmail=' + encodeURIComponent(targetEmail);
            }
        });

        // $('#chatButton').on('click', function () {
        //     $.ajax({
        //         url: '/checkToken.do',
        //         type: 'POST',
        //         dataType: 'json',
        //         success: function (response) {
        //             if (response.status === 'success') {
        //                 // 확인 창 띄우고, '확인' 누르면 채팅방으로 이동 (토큰 차감)
        //                 if (confirm("대화를 시작하시겠습니까? ('확인'을 누르면 토큰이 1개 차감됩니다)")) {
        //                     // 채팅방 이동 시 targetEmail을 파라미터로 포함
        //                     window.location.href = '/deductToken.do?targetEmail=' + encodeURIComponent(targetEmail);
        //                 }
        //                 // 토큰 보유 0개이면
        //             } else {
        //                 alert(response.message);  // 실패 시 메시지 출력
        //                 window.location.href = "/insufficientToken.do";  // 토큰 수 부족하면 이동하게 하기
        //             }
        //         },
        //         error: function () {
        //             alert("서버 오류가 발생했습니다. 다시 시도해주세요.");
        //         }
        //     });
        // });
    });

</script>
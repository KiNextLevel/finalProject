<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    <title>이벤트 페이지</title>
    <style>
        .participant-badges {
            color: #6ecb1a; /* 글씨 색상 */
            font-size: 12px;
            font-weight: bold;
            padding: 3px 8px;
            border-radius: 5px;
            white-space: nowrap; /* 줄바꿈 방지 */
        }
    </style>

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
    <!--
    <link href="assets/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <link href="assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
     -->
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
    <link href="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/css/BoardPage.css" rel="stylesheet">
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
                alt="mainPage"></a>

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

<div class="main event-page">
    <div class="container">
        <ul class="breadcrumb">
            <li><a href="mainPage.do">메인페이지</a></li>
            <li class="active">이벤트 페이지</li>
        </ul>

        <!-- 페이지 제목 섹션 -->
        <div class="page-title-container">
            <h1><i class="fa fa-calendar"></i> 이벤트</h1>
            <p>다양한 이벤트에 참여하고 특별한 혜택을 누려보세요!</p>
        </div>

        <!-- 이벤트 목록 시작 -->
        <c:choose>
            <c:when test="${empty datas}">
                <div class="no-events">
                    <i class="fa fa-calendar-o"></i>
                    <h2>현재 진행 중인 이벤트가 없습니다.</h2>
                    <p>새로운 이벤트가 등록되면 이곳에서 확인하실 수 있습니다.</p>
                </div>
                <!-- 관리자인 경우 이벤트 추가 버튼 표시 -->
                <c:if test="${sessionScope.userRole=='1'}">
                    <div class="admin-add-btn text-right">
                        <a href="adminAddBoardPage.do" class="btn-add-event">
                            <i class="fa fa-plus-circle"></i> 새 이벤트 추가
                        </a>
                    </div>
                </c:if>
            </c:when>
            <c:otherwise>
                <!-- 관리자인 경우 이벤트 추가 버튼 표시 -->
                <c:if test="${sessionScope.userRole=='1'}">
                    <div class="admin-add-btn text-right">
                        <a href="adminAddBoardPage.do" class="btn-add-event">
                            <i class="fa fa-plus-circle"></i> 새 이벤트 추가
                        </a>
                    </div>
                </c:if>

                <!-- 이벤트 목록 -->
                <div class="event-list">
                    <c:forEach var="data" items="${datas}">
                        <div class="event-card">
                            <div class="event-header" onclick="toggleEvent(${data.boardNumber})">
                                <h4><span class="event-number">#${data.boardNumber}</span> ${data.boardTitle}
                                    <c:if test="${data.participant == 1}">
                                        <span class="participant-badges">참가중</span>
                                    </c:if>
                                </h4>
                            </div>
                            <div id="event-content-${data.boardNumber}" class="event-content">
                                <div class="event-body">
                                        ${data.boardContent}
                                </div>
                                <div class="event-footer">
                                    <div class="participant-info">
                                            <span class="participant-count">
                                                <i class="fa fa-users"></i> 참가자: ${data.boardParticipant} / ${data.boardLimit}
                                            </span>
                                        <div class="progress">
                                            <div class="progress-bar progress-bar-success" role="progressbar"
                                                 aria-valuenow="${(data.boardParticipant / data.boardLimit) * 100}"
                                                 aria-valuemin="0"
                                                 aria-valuemax="100"
                                                 style="width: ${(data.boardParticipant / data.boardLimit) * 100}%">
                                            </div>
                                        </div>
                                    </div>

                                    <c:choose>
                                        <c:when test="${sessionScope.userRole=='1'}">
                                            <!-- 관리자용 버튼 -->
                                            <div class="admin-actions">
                                                <a href="adminModifyBoardPage.do?boardNum=${data.boardNumber}"
                                                   class="btn btn-edit">
                                                    <i class="fa fa-pencil"></i> 수정
                                                </a>
                                                <form action="adminDeleteBoard.do" method="POST"
                                                      style="display: inline;">
                                                    <input type="hidden" name="boardNum" value="${data.boardNumber}">
                                                    <button type="submit" class="btn btn-delete"
                                                            onclick="return confirm('정말로 이 이벤트를 삭제하시겠습니까?');">
                                                        <i class="fa fa-trash-o"></i> 삭제
                                                    </button>
                                                </form>
                                            </div>
                                        </c:when>
                                        <c:otherwise>
                                            <c:choose>
                                                <c:when test="${data.participant == 1}">
                                                    <!-- 일반 사용자용 참가 버튼 -->
                                                    <form action="participantBoard.do" method="POST">
                                                        <input type="hidden" name="boardNumber"
                                                               value="${data.boardNumber}">
                                                        <button type="submit" class="btn-participate"
                                                            ${data.boardParticipant >= data.boardLimit ? 'disabled' : ''}>
                                                            <i class="fa fa-check-circle"></i>
                                                                ${data.boardParticipant >= data.boardLimit ? '마감되었습니다' : '참가 취소하기'}
                                                        </button>
                                                    </form>
                                                </c:when>
                                                <c:otherwise>
                                                    <!-- 일반 사용자용 참가 버튼 -->
                                                    <form action="participantBoard.do" method="POST">
                                                        <input type="hidden" name="boardNumber"
                                                               value="${data.boardNumber}">
                                                        <button type="submit" class="btn-participate"
                                                            ${data.boardParticipant >= data.boardLimit ? 'disabled' : ''}>
                                                            <i class="fa fa-check-circle"></i>
                                                                ${data.boardParticipant >= data.boardLimit ? '마감되었습니다' : '참가하기'}
                                                        </button>
                                                    </form>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </c:otherwise>
        </c:choose>
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

<!-- 자바스크립트 -->
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
<script src="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/plugins/fancybox/source/jquery.fancybox.pack.js"
        type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/plugins/owl.carousel/owl.carousel.min.js"
        type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/corporate/scripts/layout.js"
        type="text/javascript"></script>

<script type="text/javascript">
    jQuery(document).ready(function () {
        Layout.init();
        Layout.initOWL();
        Layout.initTwitter();
    });

    // 이벤트 토글 함수
    function toggleEvent(eventId) {
        var content = document.getElementById('event-content-' + eventId);
        var header = content.previousElementSibling;

        if (content.classList.contains('active')) {
            content.classList.remove('active');
            header.classList.remove('active');
        } else {
            content.classList.add('active');
            header.classList.add('active');
        }
    }
</script>
</body>
<!-- END BODY -->

</html>
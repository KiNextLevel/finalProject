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
    <title>신고 관리 페이지</title>

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

    <link href="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/css/AdminReportPage.css"
          rel="stylesheet">

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
    <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css">
    <!-- for slider-range -->
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

<div class="main">
    <div class="container">
        <div class="row margin-bottom-40">
            <!-- 왼쪽 사이드바 추가 (관리자 메뉴) -->
            <div class="col-md-3 col-sm-5">
                <div class="sidebar">
                    <ul class="list-group margin-bottom-25 sidebar-menu">
                        <li class="list-group-item clearfix"><a href="adminPage.do"><i class="fa fa-home"></i> 관리자 대시보드</a>
                        </li>
                        <li class="list-group-item clearfix"><a href="boardPage.do"><i class="fa fa-file-text"></i> 게시글
                            관리</a></li>
                        <li class="list-group-item clearfix"><a href="adminPaymentListPage.do"><i
                                class="fa fa-credit-card"></i> 결제 내역 관리</a></li>
                        <li class="list-group-item clearfix active"><a href="adminReportPage.do"><i
                                class="fa fa-warning"></i> 신고 회원 관리</a></li>
                    </ul>
                </div>
            </div>

            <!-- 메인 컨텐츠 -->
            <div class="col-md-9 col-sm-7">
                <h1 class="page-title">신고 회원 관리</h1>

                <!-- 신고 목록 섹션 -->
                <div class="section-title">
                    <i class="fa fa-flag"></i> 신고 목록
                </div>

                <c:choose>
                    <c:when test="${empty reported}">
                        <div class="empty-message">
                            <i class="fa fa-info-circle fa-2x"></i>
                            <p>현재 접수된 신고 내역이 없습니다.</p>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <c:forEach var="data" items="${reported}">
                            <div class="report-panel">
                                <div class="report-header">
                                    <div class="row">
                                        <div class="col-md-6">
                                            <h4><span class="user-badge">신고자</span> ${data.reportReporter}</h4>
                                        </div>
                                        <div class="col-md-6 text-right">
                                            <h4><span class="user-badge">피신고자</span> ${data.reportReported}</h4>
                                        </div>
                                    </div>
                                </div>

                                <div class="report-content">
                                    <h5><strong>신고 사유:</strong> ${data.reportReason}</h5>
                                    <p>${data.reportDescription}</p>
                                </div>

                                <div class="report-actions">
                                    <form id="sendAlert-${data.reportReported}" method="POST" class="form-inline">
                                        <input type="hidden" name="reportedUser" value="${data.reportReported}">
                                        <input type="hidden" name="reason" value="${data.reportReason}">
                                        <input type="hidden" name="reportNum" value="${data.reportNumber}">
                                        <div class="form-group">
                                            <select id="action-${data.reportReported}" name="action"
                                                    class="form-control">
                                                <option value="adminSendWarning.do">경고 메시지 발송</option>
                                                <option value="adminAddBlack.do">블랙리스트에 추가</option>
                                            </select>
                                        </div>

                                        <button type="submit" class="btn btn-primary"
                                                onclick="setFormAction(event, this)">
                                            <i class="fa fa-send"></i> 조치 실행
                                        </button>
                                    </form>
                                </div>
                            </div>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>

                <!-- 블랙리스트 섹션 -->
                <div class="section-title">
                    <i class="fa fa-ban"></i> 블랙리스트
                </div>

                <table class="blacklist-table">
                    <thead>
                    <tr>
                        <th width="10%">#</th>
                        <th width="25%">회원 이름</th>
                        <th width="40%">회원 이메일</th>
                        <th width="25%">관리</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:choose>
                        <c:when test="${empty blacks}">
                            <tr>
                                <td colspan="4" class="empty-message">블랙리스트에 등록된 회원이 없습니다.</td>
                            </tr>
                        </c:when>
                        <c:otherwise>
                            <c:forEach var="blacksItem" items="${blacks}" varStatus="status">
                                <tr>
                                    <td class="text-center">${status.count}</td>
                                    <td>${blacksItem.userName}</td>
                                    <td>${blacksItem.userEmail}</td>
                                    <td>
                                        <form id="DELETEBLACK-${blacksItem.userEmail}" action="adminDeleteBlack.do"
                                              method="POST">
                                            <input type="hidden" name="blackEmail" value="${blacksItem.userEmail}">
                                            <button type="submit" class="btn btn-remove btn-sm">
                                                <i class="fa fa-trash-o"></i> 블랙리스트에서 삭제
                                            </button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<!-- BEGIN BRANDS -->

<!-- END BRANDS -->

<!-- BEGIN STEPS -->

<!-- END STEPS -->
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
    </div>
</div>
</div>
<script src="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/js/AdminReportPage.js"></script>

</body>
<!-- END BODY -->

</html>
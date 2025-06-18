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

<html lang="en">
<!--<![endif]-->

<!-- Head BEGIN -->
<head>
    <link rel="icon" type="image/png" sizes="16x16"  href="/favicon-32x32.png">
    <meta charset="utf-8">
    <title>이벤트 추가 페이지</title>

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
    <link href="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/css/AdminAddBoardPage.css"
          rel="stylesheet">
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

<div class="main event-add-page">
    <div class="container">
        <ul class="breadcrumb">
            <li><a href="mainPage.do">메인페이지</a></li>
            <li><a href="boardPage.do">이벤트 페이지</a></li>
            <li class="active">이벤트 추가</li>
        </ul>

        <!-- 페이지 제목 섹션 -->
        <div class="page-title-container">
            <h1><i class="fa fa-plus-circle"></i> 새 이벤트 추가</h1>
            <p>새로운 이벤트를 생성하고 참가자들에게 특별한 혜택을 제공하세요!</p>
        </div>

        <!-- BEGIN SIDEBAR & CONTENT -->
        <div class="row margin-bottom-40">
            <!-- 이벤트 추가 폼 시작 -->
            <div class="col-md-8">
                <div class="form-container">
                    <h2 class="form-title">이벤트 정보 입력</h2>
                    <form action="adminAddBoard.do" method="POST" role="form" class="form-horizontal">
                        <input type="hidden" name="command" value="ADMINADDBOARD">

                        <div class="form-group">
                            <label class="col-lg-3 control-label" for="event-title">이벤트 제목<span class="require">*</span></label>
                            <div class="col-lg-9">
                                <input type="text" id="event-title" name="boardTitle" class="form-control"
                                       placeholder="이벤트 제목을 입력하세요">
                                <span class="help-block">참가자들의 관심을 끌 수 있는 매력적인 제목을 입력하세요.</span>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-lg-3 control-label" for="event-content">이벤트 내용<span class="require">*</span></label>
                            <div class="col-lg-9">
        <textarea id="event-content" class="form-control" rows="10" name="boardContent"
                  placeholder="이벤트 내용과 날짜를 입력하세요"></textarea>
                                <span class="help-block">이벤트 내용, 진행 기간, 참여 방법, 혜택 등을 자세히 설명해주세요.</span>
                            </div>
                        </div>


                        <div class="form-group">
                            <label class="col-lg-3 control-label" for="event-limit">최대 참여 인원<span
                                    class="require">*</span></label>
                            <div class="col-lg-9">
                                <input type="number" id="event-limit" name="boardLimit" class="form-control" min="1"
                                       placeholder="최대 참여 가능 인원수">
                                <span class="help-block">이벤트에 참여할 수 있는 최대 인원을 설정하세요. (제한이 없으면 큰 숫자를 입력하세요)</span>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-lg-9 col-lg-offset-3">
                                <a href="boardPage.do" class="btn btn-event-cancel">
                                    <i class="fa fa-times"></i> 취소하기
                                </a>
                                <button class="btn btn-event-add" type="submit">
                                    <i class="fa fa-check"></i> 이벤트 추가하기
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <!-- 이벤트 추가 폼 끝 -->

            <!-- 사이드바 정보 시작 -->
            <div class="col-md-4">
                <div class="side-info-panel">
                    <h3 class="side-info-title"><i class="fa fa-info-circle"></i> 이벤트 등록 안내</h3>
                    <div class="info-item">
                        <i class="fa fa-check-circle"></i> 이벤트 제목은 간결하고 명확하게 작성해주세요.
                    </div>
                    <div class="info-item">
                        <i class="fa fa-check-circle"></i> 이벤트 내용에는 진행 기간, 참여 방법, 당첨자 발표일 등을 포함해주세요.
                    </div>
                    <div class="info-item">
                        <i class="fa fa-check-circle"></i> 이벤트 참여 인원은 준비된 상품 수량에 맞게 설정해주세요.
                    </div>
                    <div class="info-item">
                        <i class="fa fa-check-circle"></i> 등록된 이벤트는 관리자 페이지에서 수정 및 삭제가 가능합니다.
                    </div>
                    <div class="info-item">
                        <i class="fa fa-exclamation-triangle" style="color: #f39c12;"></i> 이벤트 내용에 개인정보 수집 동의 관련 내용을
                        포함해주세요.
                    </div>
                </div>

                <div class="side-info-panel" style="margin-top: 20px;">
                    <h3 class="side-info-title"><i class="fa fa-lightbulb-o"></i> 성공적인 이벤트 팁</h3>
                    <div class="info-item">
                        <i class="fa fa-star"></i> 계절이나 특별한 날에 맞춰 이벤트를 기획하세요.
                    </div>
                    <div class="info-item">
                        <i class="fa fa-star"></i> 참여자들이 쉽게 참여할 수 있는 방법을 제공하세요.
                    </div>
                    <div class="info-item">
                        <i class="fa fa-star"></i> 매력적인 혜택이나 상품을 준비하세요.
                    </div>
                    <div class="info-item">
                        <i class="fa fa-star"></i> SNS 공유를 통해 이벤트 참여율을 높이세요.
                    </div>
                </div>
            </div>
            <!-- 사이드바 정보 끝 -->
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

                // 폼 유효성 검사
                $('form').on('submit', function (e) {
                    var title = $('#event-title').val();
                    var content = $('#event-content').val();
                    var limit = $('#event-limit').val();

                    if (title.trim() === '') {
                        alert('이벤트 제목을 입력해주세요.');
                        $('#event-title').focus();
                        e.preventDefault();
                        return false;
                    }

                    if (content.trim() === '') {
                        alert('이벤트 내용을 입력해주세요.');
                        $('#event-content').focus();
                        e.preventDefault();
                        return false;
                    }

                    if (limit.trim() === '' || parseInt(limit) < 1) {
                        alert('유효한 참여 인원 수를 입력해주세요.');
                        $('#event-limit').focus();
                        e.preventDefault();
                        return false;
                    }

                    return true;
                });
            });
        </script>
        <!-- END PAGE LEVEL JAVASCRIPTS -->
    </div>
</div>
</div>
<!-- END PRE-FOOTER -->
</body>
<!-- END BODY -->
</html>

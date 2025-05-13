<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>

<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<html lang="en">
<!-- Head BEGIN -->
<head>
    <link rel="icon" type="image/png" sizes="16x16"  href="/favicon-16x16.png">
    <meta name="msapplication-TileColor" content="#ffffff">
    <meta name="theme-color" content="#ffffff">
    <meta charset="utf-8">
    <title>회원 탈퇴</title>

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
    <link href="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/css/AccountDelete.css"
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
        <a class="site-logo" href="mainPage.do"><img src="assets/corporate/img/logos/3.png" alt="mainPage"></a>

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

<!-- 회원탈퇴 안내 -->
<div class="main">
    <div class="container">
        <div class="withdrawal-container">
            <div class="withdrawal-card">
                <!-- 헤더 부분 - 더 부드러운 색상으로 변경 -->
                <div class="card-header">
                    <i class="fa fa-info-circle header-icon"></i>
                    <h2>회원탈퇴 안내</h2>
                    <p>탈퇴 전 아래 내용을 반드시 확인해주세요.</p>
                </div>

                <!-- 유의사항 섹션 - 배경색 부드럽게 변경 -->
                <div class="card-body">
                    <div class="notice-section">
                        <h3>탈퇴 시 유의사항</h3>
                        <ul>
                            <li>탈퇴 후에는 회원님의 모든 정보가 삭제되며 복구할 수 없습니다.</li>
                            <li>작성한 신고 및 사유는 자동으로 삭제되지 않습니다.</li>
                            <li>보유하신 토큰와 혜택은 모두 소멸됩니다.</li>
                        </ul>
                    </div>

                    <!-- 약관 섹션 -->
                    <div class="terms-section">
                        <h3>회원탈퇴 약관</h3>
                        <div class="terms-box">
                            <div class="terms-content">
                                <strong>제1조 (목적)</strong><br>
                                본 약관은 Next Level(이하 "회사"라 함)의 서비스 이용계약 해지(회원탈퇴)와 관련하여 회사와 회원 간의 권리, 의무 및 책임사항, 서비스 이용에
                                따른 정보 처리 등에 관한 사항을 규정함을 목적으로 합니다.<br><br>

                                <strong>제2조 (회원탈퇴 및 서비스 이용계약 해지)</strong><br>
                                ① 회원은 언제든지 회사에 대하여 회원탈퇴를 요청할 수 있으며, 회사는 관련법 등이 정하는 바에 따라 이를 즉시 처리합니다.<br>
                                ② 회원이 회원탈퇴를 요청한 경우 회사는 회원 본인 여부를 확인할 수 있으며, 본인 확인이 되지 않은 경우 회원탈퇴 요청을 거부할 수 있습니다.<br>
                                ③ 회원탈퇴로 인해 발생한 불이익에 대한 책임은 회원 본인에게 있으며, 회사는 회원에게 발생한 손해에 대해 책임을 지지 않습니다.<br><br>

                                <strong>제3조 (회원정보의 보유 및 이용)</strong><br>
                                ① 회원탈퇴 시 회원의 모든 개인정보는 즉시 삭제됩니다. 단, 관련법령에 의해 보존할 필요가 있는 경우 해당 정보는 법령에서 정한 기간 동안
                                보관됩니다.<br>
                                ② 회원이 작성한 게시물, 신고 내역 등 공개적으로 작성한 콘텐츠는 삭제되지 않으며, 해당 콘텐츠의 삭제를 원하는 경우 회원탈퇴 전 직접 삭제해야
                                합니다.<br>
                                ③ 회원탈퇴 후 재가입하더라도 이전에 보유한 토큰, 혜택 등은 복구되지 않습니다.<br><br>

                                <strong>제4조 (미지급 서비스 이용료 등의 정산)</strong><br>
                                ① 회원탈퇴 시 미사용 토큰, 혜택 등은 소멸되며 환불되지 않습니다.<br>
                                ② 회원탈퇴 시 진행 중인 거래나 서비스가 있는 경우, 해당 거래나 서비스가 완료된 후에 탈퇴가 처리됩니다.<br><br>

                                <strong>제5조 (재가입 제한)</strong><br>
                                ① 회사는 서비스의 건전한 운영과 질서 유지를 위해 회원탈퇴 후 일정 기간(30일) 동안 동일 계정으로의 재가입을 제한할 수 있습니다.<br>
                                ② 서비스 운영정책 위반으로 강제 탈퇴된 회원의 경우, 재가입이 영구적으로 제한될 수 있습니다.<br><br>

                                <strong>제6조 (약관의 개정)</strong><br>
                                본 약관은 관련법 개정이나 서비스 정책 변경 등의 사유로 개정될 수 있으며, 회사는 약관 개정 시 적용일자 및 개정사유를 명시하여 사전에 공지합니다.
                            </div>
                        </div>

                        <div class="agree-checkbox">
                            <input type="checkbox" id="agreeTerms" required>
                            <label for="agreeTerms">위 약관에 동의합니다.</label>
                        </div>
                    </div>


                    <!-- 버튼 섹션 -->
                    <div class="buttons-section">
                        <button type="button" class="btn btn-cancel"><a href="myPage.do">취소하기</a></button>
                        <button type="button" class="btn btn-withdraw">회원탈퇴</button>
                    </div>
                </div>
            </div>
        </div>
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
        <script src="assets/plugins/jquery.min.js" type="text/javascript"></script>
        <script src="assets/plugins/jquery-migrate.min.js" type="text/javascript"></script>
        <script src="assets/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="assets/plugins/jquery-slimscroll/jquery.slimscroll.min.js"
                type="text/javascript"></script>
        <!-- END CORE PLUGINS -->

        <!-- BEGIN PAGE LEVEL JAVASCRIPTS (REQUIRED ONLY FOR CURRENT PAGE) -->
        <script src="assets/plugins/fancybox/source/jquery.fancybox.pack.js"
                type="text/javascript"></script>
        <!-- pop up -->
        <script src="assets/plugins/owl.carousel/owl.carousel.min.js" type="text/javascript"></script>
        <!-- slider for products -->

        <script src="assets/corporate/scripts/layout.js" type="text/javascript"></script>
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
<script src="${pageContext.request.contextPath}js/AccountDelete.js"></script>

</body>

</html>
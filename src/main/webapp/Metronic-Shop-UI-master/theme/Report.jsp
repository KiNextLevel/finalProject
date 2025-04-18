<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <link rel="icon" type="image/png" sizes="16x16"  href="/favicon-32x32.png">
    <meta charset="utf-8">
    <title>${param.userEmail} 신고 페이지</title>

    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

    <meta content="Metronic Shop UI description" name="description">
    <meta content="Metronic Shop UI keywords" name="keywords">
    <meta content="keenthemes" name="author">

    <meta property="og:site_name" content="-CUSTOMER VALUE-">
    <meta property="og:title" content="-CUSTOMER VALUE-">
    <meta property="og:description" content="-CUSTOMER VALUE-">
    <meta property="og:type" content="website">
    <meta property="og:image" content="-CUSTOMER VALUE-">
    <meta property="og:url" content="-CUSTOMER VALUE-">

    <link rel="shortcut icon" href="favicon.ico">
    <link href="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/css/Report.css" rel="stylesheet">

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

</head>

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
                    <!-- 관리자만 보이는 버튼 -->
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

<!-- BEGIN HEADER -->
<div class="header">
    <div class="container">
        <a class="site-logo" href="mainPage.do"><img
                src="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/corporate/img/logos/3.png"
                alt="Next Level"></a>

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
        <div class="row">
            <!-- BEGIN CONTENT -->
            <div class="col-md-12">
                <div class="content-page">
                    <div class="row">
                        <div class="col-md-8 col-md-offset-2">
                            <div class="report-container">
                                <h2 class="report-title"><i class="fa fa-exclamation-triangle"></i> 사용자 신고</h2>

                                <form action="report.do" method="post" id="reportForm">
                                    <h3 class="report-subtitle">${reportedUserNickname} 님에 대한 신고</h3>

                                    <div class="checkbox-group">
                                        <h4><i class="fa fa-list-ul"></i> 신고 사유 선택 (하나 이상 선택)</h4>
                                        <label>
                                            <input type="checkbox" name="reason" value="욕설"> 욕설 및 비방
                                        </label>
                                        <label>
                                            <input type="checkbox" name="reason" value="성희롱"> 성희롱 및 불쾌한 발언
                                        </label>
                                        <label>
                                            <input type="checkbox" name="reason" value="광고"> 스팸 및 광고성 내용
                                        </label>
                                        <label>
                                            <input type="checkbox" name="reason" value="버그 악용"> 시스템 버그 악용
                                        </label>
                                        <label>
                                            <input type="checkbox" name="reason" value="기타"> 기타 사유
                                        </label>
                                    </div>

                                    <div class="description-area">
                                        <h4><i class="fa fa-comment"></i> 상세 내용</h4>
                                        <textarea name="description"
                                                  placeholder="신고 내용을 자세히 작성해주세요. 구체적인 정보가 있으면 처리가 빨라집니다."
                                                  required></textarea>
                                    </div>

                                    <div class="agreement-box">
                                        <h4><i class="fa fa-info-circle"></i> 신고 정보 안내</h4>
                                        <p>
                                            신고 내용은 관리자에게 전달되며, 신고자와 피신고자의 정보 및 관련 대화 내용이 함께 전송됩니다.
                                            개인정보가 포함될 수 있으며, 이는 「개인정보 보호법」 제15조에 따라 신고 처리 목적으로만 수집·이용됩니다.
                                            수집된 개인정보는 신고 처리 완료 후 관련 법령에 따른 보관 기간이 지나면 안전하게 파기됩니다.
                                        </p>

                                        <div class="agreement-check">
                                            <input type="checkbox" id="agree" name="agree" required>
                                            <label for="agree">위 내용을 이해하고 동의합니다.</label>
                                        </div>
                                    </div>

                                    <input type="hidden" name="userEmail" value="${param.userEmail}">
                                    <button class="submit-btn" type="submit">신고 제출하기</button>
                                </form>

                                <div class="report-info">
                                    <h4>신고 처리 절차</h4>
                                    <p>
                                        1. 신고 접수: 신고가 접수되면 관리자에게 알림이 전송됩니다.<br>
                                        2. 내용 검토: 관리자가 신고 내용을 검토하고 필요시 추가 자료를 요청할 수 있습니다.<br>
                                        3. 조치 결정: 검토 결과에 따라 적절한 조치가 결정됩니다.<br>
                                        4. 결과 통보: 처리 결과는 신고자에게 알림으로 통보됩니다.<br>
                                    </p>
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
<script src="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/plugins/jquery-slimscroll/jquery.slimscroll.min.js"
        type="text/javascript"></script>
<!-- END CORE PLUGINS -->

<!-- BEGIN PAGE LEVEL JAVASCRIPTS -->
<script src="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/plugins/fancybox/source/jquery.fancybox.pack.js"
        type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/plugins/owl.carousel/owl.carousel.min.js"
        type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/corporate/scripts/layout.js"
        type="text/javascript"></script>
<!-- END PAGE LEVEL JAVASCRIPTS -->

<script>
    jQuery(document).ready(function () {
        Layout.init();
        Layout.initOWL();


    });
</script>
<script src="${pageContext.request.contextPath}js/Report.js"></script>
</body>
</html>

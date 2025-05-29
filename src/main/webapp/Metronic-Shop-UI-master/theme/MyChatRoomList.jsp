<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>자신의 채팅방 리스트</title>
    <%-- 스타일 시트 추가--%>
    <link href="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/css/MyChatRoomList.css"
          rel="stylesheet">
    <link rel="icon" type="image/png" sizes="16x16" href="/favicon-32x32.png">
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

<!-- BEGIN TOP BAR -->
<div class="pre-header">
    <div class="container">
        <div class="row">
            <!-- BEGIN TOP BAR LEFT PART -->
            <div class="col-md-6 col-sm-6 additional-shop-info">
                <ul class="list-unstyled list-inline">
                    <li><i class="fa fa-phone"></i><span>010 - 0242 - 0242</span></li>
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
                    <li><a href="/myChatRoomList.do">메시지</a></li>
                    <li><a href="/logout.do">로그아웃</a></li>
                </ul>
            </div>
            <!-- END TOP BAR MENU -->
        </div>
    </div>
</div>
<!-- END TOP BAR -->
<body>
<div class="wrapper">
    <div class="chat-list-wrapper">
        <h2>나의 채팅방 목록</h2>
        <c:choose>
            <c:when test="${empty roomList}">
                <!-- 채팅방이 하나도 없을 때 보여주는 문구 -->
                <p>진행 중인 채팅이 없습니다.</p>
            </c:when>
            <c:otherwise>
                <!-- roomList에 들어있는 채팅방 목록을 반복 출력 -->
                <c:forEach var="room" items="${roomList}">
                    <li class="chat-room-box">
                        <a href="/chattingRoom.do?chatRoomId=${room.chatRoomId}&targetEmail=${room.opponentEmail}">
                                <%--<p>디버깅용: 이메일 = ${room.opponentEmail}</p>--%>

                            <p>상대방: ${room.opponentNickname}</p>
                                <%-- <p>이메일: ${room.opponentEmail}</p>--%>
                        </a>
                    </li>
                </c:forEach>

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
                    <a class="twitter-timeline" href="https://github.com/KiNextLevel" data-tweet-limit="2"
                       data-theme="dark"
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
    </div>
    <!-- END FOOTER -->
</body>
</html>

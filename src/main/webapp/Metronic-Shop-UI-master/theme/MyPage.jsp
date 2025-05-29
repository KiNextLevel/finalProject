<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="kr">
<head>
    <link rel="icon" type="image/png" sizes="16x16"  href="/favicon-32x32.png">
    <meta charset="utf-8"/>
    <title>마이페이지 | Next Level</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <link href="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/css/MyPage.css" rel="stylesheet">
    <!-- 메타 태그 -->
    <meta content="Next Level - 마이페이지" name="description"/>
    <meta content="dating, social, next level" name="keywords"/>
    <meta content="Next Level" name="author"/>

    <link rel="shortcut icon" href="favicon.ico"/>
    <!-- 폰트 및 스타일시트 링크들 (원본과 동일) -->
    <link href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700|PT+Sans+Narrow|Source+Sans+Pro:200,300,400,600,700,900&amp;subset=all"
          rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"/>
    <link href="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/plugins/bootstrap/css/bootstrap.min.css"
          rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/plugins/font-awesome/css/font-awesome.min.css"
          rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/pages/css/components.css"
          rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/corporate/css/style.css"
          rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/pages/css/style-shop.css"
          rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/corporate/css/style-responsive.css"
          rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/corporate/css/themes/red.css"
          rel="stylesheet" id="style-color"/>
    <link href="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/corporate/css/custom.css"
          rel="stylesheet"/>
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
        <!-- 경로 표시 -->
        <ul class="breadcrumb">
            <li><a href="mainPage.do">홈</a></li>
            <li><a href="">마이페이지</a></li>
            <li class="active">내 정보</li>
        </ul>

        <!-- 메인 콘텐츠 -->
        <div class="row margin-bottom-40">
            <!-- 사이드바 메뉴 -->
            <div class="sidebar col-md-3 col-sm-3">
                <ul class="list-group margin-bottom-25 sidebar-menu">
                    <li class="list-group-item clearfix active"><a href="#basic-info"><i class="fas fa-user"></i> 기본 정보</a>
                    </li>
                    <li class="list-group-item clearfix"><a href="#additional-info"><i class="fa fa-heart"></i> 추가
                        정보</a></li>
                    <li class="list-group-item clearfix"><a href="#preference-info"><i class="fa fa-calendar"></i> 선호 취향</a>
                    </li>
                    <li class="list-group-item clearfix"><a href="#event-info"><i class="fa fa-coins"></i> 참가 중인 이벤트</a>
                    </li>
                    <li class="list-group-item clearfix"><a href="#token-info"><i class="fa fa-cog"></i> 토큰 잔액 및 구매
                        내역</a></li>
                    <li class="list-group-item clearfix"><a href="#delete-info"><i class="fa fa-cog"></i> 회원 탈퇴</a></li>

                </ul>
            </div>

            <!-- 메인 콘텐츠 영역 -->
            <div class="col-md-9 col-sm-9">
                <div class="main-content">
                    <h1>마이 페이지</h1>

                    <!-- 프로필 섹션 -->
                    <!-- 프로필 섹션 -->
                    <div class="profile-container">
                        <div class="profile-header">
                            <div class="profile-left">
                                <div class="profile-image-container">
                                    <!-- 프로필 이미지 업로드를 위한 폼 수정 -->
                                    <form id="profileForm" action="updateProfileImage.do" method="POST"
                                          enctype="multipart/form-data">
                                        <input type="file" id="profileUpload" name="profileImage" accept="image/*"
                                               onchange="this.form.submit();"/>
                                    </form>

                                    <label for="profileUpload">
                                        <c:choose>
                                            <c:when test="${not empty userDTO.userProfile}">
                                                <img id="profileImage" src="${userDTO.userProfile}" alt="프로필 이미지"/>
                                            </c:when>
                                            <c:otherwise>
                                                <img id="profileImage"
                                                     src="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/corporate/img/logos/3.png"
                                                     alt="기본 프로필 이미지"/>
                                            </c:otherwise>
                                        </c:choose>

                                        <div class="image-overlay">
                                            <i class="fa fa-camera"></i> <span>사진 변경</span>
                                        </div>
                                    </label>
                                </div>

                                <div class="profile-info">
                                    <h3 class="profile-name">
                                        ${userDTO.userName}
                                    </h3>
                                    <c:choose>
                                        <c:when test="${userDTO.userPremium}">
                                            <span class="profile-role">프리미엄 회원</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="profile-role">일반 회원</span>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>

                            <div class="profile-actions">
                                <a href="updateProfilePage.do" class="btn btn-primary">
                                    <i class="fa fa-edit"></i> 정보 수정
                                </a>
                            </div>
                        </div>
                        <!-- 기본 정보 카드 -->
                        <div id="basic-info" class="info-card">
                            <h3>기본 정보</h3>
                            <div class="info-list">
                                <div class="info-item">
                                    <div class="info-icon">
                                        <i class="fa fa-comment"></i>
                                    </div>
                                    <div class="info-label">자기소개</div>
                                    <div class="info-value">
                                        <c:choose>
                                            <c:when test="${not empty userDTO.userDescription}">
                                                ${userDTO.userDescription}
                                            </c:when>
                                            <c:otherwise>
                                                자기소개를 입력해주세요.
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                </div>
                                <%--  이름--%>
                                <div class="info-item">
                                    <div class="info-icon">
                                        <i class="fa fa-user"></i>
                                    </div>
                                    <div class="info-label">이름</div>
                                    <div class="info-value">
                                        ${userDTO.userName}
                                    </div>
                                </div>

                                <%-- 이메일 --%>
                                <div class="info-item">
                                    <div class="info-icon">
                                        <i class="fa fa-envelope"></i>
                                    </div>
                                    <div class="info-label">이메일</div>
                                    <div class="info-value">
                                        ${userDTO.userEmail}
                                    </div>
                                </div>
                                <%-- 전화번호 --%>
                                <div class="info-item">
                                    <div class="info-icon">
                                        <i class="fa fa-phone"></i>
                                    </div>
                                    <div class="info-label">전화번호</div>
                                    <div class="info-value">
                                        ${userDTO.userPhone}
                                    </div>
                                </div>
                                <%-- 성별 --%>
                                <div class="info-item">
                                    <div class="info-icon">
                                        <i class="fa fa-venus-mars"></i>
                                    </div>
                                    <div class="info-label">성별</div>
                                    <div class="info-value">
                                        <c:choose>
                                            <c:when test="${userDTO.userGender}">
                                                여성
                                            </c:when>
                                            <c:otherwise>
                                                남성
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                </div>
                                <%-- 생년월일 --%>
                                <div class="info-item">
                                    <div class="info-icon">
                                        <i class="fa fa-birthday-cake"></i>
                                    </div>
                                    <div class="info-label">생년월일</div>
                                    <div class="info-value">
                                        ${userDTO.userBirth}
                                    </div>
                                </div>
                                <%-- 키 --%>
                                <div class="info-item">
                                    <div class="info-icon">
                                        <i class="fas fa-ruler"></i>
                                    </div>
                                    <div class="info-label">키</div>
                                    <div class="info-value">
                                        ${userDTO.userHeight}cm
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- 추가 정보 카드 -->
                        <div id="additional-info" class="info-card">
                            <h3>추가 정보</h3>
                            <div class="info-list">
                                <%-- 체형 --%>
                                <div class="info-item">
                                    <div class="info-icon">
                                        <i class="fas fa-weight"></i>
                                    </div>
                                    <div class="info-label">체형</div>
                                    <div class="info-value">
                                        ${userDTO.userBody}
                                    </div>
                                </div>
                                <%-- 학력 --%>
                                <div class="info-item">
                                    <div class="info-icon">
                                        <i class="fa fa-graduation-cap"></i>
                                    </div>
                                    <div class="info-label">학력</div>
                                    <div class="info-value">
                                        ${userDTO.userEducation}
                                    </div>
                                </div>
                                    <%-- 지역 --%>
                                <div class="info-item">
                                    <div class="info-icon">
                                        <i class="fa fa-map-marker"></i>
                                    </div>
                                    <div class="info-label">지역</div>
                                    <div class="info-value">
                                        ${userDTO.userRegion}
                                    </div>
                                </div>
                                    <%-- MBTI --%>
                                <div class="info-item">
                                    <div class="info-icon">
                                        <i class="fas fa-theater-masks"></i>
                                    </div>
                                    <div class="info-label">MBTI</div>
                                    <div class="info-value">
                                        ${userDTO.userMbti}
                                    </div>
                                </div>
                                    <%-- 음주 --%>
                                <div class="info-item">
                                    <div class="info-icon">
                                        <i class="fas fa-wine-glass"></i>
                                    </div>
                                    <div class="info-label">음주</div>
                                    <div class="info-value">
                                        <c:choose>
                                            <c:when test="${userDTO.userDrink == 0}">전혀 안함</c:when>
                                            <c:when test="${userDTO.userDrink == 1}">가끔</c:when>
                                            <c:when test="${userDTO.userDrink == 2}">자주</c:when>
                                            <c:otherwise>음주 정보를 입력해주세요.</c:otherwise>
                                        </c:choose>
                                    </div>
                                </div>
                                    <%-- 흡연 --%>
                                <div class="info-item">
                                    <div class="info-icon">
                                        <i class="fas fa-smoking"></i>
                                    </div>
                                    <div class="info-label">흡연</div>
                                    <div class="info-value">
                                        <c:choose>
                                            <c:when test="${userDTO.userSmoke}">흡연</c:when>
                                            <c:otherwise>비흡연</c:otherwise>
                                        </c:choose>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- 선호 취향 카드 -->
                        <div id="preference-info" class="info-card">
                            <h3>선호 취향</h3>
                            <div class="preference-tags">
                            <span class="preference-tag">
                                <i class="fas fa-ruler"></i>
                                        ${preferenceDTO.preferenceHeight}
                            </span>
                                <span class="preference-tag">
                                <i class="fas fa-weight"></i>
                                        ${preferenceDTO.preferenceBody}
                            </span>
                                <span class="preference-tag">
                                <i class="fas fa-birthday-cake"></i>
                                        ${preferenceDTO.preferenceAge}
                            </span>
                            </div>
                        </div>

                        <!-- 참가중인 이벤트 -->
                        <div id="event-info" class="info-card">
                            <h3>참가 중인 이벤트</h3>
                            <table id="event-table" class="table table-striped">
                                <thead>
                                <tr>
                                    <th>이벤트 이름</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:choose>
                                    <c:when test="${not empty participantList}">
                                        <c:forEach var="event" items="${participantList}">
                                            <tr>
                                                    <%-- 이벤트 참가는 제목만 출력되게 했음--%>
                                                <td>${event.boardTitle}</td>
                                            </tr>
                                        </c:forEach>
                                    </c:when>
                                    <c:otherwise>
                                        <tr>
                                            <td>참가 중인 이벤트가 없습니다.</td>
                                        </tr>
                                    </c:otherwise>
                                </c:choose>
                                </tbody>
                            </table>
                        </div>

                        <!-- 토큰 잔액 및 구매 내역 -->
                        <div id="token-info" class="info-card">
                            <h3>토큰 잔액 및 구매 내역</h3>
                            <table id="payment-table" class="table table-striped">
                                <p>
                                    <strong>현재 보유 토큰:</strong>
                                    <span style="color: #e94d1c; font-size: 18px;">
                                ${userDTO.userToken} 개
                            </span>
                                </p>

                                <thead>
                                <tr>
                                    <th>상품명</th>
                                    <th>결제금액</th>
                                    <th>결제일</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="payment" items="${paymentList}">
                                    <tr>
                                        <td>${payment.productName}</td>
                                        <td>${payment.productPrice}</td>
                                        <td><fmt:formatDate value="${payment.paymentDate}" pattern="yyyy-MM-dd"/></td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>


                        <div id="delete-info" class="info-card">
                            <h3>계정 설정</h3>
                            <!-- 계정 설정 -->
                            <p>회원 탈퇴를 원하시면 아래 버튼을 클릭하세요.</p>
                            <a href="accountDelete.do"
                               class="btn btn-danger">
                                <i class="fa fa-user-times"></i> 회원 탈퇴하기
                            </a>
                        </div>
                    </div>
                </div>
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
    <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js" type="text/javascript"></script>
    <!-- for slider-range -->

    <script src="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/corporate/scripts/layout.js"
            type="text/javascript"></script>

    <script src="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/js/Mypage.js"></script>
    <%--페이지네이션 추가--%>
    <script src="${pageContext.request.contextPath}/target-free-admin-template/assets/js/dataTables/jquery.dataTables.js"></script>
    <%--페이지네이션 css 추가--%>
    <script src="${pageContext.request.contextPath}/target-free-admin-template/assets/js/dataTables/dataTables.bootstrap.js"></script>

    <script>
        $(document).ready(function () {
            // 결제 내역 페이지네이션 설정
            $('#payment-table').dataTable({
                "order": [[2, "desc"]],
                "searching": false,  // 검색기능 없애기
                "lengthChange": false,  // 단위 목록 출력 없애기
                "language": {
                    "zeroRecords": "일치하는 검색 결과가 없습니다.",
                    "emptyTable": "테이블에 데이터가 없습니다.",
                    "info": "총 _TOTAL_건 중 _START_ - _END_건 표시",
                    "infoEmpty": "데이터 없음",
                    "infoFiltered": "(총 _MAX_건 중 필터링됨)",
                    "search": "검색:",
                    "lengthMenu": "_MENU_ 개씩 보기",
                    "paginate": {
                        "first": "처음",
                        "last": "마지막",
                        "next": "다음",
                        "previous": "이전"
                    }
                }
            });
            // 이벤트 목록 페이지네이션 설정
            $('#event-table').dataTable({
                "order": [[0, "desc"]],
                "lengthChange": false,  // 단위 목록 출력 없애기
                "language": {
                    "zeroRecords": "일치하는 검색 결과가 없습니다.",
                    "emptyTable": "테이블에 데이터가 없습니다.",
                    "info": "총 _TOTAL_건 중 _START_ - _END_건 표시",
                    "infoEmpty": "데이터 없음",
                    "infoFiltered": "(총 _MAX_건 중 필터링됨)",
                    "search": "검색:",
                    "lengthMenu": "_MENU_ 개씩 보기",
                    "paginate": {
                        "first": "처음",
                        "last": "마지막",
                        "next": "다음",
                        "previous": "이전"
                    }
                }
            });
        });
    </script>

</body>
</html>
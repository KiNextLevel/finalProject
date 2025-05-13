<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="icon" type="image/png" sizes="16x16"  href="/favicon-16x16.png">
    <meta name="msapplication-TileColor" content="#ffffff">
    <meta name="theme-color" content="#ffffff">
    <meta charset="utf-8">
    <title>회원 정보 입력</title>
    <link href="css/Join.css" rel="stylesheet">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

    <meta content="Next Love 회원가입" name="description">
    <meta content="회원가입, 데이팅, Next Love" name="keywords">
    <meta content="Next Love" name="author">

    <link rel="shortcut icon" href="favicon.ico">

    <!-- Fonts START -->
    <link
            href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700|PT+Sans+Narrow|Source+Sans+Pro:200,300,400,600,700,900&amp;subset=all"
            rel="stylesheet" type="text/css">
    <!-- Fonts END -->

    <!-- Global styles START -->
    <link href="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- Global styles END -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Theme styles START -->
    <link href="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/pages/css/components.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/corporate/css/style.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/pages/css/style-shop.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/corporate/css/style-responsive.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/corporate/css/themes/red.css" rel="stylesheet" id="style-color">
    <link href="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/corporate/css/custom.css" rel="stylesheet">
    <!-- Theme styles END -->
</head>

<%--<script>--%>
<%--    function openAddressPopup() {--%>
<%--        window.open('/Metronic-Shop-UI-master/theme/addressPopup.html', '주소 찾기', 'width=600,height=600');--%>
<%--    }--%>
<%--</script>--%>


<body class="ecommerce">
<!-- BEGIN TOP BAR -->
<div class="pre-header">
    <div class="container">
        <div class="row">
            <div class="col-md-6 col-sm-6 additional-nav">
                <ul class="list-unstyled list-inline pull-right">
                    <li><a href="indexPage.do">로그인 화면으로</a></li>
                </ul>
            </div>
        </div>
    </div>
</div>
<!-- END TOP BAR -->

<!-- BEGIN HEADER -->
<div class="header">
    <div class="container">
        <a class="site-logo" href="Index.jsp"><img src="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/corporate/img/logos/3.png"
                                                   alt="Next Love"></a>
        <a href="javascript:void(0);" class="mobi-toggler"><i class="fa fa-bars"></i></a>
    </div>
</div>
<!-- Header END -->

<div class="main">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <h1>회원 정보 입력</h1>

                <div class="progress-container">
                    <div class="progress">
                        <div class="progress-bar bg-success" role="progressbar" style="width: 66%;" aria-valuenow="66"
                             aria-valuemin="0" aria-valuemax="100">2/3 단계
                        </div>
                    </div>
                    <div class="text-center mt-2">
                        <small>
                            <c:choose>
                                <c:when test="${socialType eq 'naver'}">
                                    네이버 계정으로 로그인하셨습니다. 추가 정보를 입력해 주세요.
                                </c:when>
                                <c:otherwise>
                                    기본 정보를 입력하셨습니다. 이제 추가 정보를 입력해 주세요.
                                </c:otherwise>
                            </c:choose>
                        </small>
                    </div>
                </div>

                <form action="joinPage.do" method="post" id="userInfoForm" enctype="multipart/form-data">
                    <!-- 소셜 로그인 타입 히든 필드 -->
                    <c:if test="${not empty socialType}">
                        <input type="hidden" name="socialType" value="${socialType}">
                    </c:if>


                    <!-- 기본 정보 섹션 -->
                    <div class="form-section">
                        <h3><i class="fa fa-user"></i> 기본 정보</h3>

                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label for="nickname" class="required-field">닉네임</label>
                                    <input type="text" class="form-control" id="nickname" name="userNickname"
                                           value="${userNickname}" required maxlength="50">
                                </div>
                            </div>

                            <div class="col-md-6">
                                <div class="form-group">
                                    <label for="email" class="required-field">이메일</label>
                                    <input type="email" class="form-control" id="email" name="userEmail"
                                           value="${userEmail}" readonly>
                                    <small class="form-text text-muted">소셜 로그인으로 가입 시 이메일은 변경할 수 없습니다.</small>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label for="name" class="required-field">이름</label>
                                    <input type="text" class="form-control" id="name" name="userName"
                                           value="${userName}" readonly>
                                    <small class="form-text text-muted">소셜 로그인으로 가입 시 이름은 변경할 수 없습니다.</small>
                                </div>
                            </div>

                            <div class="col-md-6">
                                <div class="form-group">
                                    <label for="phone" class="required-field">전화번호</label>
                                    <input type="tel" class="form-control" id="phone" name="userPhone"
                                           placeholder="010-0000-0000" maxlength="50">
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-4">
                                <div class="form-group">
                                    <label for="gender" class="required-field">성별</label>
                                    <select class="form-control" id="gender" name="userGender" required>
                                        <option value="">선택하세요</option>
                                        <option value="1">남성</option>
                                        <option value="0">여성</option>
                                    </select>
                                </div>
                            </div>

                            <div class="col-md-4">
                                <div class="form-group">
                                    <label for="birth" class="required-field">생년월일</label>
                                    <input type="date" class="form-control" id="birth" name="userBirth" required>
                                </div>
                            </div>

                            <%-- 위도/경도는 Java 서버 코드에서 GeoCodingUtil로 처리, input 2군데 제거  --%>
                            <div class="col-md-4">
                                <div class="form-group">
                                    <label for="region" class="required-field">지역</label>
                                    <input type="text" class="form-control" id="region" name="userRegion" readonly required>
                                    <button type="button" class="btn btn-secondary mt-2" onclick="openAddressPopup()">주소 찾기</button>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- 신체 정보 섹션 -->
                    <div class="form-section">
                        <h3><i class="fa fa-heart"></i> 신체 정보</h3>

                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label for="height" class="required-field">키 (cm)</label>
                                    <input type="number" class="form-control" id="height" name="userHeight" min="140"
                                           max="220">
                                </div>
                            </div>

                            <div class="col-md-6">
                                <div class="form-group">
                                    <label for="body" class="required-field">체형</label>
                                    <select class="form-control" id="body" name="userBody">
                                        <option value="">선택하세요</option>
                                        <option value="마른">마른</option>
                                        <option value="보통">보통</option>
                                        <option value="근육질">근육질</option>
                                        <option value="통통">통통</option>
                                        <option value="건장">건장</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- 생활 정보 섹션 -->
                    <div class="form-section">
                        <h3><i class="fa fa-coffee"></i> 생활 정보</h3>

                        <div class="row">
                            <div class="col-md-4">
                                <div class="form-group">
                                    <label for="drink" class="required-field">음주</label>
                                    <select class="form-control" id="drink" name="userDrink">
                                        <option value="">선택하세요</option>
                                        <option value="0">전혀 안 함</option>
                                        <option value="1">가끔</option>
                                        <option value="2">자주</option>
                                    </select>
                                </div>
                            </div>

                            <div class="col-md-4">
                                <div class="form-group">
                                    <label for="smoke" class="required-field">흡연</label>
                                    <select class="form-control" id="smoke" name="userSmoke">
                                        <option value="">선택하세요</option>
                                        <option value="0">비흡연</option>
                                        <option value="1">흡연</option>
                                    </select>
                                </div>
                            </div>

                            <div class="col-md-4">
                                <div class="form-group">
                                    <label for="religion" class="required-field">종교</label>
                                    <select class="form-control" id="religion" name="userReligion">
                                        <option value="">선택하세요</option>
                                        <option value="무교">무교</option>
                                        <option value="기독교">기독교</option>
                                        <option value="천주교">천주교</option>
                                        <option value="불교">불교</option>
                                        <option value="이슬람교">이슬람교</option>
                                        <option value="힌두교">힌두교</option>
                                        <option value="기타">기타</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- 성격 및 배경 섹션 -->
                    <div class="form-section">
                        <h3><i class="fa fa-graduation-cap"></i> 성격 및 배경</h3>

                        <div class="row">
                            <div class="col-md-4">
                                <div class="form-group">
                                    <label for="mbti" class="required-field">MBTI</label>
                                    <select class="form-control" id="mbti" name="userMbti">
                                        <option value="">선택하세요</option>
                                        <option value="ISTJ">ISTJ</option>
                                        <option value="ISFJ">ISFJ</option>
                                        <option value="INFJ">INFJ</option>
                                        <option value="INTJ">INTJ</option>
                                        <option value="ISTP">ISTP</option>
                                        <option value="ISFP">ISFP</option>
                                        <option value="INFP">INFP</option>
                                        <option value="INTP">INTP</option>
                                        <option value="ESTP">ESTP</option>
                                        <option value="ESFP">ESFP</option>
                                        <option value="ENFP">ENFP</option>
                                        <option value="ENTP">ENTP</option>
                                        <option value="ESTJ">ESTJ</option>
                                        <option value="ESFJ">ESFJ</option>
                                        <option value="ENFJ">ENFJ</option>
                                        <option value="ENTJ">ENTJ</option>
                                    </select>
                                </div>
                            </div>

                            <div class="col-md-4">
                                <div class="form-group">
                                    <label for="education" class="required-field">학력</label>
                                    <select class="form-control" id="education" name="userEducation">
                                        <option value="">선택하세요</option>
                                        <option value="고등학교 졸업">고등학교 졸업</option>
                                        <option value="전문대 졸업">전문대 졸업</option>
                                        <option value="대학교 재학">대학교 재학</option>
                                        <option value="대학교 졸업">대학교 졸업</option>
                                        <option value="대학원 재학">대학원 재학</option>
                                        <option value="대학원 졸업">대학원 졸업</option>
                                    </select>
                                </div>
                            </div>

                            <div class="col-md-4">
                                <div class="form-group">
                                    <label for="job" class="required-field">직업</label>
                                    <input type="text" class="form-control" id="job" name="userJob"
                                           placeholder="직업을 입력하세요" maxlength="30">
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- 프로필 사진 및 자기소개 섹션 -->
                    <div class="form-section">
                        <h3><i class="fa fa-image"></i> 프로필 사진 및 자기소개</h3>

                        <div class="form-group">
                            <label for="profile">프로필 사진</label>
                            <input type="file" class="form-control" id="profile" name="userProfile" required>
                            <small class="form-text text-muted">JPG, PNG 형식의 파일만 가능합니다. 최대 5MB.</small>
                        </div>

                        <div class="form-group">
                            <label for="description">자기소개</label>
                            <textarea class="form-control" id="description" name="userDescription" rows="5"
                                      maxlength="255" placeholder="자신에 대해 소개해 주세요. (최대 255자)"></textarea>
                        </div>
                    </div>

                    <!-- 제출 버튼 -->
                    <div class="text-center mt-4 mb-5">
                        <button type="submit" class="btn btn-primary btn-lg btn-next-step">
                            <i class="fa fa-check-circle"></i> 회원가입 완료하기
                        </button>
                    </div>
                </form>
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
    </div>
</div>
<!-- END PRE-FOOTER -->

<!-- Load javascripts at bottom -->
<script src="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/plugins/jquery.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/plugins/jquery-migrate.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>

<script src="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/corporate/scripts/layout.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/js/SignUp.js" type="text/javascript"></script>
<script type="text/javascript">
    jQuery(document).ready(function () {
        Layout.init();

        // 폼 유효성 검사
        // $('#userInfoForm').submit(function (e) {
        //     const nickname = $('#nickname').val();
        //     const gender = $('#gender').val();
        //     const birth = $('#birth').val();
        //     const region = $('#region').val();
        //     const profile = $('#profile').val();
        //
        //     console.log('닉네임: ' + nickname);
        //     console.log('성별: ' + gender);
        //     console.log('생일: ' + birth);
        //     console.log('지역: ' + region);
        //     console.log('프로필: ' + profile);
        //
        //
        //     if (!nickname || !gender || !birth || !region || !profile) {
        //         alert('필수 항목을 모두 입력해주세요.');
        //         e.preventDefault();
        //         return false;
        //     }
        //
        //     return true;
        // });
    });
   // 주소 찾기 버튼 눌렀을 때 팝업 띄우기 함수
   //  function openAddressPopup() {
   //      window.open("/API/adressAPI.html", "주소찾기", "width=600,height=500,scrollbars=yes");
   //  }


</script>
<script src="${pageContext.request.contextPath}js/JoinPage.js"></script>
</body>
</html>

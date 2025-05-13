    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ page isELIgnored="false" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

    <!DOCTYPE html>
    <html lang="ko">
    <head>
        <link rel="icon" type="image/png" sizes="16x16"  href="/favicon-32x32.png">
        <meta charset="utf-8">
        <title>정보 수정 페이지</title>

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
        <!-- link to image for socio -->
        <meta property="og:url" content="-CUSTOMER VALUE-">

        <link rel="shortcut icon" href="favicon.ico">

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
        <!-- Page level plugin styles END -->

        <!-- Theme styles START -->
        <link href="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/pages/css/components.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/corporate/css/style.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/pages/css/style-shop.css" rel="stylesheet"
              type="text/css">
        <link href="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/corporate/css/style-responsive.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/corporate/css/themes/red.css" rel="stylesheet"
              id="style-color">
        <link href="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/corporate/css/custom.css" rel="stylesheet">
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
            <a class="site-logo" href="mainPage.do"><img src="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/corporate/img/logos/3.png"
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

    <form action="updateProfile.do" method="POST" role="form" class="form-horizontal form-without-legend">
        <div class="container">
            <h1>정보 변경하기</h1>

            <!-- 자기소개 -->
            <div class="form-group">
                <label class="col-md-2 control-label" for="userDescription">자기소개</label>
                <div class="col-md-8">
                    <textarea class="form-control" id="userDescription" name="userDescription" rows="6" maxlength="200">${userDTO.userDescription}</textarea>
                    <small id="charCount">0 / 200</small>
                </div>
            </div>

            <!-- 닉네임 입력 -->
            <div class="form-group">
                <label class="col-md-2 control-label" for="userNickname">닉네임 <span class="require">*</span></label>
                <div class="col-md-8">
                    <input type="text" id="userNickname" name="userNickname" class="form-control"
                           value= "${userDTO.userNickname}" placeholder="닉네임을 입력하세요" required>
                </div>
            </div>

            <!-- 키 선택 -->
            <div class="form-group">
                <label class="col-md-2 control-label" for="height">키 <span class="require">*</span></label>
                <div class="col-md-8">
                    <input type="number" id="height" name="userHeight" class="form-control"
                           value="${userDTO.userHeight}" placeholder="cm 단위로 입력하세요"
                           step="1" min="100" max="200" onkeydown="return event.keyCode !== 190" required>
                </div>
            </div>

            <!-- 체형 선택 -->
            <div class="form-group">
                <label class="col-md-2 control-label" for="bodyType">체형 <span class="require">*</span></label>
                <div class="col-md-8">
                    <select id="bodyType" name="userBody" class="form-control">
                        <option value="">선택하세요</option>
                        <c:forEach var="bodyType" items="${['마른', '보통', '근육질', '통통', '건장']}">
                            <option value="${bodyType}" <c:if test="${userDTO.userBody == bodyType}">selected</c:if>> ${bodyType}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <!-- 학력 선택 -->
            <div class="form-group">
                <label class="col-md-2 control-label">학력 <span class="require">*</span></label>
                <div class="col-md-8">
                    <select name="userEducation" class="form-control">
                        <option value="">선택하세요</option>
                        <c:forEach var="education" items="${['고등학교 졸업', '전문대', '대학교 재학', '대학교 졸업', '대학원 재학', '대학원 졸업']}">
                            <option value="${education}" <c:if test="${userDTO.userEducation == education}">selected</c:if>>${education}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <!-- 직업 입력 -->
            <div class="form-group">
                <label class="col-md-2 control-label" for="job">직업 <span class="require">*</span></label>
                <div class="col-md-8">
                    <input type="text" id="job" name="userJob" class="form-control"
                           value= "${userDTO.userJob}" placeholder="직업을 입력하세요" required>
                </div>
            </div>

            <!-- 종교 선택 -->
            <div class="form-group">
                <label class="col-md-2 control-label">종교 <span class="require">*</span></label>
                <div class="col-md-8">
                    <select name="userReligion" class="form-control">
                        <option value="">선택하세요</option>
                        <option value="무교" <c:if test="${userDTO.userReligion == '무교'}">selected</c:if>>무교</option>
                        <option value="기독교" <c:if test="${userDTO.userReligion == '기독교'}">selected</c:if>>기독교</option>
                        <option value="천주교" <c:if test="${userDTO.userReligion == '천주교'}">selected</c:if>>천주교</option>
                        <option value="불교" <c:if test="${userDTO.userReligion == '불교'}">selected</c:if>>불교</option>
                        <option value="이슬람교" <c:if test="${userDTO.userReligion == '이슬람교'}">selected</c:if>>이슬람교</option>
                        <option value="힌두교" <c:if test="${userDTO.userReligion == '힌두교'}">selected</c:if>>힌두교</option>
                        <option value="기타" <c:if test="${userDTO.userReligion == '기타'}">selected</c:if>>기타</option>
                    </select>
                </div>
            </div>


            <!-- 지역 선택 -->
            <div class="form-group">
                <label class="col-md-2 control-label">지역 <span class="require">*</span></label>
                <div class="col-md-8">
                    <input type="text" class="form-control"  id="region" name="userRegion" value="${userDTO.userRegion}" readonly>
                    <button type="button" class="btn btn-success mt-4 " onclick="openAddressPopup()">주소 찾기</button>
                </div>
            </div>

            <!-- MBTI 선택 -->
            <div class="form-group">
                <label class="col-md-2 control-label">MBTI <span class="require">*</span></label>
                <div class="col-md-8">
                    <select class="form-control" name="userMbti">
                        <option value="">선택하세요</option>
                        <c:forEach var="mbti" items="${['ISTJ', 'ISFJ', 'INFJ', 'INTJ', 'ISTP', 'ISFP', 'INFP', 'INTP', 'ESTP', 'ESFP', 'ENFP', 'ENTP', 'ESTJ', 'ESFJ', 'ENFJ', 'ENTJ']}">
                            <option value="${mbti}" ${userDTO.userMbti == mbti ? 'selected' : ''}>${mbti}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <!-- 음주 여부 -->
            <div class="form-group">
                <label class="col-md-2 control-label">음주 <span class="require">*</span></label>
                <div class="col-md-8">
                    <select class="form-control" name="userDrink">
                        <option value="">선택하세요</option>
                        <option value="0" ${userDTO.userDrink == 0 ? 'selected' : ''}>전혀 안 함</option>
                        <option value="1" ${userDTO.userDrink == 1 ? 'selected' : ''}>가끔 마심</option>
                        <option value="2" ${userDTO.userDrink == 2 ? 'selected' : ''}>자주 마심</option>
                    </select>
                </div>
            </div>

            <!-- 흡연 여부 -->
            <div class="form-group">
                <label class="col-md-2 control-label">흡연 <span class="require">*</span></label>
                <div class="col-md-8">
                    <select class="form-control" name="userSmoke">
                        <option value="">선택하세요</option>
                        <option value="false" ${userDTO.userSmoke == false ? 'selected' : ''}>비흡연</option>
                        <option value="true" ${userDTO.userSmoke == true ? 'selected' : ''}>흡연</option>
                    </select>
                </div>
            </div>

            <hr>
            <!-- 선호 키 -->
            <div class="form-group">
                <label class="col-md-2 control-label" for="preferenceHeight">선호 키 <span class="require">*</span></label>
                <div class="col-md-8">
                    <input type="number" id="preferenceHeight" name="preferenceHeight" class="form-control"
                           value="${preferenceDTO.preferenceHeight}" placeholder="선호 키를 입력하세요"
                           step="1" min="100" max="200" onkeydown="return event.keyCode !== 190" required>
                </div>
            </div>

            <!-- 선호 체형 -->
            <div class="form-group">
                <label class="col-md-2 control-label">선호 체형 <span class="require">*</span></label>
                <div class="col-md-8">
                    <select name="preferenceBody" class="form-control">
                        <option value="">선택하세요</option>
                        <c:forEach var="body" items="${['마른', '보통', '근육질', '통통', '건장']}">
                            <option value="${body}" ${preferenceDTO.preferenceBody == body ? 'selected' : ''}>${body}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <!-- 선호 나이 -->
            <div class="form-group">
                <label class="col-md-2 control-label" for="preferenceAge">선호 나이 <span class="require">*</span></label>
                <div class="col-md-8">
                    <input type="text" id="preferenceAge" name="preferenceAge" class="form-control"
                           value= "${preferenceDTO.preferenceAge}" placeholder="선호 나이를 입력하세요" required>
                </div>
            </div>

            <!-- 버튼 (제출) -->
            <div class="form-group">
                <div class="col-md-8 col-md-offset-2">
                    <button class="btn btn-primary" type="submit">정보 저장</button>
                </div>
            </div>
        </div>
    </form>

            <div class="row">
                <!-- 자바스크립트 로드 부분 유지 -->
                <script src="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/plugins/jquery.min.js" type="text/javascript"></script>
                <script src="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/plugins/jquery-migrate.min.js" type="text/javascript"></script>
                <script src="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
                <script type="text/javascript">
                    var contextPath = '${pageContext.request.contextPath}';
                </script>
                <!-- 페이지 레벨 자바스크립트 -->
                <script src="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/plugins/fancybox/source/jquery.fancybox.pack.js" type="text/javascript"></script>
                <script src="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/plugins/owl.carousel/owl.carousel.min.js" type="text/javascript"></script>
                <script src="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/corporate/scripts/layout.js" type="text/javascript"></script>

                <script type="text/javascript">
                    jQuery(document).ready(function () {
                        Layout.init();
                        Layout.initOWL();
                        Layout.initTwitter();
                    });

                    // 폼 제출 전에 모든 필수 필드가 채워졌는지 확인하는 함수
                    document.addEventListener('DOMContentLoaded', function() {
                        // 폼 요소 가져오기
                        const form = document.querySelector('form');

                        if (form) {
                            // 폼 제출 이벤트 리스너 추가
                            form.addEventListener('submit', function(event) {
                                // 모든 필수 입력 필드 선택 (별표 표시가 있는 필드)
                                const requiredFields = document.querySelectorAll('input[required], select[required], textarea[required], [name="preferenceHeight"], [name="preferenceAge"]');

                                // 또는 명시적으로 필드 선택
                                const specificFields = [
                                    document.querySelector('[name="preferenceHeight"]'),
                                    document.querySelector('[name="preferenceAge"]')
                                ];

                                let allFieldsValid = true;
                                let firstInvalidField = null;

                                // 모든 필수 필드 검사
                                requiredFields.forEach(function(field) {
                                    if (!field.value || field.value.trim() === '') {
                                        allFieldsValid = false;

                                        // 빨간 테두리 스타일 적용 (인라인 스타일로 우선순위 높임)
                                        field.setAttribute('style', 'border: 2px solid red !important; box-shadow: 0 0 5px red !important;');

                                        if (!firstInvalidField) {
                                            firstInvalidField = field;
                                        }
                                    }
                                });

                                // 특정 필드 명시적 검사 (위의 선택자가 작동하지 않을 경우)
                                specificFields.forEach(function(field) {
                                    if (field && (!field.value || field.value.trim() === '')) {
                                        allFieldsValid = false;

                                        // 빨간 테두리 스타일 적용 (인라인 스타일로 우선순위 높임)
                                        field.setAttribute('style', 'border: 2px solid red !important; box-shadow: 0 0 5px red !important;');

                                        if (!firstInvalidField) {
                                            firstInvalidField = field;
                                        }
                                    }
                                });

                                // 유효성 검사 실패 시 폼 제출 방지
                                if (!allFieldsValid) {
                                    event.preventDefault();
                                    alert('모든 필수 항목을 입력해주세요.');

                                    // 첫 번째 오류 필드로 포커스 이동
                                    if (firstInvalidField) {
                                        firstInvalidField.focus();
                                    }
                                }
                            });

                            // 입력 필드에 포커스가 갈 때 빨간 테두리 제거
                            const allFields = document.querySelectorAll('input, select, textarea');
                            allFields.forEach(function(field) {
                                field.addEventListener('focus', function() {
                                    this.removeAttribute('style');
                                });

                                // 입력 시에도 테두리 제거
                                field.addEventListener('input', function() {
                                    this.removeAttribute('style');
                                });
                            });
                        }
                    });

                </script>
            </div>
        </div>
    </div>
    <script src="${pageContext.request.contextPath}js/MyPageEdit.js"></script>
    </body>
    </html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="icon" type="image/png" sizes="16x16"  href="/favicon-32x32.png">
    <meta charset="utf-8">
    <title>선호 취향 입력</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

    <meta content="Next Love 선호 취향 입력" name="description">
    <meta content="선호취향, 데이팅, Next Love" name="keywords">
    <meta content="Next Love" name="author">

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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

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
        <a class="site-logo" href="indexPage.do"><img
                src="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/corporate/img/logos/3.png"
                alt="Next Love"></a>
        <a href="javascript:void(0);" class="mobi-toggler"><i class="fa fa-bars"></i></a>
    </div>
</div>
<!-- Header END -->

<div class="main">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <h1>선호 취향 입력</h1>

                <div class="progress-container">
                    <div class="progress">
                        <div class="progress-bar bg-success" role="progressbar" style="width: 100%;" aria-valuenow="100"
                             aria-valuemin="0" aria-valuemax="100">3/3 단계
                        </div>
                    </div>
                    <div class="text-center mt-2">
                        <small>
                            마지막 단계입니다. 선호하는 상대방의 조건을 입력해 주세요.
                        </small>
                    </div>
                </div>

                <form action="userPreference.do" method="post" id="preferenceForm">
                    <!-- 히든 필드: 이메일 -->
                    <input type="hidden" name="userEmail" value="${userEmail}">

                    <!-- 선호 취향 섹션 -->
                    <div class="form-section">
                        <h3><i class="fa fa-heart"></i> 선호하는 상대방 조건</h3>

                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label for="preferenceHeight" class="required-field">선호 키 (cm)</label>
                                    <input type="number" class="form-control" id="preferenceHeight"
                                           name="preferenceHeight"
                                           min="140" max="220" value="${preferenceDTO.preferenceHeight}"
                                           placeholder="선호하는 키를 입력하세요" required>
                                    <small class="form-text text-muted">예: 170 (단위: cm)</small>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label for="preferenceBody" class="required-field">선호 체형</label>
                                    <select class="form-control" id="preferenceBody" name="preferenceBody" required>
                                        <option value="">선택하세요</option>
                                        <option value="마른" ${preferenceDTO.preferenceBody == '마른' ? 'selected' : ''}>
                                            마른
                                        </option>
                                        <option value="보통" ${preferenceDTO.preferenceBody == '보통' ? 'selected' : ''}>
                                            보통
                                        </option>
                                        <option value="근육질" ${preferenceDTO.preferenceBody == '근육질' ? 'selected' : ''}>
                                            근육질
                                        </option>
                                        <option value="통통" ${preferenceDTO.preferenceBody == '통통' ? 'selected' : ''}>
                                            통통
                                        </option>
                                        <option value="건장" ${preferenceDTO.preferenceBody == '건장' ? 'selected' : ''}>
                                            건장
                                        </option>
                                    </select>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label for="preferenceAge" class="required-field">선호 나이</label>
                                    <input type="text" class="form-control" id="preferenceAge" name="preferenceAge"
                                           value="${preferenceDTO.preferenceAge}" placeholder="선호하는 나이 범위를 입력하세요"
                                           required>
                                    <small class="form-text text-muted">예: 25-35 (범위로 입력)</small>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- 제출 버튼 -->
                    <div class="text-center mt-4 mb-5">
                        <button type="submit" class="btn btn-primary btn-lg btn-next-step">
                            <i class="fa fa-check-circle"></i> 선호 취향 저장하기
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
<!-- END PRE-FOOTER -->

<!-- Load javascripts at bottom -->
<script src="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/plugins/jquery.min.js"
        type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/plugins/jquery-migrate.min.js"
        type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/plugins/bootstrap/js/bootstrap.min.js"
        type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/plugins/jquery-slimscroll/jquery.slimscroll.min.js"
        type="text/javascript"></script>

<script src="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/corporate/scripts/layout.js"
        type="text/javascript"></script>
<script type="text/javascript">
    jQuery(document).ready(function () {
        Layout.init();

        // 폼 유효성 검사
        $('#preferenceForm').submit(function (e) {
            const preferenceHeight = $('#preferenceHeight').val();
            const preferenceBody = $('#preferenceBody').val();
            const preferenceAge = $('#preferenceAge').val();

            if (!preferenceHeight || !preferenceBody || !preferenceAge) {
                alert('필수 항목을 모두 입력해주세요.');
                console.log(preferenceHeight, preferenceAge, preferenceBody);
                e.preventDefault();
                return false;
            }

            // 키 유효성 검사
            if (preferenceHeight < 140 || preferenceHeight > 220) {
                alert('선호 키는 140cm에서 220cm 사이로 입력해주세요.');
                e.preventDefault();
                return false;
            }

            // 나이 형식 검사 (예: "25-35")
            const agePattern = /^\d+(-\d+)?$/;
            if (!agePattern.test(preferenceAge)) {
                alert('선호 나이는 숫자 또는 숫자-숫자 형식으로 입력해주세요. 예: 25 또는 25-35');
                e.preventDefault();
                return false;
            }

            return true;
        });
    });
</script>
</body>
</html>

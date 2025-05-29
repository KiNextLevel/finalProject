<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <link rel="icon" type="image/png" sizes="16x16"  href="/favicon-32x32.png">
    <meta charset="utf-8">
    <title>이벤트 수정 페이지</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

    <!-- 파비콘 -->
    <link rel="shortcut icon" href="favicon.ico">

    <!-- 폰트 -->
    <link href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700|PT+Sans+Narrow|Source+Sans+Pro:200,300,400,600,700,900&amp;subset=all" rel="stylesheet" type="text/css">

    <!-- 글로벌 스타일 -->
    <link href="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- 페이지 레벨 플러그인 스타일 -->
    <link href="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/plugins/fancybox/source/jquery.fancybox.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/plugins/owl.carousel/assets/owl.carousel.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css">

    <!-- 테마 스타일 -->
    <link href="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/pages/css/components.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/corporate/css/style.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/pages/css/style-shop.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/corporate/css/style-responsive.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/corporate/css/themes/red.css" rel="stylesheet" id="style-color">
    <link href="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/corporate/css/custom.css" rel="stylesheet">

    <!-- 커스텀 스타일 -->
    <style>
        body {
            background-color: #f5f7fa;
            font-family: 'Open Sans', sans-serif;
        }

        .report-container {
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 0 15px rgba(0,0,0,0.1);
            padding: 30px;
            margin-bottom: 30px;
        }

        .report-title {
            color: #e84d1c;
            margin-bottom: 25px;
            font-weight: 600;
            border-bottom: 2px solid #e84d1c;
            padding-bottom: 10px;
        }

        .report-subtitle {
            color: #3e4d5c;
            margin-bottom: 20px;
            font-weight: 500;
        }

        .form-group {
            margin-bottom: 25px;
        }

        .control-label {
            color: #3e4d5c;
            font-weight: 600;
            margin-bottom: 10px;
        }

        .form-control {
            border: 1px solid #ddd;
            border-radius: 4px;
            padding: 10px 15px;
            height: auto;
            transition: border 0.3s;
        }

        .form-control:focus {
            border-color: #e84d1c;
            outline: none;
            box-shadow: none;
        }

        textarea.form-control {
            height: 150px;
            resize: vertical;
        }

        .require {
            color: #e84d1c;
            margin-left: 3px;
        }

        .btn-primary {
            background-color: #e84d1c;
            border-color: #e84d1c;
            color: white;
            padding: 10px 25px;
            border-radius: 4px;
            font-weight: 600;
            transition: background-color 0.3s;
        }

        .btn-primary:hover {
            background-color: #d13a0a;
            border-color: #d13a0a;
        }

        .btn-secondary {
            background-color: #3e4d5c;
            border-color: #3e4d5c;
            color: white;
            padding: 10px 25px;
            border-radius: 4px;
            font-weight: 600;
            transition: background-color 0.3s;
        }

        .btn-secondary:hover {
            background-color: #2d3a46;
            border-color: #2d3a46;
        }

        .event-info {
            background-color: #f5f7fa;
            padding: 20px;
            border-radius: 4px;
            margin-top: 20px;
            border-left: 4px solid #e84d1c;
        }

        .event-info h4 {
            color: #3e4d5c;
            margin-bottom: 15px;
        }

        .event-info p {
            font-size: 13px;
            line-height: 1.6;
            color: #666;
        }

        .file-upload {
            position: relative;
            overflow: hidden;
            margin-top: 10px;
        }

        .file-upload input[type="file"] {
            position: absolute;
            top: 0;
            right: 0;
            min-width: 100%;
            min-height: 100%;
            font-size: 100px;
            text-align: right;
            filter: alpha(opacity=0);
            opacity: 0;
            outline: none;
            cursor: pointer;
            display: block;
        }

        .file-upload-btn {
            display: inline-block;
            padding: 8px 15px;
            background: #f5f7fa;
            border: 1px solid #ddd;
            border-radius: 4px;
            color: #3e4d5c;
            cursor: pointer;
            transition: all 0.3s;
        }

        .file-upload-btn:hover {
            background: #e9ecef;
        }

        .file-upload-text {
            margin-left: 10px;
            color: #777;
        }

        /* 반응형 디자인 */
        @media (max-width: 767px) {
            .report-container {
                padding: 20px;
            }
        }
    </style>
</head>

<body class="ecommerce">
<!-- 상단 바 시작 -->
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
        <a class="site-logo" href="mainPage.do"><img src="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/corporate/img/logos/3.png" alt="mainPage"></a>
        <a href="javascript:void(0);" class="mobi-toggler"><i class="fa fa-bars"></i></a>
    </div>
</div>
<!-- Header END -->

<!-- 메인 컨텐츠 시작 -->
<div class="main">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <!-- 이벤트 수정 시작 -->
                <div class="report-container">
                    <h2 class="report-title">이벤트 수정</h2>

                    <form action="adminModifyBoard.do" method="POST" role="form" class="form-horizontal">
                        <input type="hidden" name="boardNum" value="${data.boardNumber}">

                        <div class="form-group">
                            <label class="col-lg-2 control-label" for="boardTitle">제목<span class="require">*</span></label>
                            <div class="col-lg-10">
                                <input type="text" id="boardTitle" name="boardTitle" class="form-control" value="${data.boardTitle}" required>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-lg-2 control-label" for="boardContent">내용<span class="require">*</span></label>
                            <div class="col-lg-10">
                                <textarea id="boardContent" name="boardContent" class="form-control" rows="6" required>${data.boardContent}</textarea>
                            </div>
                        </div>

                        <!--<div class="form-group">
                            <label class="col-lg-2 control-label" for="eventDate">이벤트 날짜<span class="require">*</span></label>
                            <div class="col-lg-4">
                                <input type="date" id="eventDate" name="eventDate" class="form-control" required>
                            </div>
                        </div>-->

                        <div class="form-group">
                            <label class="col-lg-2 control-label" for="boardLimit">최대 참여 인원<span class="require">*</span></label>
                            <div class="col-lg-4">
                                <input type="number" id="boardLimit" name="boardLimit" class="form-control" value="${data.boardLimit}" min="1" required>
                            </div>
                        </div>

                        <!--<div class="form-group">
                            <label class="col-lg-2 control-label" for="eventImage">이벤트 이미지</label>
                            <div class="col-lg-10">
                                <div class="file-upload">
                                    <span class="file-upload-btn">파일 선택</span>
                                    <span class="file-upload-text">선택된 파일 없음</span>
                                    <input type="file" id="eventImage" name="eventImage">
                                </div>
                                <p class="help-block">권장 크기: 1200 x 600px, 최대 5MB</p>
                            </div>
                        </div>-->

                        <div class="event-info">
                            <h4>이벤트 등록 안내</h4>
                            <p>
                                이벤트 내용은 정확하고 자세하게 작성해 주세요. 참여자들이 이벤트 내용을 쉽게 이해할 수 있도록 명확한 설명과 함께
                                참여 방법, 혜택, 주의사항 등을 포함하는 것이 좋습니다. 이벤트 기간과 최대 참여 인원은 신중하게 설정해 주세요.
                            </p>
                        </div>

                        <div class="form-group">
                            <div class="col-lg-offset-2 col-lg-10">
                                <button class="btn btn-primary" type="submit">
                                    <i class="fa fa-check"></i> 이벤트 수정하기
                                </button>
                                <a href="javascript:history.back()" class="btn btn-secondary">
                                    <i class="fa fa-arrow-left"></i> 취소
                                </a>
                            </div>
                        </div>
                    </form>
                </div>
                <!-- 이벤트 수정 끝 -->
            </div>
        </div>
    </div>
</div>
<!-- 메인 컨텐츠 끝 -->

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
                    Notion: <a href="https://sheer-sundial-325.notion.site/1b5c9677015480c4a9ebfba7bbc63185">Notion</a><br>
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
</div>
<!-- END PRE-FOOTER -->

<!-- 자바스크립트 -->
<script src="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/plugins/jquery.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/plugins/jquery-migrate.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/plugins/fancybox/source/jquery.fancybox.pack.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/plugins/owl.carousel/owl.carousel.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/corporate/scripts/layout.js" type="text/javascript"></script>

<script type="text/javascript">
    jQuery(document).ready(function() {
        Layout.init();
        Layout.initOWL();
        Layout.initTwitter();

        // // 파일 업로드 텍스트 변경
        // $('#eventImage').change(function() {
        //     var fileName = $(this).val().split('\\').pop();
        //     if (fileName) {
        //         $('.file-upload-text').text(fileName);
        //     } else {
        //         $('.file-upload-text').text('선택된 파일 없음');
        //     }
        // });
        //
        // // 현재 날짜 설정
        // var today = new Date();
        // var dd = String(today.getDate()).padStart(2, '0');
        // var mm = String(today.getMonth() + 1).padStart(2, '0');
        // var yyyy = today.getFullYear();
        // today = yyyy + '-' + mm + '-' + dd;
        // document.getElementById('eventDate').value = today;
    });
</script>
</body>
</html>

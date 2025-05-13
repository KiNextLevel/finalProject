<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <link rel="icon" type="image/png" sizes="16x16"  href="/favicon-32x32.png">
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>결제 확인 페이지</title>

    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/target-free-admin-template/assets/materialize/css/materialize.min.css" media="screen,projection"/>
    <!-- Bootstrap Styles-->
    <link href="${pageContext.request.contextPath}/target-free-admin-template/assets/css/bootstrap.css" rel="stylesheet"/>
    <!-- FontAwesome Styles-->
    <link href="${pageContext.request.contextPath}/target-free-admin-template/assets/css/font-awesome.css" rel="stylesheet"/>
    <!-- Morris Chart Styles-->
    <link href="${pageContext.request.contextPath}/target-free-admin-template/assets/js/morris/morris-0.4.3.min.css" rel="stylesheet"/>
    <!-- Custom Styles-->
    <link href="${pageContext.request.contextPath}/target-free-admin-template/assets/css/custom-styles.css" rel="stylesheet"/>
    <!-- Google Fonts-->
    <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/target-free-admin-template/assets/js/Lightweight-Chart/cssCharts.css">
</head>
<body>
<div id="wrapper">
    <nav class="navbar navbar-default top-navbar" role="navigation">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle waves-effect waves-dark" data-toggle="collapse" data-target=".sidebar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand waves-effect waves-dark" href="adminPage.do"><i class="large material-icons">track_changes</i> <strong>Next Love</strong></a>

            <div id="sideNav" href=""><i class="material-icons dp48">toc</i></div>
        </div>

        <ul class="nav navbar-top-links navbar-right">
            <li><a class="dropdown-button waves-effect waves-dark" href="mainPage.do"> <b>메인 페이지</b></a></li>
            <li><a class="dropdown-button waves-effect waves-dark" href="logout.do"><i class="fa fa-user fa-fw"></i> <b>Log out</b></a></li>
            <li></li>
        </ul>
    </nav>
    <!--/. NAV TOP  -->
    <nav class="navbar-default navbar-side" role="navigation">
        <div class="sidebar-collapse">
            <ul class="nav" id="main-menu">

                <li>
                    <a class="waves-effect waves-dark" href="boardPage.do"><i class="fa fa-dashboard"></i> 이벤트 페이지</a>
                </li>
                <li>
                    <a href="adminPaymentListPage.do" class="waves-effect waves-dark"><i class="fa fa-desktop"></i> 결제 내역 페이지</a>
                </li>
                <li>
                    <a href="adminReportPage.do" class="waves-effect waves-dark"><i class="fa fa-bar-chart-o"></i> 신고 회원 관리 페이지</a>
                </li>
            </ul>

        </div>

    </nav>
    <!-- /. NAV SIDE  -->
    <div id="page-wrapper">
        <div class="header">
            <h1 class="page-header">
                결제 내역
            </h1>
            <ol class="breadcrumb">
                <li><a href="#">Home</a></li>
                <li><a href="#">Tables</a></li>
                <li class="active">Data</li>
            </ol>

        </div>

        <div id="page-inner">

            <div class="row">
                <div class="col-md-12">
                    <!-- Advanced Tables -->
                    <div class="card">
                        <div class="card-action">
                            Advanced Tables
                        </div>
                        <div class="card-content">
                            <div class="table-responsive">                                            <!-- 이게 정렬 -->
                                <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                    <thead>
                                    <tr>
                                        <th>결제 날짜</th>
                                        <th>이메일</th>
                                        <th>결제 금액</th>
                                        <th>결제 상품</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="data" items="${datas}">
                                        <tr class="odd gradeX">
                                            <td>${data.paymentDate}</td>
                                            <td>${data.userEmail}</td>
                                            <td>${data.paymentPrice}</td>
                                            <td class="center">${data.productName}</td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>

                        </div>
                    </div>
                    <!--End Advanced Tables -->
                </div>
            </div>
            <!-- /. ROW  -->

        </div>
        <!-- /. PAGE INNER  -->
    </div>
    <!-- /. PAGE WRAPPER  -->
    <!-- /. WRAPPER  -->
    <!-- JS Scripts-->


    <!-- jQuery Js -->
    <script src="${pageContext.request.contextPath}/target-free-admin-template/assets/js/jquery-1.10.2.js"></script>

    <!-- Bootstrap Js -->
    <script src="${pageContext.request.contextPath}/target-free-admin-template/assets/js/bootstrap.min.js"></script>

    <script src="${pageContext.request.contextPath}/target-free-admin-template/assets/materialize/js/materialize.min.js"></script>

    <!-- Metis Menu Js -->
    <script src="${pageContext.request.contextPath}/target-free-admin-template/assets/js/jquery.metisMenu.js"></script>
    <!-- Morris Chart Js -->
    <script src="${pageContext.request.contextPath}/target-free-admin-template/assets/js/morris/raphael-2.1.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/target-free-admin-template/assets/js/morris/morris.js"></script>


    <script src="${pageContext.request.contextPath}/target-free-admin-template/assets/js/easypiechart.js"></script>
    <script src="${pageContext.request.contextPath}/target-free-admin-template/assets/js/easypiechart-data.js"></script>

    <script src="${pageContext.request.contextPath}/target-free-admin-template/assets/js/Lightweight-Chart/jquery.chart.js"></script>
    <!-- DATA TABLE SCRIPTS -->
    <script src="${pageContext.request.contextPath}/target-free-admin-template/assets/js/dataTables/jquery.dataTables.js"></script>
    <script src="${pageContext.request.contextPath}/target-free-admin-template/assets/js/dataTables/dataTables.bootstrap.js"></script>
    <script>
        $(document).ready(function () {
            $('#dataTables-example').dataTable({
                "order": [[0, "desc"]], // 첫 번째 열(결제 날짜) 내림차순
                "language": {
                    "zeroRecords": "일치하는 검색 결과가 없습니다.",  // 필터링 후 데이터가 없을 때 메시지
                    "emptyTable": "테이블에 데이터가 없습니다.",   // 테이블이 완전히 비어 있을 때 메시지
                    "info": "총 _TOTAL_건 중 _START_ - _END_건 표시", // 페이지 정보
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
    <!-- Custom Js -->
    <script src="${pageContext.request.contextPath}/target-free-admin-template/assets/js/custom-scripts.js"></script>

</div>
</body>

</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<link rel="icon" type="image/png" sizes="16x16"  href="/favicon-32x32.png">
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<title>관리자 메인 페이지</title>
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/target-free-admin-template/assets/materialize/css/materialize.min.css" media="screen,projection" />
	<!-- Bootstrap Styles-->
	<link href="${pageContext.request.contextPath}/target-free-admin-template/assets/css/bootstrap.css" rel="stylesheet" />
	<!-- FontAwesome Styles-->
	<link href="${pageContext.request.contextPath}/target-free-admin-template/assets/css/font-awesome.css" rel="stylesheet" />
	<!-- Morris Chart Styles-->
	<link href="${pageContext.request.contextPath}/target-free-admin-template/assets/js/morris/morris-0.4.3.min.css" rel="stylesheet" />
	<!-- Custom Styles-->
	<link href="${pageContext.request.contextPath}/target-free-admin-template/assets/css/custom-styles.css" rel="stylesheet" />
	<!-- Google Fonts-->
	<link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css' />
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
			<a class="navbar-brand waves-effect waves-dark" href="/adminPage.do"><i class="large material-icons">track_changes</i> <strong>Next Love</strong></a>

			<div id="sideNav" href=""><i class="material-icons dp48">toc</i></div>
		</div>

		<ul class="nav navbar-top-links navbar-right">
			<li><a class="dropdown-button waves-effect waves-dark" href="/mainPage.do"> <b>메인 페이지</b></a></li>
			<li><a class="dropdown-button waves-effect waves-dark" href="/logout.do"><i class="fa fa-user fa-fw"></i> <b>Log out</b></a></li>
			<li></li>
		</ul>
	</nav>
	<!--/. NAV TOP  -->
	<nav class="navbar-default navbar-side" role="navigation">
		<div class="sidebar-collapse">
			<ul class="nav" id="main-menu">

				<li>
					<a class="waves-effect waves-dark" href="/boardPage.do"><i class="fa fa-dashboard"></i> 이벤트 페이지</a>
				</li>
				<li>
					<a href="/adminPaymentListPage.do" class="waves-effect waves-dark"><i class="fa fa-desktop"></i> 결제 내역 페이지</a>
				</li>
				<li>
					<a href="/adminReportPage.do" class="waves-effect waves-dark"><i class="fa fa-bar-chart-o"></i> 신고 회원 관리 페이지</a>
				</li>
			</ul>

		</div>
	</nav>
	<!-- /. NAV SIDE  -->

	<div id="page-wrapper">
		<div class="header">
			<h1 class="page-header">
				관리자 페이지
			</h1>
			<ol class="breadcrumb">
				<li><a href="#">Home</a></li>
				<li><a href="#">Dashboard</a></li>
				<li class="active">Data</li>
			</ol>

		</div>
		<div id="page-inner">

			<div class="dashboard-cards">
				<div class="row">
					<div class="col-xs-12 col-sm-6 col-md-3">

						<div class="card horizontal cardIcon waves-effect waves-dark">
							<div class="card-image red">
								<i class="material-icons dp48">import_export</i>
							</div>
							<div class="card-stacked red">
								<div class="card-content premium-membership">
									<h3></h3>
								</div>
								<div class="card-action">
									<strong>프리미엄 멤버쉽</strong>
								</div>
							</div>
						</div>

					</div>
					<div class="col-xs-12 col-sm-6 col-md-3">

						<div class="card horizontal cardIcon waves-effect waves-dark">
							<div class="card-image orange">
								<i class="material-icons dp48">shopping_cart</i>
							</div>
							<div class="card-stacked orange">
								<div class="card-content basic-package">
									<h3></h3>
								</div>
								<div class="card-action">
									<strong>기본 패키지(토큰 1개)</strong>
								</div>
							</div>
						</div>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-3">

						<div class="card horizontal cardIcon waves-effect waves-dark">
							<div class="card-image blue">
								<i class="material-icons dp48">equalizer</i>
							</div>
							<div class="card-stacked blue">
								<div class="card-content popular-package">
									<h3></h3>
								</div>
								<div class="card-action">
									<strong>인기 패키지(토큰 5개)</strong>
								</div>
							</div>
						</div>

					</div>
					<div class="col-xs-12 col-sm-6 col-md-3">

						<div class="card horizontal cardIcon waves-effect waves-dark">
							<div class="card-image green">
								<i class="material-icons dp48">supervisor_account</i>
							</div>
							<div class="card-stacked green">
								<div class="card-content premium-package">
									<h3></h3>
								</div>
								<div class="card-action">
									<strong>프리미엄 패키지(토큰 10개)</strong>
								</div>
							</div>
						</div>
					</div>

					<div class="col-xs-12 col-sm-6 col-md-3">
						<div class="card horizontal cardIcon waves-effect waves-dark">
							<div class="card-image red">
								<i class="material-icons dp48">import_export</i>
							</div>
							<div class="card-stacked red">
								<div class="card-content totalUser">
									<h3></h3>
								</div>
								<div class="card-action">
									<strong>전체 회원 수</strong>
								</div>
							</div>
						</div>
					</div>

					<div class="col-xs-12 col-sm-6 col-md-3">
						<div class="card horizontal cardIcon waves-effect waves-dark">
							<div class="card-image red">
								<i class="material-icons dp48">import_export</i>
							</div>
							<div class="card-stacked red">
								<div class="card-content paidUser">
									<h3></h3>
								</div>
								<div class="card-action">
									<strong>결제 한 회원 수</strong>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- /. ROW  -->
			<div class="row">
				<div class="col-xs-12 col-sm-12 col-md-7">
					<div class="cirStats">
						<div class="row">
							<div class="col-xs-12 col-sm-6 col-md-6">
								<div class="card-panel text-center">
									<h4>Profit</h4>
									<div class="easypiechart" id="easypiechart-blue" data-percent="82" ><span class="percent">82%</span>
									</div>
								</div>
							</div>
							<div class="col-xs-12 col-sm-6 col-md-6">
								<div class="card-panel text-center">
									<h4>No. of Visits</h4>
									<div class="easypiechart" id="easypiechart-red" data-percent="46" ><span class="percent">46%</span>
									</div>
								</div>
							</div>
							<div class="col-xs-12 col-sm-6 col-md-6">
								<div class="card-panel text-center">
									<h4>Customers</h4>
									<div class="easypiechart" id="easypiechart-teal" data-percent="84" ><span class="percent">84%</span>
									</div>
								</div>
							</div>
							<div class="col-xs-12 col-sm-6 col-md-6">
								<div class="card-panel text-center">
									<h4>Sales</h4>
									<div class="easypiechart" id="easypiechart-orange" data-percent="55" ><span class="percent">55%</span>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div><!--/.row-->
				<div class="col-xs-12 col-sm-12 col-md-5">
					<div class="row">
						<div class="col-xs-12">
							<div class="card">
								<div class="card-image donutpad">
									<div id="morris-donut-chart"></div>
								</div>
								<div class="card-action">
									<b>Donut Chart Example</b>
								</div>
							</div>
						</div>
					</div>
				</div><!--/.row-->
			</div>

			<!--일별 매출-->
			<canvas id="morris-line-chart"></canvas>

			<!--주별 매출-->
			<canvas id="week-bar-chart"></canvas>

			<!--월별 매출-->
			<canvas id="month-bar-chart"></canvas>

			<div class="row">


				<!--line chart-->
				<div class="col-md-7">
					<div class="card">
						<div class="card-image">
							<div id="morris-bar-chart"></div>
						</div>
						<div class="card-action">
							<b>Bar Chart</b>
						</div>
					</div>
				</div>

			</div>



			<div class="row">
				<div class="col-xs-12">
					<div class="card">
						<div class="card-image">
							<div id="morris-area-chart"></div>
						</div>
						<div class="card-action">
							<b>Area Chart</b>
						</div>
					</div>
				</div>
			</div>
<%--			<div class="row">--%>
<%--				<div class="col-md-12">--%>

<%--				</div>--%>
<%--			</div>--%>
			<!-- /. ROW  -->

			<div class="row">
				<div class="col-md-4 col-sm-12 col-xs-12">
					<div class="card"><div class="card-action">
						<b>Tasks Panel</b>
					</div>
						<div class="card-image">
							<div class="collection">
								<a href="#!" class="collection-item">Red<span class="new badge red" data-badge-caption="red">4</span></a>
								<a href="#!" class="collection-item">Blue<span class="new badge blue" data-badge-caption="blue">4</span></a>
								<a href="#!" class="collection-item"><span class="badge">1</span>Alan</a>
								<a href="#!" class="collection-item"><span class="new badge">4</span>Alan</a>
								<a href="#!" class="collection-item">Alan<span class="new badge blue" data-badge-caption="blue">4</span></a>
								<a href="#!" class="collection-item"><span class="badge">14</span>Alan</a>
								<a href="#!" class="collection-item">Custom Badge Captions<span class="new badge" data-badge-caption="custom caption">4</span></a>
								<a href="#!" class="collection-item">Custom Badge Captions<span class="badge" data-badge-caption="custom caption">4</span></a>
							</div>
						</div>

					</div>

				</div>
				<div class="col-md-8 col-sm-12 col-xs-12">
					<div class="card">
						<div class="card-action">
							<b>최근 회원가입 한 회원</b>
						</div>
						<div class="card-image">
							<ul class="collection">
								<li class="users">
									<i class="material-icons circle green">track_changes</i>
									<span class="title">Title</span>
									<p>First Line <br>
										Second Line
									</p>
									<a href="#!" class="secondary-content"></a>
								</li>
								<li class="users">
									<i class="material-icons circle">folder</i>
									<span class="title">Title</span>
									<p>First Line <br>
										Second Line
									</p>
									<a href="#!" class="secondary-content"></a>
								</li>
								<li class="users">
									<i class="material-icons circle green">track_changes</i>
									<span class="title">Title</span>
									<p>First Line <br>
										Second Line
									</p>
									<a href="#!" class="secondary-content"></a>
								</li>
								<li class="users">
									<i class="material-icons circle red">play_arrow</i>
									<span class="title">Title</span>
									<p>First Line <br>
										Second Line
									</p>
									<a href="#!" class="secondary-content"></a>
								</li>
							</ul>
						</div>
					</div>

				</div>
			</div>
			<!-- /. ROW  -->
			<div class="fixed-action-btn horizontal click-to-toggle">
				<a class="btn-floating btn-large red">
					<i class="material-icons">menu</i>
				</a>
				<ul>
					<li><a class="btn-floating red"><i class="material-icons">track_changes</i></a></li>
					<li><a class="btn-floating yellow darken-1"><i class="material-icons">format_quote</i></a></li>
					<li><a class="btn-floating green"><i class="material-icons">publish</i></a></li>
					<li><a class="btn-floating blue"><i class="material-icons">attach_file</i></a></li>
				</ul>
			</div>

			<footer><p>All right reserved. Template by: <a href="https://webthemez.com/admin-template/">WebThemez.com</a></p>


			</footer>
		</div>
		<!-- /. PAGE INNER  -->
	</div>
	<!-- /. PAGE WRAPPER  -->
</div>
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

<!-- Custom Js -->
<script src="${pageContext.request.contextPath}/target-free-admin-template/assets/js/custom-scripts.js"></script>
<!--chart.js 최신버전-->
<script src="https://cdn.jsdelivr.net/npm/chart.js@3.7.1/dist/chart.min.js"></script>

<script>
	//상품별 매출
	$(document).ready(function(){
		$.ajax({
			url: '/getProductPrice.do',
			type: 'GET',
			dataType: 'json',
			success: function(data) {
				console.log(data); // 확인용
				data.result.forEach(function(item) {
					let productNum = item.productNumber;
					let totalSales = item.productPrice.toLocaleString(); // 1000단위 콤마 찍기

					if (productNum === 1) {
						$(".premium-membership h3").text(totalSales);
					} else if (productNum === 2) {
						$(".basic-package h3").text(totalSales);
					} else if (productNum === 3) {
						$(".popular-package h3").text(totalSales);
					} else if (productNum === 4) {
						$(".premium-package h3").text(totalSales);
					}
				});
			},
			error: function(xhr, status, error) {
				console.error("매출 조회 실패:", error);
			}
		});
	});

	//가입한 회원 최신순 4명
	$(document).ready(function() {
		$.ajax({
			url: '/getFourUser.do',
			type: 'GET',
			dataType: 'json',
			success: function(data) {
				console.log(data); // 확인용

				// .users 모두 순회
				const items = $('.users');
				data.users.forEach(function(item, index) {
					if (index < items.length) {
						const $currentItem = $(items[index]);
						$currentItem.find('.title').text("이름: "+item.userName);
						$currentItem.find('p').html("이메일: "+item.userEmail + "<br>보유 토큰: " + item.userToken);
					}
				});
			},
			error: function(xhr, status, error) {
				console.error("회원 조회 실패:", error);
			}
		});
	});

	//일별 매출
	$(document).ready(function() {
		$.ajax({
			url: '/getDayPrice.do',
			type: 'GET',
			dataType: 'json',
			success: function(data) {
				console.log(data.dayResult); // 로그

				var labels = [];	//날짜 담을 배열
				var salesData = [];	//매출액 담을 배열

				data.dayResult.forEach(function(item) {
					labels.push(item.paymentDate); // 날짜
					salesData.push(item.productPrice); // 매출액
				});

				// Chart.js로 라인 차트 그리기
				// ctx: 위치
				// id가 morris-line-chart인 canvas 태그에 그림
				var ctx = document.getElementById('morris-line-chart').getContext('2d');
				var myLineChart = new Chart(ctx, {
					type: 'line',
					data: {
						labels: labels,
						datasets: [{
							label: '일별 매출',
							data: salesData,
							backgroundColor: 'rgba(75, 192, 192, 0.2)', // 채우기 색
							borderColor: 'rgba(75, 192, 192, 1)', // 선 색
							borderWidth: 2,
							fill: false,
							tension: 0.3 // 곡선 부드러운 정도
						}]
					},
					options: {
						responsive: true,	//크기 자동 조절(false는 고정)
						scales: {
							y: {
								beginAtZero: true	//y축 시작을 0부터
							}
						},
						ticks: {
							autoSkip: true, // 자동으로 스킵
							maxTicksLimit: 10 // 최대 10개만 표시
						}
					}
				});
			},
			error: function(xhr, status, error) {
				console.error("일별 매출 조회 실패:", error);
			}
		});
	});

	//주별 매출
	$(document).ready(function() {
		$.ajax({
			url: '/getWeekPrice.do',
			type: 'GET',
			dataType: 'json',
			success: function(data) {
				console.log(data.weekResult); // 로그

				var labels = [];	//날짜 담을 배열
				var salesData = [];	//매출액 담을 배열

				data.weekResult.forEach(function(item) {
					labels.push(item.searchKeyword); // 날짜
					salesData.push(item.productPrice); // 매출액
				});

				// Chart.js로 라인 차트 그리기
				// ctx: 위치
				// id가 morris-line-chart인 canvas 태그에 그림
				var ctx = document.getElementById('week-bar-chart').getContext('2d');
				var myLineChart = new Chart(ctx, {
					type: 'bar',
					data: {
						labels: labels,
						datasets: [{
							label: '주별 매출',
							data: salesData,
							backgroundColor: 'rgba(75, 192, 192, 0.2)', // 채우기 색
							borderColor: 'rgba(75, 192, 192, 1)', // 선 색
							borderWidth: 2,
							fill: true,
							tension: 0 // 곡선 부드러운 정도
						}]
					},
					options: {
						responsive: true,	//크기 자동 조절(false는 고정)
						scales: {
							y: {
								beginAtZero: true	//y축 시작을 0부터
							}
						},
						ticks: {
							autoSkip: true, // 자동으로 스킵
							maxTicksLimit: 8 // 최대 8개만 표시
						}
					}
				});
			},
			error: function(xhr, status, error) {
				console.error("주별 매출 조회 실패:", error);
			}
		});
	});

	//월별 매출
	$(document).ready(function() {
		$.ajax({
			url: '/getMonthPrice.do',
			type: 'GET',
			dataType: 'json',
			success: function(data) {
				console.log(data.monthResult); // 로그

				var labels = [];	//날짜 담을 배열
				var salesData = [];	//매출액 담을 배열

				data.monthResult.forEach(function(item) {
					labels.push(item.searchKeyword); // 날짜
					salesData.push(item.productPrice); // 매출액
				});

				// Chart.js로 라인 차트 그리기
				// ctx: 위치
				// id가 morris-line-chart인 canvas 태그에 그림
				var ctx = document.getElementById('month-bar-chart').getContext('2d');
				var myLineChart = new Chart(ctx, {
					type: 'line',
					data: {
						labels: labels,
						datasets: [{
							label: '월별 매출',
							data: salesData,
							backgroundColor: 'rgba(75, 192, 192, 0.2)', // 채우기 색
							borderColor: 'rgba(75, 192, 192, 1)', // 선 색
							borderWidth: 2,
							fill: true,
							tension: 0 // 곡선 부드러운 정도
						}]
					},
					options: {
						responsive: true,	//크기 자동 조절(false는 고정)
						scales: {
							y: {
								beginAtZero: true	//y축 시작을 0부터
							}
						},
						ticks: {
							autoSkip: true, // 자동으로 스킵
							maxTicksLimit: 10 // 최대 10개만 표시
						}
					}
				});
			},
			error: function(xhr, status, error) {
				console.error("월별 매출 조회 실패:", error);
			}
		});
	});

</script>

</body>

</html>
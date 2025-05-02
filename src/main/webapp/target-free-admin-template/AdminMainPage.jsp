<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<link rel="icon" type="image/png" sizes="16x16" href="/favicon-32x32.png">
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<title>관리자 메인 페이지</title>
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/target-free-admin-template/assets/materialize/css/materialize.min.css" media="screen,projection" />
	<link href="${pageContext.request.contextPath}/target-free-admin-template/assets/css/bootstrap.css" rel="stylesheet" />
	<link href="${pageContext.request.contextPath}/target-free-admin-template/assets/css/font-awesome.css" rel="stylesheet" />
	<link href="${pageContext.request.contextPath}/target-free-admin-template/assets/css/custom-styles.css" rel="stylesheet" />
	<link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css' />
	<style>
		.chart-container {
			width: 50%; /* 가로 전체 너비 */
			padding: 10px; /* 약간의 여백 추가 */
		}
		.chart-container canvas {
			width: 50%; /* 가로 전체 채움 */
			max-height: 300px; /* 최대 높이 제한 */
		}
		.users-collection {
			margin: 0;
			border: none;
		}
		.users-collection .users {
			display: flex;
			align-items: center;
			padding: 15px;
			margin-bottom: 10px;
			background-color: #f9f9f9;
			border-radius: 8px;
			transition: all 0.3s ease;
			box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
		}
		.users-collection .users:hover {
			background-color: #e0f7fa;
			transform: translateY(-2px);
			box-shadow: 0 4px 8px rgba(0, 0, 0, 0.15);
		}
		.users-collection .users i.material-icons {
			font-size: 32px;
			margin-right: 15px;
			padding: 10px;
			border-radius: 50%;
			background-color: #e0e0e0;
		}
		.users-collection .users .title {
			font-size: 18px;
			font-weight: bold;
			color: #0277bd;
			margin-bottom: 5px;
		}
		.users-collection .users p {
			font-size: 14px;
			color: #555;
			margin: 0;
			line-height: 1.5;
		}
		.users-collection .users .secondary-content {
			margin-left: auto;
			color: #0288d1;
		}
		.users-collection .users .secondary-content:hover {
			color: #01579b;
		}
		@media (max-width: 600px) {
			.users-collection .users {
				flex-direction: column;
				align-items: flex-start;
				padding: 10px;
			}
			.users-collection .users i.material-icons {
				margin-bottom: 10px;
			}
		}
	</style>
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
		</ul>
	</nav>
	<nav class="navbar-default navbar-side" role="navigation">
		<div class="sidebar-collapse">
			<ul class="nav" id="main-menu">
				<li><a class="waves-effect waves-dark" href="/boardPage.do"><i class="fa fa-dashboard"></i> 이벤트 페이지</a></li>
				<li><a href="/adminPaymentListPage.do" class="waves-effect waves-dark"><i class="fa fa-desktop"></i> 결제 내역 페이지</a></li>
				<li><a href="/adminReportPage.do" class="waves-effect waves-dark"><i class="fa fa-bar-chart-o"></i> 신고 회원 관리 페이지</a></li>
			</ul>
		</div>
	</nav>

	<div id="page-wrapper">
		<div class="header">
			<h1 class="page-header">관리자 페이지</h1>
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
							<div class="card-image green"><i class="material-icons dp48">import_export</i></div>
							<div class="card-stacked purple">
								<div class="card-content totalUser"><h3></h3></div>
								<div class="card-action"><strong>전체 회원 수</strong></div>
							</div>
						</div>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-3">
						<div class="card horizontal cardIcon waves-effect waves-dark">
							<div class="card-image cyan"><i class="material-icons dp48">import_export</i></div>
							<div class="card-stacked lime">
								<div class="card-content paidUser"><h3></h3></div>
								<div class="card-action"><strong>결제한 회원 수</strong></div>
							</div>
						</div>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-3">
						<div class="card horizontal cardIcon waves-effect waves-dark">
							<div class="card-image cyan"><i class="material-icons dp48">import_export</i></div>
							<div class="card-stacked lime">
								<div class="card-content todayVisit"><h3></h3></div>
								<div class="card-action"><strong>오늘 방문자 수</strong></div>
							</div>
						</div>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-3">
						<div class="card horizontal cardIcon waves-effect waves-dark">
							<div class="card-image cyan"><i class="material-icons dp48">import_export</i></div>
							<div class="card-stacked lime">
								<div class="card-content totalPrice"><h3></h3></div>
								<div class="card-action"><strong>총 매출액</strong></div>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<h4>상품별 매출</h4>
						<div class="chart-container">
							<canvas id="product-doughnut-chart"></canvas>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<h4>일별 방문자</h4>
						<div class="chart-container">
							<canvas id="day-line-chart2"></canvas>
						</div>
					</div>
				</div>
			</div>

			<!-- Sales Charts -->
			<div class="row">
				<div class="col-md-12">
					<h4>일간 매출</h4>
					<div class="chart-container">
						<canvas id="day-line-chart"></canvas>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<h4>주간 매출</h4>
					<div class="chart-container">
						<canvas id="week-bar-chart"></canvas>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<h4>월간 매출</h4>
					<div class="chart-container">
						<canvas id="month-bar-chart"></canvas>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-md-4 col-sm-12 col-xs-12">
					<div class="card">
						<div class="card-action"><b>Tasks Panel</b></div>
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
						<div class="card-action"><b>최근 회원가입 한 회원</b></div>
						<div class="card-image">
							<ul class="collection users-collection">
								<li class="users">
									<i class="material-icons circle">person</i>
									<div class="user-info">
										<span class="title">Title</span>
										<p>First Line <br>Second Line</p>
									</div>
								</li>
								<li class="users">
									<i class="material-icons circle">person</i>
									<div class="user-info">
										<span class="title">Title</span>
										<p>First Line <br>Second Line</p>
									</div>
								</li>
								<li class="users">
									<i class="material-icons circle">person</i>
									<div class="user-info">
										<span class="title">Title</span>
										<p>First Line <br>Second Line</p>
									</div>
								</li>
								<li class="users">
									<i class="material-icons circle">person</i>
									<div class="user-info">
										<span class="title">Title</span>
										<p>First Line <br>Second Line</p>
									</div>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>

			<div class="fixed-action-btn horizontal click-to-toggle">
				<a class="btn-floating btn-large red"><i class="material-icons">menu</i></a>
				<ul>
					<li><a class="btn-floating red"><i class="material-icons">track_changes</i></a></li>
					<li><a class="btn-floating yellow darken-1"><i class="material-icons">format_quote</i></a></li>
					<li><a class="btn-floating green"><i class="material-icons">publish</i></a></li>
					<li><a class="btn-floating blue"><i class="material-icons">attach_file</i></a></li>
				</ul>
			</div>

			<footer><p>All right reserved. Template by: <a href="https://webthemez.com/admin-template/">WebThemez.com</a></p></footer>
		</div>
	</div>
</div>

<!-- JS Scripts -->
<script src="${pageContext.request.contextPath}/target-free-admin-template/assets/js/jquery-1.10.2.js"></script>
<script src="${pageContext.request.contextPath}/target-free-admin-template/assets/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/target-free-admin-template/assets/materialize/js/materialize.min.js"></script>
<script src="${pageContext.request.contextPath}/target-free-admin-template/assets/js/jquery.metisMenu.js"></script>
<script src="${pageContext.request.contextPath}/target-free-admin-template/assets/js/custom-scripts.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js@3.7.1/dist/chart.min.js"></script>

<script>
	// 상품별 매출 (도넛 차트)
	$(document).ready(function(){
		$.ajax({
			url: '/getProductPrice.do',
			type: 'GET',
			dataType: 'json',
			success: function(data) {
				console.log(data);
				var labels = ['프리미엄 멤버쉽', '기본 패키지', '인기 패키지', '프리미엄 패키지'];
				var salesData = [0, 0, 0, 0];
				data.result.forEach(function(item) {
					let productNum = item.productNumber;
					if (productNum >= 1 && productNum <= 4) {
						salesData[productNum - 1] = item.productPrice;
					}
				});

				var ctx = document.getElementById('product-doughnut-chart').getContext('2d');
				var myDoughnutChart = new Chart(ctx, {
					type: 'doughnut',
					data: {
						labels: labels,
						datasets: [{
							data: salesData,
							backgroundColor: [
								'rgba(244, 67, 54, 0.8)', // 빨강 (프리미엄 멤버쉽)
								'rgba(255, 152, 0, 0.8)', // 주황 (기본 패키지)
								'rgba(33, 150, 243, 0.8)', // 파랑 (인기 패키지)
								'rgba(76, 175, 80, 0.8)' // 초록 (프리미엄 패키지)
							],
							borderColor: [
								'rgba(244, 67, 54, 1)',
								'rgba(255, 152, 0, 1)',
								'rgba(33, 150, 243, 1)',
								'rgba(76, 175, 80, 1)'
							],
							borderWidth: 2,
							hoverOffset: 20
						}]
					},
					options: {
						responsive: true,
						maintainAspectRatio: true,
						aspectRatio: 2,
						plugins: {
							legend: {
								position: 'bottom',
								labels: {
									font: {
										size: 12,
										family: 'Open Sans'
									},
									padding: 20
								}
							},
							tooltip: {
								callbacks: {
									label: function(tooltipItem) {
										var value = tooltipItem.raw;
										return tooltipItem.label + ': ' + value.toLocaleString() + '원';
									}
								}
							}
						},
						layout: {
							padding: {
								left: 5,
								right: 5,
								top: 5,
								bottom: 5
							}
						}
					}
				});
			},
			error: function(xhr, status, error) {
				console.error("매출 조회 실패:", error);
			}
		});
	});

	// 전체 회원 수
	$(document).ready(function(){
		$.ajax({
			url: '/getTotalUserCount.do',
			type: 'GET',
			dataType: 'json',
			success: function(data) {
				console.log(data);
				$(".totalUser h3").text(data.userCount + "명");
			},
			error: function(xhr, status, error) {
				console.error("전체 회원 수 조회 실패:", error);
			}
		});
	});

	// 총 매출액
	$(document).ready(function(){
		$.ajax({
			url: '/getTotalPrice.do',
			type: 'GET',
			dataType: 'json',
			success: function(data) {
				console.log(data);
				$(".totalPrice h3").text(data.data.paymentNumber + "원");
			},
			error: function(xhr, status, error) {
				console.error("총 매출액 조회 실패:", error);
			}
		});
	});

	// 결제한 회원 수
	$(document).ready(function(){
		$.ajax({
			url: '/getPaidUser.do',
			type: 'GET',
			dataType: 'json',
			success: function(data) {
				console.log(data);
				$(".paidUser h3").text(data.paidUser + "명");
			},
			error: function(xhr, status, error) {
				console.error("결제 회원 수 조회 실패:", error);
			}
		});
	});

	// 최근 가입한 사용자 4명
	$(document).ready(function() {
		$.ajax({
			url: '/getFourUser.do',
			type: 'GET',
			dataType: 'json',
			success: function(data) {
				console.log(data);
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

	//남여 일별 방문자
	$(document).ready(function() {
		$.ajax({
			url: '/getDailyVisit.do',
			type: 'GET',
			dataType: 'json',
			success: function(data) {
				console.log(data);

				// 날짜 레이블 배열 생성 (중복 제거)
				var labels = [...new Set(data.visitors.map(item => item.visitorDate))].sort();

				// 남자(0)와 여자(1) 데이터 분리
				var maleData = new Array(labels.length).fill(0); // 남자 방문자 수
				var femaleData = new Array(labels.length).fill(0); // 여자 방문자 수

				// 날짜별로 남녀 방문자 수 매핑
				data.visitors.forEach(function(item) {
					var dateIndex = labels.indexOf(item.visitorDate);
					if (item.visitorGender === 1) { // 남자 (0)
						maleData[dateIndex] = item.visitorDaily;
					} else if (item.visitorGender === 0) { // 여자 (1)
						femaleData[dateIndex] = item.visitorDaily;
					}
				});

				// 차트 생성
				var ctx = document.getElementById('day-line-chart2').getContext('2d');

				// 남자 데이터 색상
				var maleColor = 'rgba(54, 162, 235, 0.8)'; // 파랑

				// 여자 데이터 색상
				var femaleColor = 'rgba(255, 99, 132, 0.8)'; // 분홍

				var myBarChart = new Chart(ctx, {
					type: 'bar',
					data: {
						labels: labels,
						datasets: [
							{
								label: '남자 방문자 수',
								data: maleData,
								backgroundColor: maleColor,
								borderColor: 'rgba(54, 162, 235, 1)',
								borderWidth: 1,
								barThickness: 20, // 막대 두께 조정
								categoryPercentage: 0.5, // 카테고리 내 막대 간격 조정
								barPercentage: 0.8 // 막대 자체의 너비 비율
							},
							{
								label: '여자 방문자 수',
								data: femaleData,
								backgroundColor: femaleColor,
								borderColor: 'rgba(255, 99, 132, 1)',
								borderWidth: 1,
								barThickness: 20,
								categoryPercentage: 0.5,
								barPercentage: 0.8
							}
						]
					},
					options: {
						responsive: true,
						maintainAspectRatio: true,
						aspectRatio: 2.5, // 가로 폭을 늘려 간격 확보
						plugins: {
							legend: {
								labels: {
									font: {
										size: 12,
										family: 'Open Sans'
									}
								}
							},
							tooltip: {
								callbacks: {
									label: function(tooltipItem) {
										return tooltipItem.dataset.label + ': ' + tooltipItem.raw + '명';
									}
								}
							}
						},
						scales: {
							x: {
								stacked: true, // X축에서 막대 쌓기
								grid: {
									display: false
								},
								ticks: {
									font: {
										family: 'Open Sans',
										size: 12
									},
								}
							},
							y: {
								stacked: true, // Y축에서 막대 쌓기
								beginAtZero: true,
								grid: {
									color: 'rgba(200, 200, 200, 0.2)'
								},
								ticks: {
									font: {
										family: 'Open Sans',
										size: 12
									},
									stepSize: 1 // 방문자 수는 정수이므로 1 단위로 설정
								}
							}
						}
					}
				});
			},
			error: function(xhr, status, error) {
				console.error("일별 방문자 조회 실패:", error);
			}
		});
	});

	// 오늘 방문 회원수
	$(document).ready(function() {
		$.ajax({
			url: '/getTodayVisit.do',
			type: 'GET',
			dataType: 'json',
			success: function(data) {
				console.log("ㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓ"+data);
				$(".todayVisit h3").text(data.visitor.visitorToday + "명");
			},
			error: function(xhr, status, error) {
				console.error("일별 매출 조회 실패:", error);
			}
		});
	});

	// 일간 매출
	$(document).ready(function() {
		$.ajax({
			url: '/getDayPrice.do',
			type: 'GET',
			dataType: 'json',
			success: function(data) {
				console.log(data.dayResult);
				var labels = [];
				var salesData = [];
				data.dayResult.forEach(function(item) {
					labels.push(item.paymentDate);
					salesData.push(item.productPrice);
				});
				var ctx = document.getElementById('day-line-chart').getContext('2d');
				var gradient = ctx.createLinearGradient(0, 0, 0, 400);
				gradient.addColorStop(0, 'rgba(255, 99, 132, 0.8)');
				gradient.addColorStop(1, 'rgba(255, 99, 132, 0.2)');

				var myLineChart = new Chart(ctx, {
					type: 'line',
					data: {
						labels: labels,
						datasets: [{
							label: '일별 매출',
							data: salesData,
							backgroundColor: gradient,
							borderColor: 'rgba(255, 99, 132, 1)',
							borderWidth: 3,
							fill: true,
							tension: 0.4,
							pointBackgroundColor: 'rgba(255, 99, 132, 1)',
							pointBorderColor: '#fff',
							pointHoverBackgroundColor: '#fff',
							pointHoverBorderColor: 'rgba(255, 99, 132, 1)',
							pointRadius: 5,
							pointHoverRadius: 7
						}]
					},
					options: {
						responsive: true,
						maintainAspectRatio: true,
						aspectRatio: 2,
						plugins: {
							legend: {
								labels: {
									font: {
										size: 12,
										family: 'Open Sans'
									}
								}
							}
						},
						scales: {
							y: {
								beginAtZero: true,
								grid: {
									color: 'rgba(200, 200, 200, 0.2)'
								},
								ticks: {
									font: {
										family: 'Open Sans',
										size: 12
									}
								}
							},
							x: {
								grid: {
									display: false
								},
								ticks: {
									font: {
										family: 'Open Sans',
										size: 12
									}
								}
							}
						},
						ticks: {
							autoSkip: true,
							maxTicksLimit: 10
						}
					}
				});
			},
			error: function(xhr, status, error) {
				console.error("일별 매출 조회 실패:", error);
			}
		});
	});

	// 주간 매출
	$(document).ready(function() {
		$.ajax({
			url: '/getWeekPrice.do',
			type: 'GET',
			dataType: 'json',
			success: function(data) {
				console.log(data.weekResult);
				var labels = [];
				var salesData = [];
				data.weekResult.forEach(function(item) {
					labels.push(item.searchKeyword);
					salesData.push(item.productPrice);
				});
				var ctx = document.getElementById('week-bar-chart').getContext('2d');
				var gradient = ctx.createLinearGradient(0, 0, 0, 400);
				gradient.addColorStop(0, 'rgba(54, 162, 235, 0.8)');
				gradient.addColorStop(1, 'rgba(54, 162, 235, 0.2)');

				var myBarChart = new Chart(ctx, {
					type: 'bar',
					data: {
						labels: labels,
						datasets: [{
							label: '주별 매출',
							data: salesData,
							backgroundColor: gradient,
							borderColor: 'rgba(54, 162, 235, 1)',
							borderWidth: 2,
							hoverBackgroundColor: 'rgba(54, 162, 235, 1)',
							hoverBorderColor: 'rgba(54, 162, 235, 1)'
						}]
					},
					options: {
						responsive: true,
						maintainAspectRatio: true,
						aspectRatio: 2,
						plugins: {
							legend: {
								labels: {
									font: {
										size: 12,
										family: 'Open Sans'
									}
								}
							}
						},
						scales: {
							y: {
								beginAtZero: true,
								grid: {
									color: 'rgba(200, 200, 200, 0.2)'
								},
								ticks: {
									font: {
										family: 'Open Sans',
										size: 12
									}
								}
							},
							x: {
								grid: {
									display: false
								},
								ticks: {
									font: {
										family: 'Open Sans',
										size: 12
									}
								}
							}
						},
						ticks: {
							autoSkip: true,
							maxTicksLimit: 8
						}
					}
				});
			},
			error: function(xhr, status, error) {
				console.error("주별 매출 조회 실패:", error);
			}
		});
	});

	// 월간 매출
	$(document).ready(function() {
		$.ajax({
			url: '/getMonthPrice.do',
			type: 'GET',
			dataType: 'json',
			success: function(data) {
				console.log(data.monthResult);
				var labels = [];
				var salesData = [];
				data.monthResult.forEach(function(item) {
					labels.push(item.searchKeyword);
					salesData.push(item.productPrice);
				});
				var ctx = document.getElementById('month-bar-chart').getContext('2d');
				var gradient = ctx.createLinearGradient(0, 0, 0, 400);
				gradient.addColorStop(0, 'rgba(153, 102, 255, 0.8)');
				gradient.addColorStop(1, 'rgba(153, 102, 255, 0.2)');

				var myLineChart = new Chart(ctx, {
					type: 'line',
					data: {
						labels: labels,
						datasets: [{
							label: '월별 매출',
							data: salesData,
							backgroundColor: gradient,
							borderColor: 'rgba(153, 102, 255, 1)',
							borderWidth: 3,
							fill: true,
							tension: 0.4,
							pointBackgroundColor: 'rgba(153, 102, 255, 1)',
							pointBorderColor: '#fff',
							pointHoverBackgroundColor: '#fff',
							pointHoverBorderColor: 'rgba(153, 102, 255, 1)',
							pointRadius: 5,
							pointHoverRadius: 7
						}]
					},
					options: {
						responsive: true,
						maintainAspectRatio: true,
						aspectRatio: 2,
						plugins: {
							legend: {
								labels: {
									font: {
										size: 12,
										family: 'Open Sans'
									}
								}
							}
						},
						scales: {
							y: {
								beginAtZero: true,
								grid: {
									color: 'rgba(200, 200, 200, 0.2)'
								},
								ticks: {
									font: {
										family: 'Open Sans',
										size: 12
									}
								}
							},
							x: {
								grid: {
									display: false
								},
								ticks: {
									font: {
										family: 'Open Sans',
										size: 12
									}
								}
							}
						},
						ticks: {
							autoSkip: true,
							maxTicksLimit: 10
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
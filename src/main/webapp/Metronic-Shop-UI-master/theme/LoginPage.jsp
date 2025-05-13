<!DOCTYPE html>
<html lang="ko">
<head>
    <link rel="icon" type="image/png" sizes="16x16" href="/favicon-32x32.png">
    <meta name="msapplication-TileColor" content="#ffffff">
    <meta name="theme-color" content="#ffffff">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Next Love - 로그인 회원가입</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <!-- 카카오 SDK 추가 -->
    <script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
    <style>
        /* 전체 스타일 리셋 */
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: 'Noto Sans KR', sans-serif;
        }

        body {
            background-color: #f5f5f5;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            padding: 20px;
        }

        /* 메인 컨테이너 - 웹 UI 스타일 (세로 크기 증가) */
        .main-container {
            display: flex;
            width: 1000px;
            max-width: 100%;
            height: 700px; /* 기존 600px에서 700px로 증가 */
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.1);
            overflow: hidden;
        }

        /* 왼쪽 이미지 섹션 */
        .image-section {
            flex: 1;
            background-color: #f8f9fa;
            position: relative;
            overflow: hidden;
        }

        .image-section img {
            width: 100%;
            height: 100%;
            object-fit: cover;
            transition: transform 0.5s ease;
        }

        .image-overlay {
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background: linear-gradient(to bottom, rgba(0,0,0,0.2), rgba(0,0,0,0.6));
            display: flex;
            flex-direction: column;
            justify-content: flex-end;
            padding: 50px; /* 패딩 증가 */
            color: white;
        }

        .image-overlay h2 {
            font-size: 36px; /* 글자 크기 증가 */
            margin-bottom: 15px; /* 여백 증가 */
            text-shadow: 1px 1px 3px rgba(0,0,0,0.3);
        }

        .image-overlay p {
            font-size: 18px; /* 글자 크기 증가 */
            margin-bottom: 25px; /* 여백 증가 */
            line-height: 1.6;
            text-shadow: 1px 1px 2px rgba(0,0,0,0.3);
        }

        /* 오른쪽 인증 섹션 */
        .auth-section {
            flex: 1;
            padding: 40px; /* 패딩 증가 */
            display: flex;
            flex-direction: column;
        }

        /* 헤더 영역 */
        .auth-header {
            text-align: center;
            margin-bottom: 25px; /* 여백 증가 */
        }

        .auth-header h2 {
            font-size: 32px; /* 글자 크기 증가 */
            color: #333;
            margin-bottom: 10px;
        }

        /* 탭 영역 */
        .auth-tabs {
            display: flex;
            margin-bottom: 25px; /* 여백 증가 */
            border-bottom: 1px solid #e0e0e0;
        }

        .auth-tab {
            flex: 1;
            text-align: center;
            padding: 15px 0; /* 패딩 증가 */
            cursor: pointer;
            font-weight: 500;
            font-size: 16px; /* 글자 크기 증가 */
            color: #666;
            transition: all 0.3s ease;
        }

        .auth-tab.active {
            color: #ff6b6b;
            border-bottom: 2px solid #ff6b6b;
        }

        /* 컨텐츠 영역 */
        .auth-content {
            flex: 1;
            overflow-y: auto;
        }

        .auth-form {
            display: none;
        }

        .auth-form.active {
            display: block;
        }

        .form-group {
            margin-bottom: 22px; /* 여백 증가 */
        }

        .form-group label {
            display: block;
            margin-bottom: 10px; /* 여백 증가 */
            color: #333;
            font-weight: 500;
            font-size: 15px; /* 글자 크기 증가 */
        }

        .form-group .fa {
            margin-right: 8px;
            color: #666;
        }

        .form-control {
            width: 100%;
            padding: 14px 16px; /* 패딩 증가 */
            border: 1px solid #ddd;
            border-radius: 4px;
            font-size: 15px; /* 글자 크기 증가 */
            transition: border-color 0.3s;
        }

        .form-control:focus {
            border-color: #ff6b6b;
            outline: none;
        }

        .email-input-group {
            display: flex;
            gap: 10px;
        }

        .email-input-group .form-control {
            flex: 1;
        }

        .email-check-btn {
            padding: 0 18px; /* 패딩 증가 */
            background-color: #f0f0f0;
            border: 1px solid #ddd;
            border-radius: 4px;
            color: #333;
            font-size: 14px; /* 글자 크기 증가 */
            cursor: pointer;
            transition: all 0.3s;
            white-space: nowrap;
        }

        .email-check-btn:hover {
            background-color: #e0e0e0;
        }

        #emailCheckResult {
            margin-top: 6px;
            font-size: 13px; /* 글자 크기 증가 */
        }

        .submit-btn {
            width: 100%;
            padding: 15px; /* 패딩 증가 */
            background-color: #ff6b6b;
            color: white;
            border: none;
            border-radius: 4px;
            font-size: 17px; /* 글자 크기 증가 */
            font-weight: 500;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        .submit-btn:hover {
            background-color: #ff5252;
        }

        .submit-btn:disabled {
            background-color: #cccccc;
            color: #666666;
            cursor: not-allowed;
        }

        .divider {
            display: flex;
            align-items: center;
            margin: 25px 0; /* 여백 증가 */
            color: #888;
        }

        .divider::before,
        .divider::after {
            content: "";
            flex: 1;
            border-bottom: 1px solid #e0e0e0;
        }

        .divider-text {
            padding: 0 12px; /* 패딩 증가 */
            font-size: 15px; /* 글자 크기 증가 */
        }

        .social-login {
            margin-bottom: 25px; /* 여백 증가 */
        }

        .social-login-title {
            text-align: center;
            margin-bottom: 18px; /* 여백 증가 */
            font-size: 15px; /* 글자 크기 증가 */
            color: #666;
        }

        .social-buttons {
            display: flex;
            justify-content: center;
            gap: 20px; /* 간격 증가 */
        }

        /* 소셜 버튼 스타일 수정 - 크기 증가 */
        .social-btn {
            width: 55px; /* 크기 증가 */
            height: 55px; /* 크기 증가 */
            border-radius: 50%;
            border: none;
            font-size: 20px; /* 글자 크기 증가 */
            display: flex;
            align-items: center;
            justify-content: center;
            cursor: pointer;
            transition: transform 0.3s, box-shadow 0.3s;
            position: relative;
            z-index: 1;
        }

        .social-btn:hover {
            transform: translateY(-3px);
            box-shadow: 0 5px 10px rgba(0, 0, 0, 0.1);
        }

        .naver-btn {
            background-color: #03C75A;
            color: white;
            font-weight: bold;
        }

        .kakao-btn {
            background-color: #FEE500;
            color: #3C1E1E;
        }

        /* 소셜 버튼 내부 텍스트 스타일 */
        .naver-btn-text {
            font-weight: bold;
            font-size: 22px; /* 글자 크기 증가 */
        }

        .auth-info {
            text-align: center;
            font-size: 15px; /* 글자 크기 증가 */
            color: #666;
            margin-top: 10px; /* 여백 추가 */
        }

        .auth-info a {
            color: #ff6b6b;
            text-decoration: none;
            font-weight: 500;
        }

        .auth-info a:hover {
            text-decoration: underline;
        }

        /* 반응형 디자인 */
        @media (max-width: 768px) {
            .main-container {
                flex-direction: column;
                height: auto;
            }

            .image-section {
                height: 250px; /* 모바일에서 이미지 섹션 높이 증가 */
            }

            .image-overlay {
                padding: 30px;
            }

            .image-overlay h2 {
                font-size: 28px;
            }

            .auth-section {
                padding: 30px;
            }
        }
    </style>
</head>
<body>
<div class="main-container">
    <!-- 왼쪽 이미지 섹션 -->
    <div class="image-section">
        <img src="https://images.unsplash.com/photo-1515378791036-0648a3ef77b2?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1350&q=80" alt="Next Love">
        <div class="image-overlay">
            <h2>Next Love에 오신 것을 환영합니다</h2>
            <p>새로운 인연을 만나고 <br/>특별한 관계를 시작하세요</p>
        </div>
    </div>

    <!-- 오른쪽 인증 섹션 -->
    <div class="auth-section">
        <!-- 헤더 영역 -->
        <div class="auth-header">
            <h2>Next Love</h2>
        </div>

        <!-- 탭 영역 -->
        <div class="auth-tabs">
            <div class="auth-tab" id="login-tab">로그인</div>
            <div class="auth-tab active" id="signup-tab">회원가입</div>
        </div>

        <!-- 컨텐츠 영역 -->
        <div class="auth-content">
            <!-- 로그인 폼 -->
            <form class="auth-form" id="login-form" action="login.do" method="POST">
                <div class="form-group">
                    <label for="login-email"><i class="fa fa-envelope"></i> 이메일</label>
                    <input type="email" id="login-email" name="userEmail" class="form-control" placeholder="이메일 주소" required>
                </div>

                <div class="form-group">
                    <label for="login-password"><i class="fa fa-lock"></i> 비밀번호</label>
                    <input type="password" id="login-password" name="userPassword" class="form-control" placeholder="비밀번호" required>
                </div>

                <button type="submit" class="submit-btn">로그인</button>

                <div class="divider">
                    <span class="divider-text">또는</span>
                </div>

                <div class="social-login">
                    <div class="social-login-title">소셜 계정으로 로그인</div>
                    <div class="social-buttons">
                        <!-- 네이버 로그인 버튼 -->
                        <button type="button" class="social-btn naver-btn" onclick="naverLogin()">
                            <span class="naver-btn-text">N</span>
                        </button>
                        <!-- 카카오 로그인 버튼 -->
                        <button type="button" class="social-btn kakao-btn" onclick="kakaoLogin()">
                            <i class="fas fa-comment"></i>
                        </button>
                    </div>
                </div>

                <div class="auth-info">
                    <p>아직 계정이 없으신가요? <a href="#" id="go-to-signup">회원가입</a>하고 서비스를 이용하세요.</p>
                </div>
            </form>

            <!-- 회원가입 폼 -->
            <form class="auth-form active" id="signup-form" action="join.do" method="POST">
                <div class="form-group">
                    <label for="signup-name"><i class="fa fa-user"></i> 이름</label>
                    <input type="text" id="signup-name" name="userName" class="form-control" placeholder="이름" required>
                </div>

                <div class="form-group">
                    <label for="signup-email"><i class="fa fa-envelope"></i> 이메일</label>
                    <div class="email-input-group">
                        <input type="email" id="signup-email" name="userEmail" class="form-control" placeholder="이메일 주소" required>
                        <button type="button" class="email-check-btn" onclick="checkEmailDuplicate()">중복 확인</button>
                    </div>
                    <div id="emailCheckResult"></div>
                    <input type="hidden" id="emailVerified" name="emailVerified" value="false">
                </div>

                <div class="form-group">
                    <label for="signup-password"><i class="fa fa-lock"></i> 비밀번호</label>
                    <input type="password" id="signup-password" name="userPassword" class="form-control" placeholder="비밀번호" required>
                </div>

                <!-- 회원가입 버튼 - 초기에 비활성화 상태로 설정 -->
                <button type="submit" id="signup-submit-btn" class="submit-btn" disabled>회원가입</button>

                <div class="divider">
                    <span class="divider-text">또는</span>
                </div>

                <div class="social-login">
                    <div class="social-login-title">소셜 계정으로 가입</div>
                    <div class="social-buttons">
                        <!-- 네이버 가입 버튼 -->
                        <button type="button" class="social-btn naver-btn" onclick="naverLogin()">
                            <span class="naver-btn-text">N</span>
                        </button>
                        <!-- 카카오 가입 버튼 -->
                        <button type="button" class="social-btn kakao-btn" onclick="kakaoLogin()">
                            <i class="fas fa-comment"></i>
                        </button>
                    </div>
                </div>

                <div class="auth-info">
                    <p>이미 계정이 있으신가요? <a href="#" id="go-to-login">로그인</a>하고 서비스를 이용하세요.</p>
                </div>
            </form>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/js/SignInUp.js"></script>
<script>
    // 페이지 로드 시 실행
    $(document).ready(function() {
        // 탭 전환 기능
        $('#login-tab').click(function() {
            $(this).addClass('active');
            $('#signup-tab').removeClass('active');
            $('#login-form').addClass('active');
            $('#signup-form').removeClass('active');
        });

        $('#signup-tab').click(function() {
            $(this).addClass('active');
            $('#login-tab').removeClass('active');
            $('#signup-form').addClass('active');
            $('#login-form').removeClass('active');
        });

        // 로그인/회원가입 전환 링크
        $('#go-to-signup').click(function(e) {
            e.preventDefault();
            $('#signup-tab').click();
        });

        $('#go-to-login').click(function(e) {
            e.preventDefault();
            $('#login-tab').click();
        });

        // 이메일 입력란에 변경 이벤트 리스너 추가
        $('#signup-email').on('input', function() {
            // 이메일이 변경되면 버튼 비활성화 및 확인 결과 초기화
            $('#signup-submit-btn').prop('disabled', true);
            $('#emailVerified').val('false');
            $('#emailCheckResult').html('');
        });
    });


    function checkEmailDuplicate() {
        // 이메일 입력 필드의 ID를 HTML과 일치시킴
        const userEmail = document.getElementById('signup-email').value;

        // 이메일이 비어있는지 확인
        if (!userEmail) {
            alert('이메일을 입력해주세요.');
            return;
        }

        // 이메일 형식 검증
        const emailRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;
        if (!emailRegex.test(userEmail)) {
            alert('올바른 이메일 형식이 아닙니다.');
            return;
        }

        // 소셜 타입 가져오기 (소셜 로그인 여부에 따라 다르게 설정)
        const socialType = 'normal'; // 기본값으로 normal 설정

        console.log("이메일 중복 체크 요청:", userEmail);

        // AJAX 요청
        $.ajax({
            url: '/checkEmailDuplicate.do', // 서블릿 URL
            type: 'POST',
            data: {
                userEmail: userEmail,
                socialType: socialType
            },
            success: function(response) {
                console.log("서버 응답:", response);

                if (response.isDuplicate) {
                    // 중복된 이메일인 경우
                    alert(response.message);
                    document.getElementById('emailCheckResult').innerHTML =
                        '<span style="color: red;">이미 사용 중인 이메일입니다.</span>';
                    document.getElementById('emailVerified').value = 'false';
                    // 회원가입 버튼 비활성화
                    document.getElementById('signup-submit-btn').disabled = true;
                } else {
                    // 사용 가능한 이메일인 경우
                    alert(response.message);
                    document.getElementById('emailCheckResult').innerHTML =
                        '<span style="color: green;">사용 가능한 이메일입니다.</span>';
                    document.getElementById('emailVerified').value = 'true';
                    // 회원가입 버튼 활성화
                    document.getElementById('signup-submit-btn').disabled = false;
                }
            },
            error: function(xhr, status, error) {
                console.error('이메일 중복 체크 중 오류 발생:', error);
                console.error('상태 코드:', xhr.status);
                console.error('응답 텍스트:', xhr.responseText);
                alert('서버 오류가 발생했습니다. 나중에 다시 시도해주세요.');
                // 오류 발생 시 버튼 비활성화
                document.getElementById('signup-submit-btn').disabled = true;
            }
        });
    }
</script>
</body>
</html>

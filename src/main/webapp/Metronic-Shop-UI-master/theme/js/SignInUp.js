// 탭 전환 기능
document.addEventListener('DOMContentLoaded', function() {
    const loginTab = document.getElementById('login-tab');
    const signupTab = document.getElementById('signup-tab');
    const loginForm = document.getElementById('login-form');
    const signupForm = document.getElementById('signup-form');
    const goToLogin = document.getElementById('go-to-login');
    const goToSignup = document.getElementById('go-to-signup');

    // 로그인 탭 클릭 시
    loginTab.addEventListener('click', function() {
        loginTab.classList.add('active');
        signupTab.classList.remove('active');
        loginForm.classList.add('active');
        signupForm.classList.remove('active');
    });

    // 회원가입 탭 클릭 시
    signupTab.addEventListener('click', function() {
        signupTab.classList.add('active');
        loginTab.classList.remove('active');
        signupForm.classList.add('active');
        loginForm.classList.remove('active');
    });

    // "로그인" 링크 클릭 시
    goToLogin.addEventListener('click', function(e) {
        e.preventDefault();
        loginTab.click();
    });

    // "회원가입" 링크 클릭 시
    goToSignup.addEventListener('click', function(e) {
        e.preventDefault();
        signupTab.click();
    });

    // 소셜 로그인 버튼 이벤트 확인
    const naverButtons = document.querySelectorAll('.naver-btn');
    naverButtons.forEach(button => {
        button.addEventListener('click', function() {
            console.log('네이버 로그인 버튼 클릭됨');
            naverLogin();
        });
    });

    const kakaoButtons = document.querySelectorAll('.kakao-btn');
    kakaoButtons.forEach(button => {
        button.addEventListener('click', function() {
            console.log('카카오 로그인 버튼 클릭됨');
            kakaoLogin();
        });
    });
});

// 네이버 로그인 함수
function naverLogin() {
    console.log('네이버 로그인 함수 실행');
    var clientId = "HPtl9HdFUiGzoDPAPQ4a";
    var redirectURI = encodeURIComponent("http://localhost:8088/Metronic-Shop-UI-master/theme/naverCallback.do");
    var state = generateState();

    // 상태 토큰 저장
    localStorage.setItem("naverState", state);

    // 네이버 로그인 페이지로 리다이렉트
    var naverLoginUrl = "https://nid.naver.com/oauth2.0/authorize?response_type=code"
        + "&client_id=" + clientId
        + "&redirect_uri=" + redirectURI
        + "&state=" + state;

    window.location.href = naverLoginUrl;
}

// 카카오 로그인 함수
function kakaoLogin() {
    console.log('카카오 로그인 함수 실행');
    // 카카오 SDK 초기화 (앱 키 설정)
    Kakao.init('cb9656ab4895e6ee319e89e74f28a308');

    // 카카오 로그인 처리
    Kakao.Auth.authorize({
        redirectUri: 'http://localhost:8088/Metronic-Shop-UI-master/theme/kakaoCallBack.do'  // 리다이렉트 URI 설정
    });
}

// 랜덤 상태 토큰 생성 함수
function generateState() {
    return Math.random().toString(36).substr(2, 11);
}
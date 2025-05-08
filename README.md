NextLevel/
├── .gradle/                  # Gradle 관련 파일
├── .idea/                    # IntelliJ IDEA 설정 파일
├── build/                    # 빌드 결과물
├── gradle/                   # Gradle 래퍼 파일
├── src/
│   └── main/
│       ├── java/
│       │   └── com.example.common/
│       │       ├── biz/                      # 비즈니스 로직 패키지
│       │       │   ├── alert/                # 알림 관련 클래스
│       │       │   ├── board/                # 게시판 관련 클래스
│       │       │   ├── chatMessage/          # 채팅 메시지 관련 클래스
│       │       │   ├── chatRoom/             # 채팅방 관련 클래스
│       │       │   ├── crawling/             # 크롤링 관련 클래스
│       │       │   ├── participant/          # 참여자 관련 클래스
│       │       │   ├── payment/              # 결제 관련 클래스
│       │       │   ├── preference/           # 환경설정 관련 클래스
│       │       │   ├── product/              # 상품 관련 클래스
│       │       │   ├── report/               # 리포트 관련 클래스
│       │       │   ├── user/                 # 사용자 관련 클래스
│       │       │   └── visitor/              # 방문자 관련 클래스
│       │       ├── config/                   # 설정 관련 패키지
│       │       │   ├── security/             # 보안 설정
│       │       │   ├── swagger/              # Swagger 설정
│       │       │   └── websocket/            # 웹소켓 설정
│       │       ├── view/                     # 뷰 관련 패키지
│       │       │   ├── adminPaymentList/     # 관리자 결제 목록
│       │       │   ├── adminUserModeration/  # 관리자 사용자 관리
│       │       │   ├── asyn/                 # 비동기 처리
│       │       │   ├── auth/                 # 인증 관련
│       │       │   ├── board/                # 게시판 화면
│       │       │   ├── chatting/             # 채팅 화면
│       │       │   ├── excel/                # 엑셀 처리
│       │       │   ├── logic/                # 로직 처리
│       │       │   ├── mainPage/             # 메인 페이지
│       │       │   ├── myPage/               # 마이 페이지
│       │       │   ├── payment/              # 결제 화면
│       │       │   ├── userAccount/          # 사용자 계정
│       │       │   └── userInteraction/      # 사용자 상호작용
│       │       ├── GeoCodingUtil.java        # 지오코딩 유틸리티
│       │       ├── IndexPageController.java  # 인덱스 페이지 컨트롤러
│       │       ├── JDBCUtil.java             # JDBC 유틸리티
│       │       ├── NextLevelApplication.java # 애플리케이션 시작점
│       │       └── ServletInitializer.java   # 서블릿 초기화
│       └── resources/
│           ├── static/                       # 정적 리소스
│           ├── templates/                    # 템플릿 파일
│           ├── application.properties        # 애플리케이션 설정
│           └── application.yml               # YAML 형식 설정
├── .gitignore                # Git 무시 파일 설정
├── build.gradle              # Gradle 빌드 스크립트
└── README.md                 # 프로젝트 설명서

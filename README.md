# NextLevel 프로젝트 구조 📂

## 기술 스택 🛠️
<!-- 기술 스택 배지 -->
<p align="center">
  <!-- 첫 번째 줄: 프로그래밍 언어 -->
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java" />&nbsp;
  <img src="https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black" alt="JavaScript" />&nbsp;
  <img src="https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=html5&logoColor=white" alt="HTML5" />&nbsp;
  <img src="https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=css3&logoColor=white" alt="CSS3" />
</p>

<p align="center">
  <!-- 두 번째 줄: 프레임워크 및 라이브러리 -->
  <img src="https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white" alt="Spring Boot" />&nbsp;
  <img src="https://img.shields.io/badge/JPA-6DB33F?style=for-the-badge&logo=spring&logoColor=white" alt="JPA" />&nbsp;
  <img src="https://img.shields.io/badge/MyBatis-000000?style=for-the-badge&logo=mybatis&logoColor=white" alt="MyBatis" />&nbsp;
  <img src="https://img.shields.io/badge/JDBC-007396?style=for-the-badge&logo=java&logoColor=white" alt="JDBC" />&nbsp;
</p>

<p align="center">
  <!-- 세 번째 줄: 데이터베이스 -->
  <img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white" alt="MySQL" />&nbsp;
  <img src="https://img.shields.io/badge/Oracle-F80000?style=for-the-badge&logo=oracle&logoColor=white" alt="Oracle" />
</p>


## 프로젝트 디렉토리 구조 🗂️
```
NextLevel/
├── .gradle/                 
├── .idea/                    
├── build/                   
├── gradle/                  
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
│           ├── static/                      
│           ├── templates/                   
│           ├── application.properties        # 애플리케이션 설정
│           └── application.yml               # YAML 형식 설정
├── .gitignore                
├── build.gradle              
└── README.md                 
```

## 주요 패키지 설명 📚

### DB (biz) 💼
- **alert**: 알림 시스템 관련 클래스 🔔
- **board**: 게시판 기능 관련 클래스 📋
- **chatMessage/chatRoom**: 실시간 채팅 기능 관련 클래스 💬
- **crawling**: 웹 크롤링 기능 관련 클래스 🕸️
- **payment**: 결제 시스템 관련 클래스 💰
- **user**: 사용자 관리 관련 클래스 👤

### 설정 (config) ⚙️
- **security**: Spring Security 관련 설정 🔒
- **swagger**: API 문서화 관련 설정 📝
- **websocket**: 웹소켓 통신 관련 설정 🔌

### Service (view) 👁️
- **adminPaymentList/adminUserModeration**: 관리자 기능 관련 클래스 👮
- **auth**: 인증 및 권한 관련 클래스 🔑
- **board/chatting**: 사용자 인터페이스 관련 클래스 📝
- **payment**: 결제 화면 관련 클래스 💳

### 유틸리티 🔧
- **GeoCodingUtil**: 위치 정보 변환 유틸리티 🗺️
- **JDBCUtil**: 데이터베이스 연결 유틸리티 🗃️

## 프로젝트 주요 기능 ✨

- 사용자 인증 및 권한 관리 🔐
- 실시간 채팅 시스템 💬
- 게시판 기능 📋
- 결제 시스템 연동 💳
- 데이터 크롤링 및 분석 📊
- 관리자 대시보드 📈

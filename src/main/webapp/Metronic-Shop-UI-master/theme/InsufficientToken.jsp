<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>토큰 부족</title>
  <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;500;600;700&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">
  <link href="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/css/insufficientToken.css" rel="stylesheet">
</head>
<body>
<div class="token-container">
  <div class="token-header">
    <h1><i class="fas fa-exclamation-circle"></i> 토큰 부족 안내</h1>
  </div>
  <div class="token-content">
    <div class="icon">
      <i class="fas fa-coins"></i>
    </div>
    <h2 style="margin-bottom: 15px; color: #333;">토큰이 부족합니다</h2>
    <p>채팅을 시작하기 위한 토큰이 부족합니다.<br>토큰을 충전한 후 다시 시도해주세요.</p>
    <a href="/productPage.do" class="btn">
      <i class="fas fa-plus-circle"></i> 토큰 충전하기
    </a>
  </div>
</div>
</body>
</html>

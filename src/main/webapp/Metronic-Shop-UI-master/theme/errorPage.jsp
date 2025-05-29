<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>접근 오류</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      background-color: #f8f9fa;
      color: #333;
      text-align: center;
      padding: 40px;
    }
    .error-container {
      display: inline-block;
      background: #fff;
      border: 1px solid #ddd;
      padding: 40px 60px;
      border-radius: 8px;
      box-shadow: 0 4px 10px rgba(0,0,0,0.1);
    }
    h1 {
      color: #dc3545;
      font-size: 2em;
    }
    p {
      margin: 20px 0;
      font-size: 1.1em;
    }
    a {
      display: inline-block;
      padding: 10px 20px;
      background: #007bff;
      color: #fff;
      text-decoration: none;
      border-radius: 4px;
      margin-top: 10px;
    }
    a:hover {
      background: #0056b3;
    }
  </style>
</head>
<body>

<div class="error-container">
  <h1>로그인이 필요합니다</h1>
  <p>이 페이지에 접근하려면 로그인해야 합니다.</p>
</div>

</body>
</html>

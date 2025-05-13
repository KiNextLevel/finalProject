<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <meta charset="UTF-8">
  <title>채팅방 목록</title>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">
  <style>
    body { font-family: 'Noto Sans KR', sans-serif; padding: 20px; }
    .chat-room-box {
      border: 1px solid #ccc;
      padding: 15px;
      margin-bottom: 10px;
      border-radius: 8px;
      transition: background 0.2s;
    }
    .chat-room-box:hover {
      background: #f9f9f9;
    }
    .chat-header {
      display: flex;
      justify-content: space-between;
      align-items: center;
    }
    .nickname { font-weight: bold; font-size: 1.1em; }
    .last-time { color: gray; font-size: 0.9em; }
    .last-message { color: #555; margin-top: 5px; }
  </style>
</head>
<body>

<h2><i class="fas fa-comments"></i> 채팅방 목록</h2>

<c:choose>
  <c:when test="${empty roomList}">
    <!-- 채팅방이 하나도 없을 때 보여주는 문구 -->
    <p>진행 중인 채팅이 없습니다.</p>
  </c:when>
  <c:otherwise>
    <!-- roomList에 들어있는 채팅방 목록을 반복 출력 -->
    <c:forEach var="room" items="${roomList}">

      <!-- 각 채팅방 하나를 감싸는 박스 -->
      <div class="chat-room-box">

        <!-- 클릭하면 채팅방으로 이동. 상대 이메일을 파라미터로 보냄 -->
        <a href="/chattingRoom.do?targetEmail=${room.opponentEmail}">

          <!-- 닉네임과 마지막 메시지 시간 -->
          <div class="chat-header">
            <span class="nickname">${room.opponentNickname}</span>  <!-- 상대방 닉네임 -->
            <span class="last-time">${room.lastTime}</span>  <!-- 마지막 메시지 시간 -->
          </div>

          <!-- 마지막 메시지 내용 -->
          <div class="last-message">${room.lastMessage}</div>
        </a>
      </div>

    </c:forEach>
  </c:otherwise>
</c:choose>

</body>
<%--<script>--%>
<%--  $.ajax({--%>
<%--    url: "/chatHistory.do",--%>
<%--    method: "GET",--%>
<%--    data: { chatRoomId: chatRoomId },--%>
<%--    success: function(messages) {--%>
<%--      // 메시지를 반복해서 채팅창에 표시--%>
<%--    }--%>
<%--  });--%>



</script>
</html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>자신의 채팅방 리스트</title>
</head>
<body>

나의 채팅방목록 <%-- 나중에는 없애기 , 그냥 리스트만 나오게 하기 --%>
<c:choose>
    <c:when test="${empty roomList}">
        <!-- 채팅방이 하나도 없을 때 보여주는 문구 -->
        <p>진행 중인 채팅이 없습니다.</p>
    </c:when>
    <c:otherwise>
        <!-- roomList에 들어있는 채팅방 목록을 반복 출력 -->
        <c:forEach var="room" items="${roomList}">
            <li class="chat-room-box">
                <a href="/chattingRoom.do?chatRoomId=${room.chatRoomId}&targetEmail=${room.opponentEmail}">
                <%--<p>디버깅용: 이메일 = ${room.opponentEmail}</p>--%>

                    <p>상대방: ${room.opponentNickname}</p>
                <%-- <p>이메일: ${room.opponentEmail}</p>--%>
                </a>
            </li>
        </c:forEach>

    </c:otherwise>
</c:choose>

</body>
</html>

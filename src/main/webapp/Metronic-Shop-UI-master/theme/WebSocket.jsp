<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>실시간 채팅</title>
  <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;500;600;700&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">
  <link href="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/css/WebSocket.css" rel="stylesheet">
</head>
<body>
<div class="chat-container">
  <div class="chat-header">
    <h2><i class="fas fa-comments"></i> 실시간 채팅</h2>
    <div class="status-indicator"></div>
    <span class="status-text">온라인</span>
  </div>
  <div id="chat-box"></div>
  <div class="chat-input">
    <input type="text" id="msgInput" placeholder="메시지를 입력하세요..." />
    <button class="send-btn" onclick="sendMessage()">
      <i class="fas fa-paper-plane"></i>
    </button>
  </div>
</div>

<script>
  // 서버에서 전달받은 현재 사용자 정보 (이메일)
  const currentUser = "${sessionScope.userEmail}" || "나";

  // URL 파라미터에서 상대방 이메일 가져오기
  const urlParams = new URLSearchParams(window.location.search);
  const targetEmail = urlParams.get('targetEmail') || "상대방";

  // 채팅방 ID 생성 (두 사용자 이메일 조합)
  String.prototype.hashCode = function() {
    let hash = 0;
    for (let i = 0; i < this.length; i++) {
      hash = ((hash << 5) - hash) + this.charCodeAt(i);
      hash = hash & hash;
    }
    return Math.abs(hash);
  };

  const chatRoomId = [currentUser, targetEmail].sort().join('_').hashCode();

  const socket = new WebSocket("ws://localhost:8088/ws/chat");
  const chatBox = document.getElementById("chat-box");
  const msgInput = document.getElementById("msgInput");

  // 현재 시간을 포맷팅하는 함수
  function getCurrentTime() {
    const now = new Date();
    const hours = now.getHours();
    const minutes = now.getMinutes().toString().padStart(2, '0');
    const ampm = hours >= 12 ? '오후' : '오전';
    const displayHours = hours % 12 || 12;
    return `${ampm} ${displayHours}:${minutes}`;
  }

  socket.onopen = () => {
    // 입장 메시지 전송
    const joinMsg = {
      chatRoomId: chatRoomId,
      sender: currentUser,
      messageType: "JOIN",
      message: "입장했습니다"
    };
    socket.send(JSON.stringify(joinMsg));

    // 상대방 정보 표시
    const roomInfoElement = document.createElement("div");
    roomInfoElement.className = "join-message";
    roomInfoElement.innerHTML = `<i class="fas fa-info-circle"></i> ${targetEmail}님과의 대화방입니다`;
    chatBox.appendChild(roomInfoElement);
  };

  socket.onmessage = (event) => {
    const data = JSON.parse(event.data);

    if (data.messageType === "JOIN") {
      // 입장 메시지 표시
      const joinElement = document.createElement("div");
      joinElement.className = "join-message";
      joinElement.innerHTML = `<i class="fas fa-user-plus"></i> ${data.sender}님이 ${data.message}`;
      chatBox.appendChild(joinElement);
    } else if (data.messageType === "LEAVE") {
      // 퇴장 메시지 표시
      const leaveElement = document.createElement("div");
      leaveElement.className = "join-message";
      leaveElement.innerHTML = `<i class="fas fa-user-minus"></i> ${data.sender}님이 ${data.message}`;
      chatBox.appendChild(leaveElement);
    } else {
      // 채팅 메시지 표시
      const isMine = data.sender === currentUser;
      const currentTime = getCurrentTime();

      // 메시지 래퍼 생성 (정렬을 위한 컨테이너)
      const messageWrapper = document.createElement("div");
      messageWrapper.className = isMine ? "message-wrapper my-message-wrapper" : "message-wrapper other-message-wrapper";

      // 메시지 요소 생성
      const messageElement = document.createElement("div");
      messageElement.className = `message ${isMine ? 'sender-message' : 'receiver-message'}`;
      messageElement.textContent = data.message;

      // 메시지 시간 표시
      const timeElement = document.createElement("div");
      timeElement.className = "message-time";
      timeElement.textContent = currentTime;

      // 발신자 정보 (상대방 메시지인 경우만)
      if (!isMine) {
        const infoElement = document.createElement("div");
        infoElement.className = "message-info";
        infoElement.textContent = data.sender;
        messageWrapper.appendChild(infoElement);
      }

      // 메시지와 시간을 래퍼에 추가
      messageWrapper.appendChild(messageElement);
      messageWrapper.appendChild(timeElement);

      // 채팅창에 추가
      chatBox.appendChild(messageWrapper);
    }

    // 스크롤을 항상 아래로 유지
    chatBox.scrollTop = chatBox.scrollHeight;
  };

  socket.onclose = () => {
    // 퇴장 메시지 전송
    const leaveMsg = {
      chatRoomId: chatRoomId,
      sender: currentUser,
      messageType: "LEAVE",
      message: "퇴장했습니다"
    };

    try {
      socket.send(JSON.stringify(leaveMsg));
    } catch (e) {
      // 이미 연결이 끊긴 경우 무시
    }

    const disconnectMsg = document.createElement("div");
    disconnectMsg.className = "join-message";
    disconnectMsg.innerHTML = `<i class="fas fa-exclamation-triangle"></i> 서버와의 연결이 종료되었습니다.`;
    chatBox.appendChild(disconnectMsg);

    // 상태 표시 업데이트
    document.querySelector('.status-indicator').style.backgroundColor = '#F44336';
    document.querySelector('.status-text').textContent = '오프라인';
  };

  function sendMessage() {
    const msg = msgInput.value.trim();
    if (msg !== "") {
      const sendMsg = {
        chatRoomId: chatRoomId,
        sender: currentUser,
        messageType: "TALK",
        message: msg
      };
      socket.send(JSON.stringify(sendMsg));
      msgInput.value = "";
    }
  }

  // Enter 키로 메시지 전송
  msgInput.addEventListener("keypress", (e) => {
    if (e.key === "Enter") {
      e.preventDefault();
      sendMessage();
    }
  });

  // 웹소켓 연결 상태 확인
  function checkConnection() {
    if (socket.readyState === WebSocket.OPEN) {
      document.querySelector('.status-indicator').style.backgroundColor = '#4CAF50';
      document.querySelector('.status-text').textContent = '온라인';
    } else {
      document.querySelector('.status-indicator').style.backgroundColor = '#F44336';
      document.querySelector('.status-text').textContent = '오프라인';
    }
  }

  // 주기적으로 연결 상태 확인
  setInterval(checkConnection, 5000);

  // 페이지 나가기 전에 퇴장 메시지 전송
  window.addEventListener('beforeunload', () => {
    if (socket.readyState === WebSocket.OPEN) {
      const leaveMsg = {
        chatRoomId: chatRoomId,
        sender: currentUser,
        messageType: "LEAVE",
        message: "퇴장했습니다"
      };
      socket.send(JSON.stringify(leaveMsg));
    }
  });
</script>
</body>
</html>

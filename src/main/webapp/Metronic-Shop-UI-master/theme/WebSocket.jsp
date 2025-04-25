<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>실시간 채팅</title>
  <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;500;600;700&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">
  <style>
    * {
      margin: 0;
      padding: 0;
      box-sizing: border-box;
      font-family: 'Noto Sans KR', sans-serif;
    }

    body {
      background-color: #f5f7fb;
      display: flex;
      justify-content: center;
      align-items: center;
      min-height: 100vh;
      padding: 20px;
    }

    .chat-container {
      width: 100%;
      max-width: 600px;
      background-color: white;
      border-radius: 8px;
      box-shadow: 0 10px 30px rgba(0, 0, 0, 0.1);
      overflow: hidden;
    }

    .chat-header {
      background: linear-gradient(135deg, #e94d1c, #d43e0a);
      color: white;
      padding: 20px;
      text-align: center;
      font-weight: 600;
      font-size: 20px;
      border-radius: 8px 8px 0 0;
      position: relative;
    }

    .chat-header h2 {
      margin: 0;
      font-size: 22px;
      font-weight: 600;
      display: inline-block;
    }

    .chat-header h2 i {
      margin-right: 10px;
    }

    #chat-box {
      height: 450px;
      padding: 20px;
      overflow-y: auto;
      background-color: #f9f9f9;
    }

    .message {
      margin-bottom: 15px;
      max-width: 80%;
      word-wrap: break-word;
      position: relative;
    }

    .join-message {
      text-align: center;
      color: #888;
      font-size: 14px;
      margin: 15px 0;
      background-color: #f0f0f0;
      padding: 8px 15px;
      border-radius: 20px;
      display: inline-block;
      position: relative;
      left: 50%;
      transform: translateX(-50%);
    }

    .sender-message {
      margin-left: auto;
      background-color: #e94d1c;
      color: white;
      border-radius: 18px 18px 0 18px;
      padding: 12px 15px;
      box-shadow: 0 2px 5px rgba(233, 77, 28, 0.2);
    }

    .receiver-message {
      margin-right: auto;
      background-color: #f0f2f5;
      color: #333;
      border-radius: 18px 18px 18px 0;
      padding: 12px 15px;
      box-shadow: 0 2px 5px rgba(0, 0, 0, 0.05);
    }

    .message-info {
      font-size: 12px;
      color: #888;
      margin-bottom: 5px;
      font-weight: 500;
    }

    .chat-input {
      display: flex;
      padding: 15px;
      background-color: #f9f9f9;
      border-top: 1px solid #eee;
    }

    #msgInput {
      flex: 1;
      padding: 15px;
      border: 1px solid #ddd;
      border-radius: 6px;
      outline: none;
      background-color: white;
      box-shadow: 0 2px 5px rgba(0, 0, 0, 0.05);
      font-size: 15px;
      transition: all 0.3s ease;
    }

    #msgInput:focus {
      border-color: #e94d1c;
      box-shadow: 0 0 0 3px rgba(233, 77, 28, 0.1);
    }

    .send-btn {
      background: linear-gradient(135deg, #e94d1c, #d43e0a);
      color: white;
      border: none;
      border-radius: 6px;
      width: 50px;
      height: 50px;
      margin-left: 10px;
      cursor: pointer;
      display: flex;
      justify-content: center;
      align-items: center;
      transition: all 0.3s ease;
      box-shadow: 0 4px 8px rgba(233, 77, 28, 0.2);
    }

    .send-btn:hover {
      transform: translateY(-2px);
      box-shadow: 0 5px 15px rgba(233, 77, 28, 0.3);
    }

    .send-btn:active {
      transform: scale(0.95);
    }

    /* 스크롤바 디자인 */
    #chat-box::-webkit-scrollbar {
      width: 8px;
    }

    #chat-box::-webkit-scrollbar-track {
      background: #f1f1f1;
      border-radius: 10px;
    }

    #chat-box::-webkit-scrollbar-thumb {
      background: #e0e0e0;
      border-radius: 10px;
    }

    #chat-box::-webkit-scrollbar-thumb:hover {
      background: #d43e0a;
    }

    /* 시간 표시 */
    .message-time {
      font-size: 11px;
      color: #aaa;
      margin-top: 5px;
      text-align: right;
    }

    .receiver-message .message-time {
      text-align: left;
    }

    /* 상태 표시 */
    .status-indicator {
      position: absolute;
      top: 15px;
      right: 15px;
      width: 10px;
      height: 10px;
      border-radius: 50%;
      background-color: #4CAF50;
      box-shadow: 0 0 5px #4CAF50;
    }

    .status-text {
      font-size: 12px;
      color: rgba(255, 255, 255, 0.8);
      position: absolute;
      top: 13px;
      right: 30px;
    }

    /* 반응형 디자인 */
    @media (max-width: 767px) {
      .chat-container {
        max-width: 100%;
        height: 100vh;
        border-radius: 0;
      }

      .chat-header {
        border-radius: 0;
      }

      #chat-box {
        height: calc(100vh - 130px);
      }
    }
  </style>
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
  const socket = new WebSocket("ws://localhost:8088/ws/chat");
  const chatBox = document.getElementById("chat-box");
  const msgInput = document.getElementById("msgInput");
  const currentUser = "yunji"; // 현재 사용자 이름

  // 현재 시간을 포맷팅하는 함수
  function getCurrentTime() {
    const now = new Date();
    const hours = now.getHours().toString().padStart(2, '0');
    const minutes = now.getMinutes().toString().padStart(2, '0');
    return `${hours}:${minutes}`;
  }

  socket.onopen = () => {
    // 입장 메시지 전송
    const joinMsg = {
      chatRoomId: 1,
      sender: currentUser,
      messageType: "JOIN",
      message: "입장합니다"
    };
    socket.send(JSON.stringify(joinMsg));
  };

  socket.onmessage = (event) => {
    const data = JSON.parse(event.data);

    if (data.messageType === "JOIN") {
      // 입장 메시지 표시
      const joinElement = document.createElement("div");
      joinElement.className = "join-message";
      joinElement.innerHTML = `<i class="fas fa-user-plus"></i> ${data.sender}님이 ${data.message}`;
      chatBox.appendChild(joinElement);
    } else {
      // 채팅 메시지 표시
      const messageDiv = document.createElement("div");
      const isMine = data.sender === currentUser;
      const currentTime = getCurrentTime();

      messageDiv.className = `message ${isMine ? 'sender-message' : 'receiver-message'}`;

      if (!isMine) {
        const nameSpan = document.createElement("div");
        nameSpan.className = "message-info";
        nameSpan.innerHTML = `<i class="fas fa-user"></i> ${data.sender}`;
        messageDiv.appendChild(nameSpan);
      }

      const messageContent = document.createElement("div");
      messageContent.className = "message-content";
      messageContent.textContent = data.message;
      messageDiv.appendChild(messageContent);

      const timeSpan = document.createElement("div");
      timeSpan.className = "message-time";
      timeSpan.textContent = currentTime;
      messageDiv.appendChild(timeSpan);

      chatBox.appendChild(messageDiv);
    }

    // 스크롤을 항상 아래로 유지
    chatBox.scrollTop = chatBox.scrollHeight;
  };

  socket.onclose = () => {
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
        chatRoomId: 1,
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
</script>
</body>
</html>

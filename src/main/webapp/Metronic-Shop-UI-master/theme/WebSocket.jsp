<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"> <!-- 모바일에서도 잘 보이게 설정 -->
    <title>실시간 채팅</title>

    <!-- 구글에서 제공하는 한글 폰트 불러오기 -->
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;500;600;700&display=swap"
          rel="stylesheet">

    <!-- 채팅창 디자인용 아이콘 폰트 (말풍선, 전송 버튼 등) -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">

    <!-- WebSocket 채팅 전용 CSS 연결 (페이지 경로 자동 인식) -->
    <link href="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/css/WebSocket.css" rel="stylesheet">
</head>


<body>
<!-- 채팅 전체 틀을 감싸는 div -->
<div class="chat-container">

    <!-- 채팅창 상단: 제목, 상태표시 -->
    <div class="chat-header">
        <h2><i class="fas fa-comments"></i> 실시간 채팅</h2> <!-- 아이콘 + 제목 -->
        <div class="status-indicator"></div> <!-- 원형 표시등 (온라인/오프라인) -->
        <span class="status-text">온라인</span> <!-- 현재 상태 텍스트 -->
    </div>

    <!-- 메시지를 보여줄 공간 -->
    <div id="chat-box">
        <script>
            // 사용자 닉네임 표시 (JSP 세션에서 가져옴)
            <%--const userNickname = "${sessionScope.userNickname}" || "상대방"; -> 상대방이 아닌, 자신의 닉네임을 가지고 오고 있었음--%>
            const targetNickname = "${targetNickname}" || "상대방";     //ChattingRestController에서 가져오기
            console.log("상대방 이메일 나오나 확인 : " + targetNickname);

            // 웹페이지가 열리면 과거 채팅 메시지를 먼저 가져오자!
            window.onload = () => {
                // 서버에서 chatRoomId에 해당하는 채팅 메시지들을 요청함
                fetch(`/chat/messages?chatRoomId=${chatRoomId}`)
                    .then(res => res.json()) // 응답 받은 데이터를 JSON(문자처럼 생긴 데이터)으로 바꿔줌
                    .then(messages => {
                        // 메시지를 거꾸로(옛날 → 최근 순으로) 정렬함
                        messages.reverse().forEach(msg => {
                            // 내 메시지인지 확인 (내 이메일이랑 같으면 내 메시지!)
                            const isMine = msg.memberEmail1 === currentUser;

                            // 메시지를 감싸는 큰 박스(div)를 만듦
                            const messageWrapper = document.createElement("div");
                            // 내가 보낸 메시지면 'my-message-wrapper', 아니면 'other-message-wrapper' 클래스를 줌
                            messageWrapper.className = isMine ? "message-wrapper my-message-wrapper" : "message-wrapper other-message-wrapper";

                                const nicknameElement = document.createElement("div");
                                nicknameElement.className = "message-nickname";
                                nicknameElement.textContent = targetNickname; // 서버에서 JSP로 전달된 값 사용
                                messageWrapper.appendChild(nicknameElement);

                            // 실제 메시지 내용이 들어가는 박스를 만들고, 안에 텍스트를 넣음
                            const messageElement = document.createElement("div");
                            // 내가 보낸 건 sender-message, 받은 건 receiver-message 스타일로 꾸밈
                            messageElement.className = `message ${isMine ? 'sender-message' : 'receiver-message'}`;
                            messageElement.textContent = msg.messageContent;
                            //console.log("메시지 시간 값 확인", msg.chatMessageDate);

                            // 시간 표시할 박스를 만들어요
                            const timeElement = document.createElement("div");
                            timeElement.className = "message-time";
                            // const date = new Date(msg.chatMessageDate); // DB에서 오는 timestamp, 그런데 형식 안맞아서 오류
                            //
                            // timeElement.textContent = date.toLocaleTimeString('ko-KR', {
                            //     hour: '2-digit',
                            //     minute: '2-digit'
                            // });

                            // DB에서 받은 시간 데이터로 날짜 객체 만들기
                            const date = new Date(msg.sentTime); // DB에서 오는 timestamp, 그런데 형식 안맞아서 오류
                            // 한국식으로 시:분만 보여주기 (예: 오후 02:15)
                            timeElement.textContent = date.toLocaleTimeString('ko-KR', {
                                hour: '2-digit',
                                minute: '2-digit'
                            });
                            // 메시지 박스에 메시지와 시간 박스를 넣기
                            messageWrapper.appendChild(messageElement);
                            messageWrapper.appendChild(timeElement);
                            // 채팅창에 이 메시지 박스를 넣기
                            chatBox.appendChild(messageWrapper);
                        });
                        // 채팅창을 제일 아래로 스크롤 (가장 최근 메시지 보이게)
                        chatBox.scrollTop = chatBox.scrollHeight; // 스크롤 맨 아래로
                    });
            };
        </script>

    </div>

    <!-- 메시지를 입력하는 입력창 + 전송 버튼 -->
    <div class="chat-input">
        <input type="text" id="msgInput" placeholder="메시지를 입력하세요..."/>
        <button class="send-btn" onclick="sendMessage()">
            <i class="fas fa-paper-plane"></i>
        </button>
    </div>
</div>

<!-- JavaScript 부분 -->
<script>

    // 현재 로그인한 사용자의 이메일 (서버 세션에서 가져옴)
    // 서버에서 전달받은 실제 채팅방 ID
    const chatRoomId = ${chatRoomId};
    <%--const currentUser = "${sessionScope.userEmail}" || "이메일이 안나옴";--%>
    const currentUser = "${userEmail}" || "이메일이 안나옴";
    const currentUserNickname = "${currentUserNickname}" || "닉네임이 안나옴";
    console.log("채팅방 아이디 잘 나오나 확인하기 로그 : " + chatRoomId)
    console.log("이메일 잘 나오나 확인하기 로그 : " + currentUser)
    console.log("닉네임 잘 나오나 확인하기 로그 : " + currentUserNickname)

    // URL 주소에서 상대방 이메일을 찾아서 저장
    const urlParams = new URLSearchParams(window.location.search); // 예: ?targetEmail=abc@test.com
    const targetEmail = urlParams.get('targetEmail') || "상대방";

    // 두 사람의 이메일을 정렬해서 고유한 채팅방 ID 생성
    // 채팅방 ID를 고유하게 만들기 위한 해시 함수
    // 해시 함수 없애기, 채팅방 ID 조회 후 JSP에 전달하는 방식으로 바꿈
    // String.prototype.hashCode = function() {
    //   let hash = 0;
    //   for (let i = 0; i < this.length; i++) {
    //     hash = ((hash << 5) - hash) + this.charCodeAt(i);
    //     hash = hash & hash; // 32비트 정수로 유지
    //   }
    //   return Math.abs(hash); // 음수 방지
    // };
    //
    // const chatRoomId = [currentUser, targetEmail].sort().join('_').hashCode();

    // WebSocket으로 서버 연결 (채팅 실시간 처리용)
    const socket = new WebSocket("ws://localhost:8088/ws/chat");

    // HTML 요소 변수 저장
    const chatBox = document.getElementById("chat-box");
    const msgInput = document.getElementById("msgInput");

    // 현재 시간 포맷 함수 (예: 오후 2:30)
    function getCurrentTime() {
        const now = new Date();
        const hours = now.getHours();
        const minutes = now.getMinutes().toString().padStart(2, '0');
        const ampm = hours >= 12 ? '오후' : '오전';
        const displayHours = hours % 12 || 12;
        return `${ampm} ${displayHours}:${minutes}`;
    }

    // 연결되었을 때 실행
    socket.onopen = () => {
        // 서버에 입장 메시지를 보냄
        const joinMsg = {
            chatRoomId: chatRoomId,
            sender: currentUser,
            receiver: targetEmail,
            senderNickname: currentUserNickname,  // 표시용으로 추가하기
            messageType: "JOIN",
           // message: "입장했습니다"
        };
        socket.send(JSON.stringify(joinMsg)); // JSON 형식으로 서버에 전송 / JS객체 → 문자열

        // 채팅창 상단에 "누구와 대화 중" 표시
        const roomInfoElement = document.createElement("div");
        roomInfoElement.className = "join-message";
        roomInfoElement.innerHTML = `<i class="fas fa-info-circle"></i> ${targetNickname}님과의 대화방입니다`;
        chatBox.appendChild(roomInfoElement);
    };

    // 메시지를 받았을 때 실행
    socket.onmessage = (event) => {
        console.log("수신된 메시지:", event.data);
        const data = JSON.parse(event.data); // JSON 문자열 → 객체로 변환

        if (data.messageType === "JOIN" || data.messageType === "LEAVE") {
            return; //카톡처럼 누가 입장했는지는 필요 없기때문에 화면 출력은 하지 않기 위해 추가
        }

        // const sendTime = data.timestamp
        //         ? new Date(data.timestamp).toLocaleTimeString('ko-KR', { hour: '2-digit', minute: '2-digit' })
        //         : getCurrentTime(); // fallback

        //timeElement.textContent = sendTime;

        <%--if (data.messageType === "JOIN") {--%>
        <%--    // 누가 들어왔는지 채팅창에 표시--%>
        <%--    const joinElement = document.createElement("div");--%>
        <%--    joinElement.className = "join-message";--%>
        <%--    &lt;%&ndash;// joinElement.innerHTML = `<i class="fas fa-user-plus"></i> ${data.sender}님이 ${data.message}`;&ndash;%&gt;--%>
        <%--    &lt;%&ndash; joinElement.innerHTML = `<i class="fas fa-user-plus"></i> ${data.senderNickname}님이 ${data.message}`;&ndash;%&gt;--%>
        <%--    joinElement.innerHTML = `<i class="fas fa-user-plus"></i> \${data.sender || data.senderNickname}님이 ${data.message}`;--%>
        <%--    console.log("수신 메시지:", data);--%>

        <%--    chatBox.appendChild(joinElement);--%>
        //     }
        <%-- if (data.messageType === "LEAVE") {--%>
        <%--    // 누가 나갔는지 표시--%>
        <%--    const leaveElement = document.createElement("div");--%>
        <%--    leaveElement.className = "join-message";--%>
        <%--    &lt;%&ndash;//leaveElement.innerHTML = `<i class="fas fa-user-minus"></i> ${data.sender}님이 ${data.message}`;&ndash;%&gt;--%>
        <%--    &lt;%&ndash;leaveElement.innerHTML = `<i class="fas fa-user-minus"></i> ${data.senderNickname}님이 ${data.message}`;&ndash;%&gt;--%>
        <%--    leaveElement.innerHTML = `<i class="fas fa-user-plus"></i> ${data.sender || data.senderNickname}님이 ${data.message}`;--%>
        <%--    console.log("발신 메시지:", data);--%>
        <%--    chatBox.appendChild(leaveElement);--%>

        <%--} --%>
        if (data.messageType === "TALK") {
            // 일반 채팅 메시지일 때
            const isMine = data.sender === currentUser; // 내 메시지인지 확인
            //const currentTime = getCurrentTime(); // 현재 시간 가져오기
            const currentTime = data.timestamp
                ? new Date(data.timestamp).toLocaleTimeString('ko-KR', {hour: '2-digit', minute: '2-digit'})
                : getCurrentTime();
            console.log("시간 체크 :  " + data.timestamp)  // 시간 잘나오나 체크

            // 메시지 감싸는 틀 생성 (내 메시지인지 아닌지 구분용)
            const messageWrapper = document.createElement("div");
            messageWrapper.className = isMine ? "message-wrapper my-message-wrapper" : "message-wrapper other-message-wrapper";

            // 실제 메시지 내용
            const messageElement = document.createElement("div");
            messageElement.className = `message ${isMine ? 'sender-message' : 'receiver-message'}`;
            messageElement.textContent = data.message;

            // 메시지 시간 표시
            const timeElement = document.createElement("div");
            timeElement.className = "message-time";
            timeElement.textContent = currentTime;

            // 상대방 메시지일 경우, 이름도 표시
            if (!isMine) {
                const infoElement = document.createElement("div");
                infoElement.className = "message-info";
                // 여기 순서 바꿔줘보기
                //infoElement.textContent = data.sender || data.senderNickname;
                infoElement.textContent = data.senderNickname || data.sender;
                messageWrapper.appendChild(infoElement);
            }

            // 메시지와 시간을 채팅창에 추가
            messageWrapper.appendChild(messageElement);
            messageWrapper.appendChild(timeElement);  // 시간 붙이기
            chatBox.appendChild(messageWrapper);  //전체 메시지를 화면에 추가
        }

        // 항상 스크롤을 아래로 유지해서 최근 메시지 보기 편하게
        chatBox.scrollTop = chatBox.scrollHeight;
    };

    // 연결이 끊겼을 때
    socket.onclose = () => {
        const leaveMsg = {
            chatRoomId: chatRoomId,
            sender: currentUser,
            receiver: targetEmail,
            senderNickname: currentUserNickname,  // ✅ 추가
            messageType: "LEAVE",
            //message: "퇴장했습니다"
        };
        try {
            socket.send(JSON.stringify(leaveMsg));
        } catch (e) {
            // 이미 끊어졌으면 무시
        }

        // 연결 종료 메시지 표시
        const disconnectMsg = document.createElement("div");
        disconnectMsg.className = "join-message";
        disconnectMsg.innerHTML = `<i class="fas fa-exclamation-triangle"></i> 서버와의 연결이 종료되었습니다.`;
        chatBox.appendChild(disconnectMsg);

        // 상태 표시 변경 (오프라인으로)
        document.querySelector('.status-indicator').style.backgroundColor = '#F44336';
        document.querySelector('.status-text').textContent = '오프라인';
    };

    // 메시지를 서버로 보내는 함수
    function sendMessage() {
        const msg = msgInput.value.trim(); // 공백 제거
        if (msg !== "") {
            const sendMsg = {
                chatRoomId: chatRoomId,
                sender: currentUser,
                receiver: targetEmail,
                senderNickname: currentUserNickname,  // ✅ 추가
                messageType: "TALK",
                message: msg,
                timestamp: new Date().toISOString()  // 시간 정보 추가! (ex: "2025-05-04T11:35:00.000Z")
            };
            socket.send(JSON.stringify(sendMsg));
            msgInput.value = ""; // 입력창 비우기
        }
    }

    // Enter 키로 메시지 전송되도록 설정
    msgInput.addEventListener("keypress", (e) => {
        if (e.key === "Enter") {
            e.preventDefault(); // 기본 Enter 동작 막기
            sendMessage();
        }
    });

    // 5초마다 서버 연결 상태 확인
    function checkConnection() {
        if (socket.readyState === WebSocket.OPEN) {
            document.querySelector('.status-indicator').style.backgroundColor = '#4CAF50';
            document.querySelector('.status-text').textContent = '온라인';
        } else {
            document.querySelector('.status-indicator').style.backgroundColor = '#F44336';
            document.querySelector('.status-text').textContent = '오프라인';
        }
    }

    setInterval(checkConnection, 5000);

    // 페이지를 나가기 전에 서버에 퇴장 메시지 보내기
    // window.addEventListener('beforeunload', () => {
    //     if (socket.readyState === WebSocket.OPEN) {
    //         const leaveMsg = {
    //             chatRoomId: chatRoomId,
    //             sender: currentUser,
    //             receiver: targetEmail,
    //             messageType: "LEAVE",
    //             message: "퇴장했습니다"
    //         };
    //         socket.send(JSON.stringify(leaveMsg));
    //     }
    // });
    // 과거 메시지 불러오는 스크립트
    <%--  $.ajax({--%>
    <%--    url: "/chatHistory.do",--%>
    <%--    method: "GET",--%>
    <%--    //data: { chatRoomId: chatRoomId },--%>
    <%--    success: function(messages) {--%>
    <%--      console.log("불러온 메시지들:", messages);--%>
    <%--      messages.forEach(function(msg) {--%>
    <%--        const isMine = msg.sender === currentUser;--%>

    <%--        const messageWrapper = document.createElement("div");--%>
    <%--        messageWrapper.className = isMine ? "message-wrapper my-message-wrapper" : "message-wrapper other-message-wrapper";--%>

    <%--        const messageElement = document.createElement("div");--%>
    <%--        messageElement.className = `message ${isMine ? 'sender-message' : 'receiver-message'}`;--%>
    <%--        messageElement.textContent = msg.messageContent;--%>

    <%--        const timeElement = document.createElement("div");--%>
    <%--        timeElement.className = "message-time";--%>
    <%--        timeElement.textContent = new Date(msg.sentTime).toLocaleTimeString('ko-KR', { hour: '2-digit', minute: '2-digit' });--%>

    <%--        if (!isMine) {--%>
    <%--          const infoElement = document.createElement("div");--%>
    <%--          infoElement.className = "message-info";--%>
    <%--          infoElement.textContent = msg.senderNickname || msg.sender;--%>
    <%--          messageWrapper.appendChild(infoElement);--%>
    <%--        }--%>

    <%--        messageWrapper.appendChild(messageElement);--%>
    <%--        messageWrapper.appendChild(timeElement);--%>
    <%--        chatBox.appendChild(messageWrapper);--%>
    <%--      });--%>

    <%--      // 스크롤 가장 아래로 이동--%>
    <%--      chatBox.scrollTop = chatBox.scrollHeight;--%>
    <%--    },--%>
    <%--    error: function() {--%>
    <%--      console.error("이전 메시지 불러오기 실패");--%>
    <%--    }--%>
    <%--  });--%>

</script>
</body>
</html>

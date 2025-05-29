package com.example.common.config.websocket;

//import com.example.common.biz.chatMessage.ChatMessageService;
//import com.example.common.biz.chatMessage.ChatMessageVO;
//import com.example.common.biz.chatRoom.ChatRoomService;
//import com.example.common.biz.chatRoom.ChatRoomVO;
import com.example.common.biz.chatMessage2.ChatMessageService;
import com.example.common.biz.chatMessage2.ChatMessageVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.time.Instant;

@Slf4j
@Component
@RequiredArgsConstructor
public class WebSocketChatHandler extends TextWebSocketHandler {
    private final ObjectMapper mapper;
    private final ChatMessageService chatMessageService;  // 추가
    //private final ChatRoomService chatRoomService;        // 추가

    // 소켓 세션을 저장할 Set
    private final Set<WebSocketSession> sessions = new HashSet<>();

    // 채팅방 id와 소켓 세션을 저장할 Map
    private final Map<Integer, Set<WebSocketSession>> chatRoomSessionMap = new HashMap<>();

    // 소켓 연결 확인
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // TODO Auto-generated method stub
        sessions.add(session);
        session.sendMessage(new TextMessage("WebSocket 연결 완료"));
    }

    // 소켓 메세지 처리
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        log.info("세션 열려있나? {}", session.isOpen());

        String payload = message.getPayload();
        log.info("payload {}", payload);
        log.info("chatRoomSessionMap keySet: {}", chatRoomSessionMap.keySet());


        // 클라이언트로부터 받은 메세지를 chatMessageWebsocketVO 변환
        // 즉, JSON을 자바 객체로 변환하기
        // 왜냐하면, 웹소켓은 WebSocket은 "문자열이나 바이트"만 주고받을 수 있기 때문
        ChatMessageWebsocketVO chatMessageWebsocketVO = mapper.readValue(payload, ChatMessageWebsocketVO.class);
        log.info("session {}", chatMessageWebsocketVO.toString());
        System.out.println("====" + chatMessageWebsocketVO);
        // timestamp 세팅 추가 (채팅 보낼때 보낸 시간도 표시)
        chatMessageWebsocketVO.setTimestamp(Instant.now().toString());

        log.info("chatRoomSessionMap keySet: {}", chatRoomSessionMap.keySet());
        log.info("현재 요청 chatRoomId: {}", chatMessageWebsocketVO.getChatRoomId());
        log.info("chatRoomSessions: {}", chatRoomSessionMap.get(chatMessageWebsocketVO.getChatRoomId()));

        //디비에 저장될 수 있도록 추가 - 1번
        // WebSocket VO → DB 저장용 VO로 변환BOARD
        // CHAT_MESSAGE 테이블에 채팅 메시지 저장
        if (chatMessageWebsocketVO.getMessageType().equals(ChatMessageWebsocketVO.MessageType.TALK)) {
            ChatMessageVO dbVO = new ChatMessageVO();
            dbVO.setChatRoomId(chatMessageWebsocketVO.getChatRoomId());
            dbVO.setMemberEmail1(chatMessageWebsocketVO.getSender());
            dbVO.setMemberEmail2(chatMessageWebsocketVO.getReceiver());
            dbVO.setMessageContent(chatMessageWebsocketVO.getMessage());
            dbVO.setSentTime(new Timestamp(System.currentTimeMillis())); // 서버 시간 기준으로 디비에 저장
            //시간 보내는 것도 저장

            // DB에 채팅 내용을 저장
            chatMessageService.insert(dbVO);
        }

//
//        // 디비에 저장될 수 있도록 추가 - 2번
//        // CHAT_ROOM 테이블에 ‘마지막 메시지’만 업데이트
//        ChatRoomVO roomVO = new ChatRoomVO();
//        roomVO.setChatRoomId(chatMessageWebsocketVO.getChatRoomId());  // 채팅방 번호 객체에 담기
//        roomVO.setLastMessage(chatMessageWebsocketVO.getMessage());  // 마지막 메시지를 객체에 담기
//        // 채팅방의 마지막 메시지 업데이트
//        chatRoomService.update(roomVO);


        // 채팅방이 없으면 생성
        if (!chatRoomSessionMap.containsKey(chatMessageWebsocketVO.getChatRoomId())) {
            chatRoomSessionMap.put(chatMessageWebsocketVO.getChatRoomId(), new HashSet<>());
        }

        //메세지 타입에 따라 분기
        if (chatMessageWebsocketVO.getMessageType().equals(ChatMessageWebsocketVO.MessageType.JOIN)) {
            // 입장 메세지
            chatRoomSessionMap.get(chatMessageWebsocketVO.getChatRoomId()).add(session);
            //chatMessageVO.setMessage(chatMessageVO.getSender() + "님이 입장하셨습니다.");
            //chatMessageWebsocketVO.setMessage(chatMessageWebsocketVO.getSenderNickname() + "님이 입장하셨습니다.");
        } else if (chatMessageWebsocketVO.getMessageType().equals(ChatMessageWebsocketVO.MessageType.LEAVE)) {
            // 퇴장 메세지
            chatRoomSessionMap.get(chatMessageWebsocketVO.getChatRoomId()).remove(session);
            //chatMessageVO.setMessage(chatMessageVO.getSender() + "님이 퇴장하셨습니다.");
            //chatMessageWebsocketVO.setMessage(chatMessageWebsocketVO.getSenderNickname() + "님이 퇴장하셨습니다.");
        }

        // NPE 방지를 위한 안전 장치
        Set<WebSocketSession> chatRoomSessions = chatRoomSessionMap.get(chatMessageWebsocketVO.getChatRoomId());
        if (chatRoomSessions != null) {
            // 채팅 메세지 전송
            for (WebSocketSession webSocketSession : chatRoomSessions) {
                webSocketSession.sendMessage(new TextMessage(mapper.writeValueAsString(chatMessageWebsocketVO)));
            }
        }
    }

    // 소켓 연결 종료
    // WebSocket 연결이 종료되었을 때 호출되는 메서드
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // TODO Auto-generated method stub
        log.info("{} 연결 끊김", session.getId());
        // 2) 전체 세션 목록에서 해당 세션 제거
        // sessions는 전체 연결된 WebSocket 세션을 관리하는 Set
        sessions.remove(session);

        // 3) 각 채팅방의 세션 목록에서도 해당 세션을 제거
        // chatRoomSessionMap: 채팅방 ID(Integer)를 키로, 채팅방에 참여한 세션 집합(Set<WebSocketSession>)을 값으로 갖는 Map
        for (Set<WebSocketSession> chatRoomSessions : chatRoomSessionMap.values()) {
            // 채팅방의 세션 목록에서 연결이 끊긴 세션 제거
            chatRoomSessions.remove(session);

        }
    }
}

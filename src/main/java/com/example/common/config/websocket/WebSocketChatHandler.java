package com.example.common.config.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

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

    // 소켓 세션을 저장할 Set
    private final Set<WebSocketSession> sessions = new HashSet<>();

    // 채팅방 id와 소켓 세션을 저장할 Map
    private final Map<Long, Set<WebSocketSession>> chatRoomSessionMap = new HashMap<>();

    // 소켓 연결 확인
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // TODO Auto-generated method stub
        log.info("{} 연결됨", session.getId());
        sessions.add(session);
        session.sendMessage(new TextMessage("WebSocket 연결 완료"));
    }

    // 소켓 메세지 처리
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        log.info("payload {}", payload);

        // 클라이언트로부터 받은 메세지를 ChatMessageDto로 변환
        ChatMessageVO chatMessageVO = mapper.readValue(payload, ChatMessageVO.class);
        log.info("session {}", chatMessageVO.toString());

        // timestamp 세팅 추가 (채팅 보낼때 보낸 시간도 표시)
        chatMessageVO.setTimestamp(Instant.now().toString());

        // 채팅방이 없으면 생성
        if (!chatRoomSessionMap.containsKey(chatMessageVO.getChatRoomId())) {
            chatRoomSessionMap.put(chatMessageVO.getChatRoomId(), new HashSet<>());
        }

        // 메세지 타입에 따라 분기
        if(chatMessageVO.getMessageType().equals(ChatMessageVO.MessageType.JOIN)){
            // 입장 메세지
            chatRoomSessionMap.get(chatMessageVO.getChatRoomId()).add(session);
            //chatMessageVO.setMessage(chatMessageVO.getSender() + "님이 입장하셨습니다.");
            chatMessageVO.setMessage(chatMessageVO.getSenderNickname() + "님이 입장하셨습니다.");
        }
        else if(chatMessageVO.getMessageType().equals(ChatMessageVO.MessageType.LEAVE)){
            // 퇴장 메세지
            chatRoomSessionMap.get(chatMessageVO.getChatRoomId()).remove(session);
            //chatMessageVO.setMessage(chatMessageVO.getSender() + "님이 퇴장하셨습니다.");
            chatMessageVO.setMessage(chatMessageVO.getSenderNickname() + "님이 퇴장하셨습니다.");
        }

        // NPE 방지를 위한 안전 장치
        Set<WebSocketSession> chatRoomSessions = chatRoomSessionMap.get(chatMessageVO.getChatRoomId());
        if (chatRoomSessions != null) {
            // 채팅 메세지 전송
            for(WebSocketSession webSocketSession : chatRoomSessions){
                webSocketSession.sendMessage(new TextMessage(mapper.writeValueAsString(chatMessageVO)));
            }
        }
    }


    // 소켓 연결 종료
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // TODO Auto-generated method stub
        log.info("{} 연결 끊김", session.getId());
        sessions.remove(session);
        session.sendMessage(new TextMessage("WebSocket 연결 종료"));
    }
}

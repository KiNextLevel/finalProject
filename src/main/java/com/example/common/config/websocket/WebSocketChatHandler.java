package com.example.common.config.websocket;

//import com.example.common.biz.chatMessage.ChatMessageService;
//import com.example.common.biz.chatMessage.ChatMessageVO;
//import com.example.common.biz.chatRoom.ChatRoomService;
//import com.example.common.biz.chatRoom.ChatRoomVO;
import com.example.common.biz.chatMessage2.ChatMessageService;
import com.example.common.biz.chatMessage2.ChatMessageVO;
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

        // 클라이언트로부터 받은 메세지를 ChatMessageDto로 변환
        ChatMessageWebsocketVO chatMessageWebsocketVO = mapper.readValue(payload, ChatMessageWebsocketVO.class);
        log.info("session {}", chatMessageWebsocketVO.toString());
        System.out.println("====" + chatMessageWebsocketVO);
        // timestamp 세팅 추가 (채팅 보낼때 보낸 시간도 표시)
        chatMessageWebsocketVO.setTimestamp(Instant.now().toString());

         //디비에 저장될 수 있도록 추가 - 1번
        // WebSocket VO → DB 저장용 VO로 변환
        // CHAT_MESSAGE 테이블에 채팅 메시지 저장
       ChatMessageVO dbVO = new ChatMessageVO();
        dbVO.setChatRoomId(chatMessageWebsocketVO.getChatRoomId());
        dbVO.setSenderEmail(chatMessageWebsocketVO.getSender());
        dbVO.setMessageContent(chatMessageWebsocketVO.getMessage());

        // DB에 채팅 내용을 저장
        chatMessageService.insert(dbVO);




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

        // 메세지 타입에 따라 분기
        if (chatMessageWebsocketVO.getMessageType().equals(ChatMessageWebsocketVO.MessageType.JOIN)) {
            // 입장 메세지
            chatRoomSessionMap.get(chatMessageWebsocketVO.getChatRoomId()).add(session);
            //chatMessageVO.setMessage(chatMessageVO.getSender() + "님이 입장하셨습니다.");
            chatMessageWebsocketVO.setMessage(chatMessageWebsocketVO.getSenderNickname() + "님이 입장하셨습니다.");
        } else if (chatMessageWebsocketVO.getMessageType().equals(ChatMessageWebsocketVO.MessageType.LEAVE)) {
            // 퇴장 메세지
            chatRoomSessionMap.get(chatMessageWebsocketVO.getChatRoomId()).remove(session);
            //chatMessageVO.setMessage(chatMessageVO.getSender() + "님이 퇴장하셨습니다.");
            chatMessageWebsocketVO.setMessage(chatMessageWebsocketVO.getSenderNickname() + "님이 퇴장하셨습니다.");
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
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // TODO Auto-generated method stub
        log.info("{} 연결 끊김", session.getId());
        sessions.remove(session);
//        session.sendMessage(new TextMessage("WebSocket 연결 종료"));
    }
}

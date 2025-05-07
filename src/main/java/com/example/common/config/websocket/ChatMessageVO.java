package com.example.common.config.websocket;

import lombok.*;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
class ChatMessageVO {
    // 메시지 타입 : 입장, 채팅, 퇴장
    public enum MessageType{
        JOIN, TALK, LEAVE
    }

    private MessageType messageType; // 메시지 타입
    private Long chatRoomId; // 방번호
    private String sender; // 발신자
    private String message; // 메시지
    private String senderNickname;  // 발신자 닉네임, 닉네임 표시 되게 하기 위해 추가
    private String timestamp; // 채팅 보내는 시간 추가

}
